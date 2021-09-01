package com.example.myappuserslist1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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
    ArrayList<String> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Связываем создаваемую активность с layout-файлом activity_main.xml
        setContentView(R.layout.activity_main);
        //Создаём список пользователей
        for (int i = 0; i < 100; i++) {
            userList.add("Пользователь "+i);
        }
        //Связываем элемент recyclerView в представлении с активностью через его id
        recyclerView = findViewById(R.id.recyclerView);
        //Задаём recyclerView в виде списка для данной активности, указав в качестве контекста основную активность
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //Создаём новый адаптер и передаём ему список пользователей для отображения
        userAdapter = new UserAdapter(userList);
        //Адаптер размещает строчки в recyclerView
        recyclerView.setAdapter(userAdapter);
    }
    //Объект-держатель представления
    //Создание отдельного элемента списка
    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Добавляем TextView
        TextView itemTextView;
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
        public void bind(String userName){
            //Передаём элементу TextView имя пользователя
            itemTextView.setText(userName);
        }

        //Метод, обрабатывающий клик на пользователе
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(MainActivity.this, UserinfoActivity.class);
            startActivity(intent);
        }
    }
    //описание класса типа Адаптер, связывающего представление с данными для этого представления
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        //Список пользователей в адаптере
        ArrayList<String> users;
        //Передаём конструктору список пользователей
        public UserAdapter(ArrayList<String> users) {
            this.users = users;
        }
        //Метод для передачи данных в элемент списка пользователей
        @Override
        public UserHolder onCreateViewHolder( ViewGroup parent, int viewType) {
            //Создаём LayoutInflater, который из содержимого layout-файла создаёт View-элемент
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            //Возвращаем данные для элемента с одним пользователем
            return new UserHolder(layoutInflater, parent);
        }
        //Метод для привязки объекта UserHolder к конкретному пользователю
        @Override
        public void onBindViewHolder(UserHolder holder, int position) {
            //Берём пользователя из списка по номеру позиции в списке
            String user = users.get(position);
            //Вызываем метод связывания и передаём в него пользователя
            holder.bind(user);
        }
        //Возвращает общее количество пользователей
        @Override
        public int getItemCount() {
            return users.size();
        }
    }


}

