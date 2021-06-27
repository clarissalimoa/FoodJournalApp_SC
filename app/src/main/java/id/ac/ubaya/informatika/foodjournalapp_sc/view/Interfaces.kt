package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.view.View
import id.ac.ubaya.informatika.foodjournalapp_sc.model.Data
import id.ac.ubaya.informatika.foodjournalapp_sc.model.Food
import id.ac.ubaya.informatika.foodjournalapp_sc.model.User


interface UserSaveWelcomeChangesListener{
    fun UserSaveWelcomeChanges(v:View)
}

interface ButtonAddMealClick{
    fun onButtonAddMealClick(v: View )
}

interface ButtoneditUserClick{
    fun onButtonEditUserClick(v: View )
}
