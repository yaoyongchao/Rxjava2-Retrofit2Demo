package com.yyc.rxjava2_retrofit2demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.yyc.httplib.entity.BaseEntity;
import com.yyc.httplib.retrofit2.BaseObserver;
import com.yyc.httplib.retrofit2.RxSchedulers;
import com.yyc.httplib.retrofit2.ServiceFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: Page
 * @time: 17-8-10
 * @description: Response preprocessing.
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    @BindView(R.id.btn_login)
    Button btnLogin;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
    }


    private void login() {
        ServiceFactory.getService(LoginService.class)
                .login()
                .compose(RxSchedulers.<BaseEntity<User>>ioMain(context))
                .subscribe(new BaseObserver<User>(context) {
                    @Override
                    public void onSuccess(User user) {
                        Log.e(TAG, "User: " + user);
                    }
                });


    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        Log.e(TAG,"User: " );
        login();
    }
}
