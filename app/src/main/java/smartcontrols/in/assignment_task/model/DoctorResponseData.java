
package smartcontrols.in.assignment_task.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorResponseData implements Serializable {

    @SerializedName("meta")
    @Expose
    public Meta meta;
    @SerializedName("data")
    @Expose
    public List<DoctorDetailData> data = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<DoctorDetailData> getData() {
        return data;
    }

    public void setData(List<DoctorDetailData> data) {
        this.data = data;
    }
}
