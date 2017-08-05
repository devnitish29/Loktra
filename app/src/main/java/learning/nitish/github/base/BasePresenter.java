package learning.nitish.github.base;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Nitish Singh Rathore on 5/8/17.
 */

public  abstract class BasePresenter implements BasePresenterInterface {


    private CompositeSubscription mCompositeSubscription;

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {
        cofigureSubsciption();

    }

    private CompositeSubscription cofigureSubsciption() {
        
        if(mCompositeSubscription == null || mCompositeSubscription.isUnsubscribed()){
            mCompositeSubscription = new CompositeSubscription();
        }
        
        return null;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        unSubscribeAll();

    }

    protected void unSubscribeAll() {
        if(mCompositeSubscription != null){
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription.clear();
        }
    }


    protected  <T> void subscribe(Observable<T> observable, Observer<T> observer){

        Subscription subscription = observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribe(observer);

    mCompositeSubscription.add(subscription);

    }
}
