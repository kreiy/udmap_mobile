package com.example.yasmina.udmap.news;

/**
 * Created by cyrano on 21/03/18.
 */

public interface NewsHandler<T> {

   void onData(T data);

   void onError(String errorMessage);

}
