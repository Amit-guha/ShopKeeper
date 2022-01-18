package com.example.shopkeeper.salesfragment

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shopkeeper.R
import com.example.shopkeeper.data.salesIntro
import com.google.android.material.textfield.TextInputLayout


class salesheader : Fragment(R.layout.sales_header) {

    private lateinit var etname: TextInputLayout
    private lateinit var etphn: TextInputLayout
    private lateinit var etaddress: TextInputLayout
    private lateinit var buttonnext: Button
    private lateinit var buttonShow: Button

    private lateinit var tv: TextView


/*    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.sales_header, container, false)

        etname = v.findViewById(R.id.text_inputname)
        etphn = v.findViewById(R.id.text_inputnumber)
        etaddress = v.findViewById(R.id.text_inputaddress)

        tv = v.findViewById(R.id.text_view_id)
        buttonnext = v.findViewById(R.id.Btn_next)

        val name = etname.editText?.text.toString()
        val phn = etphn.editText?.text.toString()
        val address = etaddress.editText?.text.toString()


        val salesdata = salesIntro(1, name, phn, address)
        println("salesdata = $salesdata")


        buttonnext.setOnClickListener {

            Toast.makeText(requireContext(),"$name",Toast.LENGTH_SHORT).show()
            println("salesdata = $salesdata")
            val action = salesheaderDirections.actionSalesheaderToSalesItemAdd(salesdata)
            findNavController().navigate(action)
        }


        return v

    }*/

    //when back pressed button is pressed i lost my data

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

        etname = v.findViewById(R.id.text_inputname)
        etphn = v.findViewById(R.id.text_inputnumber)
        etaddress = v.findViewById(R.id.text_inputaddress)


        tv = v.findViewById(R.id.text_view_id)
        buttonnext = v.findViewById(R.id.Btn_next)
        buttonShow=v.findViewById(R.id.Btn_show)


        buttonnext.setOnClickListener {
            val name = etname.editText?.text.toString()
            val phn = etphn.editText?.text.toString()
            val address = etaddress.editText?.text.toString()

            val salesdata = salesIntro(1, name, phn, address)

            //println("data = $salesdata")

            if ((TextUtils.isEmpty(name) || (TextUtils.isEmpty(phn) || TextUtils.isEmpty(address)))) {
                Toast.makeText(requireContext(), "Please fill up all the field", Toast.LENGTH_SHORT)
                    .show()
            } else {
                //Toast.makeText(requireContext(), name, Toast.LENGTH_LONG).show()
                val action = salesheaderDirections.actionSalesheaderToSalesItemAdd(salesdata)
                findNavController().navigate(action)
            }


        }

        //Show Database
        buttonShow.setOnClickListener {
            findNavController().navigate(R.id.action_salesheader_to_saleslistshow)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }


    /*    buttonnext.setOnClickListener {
          Toast.makeText(requireContext(),name1,Toast.LENGTH_SHORT).show()

          val salesdata = salesIntro(1,name1,phn1,address1)

          val action = salesheaderDirections.actionSalesheaderToSalesItemAdd(salesdata)
          findNavController().navigate(action)
      }
*/
    //  }
}

/*




 */