package com.example.avallejo.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

    //Audio
    // Inicializamos la instancia del receiver
    MusicIntentReceiver myReceiver;
    public MyService() {
    }

    //Se ejecuta cuando el servicio esta creado en memoria
    @Override
    public void onCreate() {
        super.onCreate();
        //Instanciamos el NotificationManagement
        Toast.makeText(this,"Servicio creado",Toast.LENGTH_LONG).show();
    }

    //Cuando el proceso se encuentra activo llama al Onstarcommand
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        myReceiver = new MusicIntentReceiver();
        myReceiver.setActivate(true);
        registerReceiver(myReceiver, filter);
        Toast.makeText(this,"Servicio Iniciado " +startId,Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //Elimina el servicio
    @Override
    public void onDestroy() {
        Toast.makeText(this,"Servicio Finalizado ",Toast.LENGTH_LONG).show();
        myReceiver.setActivate(false);
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }
}
