package com.example.dezvezesdez.cardwork.activities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

import com.example.dezvezesdez.cardwork.R;
import com.example.dezvezesdez.cardwork.activities.util.Card;

import java.util.ArrayList;

public class CardProfileActivity extends AppCompatActivity {

    private ArrayList<Card> card_array_list = new ArrayList<>();

    private EditText ed_id_card, ed_name, ed_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_profile);

        GetIntent();
        PersonalizMyCard();
    }

    private void PersonalizMyCard() {

        AppCompatButton bt_ok = (AppCompatButton) findViewById(R.id.bt_ok);
        ed_id_card = (EditText) findViewById(R.id.ed_id_card);
        ed_name = (EditText) findViewById(R.id.ed_name);
        ed_number = (EditText) findViewById(R.id.ed_number);


        assert bt_ok != null;
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String id = ed_id_card.getText().toString();
                String name = ed_name.getText().toString();
                String number = ed_number.getText().toString();

                Card card = new Card(id, name, number, "avatar_1");


                card_array_list.set(0, card);

            }
        });


    }


    private boolean GetIntent() {

        int aa = 12;

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

        Intent i = new Intent(CardProfileActivity.this, MainActivity.class);
        i.putParcelableArrayListExtra("cards", card_array_list);
        startActivity(i);


    }
}
