package annabel.grocereasy;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class GrocerEasy extends Activity implements OnClickListener, OnItemClickListener {
    
    private Button b_newList;                       // to create new list
    private ListView myListView;                    // for displaying items
    private ArrayList<FoodItem> foodItems;          // list of food item objects
    private ArrayList<String> itemNames;            // list of item names
    private ArrayAdapter<String> itemAdapter;       // adapter for itemNames
    private MyDBAdapter dbAdapter;                  // adapter for database
    private Cursor dbCursor;                        // cursor!
    
    /**
     * Android magic :)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        b_newList = (Button) findViewById(R.id.b_newList);
        
        // Set up the list of items
        itemNames = new ArrayList<String>();
        foodItems = new ArrayList<FoodItem>();
        myListView = (ListView) findViewById(R.id.lv_groceryList);
        itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemNames);
        myListView.setAdapter(itemAdapter);  
        
        // Set up database
        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();
        dbCursor = dbAdapter.getAllEntries();
        startManagingCursor(dbCursor);
        updateArray();
    }
    
    private void updateArray() {
        dbCursor = dbAdapter.getAllEntries();
        foodItems.clear();
        itemNames.clear();
        if (dbCursor.moveToFirst()) {
            do {
                String name = new String(dbCursor.getString(dbAdapter.FNAME_COLUMN));
                double quantity = dbCursor.getDouble(dbAdapter.FQTY_COLUMN);
                String measurement = new String(dbCursor.getString(dbAdapter.FMEASURE_COLUMN));
                String notes = new String(dbCursor.getString(dbAdapter.FNOTES_COLUMN));
                FoodItem fi = new FoodItem(name, quantity, measurement, notes);
                foodItems.add(fi);
                itemNames.add(fi.getName());
            } while (dbCursor.moveToNext());
        }
        itemAdapter.notifyDataSetChanged();
    }
    
    /**
     * Helper function to set the click listeners for 
     * the views that need them.
     */
    private void setupListeners() {
        b_newList.setOnClickListener(this);
        myListView.setOnItemClickListener(this);
    }

    /**
     * Respond to touch events.
     */
    @Override
    public void onClick(View v) {
        // TODO
    }

    /**
     * Respond to touch events.
     */
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO
        
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }
}