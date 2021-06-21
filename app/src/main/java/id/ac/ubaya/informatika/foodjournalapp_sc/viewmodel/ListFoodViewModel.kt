package id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.foodjournalapp_sc.model.Food
import id.ac.ubaya.informatika.foodjournalapp_sc.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListFoodViewModel (application: Application) : AndroidViewModel(application), CoroutineScope {
    val foodLD = MutableLiveData<List<Food>>()
    val foodErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh()
    {
        loadingLD.value = true
        foodErrorLD.value = false
        launch {
            val db = buildDb(getApplication())
            //Room.databaseBuilder( getApplication(),  TodoDatabase::class.java , "newtododb").build() dari week 8
            //foodLD.value = db.foodDao().selectAllFood()
        }
    }
}