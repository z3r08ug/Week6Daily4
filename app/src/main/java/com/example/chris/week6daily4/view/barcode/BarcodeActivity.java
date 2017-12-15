package com.example.chris.week6daily4.view.barcode;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.chris.week6daily4.BarcodeApplication;
import com.example.chris.week6daily4.R;
import com.google.zxing.Result;

import javax.inject.Inject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class BarcodeActivity extends AppCompatActivity implements BarcodeContract.View, ZXingScannerView.ResultHandler
{
    public static final String TAG = BarcodeActivity.class.getSimpleName() + "_TAG";
    public static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    
    @Inject
    BarcodePresenter presenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        System.gc();
    
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        
        BarcodeApplication.get(this).getBarcodeComponent().inject(this);
        
        
        presenter.attachView(this);
    }
    
    private boolean checkPermission()
    {
        return ContextCompat.checkSelfPermission(BarcodeActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }
    
    @Override
    protected void onResume()
    {
        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkPermission())
            {
                if (scannerView == null)
                {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            }
            else
            {
                requestPermission();
            }
        }
    }
    
    private void requestPermission()
    {
        ActivityCompat.requestPermissions(BarcodeActivity.this, new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA);
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        scannerView.stopCamera();
        presenter.detachView();
    }
    
    @Override
    protected void onStop()
    {
        super.onStop();
        BarcodeApplication.get(this).clearBarcodeComponent();
    }
    
    @Override
    public void handleResult(final Result result)
    {
        final String scanResult = result.getText();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                scannerView.resumeCameraPreview(BarcodeActivity.this);
            }
        });
        builder.setNeutralButton("Visit", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(scanResult));
                try
                {
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e)
                {
                    Toast.makeText(BarcodeActivity.this, "No link to visit.", Toast.LENGTH_SHORT).show();
                    scannerView.resumeCameraPreview(BarcodeActivity.this);
                }
            }
        });
        builder.setMessage(scanResult);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    
    @Override
    public void showError(String error)
    {
        
    }
    
    @Override
    public void showProgress(String progress)
    {
        
    }
}
