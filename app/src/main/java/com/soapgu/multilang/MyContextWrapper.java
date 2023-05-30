package com.soapgu.multilang;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public abstract class MyContextWrapper {
    private static Locale currentLocale;
    public static void setLocale(Locale locale) {
        currentLocale = locale;
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static Context wrap(Context context) {
        if (currentLocale != null) {
            Resources res = context.getResources();
            Configuration configuration = res.getConfiguration();
            configuration.setLocale(currentLocale);
            context = context.createConfigurationContext(configuration);
        }
        return context;
    }
}
