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


    val personDAO = AppDatabase
        .getDatabase(application.applicationContext)
        .userDAO()

    val repository = UserRepostory(personDAO)
    val allUsers = repository.allUsers


    fun insert(user: User) = scope.launch(Dispatchers.IO) {
        repository.insert(user)
    }

    fun delete(user: User) = scope.launch(Dispatchers.IO) {
        repository.delete(user)
    }

}