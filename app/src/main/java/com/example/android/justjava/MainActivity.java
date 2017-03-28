package com.example.android.justjava;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.id.edit;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 2;

    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_me_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_me_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText text = (EditText) findViewById(R.id.name_view);
        String str = text.getText().toString();

        int price = calculatePrice(quantity, hasChocolate, hasWhippedCream);
        String priceMessage = "Name : " + str;
        priceMessage += "\nPrice : $" + price + " for " + quantity + " cups of coffee";
        priceMessage += "\ncontains whipped cream? " + hasWhippedCream;
        priceMessage += "\ncontains chocolate? " + hasChocolate;
        priceMessage += "\nTHANK YOU!";
        displayMessage(priceMessage);

    }

    public void submitOrder2(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6,-122.3?z=11"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        final int REQUEST_IMAGE_CAPTURE = 1;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
//        Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
//        intent.putExtra(MediaStore.EXTRA_MEDIA_FOCUS,
//                MediaStore.Audio.Artists.ENTRY_CONTENT_TYPE);
//        intent.putExtra(MediaStore.EXTRA_MEDIA_ARTIST, intent.getStringExtra(MediaStore.EXTRA_MEDIA_ARTIST));
//        intent.putExtra(SearchManager.QUERY, intent.getStringExtra(MediaStore.EXTRA_MEDIA_ARTIST));
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
    }

    private int calculatePrice(int num, boolean choc, boolean whippedCream) {
        int price = 0;
        if (choc && whippedCream)
            price = num * (5 + 2 + 1);
        else if (choc)
            price = num * (5 + 2);
        else if (whippedCream)
            price = num * (5 + 1);
        else
            price = num * 5;
        return price;
    }

    public void increment(View view) {
        if (quantity >= 100) {
            Toast.makeText(this, "Limit exceeded! ", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity <= 1) {
            Toast.makeText(this, "Come on! Order atleast a cup :P ", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        display(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}