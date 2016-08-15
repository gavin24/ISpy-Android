package com.ackerman.j.gavin.ispy.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ackerman.j.gavin.ispy.Config.DbConstants.DbConstants;
import com.ackerman.j.gavin.ispy.Domain.Story;
import com.ackerman.j.gavin.ispy.Repositories.StoryRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public class StoryRepositoryImpl extends SQLiteOpenHelper implements StoryRepository {
    public static final String TABLE_NAME = "story";
    private SQLiteDatabase db;



    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TAG = "tag";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_USERID = "userid";
    public static final String COLUMN_IMAGEID = "imageid";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT  NOT NULL , "
            + COLUMN_TAG + " TEXT NOT NULL ,"
            + COLUMN_TEXT + " TEXT  NULL ,"
            + COLUMN_USERID + " INTEGER NULL , "
            + COLUMN_IMAGEID + " INTEGER  NULL );";



    public StoryRepositoryImpl(Context context) {
        super(context, DbConstants.DATABASE_NAME, null, DbConstants.DATABASE_VERSION);
    }

    public StoryRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Story findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_TAG,
                        COLUMN_TEXT,
                        COLUMN_USERID,
                        COLUMN_IMAGEID},

                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final Story animal = new Story.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .tag(cursor.getString(cursor.getColumnIndex(COLUMN_TAG)))
                    .text(cursor.getString(cursor.getColumnIndex(COLUMN_TEXT)))
                    .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_USERID)))
                    .image(cursor.getLong(cursor.getColumnIndex(COLUMN_IMAGEID)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }



    @Override
    public Story save(Story entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());

        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_TAG, entity.getTag());
        values.put(COLUMN_TEXT, entity.getText());
        values.put(COLUMN_USERID, entity.getuserId());
        values.put(COLUMN_IMAGEID, entity.getImage());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Story insertedEntity = new Story.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public Story update(Story entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());

        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_TAG, entity.getTag());
        values.put(COLUMN_TEXT, entity.getText());
        values.put(COLUMN_USERID, entity.getuserId());
        values.put(COLUMN_IMAGEID, entity.getImage());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Story delete(Story entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Story> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Story> animal = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final Story animals = new Story.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .tag(cursor.getString(cursor.getColumnIndex(COLUMN_TAG)))
                        .text(cursor.getString(cursor.getColumnIndex(COLUMN_TEXT)))
                        .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_USERID)))
                        .image(cursor.getLong(cursor.getColumnIndex(COLUMN_IMAGEID)))
                        .build();
                animal.add(animals);
            } while (cursor.moveToNext());
        }
        return animal;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
