package com.yyc.httplib.retrofit2;

import android.content.Context;
import android.widget.Toast;

import com.yyc.httplib.entity.BaseEntity;
import com.yyc.httplib.utils.LogUtil;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author: Page
 * @time: 17-8-7
 * @description: Response preprocessing.
 */
public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {

    private static final String TAG = "BaseObserver";
    public static final int SUCCESS_CODE = 1;
    private Context mContext;

    protected BaseObserver(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull BaseEntity<T> tBaseEntity) {
        LogUtil.d(TAG,"onNext");
        if (SUCCESS_CODE == tBaseEntity.getCode()) {
            T t = tBaseEntity.getData();
            onSuccess(t);
        } else {
            onFailure(tBaseEntity.getMsg());
        }

        onCompleted();
    }

    @Override
    public void onError(@NonNull Throwable e) {
        LogUtil.d(TAG,"onError");
    }

    @Override
    public void onComplete() {
        LogUtil.d(TAG,"onComplete");

    }

    public abstract void onSuccess(T t);

    public void onFailure(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public void onCompleted() {

    }
}
