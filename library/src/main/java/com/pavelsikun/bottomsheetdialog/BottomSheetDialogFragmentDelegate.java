package com.pavelsikun.bottomsheetdialog;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;

/**
 * Created by Pavel Sikun on 07.06.16.
 */

public class BottomSheetDialogFragmentDelegate extends BottomSheetDialogFragment {

    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if(newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };

    private View contentView;

    public void setView(View view) {
        this.contentView = view;
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();


        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            BottomSheetBehavior b = ((BottomSheetBehavior) behavior);
            b.setState(BottomSheetBehavior.STATE_EXPANDED);
            b.setBottomSheetCallback(bottomSheetCallback);
        }
    }
}
