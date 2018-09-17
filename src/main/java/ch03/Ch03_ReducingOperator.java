package ch03;


import io.reactivex.Observable;

public class Ch03_ReducingOperator {

    public static void simpleCount() {

        Observable.just("one", "two", "three")
                .count().subscribe(s -> System.out.println(s));
    }

    public static void simpleReduce() {

        Observable.just(5, 3, 7, 10)
                .reduce((t, n) -> t + n)
                .subscribe(s -> System.out.println(s));
    }

    public static void simpleAny() {

        Observable.just(5, 3, 7, 10)
                .any(integer -> integer > 5)
                .subscribe(s -> System.out.println(s));

    }

    public static void simpleContains() {

        Observable.range(1,10000)
                .contains(9563)
                .subscribe(s -> System.out.println(s));
    }
}
