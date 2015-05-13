package com.examples.android.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button btn_time = (Button) findViewById(R.id.butn_time);
		final Button btn_date = (Button) findViewById(R.id.butn_date);

		btn_time.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Calendar mcurrentTime = Calendar.getInstance();
				int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
				int minute = mcurrentTime.get(Calendar.MINUTE);
				TimePickerDialog mTimePicker;
				mTimePicker = new TimePickerDialog(MainActivity.this,
						new TimePickerDialog.OnTimeSetListener() {
							@Override
							public void onTimeSet(TimePicker timePicker,
									int selectedHour, int selectedMinute) {

								try {
									String time = timeSetting(selectedHour
											+ ":" + selectedMinute + ":00");
									btn_time.setText(time);
								} catch (java.text.ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

						}, hour, minute, false);// Yes 24 hour time
				mTimePicker.setTitle("Select Time");
				mTimePicker.show();

			}
		});

		btn_date.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Calendar mcurrentDate = Calendar.getInstance();
				int mYear = mcurrentDate.get(Calendar.YEAR);
				int mMonth = mcurrentDate.get(Calendar.MONTH);
				int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

				DatePickerDialog mDatePicker;
				mDatePicker = new DatePickerDialog(MainActivity.this,
						new OnDateSetListener() {
							public void onDateSet(DatePicker datepicker,
									int selectedyear, int selectedmonth,
									int selectedday) {
								// TODO Auto-generated method stub
								/* Your code to get date and time */
								selectedmonth = selectedmonth + 1;
								btn_date.setText("" + selectedday + "/"
										+ selectedmonth + "/" + selectedyear);
							}
						}, mYear, mMonth, mDay);
				mDatePicker.setTitle("Select Date");
				mDatePicker.show();
			}
		});
	}

	private String timeSetting(String time) throws java.text.ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm a");
		Date dt;
		try {
			dt = sdf.parse(time);
			System.out.println("Time Display: " + sdfs.format(dt)); // <-- I got
			return "" + sdfs.format(dt); // result
			// here
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
