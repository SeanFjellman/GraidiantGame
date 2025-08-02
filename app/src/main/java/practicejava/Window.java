package practicejava;

import javax.swing.*;
import java.awt.*;

public class Window {

    public Window() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame frame = new JFrame("Purple with Radiating Yellow");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLocation(0, 0);
        frame.add(new ShadedPanel());
        frame.setVisible(true);
    }

    private static class ShadedPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            int width = getWidth();
            int height = getHeight();

            // Step 1: Background purple gradient (same as before)
            GradientPaint purpleGradient = new GradientPaint(
                0, height, new Color(50, 0, 70),        // bottom
                0, 0,     new Color(200, 130, 255)      // top
            );
            g2.setPaint(purpleGradient);
            g2.fillRect(0, 0, width, height);

            // Step 2: Add radiating yellow glow in top-right corner
            // Circle center near top-right
            float centerX = width - 100;
            float centerY = 100;
            float radius = 500f;

            // Define color stops (center yellow -> transparent)
            float[] dist = {0f, 1f};
            Color[] colors = {
                new Color(255, 238, 66, 255), // yellow with some alpha
                new Color(255, 238, 66, 0)    // fully transparent
            };

            RadialGradientPaint glow = new RadialGradientPaint(
                centerX, centerY, radius,
                dist, colors,
                MultipleGradientPaint.CycleMethod.NO_CYCLE
            );

            g2.setPaint(glow);
            g2.fillOval((int)(centerX - radius), (int)(centerY - radius), (int)(radius * 2), (int)(radius * 2));
        }
    }
}
