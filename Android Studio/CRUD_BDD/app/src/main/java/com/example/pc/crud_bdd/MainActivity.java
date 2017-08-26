package com.example.pc.crud_bdd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    Button  botonInsertar,botonActualizar,botonBorrar,botonBuscar;
    EditText textoId,textoNombre,textoApellidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        botonInsertar=(Button)findViewById(R.id.insertar);
        botonActualizar=(Button)findViewById(R.id.actualizar);
        botonBorrar=(Button)findViewById(R.id.borrar);
        botonBuscar=(Button)findViewById(R.id.buscar);

        textoId=(EditText)findViewById(R.id.id);
        textoNombre=(EditText)findViewById(R.id.nombre);
        textoApellidos=(EditText)findViewById(R.id.apellidos);

        final BBDO_Helper helper = new BBDO_Helper(this);

        botonInsertar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();

// Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(Estructura_BDDO.NOMBRE_COLUMNA1, textoId.getText().toString());
                values.put(Estructura_BDDO.NOMBRE_COLUMNA2, textoNombre.getText().toString());
                values.put(Estructura_BDDO.NOMBRE_COLUMNA3, textoApellidos.getText().toString());
// Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(Estructura_BDDO.TABLE_NAME, null, values);
                Toast.makeText(getApplicationContext(),"Se guardo el registro: "+newRowId,Toast.LENGTH_SHORT).show();

                textoId.setText("");
                textoNombre.setText("");
                textoApellidos.setText("");
            }
        });
        botonActualizar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();

// New value for one column
                ContentValues values = new ContentValues();
                values.put(Estructura_BDDO.NOMBRE_COLUMNA2, textoNombre.getText().toString());
                values.put(Estructura_BDDO.NOMBRE_COLUMNA3, textoApellidos.getText().toString());
// Which row to update, based on the title
                String selection = Estructura_BDDO.NOMBRE_COLUMNA1 + " LIKE ?";
                String[] selectionArgs = { textoId.getText().toString() };

                int count = db.update(
                        Estructura_BDDO.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                Toast.makeText(getApplicationContext(),"Se actualizo el registro: "+textoId.getText().toString(),Toast.LENGTH_SHORT).show();
                textoNombre.setText("");
                textoApellidos.setText("");
            }

        });
        botonBorrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();
                // Define 'where' part of query.
                String selection = Estructura_BDDO.NOMBRE_COLUMNA1 + " LIKE ?";
// Specify arguments in placeholder order.
                String[] selectionArgs = { textoId.getText().toString() };
// Issue SQL statement.
                db.delete(Estructura_BDDO.TABLE_NAME, selection, selectionArgs);
                Toast.makeText(getApplicationContext(),"Se borro el registro: "+textoId.getText().toString(),Toast.LENGTH_SHORT).show();
                textoId.setText("");
                textoNombre.setText("");
                textoApellidos.setText("");
            }
        });
        botonBuscar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
                String[] projection = {
                       // FeedEntry._ID,
                        Estructura_BDDO.NOMBRE_COLUMNA2,
                        Estructura_BDDO.NOMBRE_COLUMNA3
                };

// Filter results WHERE "title" = 'My Title'
                String selection = Estructura_BDDO.NOMBRE_COLUMNA1 + " = ?";
                String[] selectionArgs = { textoId.getText().toString() };

// How you want the results sorted in the resulting Cursor
                /*String sortOrder =
                        FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";*/
                try {
                    Cursor c = db.query(
                            Estructura_BDDO.TABLE_NAME,                     // The table to query
                            projection,                               // The columns to return
                            selection,                                // The columns for the WHERE clause
                            selectionArgs,                            // The values for the WHERE clause
                            null,                                     // don't group the rows
                            null,                                     // don't filter by row groups
                            null                                 // The sort order
                    );

                    c.moveToFirst();

                    textoNombre.setText(c.getString(0));
                    textoApellidos.setText(c.getString(1));
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"No se encontro el registro "+textoId.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
