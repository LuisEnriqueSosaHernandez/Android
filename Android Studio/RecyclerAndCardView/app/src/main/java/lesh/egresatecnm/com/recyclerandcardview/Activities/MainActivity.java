package lesh.egresatecnm.com.recyclerandcardview.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import lesh.egresatecnm.com.recyclerandcardview.Modelos.Movie;
import lesh.egresatecnm.com.recyclerandcardview.Adaptadores.MyAdapter;
import lesh.egresatecnm.com.recyclerandcardview.R;

public class MainActivity extends AppCompatActivity {
RecyclerView my_recycler_view;
    private List<Movie> movies;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies=this.getAllMovies();
        recyclerView=(RecyclerView)findViewById(R.id.my_recycler_view);
        layoutManager=new LinearLayoutManager(this);
       // layoutManager=new GridLayoutManager(this,4);
        //layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        adapter=new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {
               deleteMovie(position);
            }
        });
        //recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    private List<Movie>getAllMovies(){
        return new ArrayList<Movie>(){{
            add(new Movie("Uvas",R.drawable.uvas));
            add(new Movie("Star",R.drawable.star));
            add(new Movie("Peli",R.drawable.de_pelicula));
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
                this.addMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addMovie(int position) {
        movies.add(position,new Movie("Hola"+(++counter),R.drawable.uvas));
        adapter.notifyItemInserted(position);
        layoutManager.scrollToPosition(position);
    }
    private void deleteMovie(int position) {
        movies.remove(position);
        adapter.notifyItemRemoved(position);

    }

}
