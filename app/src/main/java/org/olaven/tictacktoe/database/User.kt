package org.olaven.tictacktoe.database

import android.arch.persistence.room.Entity

@Entity
data class User(
    var name: String,
    var losses: Int,
    var wins: Int
)