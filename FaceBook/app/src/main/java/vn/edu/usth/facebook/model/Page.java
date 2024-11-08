package vn.edu.usth.facebook.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Page {
    public Page() {
    }

    public Page(Integer id, String name, Integer categoryId, Integer ownedBy, String avatarUrl, String backgroundUrl, String address, String mobile, String email, String instagram) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.ownedBy = ownedBy;
        this.avatarUrl = avatarUrl;
        this.backgroundUrl = backgroundUrl;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.instagram = instagram;
    }

//    @SerializedName("id")
    private Integer id;
//    @SerializedName("name")
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(Integer ownedBy) {
        this.ownedBy = ownedBy;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

//    @SerializedName("categoryId")
    private Integer categoryId;
//    @SerializedName("ownedBy")
    private Integer ownedBy;
//    @SerializedName("avatarUrl")
    private String avatarUrl;
//    @SerializedName("backgroundUrl")
    private String backgroundUrl;
//    @SerializedName("address")
    private String address;
//    @SerializedName("mobile")
    private String mobile;
//    @SerializedName("email")
    private String email;
//    @SerializedName("instagram")
    private String instagram;
}
