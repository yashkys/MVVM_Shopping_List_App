package com.kys.mvvmshoppinglistapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kys.mvvmshoppinglistapp.data.db.entities.ShoppingItem

@Dao
interface ShoppingDao {     //Dao -> Data Access Object
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item : ShoppingItem)

    @Delete
    suspend fun delete(item : ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems() : LiveData<List<ShoppingItem>>
}