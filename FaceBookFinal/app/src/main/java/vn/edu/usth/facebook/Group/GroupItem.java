package vn.edu.usth.facebook.Group;

public class GroupItem {
    String name;
    String post;
    int avatar;

    public GroupItem(String name, String post, int avatar) {
        this.name = name;
        this.post = post;
        this.avatar = avatar;
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

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}