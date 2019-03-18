package org.olaven.tictacktoe.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface UserDAO {

    @Insert
    fun insert(user: User): Long

    @Delete
    fun delete(user: User)

    @Query("select * from User")
    fun getAll(): LiveData<List<User>>
}