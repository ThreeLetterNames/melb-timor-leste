package org.rhok.linguist.interview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import org.rhok.linguist.R;
import org.rhok.linguist.old.StudyActivity;
import org.rhok.linguist.code.ListViewPopulator;
import org.rhok.linguist.code.Person;
import org.rhok.linguist.location.InterviewMunicipalityActivity;


public class InterviewSpokenLanguageActivity extends BaseInterviewActivity {

    private String selectedLanguage = "";
    private String nextActivity = "";
    private int languageNumber;
    private Person _person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_spoken_language);
        setTitle("Select Language");

        TextView question = (TextView)findViewById(R.id.language_question);
        question.setText(languageQuestion());
        populateLanguages();

        Bundle extras = getIntent().getExtras();

        nextActivity = extras.getString("NEXT_ACTIVITY");
        _person = (Person) extras.getSerializable("Person");

        setTitle("Interview - Language");
    }

    public void nextButtonClick(android.view.View view) {

        if (nextActivity.equals("Study")) {
            Intent intent = new Intent(this, StudyActivity.class);
            intent.putExtra("Person", _person);
            intent.putExtra("LANGUAGE", selectedLanguage);
            startActivity(intent);
        }
        if (nextActivity.equals("MoreLanguages")) {

            Intent intent = new Intent(this, InterviewMoreLanguagesActivity.class);

            languageNumber = getIntent().getExtras().getInt("LanguageNumber");
            if (languageNumber == 1) {
                _person.firstlanguage = selectedLanguage;
            }
            if (languageNumber == 2) {
                _person.secondlanguage = selectedLanguage;
            }
            if (languageNumber == 3) {
                _person.thirdlanguage = selectedLanguage;
            }
            if (languageNumber == 4) {
                _person.otherlanguages = selectedLanguage;

                intent = new Intent(this, InterviewMunicipalityActivity.class);
                intent.putExtra("mode", "lives");
            }
            intent.putExtra("Person", _person);
            intent.putExtra("LastLanguageNumber", languageNumber);
            startActivity(intent);
        }
    }

    private String languageQuestion() {
        Bundle extras = getIntent().getExtras();
        String value = "";
        if (extras != null) {
            value = extras.getString("LANGUAGE_QUESTION");
        } else {
            //TODO when pressing the UP button on the "select study" activity,
            // the intent is null, so the "extras" is  null. Needs to be fixed somehow
        }
        return value;
    }

    private void populateLanguages() {

        ListViewPopulator.populate(this, R.id.language_list, R.array.study_languages, true,
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectedLanguage = (String) parent.getItemAtPosition(position);
                    //Log.i("languageapp", selectedLanguage);
                    view.setSelected(true);
                }
            }
        );

    }


}