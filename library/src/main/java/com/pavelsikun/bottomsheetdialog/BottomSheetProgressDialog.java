package com.pavelsikun.bottomsheetdialog;

import android.content.Context;
import android.widget.ProgressBar;

/**
 * Created by Pavel Sikun on 08.06.16.
 */

public class BottomSheetProgressDialog extends AbsBottomSheetDialog<BottomSheetProgressDialog> {

    private ProgressBar progressBar;

    public BottomSheetProgressDialog(Context context) {
        super(context);
    }

    {
        setCancelable(false); //todo: it doesn't work!
        progressBar = findView(R.id.bsd_progressbar);
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_progress;
    }

    public BottomSheetProgressDialog setProgress(int progress) {
        progressBar.setIndeterminate(false);
        progressBar.setProgress(progress);
        return this;
    }

    public BottomSheetProgressDialog setIndeterminate(boolean isIndeterminate) {
        progressBar.setIndeterminate(isIndeterminate);
        return this;
    }

    public BottomSheetProgressDialog setMaxProgress(int maxProgress) {
        progressBar.setMax(maxProgress);
        return this;
    }
}