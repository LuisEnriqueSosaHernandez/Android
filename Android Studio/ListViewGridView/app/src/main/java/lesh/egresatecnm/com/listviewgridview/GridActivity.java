package lesh.egresatecnm.com.listviewgridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {
    private GridView gridView;
    private List<String> names;
    private int counter=0;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        gridView=(GridView) findViewById(R.id.gridView);
        names=new ArrayList<String>();
        names.add("Brenda");
        names.add("Avila");
        names.add("Yazmin");
        names.add("Andrea");


        //  Forma visual en quese mostrarann los datos
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        //enlazamos el adaptador con nuestrolist view
        //listView.setAdapter(adapter);

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridActivity.this, "Clicked "+names.get(position), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //Enlazamos con nuestro adaptador personalizado
        myAdapter=new MyAdapter(this,R.layout.griditem,names);
        gridView.setAdapter(myAdapter);
        registerForContextMenu(gridView);
    }
//Inflamos el layout del menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.actionbarmenu,menu);
        return true;
    }
    //Manejamos eventos click en el menu de opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                //Anidando nuevos datos en la vista y actualizando el adaptador
                this.names.add("Added n.- "+(++counter));
                this.myAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //inflamos layout del context menu

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle(this.names.get(info.position));
        inflater.inflate(R.menu.context_menu,menu);

    }
//manejamos eventos click en el context menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete_item:
               // Booramos item clickeado
                this.names.remove(info.position);
                this.myAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
