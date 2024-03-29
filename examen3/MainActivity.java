package com.example.examen3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnagregar, btnbuscar, btneliminar;
    EditText etcodigo,etmodelo,etpatente,etprecio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnagregar = (Button) findViewById(R.id.btnagregar);
        btnbuscar    = (Button) findViewById(R.id.btnbuscar);
        btneliminar = (Button) findViewById(R.id.btneliminar);

        etcodigo = (EditText) findViewById(R.id.txtcodigo);
        etmodelo = (EditText) findViewById(R.id.txtmodelo);
        etpatente = (EditText) findViewById(R.id.txtpatente);
        etprecio = (EditText) findViewById(R.id.txtprecio);

        final GestorBD gestorBD = new GestorBD(getApplicationContext());

        btnagregar.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v) {
                SQLiteDatabase db = gestorBD.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(GestorBD.TablaDatos.COLUMNA_CODIGO, etcodigo.getText().toString());


                long idRegistro = db.insert(GestorBD.TablaDatos.NOMBRE_TABLA, GestorBD.TablaDatos.COLUMNA_CODIGO, valores);
                etcodigo.setText("");
                etmodelo.setText("");
                etpatente.setText("");
                etprecio.setText("");

                Toast.makeText(getApplicationContext(), "Se Registró el Automovil con codigo: " + idRegistro, Toast.LENGTH_LONG).show();
            }
        });

        btnbuscar.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                SQLiteDatabase db = gestorBD.getReadableDatabase();
                String[] paramBusqueda = {etcodigo.getText().toString()};
                String[] salida = {GestorBD.TablaDatos.COLUMNA_CODIGO,
                                   GestorBD.TablaDatos.COLUMNA_MODELO,
                                   GestorBD.TablaDatos.COLUMNA_PATENTE};


                Cursor c = db.query(GestorBD.TablaDatos.NOMBRE_TABLA, salida,GestorBD.TablaDatos.COLUMNA_CODIGO + "=?",
                        paramBusqueda, null , null, null);

                c.moveToFirst();
                etcodigo.setText(c.getString(1));
                etmodelo.setText(c.getString(2));
                etpatente.setText(c.getString(3));
            }

        });

        btneliminar.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                SQLiteDatabase db = gestorBD.getWritableDatabase();
                String[] paramBusqueda ={etcodigo.getText().toString()};
                String seleccion =GestorBD.TablaDatos.COLUMNA_CODIGO+"=?";
                int cantidad =
                        db.delete(GestorBD.TablaDatos.NOMBRE_TABLA, seleccion,paramBusqueda);
                etcodigo.setText("");
                etmodelo.setText("");
                etpatente.setText("");
                etprecio.setText("");
                Toast.makeText(getApplicationContext(), "Se eliminó :" +
                        cantidad + " registro", Toast.LENGTH_LONG).show();
            }
        });
    }
}

