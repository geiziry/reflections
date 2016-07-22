package com.sunshine.exampleandroid.android.sunshine;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater findMenuItems=getMenuInflater();
        findMenuItems.inflate(R.menu.detail,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.action_settings)
        {
            startActivity(new Intent(this,SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_activity, new PlaceholderFragment())
                    .commit();


        }
    }

    public static class PlaceholderFragment extends Fragment{

        private String detail_string;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView=inflater.inflate(R.layout.fragment_detail_activity,container,false);

            Intent intent = getActivity().getIntent();
            if (intent!=null &&intent.hasExtra("Selecteditem")) {
               detail_string = intent.getStringExtra("Selecteditem");
                ((TextView)rootView.findViewById(R.id.detailed_weather))
                        .setText(detail_string);
           }

            return rootView;
        }

    }

}
