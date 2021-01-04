package ru.avitointernship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

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
    }

    interface ItemRemovedCallback {
        fun removeItem(pos: Int)
    }
}