package com.example.clickablecoffeeshopandroidedition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class UpgradeShopActivity extends AppCompatActivity {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
    }

    //ListView Declaration
    RecyclerView upgradeRecyclerView;
    java.util.List list;

    //Price Declarations
    static int coffeePrice = 15;
    static int sugarPrice = 105;
    static int milkPrice = 1000;
    static int almondMilkPrice = 11000;
    static int coffeeMachinePrice = 99999;
    static int betterMachinePrice = 800000;
    static int luxuryMachinePrice = 12000000;

    //Amount Owned Declarations
    static int coffeeOwned = 0;
    static int sugarOwned = 0;
    static int milkOwned = 0;
    static int almondMilkOwned = 0;
    static int coffeeMachineOwned = 0;
    static int betterMachineOwned = 0;
    static int luxuryMachineOwned = 0;

    static int beansPerSecond = Game.beansPerSecond;

    static CountDownTimer coffeeTimer;
    static CountDownTimer sugarTimer;
    static CountDownTimer milkTimer;
    static CountDownTimer almondmilkTimer;
    static CountDownTimer coffeemachineTimer;
    static CountDownTimer bettermachineTimer;
    static CountDownTimer luxurymachineTimer;


    //Declarations for Upgrades List / Adapter
    private RecyclerView myRecyclerView;
    private exampleAdapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    final ArrayList<exampleAdapter> exampleList = new ArrayList<>();

    //SharedPreference Declarations/Keys
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String cP = "coffeePrice";
    public static final String sP = "sugarPrice";
    public static final String mP = "milkPrice";
    public static final String aMP = "almondMilkPrice";
    public static final String cMP = "coffeeMachinePrice";
    public static final String bMP = "betterMachinePrice";
    public static final String lMP = "luxuryMachinePrice";

    public static final String cO = "coffeeOwned";
    public static final String sO = "sugarOwned";
    public static final String mO = "milkOwned";
    public static final String aMO = "almondMilkOwned";
    public static final String cMO = "coffeeMachineOwned";
    public static final String bMO = "betterMachineOwned";
    public static final String lMO = "luxuryMachineOwned";

    public static final String BPS = "beansPerSecond";


    @Override //OverRides the Back button for this activity. The game was getting buggy with the
    // standard use of the back button.
    public void onBackPressed(){
        saveData();
        Intent intent = new Intent(this, Game.class);
        startActivity(intent); //Goes to Game Activity
    }

    public void saveData() { //Save Data Script.
        //This saves all of the data into sharedPreferences so progress in the game can be saved.
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(cP, coffeePrice);
        editor.putInt(sP, sugarPrice);
        editor.putInt(mP, milkPrice);
        editor.putInt(aMP, almondMilkPrice);
        editor.putInt(cMP, coffeeMachinePrice);
        editor.putInt(bMP, betterMachinePrice);
        editor.putInt(lMP, luxuryMachinePrice);
        editor.putInt(cO, coffeeOwned);
        editor.putInt(sO, sugarOwned);
        editor.putInt(mO, milkOwned);
        editor.putInt(aMO, almondMilkOwned);
        editor.putInt(cMO, coffeeMachineOwned);
        editor.putInt(bMO, betterMachineOwned);
        editor.putInt(lMO, luxuryMachineOwned);
        editor.putInt(BPS, beansPerSecond);
        editor.apply();
        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();
    }


    public void loadData() { //Load Data Script.
        //This loads all of the saved sharedPreferences into the app upon startup when called.
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        coffeePrice = sharedPreferences.getInt(cP, 15);
        sugarPrice = sharedPreferences.getInt(sP, 105);
        milkPrice = sharedPreferences.getInt(mP, 1000);
        almondMilkPrice = sharedPreferences.getInt(aMP, 11000);
        coffeeMachinePrice = sharedPreferences.getInt(cMP, 99999);
        betterMachinePrice = sharedPreferences.getInt(bMP, 800000);
        luxuryMachinePrice = sharedPreferences.getInt(lMP, 12000000);
        coffeeOwned = sharedPreferences.getInt(cO, 0);
        sugarOwned = sharedPreferences.getInt(sO, 0);
        milkOwned = sharedPreferences.getInt(mO, 0);
        almondMilkOwned = sharedPreferences.getInt(aMO, 0);
        coffeeMachineOwned = sharedPreferences.getInt(cMO, 0);
        betterMachineOwned = sharedPreferences.getInt(bMO, 0);
        luxuryMachineOwned = sharedPreferences.getInt(lMO, 0);
        beansPerSecond = sharedPreferences.getInt(BPS, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_shop);
        loadData();

        //Ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //Coffee List Item
        exampleList.add(new exampleAdapter(R.drawable.coffeecup, "Coffee",
                "+1 Bean Per Second" + '\n' + "Cost: " + coffeePrice + " Beans" + " | " +
                        "Owned: " + coffeeOwned));
        //Sugar List Item
        exampleList.add(new exampleAdapter(R.drawable.sugar, "Sugar",
                "+5 Beans Per Second" + '\n' + "Cost: " + sugarPrice + " Beans" + " | " +
                        "Owned: " + sugarOwned));
        //Milk List Item
        exampleList.add(new exampleAdapter(R.drawable.milk, "Milk",
                "+10 Beans Per Second" + '\n' + "Cost: " + milkPrice + " Beans" + " | " +
                        "Owned: " + milkOwned));
        //Almond Milk List Item
        exampleList.add(new exampleAdapter(R.drawable.almondmilk, "Almond Milk",
                "+50 Beans Per Second" + '\n' + "Cost: " + almondMilkPrice + " Beans" + " | " +
                        "Owned: " + almondMilkOwned));
        //Coffee Machine List Item
        exampleList.add(new exampleAdapter(R.drawable.coffeemachine, "Coffee Machine",
                "+255 Beans Per Second" + '\n' + "Cost: " + coffeeMachinePrice + " Beans" + " | " +
                        "Owned: " + coffeeMachineOwned));
        //Better Coffee Machine List Item
        exampleList.add(new exampleAdapter(R.drawable.upgradedmachine, "Better Coffee Machine",
                "+1200 Beans Per Second" + '\n' + "Cost: " + betterMachinePrice + " Beans" + " | " +
                        "Owned: " + betterMachineOwned));
        //Luxury Coffee Machine List Item
        exampleList.add(new exampleAdapter(R.drawable.luxurymachine, "Luxury Coffee Machine",
                "+8000 Beans Per Second" + '\n' + "Cost: " + luxuryMachinePrice + " Beans" + " | " +
                        "Owned: " + luxuryMachineOwned));

        //Instantiating the List
        myRecyclerView = findViewById(R.id.upgradeRecyclerView);
        //List has a fixed size
        myRecyclerView.setHasFixedSize(true);
        //Setting up the Adapter with the List
        myLayoutManager = new LinearLayoutManager(this);
        myAdapter = new exampleAdapter(exampleList);
        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.setAdapter(myAdapter);

        //OnClickListeners for the List.
        myAdapter.setOnItemClickListener(new exampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //OnClick Handler and Events for Coffee Upgrade
                if (position == 0) {
                    if (Game.CoffeeBeans >= coffeePrice) {
                        exampleList.get(position).changeDescription("+1 Bean Per Second" + '\n'
                                + "Cost: " + Math.round(coffeePrice * 1.15) + " Beans" + " | " +
                                "Owned: " + (coffeeOwned + 1));
                        Game.CoffeeBeans -= coffeePrice;
                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Math.round(Game.CoffeeBeans));
                        coffeePrice *= 1.15;
                        coffeeOwned++;
                        Game.beansPerSecond += 1;
                         IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction(Intent.ACTION_TIME_TICK);
                        BroadcastReceiver CoffeeBeanUpdateReceiver = new BroadcastReceiver() {
                            @Override
                            public void onReceive(Context context, Intent intent) {

                                //Makes the CountDownTimer on Game functional.
                                coffeeTimer = new CountDownTimer(1000, 1000) {
                                    public void onTick(long millisUntilFinished) {

                                    }

                                    //Timer Resets when Finished; Adds Coffee Beans
                                    public void onFinish() {
                                        long millisUntilFinished = 1000;
                                        Game.CoffeeBeans += 1;
                                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Game.CoffeeBeans);
                                        start();
                                    }
                                }.start(); //Starts counting down
                            }
                        };
                        registerReceiver(CoffeeBeanUpdateReceiver, intentFilter);

                        myAdapter.notifyItemChanged(position);
                    } else {
                        //Do Nothing
                    }
                }
                //OnClick Handler and Events for Sugar Upgrade
                if (position == 1) {
                    if (Game.CoffeeBeans >= sugarPrice) {
                        exampleList.get(position).changeDescription("+5 Beans Per Second" + '\n'
                                + "Cost: " + Math.round(sugarPrice * 1.15) + " Beans" + " | " +
                                "Owned: " + (sugarOwned + 1));
                        Game.CoffeeBeans -= sugarPrice;
                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Math.round(Game.CoffeeBeans));
                        sugarPrice *= 1.15;
                        sugarOwned++;
                        Game.beansPerSecond += 5;
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction(Intent.ACTION_TIME_TICK);
                        BroadcastReceiver SugarUpdateReceiver = new BroadcastReceiver() {
                            @Override
                            public void onReceive(Context context, Intent intent) {

                                //Makes the CountDownTimer on Game functional.
                                sugarTimer = new CountDownTimer(1000, 1000) {
                                    public void onTick(long millisUntilFinished) { }

                                    //Timer Resets when Finished; Adds Coffee Beans
                                    public void onFinish() {
                                        long millisUntilFinished = 1000;
                                        Game.CoffeeBeans += 5;
                                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Game.CoffeeBeans);
                                        start();
                                    }
                                }.start(); //Starts counting down
                            }
                        };
                        registerReceiver(SugarUpdateReceiver, intentFilter);

                        myAdapter.notifyItemChanged(position);
                    } else {
                        //Do Nothing
                    }
                }
                //OnClick Handler and Events for Milk Upgrade
                if (position == 2) {
                    if (Game.CoffeeBeans >= milkPrice) {
                        exampleList.get(position).changeDescription("+10 Beans Per Second" + '\n'
                                + "Cost: " + Math.round(milkPrice * 1.15) + " Beans" + " | " +
                                "Owned: " + (milkOwned + 1));
                        Game.CoffeeBeans -= milkPrice;
                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Math.round(Game.CoffeeBeans));
                        milkPrice *= 1.15;
                        milkOwned++;
                        Game.beansPerSecond += 10;
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction(Intent.ACTION_TIME_TICK);
                        BroadcastReceiver MilkUpdateReceiver = new BroadcastReceiver() {
                            @Override
                            public void onReceive(Context context, Intent intent) {

                                //Makes the CountDownTimer on Game functional.
                                milkTimer = new CountDownTimer(1000, 1000) {
                                    public void onTick(long millisUntilFinished) { }

                                    //Timer Resets when Finished; Adds Coffee beans
                                    public void onFinish() {
                                        long millisUntilFinished = 1000;
                                        Game.CoffeeBeans += 10;
                                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Game.CoffeeBeans);
                                        start();
                                    }
                                }.start(); //Starts counting down
                            }
                        };
                        registerReceiver(MilkUpdateReceiver, intentFilter);

                        myAdapter.notifyItemChanged(position);
                    } else {
                        //Do Nothing
                    }
                }
                //OnClick Handler and Events for Almond Milk Upgrade
                if (position == 3) {
                    if (Game.CoffeeBeans >= almondMilkPrice) {
                        exampleList.get(position).changeDescription("+50 Beans Per Second" + '\n'
                                + "Cost: " + Math.round(almondMilkPrice * 1.15) + " Beans" + " | " +
                                "Owned: " + (almondMilkOwned + 1));
                        Game.CoffeeBeans -= almondMilkPrice;
                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Math.round(Game.CoffeeBeans));
                        almondMilkPrice *= 1.15;
                        almondMilkOwned++;
                        Game.beansPerSecond += 50;
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction(Intent.ACTION_TIME_TICK);
                        BroadcastReceiver AlmondMilkUpdateReceiver = new BroadcastReceiver() {
                            @Override
                            public void onReceive(Context context, Intent intent) {

                                //Makes the CountDownTimer on Game functional.
                                almondmilkTimer = new CountDownTimer(1000, 1000) {
                                    public void onTick(long millisUntilFinished) { }

                                    //Timer Resets when Finished; Adds Coffee Beans
                                    public void onFinish() {
                                        long millisUntilFinished = 1000;
                                        Game.CoffeeBeans += 50;
                                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Game.CoffeeBeans);
                                        start();
                                    }
                                }.start(); //Starts counting down
                            }
                        };
                        registerReceiver(AlmondMilkUpdateReceiver, intentFilter);

                        myAdapter.notifyItemChanged(position);
                    } else {
                        //Do Nothing
                    }
                }
                //OnClick Handler and Events for Coffee Machine Upgrade
                if (position == 4) {
                    if (Game.CoffeeBeans >= coffeeMachinePrice) {
                        exampleList.get(position).changeDescription("+255 Beans Per Second" + '\n'
                                + "Cost: " + Math.round(coffeeMachinePrice * 1.15) + " Beans" + " | " +
                                "Owned: " + (coffeeMachineOwned + 1));
                        Game.CoffeeBeans -= coffeeMachinePrice;
                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Math.round(Game.CoffeeBeans));
                        coffeeMachinePrice *= 1.15;
                        coffeeMachineOwned++;
                        Game.beansPerSecond += 255;
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction(Intent.ACTION_TIME_TICK);
                        BroadcastReceiver CoffeeMachineUpdateReceiver = new BroadcastReceiver() {
                            @Override
                            public void onReceive(Context context, Intent intent) {

                                //Makes the CountDownTimer on Game functional.
                                coffeemachineTimer = new CountDownTimer(1000, 1000) {
                                    public void onTick(long millisUntilFinished) { }

                                    //Timer Resets when Finished; Adds Coffee Beans
                                    public void onFinish() {
                                        long millisUntilFinished = 1000;
                                        Game.CoffeeBeans += 255;
                                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Game.CoffeeBeans);
                                        start();
                                    }
                                }.start(); //Starts counting down
                            }
                        };
                        registerReceiver(CoffeeMachineUpdateReceiver, intentFilter);

                        myAdapter.notifyItemChanged(position);
                    } else {
                        //Do Nothing
                    }
                }
                //OnClick Handler and Events for Better Machine Upgrade
                if (position == 5) {
                    if (Game.CoffeeBeans >= betterMachinePrice) {
                        exampleList.get(position).changeDescription("+1200 Beans Per Second" + '\n'
                                + "Cost: " + Math.round(betterMachinePrice * 1.15) + " Beans" + " | " +
                                "Owned: " + (betterMachineOwned + 1));
                        Game.CoffeeBeans -= betterMachinePrice;
                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Math.round(Game.CoffeeBeans));
                        betterMachinePrice *= 1.15;
                        betterMachineOwned++;
                        Game.beansPerSecond += 1200;
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction(Intent.ACTION_TIME_TICK);
                        BroadcastReceiver BetterMachineUpdateReceiver = new BroadcastReceiver() {
                            @Override
                            public void onReceive(Context context, Intent intent) {

                                //Makes the CountDownTimer on Game functional.
                                bettermachineTimer = new CountDownTimer(1000, 1000) {
                                    public void onTick(long millisUntilFinished) { }

                                    //Timer Resets when Finished; Adds Coffee Beans
                                    public void onFinish() {
                                        long millisUntilFinished = 1000;
                                        Game.CoffeeBeans += 1200;
                                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Game.CoffeeBeans);
                                        start();
                                    }
                                }.start(); //Starts counting down
                            }
                        };
                        registerReceiver(BetterMachineUpdateReceiver, intentFilter);

                        myAdapter.notifyItemChanged(position);
                    } else {
                        //Do Nothing
                    }
                }
                //OnClick Handler and Events for Luxury Machine Upgrade
                if (position == 6) {
                    if (Game.CoffeeBeans >= luxuryMachinePrice) {
                        exampleList.get(position).changeDescription("+8000 Beans Per Second" + '\n'
                                + "Cost: " + Math.round(luxuryMachinePrice * 1.15) + " Beans" + " | " +
                                "Owned: " + (luxuryMachineOwned + 1));
                        Game.CoffeeBeans -= luxuryMachinePrice;
                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Math.round(Game.CoffeeBeans));
                        luxuryMachinePrice *= 1.15;
                        luxuryMachineOwned++;
                        Game.beansPerSecond += 8000;
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction(Intent.ACTION_TIME_TICK);
                        BroadcastReceiver LuxuryMachineUpdateReceiver = new BroadcastReceiver() {
                            @Override
                            public void onReceive(Context context, Intent intent) {

                                //Makes the CountDownTimer on Game functional.
                                luxurymachineTimer = new CountDownTimer(1000, 1000) {
                                    public void onTick(long millisUntilFinished) { }

                                    //Timer Resets when Finished; Adds Coffee Beans
                                    public void onFinish() {
                                        long millisUntilFinished = 1000;
                                        Game.CoffeeBeans += 8000;
                                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Game.CoffeeBeans);
                                        start();
                                    }
                                }.start(); //Starts counting down
                            }
                        };
                        registerReceiver(LuxuryMachineUpdateReceiver, intentFilter);
                        myAdapter.notifyItemChanged(position);
                    } else {
                        //Do Nothing
                    }
                }
            }
        });
    }
}


