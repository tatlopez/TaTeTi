package com.example.tateti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView lblJugador, lblGanador;
    Button btnCentro, btnCentroArriba, btnCentroAbajo, btnCentroDerecha, btnCentroIzquierda,
           btnArribaDerecha, btnArribaIzquierda, btnAbajoDerecha, btnAbajoIzquierda, btnOtraVez;
    String nombre, usa;

    boolean tengoElTurno;
    ControlDeJuego cj;
    Object[] posCentro = {1,1};
    Object[] posCentroArriba = {0,1};
    Object[] posCentroAbajo = {2,1};
    Object[] posCentroDerecha = {1,2};
    Object[] posCentroIzquierda = {1, 0};
    Object[] posArribaDerecha = {0, 2};
    Object[] posArribaIzquierda = {0, 0};
    Object[] posAbajoDerecha = {2, 2};
    Object[] posAbajoIzquierda = {2, 0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tengoElTurno =  true;
        nombre = getIntent().getStringExtra("nombre");
        usa = getIntent().getStringExtra("usa");
        cj = new ControlDeJuego();
        lblJugador = (TextView) findViewById(R.id.lblJugador);
        lblJugador.setText(nombre + " juega con " + usa);
        btnCentro = (Button) findViewById(R.id.btnCentro);
        btnCentro.setTag(posCentro);
        btnCentroArriba = (Button) findViewById(R.id.btnCentroArriba);
        btnCentroArriba.setTag(posCentroArriba);
        btnCentroAbajo = (Button) findViewById(R.id.btnCentroAbajo);
        btnCentroAbajo.setTag(posCentroAbajo);
        btnCentroDerecha = (Button) findViewById(R.id.btnCentroDerecha);
        btnCentroDerecha.setTag(posCentroDerecha);
        btnCentroIzquierda = (Button) findViewById(R.id.btnCentroIzquierda);
        btnCentroIzquierda.setTag(posCentroIzquierda);
        btnArribaDerecha = (Button) findViewById(R.id.btnArribaDerecha);
        btnArribaDerecha.setTag(posArribaDerecha);
        btnArribaIzquierda = (Button) findViewById(R.id.btnArribaIzquierda);
        btnArribaIzquierda.setTag(posArribaIzquierda);
        btnAbajoDerecha = (Button) findViewById(R.id.btnAbajoDerecha);
        btnAbajoDerecha.setTag(posAbajoDerecha);
        btnAbajoIzquierda = (Button) findViewById(R.id.btnAbajoIzquierda);
        btnAbajoIzquierda.setTag(posAbajoIzquierda);
        lblGanador = (TextView) findViewById(R.id.lblGanador);
        btnOtraVez = (Button) findViewById(R.id.btnOtraVez);

        btnArribaIzquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
        btnCentroArriba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
        btnArribaDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
        btnCentroIzquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
        btnCentro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
        btnCentroDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
        btnAbajoIzquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
        btnCentroAbajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
        btnAbajoDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
        btnOtraVez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAJugar(v);
            }
        });
    }
    private void controloBoton(View v) {
        if (tengoElTurno) {
            Button seleccionado = (Button) v;
            if (usa.equalsIgnoreCase("circulos"))
                seleccionado.setText("O");
            else
                seleccionado.setText("X");
            tengoElTurno = false;
            seleccionado.setEnabled(false);
            Object tag = seleccionado.getTag();
            if (tag instanceof Object[]) {
                Object[] pos = (Object[]) tag;
                int i = (int) pos[0];
                int j = (int) pos[1];
                cj.asignarValorJugado(i, j, usa);
                if (cj.gano()) {
                    lblGanador.setText("El ganador es " + nombre);
                } else if(cj.hayEmpate()) {
                    lblGanador.setText("Hay empate");
                } else {
                    int[] nuevo = cj.proximoMovimiento();
                    int filaMaquina = nuevo[0];
                    int columnaMaquina = nuevo[1];
                    Button botonMaquina = null;
                    switch (filaMaquina) {
                        case 0:
                            switch (columnaMaquina) {
                                case 0:
                                    botonMaquina = btnArribaIzquierda;
                                    break;
                                case 1:
                                    botonMaquina = btnCentroArriba;
                                    break;
                                case 2:
                                    botonMaquina = btnArribaDerecha;
                                    break;
                            }
                            break;
                        case 1:
                            switch (columnaMaquina) {
                                case 0:
                                    botonMaquina = btnCentroIzquierda;
                                    break;
                                case 1:
                                    botonMaquina = btnCentro;
                                    break;
                                case 2:
                                    botonMaquina = btnCentroDerecha;
                                    break;
                            }
                            break;
                        case 2:
                            switch (columnaMaquina) {
                                case 0:
                                    botonMaquina = btnAbajoIzquierda;
                                    break;
                                case 1:
                                    botonMaquina = btnCentroAbajo;
                                    break;
                                case 2:
                                    botonMaquina = btnAbajoDerecha;
                                    break;
                            }
                            break;
                    }
                    if (botonMaquina != null) {
                        if (usa.equalsIgnoreCase("circulos"))
                            botonMaquina.setText("X");
                        else
                            botonMaquina.setText("O");
                        botonMaquina.setEnabled(false);
                        cj.asignarValorJugado(filaMaquina, columnaMaquina, "circulos".equalsIgnoreCase(usa) ? "cruces" : "circulos");
                        if (cj.gano()) {
                            lblGanador.setText("El ganador es la máquina");
                        } else if(cj.hayEmpate()) {
                            lblGanador.setText("Hay empate");
                        } else {
                            tengoElTurno = true;
                        }

                    }
                }
            }
        }
    }

    public void volverAJugar(View v) {
        Intent aux = new Intent(GameActivity.this, MainActivity.class);
        startActivity(aux);
    }
}
