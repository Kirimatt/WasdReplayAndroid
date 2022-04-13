package com.kirimatt.wasdAndroid.views.interfaces;

import android.widget.AbsListView;

public interface CustomOnScrollListener extends AbsListView.OnScrollListener {
    @Override
    default void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }
}
