package com.alium.niboexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alium.nibo.NiboConstants;
import com.alium.nibo.NiboPickerActivity;
import com.alium.nibo.NiboStyle;
import com.alium.nibo.models.NiboSelectedPlace;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchPickerFragment();
            }
        });
    }


    private void launchPickerFragment() {
        Intent intent = new Intent(this, NiboPickerActivity.class);
        NiboPickerActivity.NiboPickerBuilder config = new NiboPickerActivity.NiboPickerBuilder()
                .setSearchBarTitle("Search for an area")
                .setConfirmButtonTitle("Pick here bish")
                .setMarkerPinIconRes(R.drawable.ic_place)
                .setStyleEnum(NiboStyle.CUSTOM)
                .setStyleFileID(R.raw.retro);
        NiboPickerActivity.setBuilder(config);
        startActivityForResult(intent, 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == 200) {
            NiboSelectedPlace selectedPlace = data.getParcelableExtra(NiboConstants.RESULTS_SELECTED);
            Toast.makeText(this, selectedPlace.getPlaceAddress(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Error getting images", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}