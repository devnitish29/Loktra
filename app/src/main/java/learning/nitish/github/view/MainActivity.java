package learning.nitish.github.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import learning.nitish.github.R;
import learning.nitish.github.application.GithubApplication;
import learning.nitish.github.helper.DateTimeHellperClass;
import learning.nitish.github.model.CommitsResponse;
import learning.nitish.github.presenter.CommitPresenter;
import learning.nitish.github.service.CommitService;
import rx.Observable;


public class MainActivity extends AppCompatActivity implements CommitViewInterface {

    @Inject
    CommitService mCommitService;

    private CommitPresenter mCommitPresenter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    CommitAdapter mCommitAdapter;

    ProgressDialog mProgressDialog;
    List<CommitsResponse> commitsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectDependencies();
        ButterKnife.bind(MainActivity.this);

        initViews();

        mCommitPresenter = new CommitPresenter(MainActivity.this);
        mCommitPresenter.onCreate();
    }

    private void initViews() {

        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mCommitAdapter = new CommitAdapter(getLayoutInflater());
        mRecyclerView.setAdapter(mCommitAdapter);

    }

    private void injectDependencies() {

        ((GithubApplication) getApplication())
                .getmApiComponent().inject(MainActivity.this);

    }


    @Override
    protected void onResume() {
        super.onResume();
        mCommitPresenter.onResume();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Downloading Data ");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        mCommitPresenter.fetchCommits();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCommitPresenter.onDestroy();
    }

    @Override
    public void onCompleted() {

        mProgressDialog.cancel();
        Toast.makeText(MainActivity.this, "Downloaded Data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message) {

        mProgressDialog.cancel();
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onCommits(List<CommitsResponse> commitsResponses) {

         commitsList.addAll(commitsResponses);
        mCommitAdapter.addCommits(commitsList);
    }

    @Override
    public Observable<List<CommitsResponse>> getCommits() {
        return mCommitService.getLatestCommits(40);
    }

    @OnClick(R.id.btnSortTime)
    public void sortListByTime() {
        //Collections.sort(commitsList, (o1, o2) -> DateTimeHellperClass.convertTOMillis(o2.getCommit().getAuthor().getDate()) - DateTimeHellperClass.convertTOMillis(o1.getCommit().getAuthor().getDate()));
        mCommitAdapter.removeAllCommits();
        mCommitAdapter.addCommits(commitsList);


    }


    @OnClick(R.id.btnSortAuthor)
    public void sortListByAuthor() {
        List<CommitsResponse> sortedCommitsList = new ArrayList<>(commitsList);
        Collections.sort(sortedCommitsList, (o1, o2) -> o1.getAuthor().getId() - o2.getAuthor().getId());
        mCommitAdapter.removeAllCommits();
        mCommitAdapter.addCommits(sortedCommitsList);


    }


}
