package com.example.lesh.board.App;

import android.app.Application;

import com.example.lesh.board.Models.Board;
import com.example.lesh.board.Models.Note;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApplication extends Application {
    public static AtomicInteger BoardId=new AtomicInteger();
    public static AtomicInteger NoteId=new AtomicInteger();

    @Override
    public void onCreate() {
        super.onCreate();
        setUpRealmConfig();
        Realm realm=Realm.getDefaultInstance();
        BoardId=getIdByTable(realm, Board.class);
        NoteId=getIdByTable(realm, Note.class);
        realm.close();
    }

    private void setUpRealmConfig(){
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass){
        RealmResults<T> results=realm.where(anyClass).findAll();
        return (results.size()>0)?new AtomicInteger(results.max("id").intValue()):new AtomicInteger();
    }
}
