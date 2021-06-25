package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.foodjournalapp_sc.R
import id.ac.ubaya.informatika.foodjournalapp_sc.databinding.ActivityWelcomeBinding
import id.ac.ubaya.informatika.foodjournalapp_sc.model.User
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailUserViewModel
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlin.math.roundToInt


class WelcomeActivity : AppCompatActivity(),UserSaveWelcomeChangesListener {
    private lateinit var viewModel: DetailUserViewModel
    private lateinit var dataBinding: ActivityWelcomeBinding
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

        dataBinding = setContentView(this, R.layout.activity_welcome)
        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)
        observeViewModel()

        dataBinding.listener = this

        radioGroupGoals.setOnCheckedChangeListener(
                RadioGroup.OnCheckedChangeListener{ group, checkedId ->
                    val radio: RadioButton = findViewById(checkedId)
                    Toast.makeText(applicationContext," On checked change : ${radio.tag}",
                            Toast.LENGTH_SHORT).show()
                })


    }


    fun observeViewModel() {
        viewModel.fetchCurrentUser()
        viewModel.userLD.observe(this, Observer {
            if (it != null) {
                //Pindah activity
                val intent = Intent(this, MainActivity::class.java).apply {}
                startActivity(intent)
                this.finish()
            }
        })
    }

    override fun UserSaveWelcomeChanges(v: View) {
        val radio = findViewById<RadioButton>(radioGroupGoals.checkedRadioButtonId)
        val name = txtName.text.toString()
        val age = txtAge.text.toString().toInt()
        val height = txtHeight.text.toString().toDouble()
        val weight = txtWeight.text.toString().toDouble()
        var bmr:Double = if(txtGender.text.toString()=="male") 13397*weight + 4799*height - 5677*age + 88362 else 9247*weight + 3098*height - 4330*age + 447593
        var target:Int = if(radio.tag=="maintain") bmr.roundToInt()
        else if(radio.tag=="gain") (bmr*115/100).roundToInt()
        else if(radio.tag=="lose") (bmr*85/100).roundToInt()
        else 0

        var user = User(txtName.text.toString(), age, txtGender.text.toString(),
                txtHeight.text.toString().toInt(), txtWeight.text.toString().toInt(),
                radio.getTag().toString(), bmr, target)
        val list = listOf(user)
        viewModel.addUser(list)
        Toast.makeText(applicationContext, "Let's get start!", Toast.LENGTH_LONG).show()

        //Pindah activity
        val intent = Intent(this, MainActivity::class.java).apply {}
        startActivity(intent)
        this.finish()
    }



}