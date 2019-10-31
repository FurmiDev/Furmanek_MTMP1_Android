package sk.furmi.myapplication.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sk.furmi.myapplication.models.Projectile;

public interface JsonPlaceHolderAPI {

    @GET("/generateData")
    Call <List<Projectile>> getProjectiles(@Query("velocity") float velocity, @Query("angle") float angle);

    @GET("/generateDataRelative")
    Call <List<Projectile>> getProjectilesRelative(@Query("velocity") float velocity, @Query("angle") float angle, @Query("dWidth") int dWidth, @Query("dHeight") int hWidth);
}
