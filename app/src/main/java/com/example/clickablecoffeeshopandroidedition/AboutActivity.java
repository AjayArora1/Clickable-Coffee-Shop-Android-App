package com.example.clickablecoffeeshopandroidedition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    //Declaration
    TextView txtInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //Instantiating the TextView
        txtInfo = (TextView) findViewById(R.id.txtInfo);

        //TextView content
        String infoText = "Clickable Coffee Shop is an incremental game designed by Ajay Arora." +
                System.getProperty("line.separator") + System.getProperty("line.separator") +
                "The player starts out by clicking the coffee bean icon in order to accumulate" +
                " in-game currency. Once a sufficient amount of currency is gathered, you can then purchase" +
                " upgrades from the shop pages with it." +
                System.getProperty("line.separator") + System.getProperty("line.separator") +
                "Each upgrade helps the user accumulate currency" +
                " faster. There are two shops in the game. The first shop is for upgrades that" +
                " increase 'currency per click.' The second shop is for upgrades that increase 'currency per second'."
                + System.getProperty("line.separator") + System.getProperty("line.separator") +
                "Disclaimer: All artwork in this game has " +
                "been created by myself (Ajay Arora), and any sounds or other resources used have" +
                " either been created by myself, or licensed to me."
                + System.getProperty("line.separator") + System.getProperty("line.separator") +
                "Copyright © 2019-2020 by Ajay Arora";
        txtInfo.setText(infoText);

        //Makes the TextView scrollable
        txtInfo.setMovementMethod(new ScrollingMovementMethod());
    }
}
