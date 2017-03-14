package com.pavelsikun.bottomsheetdialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Pavel Sikun on 07.06.16.
 */

public class BottomSheetDialogFragmentDelegate extends BottomSheetDialogFragment {

    private DialogInterface.OnDismissListener dismissListener;

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

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(dismissListener != null) {
            dismissListener.onDismiss(dialog);
        }
    }

    private View contentView;

    public void setView(View view) {
        this.contentView = view;
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        setRetainInstance(true);

        try {
            ((ViewGroup) contentView.getParent()).removeView(contentView);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

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

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener dismissListener) {
        this.dismissListener = dismissListener;
    }
}
