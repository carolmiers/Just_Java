/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */
package com.example.android.just_java ;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.net.Uri;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.text.StringCharacterIterator;

import static android.R.attr.name;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   }
    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100){
            //Show an error message as a toast
            Toast.makeText(this,"You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            //Nothing else to display so come out of the programme
          return;
    }
        else{
        quantity = quantity + 1;}
        display (quantity);
/**
 * This method is called when the minus button is clicked
 */
    }
    public void decrement (View view) {
        if (quantity == 1){
            //Show the number of coffees ordered on the screen
            Toast.makeText(this,"You cannot order less than one cup of coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
        quantity = quantity - 1;}
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
     * This method is called when the order button is clicked and the result of the order is printed
          it takes the result of the CheckBox and puts it in a boolean variable, hascream.
     /** It also takes the result of chocolate and puts this in the order as true or false
        and it takes the Name entered and puts this on the order on the screen.
      */
        public void submitOrder(View view ) {

            CheckBox haswhippedcream = (CheckBox)findViewById(R.id.toppings_checkbox);
            boolean hascream = haswhippedcream.isChecked();

            CheckBox haschocolate = (CheckBox)findViewById(R.id.toppings_checkbox_chocolate);
            boolean haschoc = haschocolate.isChecked();

            EditText orderName =(EditText)findViewById(R.id.name);

            String namehere = orderName.getText().toString();

            int price = calculatePrice(hascream, haschoc);
            Log.v("MainActivity","This is the name" + namehere );

            //**displayMessage(createOrderSummary(price,hascream,haschoc,namehere));
            String emailpriceMessage = createOrderSummary(price,hascream,haschoc,namehere);


            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(intent.EXTRA_TEXT,emailpriceMessage);
            intent.putExtra(intent.EXTRA_SUBJECT,"This is the order from JustJava for " + namehere );
            if (intent.resolveActivity(getPackageManager()) != null);
            {
                startActivity(intent);
            }}


//*
  //   * Calculates the price of the order.
    // *
     /*  @return total price
     */
    private int calculatePrice(boolean withcream, boolean withchoc) {
        int basePrice = 5;
        if (withcream) {
            basePrice = basePrice + 1;
        }
        if (withchoc) {
            basePrice = basePrice +2;
        }
        return basePrice * quantity;
    }

    /** This method gets the checked state from checkbox and stores it in a variable
     private boolean orderwhippedcream (boolean hascream) {
     CheckBox haswhippedcream = findViewById(R.id.toppings_checkbox);
     return haswhippedcream ;
     */
    /**private boolean topping(Boolean hascream) {
        CheckBox haswhippedcream = ((CheckBox) findViewById(R.id.toppings_checkbox));
        hascream = haswhippedcream.isChecked();
        return hascream;
    }

    /** Displays the name, quantity and @param price on the screen
     Displays the result of the checkbox @param hascream as true or false
     Displays the result of the checkbox @param haschoc as true or false
     * Create a summary
     */
   private String createOrderSummary(int price, boolean hascream, boolean haschoc, String namehere)    {
       String PriceMessage= "Name:" + namehere;
       PriceMessage += "\nAdd whipped cream? " + hascream;
       PriceMessage += "\nAdd chocolate? " + haschoc;
       PriceMessage += "\nQuantity: " + quantity;
       PriceMessage += "\nTotal = $" + price + "\nThank you!";

       return(PriceMessage);
       }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
        
    }

    /** This method extends the gap between the checkbox and the text
     * thanks to stackoverflow post
      */
   //final float scale  = this.getResources().getDisplayMetrics().density;
    //checkBox.setPadding(checkBox)
}