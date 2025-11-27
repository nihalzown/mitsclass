import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class exp8_2 extends JFrame implements ActionListener {
    private JRadioButton redButton;
    private JRadioButton yellowButton;
    private JRadioButton greenButton;
    private JPanel lightPanel;

    public exp8_2() {
        setTitle("Traffic Light Simulator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create radio buttons
        redButton = new JRadioButton("Red");
        yellowButton = new JRadioButton("Yellow");
        greenButton = new JRadioButton("Green");

        // Group the radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(redButton);
        group.add(yellowButton);
        group.add(greenButton);

        // Add action listeners
        redButton.addActionListener(this);
        yellowButton.addActionListener(this);
        greenButton.addActionListener(this);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));
        buttonPanel.add(redButton);
        buttonPanel.add(yellowButton);
        buttonPanel.add(greenButton);

        // Create a panel for the lights
        lightPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(100, 50, 100, 300);

                if (redButton.isSelected()) {
                    g.setColor(Color.RED);
                    g.fillOval(125, 75, 50, 50);
                } else {
                    g.setColor(Color.DARK_GRAY);
                    g.fillOval(125, 75, 50, 50);
                }

                if (yellowButton.isSelected()) {
                    g.setColor(Color.YELLOW);
                    g.fillOval(125, 175, 50, 50);
                } else {
                    g.setColor(Color.DARK_GRAY);
                    g.fillOval(125, 175, 50, 50);
                }

                if (greenButton.isSelected()) {
                    g.setColor(Color.GREEN);
                    g.fillOval(125, 275, 50, 50);
                } else {
                    g.setColor(Color.DARK_GRAY);
                    g.fillOval(125, 275, 50, 50);
                }
            }
        };

        add(buttonPanel, BorderLayout.WEST);
        add(lightPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        lightPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            exp8_2 frame = new exp8_2();
            frame.setVisible(true);
        });
    }
}