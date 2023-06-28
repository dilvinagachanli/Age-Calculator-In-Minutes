package com.example.agecalculator2

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var selectedDate: TextView?= null
    private var ageInMinutes : TextView?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        selectedDate = findViewById(R.id.date)
        ageInMinutes = findViewById(R.id.minutes)


        button.setOnClickListener{
            clickDatePicker()

        }

    }


    private fun clickDatePicker(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{_, year, month, dayOfMonth->
            //Toast.makeText(
            //  this, "Year is $year, month is ${month+1}, day is $dayOfMonth", Toast.LENGTH_LONG).show()

            val date = "$dayOfMonth/${month+1}/$year"
            selectedDate?.text = date

            val simpleDate = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = simpleDate.parse(date) //we need this to calculate how much time passed in minutes

            theDate?.let {
                val dateInMinutes = theDate.time/ 60000 //how much time has passed from there?
                val currentDate = simpleDate.parse(simpleDate.format(System.currentTimeMillis())) //what is the current time?
                currentDate?.let {
                    val currentDateInMinutes = currentDate.time/60000
                    val differenceInMinutes = currentDateInMinutes - dateInMinutes
                    ageInMinutes?.text = differenceInMinutes.toString()
                }


            } },year, month, day)



        //toast: is used to display a sort time notification to the user without affecting the user interaction with UI.
        // The message displayed using Toast class displays quickly, and it disappears after some time.
        // The message in the Toast can be of type text, image or both.
        //Toast.LENGTH_LONG says how much time should the message display which means a long time in this case
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }




}
