package com.example.chris.week6daily4.view.barcode;


import com.example.chris.week6daily4.view.main.MainContract;

import javax.inject.Inject;

/**
 * Created by Admin on 11/29/2017.
 */

public class BarcodePresenter implements BarcodeContract.Presenter
{
    BarcodeContract.View view;
    public static final String TAG = BarcodePresenter.class.getSimpleName() + "_TAG";
    
    @Inject
    public BarcodePresenter()
    {
    
    }
    
    @Override
    public void attachView(BarcodeContract.View view)
    {
        this.view = view;
    }

    @Override
    public void detachView()
    {
        this.view = null;
    }
}
