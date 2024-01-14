package com.megateamaj.timecalculationapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;


public class DateFragment extends Fragment {

    TextView startDate, endDate, resultInDays;
    int startDay, startMonth, startYear, endDay, endMonth, endYear;
    public DateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date, container, false);

        final Calendar c = Calendar.getInstance();
        int selectedYear = c.get(Calendar.YEAR);
        int selectedMonth = c.get(Calendar.MONTH);
        int selectedDay = c.get(Calendar.DAY_OF_MONTH);

        startDate = view.findViewById(R.id.textViewStartDate);
        endDate = view.findViewById(R.id.textViewEndDate);
        resultInDays = view.findViewById(R.id.textViewResult);

        startDay = selectedDay;
        startMonth = selectedMonth;
        startYear = selectedYear;
        endDay = selectedDay;
        endMonth = selectedMonth;
        endYear = selectedYear;

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                    startDay =  selectedDay;
                    startMonth = selectedMonth;
                    startYear = selectedYear;
                        if (startDate != null) {
                            startDate.setText(String.format(Locale.getDefault(), "%d/%d/%d", startDay, startMonth + 1, startYear));
                        }
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), onDateSetListener, startYear, startMonth, startDay);


                datePickerDialog.setTitle("Select Date");
                datePickerDialog.show();
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        endDay =  selectedDay;
                        endMonth = selectedMonth;
                        endYear = selectedYear;
                        if (endDate != null) {
                            endDate.setText(String.format(Locale.getDefault(), "%d/%d/%d", endDay, endMonth + 1, endYear));
                        }
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), onDateSetListener, endYear, endMonth, endDay);


                datePickerDialog.setTitle("Select Date");
                datePickerDialog.show();
            }



        });

        startDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            calculateDifference();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        endDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateDifference();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        return view;
    }

    private void calculateDifference() {
        String startValue = startDate.getText().toString();
        String endValue = endDate.getText().toString();
        if (!startValue.isEmpty() && !endValue.isEmpty()) {


            Calendar startCalendar = Calendar.getInstance();
            startCalendar.set(startYear, startMonth, startDay);

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.set(endYear, endMonth, endDay);

            long startTimeInMillis = startCalendar.getTimeInMillis();
            long endTimeInMillis = endCalendar.getTimeInMillis();

            long differenceInMillis = endTimeInMillis - startTimeInMillis;
            long days = (int) (differenceInMillis / (24 * 60 * 60 * 1000)); // Convert milliseconds to days
            if (days != 1) {
                resultInDays.setText(days + " Days");
            } else if (days == 1) {
                resultInDays.setText(days + " Day");

            }
        }
    }
}