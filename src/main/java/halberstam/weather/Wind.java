package halberstam.weather;

public class Wind {
    private double speed;
    private double deg;
    private double gust;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int input) {
        this.speed = input;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(int input) {
        this.deg = input;
    }

    public double getGust() {
        return gust;
    }

    public void setGust(int input) {
        this.gust = input;
    }
}
