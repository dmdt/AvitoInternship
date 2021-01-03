package ru.avitointernship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler: RecyclerView = findViewById(R.id.rvList)
        val lAdapter = LAdapter()

        recycler.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = lAdapter
        }

        lAdapter.submitList(listOf(
            ListItem(1),
            ListItem(2),
            ListItem(3),
            ListItem(4),
            ListItem(5)
        ))
    }
}