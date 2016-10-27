package com.aldawoudy.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import com.aldawoudy.movies.di.MoviesComponent;

/**
 * Created by Ismail on 10/27/16.
 */
public class BaseFragment extends Fragment {

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (!(getActivity() instanceof BaseActivity)) {
      throw new IllegalStateException("Fragment's activity must be BaseActivity");
    }
  }

  /**
   * Replace fragment int.
   *
   * @param fragment the fragment
   * @param tag the tag
   * @param anchor the anchor
   * @param addToBackStack the add to back stack
   * @return the int
   */
  public int replaceFragment(Fragment fragment, String tag, int anchor, boolean addToBackStack) {
    return ((BaseActivity) getActivity()).replaceFragment(fragment, tag, anchor, addToBackStack);
  }

  /**
   * Update title.
   *
   * @param resId the res id
   */
  public void updateTitle(int resId) {
    ((BaseActivity) getActivity()).updateTitle(resId);
  }

  /**
   * Gets base activity.
   *
   * @return the base activity
   */
  public BaseActivity getBaseActivity() {
    return (BaseActivity) getActivity();
  }

  /**
   * Gets component.
   *
   * @return the component
   */
  public MoviesComponent getComponent() {
    return getBaseActivity().getComponent();
  }

  protected int getColor(int resId) {
    return ContextCompat.getColor(getContext(), resId);
  }
}
