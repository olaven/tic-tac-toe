package org.olaven.tictacktoe.database

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class UserRepostory(val dao: UserDAO) {

    val allUsers: LiveData<List<User>> = dao.getAll()

    @WorkerThread
    fun insert(user: User): Long =
        dao.insert(user)

    @WorkerThread
    fun delete(user: User) =
        dao.delete(user)

    @WorkerThread
    fun update(user: User) =
        dao.update(user)

    @WorkerThread
    fun deleteAll() =
            dao.deleteAll()

    @WorkerThread
    fun getByName(name: String) =
            dao.getByName(name)

}
