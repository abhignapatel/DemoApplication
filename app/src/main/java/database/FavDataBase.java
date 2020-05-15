package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


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

    public FavDataBase( Context context)
    {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
      void insertEmpty(){
          SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
      }
      //insert the data
   public  void addFavData(String itemName,String itemLogin,String itemImage){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME,itemName);
        contentValues.put(KEY_LOGIN,itemLogin);
        contentValues.put(KEY_IMAGE,itemImage);
       long insert = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
       Log.d("database" , String.valueOf(insert));

   }
      //read the data
    public Cursor readFavData(String itemLogin){
        SQLiteDatabase sqLiteDatabase= this.getReadableDatabase();
        String sql = "select * from favoriteTable WHERE itemLogin =?" +itemLogin+"";
        return sqLiteDatabase.rawQuery(sql,null,null);
    }
    public void removeFavData(String itemlogin){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, "itemLogin =?", new String[]{itemlogin});

    }
}