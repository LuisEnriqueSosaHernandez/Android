package com.example.pc.crud_bdd;

import android.provider.BaseColumns;

/**
 * Created by pc on 31/07/2017.
 */

public class Estructura_BDDO {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Estructura_BDDO() {}

    /* Inner class that defines the table contents */

    //public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "DatosPersonales";
        public static final String NOMBRE_COLUMNA1 = "Id";
        public static final String NOMBRE_COLUMNA2 = "Nombre";
         public static final String NOMBRE_COLUMNA3 = "Apellido";
    //}

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    Estructura_BDDO.NOMBRE_COLUMNA1 + " INTEGER PRIMARY KEY," +
                    Estructura_BDDO.NOMBRE_COLUMNA2 + TEXT_TYPE + COMMA_SEP +
                    Estructura_BDDO.NOMBRE_COLUMNA3 + TEXT_TYPE + " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Estructura_BDDO.TABLE_NAME;
}
