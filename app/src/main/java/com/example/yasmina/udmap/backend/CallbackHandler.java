package com.example.yasmina.udmap.backend;

/**
 * Created by cyrano on 21/03/18.
 */

public interface CallbackHandler<T> {

   void onData(T data);

   void onError(String errorMessage);

}
