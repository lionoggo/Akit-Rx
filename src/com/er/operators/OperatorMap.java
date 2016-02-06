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

                //递归执行，如果有两个Map操作-->A--->B，则B中持有A的应用，当B的call执行时，首先执行A的call

                o.onNext(OperatorMap.this.convert.call(state));

            }
        };
    }
}
