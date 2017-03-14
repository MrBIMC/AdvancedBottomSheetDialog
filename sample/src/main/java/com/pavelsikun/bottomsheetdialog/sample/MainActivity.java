package com.pavelsikun.bottomsheetdialog.sample;

import android.os.AsyncTask;
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

public class MainActivity extends AppCompatActivity implements
        BottomSheetChoiceDialog.OnItemSelectedListener<String>,
        BottomSheetChoiceDialog.OnItemsSelectedListener<String>,
        View.OnClickListener {

    //This can be anything that identifies a dialog
    private static final int ID_CUSTOM_DIALOG = R.id.dialog_custom_view;
    private static final int ID_STANDARD_DIALOG = R.id.dialog_standard;
    private static final int ID_SINGLE_CHOICE_DIALOG = R.id.dialog_single_choice;
    private static final int ID_INFO_DIALOG = R.id.dialog_info;
    private static final int ID_MULTI_CHOICE_DIALOG = R.id.dialog_multi_choice;
    private static final int ID_TEXT_INPUT_DIALOG = R.id.dialog_text_input;
    private static final int ID_PROGRESS_DIALOG = R.id.dialog_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(ID_CUSTOM_DIALOG).setOnClickListener(this);
        findViewById(ID_INFO_DIALOG).setOnClickListener(this);
        findViewById(ID_MULTI_CHOICE_DIALOG).setOnClickListener(this);
        findViewById(ID_PROGRESS_DIALOG).setOnClickListener(this);
        findViewById(ID_SINGLE_CHOICE_DIALOG).setOnClickListener(this);
        findViewById(ID_STANDARD_DIALOG).setOnClickListener(this);
        findViewById(ID_TEXT_INPUT_DIALOG).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        displayDialog(v.getId(), null);
    }

    private void displayDialog(int id, Bundle savedInstanceState) {
        Toast.makeText(this, "showing dialog!", Toast.LENGTH_SHORT).show();

        switch (id) {
            case ID_CUSTOM_DIALOG:
                customDialog();
                break;
            case ID_INFO_DIALOG:
                infoDialog();
                break;
            case ID_MULTI_CHOICE_DIALOG:
                multiChoiceDialog();
                break;
            case ID_PROGRESS_DIALOG:
                progressDialog();
                break;
            case ID_SINGLE_CHOICE_DIALOG:
                choiceDialog();
                break;
            case ID_STANDARD_DIALOG:
                standardDialog();
                break;
            case ID_TEXT_INPUT_DIALOG:
                textDialog();
                break;
        }
    }

    public void infoDialog() {

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

    public void choiceDialog() {

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

    public void multiChoiceDialog() {

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

    public void customDialog() {
        new BottomSheetCustomDialog(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Indefinite progress dialog sample!")
                .setTopColor(ContextCompat.getColor(this, R.color.colorAccent))
                .setIconTintColor(ContextCompat.getColor(this, R.color.white))
                .setTitleColor(ContextCompat.getColor(this, R.color.white))
                .setView(R.layout.custom_view_demo)
                .show();
    }

    public void progressDialog() {
        final BottomSheetProgressDialog d = new BottomSheetProgressDialog(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Progress dialog sample!")
                .setTopColor(ContextCompat.getColor(this, R.color.colorAccent))
                .setIconTintColor(ContextCompat.getColor(this, R.color.white))
                .setTitleColor(ContextCompat.getColor(this, R.color.white))
                .setProgress(0)
                .setMaxProgress(100)
                .show();

//        d.setIndeterminate(true)  <- if you don't want to track progress

            new AsyncTask<Void, Integer, Long>() {

                @Override
                protected Long doInBackground(Void... voids) {
                    for(int i = 0; i < 100; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        publishProgress(i);
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Long aLong) {
                    d.dismiss();
                    super.onPostExecute(aLong);
                }

                @Override
                protected void onProgressUpdate(Integer... values) {
                    d.setProgress(values[0]);
                    super.onProgressUpdate(values);
                }

            }.execute();
    }

    public void standardDialog() {
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

    public void textDialog() {
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
