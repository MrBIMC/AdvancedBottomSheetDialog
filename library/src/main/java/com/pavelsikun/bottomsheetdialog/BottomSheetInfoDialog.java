package com.pavelsikun.bottomsheetdialog;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
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
    private Button confirmButton;

    private int infoDialogId;

    public BottomSheetInfoDialog(Context context) {
        super(context);
    }

    {
        cbDontShowAgain = findView(R.id.ld_cb_dont_show_again);
        confirmButton = findView(R.id.ld_btn_confirm);
        confirmButton.setOnClickListener(new CloseOnClickDecorator(null));
        infoDialogId = -1;
    }

    public BottomSheetInfoDialog setNotShowAgainOptionEnabled(int dialogId) {
        infoDialogId = dialogId;
        cbDontShowAgain.setVisibility(View.VISIBLE);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean notShow = cbDontShowAgain.isChecked();
                storage(getContext()).edit().putBoolean(String.valueOf(infoDialogId), notShow).apply();
                dismiss();
            }
        });
        return this;
    }

    public BottomSheetInfoDialog setConfirmButtonText(@StringRes int text) {
        return setConfirmButtonText(string(text));
    }

    public BottomSheetInfoDialog setConfirmButtonText(String text) {
        confirmButton.setText(text);
        return this;
    }

    public BottomSheetInfoDialog setConfirmButtonColor(int color) {
        confirmButton.setTextColor(color);
        return this;
    }

    @Override
    public Fragment show() {
        if (infoDialogId == -1) {
            return super.show();
        }

        boolean shouldShowDialog = !storage(getContext()).getBoolean(String.valueOf(infoDialogId), false);
        if (shouldShowDialog) {
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
        storage(context).edit().putBoolean(String.valueOf(dialogId), false).apply();
    }

    private static SharedPreferences storage(Context context) {
        return context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
    }
}