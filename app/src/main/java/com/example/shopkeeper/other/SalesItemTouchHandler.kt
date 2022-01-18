package com.example.shopkeeper.other

import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shopkeeper.projectadapter.DataShowAdapter

class SalesItemTouchHandler(
    val salesViewModel: SalesViewModel,
    val dataShowAdapter: DataShowAdapter
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT + ItemTouchHelper.RIGHT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        salesViewModel.deleteuser(dataShowAdapter.getItemAt(viewHolder.adapterPosition))

    }
}