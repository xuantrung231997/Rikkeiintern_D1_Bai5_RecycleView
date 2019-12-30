package gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class CreateDatabase extends SQLiteOpenHelper {
    public static String TB_CONTACT="CONTACT";
    public static String TB_CONTACT_ID="ID";
    public static String TB_CONTACT_NAME="NAME";
    public static String TB_CONTACT_PHONE="PHONE";
    public CreateDatabase(Context context) {
        super(context, "Quan li Contact", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql=" CREATE TABLE " + TB_CONTACT + " ( " + TB_CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + TB_CONTACT_NAME + " TEXT , " + TB_CONTACT_PHONE + " TEXT ) ";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public SQLiteDatabase open(){
        return this.getReadableDatabase();
    }
}
