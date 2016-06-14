package com.pavelsikun.bottomsheetdialog.sample;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pavelsikun.bottomsheetdialog.BottomSheetChoiceDialog;
import com.pavelsikun.bottomsheetdialog.BottomSheetCustomDialog;
import com.pavelsikun.bottomsheetdialog.BottomSheetInfoDialog;
import com.pavelsikun.bottomsheetdialog.BottomSheetProgressDialog;
import com.pavelsikun.bottomsheetdialog.BottomSheetStandardDialog;
import com.pavelsikun.bottomsheetdialog.BottomSheetTextInputDialog;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomSheetChoiceDialog.OnItemSelectedListener<String>, BottomSheetChoiceDialog.OnItemsSelectedListener<String> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void infoDialog(View view) {

        BottomSheetInfoDialog dialog = new BottomSheetInfoDialog(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Info dialog sample!")
                .setTopColor(ContextCompat.getColor(this, R.color.darkBlueGrey))
                .setIconTintColor(ContextCompat.getColor(this, R.color.white))
                .setTitleColor(ContextCompat.getColor(this, R.color.white))
                .setMessage("Some random message that is supposed to give user some important info," +
                        " but since it's just a sample of a library, this message basically just " +
                        "shows some useless placeholder text")
                .setMessageColor(ContextCompat.getColor(this, R.color.white));

        dialog.setNotShowAgainOptionEnabled(2555);
        dialog.show();
    }

    public void choiceDialog(View view) {

        String[] items = new String[]{"Apple", "Banana", "Peach"};

        new BottomSheetChoiceDialog(this)
                .setItems(items, this)
                .setTopColor(ContextCompat.getColor(this, R.color.darkRed))
                .setIcon(R.mipmap.ic_launcher)
                .setIconTintColor(ContextCompat.getColor(this, R.color.white))
                .setTitle("Choice dialog sample!")
                .setTitleColor(ContextCompat.getColor(this, R.color.white))
                .setMessage("Select the best fruit")
                .setMessageColor(ContextCompat.getColor(this, R.color.white))
                .show();
    }

    public void multiChoiceDialog(View view) {

        String[] items = new String[]{"Apple", "Banana", "Peach"};

        new BottomSheetChoiceDialog(this)
                .setItemsMultiChoice(items, this)
                .setTopColor(ContextCompat.getColor(this, R.color.darkDeepOrange))
                .setIcon(R.mipmap.ic_launcher)
                .setIconTintColor(ContextCompat.getColor(this, R.color.white))
                .setTitle("Choice dialog sample!")
                .setTitleColor(ContextCompat.getColor(this, R.color.white))
                .setMessage("Select fruits you like")
                .setMessageColor(ContextCompat.getColor(this, R.color.white))
                .show();
    }

    public void customDialog(View view) {
        new BottomSheetCustomDialog(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Indefinite progress dialog sample!")
                .setTopColor(ContextCompat.getColor(this, R.color.colorAccent))
                .setIconTintColor(ContextCompat.getColor(this, R.color.white))
                .setTitleColor(ContextCompat.getColor(this, R.color.white))
                .show();
    }

    public void progressDialog(View view) {
//        BottomSheetProgressDialog d =
        new BottomSheetProgressDialog(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Indefinite progress dialog sample!")
                .setTopColor(ContextCompat.getColor(this, R.color.colorAccent))
                .setIconTintColor(ContextCompat.getColor(this, R.color.white))
                .setTitleColor(ContextCompat.getColor(this, R.color.white))
                .show();
    }

    public void standardDialog(View view) {
        new BottomSheetStandardDialog(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Standard dialog!")
                .setTopColor(ContextCompat.getColor(this, R.color.darkGreen))
                .setIconTintColor(ContextCompat.getColor(this, R.color.white))
                .setTitleColor(ContextCompat.getColor(this, R.color.white))
                .setPositiveButton("Yes!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setNegativeButton("No!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setNeutralButton("Tell me more", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();
    }

    public void textDialog(View view) {
        new BottomSheetTextInputDialog(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Indefinite progress dialog sample!")
                .setTopColor(ContextCompat.getColor(this, R.color.colorAccent))
                .setIconTintColor(ContextCompat.getColor(this, R.color.white))
                .setTitleColor(ContextCompat.getColor(this, R.color.white))
                .show();
    }

    @Override
    public void onItemSelected(int position, String item) {
        Toast.makeText(this, "selected item #" + position + " value = " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemsSelected(List<Integer> positions, List<String> items) {
        Toast.makeText(this, "selected items: " + items, Toast.LENGTH_LONG).show();
    }
}
