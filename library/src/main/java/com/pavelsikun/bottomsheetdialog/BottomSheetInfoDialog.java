package com.pavelsikun.bottomsheetdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * Created by Pavel Sikun on 07.06.16.
 */

public class BottomSheetInfoDialog extends AbsBottomSheetDialog<BottomSheetInfoDialog> {

    private static final String STORAGE = "ld_dont_show";

    private static final String KEY_DONT_SHOW_AGAIN = "key_dont_show_again";

    private CheckBox cbDontShowAgain;

    private int infoDialogId;

    public BottomSheetInfoDialog(Context context) {
        super(context);
    }

    {
        cbDontShowAgain = findView(R.id.ld_cb_dont_show_again);
        infoDialogId = -1;
    }

    public BottomSheetInfoDialog setNotShowAgainOptionEnabled(int dialogId) {
        infoDialogId = dialogId;
        cbDontShowAgain.setVisibility(View.VISIBLE);
        return this;
    }

    @Override
    public BottomSheetInfoDialog show() {
        if (infoDialogId == -1) {
            return super.show();
        }

        boolean shouldShowDialog = !storage(getContext()).getBoolean(String.valueOf(infoDialogId), false);
        if (shouldShowDialog) {
            BottomSheetDialogFragmentDelegate dialog = super.create().getDialogDelegate();
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    boolean notShow = cbDontShowAgain.isChecked();
                    storage(getContext()).edit().putBoolean(String.valueOf(infoDialogId), notShow).commit();
                    dismiss();
                }
            });
            return super.show();
        } else {
            return super.create();
        }
    }

    @Override
    void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_DONT_SHOW_AGAIN, cbDontShowAgain.isChecked());
    }

    @Override
    void restoreState(Bundle savedState) {
        super.restoreState(savedState);
        cbDontShowAgain.setChecked(savedState.getBoolean(KEY_DONT_SHOW_AGAIN));
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_info;
    }

    public static void reset(Context context, int dialogId) {
        storage(context).edit().putBoolean(String.valueOf(dialogId), false).commit();
    }

    private static SharedPreferences storage(Context context) {
        return context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
    }
}