package com.pavelsikun.bottomsheetdialog.sample;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
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
import com.pavelsikun.bottomsheetdialog.sample.R;

public class MainActivity extends AppCompatActivity implements BottomSheetChoiceDialog.OnItemSelectedListener<String> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void infoDialog(View view) {

        BottomSheetInfoDialog dialog = new BottomSheetInfoDialog(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTopTitle("Info dialog sample!")
                .setTopColor(ContextCompat.getColor(this, R.color.darkBlueGrey))
                .setIconTintColor(ContextCompat.getColor(this, R.color.white))
                .setTopTitleColor(ContextCompat.getColor(this, R.color.white))
                .setMessage("Some random message that is supposed to give user some important info," +
                        " but since it's just a sample of a library, this message basically just " +
                        "shows some useless placeholder text");

        dialog.setNotShowAgainOptionEnabled(dialog.hashCode());
        dialog.show();
    }

    public void choiceDialog(View view) {

        String[] items = new String[]{"Apple", "Banana", "Peach"};

        new BottomSheetChoiceDialog(this)
                .setItems(items, this)
                .setIcon(R.mipmap.ic_launcher)
                .setTopTitle("Choice dialog sample!")
                .setTopColor(ContextCompat.getColor(this, R.color.darkRed))
                .setIconTintColor(ContextCompat.getColor(this, R.color.white))
                .setTopTitleColor(ContextCompat.getColor(this, R.color.white))
                .setMessage("Select the best fruit")
                .show();
    }

    public void customDialog(View view) {
        new BottomSheetCustomDialog(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTopTitle("Yo!")
                .setTopColor(Color.MAGENTA)
                .setMessage("Testing this shit out!")
                .show();
    }

    public void progressDialog(View view) {
        new BottomSheetProgressDialog(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTopTitle("Indefinite progress dialog sample!")
                .setTopColor(ContextCompat.getColor(this, R.color.colorAccent))
                .setIconTintColor(ContextCompat.getColor(this, R.color.white))
                .setTopTitleColor(ContextCompat.getColor(this, R.color.white))
                .show();
    }

    public void standardDialog(View view) {
        new BottomSheetStandardDialog(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTopTitle("Yo!")
                .setTopColor(Color.MAGENTA)
                .setMessage("Testing this shit out!")
                .show();
    }

    public void textDialog(View view) {
        new BottomSheetTextInputDialog(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTopTitle("Yo!")
                .setTopColor(Color.MAGENTA)
                .setMessage("Testing this shit out!")
                .show();
    }

    @Override
    public void onItemSelected(int position, String item) {
        Toast.makeText(this, "selected item #" + position + " value = " + item, Toast.LENGTH_LONG).show();
    }
}
