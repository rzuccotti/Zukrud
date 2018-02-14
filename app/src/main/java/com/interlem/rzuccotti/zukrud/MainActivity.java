package com.interlem.rzuccotti.zukrud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton buttonCreateStudent = findViewById(R.id.createStudent);
        buttonCreateStudent.setOnClickListener(new OnClickListenerCreateStudent());

        Button buttonTestServer = findViewById(R.id.callServer);
        buttonTestServer.setOnClickListener(new OnClickListenerTestServer());

        countRecords();
        readRecords();

//        new Thread(new ClientThread("Avvio l'app")).start();

    }

    public void countRecords() {
        int recordCount = new TableControllerStudent(this).count();
        //TextView textViewRecordCount = findViewById(R.id.textViewRecordCount);
        //textViewRecordCount.setText(recordCount + " records found.");
    }

    public void readRecords() {

        final ListView listview = findViewById(R.id.listview);
        final List<ObjectStudent> students = new TableControllerStudent(this).read();
        final List<String> list = new ArrayList<>();
        for (ObjectStudent student: students)
        {
            list.add(student.firstname);
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);
        listview.setOnItemLongClickListener(new OnItemLongClickListenerStudentRecord());


    }

}
