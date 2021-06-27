package id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import id.ac.ubaya.informatika.foodjournalapp_sc.model.Food
import id.ac.ubaya.informatika.foodjournalapp_sc.model.User
import id.ac.ubaya.informatika.foodjournalapp_sc.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailUserViewModel(application: Application)
    :AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val userLD = MutableLiveData<User>()
    var jml = 0

    fun addUser(list:List<User>) {
        launch {
            val db = buildDb(getApplication())
            db.userDao().insertAll(*list.toTypedArray())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun fetch(uuid: Int) {
        launch {
            val db = buildDb(getApplication())
            userLD.value = db.userDao().selectUser(uuid)
        }
    }

    fun fetch() {
        launch {
            val db = buildDb(getApplication())
            val list = db.userDao().selectAllUser()
        }
    }

    fun fetchCurrentUser() {
        launch {
            val db = buildDb(getApplication())
            userLD.value = db.userDao().selectCurrentUser()
        }
    }

    fun jumlah1(){
        launch {
            val db = buildDb(getApplication())
           jml = db.userDao().selectJumlah()
        }
    }

    fun jumlah2():Int
    {
        jumlah1()
        return jml
    }

    fun goal(): Int? {
        fetchCurrentUser()
        return userLD.value?.caloriesTarget
    }

//    fun update(title:String, notes:String, priority:Int, uuid:Int) {
//        launch {
//            val db = buildDb(getApplication())
//            db.userDao().update(title, notes, priority, uuid)
//        }
//    }

//    fun update(user:User) {
//        launch {
//            val db = buildDb(getApplication())
//            db.userDao().update(user.title, user.notes, user.priority, user.uuid)
//        }
//    }

    fun update(name1:String, age1 :Int, gender1: String, height1:Int, weight1:Int, goal1:String, bmr1:Double, target1:Int, uuid1:Int)
    {
        launch {
            val db = buildDb(getApplication())
            db.userDao().update(name1, age1 , gender1, height1, weight1, goal1, bmr1, target1, uuid1)
        }
    }



}
