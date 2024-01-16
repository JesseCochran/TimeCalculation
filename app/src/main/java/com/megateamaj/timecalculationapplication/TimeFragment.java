package com.megateamaj.timecalculationapplication;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Locale;


public class TimeFragment extends Fragment {

    TextView startTime, result;
    EditText editHours, editMinutes;
    int hour, minute;

    public TimeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_time, container, false);

        startTime = view.findViewById(R.id.textViewStartTime);
        result = view.findViewById(R.id.textViewResult);
        editHours = view.findViewById(R.id.editTextHours);
        editMinutes = view.findViewById(R.id.editTextMinutes);

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        hour = selectedHour;
                        minute = selectedMinute;
                        startTime.setText((String.format(Locale.getDefault(), "%02d:%02d", hour, minute)));
                    }
                };
                int style = AlertDialog.THEME_HOLO_DARK;

                TimePickerDialog timePickerDialog = new TimePickerDialog(requireContext(), style, onTimeSetListener, hour, minute, true);

                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });
        startTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            TimeCalculation();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editHours.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TimeCalculation();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editMinutes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TimeCalculation();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return view;
    }

    private void TimeCalculation() {
        String startValue = startTime.getText().toString();
        String hoursValue = editHours.getText().toString();
        String minutesValue = editMinutes.getText().toString();
        int mathMins = 0;
        int mathHours = 0;
        try {
            if (!startValue.isEmpty() && !hoursValue.isEmpty()) {

                if (minutesValue.isEmpty()) {
                    mathMins = 0;
                } else {
                    mathMins = Integer.parseInt(minutesValue);
                    if (mathMins < 0) {
                        throw new NumberFormatException();
                    }
                }
                mathHours = Integer.parseInt(hoursValue);
                if (mathHours < 0) {
                    throw new NumberFormatException();
                }
                mathMins = mathMins + minute;

                while (mathMins >= 60) {
                    mathHours += 1;
                    mathMins -= 60;
                }
                long days = 0;
                mathHours = mathHours + hour;
                while (mathHours >= 24) {
                    mathHours -= 24;
                    days += 1;
                }
                if (days == 0) {
                    result.setText(String.format(Locale.getDefault(), "%02d:%02d", mathHours, mathMins));
                } else if (days == 1) {
                    result.setText(String.format(Locale.getDefault(), "%d day has passed and is currently %02d:%02d", days, mathHours, mathMins));
                } else {
                    result.setText(String.format(Locale.getDefault(), "%d days has passed and is currently %02d:%02d", days, mathHours, mathMins));
                }

            } else {
                result.setText("");
            }

        }
        catch (NumberFormatException e){
            result.setText("Invalid input. Please enter positive numbers.");
        }
        }
    }
