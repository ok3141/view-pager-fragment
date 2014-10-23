package com.shamanland.viewpagerfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

public class ViewPagerHelper implements ViewPager.OnPageChangeListener {
    private final FragmentManager mFragmentManager;
    private final ViewPager mViewPager;
    private int mSelectedPage;

    public ViewPagerHelper(FragmentManager fragmentManager, ViewPager viewPager) {
        mFragmentManager = fragmentManager;
        mViewPager = viewPager;
        mSelectedPage = -1;
    }

    @Override
    public void onPageSelected(int position) {
        Fragment previous = findViewPagerChildFragment(mFragmentManager, mViewPager, mSelectedPage);
        if (previous instanceof ViewPagerFragment) {
            ((ViewPagerFragment) previous).onDeselected();
        }

        Fragment current = findViewPagerChildFragment(mFragmentManager, mViewPager, position);
        if (current instanceof ViewPagerFragment) {
            ((ViewPagerFragment) current).onSelected();
        }

        mSelectedPage = position;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // empty
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // empty
    }

    /**
     * Find fragment by position inside ViewPager provided.
     *
     * @param manager  manager
     * @param pager    ViewPager instance
     * @param position position
     * @return may be null
     */
    public static Fragment findViewPagerChildFragment(FragmentManager manager, ViewPager pager, int position) {
        if (pager == null) {
            return null;
        }

        String tag = "android:switcher:" + pager.getId() + ":" + position;
        return manager.findFragmentByTag(tag);
    }

    /**
     * Get current fragment from ViewPager
     *
     * @param manager manager
     * @param pager   ViewPager instance
     * @return may be null
     */
    public static Fragment getViewPagerCurrentFragment(FragmentManager manager, ViewPager pager) {
        if (pager == null) {
            return null;
        }

        return findViewPagerChildFragment(manager, pager, pager.getCurrentItem());
    }
}
