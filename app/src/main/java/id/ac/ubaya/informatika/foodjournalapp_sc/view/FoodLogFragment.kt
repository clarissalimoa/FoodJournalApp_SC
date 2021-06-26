package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private var calTarget:Int = 0

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
        val currentDateSqlFormat = SimpleDateFormat("yyyy-MM-dd").format(Date()).toString()

        viewModelHistory.todayList(currentDateSqlFormat)
        viewModelHistory.totalCaloriesToday(currentDateSqlFormat)

        dataBinding.dateToday = currentDate
        observeViewModel(view)

    }

    fun observeViewModel(v:View) {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
//            Toast.makeText(v.context, "User Logs: "+it.toString(), Toast.LENGTH_SHORT).show()

            dataBinding.user = it
            calTarget=it.caloriesTarget

            floatingActionButton.setOnClickListener {
                val action = FoodLogFragmentDirections.actionItemFoodLogToItemLogAMeal()
                Navigation.findNavController(it).navigate(action)
            }
        })

        viewModelHistory.foodsLD.observe(viewLifecycleOwner, Observer{
//            Toast.makeText(v.context, "Food Logs: "+it.toString(), Toast.LENGTH_SHORT).show()
            foodLogListAdapter.updateFoodHistoryList(it)
        })

        viewModelHistory.totalFoodsCalories.observe(viewLifecycleOwner, Observer{
//            Toast.makeText(v.context, "totalFoodsCalories: "+it.toString(), Toast.LENGTH_SHORT).show()
            //Total dari foodlog hari ini
            val calToday = it
            dataBinding.caloriesToday = (calToday).toString()
            dataBinding.statToday = if(calToday <= 0.5*calTarget) "LOW"
            else if(calToday > 0.5*calTarget && calToday <= calTarget) "NORMAL"
            else "EXCEED"

            dataBinding.progressLog = if(calToday==0) 0
            else if(calToday>0 && calToday*100/calTarget<=100) calToday*100/calTarget
            else 100
//            Toast.makeText(v.context, "progress: "+(calToday*100/calTarget).toString(), Toast.LENGTH_SHORT).show()
        })
    }



}