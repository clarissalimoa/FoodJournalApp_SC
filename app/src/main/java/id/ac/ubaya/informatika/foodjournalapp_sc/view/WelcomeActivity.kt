package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.foodjournalapp_sc.R
import id.ac.ubaya.informatika.foodjournalapp_sc.databinding.FragmentWelcomeScreenBinding
import id.ac.ubaya.informatika.foodjournalapp_sc.model.User
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailUserViewModel
import kotlinx.android.synthetic.main.fragment_welcome_screen.*
import kotlin.math.roundToInt

class WelcomeActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailUserViewModel
    private lateinit var dataBinding: FragmentWelcomeScreenBinding
    private lateinit var user: User

    override fun onResume() {
        super.onResume()
        val genders = resources.getStringArray(R.array.genders)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_gender, genders)
        txtGender.setAdapter(arrayAdapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)

        //cek apakah user pernah ngisi
        if(viewModel.jml >=1)
        {
            val intent = Intent(this, MainActivity::class.java).apply {}
            startActivity(intent)
            this.finish()
        }

        btStart.setOnClickListener {
            val radio = this.findViewById<RadioButton>(radioGroupGoals.checkedRadioButtonId)
            val name = txtName.text.toString()
            val age = txtAge.text.toString().toInt()
            val height = txtHeight.text.toString().toDouble()
            val weight = txtWeight.text.toString().toDouble()
            var bmr:Double = if(txtGender.text.toString()=="male") 13397*weight + 4799*height - 5677*age + 88362 else 9247*weight + 3098*height - 4330*age + 447593
            var target:Int = if(radio.tag.toString()=="maintain") bmr.roundToInt()
            else if(radio.tag.toString()=="gain") (bmr*115/100).roundToInt()
            else if(radio.tag.toString()=="lose") (bmr*85/100).roundToInt()
            else 0

            var user = User(txtName.text.toString(), age, txtGender.text.toString(),
                txtHeight.text.toString().toInt(),txtWeight.text.toString().toInt(),
                radio.tag.toString(), bmr, target)
            val list = listOf(user)
            viewModel.addUser(list)
            Toast.makeText(this, "Let's get start!" + bmr + target, Toast.LENGTH_LONG).show()
            //
//            Navigation.findNavController(it).popBackStack()

            //Pindah fragment with navigation
            // val action = WelcomeScreenFragmentDirections.actionWelcomeToLog()
            // Navigation.findNavController(it).navigate(action)
            val intent = Intent(this, MainActivity::class.java).apply {}
            //startActivity(intent)
            //this.finish()
        }

    }
}