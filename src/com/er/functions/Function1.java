package com.er.functions;

/**
 * Created by God on 2016/1/26.
 */
@FunctionalInterface
public interface Function1<T,R> extends Function{
    R call(T var);
}
