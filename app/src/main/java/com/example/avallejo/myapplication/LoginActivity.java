package com.example.avallejo.myapplication;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {


    TextView registro;
    public EditText user;
    public EditText pass;
    public EditText name;
    private RadioButton rbsesion;
    private boolean isActivated;
    private final String String_preferences = "Preferencias";
    private final String Preferencias_button = "session_button";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (obtenersessionbutton()){

            Intent intent = new Intent(LoginActivity.this, Menu.class);
            LoginActivity.this.startActivity(intent);
            finish();

        }else {
            //Animacion del botón
            final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
            //***//

            registro = findViewById(R.id.Registrarse);
            user =  findViewById(R.id.User);
            pass =  findViewById(R.id.Pass);
           rbsesion = findViewById(R.id.rbsession);

            isActivated = rbsesion.isChecked(); // Radio button desactivado

            rbsesion.setOnClickListener(new View.OnClickListener() {
                //Activo
                @Override
                public void onClick(View v) {

                    if (isActivated) {

                        rbsesion.setChecked(false);
                    }
                    isActivated = rbsesion.isChecked();

                }
            });

            registro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                    LoginActivity.this.startActivity(intent);
                }
            });


            Button login = findViewById(R.id.Login);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sessionButton();
                    SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                    final SharedPreferences.Editor editor;
                    editor = sharedPreferences.edit();
                    editor.putString("User", user.getText().toString());
                    editor.putString("Pass", pass.getText().toString());
                    editor.apply();

                    view.startAnimation(animAlpha);


                    final String APP_USER = user.getText().toString();
                    final String APP_PASS = pass.getText().toString();


                    Response.Listener<String> ResponseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {

                                JSONObject Jsonresponse = new JSONObject(response);

                                Boolean success = Jsonresponse.getBoolean("success");
                                if (success) {

                                    final String APP_NAME = Jsonresponse.getString("APP_NAME");
                                    Intent intent = new Intent(LoginActivity.this, Menu.class);

                                    intent.putExtra("name", APP_NAME);
                                    Constantes.setName(APP_NAME);
                                    intent.putExtra("pass", APP_PASS);
                                    intent.putExtra("user", APP_USER);
                                    Constantes.setUser(APP_USER);


                                    LoginActivity.this.startActivity(intent);
                                    finish();

                                } else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    builder.setMessage("Error de ingreso")
                                            .setNegativeButton("Reintentar", null)
                                            .create().show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    LoginRequest loginRequest = new LoginRequest(APP_USER, APP_PASS, ResponseListener);
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(loginRequest);

                }
            });
        }
    }

    public void sessionButton (){
        SharedPreferences sharedPreferences = getSharedPreferences(String_preferences, MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(Preferencias_button, rbsesion.isChecked()).apply();

    }

    public boolean obtenersessionbutton (){
        SharedPreferences sharedPreferences = getSharedPreferences(String_preferences, MODE_PRIVATE);
        return sharedPreferences.getBoolean(Preferencias_button, false);

    }


}
