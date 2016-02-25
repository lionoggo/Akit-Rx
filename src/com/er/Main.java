package com.er;


import com.er.functions.Function1;

/**
 * Created by God on 2016/1/22.
 */
public class Main {
    public static void main(String[] args) {

        filterTest();

    }

    //filter操作测试
    private static void filterTest() {
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
    private static void mapTest() {
        Subject<String> subject = Subject.create(new Subject.OnSubscible<String>() {
            @Override
            public void call(Subscriber<? super String> var) {
                var.onNext("ok");
            }
        });


        Subject<String> newSubject = subject.map(new Function1<String, String>() {
            @Override
            public String call(String var) {

                return var + "_map";
            }
        });

        Subject<String> threeSubject = newSubject.map(new Function1<String, String>() {
            @Override
            public String call(String var) {
                return var + "|||||||||||";
            }
        });


        threeSubject.subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String state) {
                System.out.println(state);
            }
        });
    }

    private static void createTest() {
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
