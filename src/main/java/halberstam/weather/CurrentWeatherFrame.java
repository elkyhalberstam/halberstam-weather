package halberstam.weather;

import javax.swing.*;
import java.awt.*;

public class CurrentWeatherFrame extends JFrame {
    public CurrentWeatherFrame()
    {
        setSize(800, 600);
        setTitle("Current Weather");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new CurrentWeatherView(), BorderLayout.CENTER);

        setContentPane(panel);
    }
}
