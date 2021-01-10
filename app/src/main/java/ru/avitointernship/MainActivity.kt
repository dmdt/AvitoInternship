package ru.avitointernship

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.avitointernship.adapters.Adapter
import ru.avitointernship.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    private var itemCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSubtitle()

        val recycler: RecyclerView = findViewById(R.id.rvList)
        val listAdapter = Adapter(object: ItemRemovedCallback {
            override fun removeItem(pos: Int) {
                viewModel.removeItem(pos)
            }
        })

        recycler.apply {
            layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                GridLayoutManager(context, 4)
            } else {
                GridLayoutManager(context, 2)
            }
            adapter = listAdapter
        }

        viewModel.listLd.observe(this) {
            listAdapter.submitList(it.toList())

            itemCount = it.size
            setSubtitle()
        }
    }

    private fun setSubtitle() {
        supportActionBar?.subtitle = "Items in list: $itemCount."
    }

    interface ItemRemovedCallback {
        fun removeItem(pos: Int)
    }
}