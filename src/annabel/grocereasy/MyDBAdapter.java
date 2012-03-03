package annabel.grocereasy;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class MyDBAdapter {
    private static final String DATABASE_NAME = "myDatabase.db";
    private static final String DATABASE_TABLE = "mainTable";
    private static final int DATABASE_VERSION = 1;
    
    public static final String KEY_ID = "_id";
    
    // COLUMNS
    // fname | fqty | fmeasure | fnotes
    public static final String KEY_FNAME = "fname";
    public static final int FNAME_COLUMN = 0;
    public static final String KEY_FQTY = "fqty";
    public static final int FQTY_COLUMN = 1;
    public static final String KEY_FMEASURE = "fmeasure";
    public static final int FMEASURE_COLUMN = 2;
    public static final String KEY_FNOTES = "fnotes";
    public static final int FNOTES_COLUMN = 3;
    
    private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + 
        " (" + KEY_ID + " integer primary key autoincrement, " +
               KEY_FNAME + " varchar(50), " + 
               KEY_FQTY + " numeric(4,2), " + 
               KEY_FMEASURE + " varchar(50), " + 
               KEY_FNOTES + " varchar(50));";
    
    private SQLiteDatabase db;
    private final Context context;
    private myDBHelper dbHelper;
    
    public MyDBAdapter(Context _context) {
        context = _context;
        dbHelper = new myDBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public MyDBAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    
    public void close() {
        db.close();
    }
    
    public long insertItem(FoodItem item) {
        ContentValues nv = new ContentValues();
        nv.put(KEY_FNAME, item.getName());
        nv.put(KEY_FQTY, item.getQuantity());
        nv.put(KEY_FMEASURE, item.getMeasurement());
        nv.put(KEY_FNOTES, item.getNotes());
        return db.insert(DATABASE_TABLE, null, nv);
    }
    
    public boolean removeItem(FoodItem item) {
        String where = new String(KEY_FNAME + " like \'" + item.getName() + "\'");
        return db.delete(DATABASE_TABLE, where, null) > 0;
    }
    
    public boolean removeItem(int position) {
        String where = new String(KEY_ID + " = " + position);
        return db.delete(DATABASE_TABLE, where, null) > 0;
    }
    
    public Cursor getAllEntries() {
        String[] cols = {KEY_FNAME, KEY_FQTY, KEY_FMEASURE, KEY_FNOTES };
        return db.query(DATABASE_TABLE, cols, null, null, null, null, null);
    }
    
    public FoodItem getItem(String fname) {
        String[] cols = {KEY_FQTY, KEY_FMEASURE, KEY_FNOTES };
        String where = new String(KEY_FNAME + " like \'" + fname + "\'");
        Cursor c = db.query(DATABASE_TABLE, cols, where, null, null, null, null);
        if (c.moveToFirst()) {
            return new FoodItem(c.getString(FNAME_COLUMN), c.getDouble(FQTY_COLUMN),
                    c.getString(FMEASURE_COLUMN), c.getString(FNOTES_COLUMN));
        }
        return null;
    }
    
    public boolean removeAllEntries() {
        return db.delete(DATABASE_TABLE, null, null) > 0;
    }
    
    
    public boolean updateEntry(String fname) {
        // TODO
        return true;
    }
    
    private static class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        
        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DATABASE_CREATE);
        }
        
        @Override
        public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
            Log.w("TaskDBAdapter", "Upgrading from version " + _oldVersion + " to " + _newVersion + ", which will destroy all old data");
            _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(_db);
        }
    }
    

}
