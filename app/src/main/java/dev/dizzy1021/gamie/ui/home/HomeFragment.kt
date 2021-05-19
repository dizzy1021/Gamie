package dev.dizzy1021.gamie.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.dizzy1021.core.adapter.GameAdapter
import dev.dizzy1021.core.domain.model.Game
import dev.dizzy1021.core.utils.State
import dev.dizzy1021.gamie.R
import dev.dizzy1021.gamie.databinding.FragmentHomeBinding
import dev.dizzy1021.gamie.util.isNetworkAvailable

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = GameAdapter()

        binding.rvHome.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvHome.adapter = adapter
        binding.rvHome.setHasFixedSize(true)

        adapter.setOnItemClickCallback(object : GameAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Game) {
                navigateToDetailRepository(data.id)
            }
        })

        if (isNetworkAvailable(requireActivity())) {
            viewModel.games.observe(viewLifecycleOwner, { game ->
                if (game != null) {
                    when (game.state) {
                        State.PENDING -> {
                            binding.progressBar.isVisible = true
                            binding.rvHome.isGone = true
                            binding.networkError.isGone = true
                        }
                        State.SUCCESS -> {
                            binding.progressBar.isGone = true
                            binding.networkError.isGone = true
                            binding.rvHome.isVisible = true

                            game.data?.let { adapter.submitList(it) }
                        }
                        State.FAILURE -> {
                            binding.progressBar.isGone = true
                            binding.networkError.isVisible = true
                            binding.rvHome.isGone = true
                        }
                    }
                }
            })
        } else {
            binding.progressBar.isGone = true
            binding.networkError.isVisible = true
            binding.rvHome.isGone = true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()

        val actionBar = requireActivity().findViewById<Toolbar>(R.id.main_toolbar)
        actionBar?.title = getString(R.string.home)
        actionBar?.isVisible = true

        inflater.inflate(R.menu.home_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
                return true
            }
            R.id.search -> {
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToDetailRepository(id: Int) {
        val toDetail =
            HomeFragmentDirections.actionHomeFragmentToDetailFragment2(id)
        findNavController().navigate(toDetail)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}