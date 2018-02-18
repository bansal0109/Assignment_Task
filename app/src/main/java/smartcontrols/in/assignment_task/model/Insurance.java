
package smartcontrols.in.assignment_task.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Insurance implements Serializable {

    @SerializedName("insurance_plan")
    @Expose
    public InsurancePlan insurancePlan;
    @SerializedName("insurance_provider")
    @Expose
    public InsuranceProvider insuranceProvider;

    public InsurancePlan getInsurancePlan() {
        return insurancePlan;
    }

    public void setInsurancePlan(InsurancePlan insurancePlan) {
        this.insurancePlan = insurancePlan;
    }

    public InsuranceProvider getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(InsuranceProvider insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }
}
