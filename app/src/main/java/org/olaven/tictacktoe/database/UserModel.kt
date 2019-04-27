package org.olaven.tictacktoe.database

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserModel(application: Application): AndroidViewModel(application) {

    //co-routine
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)


    private val userDAO = AppDatabase
        .getDatabase(application.applicationContext)
        .userDAO()

    private val repository = UserRepostory(userDAO)
    val allUsers = repository.allUsers


    fun insert(user: User) = scope.launch(Dispatchers.IO) {
        repository.insert(user)
    }

    fun delete(user: User) = scope.launch(Dispatchers.IO) {
        repository.delete(user)
    }

    fun update(user: User) = scope.launch(Dispatchers.IO) {
        repository.update(user)
    }

    fun deleteAll() = scope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun getByName(name: String) =
        repository.getByName(name)
}