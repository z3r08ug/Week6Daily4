package com.example.chris.week6daily4.di.barcode;



import com.example.chris.week6daily4.view.barcode.BarcodeActivity;

import dagger.Subcomponent;

/**
 * Created by Admin on 11/29/2017.
 */

@Subcomponent(modules = BarcodeModule.class)
public interface BarcodeComponent
{
    void inject(BarcodeActivity barcodeActivity);
}
