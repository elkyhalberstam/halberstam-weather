package halberstam.weather;

import halberstam.weather.fivedayforcast.FiveDayForcast;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;

public class CurrentWeatherFrame extends JFrame {
    public CurrentWeatherFrame()
    {
        setSize(650, 600);
        setTitle("Current Weather");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel enterCityPanel = new JPanel();
        enterCityPanel.setLayout(new FlowLayout());

        JTextField enterCityName  = new JTextField("Passaic             ");
        JButton enterCityButton = new JButton("Enter City");

        enterCityPanel.add(enterCityName);
        enterCityPanel.add(enterCityButton);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(enterCityPanel, BorderLayout.NORTH);

        setContentPane(panel);
        CurrentWeatherView currentWeatherView = new CurrentWeatherView();
        panel.add(currentWeatherView, BorderLayout.CENTER);

        enterCityButton.addActionListener(e -> {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();

            OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class);

            FiveDayForcast fiveDayForcast = service.getFiveDayForcast(enterCityName.getText()).blockingFirst();
            currentWeatherView.setForcast(fiveDayForcast);

        });
    }
}
