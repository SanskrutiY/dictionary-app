package com.example.myapplication2;

import com.example.myapplication2.Models.APIResponse;

public interface OnFetchDataListener {
    void onFetchData(APIResponse apiResponse, String message);
    void onError(String message);
}
