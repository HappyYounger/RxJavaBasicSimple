package ch03;


import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Ch03_SuppressingOperator {


    public static void sleep(int n) {


        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void simpleFilter() {

        Observable.just("one", "two", "three", "four")
                .filter(s -> s.length() > 3)
                .subscribe(s -> System.out.println(s));
    }

    public static void simpleTake() {

        Observable.just("one", "two", "three", "four")
                .take(2).subscribe(s -> System.out.println(s));

        Observable.interval(300, TimeUnit.MILLISECONDS)
                .take(2, TimeUnit.SECONDS)
                .subscribe(s -> System.out.println(s));


        sleep(3000);

    }

    public static void simpleSkip() {


        Observable.just("one", "two", "three", "four")
                .skip(2).subscribe(s -> System.out.println(s));

        Observable.interval(300, TimeUnit.MILLISECONDS)
                .skip(2, TimeUnit.SECONDS)
                .subscribe(s -> System.out.println(s));

        sleep(3000);
    }

    public static void simpleTakeWhile() {

        Observable.range(1, 12)
                .takeWhile(i -> i < 5)
                .subscribe(i -> System.out.println(i));

    }

    public static void simpleSkipWhile() {

        Observable.range(1, 12)
                .skipWhile(i -> i < 5)
                .subscribe(i -> System.out.println(i));

    }

    public static void simpleDistinct() {

        Observable.just("one", "two", "three", "four", "five", "six")
                .map(String::length)
                .distinct().subscribe(s -> System.out.println(s));

    }

    public static void simpleDistinctUntilChanged() {

        Observable.just("one", "two", "three", "four", "five", "six")
                .map(String::length)
                .distinctUntilChanged().subscribe(s -> System.out.println(s));
    }


    public static void simpleElementAt() {

        Observable.just("one", "two", "three", "four", "five", "six")
                .elementAt(9, "nothing").subscribe(s -> System.out.println(s));
    }
}
