package com.kys.mvvmshoppinglistapp.ui.shoppinglist

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kys.mvvmshoppinglistapp.R
import com.kys.mvvmshoppinglistapp.data.db.ShoppingDatabase
import com.kys.mvvmshoppinglistapp.data.db.entities.ShoppingItem
import com.kys.mvvmshoppinglistapp.data.repositories.ShoppingRepository
import com.kys.mvvmshoppinglistapp.other.ShoppingItemAdapter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

//        val database = ShoppingDatabase(this)
//        val repository = ShoppingRepository(database)
//        val factory = ShoppingViewModelFactory(repository )

        val viewModel = ViewModelProviders.of(this, factory)[ShoppingViewModel::class.java]

        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        val rvShoppingItems: RecyclerView = findViewById(R.id.rvShoppingItems)
        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer{
            adapter.items = it
            adapter.notifyDataSetChanged()
        })
        findViewById<FloatingActionButton>(R.id.fab)?.setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogListener  {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }

    }
}