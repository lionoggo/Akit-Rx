package com.er;

public interface Observer<T> {
    public void onNext(T state);
}
