package com.example.lachlan.myfirstapp.util;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.lachlan.myfirstapp.HomeActivity;
import com.example.lachlan.myfirstapp.R;
import com.example.lachlan.myfirstapp.code.DatabaseHelper;
import com.example.lachlan.myfirstapp.code.Person;

public class GenderCaptureActivity extends ActionBarActivity {

    String maleText;
    String femaleText;
    private boolean editMode;
    private int personId;
    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_capture);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gender_capture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populatePersonDetails() {
        Intent intent = getIntent();
        personId = intent.getIntExtra(HomeActivity.INTENT_PERSONID, -1);
        String windowTitle = getResources().getString(R.string.person_title);

        if (personId != -1) {
            editMode = true;
            windowTitle = getResources().getString(R.string.person_title_edit);

            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
            Person person = db.getPerson(personId);
            if (person != null) {
                nameEditText.setText(person.name);
            }
        }
    }

    public void nextPersonButton(android.view.View view) {
        Intent intent = new Intent(this, HomeCaptureActivity.class);
        //intent.putExtra
        startActivity(intent);
    }
}
