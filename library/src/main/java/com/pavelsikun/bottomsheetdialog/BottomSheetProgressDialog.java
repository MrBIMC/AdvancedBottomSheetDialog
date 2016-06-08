package com.pavelsikun.bottomsheetdialog;

import android.content.Context;

/**
 * Created by Pavel Sikun on 08.06.16.
 */

public class BottomSheetProgressDialog extends AbsBottomSheetDialog<BottomSheetProgressDialog> {

    public BottomSheetProgressDialog(Context context) {
        super(context);
    }


    {
        setCancelable(false);
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_progress;
    }
}