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
import java.util.Collections;

public class ItemListActivity extends AppCompatActivity {

    ListView lvItems;
    Spinner spinner;
    EditText etNewItem;
    Button btnAdd, btnFilter;

    DatePicker dp;
    ArrayList<String> alItems;
    ArrayList<String> unsortedALItems;


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
        unsortedALItems = new ArrayList<>();

        aaItems = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alItems);
        lvItems.setAdapter(aaItems);

        registerForContextMenu(lvItems);

        alItems.add("Expires 2021-5-4 Lenovo");
        alItems.add("Expires 2021-6-8 Asus");
        alItems.add("Expires 2021-7-9 Acer");
        alItems.add("Expires 2021-9-10 HP");
        alItems.add("Expires 2021-10-1 Dell");

//        unsortedALItems.add("Lenovo");
//        unsortedALItems.add("Asus");
//        unsortedALItems.add("Acer");
//        unsortedALItems.add("HP");
//        unsortedALItems.add("Dell");

        Collections.sort(unsortedALItems, String.CASE_INSENSITIVE_ORDER);
        aaItems.notifyDataSetChanged();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int m = dp.getMonth() + 1;
                String item = etNewItem.getText().toString();
                String aItem = "Expires " + dp.getYear() + "-" + m
                        + "-" + dp.getDayOfMonth() + " " + item;
                alItems.add(aItem);
                Collections.sort(alItems, String.CASE_INSENSITIVE_ORDER);
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
                        eMonth = 0;
                        break;
                    case 1:
                        eMonth = 1;
                        break;
                    case 2:
                        eMonth = 3;
                        break;
                    case 3:
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
            int m = dp.getMonth() + 1;
            String nItem = etNewItem.getText().toString();
            alItems.set(itemClicked, "Expires " + dp.getYear() + "-" + m
                    + "-" + dp.getDayOfMonth() + " " + nItem);

            aaItems.notifyDataSetChanged();
        } else if (item.getItemId()==1){
            alItems.remove(itemClicked);

            aaItems.notifyDataSetChanged();
        }

        return super.onContextItemSelected(item);
    }
}