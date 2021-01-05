package ru.avitointernship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
//    private val itemThread = ItemThread()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler: RecyclerView = findViewById(R.id.rvList)
        val listAdapter = LAdapter(object: ItemRemovedCallback {
            override fun removeItem(pos: Int) {
                viewModel.removeItem(pos)
            }
        })

        recycler.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = listAdapter
        }

        viewModel.listLd.observe(this) {
            listAdapter.submitList(it.toList())
        }

//        itemThread.start()
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        itemThread.interrupt()
//    }

    interface ItemRemovedCallback {
        fun removeItem(pos: Int)
    }

//    inner class ItemThread: Thread() {
//        override fun run() {
//            while (!isInterrupted) {
//                viewModel.addItemFromThread()
//                sleep(5000)
//            }
//        }
//    }
}