package com.example.chris.week6daily4.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.chris.week6daily4.BarcodeApplication;
import com.example.chris.week6daily4.R;
import com.example.chris.week6daily4.util.SingletonSavesContext;
import com.example.chris.week6daily4.view.barcode.BarcodeActivity;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View
{
    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    
    @Inject
    MainPresenter presenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        BarcodeApplication.get(this).getMainComponent().inject(this);
        
        presenter.attachView(this);
        
        System.gc();
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        presenter.detachView();
        RefWatcher refWatcher = BarcodeApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
    
    @Override
    protected void onStop()
    {
        super.onStop();
        BarcodeApplication.get(this).clearMainComponent();
        RefWatcher refWatcher = BarcodeApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
    
    @Override
    public void showError(String error)
    {
        
    }
    
    @Override
    public void showProgress(String progress)
    {
        
    }
    
    public void onScan(View view)
    {
        startActivity(new Intent(this, BarcodeActivity.class));
    }
    
    public void onMemoryLeak(View view)
    {
        new MyAsyncTask().execute(this);
    }
    
    
    public class MyAsyncTask extends AsyncTask<Object, String, String>
    {
        private Context context;
        
        @Override
        protected String doInBackground(Object... params)
        {
            context = (Context)params[0];
            
            // Invoke the leak!
            SingletonSavesContext.getInstance().setContext(context);
            
            // Simulate long running task
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                Log.e(TAG, "doInBackground: ", e);
            }
            
            return "result";
        }
        
        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            
            Intent newActivity = new Intent(context, BarcodeActivity.class);
            startActivity(newActivity);
        }
    }
}
