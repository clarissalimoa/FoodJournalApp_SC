package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.foodjournalapp_sc.R
import id.ac.ubaya.informatika.foodjournalapp_sc.databinding.FragmentProfileBinding
import id.ac.ubaya.informatika.foodjournalapp_sc.model.User
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailUserViewModel
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.txtAge
import kotlinx.android.synthetic.main.fragment_profile.txtGender
import kotlinx.android.synthetic.main.fragment_profile.txtHeight
import kotlinx.android.synthetic.main.fragment_profile.txtName
import kotlinx.android.synthetic.main.fragment_profile.txtWeight
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


class ProfileFragment : Fragment(), RadioClick, ButtoneditUserClick {

    private lateinit var viewModel2: DetailUserViewModel
    private lateinit var dataBinding: FragmentProfileBinding
    private var selectedGoal: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater, R.layout.fragment_profile, container, false)
        return dataBinding.root
    }


    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel2 = ViewModelProvider(this).get(DetailUserViewModel::class.java)

        dataBinding.listener = this
        dataBinding.radioListener = this

//        val sdf = SimpleDateFormat("dd MMMM yyyy")
//        val currentDate = sdf.format(Date())
//        textViewFLTanggal.text = currentDate
//        dataBinding.dateToday = currentDate
//
//        val sdf1 = SimpleDateFormat("yyyy-MM-dd")
//        val currentDate1 = sdf1.format(Date())

        viewModel2.fetchCurrentUser()

        observeViewModel()

    }


    override fun onResume() {
        super.onResume()
        val genders = resources.getStringArray(R.array.genders)
        val arrayAdapter = this.context?.let { ArrayAdapter(it, R.layout.dropdown_gender, genders) }
        txtGender.setAdapter(arrayAdapter)
    }

    override fun onButtonEditUserClick(v: View, obj: User) {
        if (txtName.text.isNullOrEmpty() || txtAge.text.isNullOrEmpty() ||txtHeight.text.isNullOrEmpty() ||txtWeight.text.isNullOrEmpty() ||txtGender.text.isNullOrEmpty()) {
            Toast.makeText(v.context, "Fill all the blank!", Toast.LENGTH_SHORT).show()
        } else {
            val name = txtName.text.toString()
            val age = txtAge.text.toString().toInt()
            val height = txtHeight.text.toString().toDouble()
            val weight = txtWeight.text.toString().toDouble()
            var bmr: Double = if (txtGender.text.toString() == "male") 13.397 * weight + 4.799 * height - 5.677 * age + 88.362 else 9.247 * weight + 3.098 * height - 4.330 * age + 447.593
            var target: Int = if (selectedGoal == "maintain") bmr.roundToInt()
            else if (selectedGoal == "gain") (bmr * 115 / 100).roundToInt()
            else if (selectedGoal == "lose") (bmr * 85 / 100).roundToInt()
            else 0
            viewModel2.update(name, age, txtGender.text.toString(), height.toInt(), weight.toInt(), selectedGoal, bmr, target, obj.uuid)
            Toast.makeText(v.context, "User Data Updated", Toast.LENGTH_SHORT).show()
        }

    }

    private fun observeViewModel() {

        viewModel2.userLD.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            dataBinding.user = it
            selectedGoal = it.goal
        })

    }

    override fun onRadioClick(v: View, goal: String, obj: User) {
        obj.goal = goal
        selectedGoal = goal

    }


}