package itver.donapps.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import itver.donapps.R;
import itver.donapps.models.Cities;
import itver.donapps.models.ListDonorsAdapter;
import itver.donapps.models.User;
import itver.donapps.utils.Utils;

public class ListDonorsFoundActivity extends AppCompatActivity {


    //FIREBASE
    private DatabaseReference tableUser;

    //UI ELEMENTS
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ListDonorsAdapter adapter;
    private List<User> donorsFound;
    private TextView txtNotFoundDonors;

    //PARAMETERS
    private String bloodGroup;
    private int cityID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_donors_found);

        tableUser = FirebaseDatabase.getInstance().getReference("users");
        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        foundDonorsFromDatabase();
    }

    private void initComponents() {
        Bundle info = getIntent().getExtras();
        bloodGroup = info.getString("bloodGroup");
        cityID = info.getInt("city");
        toolbar = (Toolbar) findViewById(R.id.toolbarFoundDonors);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lista de Donadores: " + bloodGroup);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);
        donorsFound = new ArrayList<>();
        txtNotFoundDonors = (TextView) findViewById(R.id.txtNotFoundDonors);
    }

    private void foundDonorsFromDatabase() {

        //QUERY
         Query query = tableUser.orderByChild("city").
                 equalTo(cityID);
         query.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 donorsFound.clear();
                 for (DataSnapshot data: dataSnapshot.getChildren() ) {
                     User user = data.getValue(User.class);
                     try{

                         if (user.getBloodGroup().equals(bloodGroup)
                                 && !user.getEmail().equals(Utils.getCurrentUser().getEmail())
                                 && !user.getCategoria().equals("Receptor")) {
                             donorsFound.add(user);
                         }
                         if (bloodGroup.equals("O+") && !user.getCategoria().equals("Receptor")) {
                             if (user.getBloodGroup().equals("O-")) {
                                 donorsFound.add(user);
                             }
                         }
                         if (bloodGroup.equals("A+") && !user.getCategoria().equals("Receptor")) {
                             if (user.getBloodGroup().equals("O+")) {
                                 donorsFound.add(user);
                             }
                             if (user.getBloodGroup().equals("O-")) {
                                 donorsFound.add(user);
                             }
                             if (user.getBloodGroup().equals("A-")) {
                                 donorsFound.add(user);
                             }
                         }
                         if (bloodGroup.equals("A-") && !user.getCategoria().equals("Receptor")) {
                             if (user.getBloodGroup().equals("O-")) {
                                 donorsFound.add(user);
                             }
                         }
                         if (bloodGroup.equals("B+") && !user.getCategoria().equals("Receptor")) {
                             if (user.getBloodGroup().equals("O+")) {
                                 donorsFound.add(user);
                             }
                             if (user.getBloodGroup().equals("O-")) {
                                 donorsFound.add(user);
                             }
                             if (user.getBloodGroup().equals("B-")) {
                                 donorsFound.add(user);
                             }
                         }
                         if (bloodGroup.equals("B-") && !user.getCategoria().equals("Receptor")) {
                             if (user.getBloodGroup().equals("O-")) {
                                 donorsFound.add(user);
                             }
                         }
                         if (bloodGroup.equals("AB+") && !user.getCategoria().equals("Receptor")) {
                             if (!user.getBloodGroup().equals("AB+")) {
                                 donorsFound.add(user);
                             }
                         }
                         if (bloodGroup.equals("AB-") && !user.getCategoria().equals("Receptor")) {
                             if (user.getBloodGroup().equals("O-")) {
                                 donorsFound.add(user);
                             }
                             if (user.getBloodGroup().equals("A-")) {
                                 donorsFound.add(user);
                             }
                             if (user.getBloodGroup().equals("B-")) {
                                 donorsFound.add(user);
                             }
                         }
                     }catch (Exception e){

                     }
                     }

                     adapter = new ListDonorsAdapter(ListDonorsFoundActivity.this, donorsFound);

                     recyclerView.setAdapter(adapter);
                     adapter.notifyDataSetChanged();
                     if (donorsFound.isEmpty()) {
                         txtNotFoundDonors.setVisibility(View.VISIBLE);
                     }


             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });
    }

}
