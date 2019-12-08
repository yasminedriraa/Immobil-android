package com.example.projetimmo.login;

public interface LoginWebServiceListener {
    void onSuccess(int type);
    void onError(String message);
    void onFailure(String message);
}
