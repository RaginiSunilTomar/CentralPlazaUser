package plaza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.sagar.abt.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import plaza.Model.Products;
import plaza.Prevalent.Prevalent;

public class ProductDetailsActivity extends AppCompatActivity {

    private Button addtocartbtn;

    private ImageView productimage;
    private ElegantNumberButton elegantNumberButton;
    private TextView productprice,productdescription,productname;

    private String productID="",state="Normal";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pd);

        addtocartbtn=findViewById(R.id.pd_add_to_cart_button);

        elegantNumberButton = findViewById(R.id.number_btn);
        productimage = findViewById(R.id.imageView);
        productprice = findViewById(R.id.product_price_details);
        productdescription = findViewById(R.id.product_description_details);
        productname = findViewById(R.id.product_name_details);

        productID = getIntent().getStringExtra("pid");


        getproductdetails(productID);

        addtocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(state.equals("Order Placed") || state.equals("Order Shipped"))
                {
                    Toast.makeText(ProductDetailsActivity.this, "You can add purchase more products,once your order is shipped or confirmed. ", Toast.LENGTH_LONG).show();
                }
                else
                {
                    addtocartlist();
                }
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        CheckOrderState();
    }

    private void addtocartlist() {

        String savecurrentime,savecurrentdate;

        Calendar calfordate= Calendar.getInstance();
        SimpleDateFormat currentdate = new SimpleDateFormat("MMM dd, YYYY");

        savecurrentdate = currentdate.format(calfordate.getTime());


        SimpleDateFormat currentime = new SimpleDateFormat("HH:mm:ss a");

        savecurrentime = currentime.format(calfordate.getTime());


        final DatabaseReference cartlistref= FirebaseDatabase.getInstance().getReference().child("Cart List");

        final HashMap<String,Object> cartmap = new HashMap<>();
        cartmap.put("pid",productID);
        cartmap.put("pname",productname.getText().toString());
        cartmap.put("price",productprice.getText().toString());
        cartmap.put("date",savecurrentdate);
        cartmap.put("time",savecurrentime);
        cartmap.put("quantity",elegantNumberButton.getNumber());
        cartmap.put("discount","");

        cartlistref.child("User View").child(Prevalent.currentOnlineUser.getPhone())
                .child("Products").child(productID)
                .updateChildren(cartmap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful())
                        {
                            cartlistref.child("Admin View").child(Prevalent.currentOnlineUser.getPhone())
                                    .child("Products").child(productID)
                                    .updateChildren(cartmap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(ProductDetailsActivity.this, "Added To Cart", Toast.LENGTH_SHORT).show();

                                            Intent intentcart= new Intent(getApplicationContext(), HomeActivity.class);
                                            startActivity(intentcart);
                                        }
                                    });

                        }
                    }
                });




    }

    private void getproductdetails(final String productID) {

        DatabaseReference productsRef= FirebaseDatabase.getInstance().getReference().child("Products");

        productsRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {
                    Products products = dataSnapshot.getValue(Products.class);

                    productname.setText(products.getPname());
                    productprice.setText(products.getPrice());
                    productdescription.setText(products.getDescription());
                    Picasso.get().load(products.getImage()).into(productimage);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void CheckOrderState()
    {
        DatabaseReference ordersRef;
        ordersRef=FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentOnlineUser.getPhone());
        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    String shippingState=dataSnapshot.child("state").getValue().toString();

                    if(shippingState.equals("shipped"))
                    {
                       state="Order Shipped";
                    }
                    else if (shippingState.equals("not shipped"))
                    {
                        state="Order Placed";
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
