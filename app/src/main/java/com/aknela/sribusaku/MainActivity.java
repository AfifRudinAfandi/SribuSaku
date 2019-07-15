package com.aknela.sribusaku;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {





        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    BerandaFragment berandaFragment = new BerandaFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.inti, berandaFragment);
                    fragmentTransaction.commit();

                    return true;
                case R.id.navigation_dashboard:
                    FAQFragment faqFragment = new FAQFragment();
                    FragmentTransaction fragmentFAQTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentFAQTransaction.replace(R.id.inti, faqFragment);
                    fragmentFAQTransaction.commit();

                    return true;
                case R.id.navigation_notifications:
                    AkunFragment akunFragment = new AkunFragment();
                    FragmentTransaction fragmentAkunTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentAkunTransaction.replace(R.id.inti, akunFragment);
                    fragmentAkunTransaction.commit();

                    return true;
            }
            return false;
        }

    };

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        BerandaFragment berandaFragment = new BerandaFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.inti, berandaFragment);
        fragmentTransaction.commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshview);
// set color schemes on refresh view
        swipeRefreshLayout.setColorSchemeResources(R.color.colorBiru,R.color.colorPrimary);
// implement refresh listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                BerandaFragment berandaFragment1 = new  BerandaFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.inti,new BerandaFragment());
                fragmentTransaction.commit();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }
}
