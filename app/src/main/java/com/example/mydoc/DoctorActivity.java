package com.example.mydoc;

import android.content.Intent;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.util.Calendar;

public class DoctorActivity extends AppCompatActivity  {

    FirebaseAuth auth ;
    FirebaseUser user ;
    Button buttonAjouterPatient ;
    Button buttonConsulterListPatients ;
    Button buttonAjouterRDV ;
    Button buttonConsulterListRDV ;

    private DrawerLayout drawerLayout;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_espace_docteur );

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        buttonAjouterPatient = (Button) findViewById( R.id.buttonAjouterPatient );
        buttonConsulterListPatients = (Button) findViewById( R.id.buttonConsulterListPatients );
        buttonAjouterRDV = (Button) findViewById( R.id.buttonAjouterRDV );
        buttonConsulterListRDV = (Button) findViewById( R.id.buttonConsulterRDV );
           Calendar calendar = Calendar.getInstance();
           String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

           TextView textViewDate = findViewById(R.id.text_view_date);
           textViewDate.setText(currentDate);




           buttonAjouterPatient.setOnClickListener( new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent( DoctorActivity.this, AddPatient.class );
                   startActivity(intent);
               }
           } );
           buttonConsulterListPatients.setOnClickListener( new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent1 = new Intent( DoctorActivity.this,ConsulterListPatients.class );
                   startActivity(intent1);

               }
           } );

           buttonAjouterRDV.setOnClickListener( new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent( DoctorActivity.this, AddRDV.class );
                   startActivity(intent);

               }
           } );
           buttonConsulterListRDV.setOnClickListener( new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent3 = new Intent( DoctorActivity.this,ConsulterListRDV.class );
                   startActivity(intent3);
               }
           } );

          /* Toolbar toolbar = findViewById( R.id.toolbar );
           setSupportActionBar( toolbar );

           drawerLayout = findViewById( R.id.draw_layout2 );

           NavigationView navigationView = findViewById( R.id.nav_view );
           navigationView.setNavigationItemSelectedListener( this );


           ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawerLayout, toolbar,
                   R.string.navigation_drawer_open, R.string.navigation_drawer_close );

           drawerLayout.addDrawerListener( toggle );
           toggle.syncState();*/



       }

    public void signout(View v)
    {
        auth.signOut();
        finish();
        Intent i = new Intent( this,MainActivity.class );
        startActivity(i);
    }

   /* @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.nav_Search:
                getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container,
                        new MessageFragment() ).commit();
                break;

            case R.id.nav_ajouter_patient:
                Intent intent = new Intent( EspaceDocteur.this,AjouterPatient.class );
                startActivity(intent);
                break;

            case R.id.nav_consulter_list_patient:
                Intent intent1 = new Intent( EspaceDocteur.this,ConsulterListPatients.class );
                startActivity(intent1);
                break;

            case R.id.nav_ajoutet_rdv :

                break;

            case R.id.nav_Consulter_list_rdv :

                                break;



        }

        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen( GravityCompat.START )) {
            drawerLayout.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }
*/

}
