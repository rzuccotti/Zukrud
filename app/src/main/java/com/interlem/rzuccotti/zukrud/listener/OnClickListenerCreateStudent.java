package com.interlem.rzuccotti.zukrud.listener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.interlem.rzuccotti.zukrud.activity.MainActivity;
import com.interlem.rzuccotti.zukrud.R;
import com.interlem.rzuccotti.zukrud.database.TableControllerStudent;
import com.interlem.rzuccotti.zukrud.database.model.ObjectStudent;
import com.interlem.rzuccotti.zukrud.enumeration.ServerOperation;
import com.interlem.rzuccotti.zukrud.server.ClientThread;
import com.interlem.rzuccotti.zukrud.utility.BuildXMLFile;

/**
 * Created by rzuccotti on 09/02/2018.
 */

public class OnClickListenerCreateStudent implements View.OnClickListener {

    Context context;

    @Override
    public void onClick(View view) {
        context = view.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.student_input_form, null, false);

        final EditText editTextStudentFirstname = formElementsView.findViewById(R.id.editTextStudentFirstname);
        final EditText editTextStudentEmail = formElementsView.findViewById(R.id.editTextStudentEmail);

        new AlertDialog.Builder(context)
            .setView(formElementsView)
            .setTitle("Create Student")
            .setPositiveButton("Add",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                String studentFirstname = editTextStudentFirstname.getText().toString();
                String studentEmail = editTextStudentEmail.getText().toString();

                ObjectStudent objectStudent = new ObjectStudent();
                objectStudent.setFirstName(studentFirstname);
                objectStudent.setEmail(studentEmail);

                boolean createSuccessful = new TableControllerStudent(context).create(objectStudent);

                if(createSuccessful){
                    String xml = BuildXMLFile.buildStudentXML(objectStudent, ServerOperation.CREATE);
                    new Thread(new ClientThread(xml)).start();

                    Toast.makeText(context, "Student information was saved.", Toast.LENGTH_SHORT).show();
                    Log.i("Crea studente", "Creato studente: " + objectStudent.toString());
                }else{
                    Toast.makeText(context, "Unable to save student information.", Toast.LENGTH_SHORT).show();
                    Log.e("Crea studente", "Impossibile creare lo studente: " + objectStudent.toString());
                }

                ((MainActivity) context).readRecords();
            }

        }).show();
    }
}