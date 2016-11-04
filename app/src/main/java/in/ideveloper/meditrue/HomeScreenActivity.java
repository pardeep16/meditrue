package in.ideveloper.meditrue;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class HomeScreenActivity extends AppCompatActivity {

    GridView gridView;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        gridView=(GridView)findViewById(R.id.gridViewHome);
        HomwGridViewAdapter homwGridViewAdapter=new HomwGridViewAdapter();
        gridView.setAdapter(homwGridViewAdapter);
        Toolbar toolbar=(Toolbar)findViewById(R.id.tool1);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawer=(DrawerLayout)findViewById(R.id.drawer_layout);



        NavigationView navigation=(NavigationView)findViewById(R.id.navigation_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawer.closeDrawers();
                switch (menuItem.getItemId()) {
                    default:
                        return false;
                }

            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(HomeScreenActivity.this,AddMedicineRemainder.class));
                        break;
                    case 1:
                        startActivity(new Intent(HomeScreenActivity.this,ReminderListActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(HomeScreenActivity.this,UploadFileViewActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(HomeScreenActivity.this,ReminderListActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(HomeScreenActivity.this,ViewFiles.class));
                        break;
                    default:
                        Toast.makeText(HomeScreenActivity.this, "Default", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()){
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
