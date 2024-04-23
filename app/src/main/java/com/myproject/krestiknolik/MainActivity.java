package com.myproject.krestiknolik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {
    private boolean Vibration;
    private static final String PREFS_NAME = "vibration";
    private static final String PREF_VIBRATION = "TicVib";

    /*OnCreate әдісі әрекет алғаш жасалған кезде шақырылады.
    Ол келесі тапсырмаларды орындайды:
    Мазмұн көрінісін activity_main ішінде анықталған орналасуға орнатады.
    ПАЙДАЛАНУШЫ ИНТЕРФЕЙСІНІҢ элементтерін (CardViews) олардың Идентификаторларын пайдаланып табу арқылы инициализациялайды.
    Жиындар Ai, FM Және параметрлер Карталарын Көру үшін "тыңдаушылар" түймесін басыңыз.
    GetWindow ().setFlags(...) line қолданбаның толық экран режимінде жұмыс істеуін қамтамасыз етеді.*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window w = getWindow(); // in Activity's onCreate() for instance
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        CardView Ai = findViewById(R.id.ai_mode);
        CardView FM = findViewById(R.id.friends_mode);

        /*Ai CardView:
            Басқан кезде Ол Атау Әрекетін бастайды.
        FM CardView:
            Басқан кезде Ол Екі Атау Әрекетін бастайды.
        параметрлер CardView:
            Басқан кезде Ол Параметрлер Әрекетін бастайды.*/

        Ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NameActivity.class);
                startActivity(intent);
            }
        });

        FM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TwoNameActivity.class);
                startActivity(intent);
            }
        });

        CardView settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    /*Артқа түймесі басылғанда, onBackPressed әдісі негізгі әрекетті бастау арқылы негізгі экранға өтеді.*/

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}
