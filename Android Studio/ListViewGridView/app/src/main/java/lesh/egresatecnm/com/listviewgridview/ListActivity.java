package lesh.egresatecnm.com.listviewgridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listView);
        names=new ArrayList<String>();
        names.add("Alejandro");
        names.add("Fernando");
        names.add("Yazmin");
        names.add("Andrea");
        names.add("Cryderman");
        names.add("Coronel");
        names.add("Yazmin");
        names.add("Andrea");
        names.add("Cryderman");
        names.add("Coronel");
        names.add("Yazmin");
        names.add("Andrea");
        names.add("Cryderman");
        names.add("Coronel");
        names.add("Yazmin");
        names.add("Andrea");
        names.add("Cryderman");
        names.add("Coronel");
        names.add("Yazmin");
        names.add("Andrea");
        names.add("Cryderman");
        names.add("Coronel");
        names.add("Yazmin");
        names.add("Andrea");
        names.add("Cryderman");
        names.add("Coronel");
        names.add("Yazmin");
        names.add("Andrea");
        names.add("Cryderman");
        names.add("Coronel");
        names.add("Yazmin");
        names.add("Andrea");
        names.add("Cryderman");
        names.add("Coronel");
        names.add("Yazmin");
        names.add("Andrea");
        names.add("Cryderman");
        names.add("Coronel");

        //  Forma visual en quese mostrarann los datos
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        //enlazamos el adaptador con nuestrolist view
        //listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListActivity.this, "Clicked "+names.get(position), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //Enlazamos con nuestro adaptador personalizado
        MyAdapter myAdapter=new MyAdapter(this,R.layout.listview,names);
        listView.setAdapter(myAdapter);
    }
}
