package sk.furmi.myapplication.models;



public class Physic {

    private double x;

    private double y;

    private double maxTime;

    public double maxTime() {
        return maxTime;
    }

    public void maxTime(double maxTime) {
        this.maxTime = maxTime;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
