package halberstam.weather;

import javax.swing.*;
import java.awt.*;

public class CurrentWeatherView extends JComponent {
    int x = 0;
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawLine(0,0,100,100);
        g.drawOval(0,0,100,100);

        g.setColor(Color.GREEN);

        int height = getHeight();
        g.fillOval(x-50,height - 50,100,100);

        x++;
    }
}
