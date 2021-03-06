package com.frodo.app.android.simple;

import com.frodo.app.android.core.task.AndroidFetchNetworkDataTask;
import com.frodo.app.framework.exception.HttpException;
import com.frodo.app.android.simple.cloud.amdb.entities.Configuration;
import com.frodo.app.android.simple.cloud.amdb.services.ConfigurationService;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by frodo on 2015/8/13. use rxjava Observable & Subscriber
 */
public class FetchTmdbConfigurationWithRxjavaTask extends AndroidFetchNetworkDataTask<ConfigurationService, Configuration> {

    protected FetchTmdbConfigurationWithRxjavaTask(ConfigurationService service, Subscriber<Configuration> subscriber) {
        super(service, subscriber);
    }

    @Override
    public final Configuration doBackgroundCall() throws HttpException {
        return getService().configuration();
    }

    @Override
    public void onSuccess(final Configuration configuration) {
        Observable<Configuration> observable = Observable.create(new Observable.OnSubscribe<Configuration>() {
            @Override
            public void call(Subscriber<? super Configuration> subscriber) {
                subscriber.onNext(configuration);
                subscriber.onCompleted();
            }
        });
        observable.subscribe(getSubscriber());
    }

}
