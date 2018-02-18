
package smartcontrols.in.assignment_task.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Phone implements Serializable {

    @SerializedName("number")
    @Expose
    public String number;
    @SerializedName("type")
    @Expose
    public String type;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
