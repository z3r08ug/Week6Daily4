package com.example.chris.week6daily4.view.main;


import com.example.chris.week6daily4.util.BasePresenter;
import com.example.chris.week6daily4.util.BaseView;

/**
 * Created by Admin on 11/29/2017.
 */

public interface MainContract
{
    //methods for main activity
    interface View extends BaseView
    {
        void showProgress(String progress);
    }

    interface Presenter extends BasePresenter<View>
    {
    
    }
}
