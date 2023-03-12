package tt.widget;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends Activity implements NumberPicker.OnValueChangeListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private TextView tvTime;
    private NumberPicker npYear, npMonth, npDate, npHour, npMinute;
    private NumberPicker.Formatter monthFormatter = new NumberPicker.Formatter() {
        private Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        private FieldPosition fieldPosition = new FieldPosition(DateFormat.MONTH_FIELD);

        @Override
        public String format(int value) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getMinimum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.MONTH, value);
            return format.format(
                    calendar.getTime(), new StringBuffer(), fieldPosition
            ).substring(fieldPosition.getBeginIndex(), fieldPosition.getEndIndex());
        }
    };
    private Calendar now = Calendar.getInstance();
    private Calendar time = (Calendar) now.clone();
    private DateFormat format = SimpleDateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTime = findViewById(R.id.tv_time);
        tvTime.setText(format.format(time.getTime()));

        int month = time.get(Calendar.MONTH);
        npMonth = findViewById(R.id.np_month);
        npMonth.setMinValue(Calendar.JANUARY);
        npMonth.setMaxValue(Calendar.DECEMBER);
        npMonth.setValue(month);
        npMonth.setOnValueChangedListener(this);
        npMonth.setFormatter(monthFormatter);

        int dayOfMonth = time.get(Calendar.DAY_OF_MONTH);
        npDate = findViewById(R.id.np_date);
        npDate.setMinValue(time.getMinimum(Calendar.DAY_OF_MONTH));
        npDate.setMaxValue(time.getActualMaximum(Calendar.DAY_OF_MONTH));
        npDate.setValue(dayOfMonth);
        npDate.setOnValueChangedListener(this);

        int year = time.get(Calendar.YEAR);
        npYear = findViewById(R.id.np_year);
        npYear.setMinValue(1990);
        npYear.setMaxValue(year);
        npYear.setValue(year);
        npYear.setEnabled(false);
        npYear.setOnValueChangedListener(this);

        int hourOfDay = time.get(Calendar.HOUR_OF_DAY);
        npHour = findViewById(R.id.np_hour);
        npHour.setMinValue(time.getMinimum(Calendar.HOUR_OF_DAY));
        npHour.setMaxValue(time.getMaximum(Calendar.HOUR_OF_DAY));
        npHour.setValue(hourOfDay);
        npHour.setFormatter(NumberPicker.getTwoDigitFormatter());
        npHour.setOnValueChangedListener(this);

        int minute = time.get(Calendar.MINUTE);
        npMinute = findViewById(R.id.np_minute);
        npMinute.setMinValue(time.getMinimum(Calendar.MINUTE));
        npMinute.setMaxValue(time.getMaximum(Calendar.MINUTE));
        npMinute.setValue(minute);
        npMinute.setFormatter(NumberPicker.getTwoDigitFormatter());
        npMinute.setOnValueChangedListener(this);
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        if (npYear.equals(picker)) {
            time.set(Calendar.DAY_OF_MONTH, time.getMinimum(Calendar.DAY_OF_MONTH));
            time.set(Calendar.YEAR, newVal);
            npDate.setMaxValue(time.getActualMaximum(Calendar.DAY_OF_MONTH));
            time.set(Calendar.DAY_OF_MONTH, npDate.getValue());
        } else if (npMonth.equals(picker)) {
            time.set(Calendar.DAY_OF_MONTH, time.getMinimum(Calendar.DAY_OF_MONTH));
            time.set(Calendar.MONTH, newVal);
            npDate.setMaxValue(time.getActualMaximum(Calendar.DAY_OF_MONTH));
            time.set(Calendar.DAY_OF_MONTH, npDate.getValue());
        } else if (npDate.equals(picker)) {
            time.set(Calendar.DAY_OF_MONTH, newVal);
        } else if (npHour.equals(picker)) {
            time.set(Calendar.HOUR_OF_DAY, newVal);
        } else if (npMinute.equals(picker)) {
            time.set(Calendar.MINUTE, newVal);
        }
        tvTime.setText(format.format(time.getTime()));
    }
}