package dev.dizzy1021.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.dizzy1021.core.R
import dev.dizzy1021.core.databinding.ItemListGameBinding
import dev.dizzy1021.core.domain.model.Game

class GameAdapter : RecyclerView.Adapter<GameAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    interface OnItemClickCallback {
        fun onItemClicked(data: Game)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListGameBinding.bind(itemView)

        fun bind(items: Game) {
            with(binding) {
                this.gamesRating.text = items.rating.toString()
                this.gamesTitle.text = items.name

                Glide.with(itemView.context)
                    .load(items.poster)
                    .error(R.drawable.ic_no_image)
                    .placeholder(R.drawable.ic_loading)
                    .into(this.posterGame)

            }

            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(items) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_game, parent, false)
        )

    override fun onBindViewHolder(holder: GameAdapter.ListViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val diffCallback = object : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Game>) = differ.submitList(list)
}