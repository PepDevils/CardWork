package com.example.dezvezesdez.cardwork.activities.adapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;

import com.example.dezvezesdez.cardwork.R;
import com.example.dezvezesdez.cardwork.activities.activities.MainActivity;
import com.example.dezvezesdez.cardwork.activities.util.Card;

import java.util.ArrayList;
import java.util.List;

public class SearchCardActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static ArrayList<Card> card_array_list = new ArrayList<>();

    private RecyclerView rc;
    private ItemsAdapter itemsAdapter;
    private ArrayList<Card> new_array_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_card);

        GetIntent();

        rc = (RecyclerView) findViewById(R.id.rc);
        rc.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchCardActivity.this, LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(linearLayoutManager);

        new_array_list = new ArrayList<>();
        for (Card card_item : card_array_list) {
            new_array_list.add(card_item);
        }

        itemsAdapter = new ItemsAdapter(SearchCardActivity.this, rc, new_array_list);
        rc.setAdapter(itemsAdapter);

        setupSearchView();
    }



    private void setupSearchView() {

        SearchView searchView1 = (SearchView) findViewById(R.id.searchView1);


        if (searchView1 != null) {

            searchView1.setIconifiedByDefault(false);
            searchView1.setOnQueryTextListener(this);
            searchView1.setSubmitButtonEnabled(true);
            searchView1.setQueryHint("Search ID Card Here");
        }


    }


    private boolean GetIntent() {

        Intent i = getIntent();


        if (i.getParcelableArrayListExtra("cards") != null) {

            card_array_list = i.getParcelableArrayListExtra("cards");

            return true;
        } else {
            return false;
        }

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        Intent i = new Intent(SearchCardActivity.this, MainActivity.class);
        i.putParcelableArrayListExtra("cards", card_array_list);
        startActivity(i);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if(card_array_list.size() != new_array_list.size()){
            new_array_list = new ArrayList<>();
            for (Card card_item : card_array_list) {
                new_array_list.add(card_item);
            }
        }



        final List<Card> filteredModelList = filter(new_array_list, newText);
        itemsAdapter.animateTo(filteredModelList);
        rc.scrollToPosition(0);
        return true;
    }



    private List<Card> filter(List<Card> models, String query) {
        query = query.toLowerCase();

        final List<Card> filteredModelList = new ArrayList<>();
        for (Card model : models) {
            final String text = model.getId_card().toString().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }


}
