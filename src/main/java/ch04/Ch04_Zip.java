package ch04;


import io.reactivex.Observable;

public class Ch04_Zip {

    public static void simpleZip() {

        Observable observable1 = Observable.just("one", "two", "three");
        Observable observable2 = Observable.range(1,  10);

        Observable.zip(observable1, observable2, (s, i) -> s + " " + i)
                .subscribe(s -> System.out.println(s));
    }
}
