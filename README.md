
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-easydatabinding-green.svg?style=true)](https://android-arsenal.com/details/1/4106)

# easydatabinding
This project goal is to create activity fragment and views, with less code to be more efficient on the view models


# Version : 0.6.6
# Goal

I used DataBinding for a few weeks since Google allows us to use it.

For now i saw a lot of duplicated code to instantiate an activity or a fragment.
So I make this library to avoid boilerplate, and you will be able to focus on your code .


## Thanks

Very useful lib I use :

https://github.com/evant/binding-collection-adapter/


It handles the databinding inside the recyclerview => No adapter to write :)

## How to use it

Gradle root :

```groovy
repositories {
    maven {
        url  "http://dl.bintray.com/joxad/maven"
    }
}
```

Gradle project :

```groovy
compile "com.joxad.easydatabinding:lib:$currentVersion"
```



### Fast Sample

With the library an activity will be :


```java
public class ActivityMain extends ActivityBase<ActivityMainBinding,ActivityMainVM> {

    @Override
    public int data() {
        return joxad.easydatabinding.sample.BR.activityMainVM;
    }

    @Override
    public int layoutResources() {
        return R.layout.activity_main;
    }

    @Override
    public ActivityMainBaseVM baseActivityVM(ActivityMainBinding binding) {
        return new ActivityMainVM(this, binding);
    }

}
```

And your VM :

```java
public class ActivityMainVM extends ActivityBaseVM<ActivityMain, ActivityMainBinding> {
    /***
     * @param activity
     * @param binding
     */
    public ActivityMainVM(ActivityMain activity, ActivityMainBinding binding) {
        super(activity, binding);
    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }
}
```


### More

To handle Result there are useful interfaces you can put in your VM's class of your activity



## Notes about the DataBinding

- Each Activity or Fragment is linked to a viewmodel who will expose the data to the view (layout) and handle some specific fucntionnalities


- ViewModel will also handle some treatments :

    - WS Calls . Database to update the recyclerview of the layout
    - activityresult of activity (for facebook . google login for example)

So in a very general case, code of an activity will look like this :

### Activity

For example, in class DB, we have to do

-  layout
-  class activity
-  class view model linked to the activity



#### Layout

First of all we need something like this for the layout

```xml
<?xml version="1.0" encoding="utf-8"?>

<layout xmlns:android="http:..schemas.android.com.apk.res.android"
    xmlns:app="http:..schemas.android.com.apk.res-auto"
    xmlns:tools="http:..schemas.android.com.tools">

    <data>


        <variable
            name="activityXXXViewModel"
            type="package.to.your.viewmodel.ActivityXXXViewModel" .>
    </data>

    <android.support.design.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        tools:bindingContext=".core.MainActivity">

        <android.support.design.widget.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:theme="@style.AppTheme.AppBarOverlay">

            <android.support.v7.widget.Toolbar
                android:id="@+id.toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr.actionBarSize"
                android:background="?attr.colorPrimary"
                app:popupTheme="@style.AppTheme.PopupOverlay" .>

        </android.support.design.widget.AppBarLayout>


        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_behavior="@string.appbar_scrolling_view_behavior">



        </RelativeLayout>


    </android.support.design.widget.CoordinatorLayout>
</layout>

```

Then Comes our view model :

### ViewModel : XXXActivityViewModel

```java

public class ActivityXXXViewModel extends BaseObservable implements ViewModel {
    private static final String TAG = ActivityXViewModel.class.getSimpleName();


    private final Context context;
    private final ActivityXXXBinding binding;
    §/***
     * @param context
     */
    public ActivityXXXViewModel(ActivityXXX context, ActivityXXXBinding binding) {
        this.context = context;
        this.binding = binding;
    }

    @Override
    public void onDestroy() {

    }

}

```

### Activity : XXXActivity

It will just manage to put the viewmodel into the activity


```java

public class XXXActivity extends AppCompatActivity {

    /**
    * Generated class by Android when used DataBinding
    **/
    ActivityXXXBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_xxx);
        setSupportActionBar(binding.toolbar);
        ActivityXXXViewModel activityXXXViewModel = new ActivityXXXViewModel(this);
        binding.setActivityXXXViewModel(activityXXXViewModel);
        binding.xxxRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}

```

