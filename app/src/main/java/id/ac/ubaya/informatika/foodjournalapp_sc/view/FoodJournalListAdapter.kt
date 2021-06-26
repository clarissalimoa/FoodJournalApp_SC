package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.foodjournalapp_sc.R
import id.ac.ubaya.informatika.foodjournalapp_sc.databinding.HistoryItemBinding
import id.ac.ubaya.informatika.foodjournalapp_sc.model.FoodHistory
import id.ac.ubaya.informatika.foodjournalapp_sc.model.History

class FoodJournalListAdapter(val HistoryList:ArrayList<History>, val calory: Int?) : RecyclerView.Adapter<FoodJournalListAdapter.FoodViewHolder>() {

    class FoodViewHolder(var view: HistoryItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<HistoryItemBinding>(inflater, R.layout.history_item, parent, false)
        return FoodViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.view.food = HistoryList[position]

    }

    override fun getItemCount(): Int {
        return HistoryList.size
    }

    fun update(newHistoryList: List<History>) {
        HistoryList.clear()
        HistoryList.addAll(newHistoryList)
        notifyDataSetChanged()
    }



}