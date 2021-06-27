package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.foodjournalapp_sc.R
import id.ac.ubaya.informatika.foodjournalapp_sc.databinding.SavedFoodListItemBinding
import id.ac.ubaya.informatika.foodjournalapp_sc.model.Food
import id.ac.ubaya.informatika.foodjournalapp_sc.model.FoodHistory
import id.ac.ubaya.informatika.foodjournalapp_sc.model.History
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailFoodViewModel
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailUserViewModel
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.ListFoodViewModel
import kotlinx.android.synthetic.main.fragment_log_meal.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ChooseFoodListAdapter(val foodList: ArrayList<Food>, val adapterOnClick: (Any) -> Unit, val owner: ViewModelStoreOwner, val viewLifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<ChooseFoodListAdapter.FoodViewHolder>(), DeleteSavedFoodListener, ChooseSavedFoodListener {
    private lateinit var viewModel: DetailFoodViewModel
    private lateinit var viewModel2: DetailUserViewModel
    private var calory: Int = 0
    private var goals: Int = 0


    class FoodViewHolder(var view: SavedFoodListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<SavedFoodListItemBinding>(inflater, R.layout.saved_food_list_item, parent, false)
        return FoodViewHolder(view)


    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        viewModel = ViewModelProvider(owner).get(DetailFoodViewModel::class.java)
        viewModel2 = ViewModelProvider(owner).get(DetailUserViewModel::class.java)
        holder.view.food = foodList[position]
        holder.view.chooseListener = this
        holder.view.deleteListener = this
        observeViewModel()
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun update(newfoodList: List<Food>) {
        foodList.clear()
        foodList.addAll(newfoodList)
        notifyDataSetChanged()
    }

    private fun observeViewModel() {
        viewModel.totalFoodsCalories.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            calory = it
        })

        viewModel2.userLD.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            goals = it.caloriesTarget
        })
    }

    override fun onDeleteSavedFoodClicked(v: View, food: Food) {
        val builder = AlertDialog.Builder(v.context)
        builder.setTitle("Delete this food?")
        builder.setMessage(food.name + " - " + food.calories + " cal")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            viewModel.delete(food)
            val action = ChooseFoodFragmentDirections.actionChooseFoodToLogMeal()
            Navigation.findNavController(v).navigate(action)
            Toast.makeText(v.context, "Food deleted", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton(android.R.string.no) { dialog, which ->

        }
        builder.show()
    }

    override fun onChooseSaveFoodClicked(v: View, food: Food) {
        val builder = AlertDialog.Builder(v.context)
        builder.setTitle("Log this food?")
        builder.setMessage(food.name + " - " + food.calories + " cal")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val currentDate = sdf.format(Date())
            val sdf1 = SimpleDateFormat("dd MMMM yyyy")
            val currentDate1 = sdf1.format(Date())
            viewModel.addFoodHistory(listOf(FoodHistory(food.name, food.calories.toInt(), currentDate)))

            var jumlahHistory = 0;
            viewModel.cekHistory()
            viewModel2.fetchCurrentUser()
            viewModel.angka1.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                jumlahHistory = it
                if (jumlahHistory >= 1) {
                    var status = "";
                    if ((calory + food.calories.toInt()) >= goals) {
                        status = "EXCEED"
                    } else if ((calory + food.calories.toInt()) >= 0.51 * goals) {
                        status = "NORMAL"
                    } else {
                        status = "LOW"
                    }

                    viewModel.updateHistory(food.calories.toInt(), status, currentDate1)
                } else {
                    var status = "";
                    if ((calory + food.calories.toInt()) >= goals) {
                        status = "EXCEED"
                    } else if ((calory + food.calories.toInt()) >= 0.51 * goals) {
                        status = "NORMAL"
                    } else {
                        status = "LOW"
                    }
                    val history = History(currentDate1, 1, food.calories.toInt(), status)
                    viewModel.addhistory(history)
                }
            })



            Toast.makeText(v.context, "Food Log added", Toast.LENGTH_SHORT).show()
            val action = ChooseFoodFragmentDirections.actionChooseFoodToLogMeal()
            Navigation.findNavController(v).navigate(action)
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->

        }
        builder.show()
    }


}