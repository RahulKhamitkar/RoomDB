package com.rahul.roomdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import androidx.room.Room;

import com.rahul.roomdb.Adapter.UserAdapter;
import com.rahul.roomdb.database.AppDatabase;
import com.rahul.roomdb.database.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);


        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"production")
                .allowMainThreadQueries()
                .build();
        List<User> users = db.userDao().getAllUsers();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new UserAdapter(users);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void add(View view) {
        Intent addUserIntent = new Intent(getApplicationContext(),AddedUserActivity.class);
        startActivity(addUserIntent);
    }
}
