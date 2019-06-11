package com.example.yourcafe.ui.admin;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GetFill {
    private OkHttpClient client = new OkHttpClient();
    private String respstring;
    private CountDownLatch countDownLatch;

    String run(String url) throws IOException, InterruptedException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            countDownLatch = new CountDownLatch(1);
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    countDownLatch.countDown();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() == 200) {
                        try (ResponseBody responseBody = response.body()) {
                            respstring = responseBody.string();
                        }
                    }
                    countDownLatch.countDown();
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                }
            });
        } catch (Exception e){
            System.out.println(e);
        }

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        countDownLatch.await();
        return respstring;
    }
}
