package com.interlem.rzuccotti.zukrud;

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * Created by rzuccotti on 12/02/2018.
 */

public class OnClickListenerTestServer implements View.OnClickListener {

    private static Context context;

    @Override
    public void onClick(View view) {
        //new Thread(new ClientThread("Pigio il bottone 'test server'")).start();
        context = view.getContext();

        final List<ObjectStudent> students = new TableControllerStudent(context).read();
        final ObjectStudent student = students.get(0);
        String xmlCreate = BuildXMLFile.buildStudentXML(student, "CREATE");
        String xmlUpdate = BuildXMLFile.buildStudentXML(student, "UPDATE");
        String xmlDelete = BuildXMLFile.buildStudentXML(student, "DELETE");

    }
}