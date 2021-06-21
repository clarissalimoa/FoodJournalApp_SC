package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.foodjournalapp_sc.R
import id.ac.ubaya.informatika.foodjournalapp_sc.databinding.FragmentFoodLogBinding
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailUserViewModel

class FoodLogFragment : Fragment() {
    private lateinit var viewModel: DetailUserViewModel
    private lateinit var dataBinding: FragmentFoodLogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_food_log, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentFoodLogBinding>(inflater, R.layout.fragment_food_log, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)
        viewModel.fetchCurrentUser()

        observeViewModel()

    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
            //Total dari foodlog hari ini
          //  dataBinding.caloriesToday = it.caloriesTarget //masih salah | Mo ini error jadi ku comment du
          // dataBinding.statToday = if(dataBinding.caloriesToday <= 0.5*it.caloriesTarget) "LOW"
          // else if(dataBinding.caloriesToday > 0.5*it.caloriesTarget && dataBinding.caloriesToday <= it.caloriesTarget) "NORMAL"
          // else "EXCEED"
        })
    }


}