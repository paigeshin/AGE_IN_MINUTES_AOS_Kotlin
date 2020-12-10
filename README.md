### res/values

- res/values/colors로 들어가서 필요한 값들을 미리 적어놓고 layout에서 @color 식으로 불러올 수 있다.

```xml
<TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="15dp"
        android:text="CALCULATE YOUR"
        android:textColor="@color/textColor" 
        android:textSize="25sp"
        android:textStyle="bold" />
				<!-- @color/textColor -->
```

### gravity vs layout_gravity

The difference between the gravity and layout_gravity XML attributes in Android is that the android:gravity attribute is used to arrange the position of the content inside a View (for example text inside a Button widget) while the android:layout_gravity is used to arrange the position of the entire View relative to it’s container.

- gravity, 자신의 컨텐트 안에.
- layout_gravity, parent와 상대적으로.

### dp vs sp

- dp: Density-independent Pixels
- sp: Scaleable Pixels OR scale-independent pixels

DP(Density-Independent Pixels)는 UI 레이아웃을 정의할 때 레이아웃 치수나 위치를 지정하기 위해 사용하는 단위이다.

SP(Scale-Independent Pixels)는 UI 레이아웃을 정의할 때 텍스트의 크기를 지정하기 위해 사용하는 단위이다.

안드로이드 설정 화면에서 사용자는 안드로이드 시스템 전체에서 보여지는 텍스트의 크기를 선택하여 설정할 수 있는데 SP는 해당 설정에 영향을 받는다. 예를 들어, 시스템 설정에서 텍스트 크기를 최대 크게로 설정하게 되면 UI 레이아웃을 정의할 때 SP로 크기를 지정해놓은 TextView의 텍스트 크기가 영향을 받아 커진다. 반면에 TextView의 텍스트 크기를 DP로 설정하게 되면 시스템 설정의 텍스트 크기 값의 변화에 상관없이 일정한 크기를 유지한다.

그래서 디자인 명세에 따라서는 텍스트 크기의 값의 단위를 SP를 사용하는 것이 아니라 DP를 사용하는 것이 좋을 경우도 있을 것이다. 다시 말해, 언제나 일관성있게 화면에서 똑같은 텍스트 크기를 표현하고자 하는 경우에 sp가 아닌 dp 단위를 사용하도록 한다.

- dp: 변하지 않음 (일관성 있는 텍스트 크기)
- sp: 변함 (디자인 명세에 따라서)

# View Binding & Date Picker

```kotlin
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
```