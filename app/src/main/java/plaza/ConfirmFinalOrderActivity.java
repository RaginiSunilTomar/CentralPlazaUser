package plaza;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sagar.abt.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import plaza.Prevalent.Prevalent;

public class ConfirmFinalOrderActivity extends AppCompatActivity {
    private EditText nameEditText,phoneEditText,addressEditText,cityEditText;
    private Button confirmButton;
    private String totalAmount="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);
        totalAmount=getIntent().getStringExtra("Total Price");
        Toast.makeText(this, "Total Price = " + totalAmount + " $ ", Toast.LENGTH_SHORT).show();

        confirmButton=(Button)findViewById(R.id.confirm_final_order_btn);
        nameEditText=(EditText)findViewById(R.id.shippment_name);
        phoneEditText=(EditText)findViewById(R.id.shippment_phone_number);
        addressEditText=(EditText)findViewById(R.id.shippment_address);
        cityEditText=(EditText)findViewById(R.id.shippment_city);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Check();
            }
        });



    }

    private void Check()
    {
        if (TextUtils.isEmpty(nameEditText.getText().toString()))
        {
            Toast.makeText(this, "Please Provide Your Full Name", Toast.LENGTH_SHORT).show();
        }
        else  if (TextUtils.isEmpty(phoneEditText.getText().toString()))
        {
            Toast.makeText(this, "Please Provide Your Mobile Number", Toast.LENGTH_SHORT).show();
        }
        else  if (TextUtils.isEmpty(addressEditText.getText().toString()))
        {
            Toast.makeText(this, "Please Provide Your Address", Toast.LENGTH_SHORT).show();
        }
        else  if (TextUtils.isEmpty(cityEditText.getText().toString()))
        {
            Toast.makeText(this, "Please Provide Your City Name", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ConfirmOrder();
        }
    }

    private void ConfirmOrder()
    {
        final String savecurrentdate,savecurrentime;
        Calendar calfordate= Calendar.getInstance();
        SimpleDateFormat currentdate = new SimpleDateFormat("MMM dd, YYYY");

        savecurrentdate = currentdate.format(calfordate.getTime());


        SimpleDateFormat currentime = new SimpleDateFormat("HH:mm:ss a");

        savecurrentime = currentime.format(calfordate.getTime());

        final DatabaseReference ordersRef= (DatabaseReference) FirebaseDatabase.getInstance().getReference()
                .child("Orders")
                .child(Prevalent.currentOnlineUser.getPhone());
        HashMap<String,Object> ordersMap=new HashMap<>();
        ordersMap.put("totalAmount",totalAmount);
        ordersMap.put("name",nameEditText.getText().toString());
        ordersMap.put("phone",phoneEditText.getText().toString());
        ordersMap.put("address",addressEditText.getText().toString());
        ordersMap.put("city",cityEditText.getText().toString());
        ordersMap.put("date",savecurrentdate);
        ordersMap.put("time",savecurrentime);

        ordersMap.put("state","not shipped");

        ordersRef.updateChildren(ordersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
             if (task.isSuccessful())
             {
                 FirebaseDatabase.getInstance().getReference()
                         .child("Cart List")
                         .child("User View")
                         .child(Prevalent.currentOnlineUser.getPhone())
                         .removeValue()
                         .addOnCompleteListener(new OnCompleteListener<Void>() {
                             @Override
                             public void onComplete(@NonNull Task<Void> task)
                             {
                                 if (task.isSuccessful())
                                 {
                                     Toast.makeText(ConfirmFinalOrderActivity.this, "Your Final Order Has been Placed Successfully", Toast.LENGTH_SHORT).show();

                                     Intent intent= new Intent(getApplicationContext(), HomeActivity.class);
                                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                     startActivity(intent);
                                     finish();
                                 }
                             }
                         });
             }


            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ConfirmFinalOrderActivity.this, CartActivity.class);
        startActivity(intent);
    }
}
