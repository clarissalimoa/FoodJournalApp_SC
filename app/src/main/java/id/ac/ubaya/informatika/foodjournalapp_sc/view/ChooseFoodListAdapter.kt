package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.foodjournalapp_sc.R
import id.ac.ubaya.informatika.foodjournalapp_sc.databinding.SavedFoodListItemBinding
import id.ac.ubaya.informatika.foodjournalapp_sc.model.Food
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailFoodViewModel
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.ListFoodViewModel

class ChooseFoodListAdapter(val foodList:ArrayList<Food>, val adapterOnClick : (Any) -> Unit, val owner: ViewModelStoreOwner) : RecyclerView.Adapter<ChooseFoodListAdapter.FoodViewHolder>()
        ,DeleteSavedFoodListener, ChooseSavedFoodListener{
    private lateinit var viewModel: DetailFoodViewModel


    class FoodViewHolder(var view: SavedFoodListItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<SavedFoodListItemBinding>(inflater, R.layout.saved_food_list_item, parent, false)
        return FoodViewHolder(view)


    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        viewModel = ViewModelProvider(owner).get(DetailFoodViewModel::class.java)
        holder.view.food = foodList[position]
        holder.view.chooseListener =this
        holder.view.deleteListener =this
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun update(newfoodList: List<Food>) {
        foodList.clear()
        foodList.addAll(newfoodList)
        notifyDataSetChanged()
    }

    override fun onDeleteSavedFoodClicked(v: View, food: Food) {
        val builder = AlertDialog.Builder(v.context)
        builder.setTitle("Delete this food?")
        builder.setMessage(food.name+" - "+food.calories+" cal")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            viewModel.delete(food)
            val action = ChooseFoodFragmentDirections.actionChooseFoodToLogMeal()
            Navigation.findNavController(v).navigate(action)
            Toast.makeText(v.context,"Food deleted", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton(android.R.string.no) { dialog, which ->

        }
        builder.show()


    }

    override fun onChooseSaveFoodClicked(v: View, food: Food) {
        val builder = AlertDialog.Builder(v.context)
        builder.setTitle("Log this food?")
        builder.setMessage(food.name+" - "+food.calories+" cal")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            viewModel.addFood(listOf(food))
            Toast.makeText(v.context,"Food added", Toast.LENGTH_SHORT).show()
            val action = ChooseFoodFragmentDirections.actionChooseFoodToLogMeal()
            Navigation.findNavController(v).navigate(action)
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->

        }
        builder.show()
    }




}