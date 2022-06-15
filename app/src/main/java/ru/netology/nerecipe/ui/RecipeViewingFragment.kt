package ru.netology.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nerecipe.R
import ru.netology.nerecipe.adapters.StagesAdapter
import ru.netology.nerecipe.data.Recipe
import ru.netology.nerecipe.databinding.RecipeViewingFragmentBinding
import ru.netology.nerecipe.valueToStringForShowing
import ru.netology.nerecipe.view_models.RecipeViewModel

class RecipeViewingFragment : Fragment() {

    private val args by navArgs<RecipeViewingFragmentArgs>()
    private val viewModel by activityViewModels<RecipeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.navToRecipeEdit.observe(this) { postContent ->
            val direction = RecipeViewingFragmentDirections
                .actionRecipeViewingFragmentToRecipeContentFragment(postContent)
            findNavController().navigate(direction)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = RecipeViewingFragmentBinding.inflate(
        layoutInflater, container, false
    ).also { binding ->

        val adapter = StagesAdapter(viewModel)
        binding.stagesRecyclerView.adapter = adapter
        viewModel.currentRecipe.observe(viewLifecycleOwner) {
                recipe ->
            if (recipe != null) {
                adapter.submitList(recipe.stages)
            }
        }
        val simpleCallback = object :
            ItemTouchHelper.SimpleCallback(ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.END) {
            override fun isLongPressDragEnabled(): Boolean {
                return true
            }
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                viewModel.moveStage(fromPosition, toPosition)
                return true
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.END) {
                    val position = viewHolder.adapterPosition
                    viewModel.deleteStage(position)
                    adapter.notifyItemRemoved(position)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.stagesRecyclerView)

        var recipeToViewing: Recipe = args.recipeToViewing

        viewModel.data.observe(viewLifecycleOwner) {recipes ->
            recipeToViewing = recipes.first {it.id == recipeToViewing.id}
            with(binding) {
                recipeName.text = recipeToViewing.name
                recipeAuthor.text = recipeToViewing.author
                recipeCategory.text = recipeToViewing.category.value
                viewingHeart.text = valueToStringForShowing(recipeToViewing.likes)
                viewingHeart.isChecked = recipeToViewing.likedByMe
                viewingHeart.setOnClickListener { viewModel.onHeartClicked(recipeToViewing) }
            }
            val popupMenu by lazy {
                PopupMenu(requireContext(), binding.feedFragmentMenuButton).apply {
                    inflate(R.menu.options_recipe)
                    setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.removeItem -> {
                                viewModel.removeRecipeById(recipeToViewing.id)
                                findNavController().popBackStack()
                                true
                            }
                            R.id.editItem -> {
                                viewModel.editRecipe(recipeToViewing)
                                true
                            }
                            else -> false
                        }
                    }
                }
            }
            binding.feedFragmentMenuButton.setOnClickListener {
                popupMenu.show()
            }
        }
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}