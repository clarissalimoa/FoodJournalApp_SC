package id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.foodjournalapp_sc.model.Food
import id.ac.ubaya.informatika.foodjournalapp_sc.model.FoodHistory
import id.ac.ubaya.informatika.foodjournalapp_sc.model.History
import id.ac.ubaya.informatika.foodjournalapp_sc.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class ListFoodViewModel (application: Application) : AndroidViewModel(application), CoroutineScope {
    val foodLD = MutableLiveData<List<Food>>()
    val HistoryLD = MutableLiveData<List<History>>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refreshFood()
    {

        launch {
            val db = buildDb(getApplication())
            foodLD.value = db.foodDao().selectAllFood()
        }
    }

    fun refreshFoodHistory()
    {

        launch {
            val db = buildDb(getApplication())
            val sdf = SimpleDateFormat("MMMM yyyy")
            val currentDate = sdf.format(Date())
            val masuk = "%$currentDate%"
            HistoryLD.value = db.historyDao().selectAllHistory(masuk)
        }
    }
}