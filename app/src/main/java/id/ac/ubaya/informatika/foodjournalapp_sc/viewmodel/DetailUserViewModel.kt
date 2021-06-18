package id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import id.ac.ubaya.informatika.foodjournalapp_sc.model.User
import id.ac.ubaya.informatika.foodjournalapp_sc.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailUserViewModel(application: Application)
    :AndroidViewModel(application), CoroutineScope {

    val userLD = MutableLiveData<List<User>>()
    val userLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    fun refresh() {
        loadingLD.value = true
        userLoadErrorLD.value = false
        launch {
//            val db = buildDb(getApplication())
//            userLD.value = db.userDao().selectAllUndoneUser()
        }
    }

    fun clearTask(user: User) {
        launch {
            val db = buildDb(getApplication())
//            db.userDao().deleteUser(user)
//            userLD.value = db.userDao().selectAllUndoneUser()
        }
    }

    fun done(user: User) {
        launch {
            val db = buildDb(getApplication())
//            db.userDao().done(user.uuid)
//            userLD.value = db.userDao().selectAllUndoneUser()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}
