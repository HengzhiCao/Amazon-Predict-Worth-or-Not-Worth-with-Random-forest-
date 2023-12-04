import java.util.List;

public class ProductController {
    private ProductModel productModel;

    private ProductModel model;
    private PredictionController predictionController;

    public ProductController(ProductModel productModel, PredictionController predictionController) {
        this.productModel = productModel;
        this.model = model;
        this.predictionController = predictionController;
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
        // 创建 ProductDetailView 实例并传递所需的参数
        ProductDetailView detailView = new ProductDetailView(selectedProduct, predictionController);
        detailView.setVisible(true);

        // 可以在这里添加调用预测控制器的代码
        // 例如：String prediction = predictionController.predictProduct(selectedProduct);
    }
    // ... 其他方法 ...
}
