package ch04;


import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class Ch04_Grouping {

    public static void simpleGroupging() {

        Observable<String> observable =
                Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        Observable<GroupedObservable<Integer, String>> byLength =

                observable.groupBy(s -> s.length());

        byLength.flatMapSingle(grp -> grp.toList()).subscribe(System.out::println);
    }
}
