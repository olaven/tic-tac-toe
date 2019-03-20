package org.olaven.tictacktoe.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class User(
    var name: String,
    var losses: Int = 0,
    var wins: Int = 0,
    var draws: Int = 0,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)