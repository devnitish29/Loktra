package learning.nitish.github.view;

import java.util.List;

import learning.nitish.github.model.CommitsResponse;
import rx.Observable;

/**
 * Created by Nitish Singh Rathore on 5/8/17.
 */

public interface CommitViewInterface {
    void onCompleted();

    void onError(String message);

    void onCommits(List<CommitsResponse> commitsResponses);

    Observable<List<CommitsResponse>> getCommits();
}
