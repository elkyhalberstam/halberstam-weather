package halberstam.weather;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.awt.*;

public class CurrentWeatherFrame extends JFrame {

    private ForecastWeatherController controller;
    private final CurrentWeatherView view;

    private CurrentWeatherController currentWeatherController;

    @Inject

    public CurrentWeatherFrame(CurrentWeatherView view,
                               ForecastWeatherController controller,
                               CurrentWeatherController currentWeatherController,
                               @Named("imageLabel") JLabel imageLabel,
                               @Named("degreesLabel") JLabel degreesLabel)
    {
        this.view = view;
        this.controller =  controller;
        this.currentWeatherController = currentWeatherController;

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


        JPanel currentWeatherPanel = new JPanel();
        currentWeatherPanel.add(imageLabel);
        currentWeatherPanel.add(degreesLabel);
        enterCityPanel.add(currentWeatherPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        panel.add(this.view, BorderLayout.CENTER);

        controller.updateWeather(enterCityName.getText());
        currentWeatherController.updateWeather(enterCityName.getText());

        enterCityButton.addActionListener(e -> {
            this.controller.updateWeather(enterCityName.getText());
            this.currentWeatherController.updateWeather(enterCityName.getText());
        });
    }


}
