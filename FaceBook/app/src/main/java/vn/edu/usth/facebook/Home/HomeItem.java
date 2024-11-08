package vn.edu.usth.facebook.Home;

public class HomeItem {
    private String name;
    private String time;
    private String content;
    private int avatar;
    private int postimage; // drawable resource ID
    private String postimageUrl; // URL for uploaded image

    // Constructor for drawable-based posts
    public HomeItem(String name, String time, String content, int avatar, int postimage) {
        this.name = name;
        this.time = time;
        this.content = content;
        this.avatar = avatar;
        this.postimage = postimage;
    }

    // Constructor for URL-based posts
    public HomeItem(String name, String time, String content, int avatar, String postimageUrl) {
        this.name = name;
        this.time = time;
        this.content = content;
        this.avatar = avatar;
        this.postimageUrl = postimageUrl;
    }

    public String getName() { return name; }
    public String getTime() { return time; }
    public String getContent() { return content; }
    public int getAvatar() { return avatar; }
    public int getPostimage() { return postimage; }
    public String getPostimageUrl() { return postimageUrl; }
}
