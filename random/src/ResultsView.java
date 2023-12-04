import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;


public class ResultsView extends JFrame {
    private JList<Product> productList;
    private DefaultListModel<Product> productModel;

    private PredictionController predictionController; // 添加对 PredictionController 的引用


    public ResultsView() {
        this.predictionController = predictionController; // 初始化 PredictionController 引用

        setTitle("Search Results");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        productModel = new DefaultListModel<>();
        productList = new JList<>(productModel);
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(productList), BorderLayout.CENTER);

        JButton selectButton = new JButton("View Details");
        selectButton.addActionListener(e -> onViewDetails());
        add(selectButton, BorderLayout.SOUTH);
    }

    public void displayProducts(List<Product> products) {
        productModel.clear();
        products.forEach(productModel::addElement);
    }

    private void onViewDetails() {
        Product selectedProduct = productList.getSelectedValue();
        if (selectedProduct != null) {
            new ProductDetailView(selectedProduct, predictionController).setVisible(true); // 使用 PredictionController 创建 ProductDetailView
        }
    }
}
