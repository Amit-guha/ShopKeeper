package com.example.shopkeeper.salesfragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.shopkeeper.R
import com.example.shopkeeper.data.saleslist
import com.example.shopkeeper.other.SalesViewModel

class SalesItemEdit : Fragment(R.layout.salesitemedit) {

    private val viewModelupdate: SalesViewModel by viewModels()
    private val args: SalesItemEditArgs by navArgs<SalesItemEditArgs>()


    private lateinit var newname: EditText
    private lateinit var newaddress: EditText
    private lateinit var newphone: EditText
    private lateinit var newTotal: EditText
    private lateinit var newdue: EditText
    private lateinit var newpaid: EditText

    private lateinit var btnclick: Button

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)


        newname = v.findViewById(R.id.etname)
        newaddress = v.findViewById(R.id.etaddress)
        newphone = v.findViewById(R.id.etphone)
        newTotal = v.findViewById(R.id.etTotal)
        newdue = v.findViewById(R.id.etdue)
        newpaid = v.findViewById(R.id.etPaid)

        //collect data from saleslistshow Fragment
        val idnewitemset = args.newitem.id
        val nameset = args.newitem.customername
        val addressset = args.newitem.address
        val phoneset = args.newitem.phone
        val paidset = args.newitem.paid
        val totalset = args.newitem.total
        val dueset = args.newitem.due

        newname.setText(nameset)
        newaddress.setText(addressset)
        newphone.setText(phoneset)
        newTotal.setText(totalset)
        newdue.setText(dueset)
        newpaid.setText(paidset)

       // Toast.makeText(requireContext(), " id =  " + idnewitemset, Toast.LENGTH_SHORT).show()


        btnclick = v.findViewById(R.id.btnupdate)

        btnclick.setOnClickListener {

            //tricky point here
            //TAke all the items inside the onclickEvent

            val UpdateDataaset = saleslist(
                idnewitemset.toInt(),
                newname.text.toString(),
                newphone.text.toString(),
                newaddress.text.toString(),
                newTotal.text.toString(),
                newpaid.text.toString(),
                newdue.text.toString()
            )

            viewModelupdate.updateuser(UpdateDataaset)

            findNavController().navigate(R.id.action_salesItemEdit_to_saleslistshow)
        }


    }
}