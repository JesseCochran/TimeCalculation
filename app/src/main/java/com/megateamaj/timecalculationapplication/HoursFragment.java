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
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HoursFragment extends Fragment {

    TextView editStartTime, editTextEndTime;
    TextView textViewResult;
    int hour, minute;

    public HoursFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hours, container, false);
        editStartTime = view.findViewById(R.id.textViewStartTime);
        editTextEndTime = view.findViewById(R.id.textViewEndTime);
        textViewResult = view.findViewById(R.id.textViewResult);

        editStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        hour = selectedHour;
                        minute = selectedMinute;
                        editStartTime.setText((String.format(Locale.getDefault(), "%02d:%02d", hour, minute)));
                    }
                };
                int style = AlertDialog.THEME_HOLO_DARK;

                TimePickerDialog timePickerDialog = new TimePickerDialog(requireContext(), style, onTimeSetListener, hour, minute, true);

                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });
        editTextEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        hour = selectedHour;
                        minute = selectedMinute;
                        editTextEndTime.setText((String.format(Locale.getDefault(), "%02d:%02d", hour, minute)));
                    }
                };
                int style = AlertDialog.THEME_HOLO_DARK;

                TimePickerDialog timePickerDialog = new TimePickerDialog(requireContext(), style, onTimeSetListener, hour, minute, true);

                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });

        editStartTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                CalculateHours();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextEndTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                CalculateHours();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    private void CalculateHours() {
        String startValue = editStartTime.getText().toString();
        String endValue = editTextEndTime.getText().toString();
        if (!startValue.isEmpty() && !endValue.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());

            try {
                Date startTime = sdf.parse(editStartTime.getText().toString());
                Date endTime = sdf.parse(editTextEndTime.getText().toString());

                long timeDifferenceMillis = endTime.getTime() - startTime.getTime();
                if (timeDifferenceMillis / (60 * 60 * 1000) < 0){
                  timeDifferenceMillis = timeDifferenceMillis + (24 * (60 * 60 * 1000));
                }
                long hours = timeDifferenceMillis / (60 * 60 * 1000);
                long minutes = (timeDifferenceMillis / (60 * 1000)) % 60;

                // Display the result
                if (minutes > 1 && hours > 1) {
                    textViewResult.setText(String.format(Locale.getDefault(), "%d hours and %d minutes", hours, minutes));
                } else if (minutes == 1 && hours > 1) {
                    textViewResult.setText(String.format(Locale.getDefault(), "%d hours and %d minute", hours, minutes));
                }
                else if (minutes == 0 && hours > 1) {
                    textViewResult.setText(String.format(Locale.getDefault(), "%d hours", hours));
                }
                else if (minutes == 0 && hours == 1) {
                    textViewResult.setText(String.format(Locale.getDefault(), "%d hour", hours));
                }
                else if (minutes > 1 && hours == 1) {
                    textViewResult.setText(String.format(Locale.getDefault(), "%d hour and %d minutes", hours, minutes));
                }
                else if (minutes == 1 && hours == 1) {
                    textViewResult.setText(String.format(Locale.getDefault(), "%d hour and %d minute", hours, minutes));
                }
                else if (minutes > 1 && hours == 0) {
                    textViewResult.setText(String.format(Locale.getDefault(), "%d minutes", minutes));
                }
                else if (minutes == 1 && hours == 0) {
                    textViewResult.setText(String.format(Locale.getDefault(), "%d minute", minutes));
                }
                else if (minutes == 0 && hours == 0) {
                    textViewResult.setText(String.format(Locale.getDefault(), "%d hours and %d minutes", hours, minutes));
                }

            } catch (ParseException e) {
                e.printStackTrace();
                textViewResult.setText("Invalid time format");
            }
        }

        else {
            textViewResult.setText("");
        }
    }



    }
