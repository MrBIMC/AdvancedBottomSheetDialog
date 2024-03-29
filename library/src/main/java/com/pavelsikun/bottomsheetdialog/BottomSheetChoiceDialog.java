package com.pavelsikun.bottomsheetdialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pavel Sikun on 07.06.16.
 */
public class BottomSheetChoiceDialog extends AbsBottomSheetDialog<BottomSheetChoiceDialog> {

    private static final String KEY_ITEM_CHECKED_STATES = "key_item_checked_states";

    private ListView choicesList;
    private TextView confirmButton;

    public BottomSheetChoiceDialog(Context context) {
        super(context);
    }

    {
        choicesList = findView(R.id.bsd_choices);
    }

    public <T> BottomSheetChoiceDialog setItems(T[] items, OnItemSelectedListener<T> itemSelectedListener) {
        return setItems(Arrays.asList(items), itemSelectedListener);
    }

    public <T> BottomSheetChoiceDialog setItems(List<T> items, OnItemSelectedListener<T> itemSelectedListener) {
        ArrayAdapter<T> adapter = new ArrayAdapter<>(getContext(),
                R.layout.item_simple_text, android.R.id.text1,
                items);
        return setItems(adapter, itemSelectedListener);
    }

    public <T> BottomSheetChoiceDialog setItems(ArrayAdapter<T> adapter, OnItemSelectedListener<T> itemSelectedListener) {
        choicesList.setOnItemClickListener(new ItemSelectedAdapter<>(itemSelectedListener));
        choicesList.setAdapter(adapter);
        return this;
    }

    public <T> BottomSheetChoiceDialog setItemsMultiChoice(T[] items, OnItemsSelectedListener<T> itemsSelectedListener) {
        return setItemsMultiChoice(items, null, itemsSelectedListener);
    }

    public <T> BottomSheetChoiceDialog setItemsMultiChoice(T[] items, boolean[] selectionState, OnItemsSelectedListener<T> itemsSelectedListener) {
        return setItemsMultiChoice(Arrays.asList(items), selectionState, itemsSelectedListener);
    }

    public <T> BottomSheetChoiceDialog setItemsMultiChoice(List<T> items, OnItemsSelectedListener<T> itemsSelectedListener) {
        return setItemsMultiChoice(items, null, itemsSelectedListener);
    }

    public <T> BottomSheetChoiceDialog setItemsMultiChoice(List<T> items, boolean[] selectionState, OnItemsSelectedListener<T> itemsSelectedListener) {
        ArrayAdapter<T> adapter = new ArrayAdapter<>(getContext(),
                R.layout.item_simple_text_multichoice, android.R.id.text1,
                items);
        return setItemsMultiChoice(adapter, selectionState, itemsSelectedListener);
    }

    public <T> BottomSheetChoiceDialog setItemsMultiChoice(ArrayAdapter<T> adapter, OnItemsSelectedListener<T> itemsSelectedListener) {
        return setItemsMultiChoice(adapter, null, itemsSelectedListener);
    }

    public <T> BottomSheetChoiceDialog setItemsMultiChoice(ArrayAdapter<T> adapter, boolean[] selectionState, OnItemsSelectedListener<T> itemsSelectedListener) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View confirmBtnContainer = inflater.inflate(R.layout.item_footer_confirm, null);
        confirmButton = (TextView) confirmBtnContainer.findViewById(R.id.bsd_btn_confirm);
        confirmButton.setOnClickListener(new ItemsSelectedAdapter<>(itemsSelectedListener));
        choicesList.addFooterView(confirmBtnContainer);

        ListView choicesList = findView(R.id.bsd_choices);
        choicesList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        choicesList.setAdapter(adapter);

        if (selectionState != null) {
            for (int i = 0; i < selectionState.length; i++) {
                choicesList.setItemChecked(i, selectionState[i]);
            }
        }

        return this;
    }

    public BottomSheetChoiceDialog setConfirmButtonText(@StringRes int text) {
        return setConfirmButtonText(string(text));
    }

    public BottomSheetChoiceDialog setConfirmButtonText(String text) {
        if (confirmButton == null) {
            throw new IllegalStateException(string(R.string.ex_msg_dialog_choice_confirm));
        }
        confirmButton.setText(text);
        return this;
    }

    public BottomSheetChoiceDialog setConfirmButtonColor(int color) {
        if (confirmButton == null) {
            throw new IllegalStateException(string(R.string.ex_msg_dialog_choice_confirm));
        }
        confirmButton.setTextColor(color);
        return this;
    }

    @Override
    void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (isMultiChoiceList()) {
            ListAdapter adapter = choicesList.getAdapter();
            boolean[] checkedStates = new boolean[adapter.getCount()];
            SparseBooleanArray checkedPositions = choicesList.getCheckedItemPositions();
            for (int i = 0; i < checkedPositions.size(); i++) {
                if (checkedPositions.valueAt(i)) {
                    checkedStates[checkedPositions.keyAt(i)] = true;
                }
            }
            outState.putBooleanArray(KEY_ITEM_CHECKED_STATES, checkedStates);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_choice;
    }

    private boolean isMultiChoiceList() {
        return choicesList.getChoiceMode() == AbsListView.CHOICE_MODE_MULTIPLE;
    }

    private class ItemSelectedAdapter<T> implements AdapterView.OnItemClickListener {

        private OnItemSelectedListener<T> adaptee;

        private ItemSelectedAdapter(OnItemSelectedListener<T> adaptee) {
            this.adaptee = adaptee;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (adaptee != null) {
                adaptee.onItemSelected(position, (T) parent.getItemAtPosition(position));
            }
            dismiss();
        }
    }

    public interface OnItemSelectedListener<T> {
        void onItemSelected(int position, T item);
    }

    private class ItemsSelectedAdapter<T> implements View.OnClickListener {

        private OnItemsSelectedListener<T> adaptee;

        private ItemsSelectedAdapter(OnItemsSelectedListener<T> adaptee) {
            this.adaptee = adaptee;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void onClick(View v) {
            if (adaptee != null) {
                SparseBooleanArray checkedItemPositions = choicesList.getCheckedItemPositions();
                List<T> selectedItems = new ArrayList<>(checkedItemPositions.size());
                List<Integer> selectedPositions = new ArrayList<>(checkedItemPositions.size());
                ListAdapter adapter = choicesList.getAdapter();
                for (int index = 0; index < adapter.getCount(); index++) {
                    if (checkedItemPositions.get(index)) {
                        selectedPositions.add(index);
                        selectedItems.add((T) adapter.getItem(index));
                    }
                }
                adaptee.onItemsSelected(selectedPositions, selectedItems);
            }
            dismiss();
        }
    }

    public interface OnItemsSelectedListener<T> {
        void onItemsSelected(List<Integer> positions, List<T> items);
    }
}