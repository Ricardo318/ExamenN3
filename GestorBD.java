package com.example.examen3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class GestorBD extends SQLiteOpenHelper {

    public static abstract class TablaDatos implements BaseColumns {
        public static final String NOMBRE_TABLA = "automoviles";
        public static final String COLUMNA_CODIGO = "codigo";
        public static final String COLUMNA_MODELO = "modelo";
        public static final String COLUMNA_PATENTE = "patente";
        public static final String COLUMNA_PRECIO = "precio";

        public static final String TEXT_TYPE = "TEXT";
        public static final String COMMA_SEP = ",";
        public static final String CREAR_TABLA =
                "CREATE TABLE " + TablaDatos.NOMBRE_TABLA + " (" +
                        TablaDatos.COLUMNA_CODIGO + " INTEGER PRIMARY KEY," +
                        TablaDatos.COLUMNA_MODELO + TEXT_TYPE + COMMA_SEP +
                        TablaDatos.COLUMNA_PATENTE + TEXT_TYPE + COMMA_SEP +
                        TablaDatos.COLUMNA_PRECIO + TEXT_TYPE + ")";


        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TablaDatos.NOMBRE_TABLA;
    }

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME = "automovil.db";

    public GestorBD(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TablaDatos.CREAR_TABLA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TablaDatos.SQL_DELETE_ENTRIES);
        onCreate(db);

    }

}
