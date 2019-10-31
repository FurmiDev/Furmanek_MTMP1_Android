package sk.furmi.myapplication.models;


import android.graphics.Canvas;
import android.graphics.RectF;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Projectile implements Serializable{

    public Projectile() {
    }

    public Projectile(float timeVal, float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.timeVal = timeVal;
    }

    public RectF oval;

    @SerializedName("xpos")
    float xPos;
    @SerializedName("ypos")
    float yPos;
    @SerializedName("timeVal")
    float timeVal;

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public float getTimeVal() {
        return timeVal;
    }

    public void setTimeVal(float timeVal) {
        this.timeVal = timeVal;
    }

    public void move(Canvas canvas, float newX, float newY) {




    }
}
