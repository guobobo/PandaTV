package com.demo.jiyun.pandatv.net;


import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.demo.jiyun.pandatv.app.App;
import com.demo.jiyun.pandatv.config.Keys;
import com.demo.jiyun.pandatv.model.entity.LoginBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;
import com.demo.jiyun.pandatv.utils.ACache;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp implements IHttp {

    private OkHttpClient client;
    private static OkHttp okHttp;

    private OkHttp(){
        client=new OkHttpClient();
    }

    public static OkHttp getInstance(){

        if(okHttp==null){
            synchronized (OkHttp.class){
                if(okHttp==null){
                    okHttp = new OkHttp();
                }
            }
        }
        return okHttp;
    }

    //普通Get请求
    @Override
    public <T> void get(String url, final MyNetWorkCallBack<T> callBack) {
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e.getMessage().toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(getGeneric(string,callBack));
                    }
                });
            }
        });
    }


    //带请求体不带请求头的方法GET
    @Override
    public <T> void get(String url, Map<String, String> params, final MyNetWorkCallBack<T> callBack) {

        FormBody.Builder builder = new FormBody.Builder();

        if(params!=null&&params.size()>0) {

            Set<String> keySet = params.keySet();

            for (String key : keySet) {
                String value = params.get(key);

                builder.add(key,value);
            }
        }

        Request request = new Request.Builder().url(url).put(builder.build()).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e.getMessage().toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();

                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        callBack.onSuccess(getGeneric(string,callBack));

                    }
                });

            }
        });

    }

    //带请求体带请求头的Get方法
    @Override
    public <T> void get(String url, Map<String, String> params, Map<String, String> heads, final MyNetWorkCallBack<T> callBack) {

        FormBody.Builder bodyBuilder = new FormBody.Builder();

        if(params!=null&&params.size()>0) {

            Set<String> keySet = params.keySet();

            for (String key : keySet) {
                String value = params.get(key);

                bodyBuilder.add(key,value);
            }
        }

        Request.Builder builder = new Request.Builder().url(url);

        if(heads!=null&& heads.size()>0){

            Set<String> keySet = heads.keySet();

            for (String key : keySet) {

                String value = heads.get(key);

                builder.addHeader(key,value);
            }

        }

        Request request = builder.put(bodyBuilder.build()).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e.getMessage().toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();

                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        callBack.onSuccess(getGeneric(string,callBack));

                    }
                });

            }
        });

    }

    @Override
    public <T> void getHeaders(String url, Map<String, String> heads, final MyNetWorkCallBack<T> callBack) {

        Request.Builder builder = new Request.Builder().url(url);

        if(heads!=null&& heads.size()>0){

            Set<String> keySet = heads.keySet();

            for (String key : keySet) {

                String value = heads.get(key);

                builder.addHeader(key,value);
            }

        }

        Request request = builder.build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e.getMessage().toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();

                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        callBack.onSuccess(getGeneric(string,callBack));

                    }
                });

            }
        });

    }

    @Override
    public <T> void getWonderfulDta(String url, Map<String, String> params, final MyNetWorkCallBack<T> callBack) {

        StringBuffer sb = new StringBuffer(url);

        if(params!=null&&params.size()>0){

            sb.append("?");

            Set<String> keySet = params.keySet();

            for (String key :keySet){

                String value = params.get(key);

                sb.append(key).append("=").append(value).append("&");

            }
            url = sb.deleteCharAt(sb.length() - 1).toString();

        }

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        callBack.onError(e.getMessage().toString());

                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();

                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        App.context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onSuccess(getGeneric(string,callBack));
                            }
                        });
                    }
                });

            }
        });

    }

    //拼接参数的Get请求
    @Override
    public <T> void getReisteredData(String url, Map<String, String> params, Map<String, String> headers, final MyNetWorkCallBack<T> callback) {


        StringBuffer sb = new StringBuffer(url);
        if(params != null && params.size() > 0){
            sb.append("?");
            Set<String> keys = params.keySet();
            for (String key : keys) {
                String value = params.get(key);
                sb.append(key).append("=").append(value).append("&");
            }
            url = sb.deleteCharAt(sb.length()-1).toString();
        }
        Request.Builder builder = new Request.Builder();
        if(headers != null && headers.size() > 0){
            Set<String> keys = headers.keySet();
            for (String key : keys){
                String value = headers.get(key);
                builder.addHeader(key,value);
            }
        }
        Request request = builder.url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.onError(e.getMessage().toString());
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.body().string();
                //执行在子线程中
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.onSuccess(getGeneric(jsonData,callback));
                    }
                });

            }
        });

    }

    //POST带请求体
    @Override
    public <T> void post(String url, Map<String, String> params, final MyNetWorkCallBack<T> callBack) {


        FormBody.Builder builder = new FormBody.Builder();
        if(params !=null && params.size() > 0){
            Set<String> keys = params.keySet();
            for (String key : keys) {
                String value = params.get(key);
                builder.add(key,value);
            }
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callBack.onError(e.getMessage().toString());
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.body().string();
                //执行在子线程中
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callBack.onSuccess(getGeneric(jsonData,callBack));
                    }
                });

            }
        });



    }

    //POST带请求头和请求体
    @Override
    public <T> void post(String url, Map<String, String> params, Map<String, String> heads, final MyNetWorkCallBack<T> callBack) {


        FormBody.Builder bodyBuilder = new FormBody.Builder();

        if(params!=null&&params.size()>0) {

            Set<String> keySet = params.keySet();

            for (String key : keySet) {
                String value = params.get(key);

                bodyBuilder.add(key,value);
            }
        }

        Request.Builder builder = new Request.Builder();

        if(heads!=null&& heads.size()>0){

            Set<String> keySet = heads.keySet();

            for (String key : keySet) {

                String value = heads.get(key);

                builder.addHeader(key,value);
            }

        }

        Request request = builder.url(url).post(bodyBuilder.build()).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e.getMessage().toString());
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(getGeneric(string,callBack));
                    }
                });
            }
        });


    }

    @Override
    public void upLoad() {

    }

    @Override
    public void downLoad() {

    }

    @Override
    public void imageLoad(String url, ImageView imageView) {

        Glide.with(App.context).load(url).into(imageView);

    }

    //加载手机验证码
    public void loadPhoneCode(String url, Map<String, String> params, Map<String, String> heads, final MyNetWorkCallBack<String> callBack){

        FormBody.Builder bodyBuilder = new FormBody.Builder();

        if(params!=null&&params.size()>0) {

            Set<String> keySet = params.keySet();

            for (String key : keySet) {
                String value = params.get(key);

                bodyBuilder.add(key,value);
            }
        }

        Request.Builder builder = new Request.Builder();

        if(heads!=null&& heads.size()>0){

            Set<String> keySet = heads.keySet();

            for (String key : keySet) {

                String value = heads.get(key);

                builder.addHeader(key,value);
            }

        }
        Request request = builder.url(url).post(bodyBuilder.build()).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e.getMessage().toString());
                    }
                });

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();

                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(string);
                    }
                });

            }
        });
    }

    //获取图片验证码
    public void loadImgCode(String url,final MyNetWorkCallBack<Bundle> callback){
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.onError(e.getMessage().toString());
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] bytes = response.body().bytes();
                Headers headers = response.headers();
                String jsessionId =  headers.get("Set-Cookie");
                final Bundle bundle = new Bundle();
                bundle.putString(Keys.JSESSIONID,jsessionId);
                bundle.putByteArray(Keys.IMGCODE,bytes);

                //执行在子线程中
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.onSuccess(bundle);
                    }
                });

            }
        });
    }

    public <T> void getLoginCookie(String url, Map<String, String> heads, final MyNetWorkCallBack<Bundle> callBack) {

        Request.Builder builder = new Request.Builder().url(url);

        if(heads!=null&& heads.size()>0){

            Set<String> keySet = heads.keySet();

            for (String key : keySet) {

                String value = heads.get(key);

                builder.addHeader(key,value);
            }

        }

        Request request = builder.build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callBack.onError(e.getMessage().toString());
                    }
                });

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String cookie = response.headers().get("Set-Cookie");

                String string = response.body().string();

                final Bundle mbundle = new Bundle();

                Gson gson = new Gson();

                LoginBean loginBean = gson.fromJson(string, LoginBean.class);

                mbundle.putString("cookie",cookie);

                mbundle.putParcelable("loginBean",loginBean);

                //执行在子线程中
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callBack.onSuccess(mbundle);
                    }
                });

            }
        });
    }

    /**
     * 自动解析json至回调中的JavaBean
     * @param jsonData
     * @param callBack
     * @param <T>
     * @return
     */
    private <T> T getGeneric(String jsonData,MyNetWorkCallBack<T> callBack){
        Gson gson = new Gson();
        //通过反射获取泛型的实例
        Type[] types = callBack.getClass().getGenericInterfaces();
        Type[] actualTypeArguments = ((ParameterizedType) types[0]).getActualTypeArguments();
        Type type = actualTypeArguments[0];
        T t = gson.fromJson(jsonData,type);
        ACache aCache = ACache.get(App.context);
        aCache.put(t.getClass().getSimpleName(), (Serializable) t);

        return t;
    }

}
