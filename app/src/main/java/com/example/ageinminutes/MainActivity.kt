package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }

    }

    fun clickDatePicker(view: View) {
        val myCalendar: Calendar = Calendar.getInstance()
        val year: Int = myCalendar.get(Calendar.YEAR)
        val month: Int = myCalendar.get(Calendar.MONTH)
        val day: Int = myCalendar.get(Calendar.DAY_OF_MONTH)
        val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { calendarView, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate: String = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

            //view, tvSelectedDate
            val tvSelectedDate: TextView = findViewById<TextView>(R.id.tvSelectedDate)
            tvSelectedDate.setText(selectedDate)

            val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate: Date? = simpleDateFormat.parse(selectedDate)

            //use `!!` when you are certain about the value.  (It should not be null)
            val selectedDateInMinutes: Long = theDate!!.time / 60000

            val currentDate: Date? = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
            val currentDateToMinutes = currentDate!!.time / 60000

            //result
            val differenceInMinutes: Long = currentDateToMinutes - selectedDateInMinutes

            //view, tvSelectedMinutes
            val tvSelectedMinutes: TextView = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
            tvSelectedMinutes.setText(differenceInMinutes.toString())

        }, year, month, day)

        datePicker.datePicker.setMaxDate(Date().time - 8640000)
        datePicker.show()
        
    }

}