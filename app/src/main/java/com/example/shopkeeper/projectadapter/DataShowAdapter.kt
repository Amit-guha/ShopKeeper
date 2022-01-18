package com.example.shopkeeper.projectadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopkeeper.R
import com.example.shopkeeper.data.item
import com.example.shopkeeper.data.saleslist
import kotlinx.coroutines.NonDisposableHandle.parent

class DataShowAdapter : RecyclerView.Adapter<DataShowAdapter.DataViewHolder>() {
    private var userlist = emptyList<saleslist>()

    //  private  var itemlist: ArrayList<saleslist> = arrayListOf()
    var lisitiner: OnItemClickLisitiner? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(
            R.layout.databaseshow,
            parent, false
        )
        return DataViewHolder(itemview, lisitiner, this)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentitem = userlist[position]

        holder.apply {
            tvcustomername.text = currentitem.customername
            tvphone.text = currentitem.phone
            tvAddress.text = currentitem.address
            tvTotal.text = currentitem.total
            tvpaid.text = currentitem.paid
            tvdue.text = currentitem.due
        }

    }

    fun getItemAt(position: Int): saleslist {
        return userlist.get(position)
    }

    override fun getItemCount(): Int = userlist.size


    class DataViewHolder(
        itemview: View,
        var lisitiner: OnItemClickLisitiner? = null,
        var dataShowAdapter: DataShowAdapter
    ) : RecyclerView.ViewHolder(itemview) {
        val tvcustomername: TextView = itemview.findViewById(R.id.tv_itemname)
        val tvphone: TextView = itemview.findViewById(R.id.tv_itemphone)
        val tvAddress: TextView = itemview.findViewById(R.id.tv_itemAddress)
        val tvTotal: TextView = itemview.findViewById(R.id.tv_itemTotal)
        val tvpaid: TextView = itemview.findViewById(R.id.tv_itempaid_tk)
        val tvdue: TextView = itemview.findViewById(R.id.tv_itemdue_tk)

        init {
            itemview.setOnClickListener(View.OnClickListener {
                val pos: Int = adapterPosition
                if (lisitiner != null && pos != RecyclerView.NO_POSITION) {
                    lisitiner?.OnItemClick(dataShowAdapter.getItemAt(pos))
                }
            })
        }
    }

    fun setData(user: List<saleslist>) {
        this.userlist = user
        notifyDataSetChanged()
    }

    interface OnItemClickLisitiner {
        fun OnItemClick(saleslist: saleslist)
    }

    fun setOnItemClickLisitiner(lisitiner: OnItemClickLisitiner) {
        this.lisitiner = lisitiner
    }

}