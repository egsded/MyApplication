package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcv;
    AdaptadorPersona adapter;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv = findViewById(R.id.recycleview);
        btn = findViewById(R.id.btn1);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "http://nuevo.rnrsiilge-org.mx/lista", null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Type tp = new TypeToken<List<Persona>>(){}.getType();
                        Log.d("persona", tp.toString());
                        Log.d("pruebon",response.toString());
                        List<Persona> lp = new Gson().fromJson(response.toString(),tp);
                        Log.d("valor", lp.get(0).getNombre().toString());
                        rcv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        rcv.setAdapter(new AdaptadorPersona(lp, MainActivity.this));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                });
                VolleyS.getInstance(getApplicationContext()).getRequestQueue().add(jsonArrayRequest);
            }
        });
    }
}
