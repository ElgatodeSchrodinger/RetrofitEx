package com.javtex.retrofitex;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CombuyLocal local = null;
        text=findViewById(R.id.text);

        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        String URL="https://combuyapi.herokuapp.com/";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CombuyClient api=retrofit.create(CombuyClient.class);
        Log.v("RETRO","hasta aqui esta bien");


        try {
            final Call<CombuyLocal> callsync= api.getLocal(1);
            Response<CombuyLocal> response=callsync.execute();
            local=response.body();
            if(response.code()==200){
                Log.v("RETRO","Response Satisfed!");
            }else{
                Log.v("RETRO","Failed U.U");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        
        text.setText(local.toString());
        

    }
}
