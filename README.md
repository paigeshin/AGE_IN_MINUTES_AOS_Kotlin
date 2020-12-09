# AGE_IN_MINUTES_AOS_Kotlin

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