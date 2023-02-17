package com.example.openmarket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Market extends AppCompatActivity {

    private ArrayList<StoreItem> list;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    DatabaseReference databaseReference;
    private TextView addressTextView;
    private View parentView;
    private UserSettings settings;
    public Boolean bool = true;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menumarket, menu);
        return true;
    }




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        recyclerView = findViewById(R.id.recyclerViewMarket);
        addressTextView = findViewById(R.id.textViewAddressCard);
        databaseReference = FirebaseDatabase.getInstance().getReference("OpenMarket");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);
        settings = (UserSettings) getApplication();

        iniWidgets();
        loadSharedPreferences();
        changeFont();




        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    StoreItem storeItem = dataSnapshot.getValue(StoreItem.class);
                    list.add(storeItem);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }



    private void iniWidgets(){
    parentView = findViewById(R.id.parentView);
    }

    private void loadSharedPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(UserSettings.PREFERENCES,MODE_PRIVATE);
        String theme = sharedPreferences.getString(UserSettings.CUSTOM_THEME,UserSettings.RED_THEME);
        settings.setCustomTheme(theme);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.itemChangeBGRed:
                settings.setCustomTheme(UserSettings.RED_THEME);
                SharedPreferences.Editor editor = getSharedPreferences(UserSettings.PREFERENCES,
                        MODE_PRIVATE).edit();
                editor.putString(UserSettings.CUSTOM_THEME,settings.getCustomTheme());
                editor.apply();
                changeFont();
                updateView();
                break;

            case R.id.itemChangeBGYellow:
                settings.setCustomTheme(UserSettings.YELLOW_THEME);
                SharedPreferences.Editor editor2 = getSharedPreferences(UserSettings.PREFERENCES,
                        MODE_PRIVATE).edit();
                editor2.putString(UserSettings.CUSTOM_THEME,settings.getCustomTheme());
                editor2.apply();

                updateView();
                break;


            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }



    private void updateView(){
        final int red = ContextCompat.getColor(this,R.color.red);
        final int yellow = ContextCompat.getColor(this,R.color.yellow);

        if(settings.getCustomTheme().equals(UserSettings.RED_THEME)){
            parentView.setBackgroundColor(red);

        }else{
            parentView.setBackgroundColor(yellow);
        }

    }



    private void changeFont(){
        setTheme(R.style.CustomTheme_OpenMarket);

    }

    private void changeText(){


    }

}