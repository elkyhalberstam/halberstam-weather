package halberstam.weather;

import halberstam.weather.fivedayforcast.FiveDayForcast;
import halberstam.weather.fivedayforcast.List;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;

public class CurrentWeatherView extends JComponent {

    FiveDayForcast fiveDayForcast;

    public CurrentWeatherView() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class);

        FiveDayForcast fiveDayForcast = service.getFiveDayForcast("Passaic").blockingFirst();
        this.fiveDayForcast = fiveDayForcast;
    }

    public void setForcast(FiveDayForcast fiveDayForcast) {
        this.fiveDayForcast = fiveDayForcast;
        repaint();
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int height = getHeight();
        g.translate(0, height);



        java.util.List<List> forcastList = fiveDayForcast.list;
        int currTime = 0;
        int temperature1 = - 5 * (int) forcastList.get(0).main.temp;

        for (int i=0; i<forcastList.size(); i++)
        {
            int temperature2 = - (int) forcastList.get(i).main.temp;

            g.drawLine(currTime, temperature1,currTime+15,temperature2 * 5);

            currTime += 15;
            temperature1 = temperature2 * 5;
        }
    }
}
