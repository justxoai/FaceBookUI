package vn.edu.usth.facebook.Search;

public class UserSearchItem {

    String name;
    String content;
    String imageAvatarUrl;

    public UserSearchItem(String name, String content, String imageAvatarUrl) {
        this.name = name;
        this.content = content;
        this.imageAvatarUrl = imageAvatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getImageAvatarUrl() {
        return imageAvatarUrl;
    }

    public void setImageAvatarUrl(String imageAvatarUrl) {
        this.imageAvatarUrl = imageAvatarUrl;
    }

    @Override
    public String toString() {
        return "UserSearchItem{" +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", imageAvatarUrl='" + imageAvatarUrl + '\'' +
                '}';
    }
}
