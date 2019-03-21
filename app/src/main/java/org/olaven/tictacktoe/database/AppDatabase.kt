package org.olaven.tictacktoe.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context


@Database(entities = [User::class], version = 3, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDAO(): UserDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "modulist_database"
                ).build()

                INSTANCE = instance
                return instance
            }

        }

    }
}

