package bwie.com.luning20170515;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.bwei.slidingmenu.SlidingMenu;
import com.bwei.slidingmenu.app.SlidingFragmentActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import bwie.com.luning20170515.frag.LeftFragment;

public class MainActivity extends SlidingFragmentActivity implements View.OnClickListener {

    private SlidingMenu slidingMenu;
    WindowManager windowManager;
    WindowManager.LayoutParams layoutParams;
     @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inteView();
        sideslip();
         initGrayBackgroud();

        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }
    View view;

    public void initGrayBackgroud() {
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

//        应用程序窗口。 WindowManager.LayoutParams.TYPE_APPLICATION
//        所有程序窗口的“基地”窗口，其他应用程序窗口都显示在它上面。
//        普通应用功能程序窗口。token必须设置为Activity的token，以指出该窗口属谁。

//        后面的view获得焦点
        layoutParams = new WindowManager.LayoutParams
                (WindowManager.LayoutParams.TYPE_APPLICATION,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        PixelFormat.TRANSPARENT);

        view = new View(this);

        view.setBackgroundResource(R.color.color_window);

    }


    // 日 夜切换
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainActivityEvent(Myvent event){
        System.out.println("isChecked = " + event.isWhite());

        if(event.isWhite()){
            // 日
            windowManager.removeViewImmediate(view);
        } else  {
            // true 夜
            windowManager.addView(view, layoutParams);

        }
        //对所有的控件取出,设置对应的图片
//        setView();
        //更改字体颜色
//        switchTextViewColor((ViewGroup) getWindow().getDecorView(),event.isWhite());
//
//        IndexFragment indexFragment = (IndexFragment) list.get(0);
//
//        indexFragment.changeMode(event.isWhite());
    }
    private void inteView() {
        findViewById(R.id.main_ibn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Log.e("","-------------");
    }

    private void sideslip() {
        LeftFragment leftFragment = new LeftFragment();
        setBehindContentView(R.layout.left);

        getSupportFragmentManager().beginTransaction().replace(R.id.left,leftFragment).commit();


        slidingMenu = getSlidingMenu();
        // 设置slidingmenu滑动的方式
        slidingMenu.setMode(SlidingMenu.LEFT);

        // 设置触摸屏幕的模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        // 设置阴影的宽度
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        // 设置slidingmenu边界的阴影图片
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        // 设置滑动菜单视图的宽度
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        slidingMenu.setFadeDegree(0.35f);





    }
}
