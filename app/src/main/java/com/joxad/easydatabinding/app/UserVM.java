package com.joxad.easydatabinding.app;

import android.content.Context;
import android.databinding.Bindable;

import com.joxad.easydatabinding.DataBindable;
import com.joxad.easydatabinding.base.BaseVM;

/**
 * Created by josh on 13/04/16.
 */
@DataBindable
public class UserVM extends BaseVM<User> {

    public String name;
    /***
     * @param context
     * @param model
     */
    public UserVM(Context context, User model) {
        super(context, model);
    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }


    @Bindable
    public String getName(){
        return getModel().name;
    }

}
