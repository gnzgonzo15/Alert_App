package com.example.avallejo.myapplication;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Perfil extends AppCompatActivity {

    TextView viewuser , viewname ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);


        TextView mTextMessage = findViewById( R.id.message );
        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        viewuser = findViewById(R.id.textuser);
        viewname = findViewById(R.id.textcorreo);

        String S = Constantes.getName();
        String U = Constantes.getUser();

        viewname.setText(S);
        viewuser.setText(U);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Menu:

                Intent intent1 = new Intent(Perfil.this, Menu.class);
                Perfil.this.startActivity(intent1);
                return true;

            case R.id.Sonidos:
                Intent intent2 = new Intent(Perfil.this, MainActivity.class);
                Perfil.this.startActivity(intent2);

                return true;

            case R.id.Perfil:
                return true;

            case R.id.Ubicacion:
                Intent intent4 = new Intent(Perfil.this, Mapamenu.class);
                Perfil.this.startActivity(intent4);
                return true;
        }
        return false;
    }
        };
}
