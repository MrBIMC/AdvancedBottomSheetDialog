package com.pavelsikun.bottomsheetdialog;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Pavel Sikun on 07.06.16.
 */
@SuppressWarnings("unchecked")
public abstract class AbsBottomSheetDialog<T extends AbsBottomSheetDialog> {

    private static final String KEY_SAVED_STATE_TOKEN = "bsd_saved_state_token";

    private View dialogView;
    private ImageView iconView;
    private TextView titleView;
    private TextView messageView;

    private BottomSheetDialogFragmentDelegate dialog;

    private Context context;

    public AbsBottomSheetDialog(Context context) {
        this.context = context;

        dialogView = LayoutInflater.from(context).inflate(getLayout(), null);
        dialog = new BottomSheetDialogFragmentDelegate();
        dialog.setView(dialogView);

        iconView = findView(R.id.bsd_icon);
        titleView = findView(R.id.bsd_title);
        messageView = findView(R.id.bsd_message);
    }

    @LayoutRes
    protected abstract int getLayout();


    public T setMessage(@StringRes int message) {
        return setMessage(string(message));
    }

    public T setMessage(CharSequence message) {
        messageView.setVisibility(View.VISIBLE);
        messageView.setText(message);
        return (T) this;
    }

    public T setTitle(@StringRes int title) {
        return setTitle(string(title));
    }

    public T setTitle(CharSequence title) {
        titleView.setVisibility(View.VISIBLE);
        titleView.setText(title);
        return (T) this;
    }

    public T setIcon(Bitmap bitmap) {
        iconView.setVisibility(View.VISIBLE);
        iconView.setImageBitmap(bitmap);
        return (T) this;
    }

    public T setIcon(Drawable drawable) {
        iconView.setVisibility(View.VISIBLE);
        iconView.setImageDrawable(drawable);
        return (T) this;
    }

    public T setIcon(@DrawableRes int iconRes) {
        iconView.setVisibility(View.VISIBLE);
        iconView.setImageResource(iconRes);
        return (T) this;
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    public T setIconTintColor(int iconTintColor) {
        iconView.setColorFilter(iconTintColor);
        return (T) this;
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    public T setTitleColor(int titleColor) {
        titleView.setTextColor(titleColor);
        return (T) this;
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    public T setMessageColor(int messageColor) {
        messageView.setTextColor(messageColor);
        return (T) this;
    }

    public T setTitleGravity(int gravity) {
        titleView.setGravity(gravity);
        return (T) this;
    }

    public T setMessageGravity(int gravity) {
        messageView.setGravity(gravity);
        return (T) this;
    }

    public T setTopColor(@ColorInt int topColor) {
        findView(R.id.bsd_color_area).setBackgroundColor(topColor);
        return (T) this;
    }

    public T setTopColorRes(@ColorRes int topColoRes) {
        return setTopColor(color(topColoRes));
    }

    /*
     * You should call method saveInstanceState on handler object and then use saved info to restore
     * your dialog in onRestoreInstanceState. Static methods wasDialogOnScreen and getDialogId will
     * help you in this.
     */
    public T setInstanceStateHandler(int id, SaveStateHandler handler) {
        handler.handleDialogStateSave(id, this);
        return (T) this;
    }

    public T setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
        return (T) this;
    }

    public T setSavedInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            boolean hasSavedStateHere =
                    savedInstanceState.keySet().contains(KEY_SAVED_STATE_TOKEN) &&
                            savedInstanceState.getSerializable(KEY_SAVED_STATE_TOKEN) == getClass();
            if (hasSavedStateHere) {
                restoreState(savedInstanceState);
            }
        }
        return (T) this;
    }

    public T show() {
        dialog.show(((AppCompatActivity) context).getSupportFragmentManager(), dialog.getTag());
        return (T) this;
    }

    public T create() {
        return (T) this;
    }

    protected BottomSheetDialogFragmentDelegate getDialogDelegate() {
        return dialog;
    }

    public void dismiss() {
        dialog.dismiss();
    }

    void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_SAVED_STATE_TOKEN, getClass());
    }

    void restoreState(Bundle savedState) {
    }

    boolean isShowing() {
        return dialog != null && dialog.isVisible();
    }

    protected String string(@StringRes int res) {
        return dialogView.getContext().getString(res);
    }

    protected int color(@ColorRes int colorRes) {
        return ContextCompat.getColor(getContext(), colorRes);
    }

    protected Context getContext() {
        return dialogView.getContext();
    }

    protected <ViewClass extends View> ViewClass findView(int id) {
        return (ViewClass) dialogView.findViewById(id);
    }

    protected class CloseOnClickDecorator implements View.OnClickListener {

        private View.OnClickListener clickListener;

        protected CloseOnClickDecorator(View.OnClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onClick(v);
            }
            dismiss();
        }
    }
}