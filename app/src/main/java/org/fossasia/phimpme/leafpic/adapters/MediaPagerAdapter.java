package org.fossasia.phimpme.leafpic.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import org.fossasia.phimpme.leafpic.data.Media;
import org.fossasia.phimpme.leafpic.fragments.ImageFragment;

import java.util.ArrayList;

/**
 * Created by dnld on 18/02/16.
 */

public class MediaPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Media> media;
    private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    public MediaPagerAdapter(FragmentManager fm, ArrayList<Media> media) {
        super(fm);
        this.media = media;
    }


    @Override public Fragment getItem(int pos) {
        Media media = this.media.get(pos);
       return ImageFragment.newInstance(media);
    }

    @Override public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

    public void swapDataSet(ArrayList<Media> media) {
        this.media = media;
        notifyDataSetChanged();
    }

    @Override public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override public int getCount() {
        return media.size();
    }
}