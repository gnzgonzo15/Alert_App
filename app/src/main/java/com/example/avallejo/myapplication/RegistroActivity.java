package com.example.avallejo.myapplication;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {


    EditText edt_name , edt_user , edt_pass;
    TextView registrado;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edt_user = findViewById(R.id.Reg_user);
        edt_name = findViewById(R.id.Reg_nombre);
        edt_pass = findViewById(R.id.Reg_pass);
        registrado = findViewById(R.id.Registrado);


        btn_register = findViewById(R.id.Reg_button);
        btn_register.setOnClickListener(this);


        registrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(intent1);
            }
        });


    }

    public void onClick(View view) {

        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        view.startAnimation(animAlpha);

        final String APP_USER=edt_user.getText().toString();
        final String APP_NAME=edt_name.getText().toString();
        final String APP_PASS=edt_pass.getText().toString();


        Response.Listener<String> responselistener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonresponse = new JSONObject(response);
                    boolean success = jsonresponse.getBoolean("success");

                    if (success){

                        Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                        RegistroActivity.this.startActivity(intent);
                    }else

                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegistroActivity.this);
                        builder.setMessage("Error de creacion").setNegativeButton("Reintentar",null).create().show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        RegistroRequest registerRequest = new RegistroRequest(APP_USER, APP_NAME , APP_PASS, responselistener);
        RequestQueue queue = Volley.newRequestQueue(RegistroActivity.this);
        queue.add(registerRequest);

    }

    }
