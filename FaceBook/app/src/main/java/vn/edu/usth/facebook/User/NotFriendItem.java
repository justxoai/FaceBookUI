package vn.edu.usth.facebook.User;

public class NotFriendItem {


    String name;
    String mutal_friend;
    int avatar;
    int friend_avatar;

    public NotFriendItem(String name, String mutal_friend, int avatar, int friend_avatar) {
        this.name = name;
        this.mutal_friend = mutal_friend;
        this.avatar = avatar;
        this.friend_avatar = friend_avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMutal_friend() {
        return mutal_friend;
    }

    public void setMutal_friend(String mutalfriend) {
        this.mutal_friend = mutalfriend;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getFriend_avatar() {
        return friend_avatar;
    }

    public void setFriend_avatar(int postimage) {
        this.friend_avatar = friend_avatar;
    }

}
