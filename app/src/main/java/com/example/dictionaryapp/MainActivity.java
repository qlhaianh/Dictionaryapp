package com.example.dictionaryapp;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    MenuItem menuSetting;

    DictionaryFragment dictFragment;
    YourWordsFragment yourwordsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dictFragment = new DictionaryFragment();
        yourwordsFragment = new YourWordsFragment();
        goToFragment(dictFragment,true);

        dictFragment.setOnFragmentListener(new FragmentListener() {
            @Override
            public void onItemClick(String value) {
                goToFragment(DetailFragment.getNewInstance(value),false);
            }
        });

        yourwordsFragment.setOnFragmentListener(new FragmentListener() {
            @Override
            public void onItemClick(String value) {
                goToFragment(DetailFragment.getNewInstance(value),false);
            }
        });

        EditText edit_search = findViewById(R.id.edit_search);
        edit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dictFragment.filterValue(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menuSetting = menu.findItem(R.id.action_settings);
        String id = Global.getState(this,"dic_type");
        if(id!=null)
            onOptionsItemSelected(menu.findItem(Integer.valueOf(id)));
        else
            DB.getData(R.id.action_en_vi);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Global.saveState(this,"dic_type",String.valueOf(id));
        String[] source =DB.getData(id);
        if(id==R.id.action_en_vi)
        {
            dictFragment.resetDataSource(source);
            menuSetting.setIcon(getDrawable(R.drawable.uk64));
        }
        else if(id==R.id.action_vi_en)
        {
            dictFragment.resetDataSource(source);
            menuSetting.setIcon(getDrawable(R.drawable.vn64));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_save){
            goToFragment(yourwordsFragment,false);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void goToFragment(Fragment fragment, boolean isTop){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        if(!isTop)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}