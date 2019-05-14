package tf.www.echecklisttfamd.Operator;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import tf.www.echecklisttfamd.LoginActivity;
import tf.www.echecklisttfamd.R;

public class OperatorActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setElevation(4.0f);
        getSupportActionBar().setElevation(0);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(false);
                        // close drawer when item is tapped
                        drawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        switch (menuItem.getItemId()){

                            case R.id.jr:
                                SetTypeName("1");
                               getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.master_container, OperatorJobRequestList.newInstance())
                                        .commit();
                                break;
                            case R.id.buyoff:
                                SetTypeName("2");
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.master_container, OperatorBuyOffList.newInstance())
                                            .commit();
                                    break;
                            case R.id.logout:
                                startActivity(new Intent(OperatorActivity.this, LoginActivity.class));
                                finish();
                                break;
                        }
                        return false;
                    }
                });


        drawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.master_container, TermOfUseOperator.newInstance())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addFragment(Fragment fragment, boolean addToBackStack, String tag){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        if(addToBackStack){
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.master_container, fragment, tag);
        ft.commitAllowingStateLoss();
    }

    protected void SetTypeName(String type) {
        /* SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);*/
        SharedPreferences.Editor editor = getApplication().getSharedPreferences("Operator_Apps", MODE_PRIVATE).edit();
        editor.putString("type", type);
        editor.commit();
    }

    @Override
    public void onBackPressed() {

        Fragment f = getSupportFragmentManager().findFragmentById(R.id.master_container);
        if (f instanceof Device_Change_Setup_CheckList || f instanceof Buy_Off_Check_List || f instanceof OperatorScanner) {//the fragment on which you want to handle your back press
            /*Log.i("BACK PRESSED", "BACK PRESSED");*/
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.master_container, TermOfUseOperator.newInstance())
                    .commit();
        }
        else{
            super.onBackPressed();
        }
    }
}

