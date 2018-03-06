package com.example.nextdot_03.testapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Date;

/**
 * Created by NextDot-03 on 3/1/2018.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    private Calendar mCalender;
    private OnSetDateText mDateTextSetter;

    public DatePickerFragment(OnSetDateText dateTextSetter, Calendar calendar){
        mDateTextSetter = dateTextSetter;
        mCalender = calendar;
        // test line
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        int year = mCalender.get(Calendar.YEAR);
        int month = mCalender.get(Calendar.MONTH);
        int day = mCalender.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), this, year, month, day);
        Date today = new Date();
        datePickerDialog.getDatePicker()
                .setMinDate(today.getTime());
        return datePickerDialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        mCalender.set(year, month, day);
        mDateTextSetter.setDateText(mCalender);
    }

    interface OnSetDateText{
        public void setDateText(Calendar calendar);
    }
}
