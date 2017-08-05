package learning.nitish.github.dependencies;

import dagger.Module;
import dagger.Provides;
import learning.nitish.github.service.CommitService;
import retrofit2.Retrofit;

/**
 * Created by Nitish Singh Rathore on 5/8/17.
 */

@Module
public class ApiModule {


    @Provides
    @GithubAppScope
    CommitService provideCommitService(Retrofit retrofit){

        return retrofit.create(CommitService.class);
    }
}
