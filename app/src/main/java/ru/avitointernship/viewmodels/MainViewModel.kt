package ru.avitointernship.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.avitointernship.data.ListItem
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val list: ArrayList<ListItem> = ArrayList()
    val listLd: MutableLiveData<List<ListItem>> =
        MutableLiveData<List<ListItem>>()

    private var currentNumber: Int = 0
    private val removedItemIds = mutableListOf<Int>()

    init {
        for (i in 0..14) {
            list.add(ListItem(currentNumber))
            currentNumber++
        }
        listLd.value = list

        viewModelScope.launch {
            addItemFromCoroutine()
        }
    }

    fun removeItem(pos: Int) {
        removedItemIds.add(list[pos].id)
        list.removeAt(pos)
        listLd.value = list
    }

    private suspend fun addItemFromCoroutine() {
        while (true) {
            delay(5000)
            // Handle empty list insertion
            val position = when {
                list.isEmpty() -> 0
                else -> Random.nextInt(0, list.size)
            }

            if (removedItemIds.isEmpty()) {
                list.add(position, ListItem(currentNumber))
                currentNumber++
            } else {
                list.add(position, ListItem(removedItemIds[0]))
                removedItemIds.removeAt(0)
            }
            listLd.value = list
        }
    }
}