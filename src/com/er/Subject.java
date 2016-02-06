package com.er;


import com.er.functions.Action1;
import com.er.functions.Function1;
import com.er.operators.OperatorFilter;
import com.er.operators.OperatorMap;

/**
 * Created by God on 2016/1/22.
 */
public class Subject<T> {

    private OnSubscible<T> onSubscible;

    public Subject(OnSubscible<T> f) {
        this.onSubscible = f;
    }

    public static <T> Subject<T> create(OnSubscible<T> onSubscible) {
        return new Subject(onSubscible);

    }

    public void subscribe(Subscriber<T> subscribler) {
        if (subscribler == null) {
            throw new NullPointerException("Observer can not be null");
        } else if (this.onSubscible == null) {
            throw new IllegalArgumentException("onSubscribe function can not be null.");
        } else {
            this.onSubscible.call(subscribler);
        }

    }


    public <R> Subject<R> filter(Function1<? super T, Boolean> function) {

        return lift(new OperatorFilter(function));
    }


    public <R> Subject<R> map(Function1<? super T, ? extends R> function) {
        return lift(new OperatorMap(function));
    }

    private <R> Subject<R> lift(Operator<? extends R, ? super T> operator) {
        return new Subject<>(new OnSubscible<R>() {
            @Override
            public void call(Subscriber<? super R> o) {
                Subscriber<? super T> e = operator.call(o);
                Subject.this.onSubscible.call(e);//上一个Subject引用的onSubscibe

            }
        });
    }

    public interface OnSubscible<T> extends Action1<Subscriber<? super T>> {
    }

    public interface Operator<R, T> extends Function1<Subscriber<? super R>, Subscriber<? super T>> {
    }

}
