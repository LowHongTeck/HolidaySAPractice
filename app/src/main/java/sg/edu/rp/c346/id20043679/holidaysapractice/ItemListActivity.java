package sg.edu.rp.c346.id20043679.holidaysapractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ItemListActivity extends AppCompatActivity {

    ListView lvItems;
    Spinner spinner;
    EditText etNewItem;
    Button btnAdd, btnFilter;

    DatePicker dp;
    ArrayList<String> alItems;


    int itemClicked;
    ArrayAdapter aaItems;

    int eMonth = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        lvItems = findViewById(R.id.listViewItems);
        spinner = findViewById(R.id.spinner);
        etNewItem = findViewById(R.id.editTextItem);
        btnAdd = findViewById(R.id.buttonAdditem);
        btnFilter = findViewById(R.id.buttonFilteritem);
        dp = findViewById(R.id.datePicker);

        alItems = new ArrayList<String>();

        aaItems = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alItems);
        lvItems.setAdapter(aaItems);

        registerForContextMenu(lvItems);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etNewItem.getText().toString();
                String aItem = "Expires " + dp.getYear() + "-" + dp.getMonth()
                        + "-" + dp.getDayOfMonth() + " " + item;
                alItems.add(aItem);
                aaItems.notifyDataSetChanged();
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        eMonth = 1;
                        break;
                    case 1:
                        eMonth = 3;
                        break;
                    case 2:
                        eMonth = 6;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemClicked = Integer.parseInt(aaItems.getItem(position).toString());
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,0,0,"Edit");
        menu.add(0,1,1,"Delete");

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==0){
            String nItem = etNewItem.getText().toString();
            alItems.set(itemClicked, "Expires " + dp.getYear() + "-" + dp.getMonth()
                    + "-" + dp.getDayOfMonth() + " " + nItem);

            aaItems.notifyDataSetChanged();
        }

        return super.onContextItemSelected(item);
    }
}