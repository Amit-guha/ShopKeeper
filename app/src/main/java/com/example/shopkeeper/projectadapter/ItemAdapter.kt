package com.example.shopkeeper.projectadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopkeeper.R
import com.example.shopkeeper.data.item

class ItemAdapter(private val itemlist: ArrayList<item>,
                  private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemview =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_cartitem, parent, false)
        return ItemViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemlist[position]
        holder.apply {
            itemname.text = currentItem.itemnaem
            price.text = currentItem.itemprice.toString()
            quantity.text = currentItem.itemquantity.toString()
            Total.text = currentItem.itemtotal.toString()
        }
    }

    override fun getItemCount() = itemlist.size

   inner class ItemViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview),
    View.OnClickListener{
        val itemname: TextView = itemview.findViewById(R.id.tv_itemname)
        val price: TextView = itemview.findViewById(R.id.tv_itemprice)
        val quantity: TextView = itemview.findViewById(R.id.tv_itemQuantity)
        val Total: TextView = itemview.findViewById(R.id.tv_itemTotal)
        val Deletebutton : ImageButton = itemview.findViewById(R.id.img_Delete)

        init{
            itemview.setOnClickListener(this)
            Deletebutton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDeleteClick(position)
                }
            }
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }


    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onDeleteClick(position: Int)
    }
}