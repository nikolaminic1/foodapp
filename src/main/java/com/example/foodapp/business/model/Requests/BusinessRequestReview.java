package com.example.foodapp.business.model.Requests;


import com.example.foodapp.business.enumeration.BusinessRequestStatus;

public class BusinessRequestReview {
    private BusinessRequestStatus status;
    private String grantInfo;
    private String deniedInfo;

    public BusinessRequestStatus getStatus() {
        return status;
    }

    public void setStatus(BusinessRequestStatus status) {
        this.status = status;
    }

    public String getGrantInfo() {
        return grantInfo;
    }

    public void setGrantInfo(String grantInfo) {
        this.grantInfo = grantInfo;
    }

    public String getDeniedInfo() {
        return deniedInfo;
    }

    public void setDeniedInfo(String deniedInfo) {
        this.deniedInfo = deniedInfo;
    }
}
