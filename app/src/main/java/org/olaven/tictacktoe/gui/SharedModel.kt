package org.olaven.tictacktoe.gui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class SharedModel: ViewModel() {

    val user1Name = MutableLiveData<String>()
    val user2Name = MutableLiveData<String>()
}