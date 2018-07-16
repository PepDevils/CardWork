package com.example.dezvezesdez.cardwork.activities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dezvezesdez.cardwork.R;
import com.example.dezvezesdez.cardwork.activities.adapter.ItemsAdapter;
import com.example.dezvezesdez.cardwork.activities.util.Card;

import java.util.ArrayList;

public class WalletActivity extends AppCompatActivity {

    private ArrayList<Card> card_array_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        GetIntent();
        RecyclerPopulate();
    }

    private void RecyclerPopulate() {

        RecyclerView rc = (RecyclerView) findViewById(R.id.rc);
        rc.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WalletActivity.this, LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(linearLayoutManager);

        ItemsAdapter itemsAdapter = new ItemsAdapter(WalletActivity.this, rc, card_array_list);
        rc.setAdapter(itemsAdapter);

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

        Intent i = new Intent(WalletActivity.this, MainActivity.class);
        i.putParcelableArrayListExtra("cards", card_array_list);
        startActivity(i);
    }
}
