package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.foodjournalapp_sc.R
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailUserViewModel
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.ListFoodViewModel
import kotlinx.android.synthetic.main.fragment_food_journal.*
import java.text.SimpleDateFormat
import java.util.*

class FoodJournalFragment : Fragment() {
    private lateinit var viewModel: ListFoodViewModel
    private var foodListAdapter = FoodJournalListAdapter(arrayListOf(), 0)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_food_journal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListFoodViewModel::class.java)
        viewModel.refreshFoodHistory()

        //val viewModel2 = ViewModelProvider(this).get(DetailUserViewModel::class.java)
       // foodListAdapter = FoodJournalListAdapter(arrayListOf(), viewModel2.goal())

        RecyclerViewFJHistory.layoutManager = LinearLayoutManager(context)
        RecyclerViewFJHistory.adapter = foodListAdapter

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.HistoryLD.observe(viewLifecycleOwner, Observer {
            foodListAdapter.update(it)
            val sdf = SimpleDateFormat("MMMM yyyy")
            val currentDate = sdf.format(Date())
            textViewFJBulan.text =  currentDate
        })
    }
}