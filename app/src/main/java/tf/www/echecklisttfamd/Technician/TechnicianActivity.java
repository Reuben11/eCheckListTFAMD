package tf.www.echecklisttfamd.Technician;

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

import tf.www.echecklisttfamd.Operator.AwaitingList;
import tf.www.echecklisttfamd.Operator.OperatorBuyOffList;
import tf.www.echecklisttfamd.Operator.OperatorEquipmentScanner;
import tf.www.echecklisttfamd.Operator.TermOfUseOperator;
import tf.www.echecklisttfamd.R;

public class TechnicianActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician);

        Toolbar toolbar = findViewById(R.id.technician_toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setElevation(4.0f);
        getSupportActionBar().setElevation(0);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.technician_drawer_layout);


        NavigationView navigationView = findViewById(R.id.technician_nav_view);
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

                            case R.id.technicianjr:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.master_container, JobAvailable.newInstance())
                                        .commit();
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
                .replace(R.id.master_container, TermOfUseTechnician.newInstance())
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
}
