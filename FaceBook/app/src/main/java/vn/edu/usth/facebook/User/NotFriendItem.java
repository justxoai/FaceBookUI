package vn.edu.usth.facebook.User;

import android.graphics.Bitmap;

public class NotFriendItem {

    String name;
    Bitmap avatarBitmap;


    public NotFriendItem(String name, Bitmap avatarBitmap) {
        this.name = name;
        this.avatarBitmap = avatarBitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getAvatarBitmap() {
        return avatarBitmap;
    }

    public void setAvatarBitmap(Bitmap avatarBitmap) {
        this.avatarBitmap = avatarBitmap;
    }

}
