package com.pavelsikun.bottomsheetdialog;

import android.os.Bundle;
import android.util.SparseArray;

import java.lang.ref.WeakReference;

/**
 * Created by Pavel Sikun on 08.06.16.
 */

public class SaveStateHandler {

    private static final String KEY_DIALOG_ID = "id";

    private SparseArray<WeakReference<AbsBottomSheetDialog<?>>> handledDialogs;

    public SaveStateHandler() {
        handledDialogs = new SparseArray<>();
    }

    public void saveInstanceState(Bundle outState) {
        for (int index = handledDialogs.size() - 1; index >= 0; index--) {
            WeakReference<AbsBottomSheetDialog<?>> dialogRef = handledDialogs.valueAt(index);
            if (dialogRef.get() == null) {
                handledDialogs.remove(index);
                continue;
            }
            AbsBottomSheetDialog<?> dialog = dialogRef.get();
            if (dialog.isShowing()) {
                dialog.onSaveInstanceState(outState);
                outState.putInt(KEY_DIALOG_ID, handledDialogs.keyAt(index));
                return;
            }
        }
    }

    void handleDialogStateSave(int id, AbsBottomSheetDialog<?> dialog) {
        handledDialogs.put(id, new WeakReference<AbsBottomSheetDialog<?>>(dialog));
    }

    public static boolean wasDialogOnScreen(Bundle savedInstanceState) {
        return savedInstanceState.keySet().contains(KEY_DIALOG_ID);
    }

    public static int getSavedDialogId(Bundle savedInstanceState) {
        return savedInstanceState.getInt(KEY_DIALOG_ID, -1);
    }
}