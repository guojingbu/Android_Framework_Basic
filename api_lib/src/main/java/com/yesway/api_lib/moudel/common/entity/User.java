package com.yesway.api_lib.moudel.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User implements Parcelable {
    /**
     * 用户id
     */
    @Id
    private String id;
    /**
     * 车厂id
     */
    private String oem_id;
    /**
     * 姓名
     */
    private String customerName;
    /**
     * 所在省份
     */
    private String customerProvince;
    /**
     * 所在省份Id
     */
    private String customerProvinceId;
    /**
     * 所在城市
     */
    private String customerCity;
    /**
     * 所在城市Id
     */
    private String customerCityId;
    /**
     * 所在区域
     */
    private String customerArea;
    /**
     * 详细地址
     */
    private String customerAddress;
    /**
     * 昵称
     */
    private String customerNickName;
    /**
     * 头像
     */
    private String customerAvatar;
    /**
     * 年龄
     */
    private int customerAge;
    /**
     * 性别：0=女；1=男；
     */
    private int customerGender;
    /**
     * 生日
     */
    private String customerBirthday;
    /**
     * 生日
     */
    private String customerBirthdayString;
    /**
     * 手机号
     */
    private String customerPhoneNumber;

    protected User(Parcel in) {
        id = in.readString();
        oem_id = in.readString();
        customerName = in.readString();
        customerProvince = in.readString();
        customerProvinceId = in.readString();
        customerCity = in.readString();
        customerCityId = in.readString();
        customerArea = in.readString();
        customerAddress = in.readString();
        customerNickName = in.readString();
        customerAvatar = in.readString();
        customerAge = in.readInt();
        customerGender = in.readInt();
        customerBirthday = in.readString();
        customerBirthdayString = in.readString();
        customerPhoneNumber = in.readString();
    }

    @Generated(hash = 894422673)
    public User(String id, String oem_id, String customerName,
            String customerProvince, String customerProvinceId, String customerCity,
            String customerCityId, String customerArea, String customerAddress,
            String customerNickName, String customerAvatar, int customerAge,
            int customerGender, String customerBirthday,
            String customerBirthdayString, String customerPhoneNumber) {
        this.id = id;
        this.oem_id = oem_id;
        this.customerName = customerName;
        this.customerProvince = customerProvince;
        this.customerProvinceId = customerProvinceId;
        this.customerCity = customerCity;
        this.customerCityId = customerCityId;
        this.customerArea = customerArea;
        this.customerAddress = customerAddress;
        this.customerNickName = customerNickName;
        this.customerAvatar = customerAvatar;
        this.customerAge = customerAge;
        this.customerGender = customerGender;
        this.customerBirthday = customerBirthday;
        this.customerBirthdayString = customerBirthdayString;
        this.customerPhoneNumber = customerPhoneNumber;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(oem_id);
        dest.writeString(customerName);
        dest.writeString(customerProvince);
        dest.writeString(customerProvinceId);
        dest.writeString(customerCity);
        dest.writeString(customerCityId);
        dest.writeString(customerArea);
        dest.writeString(customerAddress);
        dest.writeString(customerNickName);
        dest.writeString(customerAvatar);
        dest.writeInt(customerAge);
        dest.writeInt(customerGender);
        dest.writeString(customerBirthday);
        dest.writeString(customerBirthdayString);
        dest.writeString(customerPhoneNumber);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOem_id() {
        return oem_id;
    }

    public void setOem_id(String oem_id) {
        this.oem_id = oem_id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerProvince() {
        return customerProvince;
    }

    public void setCustomerProvince(String customerProvince) {
        this.customerProvince = customerProvince;
    }

    public String getCustomerProvinceId() {
        return customerProvinceId;
    }

    public void setCustomerProvinceId(String customerProvinceId) {
        this.customerProvinceId = customerProvinceId;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerCityId() {
        return customerCityId;
    }

    public void setCustomerCityId(String customerCityId) {
        this.customerCityId = customerCityId;
    }

    public String getCustomerArea() {
        return customerArea;
    }

    public void setCustomerArea(String customerArea) {
        this.customerArea = customerArea;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerNickName() {
        return customerNickName;
    }

    public void setCustomerNickName(String customerNickName) {
        this.customerNickName = customerNickName;
    }

    public String getCustomerAvatar() {
        return customerAvatar;
    }

    public void setCustomerAvatar(String customerAvatar) {
        this.customerAvatar = customerAvatar;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }

    public int getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(int customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerBirthday() {
        return customerBirthday;
    }

    public void setCustomerBirthday(String customerBirthday) {
        this.customerBirthday = customerBirthday;
    }

    public String getCustomerBirthdayString() {
        return customerBirthdayString;
    }

    public void setCustomerBirthdayString(String customerBirthdayString) {
        this.customerBirthdayString = customerBirthdayString;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
}
