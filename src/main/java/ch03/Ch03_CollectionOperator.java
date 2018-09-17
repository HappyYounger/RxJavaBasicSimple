package ch03;

import io.reactivex.Observable;

import java.util.HashSet;

public class Ch03_CollectionOperator {

    public static void simpleToList() {

        Observable.just("One", "Two", "Three", "Four")
                .toList()
                .subscribe(s -> System.out.println(s));

        Observable.range(1, 20)
                .toList()
                .subscribe(s -> System.out.println(s));
    }

    public static void simpleToSortedList() {

        Observable.just(1, 4, 2, 7, 5, 30, 21, 19)
                .toSortedList()
                .subscribe(s -> System.out.println(s));
    }


    public static void simpleToMap() {

        Observable.just("One", "Two", "Three", "Four")
                .toMap(s -> s.charAt(0))
                .subscribe(s -> System.out.println(s));

        Observable.just("One", "Two", "Three", "Four")
                .toMultimap(s -> s.charAt(0))
                .subscribe(s -> System.out.println(s));


    }

    public static void simpleCollect() {

        Observable.just("One", "Two", "Three", "Four")
                .collect(HashSet::new, HashSet::add)
                .subscribe(s -> System.out.println(s));
    }


    public static void simpleErrorRecovery() {

        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(i -> 10 / i)
                .subscribe(i -> System.out.println(i), e -> System.out.println(e));


        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(i -> 10 / i)
                .onErrorReturnItem(-1)
                .subscribe(i -> System.out.println(i), e -> System.out.println(e));

        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(i -> 10 / i)
                .onErrorReturn(e -> -1)
                .subscribe(i -> System.out.println(i), e -> System.out.println(e));

        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(i -> 10 / i)
                .onErrorResumeNext(Observable.just(-1).repeat(3))
                .subscribe(i -> System.out.println(i), e -> System.out.println(e));

    }

    public static void simpleRetry() {

        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(i -> 10 / i)
                .retry(2)
                .subscribe(i -> System.out.println(i), e -> System.out.println(e));
    }
}
