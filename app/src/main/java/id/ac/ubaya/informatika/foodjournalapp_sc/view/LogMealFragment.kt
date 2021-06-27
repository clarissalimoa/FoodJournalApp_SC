package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.foodjournalapp_sc.R
import id.ac.ubaya.informatika.foodjournalapp_sc.databinding.FragmentLogMealBinding
import id.ac.ubaya.informatika.foodjournalapp_sc.model.*
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailFoodViewModel
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailUserViewModel
import kotlinx.android.synthetic.main.fragment_log_meal.*
import java.text.SimpleDateFormat
import java.util.*

class LogMealFragment : Fragment(), ButtonAddMealClick {
    private lateinit var viewModel: DetailFoodViewModel
    private lateinit var viewModel2: DetailUserViewModel
    private lateinit var dataBinding: FragmentLogMealBinding
    var goals = 0;
    var calory = 0;

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentLogMealBinding>(inflater, R.layout.fragment_log_meal, container, false)
        return dataBinding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailFoodViewModel::class.java)
        viewModel2 = ViewModelProvider(this).get(DetailUserViewModel::class.java)

        dataBinding.listener = this

        val sdf = SimpleDateFormat("dd MMMM yyyy")
        val currentDate = sdf.format(Date())
        textViewFLTanggal.text = currentDate

        val sdf1 = SimpleDateFormat("yyyy-MM-dd")
        val currentDate1 = sdf1.format(Date())

        viewModel.selectTotalCalory()
        viewModel.totalCaloriesToday(currentDate1)
        viewModel2.fetchCurrentUser()
        viewModel.cekHistory()

        observeViewModel()

        buttonFLList.setOnClickListener {
            val action = LogMealFragmentDirections.actionLogMealToChooseFood()
            Navigation.findNavController(it).navigate(action)
        }
        btBackFoodLog.setOnClickListener {
            val action = LogMealFragmentDirections.actionLogMealToFoodLog()
            Navigation.findNavController(it).navigate(action)
        }

    }

    private fun observeViewModel() {
        viewModel.totalFoodsCalories.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            dataBinding.sisa = it
            calory = it
        })

        viewModel2.userLD.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            dataBinding.user = it
            goals = it.caloriesTarget
        })
    }

//    override fun onButtonAddMealClick(v: View) {
//        Toast.makeText(v.context, "Food added", Toast.LENGTH_SHORT).show()
//    }

    @SuppressLint("SimpleDateFormat")
    override fun onButtonAddMealClick(v: View) {
        if (txtFLKalori.text.toString() != "0" && txtFLName.text.toString() != "") {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val currentDate = sdf.format(Date())

            val sdf1 = SimpleDateFormat("dd MMMM yyyy")
            val currentDate1 = sdf1.format(Date())

            var makanan = FoodHistory(txtFLName.text.toString(), txtFLKalori.text.toString().toInt(), currentDate)
            val list = listOf(makanan)
            viewModel.addFoodHistory(list)

            if (checkBoxFLSimpan.isChecked) {
                var makanan2 = Food(txtFLName.text.toString(), txtFLKalori.text.toString())
                val list2 = listOf(makanan2)
                viewModel.addFood(list2)
            }
            var help1 = 2;
            viewModel.cekHistory()
            viewModel.angka1.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                help1 = it
            })

//            Toast.makeText(v.context, "" + help1, Toast.LENGTH_SHORT).show()

            if (help1 >= 1) {
                var status = "";
                if ((calory + txtFLKalori.text.toString().toInt()) >= dataBinding.user?.caloriesTarget!!) {
                    status = "EXCEED"
                } else if ((calory + txtFLKalori.text.toString().toInt()) >= 0.51 * dataBinding.user?.caloriesTarget!!) {
                    status = "NORMAL"
                } else {
                    status = "LOW"
                }

                viewModel.updateHistory(txtFLKalori.text.toString().toInt(), status, currentDate1)
            } else {
                var status = "";
                if ((calory + txtFLKalori.text.toString().toInt()) >= dataBinding.user?.caloriesTarget!!) {
                    status = "EXCEED"
                } else if ((calory + txtFLKalori.text.toString().toInt()) >= 0.51 * dataBinding.user?.caloriesTarget!!) {
                    status = "NORMAL"
                } else {
                    status = "LOW"
                }
                val history = History(currentDate1, 1, txtFLKalori.text.toString().toInt(), status)
                viewModel.addhistory(history)
            }

            Toast.makeText(v.context, "Food Log added", Toast.LENGTH_SHORT).show()
            txtFLKalori.setText("")
            txtFLName.setText("")
            viewModel.selectTotalCalory()
            viewModel.totalCaloriesToday(currentDate)
            viewModel2.fetchCurrentUser()
            observeViewModel()

        } else {
            Toast.makeText(v.context, "Input Not Valid", Toast.LENGTH_SHORT).show()
        }

    }
}


