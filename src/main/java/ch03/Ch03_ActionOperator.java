package ch03;


import io.reactivex.Observable;

public class Ch03_ActionOperator {

    public static void simpleDoOnXxx() {

        Observable.just("one", "two", "three")
                .doOnNext(s -> System.out.println(s))
                .doOnSubscribe(d -> System.out.println("subscribe"))
                .doOnDispose(() -> System.out.println("disposal"))
                .doOnComplete(() -> System.out.println("complete"))
                .doOnError(e -> System.out.println(e.getLocalizedMessage()))
                .map(String::length)
                .subscribe(integer -> System.out.println(integer));


        Observable.just(1,2,3,4,5)
                .reduce((total, next) -> total + next)
                .doOnSuccess(integer -> System.out.println(integer))
                .subscribe(integer -> System.out.println(integer));
    }
}
