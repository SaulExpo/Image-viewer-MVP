package ulpgc.es;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private ImageDisplay imageDisplay;
    private final Map<String, Command> commands;



    public MainFrame() {
        this.commands = new HashMap<>();
        setTitle("Image-Viewer");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        setLayout(new BorderLayout());
        add(createImageDisplay());
        add(createToolbar(), BorderLayout.SOUTH);
    }

    private Component createToolbar() {
        JPanel panel = new JPanel();
        panel.add(createButton("<"));
        panel.add(createButton(">"));
        panel.setBackground(Color.BLACK);
        return panel;
    }

    private Component createButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(e -> commands.get(label).execute());
        button.setBackground(new Color(30, 100, 250));
        button.setPreferredSize(new Dimension(250, 40));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        return button;
    }

    private Component createImageDisplay() {
        SwingImageDisplay display = new SwingImageDisplay();
        this.imageDisplay = display;
        return display;
    }


    public MainFrame add(String name, Command command) {
        commands.put(name, command);
        return this;
    }

    public ImageDisplay imageDisplay() {
        return imageDisplay;
    }
}
