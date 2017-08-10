package com.yyc.httplib.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * @author: Page
 * @time: 17-8-10
 * @description: glide 简易二次封装
 *  本工具类只是简单的封装了Glide较常用的使用方法，如有特殊需求，请自行扩展。
 */

public class ImgUtils {
    /**
     * @param: [context, imgUrl, img]
     * @return: void
     * @description: 简单的图片加载
     */
    public void loadImg(Context context, String imgUrl, ImageView img) {
        Glide.with(context).load(imgUrl).into(img);
    }

    /**
     * @param: [context, imgUrl, img]
     * @return: void
     * @description: 设置加载占位符及加载失败
     */
    public void loadImgPlaceHolderError(Context context, String imgUrl, ImageView img, Drawable dPlaceHolder, Drawable dError) {
        Glide.with(context).load(imgUrl).placeholder(dPlaceHolder).error(dError).into(img);
    }

    /**
     * @param: [context, imgUrl, img]
     * @return: void
     * @description: 设置跳过内存
     */
    public void loadImgSkip(Context context, String imgUrl, ImageView img, boolean skip) {
        Glide.with(context).load(imgUrl).skipMemoryCache(skip).into(img);
    }

    /**
     * @param: [context, imgUrl, img]
     * @return: void
     * @description: 设置下载优先级
     */
    public void loadImgPriority(Context context, String imgUrl, ImageView img) {
        Glide.with(context).load(imgUrl).priority(Priority.NORMAL).into(img);
    }

    /**
     * @param: [context, imgUrl, img]
     * @return: void
     * @description: 策略解说：
     *    all:缓存源资源和转换后的资源
     *    none:不作任何磁盘缓存
     *    source:缓存源资源
     *   result：缓存转换后的资源
     */

    public void loadImg(Context context, String imgUrl, ImageView img,DiskCacheStrategy strategy) {
        Glide.with(context).load(imgUrl).diskCacheStrategy(strategy).into(img);
    }

    /**
     * @param: [context, imgUrl, img]
     * @return: void
     * @description: 设置加载动画
     */
    public void loadImg(Context context, String imgUrl, ImageView img, int animationId) {
        Glide.with(context).load(imgUrl).animate(animationId).into(img);
    }

    /**
     * @param: [context, imgUrl, img]
     * @return: void
     * @description: 设置缩略图支持
     */
    public void loadImg(Context context, String imgUrl, ImageView img,float sizeMultiplier) {
        Glide.with(context).load(imgUrl).thumbnail(sizeMultiplier).into(img);
    }

    /**
     * @param: [context, imgUrl, img]
     * @return: void
     * @description: 设置加载尺寸
     */
    public void loadImg(Context context, String imgUrl, ImageView img, int width, int height) {
        Glide.with(context).load(imgUrl).override(width, height).into(img);
    }

    /**
     * @param: [context, imgUrl, img]
     * @return: void
     * @description: 设置动态GIF加载方式--显示gif静态图片
     */
    public void loadImgBitmap(Context context, String imgUrl, ImageView img) {
        Glide.with(context).load(imgUrl).asBitmap().into(img);
    }

    /**
     * @param: [context, imgUrl, img]
     * @return: void
     * @description: 设置动态GIF加载方式--显示gif动态图片
     */
    public void loadImgGif(Context context, String imgUrl, ImageView img) {
        Glide.with(context).load(imgUrl).asGif().into(img);
    }

    /**
     * @param: [context, imgUrl, img]
     * @return: void
     * @description: 缓存的动态清理--清理磁盘缓存 需要在子线程中执行
     */
    public void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    /**
     * @param: [context, imgUrl, img]
     * @return: void
     * @description: 缓存的动态清理--清理内存缓存  可以在UI主线程中进行
     */
    public void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }

}
