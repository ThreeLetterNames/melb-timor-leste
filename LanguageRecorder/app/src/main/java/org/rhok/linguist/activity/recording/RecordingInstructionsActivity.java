package org.rhok.linguist.activity.recording;

import android.content.Intent;
import android.os.Bundle;

import org.rhok.linguist.R;
import org.rhok.linguist.activity.IntentUtil;
import org.rhok.linguist.activity.interview.BaseInterviewActivity;

public class RecordingInstructionsActivity extends BaseInterviewActivity {

    private int personId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording_instructions);

        personId = getIntent().getIntExtra(IntentUtil.ARG_PERSON_ID, -1);
    }

    public void nextButtonClick(android.view.View view) {

        Intent intent = new Intent(this, RecordingAudioActivity.class);
        intent.putExtra(IntentUtil.ARG_PERSON_ID, personId);
        startActivity(intent);

    }
}