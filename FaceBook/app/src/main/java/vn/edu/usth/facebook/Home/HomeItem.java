package vn.edu.usth.facebook.Home;

public class HomeItem {
    private String name;
    private String time;
    private String content;
    private int avatar;
    private String avatarUrl;
    private int postImage;
    private String postImageUrl;
    private String postVideoUrl;

    // Constructor for drawable-based posts
    public HomeItem(String name, String time, String content, int avatar, int postImage) {
        this.name = name;
        this.time = time;
        this.content = content;
        this.avatar = avatar;
        this.postImage = postImage;
    }

    // Constructor for URL-based posts
    public HomeItem(String name, String time, String content, int avatar, String postImageUrl) {
        this.name = name;
        this.time = time;
        this.content = content;
        this.avatar = avatar;
        this.postImageUrl = postImageUrl;
    }

    public HomeItem(String name, String time, String content, String avatarUrl, String postImageUrl) {
        this.name = name;
        this.time = time;
        this.content = content;
        this.avatarUrl = avatarUrl;
        this.postImageUrl = postImageUrl;
    }

    public HomeItem(String name, String time, String content, String avatarUrl, String postImageUrl, String postVideoUrl) {
        this.name = name;
        this.time = time;
        this.content = content;
        this.avatarUrl = avatarUrl;
        this.postImageUrl = postImageUrl;
        this.postVideoUrl = postVideoUrl;
    }

    public HomeItem(String name, String time, String content, String avatarUrl) {
        this.name = name;
        this.time = time;
        this.content = content;
        this.avatarUrl = avatarUrl;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPostVideoUrl() {
        return postVideoUrl;
    }

    public void setPostVideoUrl(String postVideoUrl) {
        this.postVideoUrl = postVideoUrl;
    }

    @Override
    public String toString() {
        return "HomeItem{" +
                "avatar=" + avatar +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", postImage=" + postImage +
                ", postImageUrl='" + postImageUrl + '\'' +
                ", postVideoUrl='" + postVideoUrl + '\'' +
                '}';
    }
}
