package com.er.functions;

/**
 * Created by God on 2016/1/26.
 */
@FunctionalInterface
public interface Action1<T> extends Function{
    void call(T var);
}
