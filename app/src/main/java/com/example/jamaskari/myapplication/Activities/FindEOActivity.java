package com.example.jamaskari.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

/**
 * Created by jamaskari on 03.06.2016.
 */
public class FindEOActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.findeo_form);

        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerXarray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                    databaseAccess.open();
                    List<String> list = new List<String>();

                    switch (position) {
                        case 0:
                            list = databaseAccess.getEOnames();
                            break;
                        case 1:
                            list = databaseAccess.getEOspecialities();
                            break;
                        case 2:
                            list = databaseAccess.getEOspecialityCodes();
                            break;
                        default:
                            break;
                    }

                    String[] arrayToSpinner = new String[list.size()];
                    list.toArray(arrayToSpinner);

                    Spinner spinner = (Spinner) findViewById(R.id.spinner2);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Findeo_activity.this, android.R.layout.simple_spinner_item, arrayToSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter);
                    databaseAccess.close();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
        );
    }

    public void buttonBackClick(View view) {
        Intent intent = new Intent(Findeo_activity.this, MapsActivity.class);
        startActivity(intent);
    }

    public void buttonForwardClick(View view) {
        Intent intent = new Intent(Findeo_activity.this, MapsActivity.class);

        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        String parameter = spinner1.getSelectedItem().toString();
        String values = spinner2.getSelectedItem().toString();
        intent.putExtra("parameter", parameter);
        intent.putExtra("values", values);

        startActivityForResult(intent, 42);
        finish();
    }
}
