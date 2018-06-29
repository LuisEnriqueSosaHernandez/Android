package com.example.lesh.board.Activities;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lesh.board.Adapters.NoteAdapter;
import com.example.lesh.board.Models.Board;
import com.example.lesh.board.Models.Note;
import com.example.lesh.board.R;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmResults;

public class NoteActivity extends AppCompatActivity implements RealmChangeListener<Board>{

    private ListView listView;
    private FloatingActionButton fab;

    private NoteAdapter adapter;
    private RealmList<Note> notes;
    private Realm realm;

    private int boardId;
    private Board board;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        realm=Realm.getDefaultInstance();
        if(getIntent().getExtras()!=null){
            boardId=getIntent().getExtras().getInt("id");
        }

        board =realm.where(Board.class).equalTo("id",boardId).findFirst();
        board.addChangeListener(this);
        notes=board.getNotes();
        notes.addChangeListener(new RealmChangeListener<RealmList<Note>>() {
            @Override
            public void onChange(RealmList<Note> notes) {
                adapter.notifyDataSetChanged();
            }
        });
        this.setTitle(board.getTitle());

        fab=findViewById(R.id.fabAddNote);
        listView=findViewById(R.id.listViewNote);
        adapter=new NoteAdapter(this,notes,R.layout.list_view_note_item);
        listView.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertForCreatingNote("Add new note","Type a new note for: "+board.getTitle()+".");
            }
        });
        registerForContextMenu(listView);
    }
    private void createNewNote(String note) {
        realm.beginTransaction();
        Note _note=new Note(note);
        realm.copyToRealm(_note);
        board.getNotes().add(_note);
        realm.commitTransaction();
    }

    private void editNote(String newNote,Note note){
        realm.beginTransaction();
        note.setDescription(newNote);
        realm.copyToRealmOrUpdate(note);
        realm.commitTransaction();
    }

    private void delete_note(Note note){
        realm.beginTransaction();
        note.deleteFromRealm();
        realm.commitTransaction();
    }

    private void deleteAll(){
        realm.beginTransaction();
        board.getNotes().deleteAllFromRealm();
        realm.commitTransaction();
    }

    private void showAlertForCreatingNote(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (!TextUtils.isEmpty(title)) builder.setTitle(title);
        if (!TextUtils.isEmpty(message)) builder.setMessage(message);

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_note, null);
        builder.setView(viewInflated);

        final EditText input = viewInflated.findViewById(R.id.editTextNewNote);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String note = input.getText().toString().trim();
                if (note.length() > 0) {
                    createNewNote(note);
                } else {
                    Toast.makeText(getApplicationContext(), "The note can't be a null ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAlertForEditingNote(String title, String message,final Note _note) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (!TextUtils.isEmpty(title)) builder.setTitle(title);
        if (!TextUtils.isEmpty(message)) builder.setMessage(message);

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_note, null);
        builder.setView(viewInflated);

        final EditText input = viewInflated.findViewById(R.id.editTextNewNote);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String note = input.getText().toString().trim();
               if(note.length()==0){
                   Toast.makeText(getApplicationContext(), "The description is required to edit the current Note", Toast.LENGTH_SHORT).show();
               }else if(note.equals(_note.getDescription())){
                   Toast.makeText(getApplicationContext(), "The description is the same than it was before", Toast.LENGTH_SHORT).show();
               }else{
                   editNote(note, _note);
               }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                deleteAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        getMenuInflater().inflate(R.menu.context_menu_note_activity, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete_note:
               delete_note(notes.get(info.position));
                return true;
            case R.id.edit_note:
                showAlertForEditingNote("Edit Note", "Change the description of the note", notes.get(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    @Override
    public void onChange(Board board) {
        adapter.notifyDataSetChanged();
    }
}
