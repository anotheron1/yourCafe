package com.example.yourcafe.ui.cafeCatalogue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

class GetReq {
    private OkHttpClient client = new OkHttpClient();
    private String respstring;

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//            System.out.println(response.handshake().cipherSuite());
//            System.out.println(response.body().string());
//        }

        try {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);
//
//                String s = "";
//                while ((s = response.body().string()) != null) {
//                    respstring += s;
//                }
                    try (ResponseBody responseBody = response.body()) {
                        respstring = responseBody.string();
                    }
                }
            });
        } catch (Exception e){
            System.out.println(e);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return respstring;
    }
}