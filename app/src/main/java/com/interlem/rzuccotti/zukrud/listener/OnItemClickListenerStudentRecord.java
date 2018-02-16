package com.interlem.rzuccotti.zukrud.listener;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.interlem.rzuccotti.zukrud.database.TableControllerStudent;
import com.interlem.rzuccotti.zukrud.database.model.ObjectStudent;

import java.util.List;

/**
 * Created by rzuccotti on 15/02/2018.
 */

public class OnItemClickListenerStudentRecord implements AdapterView.OnItemClickListener {

    Context context;
    String id;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int index_i, long index_l) {

        context = view.getContext();
        final List<ObjectStudent> students = new TableControllerStudent(context).read();
        final ObjectStudent student = students.get(index_i);

        Toast.makeText(context, "In futuro verrà mostrato il dettaglio dello studente: " + student.getFirstName(), Toast.LENGTH_SHORT).show();
    }
}
