package com.example.myapplication2;

import android.content.Context;
import android.widget.Toast;

import com.example.myapplication2.Models.APIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getWordMeaning(OnFetchDataListener listener, String word){
        CallMyApplication2 callMyApplication2 = retrofit.create(CallMyApplication2.class);
        Call<List<APIResponse>> call = callMyApplication2.callMeanings(word);

        try {
            call.enqueue(new Callback<List<APIResponse>>() {
                @Override
                public void onResponse(Call<List<APIResponse>> call, Response<List<APIResponse>> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.onFetchData(response.body().get(0), response.message());
                }

                @Override
                public void onFailure(Call<List<APIResponse>> call, Throwable t) {
                    listener.onError("Request failed");
                }
            });
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public interface CallMyApplication2 {
        @GET("entries/en/{word}")
        Call<List<APIResponse>> callMeanings(
                @Path("word") String word
        );
    }
}
