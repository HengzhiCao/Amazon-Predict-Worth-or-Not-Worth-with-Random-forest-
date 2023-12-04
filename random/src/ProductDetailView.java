import javax.swing.*;
import java.awt.*;

public class ProductDetailView extends JFrame {
    public ProductDetailView(Product product) {
        setTitle("Product Details");
        setSize(300, 200);
        setLayout(new GridLayout(0, 1));

        add(new JLabel("Name: " + product.getName()));
        add(new JLabel("Price: " + product.getPrice()));
        add(new JLabel("Category: " + product.getDescription()));
        add(new JLabel(new ImageIcon(product.getImageUrl()))); // 显示图片，需要有效的URL

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> setVisible(false));
        add(closeButton);
    }
}
