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

    Button btnRegistrar, btnBuscar, btneliminar;
    EditText etcodigo,etmodelo,etpatente,etprecio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnRegistrar = (Button) findViewById(R.id.btnagregar);
        btnBuscar    = (Button) findViewById(R.id.btnbuscar);
        btneliminar = (Button) findViewById(R.id.btneliminar);

        etcodigo = (EditText) findViewById(R.id.txtcodigo);
        etmodelo = (EditText) findViewById(R.id.txtmodelo);
        etpatente = (EditText) findViewById(R.id.txtpatente);
        etprecio = (EditText) findViewById(R.id.txtprecio);

        final GestorBD gestorBD = new GestorBD(getApplicationContext());

        btnRegistrar.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                SQLiteDatabase db = gestorBD.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(GestorBD.TablaDatos.COLUMNA_CODIGO, etcodigo.getText().toString());
                valores.put(GestorBD.TablaDatos.COLUMNA_CODIGO, etcodigo.getText().toString());

                long idRegistro = db.insert(GestorBD.TablaDatos.NOMBRE_TABLA,GestorBD.TablaDatos.COLUMNA_CODIGO,valores);

                //lipmpiar el formulario
                etcodigo.setText("");
                etmodelo.setText("");
                etpatente.setText("");
                etprecio.setText("");

                //mostrar el mensaje de confirmacion

                Toast.makeText(getApplicationContext(), "Se Registró el Automovil con codigo: " + idRegistro, Toast.LENGTH_LONG).show();
            }


        });


    }
}