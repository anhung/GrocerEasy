package annabel.grocereasy;

import java.util.ArrayList;

import android.app.Activity;
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
    private TextView tv_logo;                       // to display app name
    private ListView myListView;                    // for displaying the grocery lists
    private ArrayList<String> groceryListNames;     // list of grocery list names
    private ArrayAdapter<String> glAdapter;         // adapter for groceryListNames
    
    /**
     * Android magic :)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setupGroceryLists();
        setupViews();
        setupListeners();
    }
    
    /**
     * Helper function to load in the views from the layout, and
     * set up the grocery list names array and its adapter.
     */
    private void setupViews() {
        b_newList = (Button) findViewById(R.id.b_newList);
        tv_logo = (TextView) findViewById(R.id.tv_logo);
        myListView = (ListView) findViewById(R.id.lv_groceryListList);
        glAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, groceryListNames);
        myListView.setAdapter(glAdapter);         
    }
    
    /**
     * Helper function load in grocery list names.
     * Currently loads in food categories to test the listview.
     * Will change to actually load in grocery list names.
     */
    private void setupGroceryLists() {
        groceryListNames = new ArrayList<String>();
        String[] categories = getResources().getStringArray(R.array.categories);
        for (int i = 0; i < categories.length; i++) {
            groceryListNames.add(new String(categories[i]));
        }
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
}