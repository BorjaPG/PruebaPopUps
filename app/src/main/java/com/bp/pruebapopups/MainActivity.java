package com.bp.pruebapopups;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button toast_button;
    Button snack_button;
    Button alert_button;
    Button progres_button;
    Button custom_button;
    TextView snack_text;
    TextView alert_text;
    ProgressBar det_bar;
    ProgressBar in_bar;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Ejemplo de Toast. */
        toast_button = (Button) findViewById(R.id.toast_button);
        toast_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Toast de ejemplo",Toast.LENGTH_SHORT).show();
            }
        });

        /* Ejemplo de SnackBar. */
        snack_text = (TextView) findViewById(R.id.snack_text);
        snack_button = (Button) findViewById(R.id.snack_button);
        snack_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Snackbar de ejemplo", Snackbar.LENGTH_LONG)
                        //Con setAction se puede añadir una acción con la que puede interactuar el usuario.
                        .setAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snack_text.setText("Acción snackbar completada.");
                            }
                        }).show();
            }
        });

        /* Ejemplo de AlertDialog. */
        alert_text = (TextView) findViewById(R.id.alert_text);
        alert_button = (Button) findViewById(R.id.alert_button);
        alert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Crea el cuadro de dialogo.
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("AlertDialog").setMessage("¿Desea borrar el mensaje?").setCancelable(false)
                        //setPositiveButton realiza la acción en cuestión.
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alert_text.setText(""); //Realiza la acción.
                                dialog.cancel(); //después cierra el dialogo.
                            }
                            //setNegativeButton cancela la acción.
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel(); //No hace nada y cierra el dialogo.
                            }
                        });
                        builder.create().show();
            }
        });

        /* Ejemplo de ProgressBar. */
        det_bar = (ProgressBar) findViewById(R.id.det_bar);
        in_bar = (ProgressBar) findViewById(R.id.in_bar);
        progres_button = (Button) findViewById(R.id.progres_button);
        progres_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0 || i == 10) {
                    //Hace visible el progressbar
                    det_bar.setVisibility(View.VISIBLE);
                    det_bar.setMax(100); //Establece un maximo de progreso.
                    in_bar.setVisibility(View.VISIBLE);
                }else if ( i< det_bar.getMax() ) {
                    //Establece el primer valor del progressbar1.
                    det_bar.setProgress(i); //setProgress actualiza el valor de la barra.
                    //Establece el primer valor del progressbar2.
                    det_bar.setSecondaryProgress(i + 10);
                }else {
                    det_bar.setProgress(0);
                    det_bar.setSecondaryProgress(0);
                    i = 0;
                    //Cierra las progressbar.
                    det_bar.setVisibility(View.GONE);
                    in_bar.setVisibility(View.GONE);
                }
                i = i + 10;
            }
        });

        /* Ejemplo de cuadro de dialogo personalizado. */

        custom_button = (Button) findViewById(R.id.custom_button);
        custom_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);

                dialog.setContentView(R.layout.custom_alert);
                dialog.setTitle("Popup personalizado");

                TextView text = dialog.findViewById(R.id.alert_msg);
                text.setText("¡¡Popup personalizado!! ");
                ImageView image = dialog.findViewById(R.id.alert_img);
                image.setImageResource(R.drawable.nyan);

                dialog.show();

            }
        });


    }
}
