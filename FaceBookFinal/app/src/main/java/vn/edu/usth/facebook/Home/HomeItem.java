package vn.edu.usth.facebook.Home;

public class HomeItem {

    String name;
    String time;
    int image;
    int secondimage;

    public HomeItem(String name, String time, int image, int secondimage) {
        this.name = name;
        this.time = time;
        this.image = image;
        this.secondimage = secondimage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getSecondImage() {
        return secondimage;
    }
    public void setSecondImage(int secondimage) {
        this.secondimage = secondimage;
    }
}
