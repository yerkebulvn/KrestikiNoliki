package com.myproject.krestiknolik;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity { // Бұл SplashActivity экранынан MainActivity үздіксіз ауысуды қамтамасыз етеді.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        /* Мәтін Көріністерін Анимациялау:
        TextView және textView2 анимацияланған, олар төменнен жоғары қарай сырғып, өшеді.
        TranslationY сипаты мәтін көріністерін тігінен жылжытады.
        Альфа қасиеті мөлдірлікті басқарады.
        Анимациялар үшін кідірістер мен ұзақтықтар көрсетілген.*/

        TextView textView = findViewById(R.id.textView3);
        TextView textView2 = findViewById(R.id.textView2);
            textView.setAlpha(0f);
            textView2.setAlpha(0f);

        textView.animate()
                .translationY(textView.getHeight())
                .alpha(1f)
                .setStartDelay(1000)
                .setDuration(1200);

        textView2.animate()
                .translationY(textView.getHeight())
                .alpha(1f)
                .setStartDelay(1500)
                .setDuration(1000);

        ImageView imageView = findViewById(R.id.imageView3);
        imageView.setAlpha(0f);
        imageView.animate()
                .translationY(textView.getHeight())
                .alpha(1f)
                .setDuration(800);

        /* MainActivity өтіңіз:
        4 секундтық кідірістен кейін (4000 миллисекунд) Шашырау Белсенділігі Негізгі Әрекетке ауысады.
        FLAG_ACTIVITY_NO_ANIMATION жалаушасы анимациясыз тегіс өтуді қамтамасыз етеді.
        Finish () әдісі шашырау әрекетінің артқы дестеден жойылуын қамтамасыз етеді.*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }
        }, 4000);

    }
}
