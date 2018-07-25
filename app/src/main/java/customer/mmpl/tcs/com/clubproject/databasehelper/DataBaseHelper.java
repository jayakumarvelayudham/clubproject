package customer.mmpl.tcs.com.clubproject.databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import customer.mmpl.tcs.com.clubproject.modul.Alluser_Module;

/**
 * Created by BeAsT on 18-Jul-18.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DataBaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserManager.db1";

    // User table name
    private static final String TABLE_USER = "user_member";

    // User Table Columns names
    private static final String USER_ID = "user_id";
    private static final String F_NAME = "f_name";
    private static final String PASSWOD = "password";
    private static final String EMAIL_ID = "email_id";
    private static final String PHONE_NO = "Phone_no";
    private static final String IMAGE_URL = "image_url1";
    private static final String ADDRESS = "address";
    private static final String PROFESSION = "profession1";
    private static final String GENDER = "gender1";
    private static final String USER_DOB = "user_dob3";
    private static final String BLOOD_GROUP = "blood_group1";
    private static final String ROLE_ID = "role_id2";
    private static final String POSITION = "position1";
    private static final String UPDATE_DATE_TIME = "update_date_time1";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + USER_ID + " TEXT,"
            + F_NAME + " TEXT,"
            + PASSWOD + " TEXT,"
            + EMAIL_ID+ " TEXT,"
            + PHONE_NO+ " TEXT,"
            + IMAGE_URL+ " TEXT,"
            + ADDRESS+ " TEXT,"
            + PROFESSION+ " TEXT,"
            + GENDER+ " TEXT,"
            + USER_DOB+ " TEXT,"
            + BLOOD_GROUP+ " TEXT,"
            + ROLE_ID+ " TEXT,"
            + POSITION+ " TEXT,"
            + UPDATE_DATE_TIME + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser_Module(Alluser_Module alluser_module) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, alluser_module.getUser_id());
        values.put(F_NAME, alluser_module.getF_name());
        values.put(PASSWOD, alluser_module.getPassword());
        values.put(EMAIL_ID, alluser_module.getEmail_id());
        values.put(PHONE_NO, alluser_module.getPhone_no());
        values.put(IMAGE_URL, alluser_module.getImage_url());
        values.put(ADDRESS, alluser_module.getAddress());
        values.put(PROFESSION, alluser_module.getProfession());
        values.put(GENDER, alluser_module.getGender());
        values.put(USER_DOB, alluser_module.getUser_dob());
        values.put(BLOOD_GROUP, alluser_module.getBlood_group());
        values.put(ROLE_ID, alluser_module.getRole_id());
        values.put(POSITION, alluser_module.getPosition());
        values.put(UPDATE_DATE_TIME, alluser_module.getUpdate_date_time());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public List<Alluser_Module> getAllUser() {

        List<Alluser_Module> modules = new ArrayList<>();
        String[] columns = {
                USER_ID,
                F_NAME,
                PASSWOD,
                EMAIL_ID,
                PHONE_NO,
                IMAGE_URL,
                ADDRESS,
                PROFESSION,
                GENDER,
                USER_DOB,
                BLOOD_GROUP,
                ROLE_ID,
                POSITION,
                UPDATE_DATE_TIME
        };

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER,
                columns,
                null,
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst()) {
            do {
                Alluser_Module alluser_module = new Alluser_Module();
                alluser_module.setUser_id((cursor.getString(cursor.getColumnIndex(USER_ID))));
                alluser_module.setF_name(cursor.getString(cursor.getColumnIndex(F_NAME)));
                alluser_module.setPassword(cursor.getInt(cursor.getColumnIndex(PASSWOD)));
                alluser_module.setEmail_id(cursor.getString(cursor.getColumnIndex(EMAIL_ID)));
                alluser_module.setPhone_no(cursor.getInt(cursor.getColumnIndex(PHONE_NO)));
                alluser_module.setImage_url(cursor.getString(cursor.getColumnIndex(IMAGE_URL)));
                alluser_module.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
                alluser_module.setProfession(cursor.getString(cursor.getColumnIndex(PROFESSION)));
                alluser_module.setGender(cursor.getString(cursor.getColumnIndex(GENDER)));
                alluser_module.setUser_dob(cursor.getString(cursor.getColumnIndex(USER_DOB)));
                alluser_module.setBlood_group(cursor.getString(cursor.getColumnIndex(BLOOD_GROUP)));
                alluser_module.setRole_id(cursor.getString(cursor.getColumnIndex(ROLE_ID)));
                alluser_module.setPosition(cursor.getString(cursor.getColumnIndex(POSITION)));
//                alluser_module.setUpdate_date_time(cursor.getString(cursor.getColumnIndex(UPDATE_DATE_TIME)));
                modules.add(alluser_module);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return modules;
    }

    public Alluser_Module getUser(String id) {

        Alluser_Module alluser_module = new Alluser_Module();
        String[] columns = {
                USER_ID,
                F_NAME,
                PASSWOD,
                EMAIL_ID,
                PHONE_NO,
                IMAGE_URL,
                ADDRESS,
                PROFESSION,
                GENDER,
                USER_DOB,
                BLOOD_GROUP,
                ROLE_ID,
                POSITION,
                UPDATE_DATE_TIME
        };

        String selection = USER_ID + " = ?";

        String[] selectionArgs = {id};


        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor.moveToFirst()) {
            do {

                alluser_module.setUser_id((cursor.getString(cursor.getColumnIndex(USER_ID))));
                alluser_module.setF_name(cursor.getString(cursor.getColumnIndex(F_NAME)));
                alluser_module.setPassword(cursor.getInt(cursor.getColumnIndex(PASSWOD)));
                alluser_module.setEmail_id(cursor.getString(cursor.getColumnIndex(EMAIL_ID)));
                alluser_module.setPhone_no(cursor.getInt(cursor.getColumnIndex(PHONE_NO)));
                alluser_module.setImage_url(cursor.getString(cursor.getColumnIndex(IMAGE_URL)));
                alluser_module.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
                alluser_module.setProfession(cursor.getString(cursor.getColumnIndex(PROFESSION)));
                alluser_module.setGender(cursor.getString(cursor.getColumnIndex(GENDER)));
                alluser_module.setUser_dob(cursor.getString(cursor.getColumnIndex(USER_DOB)));
                alluser_module.setBlood_group(cursor.getString(cursor.getColumnIndex(BLOOD_GROUP)));
                alluser_module.setRole_id(cursor.getString(cursor.getColumnIndex(ROLE_ID)));
                alluser_module.setPosition(cursor.getString(cursor.getColumnIndex(POSITION)));
//                alluser_module.setUpdate_date_time(cursor.getString(cursor.getColumnIndex(UPDATE_DATE_TIME)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return alluser_module;
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        if (!db.isOpen())
            db = this.getWritableDatabase();
        db.delete(TABLE_USER, null, null);
        db.close();
    }
}
