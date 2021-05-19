package dev.dizzy1021.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.EntryPointAccessors
import dev.dizzy1021.core.adapter.GameAdapter
import dev.dizzy1021.core.domain.model.Game
import dev.dizzy1021.core.utils.State
import dev.dizzy1021.gamie.R
import dev.dizzy1021.gamie.di.DynamicFeaturesDependencies
import dev.dizzy1021.gamie.util.isNetworkAvailable
import dev.dizzy1021.search.databinding.FragmentSearchBinding
import javax.inject.Inject

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding as FragmentSearchBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: SearchViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        DaggerSearchComponent.factory().create(
            EntryPointAccessors.fromApplication(requireContext(), DynamicFeaturesDependencies::class.java)
        ).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener { activity?.onBackPressed() }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()

        val actionBar = requireActivity().findViewById<Toolbar>(R.id.main_toolbar)
        actionBar.title = null
        actionBar.isGone = true

        val adapter = GameAdapter()

        binding.rvSearch.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvSearch.adapter = adapter
        binding.rvSearch.setHasFixedSize(true)

        adapter.setOnItemClickCallback(object : GameAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Game) {
                navigateToDetailRepository(data.id)
            }
        })

        if (isNetworkAvailable(requireActivity())) {

            val searchManager =
                requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager

            binding.networkError.isGone = true
            binding.searchData.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            binding.searchData.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    if (query != null) {
                        viewModel.games(query).observe(viewLifecycleOwner, { game ->
                            if (game != null) {
                                when(game.state) {
                                    State.PENDING -> {
                                        binding.progressBar.isVisible = true
                                        binding.rvSearch.isGone = true
                                        binding.networkError.isGone = true
                                    }
                                    State.SUCCESS -> {
                                        binding.progressBar.isGone = true
                                        binding.networkError.isGone = true
                                        binding.rvSearch.isVisible = true

                                        game.data?.let { adapter.submitList(it) }
                                    }
                                    State.FAILURE -> {
                                        binding.progressBar.isGone = true
                                        binding.networkError.isVisible = true
                                        binding.rvSearch.isGone = true
                                    }
                                }
                            }
                        })
                        return true
                    } else {
                        return false
                    }
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return if (newText != "") {
                        binding.searchBanner.isGone = true
                        true
                    } else {
                        if(!binding.rvSearch.isVisible) binding.searchBanner.isVisible = true
                        false
                    }
                }

            })


        } else {
            binding.searchBanner.isGone = true
            binding.networkError.isVisible = true
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun navigateToDetailRepository(id: Int) {
        val toDetail =
            SearchFragmentDirections.actionSearchFragmentToDetailFragment2(id)
        findNavController().navigate(toDetail)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}