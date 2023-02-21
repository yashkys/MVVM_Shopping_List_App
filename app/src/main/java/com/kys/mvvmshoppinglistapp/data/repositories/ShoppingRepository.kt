package com.kys.mvvmshoppinglistapp.data.repositories

import com.kys.mvvmshoppinglistapp.data.db.ShoppingDatabase
import com.kys.mvvmshoppinglistapp.data.db.entities.ShoppingItem

class ShoppingRepository (
    private val db: ShoppingDatabase
    ) {

    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()

}