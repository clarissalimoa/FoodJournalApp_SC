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
import id.ac.ubaya.informatika.foodjournalapp_sc.R
import id.ac.ubaya.informatika.foodjournalapp_sc.databinding.FragmentLogMealBinding
import id.ac.ubaya.informatika.foodjournalapp_sc.model.User
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailFoodViewModel
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailUserViewModel
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.fragment_log_meal.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


class ProfileFragment : Fragment(), RadioClick, ButtoneditUserClick{

    private lateinit var viewModel2:DetailUserViewModel
    private lateinit var dataBinding: FragmentLogMealBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel2 = ViewModelProvider(this).get(DetailUserViewModel::class.java)

       // dataBinding.listener = this

        val sdf = SimpleDateFormat("dd MMMM yyyy")
        val currentDate = sdf.format(Date())
        textViewFLTanggal.text = currentDate

        val sdf1 = SimpleDateFormat("yyyy-MM-dd")
        val currentDate1 = sdf1.format(Date())

        viewModel2.fetchCurrentUser()

        observeViewModel()

    }

    private fun observeViewModel() {

        viewModel2.userLD.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            dataBinding.user = it
        })

    }

    override fun onButtonEditUserClick(v: View, obj: User) {

        var bmr:Double = if(txtGender.text.toString()=="male") 13.397*obj.weight + 4.799*obj.height - 5.677*obj.age + 88.362 else 9.247*obj.weight + 3.098*obj.height - 4.330*obj.age + 447.593
        var target:Int = if(obj.goal=="maintain") bmr.roundToInt()
        else if(obj.goal=="gain") (bmr*115/100).roundToInt()
        else if(obj.goal=="lose") (bmr*85/100).roundToInt()
        else 0
        viewModel2.update(obj.name, obj.age, obj.gender, obj.height, obj.weight,obj.goal, bmr, target, obj.uuid)
        Toast.makeText(v.context, "User Data Updated", Toast.LENGTH_SHORT).show()
    }

    override fun onRadioClick(v: View, goal: String, obj: User) {
        obj.goal = goal
    }


}