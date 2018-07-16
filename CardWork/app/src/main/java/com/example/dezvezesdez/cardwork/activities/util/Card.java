package com.example.dezvezesdez.cardwork.activities.util;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dezvezesdez on 04/08/16.
 */
public class Card implements Parcelable {

    private String id_card;
    private String name;
    private String number;
    private String link_icon;


    public Card() {

    }

    public Card(String id_card, String name, String number, String link_icon) {
        this.id_card = id_card;
        this.name = name;
        this.number = number;
        this.link_icon = link_icon;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLink_icon() {
        return link_icon;
    }

    public void setLink_icon(String link_icon) {
        this.link_icon = link_icon;
    }

    // Creator
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    public Card(Parcel in) {
        name = in.readString();
        number = in.readString();
        id_card = in.readString();
        link_icon = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

        @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(number);
        dest.writeString(id_card);
        dest.writeString(link_icon);

    }
}
