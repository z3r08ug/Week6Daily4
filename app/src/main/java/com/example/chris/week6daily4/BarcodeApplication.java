package com.example.chris.week6daily4;

import android.app.Application;
import android.content.Context;

import com.example.chris.week6daily4.di.app.AppComponent;
import com.example.chris.week6daily4.di.app.AppModule;
import com.example.chris.week6daily4.di.app.DaggerAppComponent;
import com.example.chris.week6daily4.di.barcode.BarcodeComponent;
import com.example.chris.week6daily4.di.barcode.BarcodeModule;
import com.example.chris.week6daily4.di.main.MainComponent;
import com.example.chris.week6daily4.di.main.MainModule;
import com.example.chris.week6daily4.view.main.MainActivity;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by chris on 12/14/2017.
 */

public class BarcodeApplication extends Application
{
    private AppComponent appComponent;
    private MainComponent mainComponent;
    private BarcodeComponent barcodeComponent;
    private RefWatcher refWatcher;
    
    @Override
    public void onCreate()
    {
        super.onCreate();
    
        if (LeakCanary.isInAnalyzerProcess(this))
        {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
    
        AppModule appModule = new AppModule();
        
        appComponent = DaggerAppComponent.builder()
                .appModule(appModule)
                .build();
    }
    
    public static RefWatcher getRefWatcher(Context context)
    {
        BarcodeApplication application = (BarcodeApplication) context.getApplicationContext();
        return application.refWatcher;
    }
    
    public static BarcodeApplication get(Context context)
    {
        return (BarcodeApplication) context.getApplicationContext();
    }
    
    public MainComponent getMainComponent()
    {
        mainComponent = appComponent.add(new MainModule());
        return mainComponent;
    }
    
    public void clearMainComponent()
    {
        mainComponent = null;
    }
    
    public BarcodeComponent getBarcodeComponent()
    {
        barcodeComponent = appComponent.add(new BarcodeModule());
        return barcodeComponent;
    }
    public void clearBarcodeComponent()
    {
        barcodeComponent = null;
    }
}
