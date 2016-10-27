package com.aldawoudy.movies;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.aldawoudy.movies.di.MoviesComponent;

/**
 * Created by Ismail on 10/27/16.
 */
public class BaseActivity extends AppCompatActivity {

  protected Toolbar mToolbar = null;

  protected Toolbar getToolbar() {
    if (mToolbar == null) {
      mToolbar = (Toolbar) findViewById(R.id.toolbar);
      if (mToolbar != null) {
        setTitle(null);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(null);
        mToolbar.setSubtitle(null);
      }
    }
    return mToolbar;
  }

  @Override
  public void setContentView(int layoutResID) {
    super.setContentView(layoutResID);
    getToolbar();
  }

  public int replaceFragment(Fragment fragment, String tag, int anchor, boolean addToBackStack) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    if (addToBackStack) {
      fragmentTransaction.addToBackStack(tag);
    }
    fragmentTransaction.replace(anchor, fragment, tag);
    return fragmentTransaction.commit();
  }

  public void updateTitle(int resId) {
    updateTitle(getString(resId));
  }

  public void updateTitle(CharSequence title) {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(title);
    setSupportActionBar(toolbar);
  }

  public MoviesComponent getComponent() {
    return ((MoviesApplication) getApplication()).getComponent();
  }
}
