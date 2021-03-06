package com.frodo.app.android.simple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.frodo.android.app.simple.R;
import com.frodo.app.android.core.UIView;
import com.frodo.app.android.core.toolbox.FragmentScheduler;
import com.frodo.app.android.ui.activity.FragmentContainerActivity;
import com.frodo.app.android.ui.fragment.AbstractBaseFragment;
import com.squareup.picasso.Picasso;

/**
 * Created by frodo on 2015/9/15.
 */
public class SplashFragment extends AbstractBaseFragment {
    @Override
    public UIView createUIView(Context context, LayoutInflater inflater, ViewGroup container) {
        return new UIView(this, inflater, container, R.layout.layout_splash) {
            ImageView ad;

            @Override
            public void initView() {
                ad = (ImageView) getRootView().findViewById(R.id.ad);
                Picasso.with(getAndroidContext())
                        .load("http://b.hiphotos.baidu.com/image/pic/item/d009b3de9c82d15815bba0ce830a19d8bc3e4290.jpg")
                        .into(ad);
            }

            @Override
            public void registerListener() {
                ad.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FragmentScheduler.nextFragment(((FragmentContainerActivity) getActivity()), MovieFragment.class, null, true);
                    }
                }, 5000);
            }
        };
    }
}
