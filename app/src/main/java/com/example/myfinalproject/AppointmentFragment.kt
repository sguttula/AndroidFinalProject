package com.example.myfinalproject

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import java.lang.reflect.AccessibleObject.setAccessible
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import com.example.myfinalproject.databinding.AppointmentBinding
import kotlinx.android.synthetic.main.appointment.*
import java.util.*


class AppointmentFragment: Fragment() {

    private lateinit var binding: AppointmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.appointment, container, false )

        date()

        //     val textView4: TextView = findViewById(R.id.textView4)
        //   val textView5: TextView = findViewById(R.id.textView5)
        //     val textView6: TextView = findViewById(R.id.textView6)
        //     val textView7: TextView = findViewById(R.id.textView7)

        //   val textView8: TextView = findViewById(R.id.textView8)
        //Location Text View


        val t=inflater.inflate(R.layout.appointment, container, false)
        //RETURN t
        val spinner = t.findViewById<Spinner>(R.id.spinner3)

        val times = arrayOf(
            "8:00 AM",
            "9:00 AM",
            "10:00 AM",
            "11:00 AM",
            "12:00 AM",
            "1:00 PM",
            "2:00 PM",
            "3:00 PM",
            "4:00 PM",
            "5:00 PM",
            "6:00 PM",
            "7:00 PM",
            "8:00 PM"
        )

        spinner?.adapter = ArrayAdapter(activity!!.applicationContext, R.layout.support_simple_spinner_dropdown_item, times) as SpinnerAdapter
        // Specify the layout to use when the list of choices appears
        // adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        // Apply the adapter to the spinner
        //spinner3.adapter = adapter

        spinner3.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                // Display the selected item text on text view
                textViewTime.text = "${parent.getItemAtPosition(position).toString()}"
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }

        button()

        return binding.root
    }
    private fun date() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        pickDateBtn.setOnClickListener {
            val dpd = DatePickerDialog(activity!!, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                dateTv.setText("" + month + " / " + dayOfMonth + " / " + year)
            }, year, month, day)
            dpd.show()
        }
    }

    private fun button() {
        confirm_button.setOnClickListener {
            activity?.let{
                val intent = Intent (it, ConfirmationActivity::class.java)
                it.startActivity(intent)
            }
        }
    }

}