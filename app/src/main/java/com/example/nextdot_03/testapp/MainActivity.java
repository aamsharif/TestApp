package com.example.nextdot_03.testapp;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.DialogFragment;


public class MainActivity extends AppCompatActivity
                            implements DatePickerFragment.OnSetDateText {


    private Calendar mCalender;
    private SimpleDateFormat mSimpleDateFormat;

    private TextView mDateView;
    private ImageView mLeftArrow;
    private ImageView mRightArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDateView = findViewById(R.id.tv_date);
        mLeftArrow = findViewById(R.id.iv_left_arrow);
        mRightArrow = findViewById(R.id.iv_right_arrow);

        mCalender = Calendar.getInstance();
        mSimpleDateFormat = new SimpleDateFormat("EEE, MMM d, yyyy");

        setDateText(mCalender);

        mLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement();
            }
        });

        mRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment();
            }
        });

    }

    @Override
    public void setDateText(Calendar calendar) {
        Date date = mCalender.getTime();
        String dateString = mSimpleDateFormat.format(date);
        mDateView.setText(dateString);
    }

    private void increment() {
        mCalender.add(Calendar.DATE, 1);
        setDateText(mCalender);
    }

    // compare date in calender with today's date and disable it when it is today's date
    private void decrement() {
        Date calenderDate = mCalender.getTime();
        Date today = new Date();
        //comparing calender's date with today's date
        if(calenderDate.after(today)) {
            mCalender.add(Calendar.DATE, -1);
            setDateText(mCalender);
        }
    }

    public void openDatePicker(View view){
        DialogFragment newFragment = new DatePickerFragment(this, mCalender);
        FragmentManager fragmentManager = getSupportFragmentManager();
        newFragment.show(fragmentManager, "datePicker");
    }
}
