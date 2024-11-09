package vn.edu.usth.facebook.Profile;

public class ProfileItem {

    String name;
    String time;
    String content;
    String avatarUrl;
    String postImageUrl;

    public ProfileItem(String name, String time, String content, String avatarUrl, String postImageUrl) {
        this.name = name;
        this.time = time;
        this.content = content;
        this.avatarUrl = avatarUrl;
        this.postImageUrl = postImageUrl;
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

    @Override
    public String toString() {
        return "ProfileItem{" +
                "avatarUrl='" + avatarUrl + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", postImageUrl='" + postImageUrl + '\'' +
                '}';
    }
}
