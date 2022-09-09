package com.example.clickablecoffeeshopandroidedition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

import static com.example.clickablecoffeeshopandroidedition.Game.beansPerClick;
import static com.example.clickablecoffeeshopandroidedition.Game.btnBean;

public class IconShopActivity extends AppCompatActivity {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
    }

    //Declarations for the Icons List / Adapter
    private RecyclerView myRecyclerView;
    private exampleAdapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    //Icons ListView
    RecyclerView iconRecyclerView;
    java.util.List list;

    //Price Declarations
    static int bronzePrice = 200;
    static int silverPrice = 500;
    static int goldPrice = 1000;
    static int mintPrice = 2000;
    static int vanillaPrice = 5000;
    static int bananaPrice = 20000;
    static int muffinPrice = 100000;

    //Owned? Declarations (T/F)
    static boolean bronzeOwned = false;
    static boolean silverOwned = false;
    static boolean goldOwned = false;
    static boolean mintOwned = false;
    static boolean vanillaOwned = false;
    static boolean bananaOwned = false;
    static boolean muffinOwned = false;

    //Declaration of Shared Preferences / Shared Preference Keys.
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String bP = "bronzePrice";
    public static final String sP = "silverPrice";
    public static final String gP = "goldPrice";
    public static final String mP = "mintPrice";
    public static final String vP = "vanillaPrice";
    public static final String banP = "bananaPrice";
    public static final String mufP = "muffinPrice";

    public static final String bO = "bronzeOwned";
    public static final String sO = "silverOwned";
    public static final String gO = "goldOwned";
    public static final String mO = "mintOwned";
    public static final String vO = "vanillaOwned";
    public static final String banO = "bananaOwned";
    public static final String mufO = "muffinOwned";


    @Override //Overrides the back button, as the game runs into bugs with the standard usage of it.
    public void onBackPressed(){
        saveData();
        Intent intent = new Intent(this, Game.class);
        startActivity(intent); //Goes to Game Activity
    }

    //Save Data Script.
    //Saves all of the data into shared preferences to be accessed later on.
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(bP, bronzePrice);
        editor.putInt(sP, silverPrice);
        editor.putInt(gP, goldPrice);
        editor.putInt(mP, mintPrice);
        editor.putInt(vP, vanillaPrice);
        editor.putInt(banP, bananaPrice);
        editor.putInt(mufP, muffinPrice);
        editor.putBoolean(bO, bronzeOwned);
        editor.putBoolean(sO, silverOwned);
        editor.putBoolean(gO, goldOwned);
        editor.putBoolean(mO, mintOwned);
        editor.putBoolean(vO, vanillaOwned);
        editor.putBoolean(banO, bananaOwned);
        editor.putBoolean(mufO, muffinOwned);
        editor.apply();
        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();
    }

    //Load Data Script.
    //Reloads the shared preference data into the game upon starting up the app.
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        bronzePrice = sharedPreferences.getInt(bP, 200);
        silverPrice = sharedPreferences.getInt(sP, 500);
        goldPrice = sharedPreferences.getInt(gP, 1000);
        mintPrice = sharedPreferences.getInt(mP, 2000);
        vanillaPrice = sharedPreferences.getInt(vP, 5000);
        bananaPrice = sharedPreferences.getInt(banP, 20000 );
        muffinPrice = sharedPreferences.getInt(mufP, 100000);
        bronzeOwned = sharedPreferences.getBoolean(bO, false);
        silverOwned = sharedPreferences.getBoolean(sO, false);
        goldOwned = sharedPreferences.getBoolean(gO, false);
        mintOwned = sharedPreferences.getBoolean(mO, false);
        vanillaOwned = sharedPreferences.getBoolean(vO, false);
        bananaOwned = sharedPreferences.getBoolean(banO, false);
        muffinOwned = sharedPreferences.getBoolean(mufO, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_shop);

        loadData(); //Load

        //Ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //Array List for the Icons List
        final ArrayList<exampleAdapter> exampleList = new ArrayList<>();
        //Bronze Bean List Item
        exampleList.add(new exampleAdapter(R.drawable.bronzebean, "Bronze Coffee Bean",
                "2 Beans Per Click" + '\n' + "Cost: " + bronzePrice + " Beans" + " | " +
                "Owned: " + bronzeOwned));
        //Silver Bean List Item
        exampleList.add(new exampleAdapter(R.drawable.silverbean, "Silver Coffee Bean",
                "5 Beans Per Click" + '\n' + "Cost: " + silverPrice + " Beans" + " | " +
                        "Owned: " + silverOwned));
        //Gold Bean List Item
        exampleList.add(new exampleAdapter(R.drawable.goldbean, "Gold Coffee Bean",
                "10 Beans Per Click" + '\n' + "Cost: " + goldPrice + " Beans" + " | " +
                        "Owned: " + goldOwned));
        //Mint Bean List Item
        exampleList.add(new exampleAdapter(R.drawable.mintbean, "Minty Coffee Bean",
                "20 Beans Per Click" + '\n' + "Cost: " + mintPrice + " Beans" + " | " +
                        "Owned: " + mintOwned));
        //Vanilla Bean List Item
        exampleList.add(new exampleAdapter(R.drawable.vanillabean, "Vanilla Coffee Bean",
                "50 Beans Per Click" + '\n' + "Cost: " + vanillaPrice + " Beans" + " | " +
                        "Owned: " + vanillaOwned));
        //Banana List Item
        exampleList.add(new exampleAdapter(R.drawable.banana, "Banana",
                "200 Beans Per Click" + '\n' + "Cost: " + bananaPrice + " Beans" + " | " +
                        "Owned: " + bananaOwned));
        //Muffin List Item
        exampleList.add(new exampleAdapter(R.drawable.muffin, "Muffin",
                "1000 Beans Per Click" + '\n' + "Cost: " + muffinPrice + " Beans" + " | " +
                        "Owned: " + muffinOwned));

        //Instantiating the List
        myRecyclerView = findViewById(R.id.iconRecyclerView);
        //List has a fixed size
        myRecyclerView.setHasFixedSize(true); //Crashes on Horizontal View for this activity only. Because of this line...
        //Setting up the Adapter with the List
        myLayoutManager = new LinearLayoutManager(this);
        myAdapter = new exampleAdapter(exampleList);
        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.setAdapter(myAdapter);

        //onClickListeners for the List.
        myAdapter.setOnItemClickListener(new exampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //OnClick Handler and Events for Bronze Coffee Bean
                if (position == 0) {
                    if (Game.CoffeeBeans >= bronzePrice) {
                        exampleList.get(position).changeDescription("2 Beans Per Click" + '\n'
                                + "Cost: " + 0 + " Beans" + " | " +
                                "Owned: " + "true");
                        Game.CoffeeBeans -= bronzePrice;
                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Math.round(Game.CoffeeBeans));
                        bronzePrice = 0;
                        bronzeOwned = true;
                        beansPerClick = 2;
                        Game.clickIncrement = 2;

                        Intent act2= new Intent(IconShopActivity.this,Game.class);
                        act2.putExtra("myImageResource", R.drawable.bronzebean);
                        startActivity(act2);
                        saveData();

                        myAdapter.notifyItemChanged(position);
                    } else {
                        //Do Nothing
                    }
                }
                //OnClick Handler and Events for Silver Coffee Bean
                if (position == 1) {
                    if (Game.CoffeeBeans >= silverPrice) {
                        exampleList.get(position).changeDescription("5 Beans Per Click" + '\n'
                                + "Cost: " + 0 + " Beans" + " | " +
                                "Owned: " + "true");
                        Game.CoffeeBeans -= silverPrice;
                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Math.round(Game.CoffeeBeans));
                        silverPrice = 0;
                        silverOwned = true;
                        beansPerClick = 5;
                        Game.clickIncrement = 5;

                        Intent act2= new Intent(IconShopActivity.this,Game.class);
                        act2.putExtra("myImageResource", R.drawable.silverbean);
                        startActivity(act2);
                        saveData();

                        myAdapter.notifyItemChanged(position);
                    } else {
                        //Do Nothing
                    }
                }
                //OnClick Handler and Events for Gold Coffee Bean
                if (position == 2) {
                    if (Game.CoffeeBeans >= goldPrice) {
                        exampleList.get(position).changeDescription("10 Beans Per Click" + '\n'
                                + "Cost: " + 0 + " Beans" + " | " +
                                "Owned: " + "true");
                        Game.CoffeeBeans -= goldPrice;
                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Math.round(Game.CoffeeBeans));
                        goldPrice = 0;
                        goldOwned = true;
                        beansPerClick = 10;
                        Game.clickIncrement = 10;

                        Intent act2= new Intent(IconShopActivity.this,Game.class);
                        act2.putExtra("myImageResource", R.drawable.goldbean);
                        startActivity(act2);
                        saveData();

                        myAdapter.notifyItemChanged(position);
                    } else {
                        //Do Nothing
                    }
                }
                //OnClick Handler and Events for Mint Coffee Bean
                if (position == 3) {
                    if (Game.CoffeeBeans >= mintPrice) {
                        exampleList.get(position).changeDescription("20 Beans Per Click" + '\n'
                                + "Cost: " + 0 + " Beans" + " | " +
                                "Owned: " + "true");
                        Game.CoffeeBeans -= mintPrice;
                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Math.round(Game.CoffeeBeans));
                        mintPrice = 0;
                        mintOwned = true;
                        beansPerClick = 20;
                        Game.clickIncrement = 20;

                        Intent act2= new Intent(IconShopActivity.this,Game.class);
                        act2.putExtra("myImageResource", R.drawable.mintbean);
                        startActivity(act2);
                        saveData();

                        myAdapter.notifyItemChanged(position);
                    } else {
                        //Do Nothing
                    }
                }
                //OnClick Handler and Events for Vanilla Coffee Bean
                if (position == 4) {
                    if (Game.CoffeeBeans >= vanillaPrice) {
                        exampleList.get(position).changeDescription("50 Beans Per Click" + '\n'
                                + "Cost: " + 0 + " Beans" + " | " +
                                "Owned: " + "true");
                        Game.CoffeeBeans -= vanillaPrice;
                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Math.round(Game.CoffeeBeans));
                        vanillaPrice = 0;
                        vanillaOwned = true;
                        beansPerClick = 50;
                        Game.clickIncrement = 50;

                        Intent act2= new Intent(IconShopActivity.this,Game.class);
                        act2.putExtra("myImageResource", R.drawable.vanillabean);
                        startActivity(act2);
                        saveData();

                        myAdapter.notifyItemChanged(position);
                    } else {
                        //Do Nothing
                    }
                }
                //OnClick Handler and Events for Banana Icon
                if (position == 5) {
                    if (Game.CoffeeBeans >= bananaPrice) {
                        exampleList.get(position).changeDescription("200 Beans Per Click" + '\n'
                                + "Cost: " + 0 + " Beans" + " | " +
                                "Owned: " + "true");
                        Game.CoffeeBeans -= bananaPrice;
                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Math.round(Game.CoffeeBeans));
                        bananaPrice = 0;
                        bananaOwned = true;
                        beansPerClick = 200;
                        Game.clickIncrement = 200;

                        Intent act2= new Intent(IconShopActivity.this,Game.class);
                        act2.putExtra("myImageResource", R.drawable.banana);
                        startActivity(act2);
                        saveData();

                        myAdapter.notifyItemChanged(position);
                    } else {
                        //Do Nothing
                    }
                }
                //OnClick Handler and Events for Muffin Icon
                if (position == 6) {
                    if (Game.CoffeeBeans >= muffinPrice) {
                        exampleList.get(position).changeDescription("1000 Beans Per Click" + '\n'
                                + "Cost: " + 0 + " Beans" + " | " +
                                "Owned: " + "true");
                        Game.CoffeeBeans -= muffinPrice;
                        Game.txtCoffeeBeans.setText("Coffee Beans: " + Math.round(Game.CoffeeBeans));
                        muffinPrice = 0;
                        muffinOwned = true;
                        beansPerClick = 1000;
                        Game.clickIncrement = 1000;

                        Intent act2= new Intent(IconShopActivity.this,Game.class);
                        act2.putExtra("myImageResource", R.drawable.muffin);
                        startActivity(act2);
                        saveData();

                        myAdapter.notifyItemChanged(position);
                    } else {
                        //Do Nothing
                    }
                }
            }
        });
    }
}
