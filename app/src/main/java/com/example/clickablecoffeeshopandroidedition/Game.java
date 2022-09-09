package com.example.clickablecoffeeshopandroidedition;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.bO;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.bP;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.banO;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.banP;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.bananaOwned;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.bananaPrice;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.bronzeOwned;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.bronzePrice;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.gO;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.gP;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.goldOwned;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.goldPrice;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.mO;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.mP;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.mintOwned;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.mintPrice;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.mufO;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.mufP;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.muffinOwned;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.muffinPrice;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.sO;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.sP;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.silverOwned;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.silverPrice;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.vO;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.vP;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.vanillaOwned;
import static com.example.clickablecoffeeshopandroidedition.IconShopActivity.vanillaPrice;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.BPS;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.aMO;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.aMP;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.almondMilkOwned;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.almondMilkPrice;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.bMO;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.bMP;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.betterMachineOwned;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.betterMachinePrice;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.cMO;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.cMP;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.cO;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.cP;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.coffeeMachineOwned;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.coffeeMachinePrice;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.coffeeOwned;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.coffeePrice;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.lMO;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.lMP;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.luxuryMachineOwned;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.luxuryMachinePrice;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.milkOwned;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.milkPrice;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.sugarOwned;
import static com.example.clickablecoffeeshopandroidedition.UpgradeShopActivity.sugarPrice;

public class Game extends AppCompatActivity {

    
    @Override
    public void onBackPressed(){} //Disables Back Button

    //Declarations
    static Button btnBean;
    Button btnIconShop;
    Button btnUpgrades;
    Button btnReportBug;
    Button btnResetProgress;
    static TextView txtCoffeeBeans;
    static int CoffeeBeans = 0;
    static int clickIncrement = 1;
    static int beansPerSecond = 0;
    static int beansPerClick = 1;

    //Declaration of Shared Preferences / Shared Preference keys.
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String cB = "coffeeBeans";
    public static final String cI = "clickIncrement";
    public static final String bPS = "beansPerSecond";
    public static final String bPC = "beansPerClick";

    //Declaration of the one timer created in this activity.
    static CountDownTimer BeanTimer;


    //Save Data Script. The data is stored in shared preferences to be accessed later on.
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(cB, CoffeeBeans);
        editor.putInt(cI, clickIncrement);
        editor.putInt(bPS, beansPerSecond);
        editor.putInt(bPC, beansPerClick);
        editor.apply();
        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();
    }

    //Load Data Script. This loads the shared preferences into the app on subsequent uses so data is
    //not lost.
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        CoffeeBeans = sharedPreferences.getInt(cB, 0);
        clickIncrement = sharedPreferences.getInt(cI, 1);
        beansPerSecond = sharedPreferences.getInt(bPS, 0);
        beansPerClick = sharedPreferences.getInt(bPC, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        if (CoffeeBeans == 0) { //DON'T DELETE THIS
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Intent.ACTION_TIME_TICK);
            final BroadcastReceiver CoffeeBeanUpdateReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    //On app restart, this timer will handle the events of all the upgrade shop timers.
                    //This is so the timers don't stop working on subsequent uses of the app.
                    BeanTimer = new CountDownTimer(1000, 1000) {
                        public void onTick(long millisUntilFinished) {

                        }

                        //Timer Resets when Finished; Adds Coffee Beans
                        public void onFinish() {
                            long millisUntilFinished = 1000;
                            Game.CoffeeBeans += beansPerSecond;
                            Game.txtCoffeeBeans.setText("Coffee Beans: " + Game.CoffeeBeans);
                            start();
                        }
                    }.start(); //Starts counting down
                }
            };
            registerReceiver(CoffeeBeanUpdateReceiver, intentFilter);
            loadData(); //Load
        }

        //Instantiating the controls
        btnBean = (Button) findViewById(R.id.btnBean);
        txtCoffeeBeans = (TextView) findViewById(R.id.txtCoffeeBeans);
        btnResetProgress = (Button) findViewById(R.id.btnResetProgress);

        //Setting the textView text (Coffee Beans)
        txtCoffeeBeans.setText("Coffee Beans: " + CoffeeBeans);

        //Sets the image resource of the main game button.
        btnBean.setBackgroundResource(getIntent().getIntExtra("myImageResource",R.drawable.bean));

        //If/Else If handler for Click Increment (Depending on the Sprite of the Main Button)
        if (btnBean.getBackground().equals(R.drawable.bronzebean)) {
            clickIncrement = 2;
            beansPerClick = 2;
        } else if (btnBean.getBackground().equals(R.drawable.silverbean)) {
            clickIncrement = 5;
            beansPerClick = 5;
        } else if (btnBean.getBackground().equals(R.drawable.goldbean)) {
            clickIncrement = 10;
            beansPerClick = 10;
        } else if (btnBean.getBackground().equals(R.drawable.mintbean)) {
            clickIncrement = 20;
            beansPerClick = 20;
        } else if (btnBean.getBackground().equals(R.drawable.vanillabean)) {
            clickIncrement = 50;
            beansPerClick = 50;
        } else if (btnBean.getBackground().equals(R.drawable.banana)) {
            clickIncrement = 200;
            beansPerClick = 200;
        } else if (btnBean.getBackground().equals(R.drawable.muffin)) {
            clickIncrement = 1000;
            beansPerClick = 1000;
        }

        //OnClickListener for the main game button
        btnBean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation a = AnimationUtils.loadAnimation(Game.this, R.anim.coffeebeananimation);
                a.setAnimationListener(new SimpleAnimationListener(){
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        CoffeeBeans = CoffeeBeans + clickIncrement;
                        txtCoffeeBeans.setText("Coffee Beans: " + CoffeeBeans);
                    }
                });
                v.startAnimation(a);
            }
        });

        //Instantiating the buttons that go to other activities
        btnIconShop = (Button) findViewById(R.id.btnIconShop);
        btnUpgrades = (Button) findViewById(R.id.btnUpgrades);
        btnReportBug = (Button) findViewById(R.id.btnReportBug);

        //OnClickListeners for each new activity button.
        btnIconShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, IconShopActivity.class);
                startActivity(intent); //Goes to Icon Shop
                saveData();
            }
        });

        btnUpgrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, UpgradeShopActivity.class);
                startActivity(intent); //Goes to Icon Shop
                saveData();
            }
        });

        btnReportBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, ReportBugActivity.class);
                startActivity(intent); //Goes to Report Bug Activity
                saveData();
            }
        });
        btnResetProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Clicking the Reset Progress button will open up an alert dialog, asking whether
                //the user wishes to reset the game or not.
                AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);

                builder.setTitle("Reset Game Progress?");
                builder.setMessage("Are you sure you want to restart the game? All of your progress" +
                        " will be lost FOREVER.");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    //If the user presses "YES", a restart script is executed.
                    //This returns all data and shared preferences to their default values.
                    //I.E; it restarts the game.
                    public void onClick(DialogInterface dialog, int which) {
                        CoffeeBeans = 0;
                        txtCoffeeBeans.setText("Coffee Beans: " + CoffeeBeans);
                        btnBean.setBackgroundResource(getIntent().getIntExtra("",R.drawable.bean));
                        clickIncrement = 1;
                        beansPerSecond = 0;
                        beansPerClick = 1;

                        coffeePrice = 15;
                        sugarPrice = 105;
                        milkPrice = 1000;
                        almondMilkPrice = 11000;
                        coffeeMachinePrice = 99999;
                        betterMachinePrice = 800000;
                        luxuryMachinePrice = 12000000;
                        coffeeOwned = 0;
                        sugarOwned = 0;
                        milkOwned = 0;
                        almondMilkOwned = 0;
                        coffeeMachineOwned = 0;
                        betterMachineOwned = 0;
                        luxuryMachineOwned = 0;
                        UpgradeShopActivity.beansPerSecond = Game.beansPerSecond;

                        bronzePrice = 200;
                        silverPrice = 500;
                        goldPrice = 1000;
                        mintPrice = 2000;
                        vanillaPrice = 5000;
                        bananaPrice = 20000;
                        muffinPrice = 100000;
                        bronzeOwned = false;
                        silverOwned = false;
                        goldOwned = false;
                        mintOwned = false;
                        vanillaOwned = false;
                        bananaOwned = false;
                        muffinOwned = false;
                        saveData();

                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putInt(cP, coffeePrice);
                        editor.putInt(UpgradeShopActivity.sP, sugarPrice);
                        editor.putInt(UpgradeShopActivity.mP, milkPrice);
                        editor.putInt(aMP, almondMilkPrice);
                        editor.putInt(cMP, coffeeMachinePrice);
                        editor.putInt(bMP, betterMachinePrice);
                        editor.putInt(lMP, luxuryMachinePrice);
                        editor.putInt(cO, coffeeOwned);
                        editor.putInt(UpgradeShopActivity.sO, sugarOwned);
                        editor.putInt(UpgradeShopActivity.mO, milkOwned);
                        editor.putInt(aMO, almondMilkOwned);
                        editor.putInt(cMO, coffeeMachineOwned);
                        editor.putInt(bMO, betterMachineOwned);
                        editor.putInt(lMO, luxuryMachineOwned);
                        editor.putInt(BPS, beansPerSecond);

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
                        //BeanTimer.cancel();
                        editor.apply();
                        dialog.dismiss();
                    }
                });

                //If the user presses "NO", nothing happens.
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.WHITE));
                alert.show();
            }
        });
    }
}
