package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.foodjournalapp_sc.R
import id.ac.ubaya.informatika.foodjournalapp_sc.databinding.FoodlogListItemBinding
import id.ac.ubaya.informatika.foodjournalapp_sc.model.Food
import kotlinx.android.synthetic.main.foodlog_list_item.view.*

class FoodLogListAdapter(val foodList:ArrayList<Food>, val adapterOnClick : (Any) -> Unit)
    :RecyclerView.Adapter<FoodLogListAdapter.FoodLogViewHolder>() {
    class FoodLogViewHolder(var view:FoodlogListItemBinding): RecyclerView.ViewHolder(view.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodLogViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<FoodlogListItemBinding>(inflater, R.layout.foodlog_list_item,parent, false)
        return FoodLogViewHolder(view)
    }
    override fun getItemCount(): Int {
        return foodList.size
    }
    override fun onBindViewHolder(holder: FoodLogViewHolder, position: Int) {
        holder.view.food = foodList[position] //food instaniated

    }
}
