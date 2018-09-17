package ch04;

import io.reactivex.Observable;

public class Ch04_Merge {

    public static void simpleMerge() {

        Observable observable1 = Observable.just("one", "two", "three");

        Observable observable2 = Observable.just("four", "five");

        Observable.merge(observable1, observable2)
                .subscribe(s -> System.out.println(s));


        observable1.mergeWith(observable2).subscribe(s -> System.out.println(s));
    }


    public static void simpleFlatMap() {

        Observable<String> observable = Observable.just("one", "two", "three");

        observable.flatMap(s -> Observable.fromArray(s.split("")))
                .subscribe(System.out::println);

    }

    public static void simpleConcat() {


        Observable observable1 = Observable.just("one", "two", "three");

        Observable observable2 = Observable.just("four", "five");

        Observable.concat(observable1, observable2)
                .subscribe(s -> System.out.println(s));

    }
}
