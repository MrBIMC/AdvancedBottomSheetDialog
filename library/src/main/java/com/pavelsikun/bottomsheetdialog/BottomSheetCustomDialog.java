package com.pavelsikun.bottomsheetdialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Pavel Sikun on 08.06.16.
 */

public class BottomSheetCustomDialog extends AbsBottomSheetDialog<BottomSheetCustomDialog> {

    private View addedView;
    private InstanceStateManager instanceStateManager;

    public BottomSheetCustomDialog(Context context) {
        super(context);
    }

    public BottomSheetCustomDialog setView(@LayoutRes int layout) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        ViewGroup parent = findView(R.id.ld_custom_view_container);
        addedView = inflater.inflate(layout, parent, true);
        return this;
    }

    public BottomSheetCustomDialog setView(View customView) {
        ViewGroup container = findView(R.id.ld_custom_view_container);
        container.addView(customView);
        addedView = customView;
        return this;
    }

    public BottomSheetCustomDialog configureView(ViewConfigurator configurator) {
        if (addedView == null) {
            throw new IllegalStateException(string(R.string.ex_msg_dialog_view_not_set));
        }
        configurator.configureView(addedView);
        return this;
    }

    public BottomSheetCustomDialog setListener(int viewId, View.OnClickListener listener) {
        return setListener(viewId, false, listener);
    }

    public BottomSheetCustomDialog setListener(int viewId, boolean dismissOnClick, View.OnClickListener listener) {
        if (addedView == null) {
            throw new IllegalStateException(string(R.string.ex_msg_dialog_view_not_set));
        }
        View.OnClickListener clickListener = dismissOnClick ?
                new CloseOnClickDecorator(listener) :
                listener;
        findView(viewId).setOnClickListener(clickListener);
        return this;
    }

    public BottomSheetCustomDialog setInstanceStateManager(InstanceStateManager instanceStateManager) {
        this.instanceStateManager = instanceStateManager;
        return this;
    }

    @Override
    void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        instanceStateManager.saveInstanceState(outState);
    }

    @Override
    void restoreState(Bundle savedState) {
        super.restoreState(savedState);
        instanceStateManager.restoreInstanceState(savedState);
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_custom;
    }

    public interface ViewConfigurator {
        void configureView(View v);
    }

    public interface InstanceStateManager {
        void saveInstanceState(Bundle outState);
        void restoreInstanceState(Bundle savedState);
    }
}