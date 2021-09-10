package com.example.myappuserslist1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//Переход к активности добовления пользователя по кнопке в верхней части основной активности
public class AddUserActivity extends AppCompatActivity {

    EditText editTextUserName;
    EditText editTextUserLastName;
    EditText editTextPhone;
    Button formAddUserBtnAdd;
    Button formAddUserBtnDelete;
    User user;
    boolean editUser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        //Передаём серелизованный объект между классами
        user = (User) getIntent().getSerializableExtra("user");
        //Находим элементы управления на активности
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextUserLastName = findViewById(R.id.editTextUserLastName);
        editTextPhone = findViewById(R.id.editTextPhone);
        formAddUserBtnAdd = findViewById(R.id.formAdduserBtnAdd);
        formAddUserBtnDelete = findViewById(R.id.formAdduserBtnDelete);
        if (user != null){
            formAddUserBtnAdd.setText("Изменить");
            editTextUserName.setText(user.getUserName());
            editTextUserLastName.setText(user.getUserLastName());
            editTextPhone.setText(user.getPhone());
            editUser = true;
        }else{
            formAddUserBtnAdd.setText("Добавить");
            user = new User();
            editUser = false;
        }

        formAddUserBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editUser){
                    user.setUserName(editTextUserName.getText().toString());
                    user.setUserLastName(editTextUserLastName.getText().toString());
                    user.setPhone(editTextPhone.getText().toString());

                    Users users = new Users(AddUserActivity.this);
                    users.updateUser(user, user.getUuid());
                }else{
                    User user = new User();
                    user.setUserName(editTextUserName.getText().toString());
                    user.setUserLastName(editTextUserLastName.getText().toString());
                    user.setPhone(editTextPhone.getText().toString());

                    Users users = new Users(AddUserActivity.this);
                    users.addUser(user);
                }
                onBackPressed();
            }
        });

        formAddUserBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editUser){
                    Users users = new Users(AddUserActivity.this);
                    users.deleteUser(user.getUuid());
                }else{
                    user = null;
                }
                onBackPressed();
            }
        });

    }
}