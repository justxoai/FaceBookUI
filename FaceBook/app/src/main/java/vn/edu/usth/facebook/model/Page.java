package vn.edu.usth.facebook.model;

public class Page {
    public Page(Integer id, String name, String description, Integer categoryId, Integer ownedBy, String avatarUrl, String backgroundUrl, String address, String mobile, String email, String instagram) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.ownedBy = ownedBy;
        this.avatarUrl = avatarUrl;
        this.backgroundUrl = backgroundUrl;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.instagram = instagram;
    }
    private Integer id;
    private String name;
    private String description;
    private Integer categoryId;
    private Integer ownedBy;
    private String avatarUrl;
    private String backgroundUrl;
    private String address;
    private String mobile;
    private String email;
    private String instagram;

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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getInstagram() {
        return instagram;
    }
    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
}
