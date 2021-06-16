package me.threebecause

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareViewModel : ViewModel() {
    val mutableSelectedItem = MutableLiveData<Int>()

    fun selectItem(item: Int) {
        mutableSelectedItem.value = item
    }
}