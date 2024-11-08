package vn.edu.usth.facebook.Group;

public class GroupItem {
    String name;
    String post;
    String imageUrl;

    public GroupItem(String name, String post, String imageUrl) {
        this.name = name;
        this.post = post;
        this.imageUrl = imageUrl;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}