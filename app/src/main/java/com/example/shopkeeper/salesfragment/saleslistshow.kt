package com.example.shopkeeper.salesfragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopkeeper.R
import com.example.shopkeeper.data.saleslist
import com.example.shopkeeper.data.updateItem
import com.example.shopkeeper.other.SalesItemTouchHandler
import com.example.shopkeeper.other.SalesViewModel
import com.example.shopkeeper.projectadapter.DataShowAdapter
import com.example.shopkeeper.projectadapter.DataShowAdapter.OnItemClickLisitiner
import com.example.shopkeeper.projectadapter.ItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class saleslistshow : Fragment(R.layout.saleshow),DataShowAdapter.OnItemClickLisitiner {

    // private lateinit var  viewModel : UserViewModel
    private val viewModel: SalesViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var floatingActionButton: FloatingActionButton

    override fun onViewCreated(v: View, savedInstanceState: Bundle?){
        super.onViewCreated(v, savedInstanceState)

        recyclerView = v.findViewById(R.id.recyclerviewitem)
        floatingActionButton = v.findViewById(R.id.floatingActionButton)

        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_saleslistshow_to_salesheader)
        }

        val adapter = DataShowAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        viewModel.alldata.observe(viewLifecycleOwner, Observer { salesitemlist ->
            adapter.setData(salesitemlist)
        })

        ItemTouchHelper(
            SalesItemTouchHandler(
                viewModel,
                adapter
            )
        ).attachToRecyclerView(recyclerView)

      adapter.setOnItemClickLisitiner(this)

        setHasOptionsMenu(true)

       // Toast.makeText(requireContext(),"Item Deleted",Toast.LENGTH_SHORT).show()
    }

    override fun OnItemClick(saleslist: saleslist) {
        Toast.makeText(context,"Item clicked",Toast.LENGTH_SHORT).show()
        val sendid: Int= saleslist.id
        val sendname : String= saleslist.customername
        val sendaddress: String = saleslist.address
        val sendphone: String = saleslist.phone
        val sendTotal : String = saleslist.total
        val sendDue: String= saleslist.due
        val sendPaid: String = saleslist.paid

        val sendata = saleslist(sendid,sendname,sendphone,sendaddress,sendTotal,sendPaid,sendDue)

        val directions = saleslistshowDirections.actionSaleslistshowToSalesItemEdit(sendata)
        findNavController().navigate(directions)

    }

    //Two menu
    //Because of delete item

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.deleteallitem,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId ==R.id.all_delete_item){
            deleteallUserfrom()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteallUserfrom() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            viewModel.deleteallu()
            Toast.makeText(requireContext(),"Successfully removed everythin",Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("NO"){_,_ ->}
        builder.setTitle("Delete Everything?")
        builder.setMessage("Are You Sure You Want to Delete Everything?")
        builder.create().show()
    }




}