package lesh.egresatecnm.com.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
RecyclerView my_recycler_view;
    private List<String> names;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        names=this.getAllNames();
        recyclerView=(RecyclerView)findViewById(R.id.my_recycler_view);
        layoutManager=new LinearLayoutManager(this);
        layoutManager=new GridLayoutManager(this,4);
        layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        adapter=new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, int position) {
               deleteName(position);
            }
        });
        //recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    private List<String>getAllNames(){
        return new ArrayList<String>(){{
            add("Yazmin");
            add("Brenda");
            add("Martha");
            add("Karina");
            add("Yazmin");
            add("Brenda");
            add("Martha");
            add("Karina");
            add("Yazmin");
            add("Brenda");
            add("Martha");
            add("Karina");
            add("Yazmin");
            add("Brenda");
            add("Martha");
            add("Karina");
            add("Yazmin");
            add("Brenda");
            add("Martha");
            add("Karina");
            add("Yazmin");
            add("Brenda");
            add("Martha");
            add("Karina");
            add("Yazmin");
            add("Brenda");
            add("Martha");
            add("Karina");
        }};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_name:
                this.addName(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addName(int position) {
        names.add("Estela "+(++counter));
        adapter.notifyItemInserted(position);
        layoutManager.scrollToPosition(position);
    }
    private void deleteName(int position) {
        names.remove(position);
        adapter.notifyItemRemoved(position);

    }

}
