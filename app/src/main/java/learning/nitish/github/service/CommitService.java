package learning.nitish.github.service;

import java.util.List;

import learning.nitish.github.model.CommitsResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Nitish Singh Rathore on 5/8/17.
 */

public interface CommitService {


    @GET("rails/rails/commits")
    Observable<List<CommitsResponse>> getLatestCommits(@Query(value = "per_page") int count);


}
