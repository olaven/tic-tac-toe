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

    @Query("select * from User where name = :name")
    fun getByName(name: String): LiveData<User>

    @Query("delete from User")
    fun deleteAll()
}