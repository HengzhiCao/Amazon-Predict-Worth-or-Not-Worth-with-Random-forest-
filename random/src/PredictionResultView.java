import javax.swing.*;
import java.awt.*;

public class PredictionResultView extends JFrame {
    public PredictionResultView(String prediction) {
        setTitle("Prediction Result");
        setSize(300, 100);
        setLayout(new FlowLayout());

        add(new JLabel("Prediction: " + prediction));

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> setVisible(false));
        add(closeButton);
    }
}
