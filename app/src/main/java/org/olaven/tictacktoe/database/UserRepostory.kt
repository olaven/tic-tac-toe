package org.olaven.tictacktoe.database

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class UserRepostory(val dao: UserDAO) {

    val allUsers: LiveData<List<User>> = dao.getAll()

    @WorkerThread
    suspend fun insert(user: User): Long =
        dao.insert(user)

    @WorkerThread
    suspend fun delete(user: User) =
        dao.delete(user)

    @WorkerThread
    suspend fun deleteAll() =
            dao.deleteAll()

    @WorkerThread
    fun getByName(name: String) =
            dao.getByName(name)

}
