package com.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.foodapp.common.Common;
import com.foodapp.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    EditText editPhone, editPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        editPassword = findViewById(R.id.editPassword);
        editPhone = findViewById(R.id.editPhone);
        btnSignIn = findViewById(R.id.btnSignIn);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // check user in database

                        if (dataSnapshot.child(editPhone.getText().toString()).exists()) {

                            mDialog.dismiss();
                            // get user info
                            User user = dataSnapshot.child(editPhone.getText().
                                    toString()).getValue(User.class);

                            if (user.getPassword().equals(editPassword.getText().toString())) {

                                Intent dash = new Intent(SignIn.this, DashboardActivity.class);
                                startActivity(dash);

                                Toast.makeText(SignIn.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                                Common.currentUser = user;
                                //startActivity(home);
                                finish();
                            } else {
                                Toast.makeText(SignIn.this, "Name Or Password Incorrect !", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this,
                                    "User Does Not Exist In Our Database", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}