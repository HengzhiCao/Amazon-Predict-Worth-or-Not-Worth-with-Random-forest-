import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;


public class MainView extends JFrame {
    private JList<Product> productList;
    private DefaultListModel<Product> productModel;
    private JTextField priceFromField;
    private JTextField priceToField;
    private JTextField productNameField;
    private ProductController controller;

    public MainView(ProductController controller) {
        this.controller = controller;

        setTitle("Product Selection");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setupInputPanel();
        setupProductList();
        setupSelectButton();
    }

    private void setupInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        inputPanel.add(new JLabel("Price From:"));
        priceFromField = new JTextField(10);
        inputPanel.add(priceFromField);

        inputPanel.add(new JLabel("Price To:"));
        priceToField = new JTextField(10);
        inputPanel.add(priceToField);

        inputPanel.add(new JLabel("Product Name:"));
        productNameField = new JTextField(10);
        inputPanel.add(productNameField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> onSearch());
        inputPanel.add(searchButton);

        add(inputPanel, BorderLayout.NORTH);
    }

    private void setupProductList() {
        productModel = new DefaultListModel<>();
        productList = new JList<>(productModel);
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(productList), BorderLayout.CENTER);
    }

    private void setupSelectButton() {
        JButton selectButton = new JButton("Select Product");
        selectButton.addActionListener(e -> onSelectProduct());
        add(selectButton, BorderLayout.SOUTH);
    }

    private void onSearch() {
        double priceFrom = Double.parseDouble(priceFromField.getText());
        double priceTo = Double.parseDouble(priceToField.getText());
        String productName = productNameField.getText();
        controller.handleSearch(priceFrom, priceTo, productName, this);
    }

    private void onSelectProduct() {
        Product selectedProduct = productList.getSelectedValue();
        if (selectedProduct != null) {
            controller.handleProductSelection(selectedProduct);
        }
    }

    public void displayProducts(List<Product> products) {
        productModel.clear();
        for (Product product : products) {
            productModel.addElement(product);
        }
    }

    // ... 其他方法 ...
}