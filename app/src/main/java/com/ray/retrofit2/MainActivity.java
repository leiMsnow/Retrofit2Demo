package com.ray.retrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ray.retrofit2.api.BaseApi;
import com.ray.retrofit2.api.IServiceApi;
import com.ray.retrofit2.model.Book;

public class MainActivity extends AppCompatActivity {

    private TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTest = (TextView) findViewById(R.id.tv_test);

        BaseApi.request(BaseApi.createApi(IServiceApi.class).getBookInfo(),
                new BaseApi.IResponseListener<Book>() {
                    @Override
                    public void onSuccess(Book data) {
                        tvTest.setText(data.summary);
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }
}
