package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.e.demoaplplication.bean.PostModel;

import java.util.ArrayList;

public class FavDataBase extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "favlistdatabase";
    private static String TABLE_NAME = "favoriteTable";
    private static String KEY_NAME = "itemName";
    private static String KEY_LOGIN = "itemLogin";
    private static String KEY_IMAGE = "itemImage";


    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
        KEY_NAME + " TEXT," +
        KEY_LOGIN + " TEXT PRIMARY KEY,"
        + KEY_IMAGE + " TEXT" +
        ")";


    public FavDataBase(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void insertEmpty() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    //insert the data
    public void addFavData(PostModel model) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, model.getName());
        contentValues.put(KEY_LOGIN, model.getLogin());
        contentValues.put(KEY_IMAGE, model.getAvatarUrl());
        long insert = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        Log.d("database", String.valueOf(insert));

    }

    //read the data
    public Cursor readFavData(PostModel model) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "select * from favoriteTable WHERE itemLogin =" + model.getLogin() + "";
        return sqLiteDatabase.rawQuery(sql, null, null);
    }

    //delete data
    public void removeFavData(PostModel model) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, KEY_LOGIN + "=?", new String[]{model.getLogin()});
        //? put any one value,= put unlimited value then app is hack
    }

    //select all fav list
    public ArrayList<PostModel> getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<PostModel> arrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
         PostModel model = new PostModel();
            model.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            model.setAvatarUrl(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
            model.setLogin(cursor.getString(cursor.getColumnIndex(KEY_LOGIN)));
           arrayList.add(model);
        }
        cursor.close();
        return arrayList;
    }

    public boolean isThisLoginAvailable(String login){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{KEY_LOGIN}, KEY_LOGIN + "=?", new String[]{login}, null, null, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}