package dev.dizzy1021.gamie.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import dev.dizzy1021.core.domain.model.Game
import dev.dizzy1021.core.utils.State
import dev.dizzy1021.gamie.R
import dev.dizzy1021.gamie.databinding.FragmentDetailBinding

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding as FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    private var favorited: Boolean = false
    private var game: Game? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idGame = DetailFragmentArgs.fromBundle(arguments as Bundle).id

        viewModel.game(idGame).observe(viewLifecycleOwner, { res ->
            if (res != null) {
                when(res.state) {
                    State.PENDING -> {
                        binding.progressBar.isVisible = true
                        binding.iconStar.isGone = true
                        binding.titlePublisher.isGone = true
                        binding.networkError.isGone = true
                    }
                    State.SUCCESS -> {
                        binding.progressBar.isGone = true
                        binding.networkError.isGone = true
                        binding.iconStar.isVisible = true
                        binding.titlePublisher.isVisible = true

                        game = res.data
                        val actionBar = requireActivity().findViewById<Toolbar>(R.id.main_toolbar)

                        if (game?.isFavorite == true) {
                            actionBar.menu.findItem(R.id.add_favorite).icon =
                                ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_favorite_24)
                        } else {
                            actionBar.menu.findItem(R.id.add_favorite).icon =
                                ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_favorite_border_24)
                        }


                        with(binding) {
                            Glide.with(this@DetailFragment)
                                .load(game?.poster)
                                .error(dev.dizzy1021.core.R.drawable.ic_no_image)
                                .placeholder(dev.dizzy1021.core.R.drawable.ic_loading)
                                .into(gamesPoster)

                            Glide.with(this@DetailFragment)
                                .load(game?.publisherPoster)
                                .error(dev.dizzy1021.core.R.drawable.ic_no_image)
                                .placeholder(dev.dizzy1021.core.R.drawable.ic_loading)
                                .into(gamesPublisherPoster)

                            gamesTitle.text = game?.name
                            gamesRating.text = game?.rating.toString()
                            gamesGenres.text = game?.genres

                            val stringBuilder = StringBuilder()

                            gamesDate.text = stringBuilder.append(getString(R.string.release_at)).append(" ").append(game?.date)
                            gamesDesc.text = game?.desc
                            gamesPublisher.text = game?.publisher

                        }

                    }
                    State.FAILURE -> {
                        binding.progressBar.isGone = true
                        binding.networkError.isVisible = true
                        binding.iconStar.isVisible = true
                        binding.titlePublisher.isVisible = true
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()

        val actionBar = requireActivity().findViewById<Toolbar>(R.id.main_toolbar)
        actionBar.title = null
        actionBar.isVisible = true

        inflater.inflate(R.menu.detail_menu, menu)

        actionBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        actionBar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.share -> {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, game?.website)
                startActivity(Intent.createChooser(shareIntent, getString(R.string.share)))
                return true
            }
            R.id.add_favorite -> {
                favorited = !favorited
                game?.isFavorite = favorited

                game?.let { viewModel.addFavorite(it) }

                if (favorited) {
                    item.setIcon(R.drawable.ic_baseline_favorite_24)
                } else {
                    item.setIcon(R.drawable.ic_baseline_favorite_border_24)
                }

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}