import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
public class ProductController {
    private ProductModel productModel;

    public ProductController(ProductModel productModel) {
        this.productModel = productModel;
    }

    public void handleSearch(double priceFrom, double priceTo, String productName, MainView view) {
        try {
            List<Product> products = productModel.getFilteredProducts(priceFrom, priceTo, productName);
            view.displayProducts(products);
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，例如显示错误消息
        }
    }

    public void handleProductSelection(Product selectedProduct) {
        // 处理用户选择的商品
        System.out.println("Selected Product: " + selectedProduct.getName());
        // 这里可以更新视图或调用其他业务逻辑
    }

    // ... 其他方法 ...
}
