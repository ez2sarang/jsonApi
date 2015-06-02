package com.fast2.zimin.navigator.bean.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ez2sarang on 15. 4. 8..
 */
@Entity(name = "Subscriber")
@Table(name = "SubscriberId")
public class Subscriber {
    @Id
    private String subscriberIdRegIdx;
    private String contractId;
    private String subscriberId;
    private String idType;
    private String smartCardId;
    private String stbModel;
    private String stbType;
    private String password;
    private String menuGroup;
    private String useYn;
    private String userName;
    private String userPhone;
    private String userGrade;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getMenuGroup() {
        return menuGroup;
    }

    public void setMenuGroup(String menuGroup) {
        this.menuGroup = menuGroup;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmartCardId() {
        return smartCardId;
    }

    public void setSmartCardId(String smartCardId) {
        this.smartCardId = smartCardId;
    }

    public String getStbModel() {
        return stbModel;
    }

    public void setStbModel(String stbModel) {
        this.stbModel = stbModel;
    }

    public String getStbType() {
        return stbType;
    }

    public void setStbType(String stbType) {
        this.stbType = stbType;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getSubscriberIdRegIdx() {
        return subscriberIdRegIdx;
    }

    public void setSubscriberIdRegIdx(String subscriberIdRegIdx) {
        this.subscriberIdRegIdx = subscriberIdRegIdx;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    /*@Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Subscriber that = (Subscriber) obj;

            if (subscriberId != that.subscriberId) return false;
            if (password != null ? !password.equals(that.password) : that.password != null) return false;

            return true;
        }*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscriber that = (Subscriber) o;

        if (subscriberIdRegIdx != null ? !subscriberIdRegIdx.equals(that.subscriberIdRegIdx) : that.subscriberIdRegIdx != null)
            return false;
        if (contractId != null ? !contractId.equals(that.contractId) : that.contractId != null) return false;
        if (subscriberId != null ? !subscriberId.equals(that.subscriberId) : that.subscriberId != null) return false;
        if (idType != null ? !idType.equals(that.idType) : that.idType != null) return false;
        if (smartCardId != null ? !smartCardId.equals(that.smartCardId) : that.smartCardId != null) return false;
        if (stbModel != null ? !stbModel.equals(that.stbModel) : that.stbModel != null) return false;
        if (stbType != null ? !stbType.equals(that.stbType) : that.stbType != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (menuGroup != null ? !menuGroup.equals(that.menuGroup) : that.menuGroup != null) return false;
        if (useYn != null ? !useYn.equals(that.useYn) : that.useYn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = subscriberIdRegIdx != null ? subscriberIdRegIdx.hashCode() : 0;
        result = 31 * result + (contractId != null ? contractId.hashCode() : 0);
        result = 31 * result + (subscriberId != null ? subscriberId.hashCode() : 0);
        result = 31 * result + (idType != null ? idType.hashCode() : 0);
        result = 31 * result + (smartCardId != null ? smartCardId.hashCode() : 0);
        result = 31 * result + (stbModel != null ? stbModel.hashCode() : 0);
        result = 31 * result + (stbType != null ? stbType.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (menuGroup != null ? menuGroup.hashCode() : 0);
        result = 31 * result + (useYn != null ? useYn.hashCode() : 0);
        return result;
    }

}
