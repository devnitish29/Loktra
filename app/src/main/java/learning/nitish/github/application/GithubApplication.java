package learning.nitish.github.application;

import android.app.Application;

import learning.nitish.github.dependencies.ApiComponent;
import learning.nitish.github.dependencies.DaggerApiComponent;
import learning.nitish.github.dependencies.DaggerRetrofitComponent;
import learning.nitish.github.dependencies.RetrofitComponent;
import learning.nitish.github.dependencies.RetrofitModule;
import learning.nitish.github.utility.Constants;

/**
 * Created by Nitish Singh Rathore on 5/8/17.
 */

public class GithubApplication extends Application {
    ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        injectDependencies();
        super.onCreate();
    }

    private void injectDependencies() {

        mApiComponent = DaggerApiComponent.builder()
                .retrofitComponent(getRetrofitComponent())
                .build();
    }


    public RetrofitComponent getRetrofitComponent() {
        return DaggerRetrofitComponent.builder()
                .retrofitModule(new RetrofitModule(Constants.BSAEURL))
                .build();
    }


    public ApiComponent getmApiComponent() {
        return mApiComponent;
    }
}
