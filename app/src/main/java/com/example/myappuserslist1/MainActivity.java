package com.example.myappuserslist1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserAdapter userAdapter;
    ArrayList<String> userList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 100; i++) {
            userList.add("Пользователь "+i);
        }


        //Связываем элемент recyclerView в представлении с активностью через его id
        recyclerView = findViewById(R.id.recyclerView);
        //Задаём recyclerView в виде списка
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        userAdapter = new UserAdapter(userList);
        //Адаптер размещает строчки recyclerView
        recyclerView.setAdapter(userAdapter);



    }
    //Объект держатель представления
    private class UserHolder extends RecyclerView.ViewHolder{

        public UserHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {

            super(layoutInflater.inflate(R.layout.single_item, viewGroup, false));
        }
    }

    //Адаптер, привязывающий представления к данным этих представлений
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        ArrayList<String> users;
        //Передаём конструктору список элементов
        public UserAdapter(ArrayList<String> users) {
            this.users = users;
        }

        @NonNull
        @Override
        public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            return new UserHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull UserHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }


}

