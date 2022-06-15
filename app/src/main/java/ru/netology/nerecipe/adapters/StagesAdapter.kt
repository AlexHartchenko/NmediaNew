package ru.netology.nerecipe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.netology.nerecipe.data.Stage
import ru.netology.nerecipe.databinding.StagesViewingLayoutBinding

internal class StagesAdapter(
    private val interactionListener: RecipeInteractionListener
    ) : ListAdapter<Stage, StagesAdapter.ViewHolder>(DiffCallback)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StagesViewingLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder (
        private val binding: StagesViewingLayoutBinding,
        listener: RecipeInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var stage: Stage

        init {
//            binding.stageText.setOnLongClickListener {
//                binding.fabUp.visibility = View.VISIBLE
//                binding.fabDown.visibility = View.VISIBLE
//                binding.fabSetInvisible.visibility = View.VISIBLE
//                binding.fabDeleteStage.visibility = View.VISIBLE
//                return@setOnLongClickListener true
//            }
//            binding.fabUp.setOnClickListener {
//                listener.stageUp(stage)
//            }
//            binding.fabDown.setOnClickListener {
//                listener.stageDown(stage)
//            }
//            binding.fabSetInvisible.setOnClickListener {
//                binding.fabUp.visibility = View.INVISIBLE
//                binding.fabDown.visibility = View.INVISIBLE
//                binding.fabSetInvisible.visibility = View.INVISIBLE
//                binding.fabDeleteStage.visibility = View.INVISIBLE
//            }
//            binding.fabDeleteStage.setOnClickListener {
//                listener.deleteStage(stage)
//            }
        }
        fun bind(stage: Stage) {
            this.stage = stage
            binding.stageText.text = stage.text
            Glide.with(binding.stageImage)
                .load(stage.imageURL)
                .into(binding.stageImage)
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<Stage>() {

        override fun areItemsTheSame(oldItem: Stage, newItem: Stage) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Stage, newItem: Stage) =
            oldItem == newItem
    }
}