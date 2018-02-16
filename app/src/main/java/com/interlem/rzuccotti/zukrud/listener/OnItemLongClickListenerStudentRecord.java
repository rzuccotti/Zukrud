package com.interlem.rzuccotti.zukrud.listener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.interlem.rzuccotti.zukrud.activity.MainActivity;
import com.interlem.rzuccotti.zukrud.R;
import com.interlem.rzuccotti.zukrud.database.TableControllerStudent;
import com.interlem.rzuccotti.zukrud.database.model.ObjectStudent;

import java.util.List;

/**
 * Created by rzuccotti on 09/02/2018.
 */

public class OnItemLongClickListenerStudentRecord implements AdapterView.OnItemLongClickListener {

    Context context;
    String id;


    public void editRecord(final int studentId) {
        final TableControllerStudent tableControllerStudent = new TableControllerStudent(context);
        ObjectStudent objectStudent = tableControllerStudent.readSingleRecord(studentId);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.student_input_form, null, false);

        final EditText editTextStudentFirstname = formElementsView.findViewById(R.id.editTextStudentFirstname);
        final EditText editTextStudentEmail = formElementsView.findViewById(R.id.editTextStudentEmail);

        editTextStudentFirstname.setText(objectStudent.getFirstName());
        editTextStudentEmail.setText(objectStudent.getEmail());

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                ObjectStudent objectStudent = new ObjectStudent();
                                objectStudent.setId(studentId);
                                objectStudent.setFirstName(editTextStudentFirstname.getText().toString());
                                objectStudent.setEmail(editTextStudentEmail.getText().toString());

                                boolean updateSuccessful = tableControllerStudent.update(objectStudent);

                                if(updateSuccessful){
                                    Toast.makeText(context, "Student record was updated.", Toast.LENGTH_SHORT).show();
                                    Log.i("Modifica studente", "Modificato studente: " + objectStudent.toString());
                                }else{
                                    Toast.makeText(context, "Unable to update student record.", Toast.LENGTH_SHORT).show();
                                    Log.e("Modifica studente", "Impossibile modificare lo studente: " + objectStudent.toString());
                                }

                                ((MainActivity) context).countRecords();
                                ((MainActivity) context).readRecords();
                            }

                        }).show();
    }

    public ObjectStudent getStudentById(final int studentId)
    {
        final TableControllerStudent tableControllerStudent = new TableControllerStudent(context);
        ObjectStudent objectStudent = tableControllerStudent.readSingleRecord(studentId);
        return objectStudent;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int index_i, long index_l) {
        context = view.getContext();
        MainActivity ma = (MainActivity) context;
        final ListView listview = ma.findViewById(R.id.listview);
        listview.setOnItemClickListener(null);

        final List<ObjectStudent> students = new TableControllerStudent(context).read();
        final ObjectStudent student = students.get(index_i);

        final CharSequence[] items = { "Edit", "Delete" };

        new AlertDialog.Builder(context).setTitle(student.getFirstName())
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        if (item == 0) {
                            editRecord(student.getId());
                        }
                        else if (item == 1) {

                            boolean deleteSuccessful = new TableControllerStudent(context).delete(student.getId());

                            if (deleteSuccessful){
                                Toast.makeText(context, "Student record was deleted.", Toast.LENGTH_SHORT).show();
                                Log.i("Elimina studente", "Aggiunto studente: " + student.toString());
                            }else{
                                Toast.makeText(context, "Unable to delete student record.", Toast.LENGTH_SHORT).show();
                                Log.e("Elimina studente", "Impossibile modificare lo studente: " + student.toString());
                            }

                            ((MainActivity) context).countRecords();
                            ((MainActivity) context).readRecords();
                            listview.setOnItemLongClickListener(new OnItemLongClickListenerStudentRecord());

                        }

                    }
                }).show();

        return false;
    }
}
