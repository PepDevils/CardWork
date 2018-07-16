package com.example.dezvezesdez.cardwork.activities.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.dezvezesdez.cardwork.R;
import com.example.dezvezesdez.cardwork.activities.adapter.SearchCardActivity;
import com.example.dezvezesdez.cardwork.activities.util.Card;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<Card> card_array_list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FAB();
        CreatCardsList();
        ButtonsFunctions();

       

        //Toast.makeText(MainActivity.this, "List: "+ card_array_list, Toast.LENGTH_SHORT).show();
    }

    private void FAB() {

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //log out
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);

            }
        });
    }


    private void CreatCardsList() {

        if(!GetIntent()){

            String[] Name_list = new String[10];
            Name_list[0] = "Pedro Fonseca";
            Name_list[1] = "Fernando Almeida";
            Name_list[2] = "Liete Serrano";
            Name_list[3] = "Libania Silva";
            Name_list[4] = "João Alberto";
            Name_list[5] = "Paulo Ferreira";
            Name_list[6] = "André Gonçalves";
            Name_list[7] = "Tiago Pereira";
            Name_list[8] = "Jacinto Garret";
            Name_list[9] = "Ana Teresa";

            String[] Icon_list = new String[10];
            Icon_list[0] = "avatar_1";
            Icon_list[1] = "avatar_2";
            Icon_list[2] = "avatar_3";
            Icon_list[3] = "avatar_4";
            Icon_list[4] = "avatar_5";
            Icon_list[5] = "avatar_6";
            Icon_list[6] = "avatar_7";
            Icon_list[7] = "avatar_2";
            Icon_list[8] = "avatar_2";
            Icon_list[9] = "avatar_4";

            for (int i = 0; i < 10; i++) {

                Random r = new Random();
                int random_phone_number = r.nextInt(939999999 - 910000000) + 910000000;
                String phone = "" + random_phone_number;

                int id_random = r.nextInt(9999 - 1111) + 1111;
                String id_card = "" + id_random;

                Card card = new Card(id_card, Name_list[i], phone, Icon_list[i]);
                card_array_list.add(card);
            }

        }

    }

    private void ButtonsFunctions() {

        AppCompatButton bt_card = (AppCompatButton) findViewById(R.id.bt_card);
        AppCompatButton bt_search_card = (AppCompatButton) findViewById(R.id.bt_search_card);
        AppCompatButton bt_wallet = (AppCompatButton) findViewById(R.id.bt_wallet);

        assert bt_card != null;
        bt_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, CardProfileActivity.class);
                i.putParcelableArrayListExtra("cards", card_array_list);
                startActivity(i);

            }
        });

        assert bt_search_card != null;
        bt_search_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, SearchCardActivity.class);
                i.putParcelableArrayListExtra("cards", card_array_list);
                startActivity(i);

            }
        });

        assert bt_wallet != null;
        bt_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, WalletActivity.class);
                i.putParcelableArrayListExtra("cards", card_array_list);
                startActivity(i);

            }
        });

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

}
