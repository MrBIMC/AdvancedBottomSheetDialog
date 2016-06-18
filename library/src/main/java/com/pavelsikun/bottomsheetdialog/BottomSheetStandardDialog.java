package com.pavelsikun.bottomsheetdialog;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Button;

import static android.view.View.VISIBLE;

/**
 * Created by Pavel Sikun on 08.06.16.
 */

public class BottomSheetStandardDialog extends AbsBottomSheetDialog<BottomSheetStandardDialog> {

    private Button positiveButton;
    private Button negativeButton;
    private Button neutralButton;

    public BottomSheetStandardDialog(Context context) {
        super(context);
    }

    {
        positiveButton = findView(R.id.bsd_btn_yes);
        negativeButton = findView(R.id.bsd_btn_no);
        neutralButton = findView(R.id.bsd_btn_neutral);
    }

    public BottomSheetStandardDialog setPositiveButton(@StringRes int text, View.OnClickListener listener) {
        return setPositiveButton(string(text), listener);
    }

    public BottomSheetStandardDialog setPositiveButton(String text, @Nullable View.OnClickListener listener) {
        positiveButton.setVisibility(VISIBLE);
        positiveButton.setText(text);
        positiveButton.setOnClickListener(new CloseOnClickDecorator(listener));
        return this;
    }

    public BottomSheetStandardDialog setNegativeButtonText(@StringRes int text) {
        return setNegativeButton(string(text), null);
    }

    public BottomSheetStandardDialog setNegativeButtonText(String text) {
        return setNegativeButton(text, null);
    }

    public BottomSheetStandardDialog setNegativeButton(@StringRes int text, View.OnClickListener listener) {
        return setNegativeButton(string(text), listener);
    }

    public BottomSheetStandardDialog setNegativeButton(String text, @Nullable View.OnClickListener listener) {
        negativeButton.setVisibility(VISIBLE);
        negativeButton.setText(text);
        negativeButton.setOnClickListener(new CloseOnClickDecorator(listener));
        return this;
    }

    public BottomSheetStandardDialog setNeutralButtonText(@StringRes int text) {
        return setNeutralButton(string(text), null);
    }

    public BottomSheetStandardDialog setNeutralButtonText(String text) {
        return setNeutralButton(text, null);
    }

    public BottomSheetStandardDialog setNeutralButton(@StringRes int text, @Nullable View.OnClickListener listener) {
        return setNeutralButton(string(text), listener);
    }

    public BottomSheetStandardDialog setNeutralButton(String text, @Nullable View.OnClickListener listener) {
        neutralButton.setVisibility(VISIBLE);
        neutralButton.setText(text);
        neutralButton.setOnClickListener(new CloseOnClickDecorator(listener));
        return this;
    }

    public BottomSheetStandardDialog setButtonsColor(@ColorInt int color) {
        positiveButton.setTextColor(color);
        negativeButton.setTextColor(color);
        neutralButton.setTextColor(color);
        return this;
    }

    public BottomSheetStandardDialog setButtonsColorRes(@ColorRes int colorRes) {
        return setButtonsColor(color(colorRes));
    }

    public BottomSheetStandardDialog setOnButtonClickListener(View.OnClickListener listener) {
        return setOnButtonClickListener(true, listener);
    }

    public BottomSheetStandardDialog setOnButtonClickListener(boolean closeOnClick, View.OnClickListener listener) {
        View.OnClickListener clickHandler = closeOnClick ?
                new CloseOnClickDecorator(listener) :
                listener;
        positiveButton.setOnClickListener(clickHandler);
        neutralButton.setOnClickListener(clickHandler);
        negativeButton.setOnClickListener(clickHandler);
        return this;
    }

    public BottomSheetStandardDialog setPositiveButtonText(@StringRes int text) {
        return setPositiveButton(string(text), null);
    }

    public BottomSheetStandardDialog setPositiveButtonText(String text) {
        return setPositiveButton(text, null);
    }

    public BottomSheetStandardDialog setPositiveButtonColor(@ColorInt int color) {
        positiveButton.setTextColor(color);
        return this;
    }

    public BottomSheetStandardDialog setNegativeButtonColor(@ColorInt int color) {
        negativeButton.setTextColor(color);
        return this;
    }

    public BottomSheetStandardDialog setNeutralButtonColor(@ColorInt int color) {
        neutralButton.setTextColor(color);
        return this;
    }

    public BottomSheetStandardDialog setPositiveButtonColorRes(@ColorRes int colorRes) {
        return setPositiveButtonColor(color(colorRes));
    }

    public BottomSheetStandardDialog setNegativeButtonColorRes(@ColorRes int colorRes) {
        return setNegativeButtonColor(color(colorRes));
    }

    public BottomSheetStandardDialog setNeutralButtonColorRes(@ColorRes int colorRes) {
        return setNeutralButtonColor(color(colorRes));
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_standard;
    }
}