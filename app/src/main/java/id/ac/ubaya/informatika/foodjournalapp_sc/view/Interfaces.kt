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
    fun onButtonEditUserClick(v: View , obj:User )
}

interface RadioClick {
    fun onRadioClick(v:View, goal:String, obj:User)
}

interface FabLogAMealClickListener{
    fun onFabLogAMealClicked(v:View)
}


interface DeleteSavedFoodListener{
    fun onDeleteSavedFoodClicked(v:View, food:Food)
}

interface ChooseSavedFoodListener{
    fun onChooseSaveFoodClicked(v:View, food:Food)
}