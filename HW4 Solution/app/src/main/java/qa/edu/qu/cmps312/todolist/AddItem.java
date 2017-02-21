package qa.edu.qu.cmps312.todolist;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.TimeZone;

import static android.R.attr.data;
import static qa.edu.qu.cmps312.todolist.R.id.dateView;
import static qa.edu.qu.cmps312.todolist.R.id.newToDo;
import static qa.edu.qu.cmps312.todolist.R.id.statusGroup;

public class AddItem extends Activity {
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    TextView dateView;
    TextView timeView;
    TextView titleView;
    RadioGroup status = null;
    RadioGroup priority = null;
    toDo item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);


        priority = (RadioGroup) findViewById(R.id.priorityGroup);
        status = (RadioGroup) findViewById(R.id.statusGroup);
        Button timeButton = (Button) findViewById(R.id.timeButton);
        Button dateButton = (Button) findViewById(R.id.dateButton);
        Button submitButton = (Button) findViewById(R.id.submitButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        Button resetButton = (Button) findViewById(R.id.resetButton);

        titleView = (TextView) findViewById(R.id.titleAdd);
        timeView = (TextView) findViewById(R.id.timeViewAdd);
        dateView = (TextView) findViewById(R.id.dateView);


        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddItem.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timeView.setText( selectedHour + ":" + selectedMinute);
                        item.setTime(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                DatePickerDialog datePicker = new DatePickerDialog(AddItem.this,
                        R.style.AppTheme,
                        datePickerListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));

                datePicker.setCancelable(false);
                datePicker.setTitle("Select the date");

                datePicker.show();
            }

    });

        status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.completedButton){
                    item.setDone(true);
                } else if(checkedId == R.id.notCompletedButton){
                    item.setDone(false);
                }
            }
        });



        priority.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.lowButton){
                    item.setPriority(0);
                } else if(checkedId == R.id.mediumButton){
                    item.setPriority(1);
                } else if(checkedId == R.id.highButton){
                    item.setPriority(2);
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setTitle(titleView.getText().toString());


                Toast.makeText(AddItem.this, "Your item has been added",
                        Toast.LENGTH_LONG).show();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("title", item.getTitle());
                returnIntent.putExtra("priority", item.getPriority());
                returnIntent.putExtra("status", item.getDone());
                returnIntent.putExtra("date", item.getDate());
                returnIntent.putExtra("time", item.getTime());
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddItem.this, "You have not added an item!",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleView.setText("");
                dateView.setText("00/00/0000");
                timeView.setText("00:00:00");
                status.clearCheck();
                priority.clearCheck();

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        item = new toDo("temp", 1,"","",false);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);
            dateView.setText(day + "/" + month1 + "/" + year1);
           item.setDate(day1 + "/" + month1 + "/" + year1);

        }

    };






}
