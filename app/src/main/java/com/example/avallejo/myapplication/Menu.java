package com.example.avallejo.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends AppCompatActivity  {

    private TextView mTextMessage;

    //Audio
    Context ctx ,men;

    private RadioButton funcion;
    private Button btnactivar;
    private Boolean active;
    private static final String String_preferences = "Button_state";
    private static final String Preference_estate_Button = "estado.button";
    AudioManager am;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("ResourceType")
        public boolean onNavigationItemSelected ( MenuItem item) {
            switch (item.getItemId()) {

                case (R.id.Menu) :
                    item.setChecked(true);
                    return true;

                case R.id.Sonidos:
                    item.setChecked(true);
                    Intent intent2 = new Intent(Menu.this, MainActivity.class);
                    Menu.this.startActivity(intent2);
                    return true;

                case R.id.Perfil:
                    item.setChecked(true);
                    Intent intent3 = new Intent(Menu.this, Perfil.class);
                    Menu.this.startActivity(intent3);
                    return true;

                case (R.id.Ubicacion):
                    item.setChecked(true);
                    Intent intent4 = new Intent(Menu.this, Mapamenu.class);
                    Menu.this.startActivity(intent4);
                    return true;
            }
            return false ;
        }

    };

    public Menu() {

    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ctx=this;
        //Audio
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        am.setStreamVolume(AudioManager.STREAM_MUSIC,am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);

        // RadioButton
        funcion = findViewById(R.id.radiobutton);
        active = funcion.isChecked(); //Desactivado

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mTextMessage = findViewById(R.id.message);

        if (obtenersavebutton()){
            funcion.setChecked(true);
            Toast.makeText(this, "Se encuentra activo", Toast.LENGTH_LONG).show();
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            // Iniciar Servicio
            startService(new Intent(ctx,MyService.class));
            onResume();

        }else{
            Toast.makeText(this, "Se encuentra inactivo", Toast.LENGTH_LONG).show();
            //Finznalizar Servicio
            stopService(new Intent(ctx,MyService.class));
        }

        funcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //activado
                if (active) {
                    funcion.setChecked(false);
                }
                active = funcion.isChecked();
            }
        });
        // Boton Guardar
        btnactivar = findViewById(R.id.btnactivar);
        btnactivar.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              saveButton();
                                              new MyService();
                                          }
                                      }
        );
    }

    public Menu saveButton (){
        SharedPreferences sharedPreferences = getSharedPreferences(String_preferences, MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(Preference_estate_Button, funcion.isChecked()).apply();
        //MyService.
        Toast.makeText(Menu.this, "Nuevo estado guardado", Toast.LENGTH_SHORT).show();
        return new Menu();
    }

    public boolean obtenersavebutton (){
        SharedPreferences sharedPreferences = getSharedPreferences(String_preferences, MODE_PRIVATE);
        return sharedPreferences.getBoolean(Preference_estate_Button, false);

    }

    @Override public  void  onResume(){
        super.onResume();
    }

    @Override public void onPause(){
        super.onPause();
    }
}

