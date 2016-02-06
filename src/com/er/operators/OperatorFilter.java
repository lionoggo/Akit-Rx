package com.er.operators;


import com.er.Subject;
import com.er.Subscriber;
import com.er.functions.Function1;

/**
 * Created by God on 2016/1/27.
 */
public class OperatorFilter<T> implements Subject.Operator<T, T> {
    Function1<? super T, Boolean> function;

    public OperatorFilter(Function1<? super T, Boolean> function) {
        this.function = function;
    }

    public Subscriber<? super T> call(Subscriber<? super T> var) {
        return new Subscriber<T>(var) {
            @Override
            public void onNext(T state) {

                if (OperatorFilter.this.function.call(state).booleanValue()) {//满足条件的留下
                    var.onNext(state);
                }
            }
        };
    }
}
