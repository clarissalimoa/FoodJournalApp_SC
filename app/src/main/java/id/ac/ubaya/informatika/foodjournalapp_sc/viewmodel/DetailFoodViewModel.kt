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
import kotlin.coroutines.CoroutineContext

class DetailFoodViewModel (application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val foodLD = MutableLiveData<Food>()
    var totalCalory = MutableLiveData<Int>()
    var angka1 = 0;

    fun addFood(list:List<Food>) {
        launch {
            val db = buildDb(getApplication())
            db.foodDao().insertAll(*list.toTypedArray())
        }
    }

    fun addhistory(history: History) {
        launch {
            val db = buildDb(getApplication())
            db.historyDao().insertAll(history)
        }
    }

    fun addFoodHistory(list:List<FoodHistory>) {
        launch {
            val db = buildDb(getApplication())
            db.foodHistoryDao().insertAll(*list.toTypedArray())
        }
    }

    fun selectTotalCalory(date:String) {
        launch {
            val db = buildDb(getApplication())
            totalCalory.value = db.historyDao().selecttotalCalory(date)
        }
    }

    fun cekHistorybd(date:String) {
        launch {
            val db = buildDb(getApplication())
            angka1 = db.historyDao().selectJumlahHistory(date)
        }
    }

    fun cekHistory(date:String) :Int {
        cekHistorybd(date)
        return angka1
    }

    fun fetch(uuid:Int)
    {
        launch{
            val db = buildDb(getApplication())
           foodLD.value = db.foodDao().selectFood(uuid)
        }
    }
    fun updateHistory(fooodCalory2:Int, status2 :String, date2:String)
    {
        launch{
            val db = buildDb(getApplication())
            db.historyDao().updatehistory(fooodCalory2,status2,date2)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}