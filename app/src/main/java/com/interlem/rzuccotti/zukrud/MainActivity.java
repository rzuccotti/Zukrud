package com.interlem.rzuccotti.zukrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCreateStudent = findViewById(R.id.buttonCreateStudent);
        buttonCreateStudent.setOnClickListener(new OnClickListenerCreateStudent());

        countRecords();
        readRecords();
    }

    public void countRecords() {
        int recordCount = new TableControllerStudent(this).count();
        TextView textViewRecordCount = findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " records found.");
    }

    public void readRecords() {

        LinearLayout linearLayoutRecords = findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<ObjectStudent> students = new TableControllerStudent(this).read();

        if (students.size() > 0) {

            for (ObjectStudent obj : students) {

                int id = obj.id;
                String studentFirstname = obj.firstname;
                String studentEmail = obj.email;

                String textViewContents = studentFirstname + " - " + studentEmail;

                TextView textViewStudentItem= new TextView(this);
                textViewStudentItem.setPadding(0, 20, 0, 20);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewStudentItem);
                textViewStudentItem.setOnLongClickListener(new OnLongClickListenerStudentRecord());
            }

        }

        else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }

    }
}
