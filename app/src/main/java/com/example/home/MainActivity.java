package com.example.home;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import com.example.home.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.home.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private int menuItemsCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        // Обработчик для кнопки "Add Device"
        Button addDeviceButton = binding.navView.findViewById(R.id.add_device);
        addDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuItemsCount++; // Увеличиваем количество пунктов меню
                addMenuItems(binding.navView); // Обновляем меню
                Toast.makeText(MainActivity.this, "Добавлен новый пункт меню!", Toast.LENGTH_SHORT).show();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Удаляем все статические элементы из меню
        navigationView.getMenu().clear();

        // Добавление элементов в NavigationView динамически
        addMenuItems(navigationView);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home) // Укажите только необходимые элементы
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void addMenuItems(NavigationView navigationView) {
        Menu menu = navigationView.getMenu();
        menu.clear(); // Очищаем меню перед добавлением новых пунктов
        for (int i = 0; i < menuItemsCount; i++) {
            int id = View.generateViewId(); // Генерация уникального идентификатора
            String title = "Dynamic Item " + (i + 1); // Заголовок для каждого элемента
            menu.add(Menu.NONE, id, i, title).setIcon(R.drawable.ic_launcher_foreground); // Замените на ваше имя файла
        }

        // Пример обработки нажатий на динамически добавленные элементы
        navigationView.setNavigationItemSelectedListener(item -> {
            Toast.makeText(MainActivity.this, "Clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
            return true; // Обработка завершена
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}