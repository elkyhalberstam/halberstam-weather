package halberstam.weather;

import halberstam.weather.fivedayforecast.FiveDayForecast;
import halberstam.weather.fivedayforecast.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;

@Singleton
public class CurrentWeatherView extends JComponent {

    private FiveDayForecast fiveDayForecast;

    @Inject

    public CurrentWeatherView() {

    }
    public void setForecast(FiveDayForecast fiveDayForecast) {
        this.fiveDayForecast = fiveDayForecast;
        repaint();
    }
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int height = getHeight();
        g.translate(0, height);


        if (fiveDayForecast != null)
        {
            java.util.List<List> forecastList = fiveDayForecast.list;
            int currTime = 0;
            int temperature1 = -5 * (int) forecastList.get(0).main.temp;

            for (int i = 0; i < forecastList.size(); i++) {
                int temperature2 = -(int) forecastList.get(i).main.temp;

                g.drawLine(currTime, temperature1, currTime + 15, temperature2 * 5);

                currTime += 15;
                temperature1 = temperature2 * 5;
            }
        }
    }
}
