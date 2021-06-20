package id.ac.ubaya.informatika.foodjournalapp_sc.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import id.ac.ubaya.informatika.foodjournalapp_sc.R

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.hostFragment)
//        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
//        NavigationUI.setupWithNavController(navView, navController)
//        bottomNav.setupWithNavController(navController)

    }

}