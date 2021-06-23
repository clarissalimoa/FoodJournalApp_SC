package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.foodjournalapp_sc.R
import id.ac.ubaya.informatika.foodjournalapp_sc.model.Food
import id.ac.ubaya.informatika.foodjournalapp_sc.model.FoodHistory
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailFoodViewModel
import kotlinx.android.synthetic.main.fragment_log_meal.*
import java.text.SimpleDateFormat
import java.util.*

class LogMealFragment : Fragment() {
    private lateinit var viewModel:DetailFoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_meal, container, false)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailFoodViewModel::class.java)
        buttonFLLog.setOnClickListener {
            val sdf = SimpleDateFormat("dd/MMMM/yyyy")
            val currentDate = sdf.format(Date())
            var makanan = FoodHistory(txtFLName.text.toString(), txtFLKalori.text.toString(),currentDate)
            val list = listOf(makanan)
           // viewModel.addFood(list)
            Toast.makeText(view.context, "Food added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack() // cara destroy dan back
        }
    }


}