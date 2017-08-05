package learning.nitish.github.dependencies;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Nitish Singh Rathore on 5/8/17.
 */

@Singleton
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {

    Retrofit retrofit();
}
