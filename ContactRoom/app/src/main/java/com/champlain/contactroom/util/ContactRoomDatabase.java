package com.champlain.contactroom.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.champlain.contactroom.data.ContactDao;
import com.champlain.contactroom.model.Contact;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactRoomDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;

    public abstract  ContactDao contactDao();

    private static volatile ContactRoomDatabase INSTANCE;

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            databaseWriteExecutor.execute(()->{
                ContactDao contactDao= INSTANCE.contactDao();
                contactDao.deleteAll();

                Contact contact = new Contact("John","Teacher");
                contactDao.insert(contact);

                contact  = new Contact("Bond","Spy");
                contactDao.insert(contact);
            });
        }
    };

    public static final ExecutorService databaseWriteExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ContactRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ContactRoomDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ContactRoomDatabase.class,"contact_database")
                        .addCallback(sRoomDatabaseCallback)
                        .build();
            }
        }
        return INSTANCE;
    }
}
