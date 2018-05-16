package com.example.avallejo.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.StrictMode;
import android.widget.Toast;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MyService extends Service {

    String correo;
    String contrasena;
    String texto;
    javax.mail.Session session;

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
        Mimail();
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

    private void Mimail(){

        correo="alertapp4@gmail.com";
        contrasena="Pruebas123$";
        texto="Este mensaje es enviado por AlertAPP, una falla de seguridad en los audifonos del dispositivo";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Properties properties = new  Properties();
        properties.put("mail.smtp.host","smtp.googlemail.com");
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port","465");

        try {
            session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(correo,contrasena);
                }
            });
            if (session!=null){
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(correo ));
                message.setSubject("Alerta de seguridad");
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("gonzalo_andrey@hotmail.com"));
                message.setRecipients(Message.RecipientType.CC,InternetAddress.parse("gnzgonzo15@gmail.com"));

                message.setContent(texto,"text/html; charset=utf-8");
                Transport.send(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
