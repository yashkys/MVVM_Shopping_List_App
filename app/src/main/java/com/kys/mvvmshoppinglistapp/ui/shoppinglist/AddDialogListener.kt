package com.kys.mvvmshoppinglistapp.ui.shoppinglist

import com.kys.mvvmshoppinglistapp.data.db.entities.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked(item: ShoppingItem)
}