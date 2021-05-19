package dev.dizzy1021.favorite

import android.os.Bundle
import android.view.*
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
import dev.dizzy1021.favorite.databinding.FragmentFavoriteBinding
import dev.dizzy1021.gamie.R
import dev.dizzy1021.gamie.di.DynamicFeaturesDependencies
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding as FragmentFavoriteBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        DaggerFavoriteComponent.factory().create(
            EntryPointAccessors.fromApplication(requireContext(), DynamicFeaturesDependencies::class.java)
        ).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = GameAdapter()

        binding.rvFavorite.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvFavorite.adapter = adapter
        binding.rvFavorite.setHasFixedSize(true)

        adapter.setOnItemClickCallback(object : GameAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Game) {
                navigateToDetailRepository(data.id)
            }
        })

        viewModel.games.observe(viewLifecycleOwner, { game ->
            if (game != null) {
                binding.progressBar.isGone = true
                binding.emptyList.isGone = true
                binding.rvFavorite.isVisible = true

                adapter.submitList(game)

                if (adapter.itemCount == 0) {
                    binding.progressBar.isGone = true
                    binding.emptyList.isVisible = true
                    binding.rvFavorite.isGone = true
                }

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()

        val actionBar = requireActivity().findViewById<Toolbar>(R.id.main_toolbar)
        actionBar?.title = getString(R.string.favorite_games)
        actionBar?.isVisible = true

        actionBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        actionBar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun navigateToDetailRepository(id: Int) {
        val toDetail =
            FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment2(id)
        findNavController().navigate(toDetail)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}