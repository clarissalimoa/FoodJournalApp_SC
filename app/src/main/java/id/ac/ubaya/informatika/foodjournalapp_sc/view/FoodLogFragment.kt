package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButtonToggleGroup
import id.ac.ubaya.informatika.foodjournalapp_sc.R
import id.ac.ubaya.informatika.foodjournalapp_sc.databinding.FragmentFoodLogBinding
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailUserViewModel
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.ListFoodHistoryViewModel
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.ListFoodViewModel
import kotlinx.android.synthetic.main.fragment_food_log.*
import kotlinx.android.synthetic.main.fragment_log_meal.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class FoodLogFragment : Fragment() {
    private lateinit var viewModel: DetailUserViewModel
    private lateinit var viewModelHistory: ListFoodHistoryViewModel
    private lateinit var dataBinding: FragmentFoodLogBinding
    private val foodLogListAdapter = FoodLogListAdapter(arrayListOf(),{})

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_food_log, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentFoodLogBinding>(inflater, R.layout.fragment_food_log, container, false)
        return dataBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)
        viewModel.fetchCurrentUser()

        viewModelHistory = ViewModelProvider(this).get(ListFoodHistoryViewModel::class.java)

        recViewLogs.layoutManager = LinearLayoutManager(context)
        recViewLogs.adapter = foodLogListAdapter
        val currentDate = SimpleDateFormat("dd MMM yyyy").format(Date()).toString()

        viewModelHistory.todayList(currentDate)
        dataBinding.dateToday = currentDate
        observeViewModel()

    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
            //Total dari foodlog hari ini
            val calToday = 0 //masih salah, harusnya select SUM today's foodlog
            dataBinding.caloriesToday = (calToday).toString()
            dataBinding.statToday = if(calToday <= 0.5*it.caloriesTarget) "LOW"
            else if(calToday > 0.5*it.caloriesTarget && calToday <= it.caloriesTarget) "NORMAL"
            else "EXCEED"

            floatingActionButton.setOnClickListener {
                val action = FoodLogFragmentDirections.actionItemFoodLogToItemLogAMeal()
                Navigation.findNavController(it).navigate(action)
            }
        })
    }



}