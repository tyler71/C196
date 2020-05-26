package org.tylery.c196.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.tylery.c196.R;
import org.tylery.c196.adapters.TermAdapter;
import org.tylery.c196.entities.TermEntity;
import org.tylery.c196.viewmodel.TermViewModel;

import java.util.List;

public class TermListActivity extends AppCompatActivity {
    public static final int ADD_TERM_REQUEST = 1;

    private TermViewModel termViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_list);

        FloatingActionButton buttonAddTerm = findViewById(R.id.btn_add_term);
        buttonAddTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TermListActivity.this, EditTermActivity.class);
                startActivityForResult(intent, ADD_TERM_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.termListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TermAdapter adapter = new TermAdapter();
        recyclerView.setAdapter(adapter);

        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        termViewModel.getAllTerms().observe(this, new Observer<List<TermEntity>>() {
            @Override
            public void onChanged(List<TermEntity> termEntities) {
                adapter.setNotes(termEntities);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_TERM_REQUEST && resultCode == RESULT_OK) {

//            TODO
//              Likely convert these to UTC datetime
            String title = data.getStringExtra(EditTermActivity.EXTRA_TITLE);
            String startDate = data.getStringExtra(EditTermActivity.EXTRA_START_DATE);
            String endDate = data.getStringExtra(EditTermActivity.EXTRA_END_DATE);

            TermEntity termEntity = new TermEntity(title, startDate, endDate);
            termViewModel.insert(termEntity);

            Toast.makeText(this, "Term saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
