package com.example.mydoc;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddRDV extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    FirebaseDatabase database ;
    DatabaseReference databaseReferenceRDV ;
    FirebaseAuth auth ;

    private RDV appointment ;
    public static String appointmentId;

    EditText editTextNom ;
    EditText editTextPrenom;
    EditText editTextDate;
    EditText editTextHeur ;
    EditText editTextPhone ;
    EditText editTextEmail ;
    EditText txt,txt1;
    Button buttonRetour ;
    public static String e=MainActivity.mail;
    Button buttonAdd ;
    RecyclerView recyclerViewRDV ;
    private ArrayList<RDV> arrayAdapterRDV = new ArrayList<>(  );
    private ArrayAdapter<RDV> rdvAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.fragment_ajouter_rendez_vous );


        editTextNom= (EditText) findViewById( R.id.editTextLastName);
        editTextDate = (EditText) findViewById( R.id.editTextDate );
        editTextHeur = (EditText) findViewById( R.id.editTextHeur );
        editTextPhone = (EditText) findViewById( R.id.editTextPhone );
        buttonAdd = (Button) findViewById( R.id.buttonAdd);
        editTextEmail = (EditText) findViewById( R.id.editTextEmail );
        buttonRetour = (Button) findViewById( R.id.buttonReturn);
        txt1=findViewById(R.id.editTextHeur);
        recyclerViewRDV = (RecyclerView) findViewById( R.id.recyclerViewRDV );

        rdvAdapter = new ArrayAdapter<RDV>(this, android.R.layout.simple_list_item_1, arrayAdapterRDV  );

        database = FirebaseDatabase.getInstance();
        databaseReferenceRDV = database.getReference( "Appointment");

        appointment = new RDV();
        txt=findViewById(R.id.editTextDate);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment d=new DateFragment();
                d.show(getSupportFragmentManager(),"DatePicker");
            }
        });

            txt1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(AddRDV.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txt1.setText( selectedHour + ":" + selectedMinute);
                        }
                    }, hour, minute, false);//Yes 24 hour time
                    mTimePicker.setTitle("Select time of your appointment");
                    mTimePicker.show();

                }
            });



        buttonRetour.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( AddRDV.this, DoctorActivity.class );
                startActivity(intent);
            }
        } );

        buttonAdd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String id = databaseReferenceRDV.push().getKey();
                if (TextUtils.isEmpty( appointmentId )) {




                    new FirebaseDatabaseHelper().readPatients(new FirebaseDatabaseHelper.DataStatus() {
                        @Override
                        public void DataIsLoaded(List<Patient> patients, List<String> keys) {
                            Patient y= new Patient();
                            System.out.println(e);
                            for(Patient x:patients) {
                                System.out.println(x.getEmail());
                                if(x.getEmail().contains(e))
                                {
                                    System.out.println(x.getFirstName());
                                    System.out.println(x.getLastName());
                                    y=x;
                                    break;
                                }
                            }

                            System.out.println(y.getFirstName());
                            System.out.println(y.getLastName());
                            System.out.println(editTextDate.getText().toString());
                            System.out.println(editTextHeur.getText().toString());
                            System.out.println(y.getPhone());
                            System.out.println(editTextNom.getText().toString());
                            System.out.println(editTextEmail.getText().toString());



                            RDV appointment1=new RDV(id,y.getFirstName(),y.getLastName(),editTextDate.getText().toString(),editTextHeur.getText().toString(),e,y.getPhone(),editTextNom.getText().toString(),editTextEmail.getText().toString(),y.getAge());
                            databaseReferenceRDV.child( id ).setValue( appointment1);
                        }



                        @Override
                        public void DataIsUpdated() {

                        }

                        @Override
                        public void DataIsDeleted() {

                        }
                    });



                    Toast.makeText( AddRDV.this, "Data inserted ...", Toast.LENGTH_LONG ).show();
                }







              /*  editTextPrenom.setText( "" );
                editTextDate.setText( "" );
                editTextHeur.setText( "" );
                editTextPhone.setText( "" );
                editTextEmail.setText( "" );*/

                Intent intent1 = new Intent( AddRDV.this, DoctorActivity.class );
                startActivity(intent1);
            }
        } );


    }

    private void getValuesRDV()
    {

        appointment.setFirstName(editTextNom.getText().toString());
        appointment.setLastName(editTextPrenom.getText().toString());
        appointment.setDateRDV(editTextDate.getText().toString());
        appointment.setHour(editTextHeur.getText().toString());
        appointment.setTel(editTextPhone.getText().toString());
        appointment.setEmail( editTextEmail.getText().toString() );
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR,i);
        c.set(Calendar.MONTH,i1);
        c.set(Calendar.DAY_OF_MONTH,i2);
        String s= DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        txt.setText(s);
    }



}