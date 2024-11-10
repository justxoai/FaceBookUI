package vn.edu.usth.facebook.Page;

import android.graphics.Bitmap;

public class ListPageItem {
    private final String name;
    private final Bitmap avatarBitmap; // Use Bitmap instead of Drawable resource ID
    public ListPageItem(String name, Bitmap avatarBitmap) {
        this.name = name;
        this.avatarBitmap = avatarBitmap;
    }
    public String getName() {
        return name;
    }
    public Bitmap getAvatarBitmap() {
        return avatarBitmap;
    }
}