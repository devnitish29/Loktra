package learning.nitish.github.dependencies;

import android.content.Context;

import dagger.Component;
import learning.nitish.github.view.MainActivity;

/**
 * Created by Nitish Singh Rathore on 5/8/17.
 */

@GithubAppScope
@Component(modules = ApiModule.class, dependencies = RetrofitComponent.class)
public interface ApiComponent {


    void inject(MainActivity context);
}
