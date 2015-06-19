package org.rhok.linguist.interview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.rhok.linguist.R;
import org.rhok.linguist.code.ListViewPopulator;
import org.rhok.linguist.code.Person;

import java.util.ArrayList;

public class InterviewGenderActivity extends BaseInterviewActivity {

    private Person _person;
    private String selectedGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_gender);

        Intent intent = getIntent();
        _person = (Person) intent.getSerializableExtra("person");

        populateGenders();

        setTitle("Interview - Gender");
    }




    private void populateGenders() {

        ListViewPopulator.populate(this, R.id.genderListView, R.array.genders, true,
                new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedGender = (String) parent.getItemAtPosition(position);
            }
        });
    }

    public void nextButtonClick(android.view.View view) {

        if (selectedGender == null) {
            Toast toast = Toast.makeText(getApplicationContext(), "Please select a gender", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            _person.gender = selectedGender;

            Intent intent = new Intent(this, InterviewOccupationActivity.class);
            intent.putExtra("person", _person);
            startActivity(intent);
        }

    }

}
