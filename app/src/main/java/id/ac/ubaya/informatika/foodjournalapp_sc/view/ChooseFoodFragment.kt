package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.foodjournalapp_sc.R
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailFoodViewModel
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailUserViewModel
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.ListFoodViewModel
import kotlinx.android.synthetic.main.fragment_choose_food.*
import kotlinx.android.synthetic.main.fragment_food_log.*

/**
 * A simple [Fragment] subclass.
 * Use the [ChooseFoodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChooseFoodFragment : Fragment() {
    private lateinit var viewModel: ListFoodViewModel
    private lateinit var savedFoodsAdapter:ChooseFoodListAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        savedFoodsAdapter = ChooseFoodListAdapter(arrayListOf(),{}, this, viewLifecycleOwner)
        return inflater.inflate(R.layout.fragment_choose_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListFoodViewModel::class.java)
        viewModel.refreshFood()

        recViewSavedFoods.layoutManager = LinearLayoutManager(context)
        recViewSavedFoods.adapter = savedFoodsAdapter
        observeViewModel(view)

        btBack.setOnClickListener {
            val action = ChooseFoodFragmentDirections.actionChooseFoodToLogMeal()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeViewModel(v:View) {
        viewModel.foodsLD.observe(viewLifecycleOwner, Observer{
            savedFoodsAdapter.update(it)
        })
    }



}