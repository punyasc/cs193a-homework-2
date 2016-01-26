package com.example.punya.cs193ahw2todolist;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> taskArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null) {
            if (savedInstanceState.getStringArrayList("taskArray") != null)
                taskArray = savedInstanceState.getStringArrayList("taskArray");
        } else {
            taskArray = new ArrayList<String>();
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskArray);
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
        list.setSelector(R.drawable.listselector);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setItemsCanFocus(false);

        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText textField = (EditText) findViewById(R.id.editText);
                String newItem = textField.getText().toString();
                textField.setText("");
                taskArray.add(newItem);
                adapter.notifyDataSetChanged();
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                taskArray.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArrayList("taskArray", taskArray);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        taskArray = savedInstanceState.getStringArrayList("taskArray");
    }

}
