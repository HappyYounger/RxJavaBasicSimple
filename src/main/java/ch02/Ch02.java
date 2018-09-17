package ch02;

import io.reactivex.*;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.ResourceObserver;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Ch02 {

    public static void simplePrint() {

        Observable observable = Observable.fromArray("jia", "yi", "bing");
        observable.subscribe(s -> System.out.println(s));
    }

    public static void simpleMap() {

        Observable observable = Observable.just("jia", "yi", "bing");
        observable.map(s -> {
                    String str = (String) s;
                    return ((String) s).toUpperCase();
                }
        ).subscribe(s -> System.out.println(s));
    }

    public static void simpleInterval() {

        Observable observable = Observable.interval(1, 1, TimeUnit.SECONDS);

        observable.subscribe(s -> System.out.println(s));

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void simpleObservableCreate() {

        Observable<String> observable = Observable.create(emitter -> {
            emitter.onNext("jia");
            emitter.onNext("yi");
            emitter.onNext("bing");
        });

        observable.doOnNext(s -> System.out.println(s + "xxx"));

        observable.subscribe(s -> System.out.println(s));


    }

    public static void simpleObservableCreateAndUseConsumer() {

        Observable observable = Observable.create(emitter -> {
            emitter.onNext("jia");
            emitter.onNext("yi");
            emitter.onNext("bing");
        });

        observable.subscribe(new Consumer() {

            @Override
            public void accept(Object o) throws Exception {

                System.out.println(o);
            }
        });
    }

    public static void simpleObservableCreateAndUseMultiConsumer() {

        Observable observable = Observable.create(emitter -> {
            emitter.onNext("jia");
            emitter.onNext("yi");
            emitter.onNext("bing");
        });

        observable.subscribe(o -> System.out.println(o),
                throwable -> System.out.println(((Throwable) throwable).getLocalizedMessage()),
                () -> System.out.println("complete!"),
                disposable -> System.out.println("onSubscribe"));
    }

    public static void simpleObservableCreateAndObserver() {

        Observable observable = Observable.create(emitter -> {
            emitter.onNext("jia");
            emitter.onNext("yi");
            emitter.onNext("bing");
        });

        observable.subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

                System.out.println(o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

                System.out.println("complete");
            }
        });
    }

    public static void simpleFilter() {

        Observable<Integer> observable = Observable.just(1, 3, 6, 8, 9, 34, 67, 005);

        observable.filter(t -> t % 2 == 0).subscribe(i -> System.out.println(i));
    }

    public static void simpleJust() {

        Observable<Integer> observable = Observable.just(2, 3, 4, 5, 6);
        observable.doOnSubscribe(d -> System.out.println("on subscribe"));
        observable.doOnNext(s -> System.out.println(s * 2));

        observable.subscribe(s -> System.out.println(s));
    }

    public static void simpleColdAndHotObservable() {


        Observable<Integer> observable = Observable.just(1, 3, 5, 7, 9, 2, 4, 6, 8);

        observable.subscribe(i -> System.out.println(i));

        observable.filter(i -> i > 4).subscribe(integer -> System.out.println(integer));


        //hot observable 通常代表事件
    }


    public static void simpleConnectableObservable() {

        ConnectableObservable<String> observable =
                Observable.just("some", "any", "none", "only").publish();

        observable.subscribe(integer -> System.out.println(integer));

        observable.map(String::length)
                .subscribe(i -> System.out.println(i));

        observable.connect();
    }

    public static void simpleRange() {

        Observable.range(1, 10).subscribe(integer -> System.out.println(integer));
    }

    public static void simpleFuture() {

//        不会

    }

    public static void simpleEmpty() {

        Observable<String> empty = Observable.empty();

        empty.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(String s) {

                System.out.println("onNext");
            }

            @Override
            public void onError(Throwable e) {

                System.out.println("onError");
            }

            @Override
            public void onComplete() {

                System.out.println("done");
            }
        });
    }

    public static void simpleNever() {

        Observable<String> observable = Observable.never();


        observable.subscribe(System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("done"));

    }

    public static void simpleError() {

        Observable<String> observable = Observable.error(new Exception("my deliberate exception"));
        observable.subscribe(i -> System.out.println(i),
                Throwable::printStackTrace,
                () -> System.out.println("done"));
    }


    //for defer
    private static int start = 0;
    private static int count = 5;

    public static void simpleDefer() {


        Observable observable = Observable.defer(() -> Observable.range(start, count));

        observable.subscribe(i -> System.out.println(i));
        count = 10;

        observable.subscribe(i -> System.out.println(i));
    }


    public static void simpleFromCallable() {

//        Observable.just(1 / 0)
//                .subscribe(integer -> System.out.println(integer)
//                        , throwable -> System.out.println(throwable));

        Observable.fromCallable(() -> 1 / 0)
                .subscribe(integer -> System.out.println(integer)
                        , throwable -> System.out.println(throwable));

        System.out.println("done");
    }

    public static void simpleSingle() {


//        Single<Integer> single = Single.create(emitter -> {
//
//            emitter.onSuccess(100);
//        });
//
//        single.subscribe(new SingleObserver<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//                System.out.println("onSubscribe");
//            }
//
//            @Override
//            public void onSuccess(Integer integer) {
//
//                System.out.println(100);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//        });


        Single.just("hello")
                .map(String::length)
                .subscribe(integer -> System.out.println(integer));
    }


    public static void simpleMaybe() {

        Maybe.just("hello")
                .map(String::length)
                .subscribe(integer -> System.out.println(integer),
                        Throwable::printStackTrace);

        Maybe.empty()
                .subscribe(s -> System.out.println(s),
                        Throwable::printStackTrace,
                        () -> System.out.println("done 0"));

    }

    public static void simpleCompletable() {


        Completable.fromRunnable(() -> runProcess())
                .subscribe(()-> System.out.println("done!"));

    }

    public static void runProcess(){

        System.out.println("run process!");
    }

    public static void simpleDisposable() {

        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);

        Disposable disposable = observable.subscribe(l -> System.out.println(l));

        sleep(5000);
        disposable.dispose();

        sleep(10000);

    }

    private static void sleep(int n){

        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void simpleResourceObservable(){

        Observable<Long> observable =
                Observable.interval(1, TimeUnit.SECONDS);

        ResourceObserver<Long> resourceObserver =
                new ResourceObserver<Long>() {
                    @Override
                    public void onNext(Long aLong) {

                        System.out.println(aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                        System.out.println("done");
                    }
                };

        Disposable disposable = observable.subscribeWith(resourceObserver);

        sleep(3000);
    }


    public static void simpleCompositeDisposable(){


        CompositeDisposable compositeDisposable = new CompositeDisposable();

        Observable<Long> observable =
                Observable.interval(1, TimeUnit.SECONDS);

        Disposable d1 = observable.subscribe(l -> System.out.println(l));
        Disposable d2 = observable.subscribe(l -> System.out.println(l));


        compositeDisposable.addAll(d1, d2);

        sleep(3000);

        compositeDisposable.dispose();

        sleep(5000);

    }

    public static void simpleHandleCreateDisposable(){


    }
}
