package org.tylery.c196.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import org.tylery.c196.R;

public class NavigationPanelActivity extends AppCompatActivity {
    private Button viewTermListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_panel);

        viewTermListBtn = findViewById(R.id.btn_view_term_list);
        viewTermListBtn.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), TermListActivity.class);
            v.getContext().startActivity(intent);
        });

    }
}
