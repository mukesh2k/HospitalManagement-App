package com.example.mydoc;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EditDeleteRDV extends AppCompatActivity {

    FirebaseDatabase database ;
    private DatabaseReference databaseReferenceRDV ;

    EditText editTextNom ;
    EditText editTextPrenom;
    EditText editTextEmail ;
    EditText editTextDate ;
    EditText editTextHeur ;
    EditText editTextPhone ;

    Button buttonRetour ;
    Button buttonEdit ;
    Button buttonDelete ;

    private String key ;
    private String nom ;
    private String prenom ;
    private String eMail ;
    private String phone ;
    private String date ;
    private String heur ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_edit_delete_rdv );

        key = getIntent().getStringExtra( "Key" );
        nom = getIntent().getStringExtra( "Nom" );
        prenom = getIntent().getStringExtra( "Prenom" );
        eMail = getIntent().getStringExtra( "Email" );
        phone = getIntent().getStringExtra( "Phone" );
        date = getIntent().getStringExtra( "Date" );
        heur = getIntent().getStringExtra( "Heur" );



        database = FirebaseDatabase.getInstance();
        databaseReferenceRDV = database.getReference( "Appointment");

        editTextNom = (EditText) findViewById( R.id.editTextName);
        editTextNom.setText( nom );

        editTextPrenom = (EditText) findViewById( R.id.editTextLastName);
        editTextPrenom.setText( prenom );

        editTextDate = (EditText) findViewById( R.id.editTextDate );
        editTextDate.setText( date );

        editTextEmail = (EditText) findViewById( R.id.editTextEmail );
        editTextEmail.setText( eMail );

        editTextHeur = (EditText) findViewById( R.id.editTextHeur );
        editTextHeur.setText( heur );

        editTextPhone = (EditText) findViewById( R.id.editTextPhone );
        editTextPhone.setText( phone );

        buttonEdit = (Button) findViewById( R.id.buttonEdit );
        buttonDelete = (Button) findViewById( R.id.buttonDelete );
        buttonRetour = (Button) findViewById( R.id.buttonReturn);

        buttonEdit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RDV appointment = new RDV( );
                appointment.setFirstName( editTextNom.getText().toString() );
                appointment.setLastName( editTextPrenom.getText().toString() );
                appointment.setEmail( editTextEmail.getText().toString() );
                appointment.setDateRDV( editTextDate.getText().toString() );
                appointment.setHour( editTextHeur.getText().toString() );
                appointment.setTel( editTextPhone.getText().toString() );

                new FirebaseDatabaseHelperRDV().upDateRDV( key, appointment, new FirebaseDatabaseHelperRDV.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<RDV> appointments, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                        Toast.makeText( EditDeleteRDV.this, "Rendez-vous has been updated succesfully ...", Toast.LENGTH_LONG ).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                } );



            }
        } );

        buttonDelete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new FirebaseDatabaseHelperRDV().deleteRDV( key, new FirebaseDatabaseHelperRDV.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<RDV> appointments, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                        Toast.makeText( EditDeleteRDV.this, "Rendez-vous has been deleted succesfuly ...", Toast.LENGTH_LONG ).show();
                        finish();return;
                    }
                } );

            }
        } );
        buttonRetour.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( EditDeleteRDV.this,ConsulterListRDV.class );
                startActivity(intent);

            }
        } );
    }



}
