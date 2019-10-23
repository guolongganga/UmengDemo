package com.fund.umengdemo;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * Author     wildma
 * DATE       2017/07/16
 * Des	      ${友盟分享工具类}
 */
public class ShareUtils {



    /**
     * 友盟分享
     * 上下文activity、分享的链接、标题、内容、类型
     * 若是要分享视频、音乐可看官方文档
     */
    public static void shareWeb(final Activity activity, String WebUrl, String title, String description, SHARE_MEDIA
            platform) {
        UMImage thumb = new UMImage(activity, R.mipmap.ic_launcher);
        UMWeb web = new UMWeb(WebUrl);//连接地址(注意链接开头必须包含http)
        web.setTitle(title);//标题
        web.setDescription(description);//描述
        web.setThumb(thumb);//缩略图
        new ShareAction(activity)
                //分享的平台
                .setPlatform(platform)
                .withMedia(web)
                .setCallback(new UMShareListener() {
                    /**
                     * @descrption 分享开始的回调
                     * @param share_media 平台类型
                     */
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.e("SSSSSSSSSSS", "onStart开始分享的平台: " + share_media);
                    }

                    /**
                     * @descrption 分享成功的回调
                     * @param share_media 平台类型
                     */
                    @Override
                    public void onResult(final SHARE_MEDIA share_media) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, " 分享成功 ", Toast.LENGTH_SHORT).show();
                                Log.e("SSSSSSSSSSS", "onStart分享成功的平台: " + share_media);
                            }
                        });
                    }

                    /**
                     * @descrption 分享失败的回调
                     * @param share_media 平台类型
                     * @param throwable 错误原因
                     */
                    @Override
                    public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
                        if (throwable != null) {
                            //失败原因
                            Log.e("SSSSSSSSSSS", "throw:" + throwable.getMessage());
                        }
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, share_media + " 分享失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    /**
                     * @descrption 分享取消的回调
                     * @param share_media 平台类型
                     */
                    @Override
                    public void onCancel(final SHARE_MEDIA share_media) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, " 分享取消 ", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .share();
    }


//    /**
//     * 分享链接
//     */
//    public static void shareWeb(final Activity activity, String WebUrl, String title, String description, String imageUrl, int imageID, SHARE_MEDIA platform) {
//        UMWeb web = new UMWeb(WebUrl);//连接地址
//        web.setTitle(title);//标题
//        web.setDescription(description);//描述
//        if (TextUtils.isEmpty(imageUrl)) {
//            web.setThumb(new UMImage(activity, imageID));  //本地缩略图
//        } else {
//            web.setThumb(new UMImage(activity, imageUrl));  //网络缩略图
//        }
//        new ShareAction(activity)
//                .setPlatform(platform)
//                .withMedia(web)
//                .setCallback(new UMShareListener() {
//                    @Override
//                    public void onStart(SHARE_MEDIA share_media) {
//
//                    }
//
//                    @Override
//                    public void onResult(final SHARE_MEDIA share_media) {
//                        activity.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (share_media.name().equals("WEIXIN_FAVORITE")) {
//                                    Toast.makeText(activity, share_media + " 收藏成功", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(activity, share_media + " 分享成功", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
//                        if (throwable != null) {
//                            Log.d("throw", "throw:" + throwable.getMessage());
//                        }
//                        activity.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(activity, share_media + " 分享失败", Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onCancel(final SHARE_MEDIA share_media) {
//                        activity.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(activity, share_media + " 分享取消", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                })
//                .share();
//
//        //新浪微博中图文+链接
//        /*new ShareAction(activity)
//                .setPlatform(platform)
//                .withText(description + " " + WebUrl)
//                .withMedia(new UMImage(activity,imageID))
//                .share();*/
//    }
}
