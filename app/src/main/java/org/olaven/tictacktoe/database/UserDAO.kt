package org.olaven.tictacktoe.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface UserDAO {

    @Insert
    fun insert(user: User): Long

    @Delete
    fun delete(user: User)

    @Update
    fun update(user: User)

    @Query("select * from User")
    fun getAll(): LiveData<List<User>>

    @Query("select * from User where name = :name")
    fun getByName(name: String): LiveData<User>

    @Query("delete from User")
    fun deleteAll()
}