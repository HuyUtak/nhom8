package com.gmail.hthong.myapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.gmail.hthong.myapp.TaiKhoanFragment;
import com.gmail.hthong.myapp.TracNghiemFragment;

/**
 *
 */
public class viewpagerAdapter extends FragmentStatePagerAdapter {
    public viewpagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch (position){
            case 0:
                return new com.gmail.hthong.myapp.TrangChuFragment();
            case 1:
                return new com.gmail.hthong.myapp.TaiLieuFragment();
            case 2:
                return new TracNghiemFragment();
            case 3:
                return new TaiKhoanFragment();
            default:
                return new com.gmail.hthong.myapp.TrangChuFragment();
        }
        //return  null;
    }

    @Override
    public int getCount()
    {
        return 5;
    }

}
