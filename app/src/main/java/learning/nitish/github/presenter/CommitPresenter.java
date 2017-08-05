package learning.nitish.github.presenter;

import java.util.List;

import learning.nitish.github.base.BasePresenter;
import learning.nitish.github.model.CommitsResponse;
import learning.nitish.github.view.CommitViewInterface;
import rx.Observer;

/**
 * Created by Nitish Singh Rathore on 5/8/17.
 */

public class CommitPresenter extends BasePresenter  implements Observer<List<CommitsResponse>> {

    private CommitViewInterface mCommitViewInterface;

    public CommitPresenter(CommitViewInterface commitViewInterface) {
        this.mCommitViewInterface = commitViewInterface;
    }

    @Override
    public void onCompleted() {
        mCommitViewInterface.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        mCommitViewInterface.onError(e.getMessage());
    }

    @Override
    public void onNext(List<CommitsResponse> commitsResponses) {

        mCommitViewInterface.onCommits(commitsResponses);

    }


    public void fetchCommits(){
        subscribe(mCommitViewInterface.getCommits(), CommitPresenter.this);
    }
}
