package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.foodjournalapp_sc.R
import id.ac.ubaya.informatika.foodjournalapp_sc.model.User
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailUserViewModel
import kotlinx.android.synthetic.main.fragment_welcome_screen.*
import kotlin.math.roundToInt


class WelcomeScreenFragment : Fragment() {

    private lateinit var viewModel:DetailUserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome_screen, container, false)
    }

    override fun onResume() {
        super.onResume()
        val genders = resources.getStringArray(R.array.genders)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_gender, genders)
        txtGender.setAdapter(arrayAdapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)
        btStart.setOnClickListener {
            val radio = view.findViewById<RadioButton>(radioGroupGoals.checkedRadioButtonId)
            val name = txtName.text.toString()
            val age = txtAge.text.toString().toInt()
            val height = txtHeight.text.toString().toDouble()
            val weight = txtWeight.text.toString().toDouble()
            var bmr:Double = if(txtGender.text.toString()=="male") 13397*weight + 4799*height - 5677*age + 88362 else 9247*weight + 3098*height - 4330*age + 447593
            var target:Int = if(radio.getTag().toString()=="maintain") bmr.roundToInt()
            else if(radio.getTag().toString()=="gain") (bmr*115/100).roundToInt()
            else if(radio.getTag().toString()=="lose") (bmr*85/100).roundToInt()
            else 0

            var user = User(txtName.text.toString(), age, txtGender.text.toString(),
                    txtHeight.text.toString().toInt(),txtWeight.text.toString().toInt(),
                    radio.getTag().toString(), bmr, target)
            val list = listOf(user)
            viewModel.addUser(list)
            Toast.makeText(view.context, "Let's get start!", Toast.LENGTH_LONG).show()
            //
//            Navigation.findNavController(it).popBackStack()

            //Pindah fragment with navigation
            val action = WelcomeScreenFragmentDirections.actionWelcomeToLog()
            Navigation.findNavController(it).navigate(action)
        }

    }

}