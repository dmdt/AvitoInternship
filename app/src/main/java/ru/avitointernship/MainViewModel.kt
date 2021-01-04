package ru.avitointernship

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel(), ItemRemove {
    private val list: ArrayList<ListItem> = ArrayList()
    val listLd: MutableLiveData<List<ListItem>> =
        MutableLiveData<List<ListItem>>()

    private var currentNumber: Int = 0

    init {
        for (i in 0..10) {
            list.add(ListItem(currentNumber))
            currentNumber++
        }
        listLd.value = list
    }

    override fun removeItem(pos: Int) {
        list.removeAt(pos)
        listLd.value = list
    }
}