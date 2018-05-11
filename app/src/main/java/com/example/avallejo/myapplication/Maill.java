package com.example.avallejo.myapplication;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Maill extends AppCompatActivity{

    String correo;
    String contrasena;
    String texto;
    Button enviar;
    javax.mail.Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapamenu);

        enviar = findViewById(R.id.enviarmaill);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        });

    }
}


