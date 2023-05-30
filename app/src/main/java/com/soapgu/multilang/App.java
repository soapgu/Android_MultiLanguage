package com.soapgu.multilang;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.util.Locale;

public class App extends Application {
    public static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .tag("multi-lang")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        Logger.i("-----app start----");
        App.instance = this;
        MyContextWrapper.setLocale( Locale.SIMPLIFIED_CHINESE );
        MyContextWrapper.wrap(this.getApplicationContext());
        //this.updateResource(this.getApplicationContext());
        super.onCreate();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Logger.i("-------APP Config Changed--------");
        Logger.i("System Language:%s", newConfig.getLocales().get(0).getDisplayName() );
    }

}
