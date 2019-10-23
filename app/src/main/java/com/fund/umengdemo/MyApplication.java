package com.fund.umengdemo;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        //初始化友盟SDK
        UMShareAPI.get(this);//初始化sd
        //开启debug模式，方便定位错误，具体错误检查方式可以查看
        //http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = true;
        //微信
       // PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //新浪微博(第三个参数为回调地址)
        PlatformConfig.setSinaWeibo("2159065491", "8b314d8fed123832de92a7725f99b409","http://sns.whalecloud.com/sina2/callback");
        //QQ
        PlatformConfig.setQQZone("1109898071", "setj4sl4tyc3rXyb");

    }
}
