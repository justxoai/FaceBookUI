package vn.edu.usth.facebook.retrofit;

public enum IPs {
    MINOXD("192.168.212.77"),;
    private final String protocol = "http";
    private final String IP;
    private final int port = 8080;
    private final String path = "/api/v1";
    IPs(String IP) {
        this.IP = IP;
    }

    public String getFullIP() {
        return protocol + "://" + IP + ":" + port + "/";
    }
}
