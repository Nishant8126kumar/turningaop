package com.example.aopdemo.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class AppResponse<T> {
    T data;
    int status;
    String error;

    public AppResponse() {
    }

    public AppResponse(T data, int status, String error) {
        this.data = data;
        this.status = status;
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static <T> AppResponse<T> makeResponse(T data) {
        return new AppResponse(data, 200, null);
    }

    private static <T> JSONObject covertIntoJSONObject(T json) {
        try {
            return new JSONObject(json.toString());
        } catch (JSONException e) {
            return null;
        }
    }

    public static <T> AppResponse<T> makeErrorResponse(String message) {
        return new AppResponse<>(null, 200, message);
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "data");
        jsonObject.put("error", JSONObject.NULL);
        jsonObject.put("status", 200);
        return jsonObject.toString();
    }
}