package com.example.shopkeeper.salesfragment

import android.app.AlertDialog
import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.*
import androidx.core.view.get
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopkeeper.R
import com.example.shopkeeper.data.item
import com.example.shopkeeper.data.saleslist
import com.example.shopkeeper.other.SalesViewModel
import com.example.shopkeeper.projectadapter.ItemAdapter
import com.google.android.material.textfield.TextInputLayout
import java.lang.Exception


//problem1
//more than 8 zero in a row, negative value
class SalesItemAdd : Fragment(R.layout.salesitemsadd), ItemAdapter.OnItemClickListener {

    private lateinit var price: TextInputLayout
    private lateinit var quantity: TextInputLayout
    private lateinit var Total: TextInputLayout
    private lateinit var addbutton: ImageButton

    //sales calculation
    private lateinit var tvshow: TextView
    private lateinit var tvdpaid: EditText
    private lateinit var tvdue: TextView

    //Linerlayout
    private lateinit var linerlayout: LinearLayout
    private lateinit var linerlayouttitle: LinearLayout

    //Recyclerview
    private lateinit var recycle: RecyclerView
    private lateinit var itemlist: ArrayList<item>

    // private val adapter = ItemAdapter(itemlist)
    private lateinit var adapter: ItemAdapter
    private lateinit var btnCreate: Button

    //viewmodel
    private val viewModel: SalesViewModel by viewModels()


    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private val args: SalesItemAddArgs by navArgs<SalesItemAddArgs>()

    // private lateinit var text : TextView

    /*   override fun onCreateView(
           inflater: LayoutInflater,
           container: ViewGroup?,
           savedInstanceState: Bundle?
       ): View? {
           val v = inflater.inflate(R.layout.salesitemsadd, container, false)
           //incoming item
           val name1 = args.header.Customername
           val phone1 = args.header.CustomerPhone
           val address1 = args.header.CustomerAddress


           autoCompleteTextView=v.findViewById(R.id.at_textview)
           val totalitems= listOf<String>("7up 500ml","Coca-Cola Pet - 1.25L","PRAN FROOTO MANGO JUICE 1.5L")
           val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,totalitems)
           autoCompleteTextView.setAdapter(arrayAdapter)

           Toast.makeText(requireContext(),"find",Toast.LENGTH_SHORT).show()

           text= v.findViewById(R.id.tvname)
           text.setText(name1)

           return v
       }*/
    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

        getActivity()?.getWindow()
            ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //incoming item
        val name1 = args.header.Customername
        val phone1 = args.header.CustomerPhone
        val address1 = args.header.CustomerAddress

        var vqunatity1: Int = 0
        var vqunatity2: Int = 0

        var vprice1: Int = 0
        var vprice2: Int = 0
        var data2_value: Int = 0


        //Button
        btnCreate = v.findViewById(R.id.btn_create)


        autoCompleteTextView = v.findViewById(R.id.at_textview)
        price = v.findViewById(R.id.text_inputPrice)
        quantity = v.findViewById(R.id.text_inputQuantity)
        Total = v.findViewById(R.id.text_inputTotalPrice)
        addbutton = v.findViewById(R.id.imgAddButton)

        //sales calculation
        tvshow = v.findViewById(R.id.tv_total_taka)
        tvdpaid = v.findViewById(R.id.tv_paid_taka)
        tvdue = v.findViewById(R.id.tv_due_taka)

        //Recyclereview
        recycle = v.findViewById(R.id.recyclerview)
        //initialize of arraylist
        itemlist = arrayListOf<item>()

        //Linerlayout
        linerlayout = v.findViewById(R.id.Linerinvisible)
        linerlayouttitle = v.findViewById(R.id.linertitle)

        //create Database using Create Button
        btnCreate.setOnClickListener {
            val TotalAmount = tvshow.text.toString()
            val PaidAmount = tvdpaid.text.toString()
            val DueAmount = tvdue.text.toString()

            createDataase(name1, phone1, address1, TotalAmount, PaidAmount, DueAmount)
        }


        val totalitems =
            listOf<String>("7up 500ml", "Coca-Cola Pet - 1.25L", "PRAN FROOTO MANGO JUICE 1.5L")
        val priceitem = listOf<Int>(30, 75, 50)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, totalitems)
        autoCompleteTextView.setAdapter(arrayAdapter)


        //Case 1
        //Selectd Item positon
        autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->
            val item = adapterView.getItemAtPosition(i).toString()
            var pos = -1

            for (j in totalitems) {
                if (totalitems[i].equals(item)) {
                    pos = i
                    break
                }
            }
            //Toast.makeText(requireContext(), "position = $pos", Toast.LENGTH_LONG).show()
            price.editText?.setText(priceitem[pos].toString())
            quantity.editText?.setText("1")

            val vaule1 = Integer.parseInt(price.editText?.text.toString())
            val vaule2 = Integer.parseInt(quantity.editText?.text.toString())
            val value3 = vaule1 * vaule2
            Total.editText?.setText(value3.toString())


            //case2
            //when user change the price
            price.editText?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {

                    //for Price
                    val vaule1 = (price.editText?.text.toString())
                    if (vaule1.isEmpty()) {
                        vprice1 = 0
                    } else {
                        vprice1 = Integer.parseInt(vaule1)
                    }

                    //for Quantity
                    val vaule2 = (quantity.editText?.text.toString())
                    if (vaule2.isEmpty()) {
                        vprice2 = 0
                    } else {
                        vprice2 = Integer.parseInt(vaule2)
                    }

                    val value3 = vprice1 * vprice2
                    Total.editText?.setText(value3.toString())
                }
            })
        }


        //Case 3
        //when user change the Quantity
        quantity.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {


                //for Quantity
                val q2 = (quantity.editText?.text.toString())
                if (q2.isEmpty()) {
                    vqunatity1 = 0
                } else {
                    vqunatity1 = Integer.parseInt(q2)
                }


                //for Price
                val q1 = (price.editText?.text.toString())
                if (q1.isEmpty()) {
                    vqunatity2 = 0
                } else {
                    vqunatity2 = Integer.parseInt(q1)
                }

                val value4 = vqunatity1 * vqunatity2
                Total.editText?.setText(value4.toString())
            }
        })


        adapter = ItemAdapter(itemlist, this)
        recycle.layoutManager = LinearLayoutManager(requireContext())
        recycle.setHasFixedSize(true)

        //click plus Add button
        addbutton.setOnClickListener {
            // Toast.makeText(requireContext(), "addbuttoncalling", Toast.LENGTH_SHORT).show()
            val Product1 = autoCompleteTextView.text.toString()
            val price1 = price.editText?.text.toString()
            val quantity1 = quantity.editText?.text.toString()
            val ProductTotal1 = Total.editText?.text.toString()

            if (Product1.isNullOrEmpty() || price1.isNullOrEmpty() || quantity1.isNullOrEmpty() || ProductTotal1
                    .isNullOrEmpty()
            ) {
                Toast.makeText(requireContext(), "Plese fill up all the fields", Toast.LENGTH_LONG)
                    .show()
            } else {

                linerlayouttitle.visibility = View.VISIBLE
                linerlayout.visibility = View.VISIBLE
                btnCreate.visibility = View.VISIBLE
                tvdpaid.setText("0")

                val newItem = item(
                    Product1,
                    Integer.parseInt(price1),
                    Integer.parseInt(quantity1),
                    Integer.parseInt(ProductTotal1)
                )
                itemlist.add(newItem)
                println("itemlist = $itemlist")
                println("item = $ProductTotal1")

                println("Adapter CAlling $adapter")


                //total price
                var totalprice: Int = 0
                for (i in 0 until itemlist.size) {
                    totalprice += itemlist.get(i).itemtotal.toInt()
                    println("Total $totalprice")
                }
                tvshow.text = totalprice.toString()
                tvdue.text = totalprice.toString()
                recycle.adapter = adapter
                adapter.notifyItemInserted(1)
            }
        }


        //Click on the Paid textview
        //calculate the due amount
        //Problem in textwatecher
        tvdpaid.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                var data1 = tvshow.text.toString()
                var data2 = tvdpaid.text.toString()

                if (data2.isEmpty()) {
                    Toast.makeText(requireContext(), "Please Enter Amount", Toast.LENGTH_LONG)
                        .show()
                    data2_value = 0
                    // tvdue.setText(data1.toString())
                } else if (data2.equals("")) {
                    Toast.makeText(requireContext(), "Empty data", Toast.LENGTH_SHORT).show()
                } else {


                    try {
                        data2_value = data2.toInt()
                        val data3 = Integer.parseInt(data1) - data2_value
                        tvdue.setText(data3.toString())
                        if (data3 >= 0) {
                            btnCreate.visibility = View.VISIBLE
                        } else {
                            btnCreate.visibility = View.INVISIBLE
                        }

                    } catch (e: Exception) {
                        println("Exception : $e")
                    }
                }


            }
        })


    }


    //take all data from edit text and previous fragment
    private fun createDataase(
        custo: String, phn: String, add: String, tot: String, paid: String,
        due: String
    ) {
        if ((TextUtils.isEmpty(custo)) || (TextUtils.isEmpty(phn)) ||
            (TextUtils.isEmpty(add)) ||
            (TextUtils.isEmpty(tot)) ||
            (TextUtils.isEmpty(paid)) ||
            (TextUtils.isEmpty(due))
        ) {
            Toast.makeText(requireContext(), "Something Wrong !", Toast.LENGTH_LONG).show()
        } else {

            //  Toast.makeText(requireContext(), tot.toString(), Toast.LENGTH_LONG).show()

            val dataset = saleslist(0, custo, phn, add, tot, paid, due)

            viewModel.adduser(dataset)
            println("$custo, $phn, $add, $tot, $paid, $due")
            Toast.makeText(requireContext(), "Successfully done !", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_salesItemAdd_to_saleslistshow)

        }


        // Toast.makeText(requireContext(),"$custo, $phn, $add, $tot, $paid, $due",Toast.LENGTH_LONG).show()

    }


    //Remove Item
    fun removeItem(position: Int) {
        if (itemlist.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "List is Empty", Toast.LENGTH_SHORT).show()
        } else {
            itemlist.removeAt(position)
            tvdpaid.setText("0")
            if (itemlist.isEmpty()) {
                linerlayouttitle.visibility = View.INVISIBLE
                linerlayout.visibility = View.INVISIBLE
                btnCreate.visibility = View.INVISIBLE
                tvdpaid.setText("0")
            }
            //total price
            var totalprice: Int = 0
            for (i in 0 until itemlist.size) {
                totalprice += itemlist.get(i).itemtotal.toInt()
                println("Total $totalprice")
            }
            tvshow.text = totalprice.toString()
            tvdue.text = totalprice.toString()

            adapter.notifyItemRemoved(position)
        }


        // tvshow.text = totalprice.toString()

    }


    override fun onItemClick(position: Int) {
        // Toast.makeText(requireContext(), "item : $position Clicked", Toast.LENGTH_LONG).show()
    }

    override fun onDeleteClick(position: Int) {
        removeItem(position)
    }


/*
    //InsertItem in Recyclerview
    fun IntsertItem(view: View){
        val Product1= autoCompleteTextView.text.toString()
        val price1= price.editText?.text.toString()
        val quantity1 = quantity.editText?.text.toString()
        val ProductTotal1 = Total.editText?.text.toString()

        val newItem = item(Product1,Integer.parseInt(price1),Integer.parseInt(quantity1),Integer.parseInt(ProductTotal1))
        itemlist.add(newItem)
        adapter.notifyItemInserted(1)
    }
*/


    // Toast.makeText(requireContext(),"find",Toast.LENGTH_SHORT).show()

    /* text= v.findViewById(R.id.tvname)
     text.setText(name1)*/

}


/*


    <TextView
        android:id="@+id/tvname"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/Etcustomername"
        android:textStyle="bold"
        android:gravity="center_horizontal"
        android:textSize="20sp"
        android:text="Amit" />
 */

