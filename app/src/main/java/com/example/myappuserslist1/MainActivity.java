package com.example.myappuserslist1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Класс основной Активности визуального интерфейса
public class MainActivity extends AppCompatActivity {
    //Добавляем в активность элемент представления recyclerView;
    RecyclerView recyclerView;
    //Добавляем в активность адаптер
    UserAdapter userAdapter;
    //Создаём список пользователей
    ArrayList<User> userList = new ArrayList<>();
    //Переменная для кнопки Добавления пользователя
    Button addUserBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Связываем создаваемую активность с layout-файлом activity_main.xml
        setContentView(R.layout.activity_main);
        //Связываем элемент recyclerView в представлении с активностью через его id
        recyclerView = findViewById(R.id.recyclerView);
        //Задаём recyclerView в виде списка для данной активности, указав в качестве контекста основную активность
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //Получаем список пользователей из класса Users
        addUserBtn = findViewById(R.id.addUserBtn);
        addUserBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
            startActivity(intent);
        });

    }

    private void recyclerViewInit(){
        Users users = new Users(MainActivity.this);
        userList = users.getUserList();
        //Создаём новый адаптер и передаём ему список пользователей для отображения
        userAdapter = new UserAdapter(userList);
        //Адаптер размещает строчки в recyclerView
        recyclerView.setAdapter(userAdapter);
    }

    //Обновляем пользователей на основной активности
    @Override
    public void onResume(){
        super.onResume();
        recyclerViewInit();
    }


    //Объект-держатель представления
    //Создание отдельного элемента списка
    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Добавляем TextView
        TextView itemTextView;
        User user;

        //Конструктор UserHolder
        public UserHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            //Вызов текущего экземпляра родительнского класса с указанием макета элемента
            super(layoutInflater.inflate(R.layout.single_item, viewGroup, false));
            //Связываем элемент типа TextView с itemTextView в представлении
            itemTextView = itemView.findViewById(R.id.itemTextView);
            //Задействуем интерфейс View.OnClickListener
            itemView.setOnClickListener(this);

        }
        //Помещаем в объект представления TextView имя пользователя
        public void bind(String userName, User user){
            this.user = user;
            //Передаём элементу TextView имя пользователя
            itemTextView.setText(userName);
        }

        //Метод, обрабатывающий клик на пользователе
        @Override
        public void onClick(View view) {
            //Интент для клика
            Intent intent = new Intent(MainActivity.this, AddUserActivity.class);//UserinfoActivity.class);
            //Передаём в интент данные о выбранном пользователе
            intent.putExtra("user", user);  //класс пользователя должен быть серилизуемым
            startActivity(intent);
        }
    }
    //описание класса типа Адаптер, связывающего представление с данными для этого представления
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        //Список пользователей в адаптере
        ArrayList<User> users;
        //Передаём конструктору список пользователей
        public UserAdapter(ArrayList<User> users) {
            this.users = users;
        }
        //Метод для передачи данных в элемент списка пользователей
        @NonNull
        @Override
        public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //Создаём LayoutInflater, который из содержимого layout-файла создаёт View-элемент
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            //Возвращаем данные для элемента с одним пользователем
            return new UserHolder(layoutInflater, parent);
        }
        //Метод для привязки объекта UserHolder к конкретному пользователю
        @Override
        public void onBindViewHolder(UserHolder holder, int position) {
            //Берём пользователя из списка по номеру позиции в списке
            User user = users.get(position);
            //Переводим пользователя в строку
            String userString = user.getUserName() + "\n" + user.getUserLastName();
            //Вызываем метод связывания и передаём в него пользователя
            holder.bind(userString, user);
        }
        //Возвращает общее количество пользователей
        @Override
        public int getItemCount() {
            return users.size();
        }
    }


}

