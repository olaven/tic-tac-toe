package org.olaven.tictacktoe.gui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class SharedModel: ViewModel() {

    val player1Name = MutableLiveData<String>()
    val player2Name = MutableLiveData<String>()

    val fragment = MutableLiveData<String>()
}