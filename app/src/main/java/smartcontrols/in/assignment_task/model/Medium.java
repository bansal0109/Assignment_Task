
package smartcontrols.in.assignment_task.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medium implements Serializable {

    @SerializedName("uid")
    @Expose
    public String uid;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("tags")
    @Expose
    public List<String> tags = null;
    @SerializedName("versions")
    @Expose
    public Versions versions;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Versions getVersions() {
        return versions;
    }

    public void setVersions(Versions versions) {
        this.versions = versions;
    }
}
