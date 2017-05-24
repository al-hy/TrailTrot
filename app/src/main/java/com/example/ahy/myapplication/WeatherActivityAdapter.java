package com.example.ahy.myapplication;

/**
 * Created by ahy on 5/16/17.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by ahy on 5/8/17.
 */

public class WeatherActivityAdapter extends RecyclerView.Adapter<WeatherActivityAdapter.ViewHolder> {

    private Context context;
    private int layoutResource;
    private List<Forecast> dailyForecast;
    //private TextView weather;
    private YelpInfo yelpInfo;
    private String timezone;


    public WeatherActivityAdapter(@NonNull List<Forecast> dailyForecast, String timezone) {

        this.dailyForecast = dailyForecast;
        this.timezone = timezone;

    }


    @Override
    public WeatherActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherActivityAdapter.ViewHolder holder, int position) {

        String[] day = {"Sun", "Mon", "Tue", "Wed", "Thu","Fri", "Sat"};

        TimeZone timeZone = TimeZone.getDefault();
        timeZone.setID(timezone);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(timeZone);
        calendar.setTimeInMillis(dailyForecast.get(position).getTime() * 1000);
        int index = calendar.get(Calendar.DAY_OF_WEEK);

        Log.i("INDEX", Integer.toString(index));


        holder.dayOfWeek.setText(day[index-1]);
        holder.maxTemp.setText(Integer.toString((int) (Math.round(dailyForecast.get(position).getMaxTemp()*1)/1.0)) + "\u00b0");
        holder.minTemp.setText(Integer.toString((int) (Math.round(dailyForecast.get(position).getMinTemp()*1)/1.0)) + "\u00b0");


        switch (dailyForecast.get(position).getIcon()) {

            case "clear-day":
                holder.forecastIcon.setImageResource(R.drawable.clearday);
                break;
            case "clear-night":
                holder.forecastIcon.setImageResource(R.drawable.clearday);
                break;
            case "rain":
                holder.forecastIcon.setImageResource(R.drawable.rainday);
                break;
            case "snow":
                holder.forecastIcon.setImageResource(R.drawable.snow);
                break;
            case "sleet":
                holder.forecastIcon.setImageResource(R.drawable.snow);
                break;
            case "wind":
                holder.forecastIcon.setImageResource(R.drawable.daywind);
                break;
            case "fog":
                holder.forecastIcon.setImageResource(R.drawable.fog);
                break;
            case "cloudy":
                holder.forecastIcon.setImageResource(R.drawable.cloudy);
                break;
            case "partly-cloudy-day":
                holder.forecastIcon.setImageResource(R.drawable.partlycloudyday);
                break;
            case "partly-cloudy-night":
                holder.forecastIcon.setImageResource(R.drawable.partlycloudyday);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return dailyForecast.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView maxTemp;
        public TextView minTemp;
        public ImageView forecastIcon;
        public TextView dayOfWeek;

        public ViewHolder(View itemView) {
            super(itemView);
            forecastIcon = (ImageView) itemView.findViewById(R.id.forecastIcon);
            maxTemp = (TextView) itemView.findViewById(R.id.maxTemp);
            minTemp = (TextView) itemView.findViewById(R.id.minTemp);
            dayOfWeek = (TextView) itemView.findViewById(R.id.dayOfWeek);
        }
    }
}

