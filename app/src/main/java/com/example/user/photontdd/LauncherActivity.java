package com.example.user.photontdd;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.example.user.photontdd.fragments.BaseFragment;
import com.example.user.photontdd.fragments.GridFragment;
/**
 * Created by tSravani on 5/14/2017.
 */

public class LauncherActivity extends AppCompatActivity implements BaseFragment.CommListener {

    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.container, new BaseFragment()).addToBackStack("basefrag").commit();
    }

    public void genGrid(int w, int h) {
        GridFragment fragment = GridFragment.newInstance(w,h);
        manager.beginTransaction().replace(R.id.container, fragment).addToBackStack("grid").commit();
    }
}
