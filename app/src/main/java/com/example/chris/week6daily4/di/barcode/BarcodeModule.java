package com.example.chris.week6daily4.di.barcode;


import com.example.chris.week6daily4.view.barcode.BarcodePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Admin on 11/29/2017.
 */

@Module
public class BarcodeModule
{
    @Provides
    BarcodePresenter providerBarcodePresenter()
    {
        return new BarcodePresenter();
    }
}
