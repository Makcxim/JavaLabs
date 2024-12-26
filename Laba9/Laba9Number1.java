import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Laba9Number1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("kek");
        frame.setPreferredSize(new Dimension(400, 400));

        final MyPanel panel = new MyPanel();
        frame.add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton increaseSpeedButton = new JButton("+");
        JButton decreaseSpeedButton = new JButton("-");

        buttonPanel.add(increaseSpeedButton);
        buttonPanel.add(decreaseSpeedButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        increaseSpeedButton.addActionListener(e -> panel.increaseSpeed()); // + 1
        decreaseSpeedButton.addActionListener(e -> panel.decreaseSpeed()); // - 1

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.updatePosition();
                frame.setTitle("Speed: " + panel.getSpeed());
            }
        });

        timer.start();
    }
}

class MyPanel extends JPanel {
    private int x = 10;
    private int y = 200;
    private int speed = 5;
    private int size = 10;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graph = (Graphics2D) g;
        graph.setColor(Color.RED);
        graph.fillOval(x, y, size, size);
    }

    public void updatePosition() {
        x += speed;
        if (x > getWidth()) {
            x = 0;
        }
        repaint();
    }

    public void increaseSpeed() {
        speed += 1;
    }

    public void decreaseSpeed() {
        if (speed > 1) {
            speed -= 1;
        }
    }

    public int getSpeed() {
        return speed;
    }
}
