package id.sch.smktelkom_mlg.privateassignment.xirpl413.movie;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import id.sch.smktelkom_mlg.privateassignment.xirpl413.movie.adapter.DataAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl413.movie.adapter.SourceAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl413.movie.adapter.TigaAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl413.movie.fragment.DuaFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl413.movie.fragment.SatuFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl413.movie.fragment.TigaFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SourceAdapter.ISourceAdapter, DataAdapter.IDataAdapter, TigaAdapter.ITigaAdapter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       /* Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences getProfs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                boolean isFirstStart = getProfs.getBoolean("firstStart", true);
                if (isFirstStart){
                    startActivity (new Intent(MainActivity.this, SatuFagment.class));
                    SharedPreferences.Editor e = getProfs.edit();
                    e.putBoolean("firstStart", false);
                    e.apply();
                }
            }
        });

        thread.start();*/
        changePage(R.id.nav_camera);
        navigationView.setCheckedItem(R.id.nav_camera);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        changePage(id);
        return true;


    }

    private void changePage(int id) {
        Fragment fragment = null;

        if (id == R.id.nav_camera) {
            fragment = new SatuFragment();
            setTitle("Now Playing");
        } else if (id == R.id.nav_gallery) {
            fragment = new DuaFragment();
            setTitle("Upcoming");
        } else if (id == R.id.nav_new) {
            fragment = new TigaFragment();
            setTitle("Top Rate");
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.satu, fragment).commitNow();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void showArticles(String original_title, String overview) {

    }
}
