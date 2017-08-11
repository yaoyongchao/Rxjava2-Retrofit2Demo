# Rxjava2-Retrofit2Demo
Retrofit2+Rxjava2简易封装demo初始上传 [Blog](http://blog.csdn.net/yyongchao/article/details/77076321?_blank)

最近把RxJava2和Retrofit2学习了一下，并做了一个简单的封装，算是对学习的总结,以及以后的做项目时可以，可以拿来及用。

注意：本篇不说RxJava2 和 Retrofit2的基础使用，只梳理一下封装的步骤，所以适合对RxJava2 和 Retrofit2有一定的了解的 Coder.

先上封装后使用网络请求的代码：


ServiceFactory.getService(LoginService.class)

                .login()

                .compose(RxSchedulers.<BaseEntity<User>>ioMain(context))

                .subscribe(new BaseObserver<User>(context) {

                    @Override

                    public void onSuccess(User user) {

                        Log.e(TAG, "User: " + user);

                    }

                });

前言：一个网络请求，一般而言，大概有以下步骤：

1.建立网络链接。

2.请求前的预处理（如：统一处理请求格式、网络判断、ProgressDialog）。

3.取消请求。

4.请求结果预处理，拿到想要的结果。

一般我们响应的数据格式如：


{
      "code":1,
      "msg":"success",
      "data":{}
  }
我们关心的数据是data，所以对响应结果需要做预处理。
封装步骤：

一、建立链接：
创建Service：写一个ServiceFactory创建Service。主要代码如下：


public class ServiceFactory {
    public static <T> T getService(String url ,final  Class<T> service) {
        return RetrofitHelper.getRetrofit(url).create(service);
    }

    public static <T> T getService(final  Class<T> service) {
        return RetrofitHelper.getRetrofit().create(service);
    }
}

OkHttpClient 和 Retrofit的创建请看底部链接 Demo。

二、请求前的预处理：

我是写了一个线程转换类，并在里面做的请求预处理，如下：

public class RxSchedulers {
    private static ProgressDialog dialog;

    /**
     * @param: [context]
     * @return: io.reactivex.ObservableTransformer<T,T>
     * @description: Thread Scheduling. The default is show dialog.
     */
    public static <T> ObservableTransformer<T, T> ioMain(final Context context) {
        return ioMain(context,true);
    }

    /**
     * @param: [context, isShow] [context，Whether or not show dialog.]
     * @return: io.reactivex.ObservableTransformer<T,T>
     * @description: Thread Scheduling.
     */
    public static <T> ObservableTransformer<T, T> ioMain(final Context context, final boolean isShow) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                if (isShow && dialog == null) {
                    dialog = new ProgressDialog(context);
                    dialog.setCancelable(true);
                    dialog.setMessage("加载中...");
                }
                return observable
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(final Disposable disposable) throws Exception {
                                LogUtil.d("doOnSubscribe -- Determine if there is a network and dialog.");

                                if (!NetWorkUtils.isConnectedByState(context)) {
                                    disposable.dispose();
                                    LogUtil.d("doOnSubscribe -- There's no network link.");
                                    Toast.makeText(context, R.string.toast_network_error, Toast.LENGTH_SHORT).show();
                                } else {
                                    LogUtil.d("doOnSubscribe -- There's a network link.");
                                    if (isShow && dialog != null && !dialog.isShowing()) {
                                        dialog.show();
                                        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                            @Override
                                            public void onCancel(DialogInterface dialog) {
                                                LogUtil.i("Dialog Cancel and interrupt network request!");
                                                disposable.dispose();
                                            }
                                        });
                                    }

                                }
                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                if (dialog != null && dialog.isShowing())
                                    dialog.dismiss();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}

在doOnSubscribe中做网络请求和Dailog show。在doFinally中做Dialog dismiss 。disposable.dispose做网络取消。

三、结果预处理：

创建一个BaseObserver类，基础Observer，如下：

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
            onFail(tBaseEntity.getMsg());
        }

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

    public void onFail(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}

BaseEntity:

public class BaseEntity<T> {
    private int code;

    private String msg;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

该类中做数据处理，把data从响应数据中分离出来，并提前对错误做处理。
大概基本的思路就这样。

好了，不写了，还有其他事先撤了，希望对您有些许帮助。

再次声明：本篇博客适合有Rxjava2 和 Retrofit2有一定基础的Coder.

拜拜啦！！！真不写了。

