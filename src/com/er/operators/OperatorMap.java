package com.er.operators;


import com.er.Subject;
import com.er.Subscriber;
import com.er.functions.Function1;

/**
 * Created by God on 2016/1/26.
 */
public class OperatorMap<T, R> implements Subject.Operator<R, T> {
    private Function1<? super T, ? extends R> convert;

    public OperatorMap(Function1<? super T, ? extends R> convert) {
        this.convert = convert;
    }


    public Subscriber<? super T> call(Subscriber<? super R> o) {


        return new Subscriber<T>(o) {
            @Override
            public void onNext(T state) {

                o.onNext(OperatorMap.this.convert.call(state));

            }
        };
    }
}
