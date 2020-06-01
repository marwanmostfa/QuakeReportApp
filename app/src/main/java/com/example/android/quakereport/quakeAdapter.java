package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class quakeAdapter extends ArrayAdapter<Earthquake> {
    public quakeAdapter(Activity context, ArrayList<Earthquake> listData){
        super(context,0,listData);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {} object located at this position in the list
        Earthquake currentQuake= getItem(position);

        TextView magText=(TextView) listItemView.findViewById(R.id.mag);
        DecimalFormat decimalFormatter = new DecimalFormat("0.0");
        magText.setText(String.valueOf(decimalFormatter.format(currentQuake.getQuakeMagnitude())));

        GradientDrawable magnitudeCircle = (GradientDrawable) magText.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentQuake.getQuakeMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        TextView locOffsetText = (TextView) listItemView.findViewById(R.id.locationOffset);
        TextView pLocText = (TextView) listItemView.findViewById(R.id.primaryLocation);
        if(currentQuake.getQuakeLocation().contains("of")) {
            String[] arrStr = currentQuake.getQuakeLocation().split("of");
            locOffsetText.setText(arrStr[0]);
            pLocText.setText(arrStr[1]);
        }
        else{
            locOffsetText.setText("Near the");
            pLocText.setText(currentQuake.getQuakeLocation());
        }

        TextView DateText=(TextView) listItemView.findViewById(R.id.date);
        Date dateObject = new Date(currentQuake.getDate());
        String formattedDate = formatDate(dateObject);
        DateText.setText(formattedDate);

        TextView timeText=(TextView) listItemView.findViewById(R.id.time);
        Date timeObject = new Date(currentQuake.getDate());
        String formattedTime = formatTime(timeObject);
        timeText.setText(formattedTime);

        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magFloor=(int) Math.floor(magnitude);
        switch(magFloor){
            case 0:
            case 1:
                magnitudeColorResourceId=R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId=R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId=R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId=R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId=R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId=R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId=R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId=R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId=R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId=R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
