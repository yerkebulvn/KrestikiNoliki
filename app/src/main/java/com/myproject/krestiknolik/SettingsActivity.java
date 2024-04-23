package com.myproject.krestiknolik;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity { //  Вибрация мен қиындық деңгейіне байланысты артықшылықтарды өңдейді.

    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";

    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";

    private static final String PREFS_NAME = "vibration";
    private static final String PREF_VIBRATION = "TicVib";
    private boolean Vibration;
    private boolean isChecked;
    private SharedPreferences sharedPreferences;
    private TextView dif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Код Діріл параметрін ортақ теңшелімдерден алады.
        // Терезе жалаулары қолданбаны толық экран режимінде іске қосу үшін орнатылған.
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Vibration = preferences.getBoolean(PREF_VIBRATION, true);

        setContentView(R.layout.activity_settings);
        Window w = getWindow();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final Intent intent = getIntent();

        /* Switch (swit) вибрацияны қосу/өшіру үшін қолданылады.
            TextView (diff) ағымдағы қиындық деңгейінің белгісін көрсетеді.
            RelativeLayout (r3) қиындықты таңдау үшін қолданылады.
            ImageView (артқа) артқа жылжу үшін пайдаланылады.*/

        Switch swit = findViewById(R.id.swith2);
        swit.setChecked(Vibration);

        /* swit басылғанда, вибрация параметрін ауыстырады.
            Белгіленген жалауша коммутатор күйіне байланысты жаңартылады.
            Артықшылық SharedPreferences көмегімен сақталады.*/
        swit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Vibration) {
                    isChecked = false;
                    SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean(PREF_VIBRATION, isChecked);
                    editor.apply();
                } else {
                    isChecked = true;

                    SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean(PREF_VIBRATION, isChecked);
                    editor.apply();
                }
            }
        });

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        dif = findViewById(R.id.dif);
        dif.setText(getDiffLabel(sharedPreferences.getInt("key", 0)));

        RelativeLayout r3 = findViewById(R.id.r3);
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty();
            }
        });

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void difficulty() {
        String[] choices = new String[] {" Easy", " Medium", " Hard", " Impossible", " Random"};

        int selected = -1;

        switch (sharedPreferences.getInt("key", 0)) {
            case 1:
                selected = 0;
                break;
            case 2:
                selected = 1;
                break;
            case 3:
                selected = 2;
                break;
            case 4:
                selected = 3;
                break;
            case 5:
                selected = 4;
                break;
        }

        /* Қиындықты Таңдау Үшін AlertDialog жасаңыз:
            AlertDialog.Builder Ескерту диалогын құратын құрастырушы.
            Диалогтың тақырыбы " Қиындықты Таңдау.”
            Диалогтың екі түймесі бар:
            Оң Батырма ("Керемет"):
            Басқан кезде ол dif TextView ағымдағы қиындық белгісімен жаңартады (теңшелімдерден алынған).
            Диалог тоқтатылды.
            Теріс Батырма ("Болдырмау"):
            Басқан кезде диалог тоқтатылады.
            Диалог терезесінде сонымен қатар таңдау массивіне негізделген бір таңдау элементтері (қиындық деңгейінің параметрлері) көрсетіледі.
            Бастапқыда таңдалған қиындық таңдалған айнымалымен анықталады (сақталған артықшылық мәні негізінде).
            Қиындық деңгейі басылғанда, артықшылық мәні сәйкесінше жаңартылады.*/

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(getApplicationContext().getResources().getString(R.string.st_difficulty))
                .setPositiveButton(getApplicationContext().getResources().getString(R.string.mes_great), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dif.setText(getDiffLabel(sharedPreferences.getInt("key", 0)));
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(getApplicationContext().getResources().getString(R.string.dialog_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setSingleChoiceItems(choices, selected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        switch (which) {
                            case 0:
                                editor.putInt("key", 1);
                                break;
                            case 1:
                                editor.putInt("key", 2);
                                break;
                            case 2:
                                editor.putInt("key", 3);
                                break;
                            case 3:
                                editor.putInt("key", 4);
                                break;
                            case 4:
                                editor.putInt("key", 5);
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + which);
                        }
                        editor.apply();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private String getDiffLabel(int level) {
        String label = "";
        switch (level) {
            case 1:
                label = "Easy";
                break;
            case 2:
                label = "Medium";
                break;
            case 3:
                label = "Hard";
                break;
            case 4:
                label = "Impossible";
                break;
            case 5:
                label = "Random";
                break;
        }
        return label;
    }

}
