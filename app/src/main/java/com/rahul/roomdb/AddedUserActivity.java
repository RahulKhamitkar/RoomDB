package com.rahul.roomdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.room.Room;

import com.rahul.roomdb.database.AppDatabase;
import com.rahul.roomdb.database.User;


public class AddedUserActivity extends AppCompatActivity {

    private EditText mFirstName;
    private EditText mLastName;
    private EditText mMailId;
    private Button mSave;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addeduser);


        mFirstName = findViewById(R.id.firstName);
        mLastName = findViewById(R.id.lastName);
        mMailId = findViewById(R.id.mailId);
        mSave =findViewById(R.id.save);


        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"production")
                .allowMainThreadQueries()
                .build();

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = mFirstName.getText().toString().trim();
                String lastName = mLastName.getText().toString().trim();
                String mailId = mMailId.getText().toString().trim();

                db.userDao().insertAll(new User(firstname,lastName,mailId));

                startActivity(new Intent(AddedUserActivity.this,MainActivity.class));
            }
        });
    }
}
