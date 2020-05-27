package org.tylery.c196.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.tylery.c196.R;
import org.tylery.c196.adapters.TermAdapter;
import org.tylery.c196.entities.TermEntity;
import org.tylery.c196.viewmodel.TermViewModel;


public class TermListActivity extends AppCompatActivity {
    public static final int ADD_TERM_REQUEST = 1;

    private TermViewModel termViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_list);

        FloatingActionButton buttonAddTerm = findViewById(R.id.btn_add_term);
        buttonAddTerm.setOnClickListener(v -> {
            Intent intent = new Intent(TermListActivity.this, AddEditTermActivity.class);
            startActivityForResult(intent, ADD_TERM_REQUEST);
        });

        RecyclerView recyclerView = findViewById(R.id.termListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TermAdapter adapter = new TermAdapter();
        recyclerView.setAdapter(adapter);

        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        termViewModel.getAllTerms().observe(this, termEntities -> adapter.setTerms(termEntities));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                TermEntity deletedTerm = adapter.getTermAt(viewHolder.getAdapterPosition());
                termViewModel.delete(deletedTerm);
                Toast.makeText(TermListActivity.this, "Term deleted", Toast.LENGTH_SHORT).show();
//                TODO
//                  Some kind of logic to prevent term from being deleted if there are courses
//                  associated with it. Maybe get courses associated with term and if size is
//                  > 1 show a error message?
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(termEntity -> {
            Intent intent = new Intent(TermListActivity.this, TermActivity.class);
            intent.putExtra(AddEditTermActivity.EXTRA_ID, termEntity.getId());
            intent.putExtra(AddEditTermActivity.EXTRA_TITLE, termEntity.getTitle());
            intent.putExtra(AddEditTermActivity.EXTRA_START_DATE, termEntity.getStart());
            intent.putExtra(AddEditTermActivity.EXTRA_END_DATE, termEntity.getEnd());
            startActivity(intent);
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_TERM_REQUEST && resultCode == RESULT_OK) {
//            TODO
//              Likely convert these to UTC datetime
            String title = data.getStringExtra(AddEditTermActivity.EXTRA_TITLE);
            String startDate = data.getStringExtra(AddEditTermActivity.EXTRA_START_DATE);
            String endDate = data.getStringExtra(AddEditTermActivity.EXTRA_END_DATE);

            TermEntity termEntity = new TermEntity(title, startDate, endDate);
            termViewModel.insert(termEntity);

            Toast.makeText(this, "Term added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Term not added", Toast.LENGTH_SHORT).show();
        }
    }

}
