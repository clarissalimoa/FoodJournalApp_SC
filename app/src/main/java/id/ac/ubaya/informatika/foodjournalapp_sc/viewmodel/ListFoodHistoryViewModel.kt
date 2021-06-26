package id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.foodjournalapp_sc.model.FoodHistory
import id.ac.ubaya.informatika.foodjournalapp_sc.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListFoodHistoryViewModel  (application: Application) : AndroidViewModel(application), CoroutineScope {
    val foodsLD = MutableLiveData<List<FoodHistory>>()
    val foodsErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun todayList(date:String)
    {
        loadingLD.value = true
        foodsErrorLD.value = false
        launch {
            val db = buildDb(getApplication())
            foodsLD.value = db.foodHistoryDao().selectToday(date)
        }
    }
}