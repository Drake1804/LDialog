package com.drake1804.ldialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.drake1804.ldialog.base.BasePresenter;

/**
 * Created by Pavel.Shkaran on 5/10/2017.
 */

class MainPresenter extends BasePresenter<MainView> {

    private final int ANIMATION_DURATION = 350;

    void revealShow(View dialogView, boolean state, final Dialog dialog, View fab) {

        final View view = dialogView.findViewById(R.id.dialog);
        int width = view.getWidth();
        int height = view.getHeight();
        int endRadius = (int) Math.hypot(width, height);
        int cx = (int) (fab.getX() + (fab.getWidth() / 2));
        int cy = (int) (fab.getY()) + fab.getHeight() + 56;

        if (state) {
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, endRadius);
            view.setVisibility(View.VISIBLE);
            revealAnimator.setDuration(ANIMATION_DURATION);
            revealAnimator.start();
        } else {
            Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius, 0);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    dialog.dismiss();
                    view.setVisibility(View.INVISIBLE);
                }
            });
            anim.setDuration(ANIMATION_DURATION);
            anim.start();
        }
    }
}
