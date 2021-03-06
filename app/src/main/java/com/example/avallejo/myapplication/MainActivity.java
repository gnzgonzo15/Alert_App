package com.example.avallejo.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView mainlist;
    private MediaPlayer msp;
    private String[] listcontent = {"Alerta 1","Alerta 2","Alerta 3"};
    private int[] resID = {R.raw.alert,R.raw.alert2,R.raw.alerta3,};


    private BottomNavigationView.OnNavigationItemSelectedListener OnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.Menu:
                    item.setChecked( true );
                    Intent intent1 = new Intent( MainActivity.this, Menu.class );
                    MainActivity.this.startActivity( intent1 );
                    return true;

                case R.id.Sonidos:
                    item.setChecked( true );
                    return true;

                case R.id.Perfil:
                    item.setChecked( true );
                    Intent intent3 = new Intent( MainActivity.this, Perfil.class );
                    MainActivity.this.startActivity( intent3 );

                    return true;
                case R.id.Ubicacion:
                    item.setChecked( true );
                    Intent intent4 = new Intent( MainActivity.this, Mapamenu.class );
                    MainActivity.this.startActivity( intent4 );

                    return true;

            }
            return false;
        }
    };



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        BottomNavigationView navigation = findViewById( R.id.navigation );
        navigation.setOnNavigationItemSelectedListener( OnNavigationItemSelectedListener );

        mainlist = findViewById( R.id.listM );
        msp = new MediaPlayer();

        ArrayAdapter<String> adapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, listcontent );
        mainlist.setAdapter( adapter );
        mainlist.setOnItemClickListener( new AdapterView.OnItemClickListener()


        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {
                playSong( position );
            }

        } );
        }

    public void playSong(int songIndex) {
        // Play song
        msp.reset();// stops any current playing song
        msp = MediaPlayer.create( getApplicationContext(), resID[songIndex] );// create's
        // new mediaplayer with song.
        msp.start(); // starting mediaplayer

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        msp.release();
    }

}
