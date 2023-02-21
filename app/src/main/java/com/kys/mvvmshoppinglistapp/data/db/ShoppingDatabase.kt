package com.kys.mvvmshoppinglistapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kys.mvvmshoppinglistapp.data.db.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun getShoppingDao() : ShoppingDao

    companion object{
        @Volatile   //Rights to this instance will be made visible instantly to other threads
        //to make sure that only one thread is writing to that instance
        private var instance : ShoppingDatabase? = null

        private val LOCK = Any()

        //this function is executed whenever we create an instance of our shopping database class
        //it returns out instance, but if the instance is null, then it will call the synchronized block
        operator fun invoke(context : Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context : Context) = Room.databaseBuilder(context.applicationContext, ShoppingDatabase::class.java, "ShoppingDB.db").build()
    }
}