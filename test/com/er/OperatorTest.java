package com.er;


import com.er.functions.Function1;
import org.junit.Test;

/**
 * Created by God on 2016/1/22.
 * operator test
 */
public class OperatorTest {


    @Test
    public void filterTest() {
        Subject<String> subject = Subject.create(new Subject.OnSubscible<String>() {
            @Override
            public void call(Subscriber<? super String> var) {
                var.onNext("");
            }
        });


        Subject<String> newSubject = subject.map(var -> var + "_map");

        Subject<String> threeSubject = newSubject.filter((Function1<String, Boolean>) var -> var.length() > 10);


        threeSubject.subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String state) {
                System.out.println(state);
            }
        });
    }

    //map操作测试
    @Test
    public void mapTest() {
        Subject<String> subject = Subject.create(new Subject.OnSubscible<String>() {
            @Override
            public void call(Subscriber<? super String> var) {
                var.onNext("ok");
            }
        });


        Subject<String> newSubject = subject.map(var -> var + "_map");

        Subject<String> threeSubject = newSubject.map(var -> var + "|||||||||||");


        threeSubject.subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String state) {
                System.out.println(state);
            }
        });
    }

    @Test
    public void createTest() {
        Subject.create(new Subject.OnSubscible<String>() {
            @Override
            public void call(Subscriber<? super String> var) {
                var.onNext("ok");
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String state) {
                System.out.println(state + "====");
            }
        });
    }
}
