package annabel.grocereasy;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class GrocerEasy extends Activity implements OnClickListener, OnItemClickListener {
    
    private Button b_newItem;                       // to create new list
    private ListView myListView;                    // for displaying items
    private ArrayList<FoodItem> foodList;          // list of food item objects
    private ArrayList<String> foodStringList;          // string to display on listview
    private ArrayAdapter<String> foodStringAdapter;       // adapter for itemNames
    private MyDBAdapter dbAdapter;                  // adapter for database
    private Cursor dbCursor;                        // cursor!
    private Menu optionsMenu;
    
    /**
     * Android magic :)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        b_newItem = (Button) findViewById(R.id.b_newList);
        b_newItem.setOnClickListener(this);
        
        // Set up the list of items
        foodStringList = new ArrayList<String>();
        foodList = new ArrayList<FoodItem>();
        myListView = (ListView) findViewById(R.id.lv_groceryList);
        foodStringAdapter = new ArrayAdapter<String>(this, R.layout.main_list_view, R.id.tv_itemDisplayName, foodStringList);
        myListView.setAdapter(foodStringAdapter); 
        myListView.setOnItemClickListener(this);        
        
        // Set up database
        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();
        dbAdapter.removeAllEntries();
        dbCursor = dbAdapter.getAllEntries();
        startManagingCursor(dbCursor);
        updateArray();
    }
    
    private void updateArray() {
        dbCursor = dbAdapter.getAllEntries();
        foodList.clear();
        foodStringList.clear();
        if (dbCursor.moveToFirst()) {
            do {
                String name = new String(dbCursor.getString(MyDBAdapter.FNAME_COLUMN));
                double quantity = dbCursor.getDouble(MyDBAdapter.FQTY_COLUMN);
                String measurement = new String(dbCursor.getString(MyDBAdapter.FMEASURE_COLUMN));
                String notes = new String(dbCursor.getString(MyDBAdapter.FNOTES_COLUMN));
                FoodItem fi = new FoodItem(name, quantity, measurement, notes);
                foodList.add(fi);
                foodStringList.add(fi.getDisplayString());
            } while (dbCursor.moveToNext());
        }
        foodStringAdapter.notifyDataSetChanged();
    }

    /**
     * Respond to touch events.
     */
    @Override
    public void onClick(View v) {
        if ( ((Button)v).equals(b_newItem)) {
            FoodItem fi = new FoodItem();
            addNewItem(fi);
        }
    }
    
    private void addNewItem(FoodItem fi) {
        foodList.add(fi);
        foodStringList.add(fi.getName());
        dbAdapter.insertItem(fi);
        updateArray();
    }

    /**
     * Respond to touch events.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        dbAdapter.removeItem(position);
        updateArray();
        
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_options_menu, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int menuItemID = item.getItemId();
        if (menuItemID == R.id.menu_add) {
            FoodItem fi = new FoodItem();
            addNewItem(fi);
            return true;
        }
        else if (menuItemID == R.id.menu_clear) {
            dbAdapter.removeAllEntries();
            updateArray();
            return true;
        }
        return false;
    }
}