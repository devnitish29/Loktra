
package learning.nitish.github.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommitsResponse {

    @SerializedName("sha")
    @Expose
    private String sha;
    @SerializedName("commit")
    @Expose
    private Commit commit;
    @SerializedName("author")
    @Expose
    private Author_ author;

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public Author_ getAuthor() {
        return author;
    }

    public void setAuthor(Author_ author) {
        this.author = author;
    }

}
