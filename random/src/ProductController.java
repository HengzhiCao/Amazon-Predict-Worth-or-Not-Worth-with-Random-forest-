import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductController {
    private ProductModel productModel;
    private MainView mainView;

    private ProductModel model;
    private PredictionController predictionController;

    private PredictionResultView predictionResultView;


    public ProductController(ProductModel productModel, PredictionController predictionController, MainView mainView) {
        this.productModel = productModel;
        this.predictionController = predictionController;
        this.mainView = mainView;
        this.predictionResultView = new PredictionResultView(); // 初始化 PredictionResultView
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
        ProductDetailView detailView = new ProductDetailView(selectedProduct, predictionController, this);
        detailView.setVisible(true);

        // 可以在这里添加调用预测控制器的代码
        // 例如：String prediction = predictionController.predictProduct(selectedProduct);
    }

    public void processPredictionAndRecommend(Product currentProduct) {
        String prediction = predictionController.predictProduct(currentProduct);
        if ("No".equals(prediction)) {
            try {
                List<Product> recommendedProducts = recommendProductsByCategory(currentProduct.getCategory(), currentProduct);
                // 更新视图以显示推荐产品
                updateViewWithRecommendations(recommendedProducts);
            } catch (IOException e) {
                // 处理异常，例如显示错误消息
                e.printStackTrace();
            }
        }
    }

    // 基于类别推荐产品，排除当前产品
    private List<Product> recommendProductsByCategory(String category, Product currentProduct) throws IOException {
        List<Product> productsInCategory = productModel.getProductsByCategory(category);
        return productsInCategory.stream()
                .filter(product -> !product.equals(currentProduct))
                .collect(Collectors.toList());
    }

    private void updateViewWithRecommendations(List<Product> recommendedProducts) {
        mainView.displayRecommendedProducts(recommendedProducts);
    }
    public void handlePredictionResult(Product selectedProduct) {
        String prediction = predictionController.predictProduct(selectedProduct);
        predictionResultView.displayPredictionResult("Prediction: " + prediction);

        if ("No".equals(prediction)) {
            try {
                List<Product> recommendedProducts = productModel.getProductsByCategory(selectedProduct.getCategory());
                recommendedProducts.remove(selectedProduct); // 移除当前选中的产品
                mainView.displayRecommendedProducts(recommendedProducts);
                mainView.notifyUserAboutUpdate();  // 通知用户更新
            } catch (Exception e) {
                // 处理异常
            }
        }
    }

}
