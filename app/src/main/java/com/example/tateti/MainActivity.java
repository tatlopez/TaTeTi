package com.example.tateti;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText nombre;
    Button boton;
    RadioButton circulo, cruces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.txtNombre);
        boton = findViewById(R.id.btnComenzar);
        circulo =  findViewById(R.id.rbCirculos);
        cruces =  findViewById(R.id.rbCruces);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aux = new Intent(MainActivity.this, GameActivity.class);
                String elNombre = nombre.getText().toString();
                Pattern caracterEspecial = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
                Matcher tieneCaracterEspecial = caracterEspecial.matcher(elNombre);
                if(tieneCaracterEspecial.find()) {
                    Toast mensaje = Toast.makeText(MainActivity.this, "El nombre no puede tener caracteres especiales", Toast.LENGTH_SHORT);
                    mensaje.show();
                } else {
                    if(elNombre.isEmpty())
                        elNombre = "Extraño";
                    aux.putExtra("nombre", elNombre);
                    if(circulo.isChecked())
                        aux.putExtra("usa", "circulos");
                    else
                        aux.putExtra("usa", "cruces");
                    startActivity(aux);
                }
            }
        });

    }
}
