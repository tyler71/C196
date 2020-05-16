package org.tylery.c196;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ActivityTerms extends AppCompatActivity {
    private ListView listTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        listTerms = (ListView) findViewById(R.id.termListView);
    }
}
