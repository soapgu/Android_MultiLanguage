package com.soapgu.multilang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("MainActivity onCreate is %s", savedInstanceState == null ? "first" : "recreate");
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.tv_msg);
        Locale primaryLocale = this.getApplicationContext().getResources().getConfiguration().getLocales().get(0);
        String locale = primaryLocale.getDisplayName();
        textView.setText(locale);

        findViewById(R.id.button_switch).setOnClickListener( v -> {
            Locale target = MyContextWrapper.getCurrentLocale() == Locale.SIMPLIFIED_CHINESE ? Locale.US : Locale.SIMPLIFIED_CHINESE;
            MyContextWrapper.setLocale( target );
            MyContextWrapper.wrap(this.getApplicationContext());
            recreate();
        } );


    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MyContextWrapper.wrap(newBase));
    }

}