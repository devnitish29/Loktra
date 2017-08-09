package learning.nitish.github.view;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import learning.nitish.github.R;
import learning.nitish.github.helper.DateTimeHellperClass;
import learning.nitish.github.model.CommitsResponse;

/**
 * Created by Nitish Singh Rathore on 5/8/17.
 */

public class CommitAdapter extends RecyclerView.Adapter<CommitAdapter.CommitViewHolder> {

    List<CommitsResponse> mCommitsResponseList;
    LayoutInflater mLayoutInflater;

    public CommitAdapter(LayoutInflater layoutInflater) {

        mLayoutInflater = layoutInflater;
        mCommitsResponseList = new ArrayList<>();
    }

    @Override
    public CommitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommitAdapter.CommitViewHolder(mLayoutInflater.inflate(R.layout.item_commit_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(CommitViewHolder holder, int position) {
        String commitID = "Commit Id: ";
        String commitMessage = "Commit: ";


        CommitsResponse commitsResponse = mCommitsResponseList.get(position);

        if (commitsResponse.getCommit().getAuthor().getName() != null) {
            holder.txtUserName.setText(commitsResponse.getCommit().getAuthor().getName());
        }

        if (commitsResponse.getSha() != null) {
            holder.txtCommitId.setText(commitID.concat(commitsResponse.getSha()));
        }


        if (commitsResponse.getCommit().getMessage() != null) {
            holder.txtCommitMessage.setText(commitMessage.concat(commitsResponse.getCommit().getMessage()));
        }

        if (commitsResponse.getAuthor().getAvatarUrl() != null) {
            Glide.with(holder.itemView.getContext())
                    .load(commitsResponse.getAuthor().getAvatarUrl())
                    .thumbnail(0.5f)
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            holder.imgUser.setImageDrawable(resource);
                            holder.imgUser.setDrawingCacheEnabled(true);
                        }
                    });

        }

        holder.txtCommitTime.setText(DateTimeHellperClass.parseDate(commitsResponse.getCommit().getAuthor().getDate()));


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mBookMarkList.add(mCommitsResponseList.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        if (mCommitsResponseList != null) {
            return mCommitsResponseList.size();
        } else {
            return 0;
        }

    }


    public void addCommits(CommitsResponse commitsResponses) {

        mCommitsResponseList.add(commitsResponses);
        notifyDataSetChanged();
    }


    public void addCommitsList(List<CommitsResponse> commitsResponses) {

        mCommitsResponseList.addAll(commitsResponses);
        notifyDataSetChanged();
    }

    public void removeAllCommits() {

        mCommitsResponseList.clear();
        notifyDataSetChanged();
    }

    class CommitViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgviewUser)
        CircleImageView imgUser;
        @BindView(R.id.txtUserName)
        TextView txtUserName;
        @BindView(R.id.txtCommitId)
        TextView txtCommitId;
        @BindView(R.id.txtCommitMessage)
        TextView txtCommitMessage;
        @BindView(R.id.txtCommitTime)
        TextView txtCommitTime;
        @BindView(R.id.imgBookMark)
        ImageView imageView;

        public CommitViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
