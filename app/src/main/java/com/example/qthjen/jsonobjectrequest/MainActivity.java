package com.example.qthjen.jsonobjectrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    String url = "http://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);

        readJson();
    }

    public void readJson() {

        JsonObjectRequest jsRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {   // jsRequest đọc json và lưu vào response
                try {

                    String monhoc = response.getString("monhoc"); // truyền key trong json vào
                    String noihoc = response.getString("noihoc");
                    String website = response.getString("website");
                    String fanpage = response.getString("fanpage");
                    String logo = response.getString("logo");

                    tv.setText("Mon hoc: " + monhoc + "\n" + "Noi hoc: " + noihoc + "\n" + "Wensite: " + website
                        + "\n" + "Fanpage: " + fanpage + "\n" + "Logo: " + logo);

                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "Error input key: " + e.toString(), Toast.LENGTH_LONG ).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(this);
        rQueue.add(jsRequest);
    }
}
