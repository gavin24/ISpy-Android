package com.ackerman.j.gavin.ispy.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ackerman.j.gavin.ispy.Config.DbConstants.DbConstants;
import com.ackerman.j.gavin.ispy.Domain.Fame;
import com.ackerman.j.gavin.ispy.Repositories.FameRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public class FameRepositoryImpl extends SQLiteOpenHelper implements FameRepository {
    public static final String TABLE_NAME = "fame";
    private SQLiteDatabase db;



    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERID = "userid";
    public static final String COLUMN_STORYID = "storyid";
    public static final String COLUMN_DISLIKES = "dislikes";
    public static final String COLUMN_LIKES = "likes";
    public static final String COLUMN_SHARES = "shares";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_USERID + " INTEGER  NOT NULL , "
            + COLUMN_STORYID + " INTEGER NOT NULL ,"
            + COLUMN_DISLIKES + " INTEGER  NOT NULL , "
            + COLUMN_LIKES + " INTERGER  NOT NULL , "
            + COLUMN_SHARES + " INTEGER  NULL );";



    public FameRepositoryImpl(Context context) {
        super(context, DbConstants.DATABASE_NAME, null, DbConstants.DATABASE_VERSION);
    }

    public FameRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Fame findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_USERID,
                        COLUMN_STORYID,
                        COLUMN_DISLIKES,
                        COLUMN_LIKES,
                        COLUMN_SHARES},

                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final Fame animal = new Fame.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_USERID)))
                    .storyId(cursor.getLong(cursor.getColumnIndex(COLUMN_STORYID)))
                    .dislikes(cursor.getInt(cursor.getColumnIndex(COLUMN_DISLIKES)))
                    .likes(cursor.getInt(cursor.getColumnIndex(COLUMN_LIKES)))
                    .shares(cursor.getInt(cursor.getColumnIndex(COLUMN_SHARES)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }



    @Override
    public Fame save(Fame entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());

        values.put(COLUMN_USERID, entity.getUserId());
        values.put(COLUMN_STORYID, entity.getStoryId());
        values.put(COLUMN_SHARES, entity.getShares());
        values.put(COLUMN_DISLIKES, entity.getDisLikes());
        values.put(COLUMN_LIKES, entity.getLikes());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Fame insertedEntity = new Fame.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public Fame update(Fame entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());

        values.put(COLUMN_USERID, entity.getUserId());
        values.put(COLUMN_STORYID, entity.getStoryId());
        values.put(COLUMN_SHARES, entity.getShares());
        values.put(COLUMN_DISLIKES, entity.getDisLikes());
        values.put(COLUMN_LIKES, entity.getLikes());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Fame delete(Fame entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Fame> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Fame> animal = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final Fame animals = new Fame.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_USERID)))
                        .storyId(cursor.getLong(cursor.getColumnIndex(COLUMN_STORYID)))
                        .dislikes(cursor.getInt(cursor.getColumnIndex(COLUMN_DISLIKES)))
                        .likes(cursor.getInt(cursor.getColumnIndex(COLUMN_LIKES)))
                        .shares(cursor.getInt(cursor.getColumnIndex(COLUMN_SHARES)))
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
