package sk.furmi.myapplication;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sk.furmi.myapplication.models.Physic;
import sk.furmi.myapplication.models.Projectile;
import sk.furmi.myapplication.service.JsonPlaceHolderAPI;


public class ResultTableActivity extends AppCompatActivity {

    private List<Projectile> projectiles;

    private List<Projectile> projectilesRelative;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_table);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        listView= (ListView)findViewById(R.id.listView);

        float angle = intent.getFloatExtra("angle",0);
        float velocity = intent.getFloatExtra("velocity",0);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        Call<List<Projectile>>  call= jsonPlaceHolderAPI.getProjectiles(velocity,angle);




        call.enqueue(new Callback<List<Projectile>>() {

            @Override
            public void onResponse(Call<List<Projectile>> call, Response<List<Projectile>> response) {
                List<HashMap<String, String>> fillMaps = new ArrayList<>();
                String[] from = new String[] {"rowid", "X", "Y", "time"};
                int[] to = new int[] { R.id.item1, R.id.item2, R.id.item3, R.id.item4 };

                if (response.isSuccessful()){


                    projectiles = response.body();

                    for(int i = 0; i < projectiles.size(); i++){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("rowid", "" + i);
                        map.put("X", "  X="+projectiles.get(i).getxPos());
                        map.put("Y", "  Y=" + projectiles.get(i).getyPos());
                        map.put("time", "  time=" + projectiles.get(i).getTimeVal());
                        fillMaps.add(map);

                    }


                }else{
                    HashMap<String, String> map = new HashMap<>();
                    map.put("rowid", "" + 0);
                    map.put("X", "  X="+response.body());
                    map.put("Y", "  Y=" + response.body());
                    map.put("time", "  time=" +response.body());


                }
                SimpleAdapter adapter = new SimpleAdapter(ResultTableActivity.this, fillMaps, R.layout.grid_item, from, to);
                listView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Projectile>> call, Throwable t) {
                System.out.println(t.getMessage());
                //listView.setAdapter();
            }
        });

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        Call<List<Projectile>> callRelative = jsonPlaceHolderAPI.getProjectilesRelative(velocity,angle,size.x,size.y);


        callRelative.enqueue(new Callback<List<Projectile>>() {
            @Override
            public void onResponse(Call<List<Projectile>> call, Response<List<Projectile>> response) {
                if (response.isSuccessful()){
                    projectilesRelative = response.body();
                }
                else {
                    System.out.println(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Projectile>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });




    }

    public void getAnimation(View view){
        Intent intentAnimation = new Intent(this, AnimationActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)projectilesRelative);
        intentAnimation.putExtra("BUNDLE",args);
        startActivity(intentAnimation);
    }

    public void getGraph(View view){
        Intent intentGraph = new Intent(this, GraphActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)projectiles);
        intentGraph.putExtra("BUNDLE",args);
        startActivity(intentGraph);

    }
}
