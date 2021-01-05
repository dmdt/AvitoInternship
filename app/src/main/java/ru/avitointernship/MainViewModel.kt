package ru.avitointernship

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel: ViewModel() {
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
        viewModelScope.launch {
            addItemFromCoroutine()
        }
    }

    fun removeItem(pos: Int) {
        list.removeAt(pos)
        listLd.value = list
    }

//    fun addItemFromThread() {
//        list.add(Random.nextInt(0, list.size), ListItem(currentNumber))
//        currentNumber++
//        listLd.postValue(list)
//    }

    private suspend fun addItemFromCoroutine() {
        while (true) {
            list.add(Random.nextInt(0, list.size), ListItem(currentNumber))
            currentNumber++
            listLd.value = list
            delay(5000)
        }
    }
}