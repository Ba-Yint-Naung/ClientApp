package com.bayintnaung.clientapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.ref.ReferenceQueue;
import java.net.URLEncoder;

import es.dmoral.toasty.Toasty;

public class MediaFireAPIConnecct {
    static String filelink;
    public static String getFileLink(String videolink, final Context context) throws UnsupportedEncodingException {
        RequestQueue referenceQueue = Volley.newRequestQueue(context);
        String url="http://media-fire-api.herokuapp.com/?url=" + URLEncoder.encode(videolink,"utf-8");
        JsonArrayRequest request= new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject object = response.getJSONObject(0);
                            filelink = object.getString("file");

                        }catch (Exception ex){
                            Toasty.error(context,ex+"",Toasty.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        referenceQueue.add(request);
        RetryPolicy retryPolicy=new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 10000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 5;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        };
        request.setRetryPolicy(retryPolicy);
        return filelink;
    }
}
