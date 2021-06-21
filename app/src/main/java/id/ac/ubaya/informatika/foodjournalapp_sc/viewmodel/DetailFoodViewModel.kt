package id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.foodjournalapp_sc.model.Food
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import id.ac.ubaya.informatika.foodjournalapp_sc.util.buildDb

class DetailFoodViewModel (application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val foodLD = MutableLiveData<Food>()

    fun addFood(list:List<Food>) {
        launch {
            val db = buildDb(getApplication())
            //Room.databaseBuilder( getApplication(), TodoDatabase::class.java, "newtododb").build()// dari week 8
            //db.foodDao().insertAll(*list.toTypedArray())
        }
    }


    fun fetch(uuid:Int)
    {
        launch{
            val db = buildDb(getApplication())
           // foodLD.value = db.foodDao().selectFood(uuid)
        }
    }



    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}