package plaza;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sagar.abt.R;

import java.util.ArrayList;

public class Catalogue extends AppCompatActivity {


    private GridviewAdapter mAdapter;
     ArrayList<String> listProduct;
    private ArrayList<Integer> listFlag;

    private GridView gridView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue2);

        prepareList();

        // prepared arraylist and passed it to the Adapter class
        mAdapter = new GridviewAdapter(this,listProduct, listFlag);

        // Set custom adapter to gridview
        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(mAdapter);

        // Implement On Item click listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                switch(position) {
                    case 0:

                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/open?id=1-7NZi3y_F4vUqR_nunsYgEhOjAqgMB7D"));
                        startActivity(browserIntent);


                        break;
                    case 1:

                        Intent browserIntent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/open?id=1-CUg0uqfVt9VCVfzQUSiD2xbSlp9SFFY"));
                        startActivity(browserIntent1);


                        break;


                    case 2:

                        Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/open?id=1pHQrkYA8zVAHrhRtPLa3T2xmoQ9muQn5"));
                        startActivity(browserIntent2);


                        break;



                    case 3:

                        Intent browserIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/open?id=1WiBtZoy3M05oVLC2dw8TnBp-DBoN19ua"));
                        startActivity(browserIntent3);


                        break;

                    case 4:

                        Intent browserIntent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/open?id=1P-tiAC7hff_7GYpCuVbCbj6uYAkpWRz8"));
                        startActivity(browserIntent4);


                        break;

                    case 5:

                        Intent browserIntent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/open?id=1-hPH_BJvMR-g6lWBu0vha4TTXFdMnkoB"));
                        startActivity(browserIntent5);


                        break;

                    case 6:

                        Intent browserIntent6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/open?id=1vRy_wCTBe4AM3aKjxgVUjn40azdXR64-"));
                        startActivity(browserIntent6);


                        break;

                    case 7:

                        Intent browserIntent7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/open?id=1mp8ogAB7rwNxIIgFSr_XmJGw-Ej4aqA2"));
                        startActivity(browserIntent7);


                        break;


                    case 8:

                        Intent browserIntent8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/open?id=1JN1BSwoSoJrAdpGkAs1OWZU9J4aKtXKk"));
                        startActivity(browserIntent8);


                        break;


                    case 9:

                        Intent browserIntent9 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/open?id=1LlFkIfMa8ZpV0H3HegP-XZAc0mRUZ4We"));
                        startActivity(browserIntent9);


                        break;


                    case 10:

                        Intent browserIntent10 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/open?id=1bwIFjbmJGypUmBEDJAuyPvGmINtYQh65"));
                        startActivity(browserIntent10);


                        break;


                    case 11:

                        Intent browserIntent11 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/open?id=1HGIgUSXmS9X4VlFq-soMlKWQPrwO-Jk5"));
                        startActivity(browserIntent11);


                        break;

                    case 12:

                        Intent browserIntent12 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/open?id=1vu3_3T35APCb6NBrKFZ7eWZkatVPKbB-"));
                        startActivity(browserIntent12);


                        break;

                    case 13:

                        Intent browserIntent13 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/open?id=1C_VDLPYzt2MDfqqk-QlTAdID1iqwfEl8"));
                        startActivity(browserIntent13);


                        break;






                }




















































                Toast.makeText(getApplicationContext(), mAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void prepareList()
    {
        listProduct = new ArrayList<String>();

        listProduct.add("airconditioner");
        listProduct.add("audiosystem");
        listProduct.add("fanone");
        listProduct.add("fan");
        listProduct.add("fridge");
        listProduct.add("semiautomatic");
        listProduct.add("smarttv");
        listProduct.add("washingmachine");
        listProduct.add("water");
        listProduct.add("waterheater");
        listProduct.add("topload");
        listProduct.add("microwave");
        listProduct.add("lightbulb");

        listFlag = new ArrayList<Integer>();
        listFlag.add(R.drawable.airconditioner);
        listFlag.add(R.drawable.audiosystem);
        listFlag.add(R.drawable.fanone);
        listFlag.add(R.drawable.fan);
        listFlag.add(R.drawable.fridge);
        listFlag.add(R.drawable.semiautomatic);
        listFlag.add(R.drawable.smarttv);
        listFlag.add(R.drawable.washingmachine);
        listFlag.add(R.drawable.water);
        listFlag.add(R.drawable.waterheater);
        listFlag.add(R.drawable.topload);
        listFlag.add(R.drawable.microwave);
        listFlag.add(R.drawable.lightbulb);

    }



}
