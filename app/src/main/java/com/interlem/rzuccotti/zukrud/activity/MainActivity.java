package com.interlem.rzuccotti.zukrud.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.interlem.rzuccotti.zukrud.R;
import com.interlem.rzuccotti.zukrud.utility.StableArrayAdapter;
import com.interlem.rzuccotti.zukrud.database.TableControllerStudent;
import com.interlem.rzuccotti.zukrud.listener.OnClickListenerCreateStudent;
import com.interlem.rzuccotti.zukrud.listener.OnItemClickListenerStudentRecord;
import com.interlem.rzuccotti.zukrud.listener.OnItemLongClickListenerStudentRecord;
import com.interlem.rzuccotti.zukrud.database.model.ObjectStudent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton buttonCreateStudent = findViewById(R.id.createStudent);
        buttonCreateStudent.setOnClickListener(new OnClickListenerCreateStudent());

        readRecords();
    }

    public void readRecords() {

        final ListView listview = findViewById(R.id.listview);
        final List<ObjectStudent> students = new TableControllerStudent(this).read();
        final List<String> list = new ArrayList<>();
        for (ObjectStudent student: students)
        {
            list.add(student.getFirstName());
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);
        listview.setOnItemLongClickListener(new OnItemLongClickListenerStudentRecord());
        listview.setOnItemClickListener(new OnItemClickListenerStudentRecord());

    }

}
