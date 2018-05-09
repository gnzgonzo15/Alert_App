package com.example.avallejo.myapplication;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Avallejo on 2/03/2018.
 */

class Utilidades {
    public static void mostrarToastText(Context actividad, String texto)

    {
        Toast toast = Toast.makeText(actividad, texto, Toast.LENGTH_LONG);
        toast.show();

    }
}
