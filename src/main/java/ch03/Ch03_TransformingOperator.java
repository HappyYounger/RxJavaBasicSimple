package ch03;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Ch03_TransformingOperator {


    private static void sleep(int n) {

        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void simpleMap() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");
        Observable.just("1/3/2016", "5/9/2016", "10/12/2016")
                .map(s -> LocalDate.parse(s, dtf))
                .subscribe(s -> System.out.println(s));

    }


    public static void simpleCast() {

        Observable<Object> items =
                Observable.just("jia", "yi")
                        .cast(Object.class);

        items.subscribe(s -> System.out.println(s));
    }

    public static void simpleStartWith() {

        Observable.just("menu", "menu-bar", "menu")
                .startWithArray("menu", "------")
                .subscribe(System.out::println);

    }

    public static void simpleDefaultEmpty() {

        Observable.just("menu", "menu-bar", "menu")
                .filter(s -> s.startsWith("Z"))
                .defaultIfEmpty("none")
                .subscribe(System.out::println);
    }


    public static void simpleSwitchIfEmpty() {
        Observable.just("menu", "menu-bar", "menu")
                .filter(s -> s.startsWith("Z"))
                .switchIfEmpty(Observable.just("apple", "macbook", "pro"))
                .subscribe(System.out::println);
    }


    public static void simpleSorted() {

        Observable.just(1, 4, 6, 89, 34).sorted().subscribe(s -> System.out.println(s));

        Observable.just("hello", "world").sorted().subscribe(s -> System.out.println(s));
    }

    public static void simpleDelay() {

        Observable.just("alpha", "beta", "gamma")
                .delay(3, TimeUnit.SECONDS)
                .subscribe(s -> System.out.println(s));


        sleep(4000);
    }

    public static void simpleRepeat() {

        Observable.just("alpha", "beta", "gamma")
//                .repeat(2)
                .repeatWhen(objectObservable -> Observable.timer(6, TimeUnit.SECONDS))
                .subscribe(s -> System.out.println(s));

        sleep(4000);

    }

    public static void simpleScan() {

//        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
//                .scan((a, n) -> a + n)
//                .subscribe(s -> System.out.println(s));

        Observable.just("Alpha", "Beta", "Gamma")
                .scan(0, (total, next) -> total + 1)
                .subscribe(s -> System.out.println(s));

    }

}
