package vn.edu.usth.facebook.Page;

public class ListPageItem {

    String name;

    int avatar;

    public ListPageItem(String name,  int avatar) {
        this.name = name;
        this.avatar = avatar;

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
