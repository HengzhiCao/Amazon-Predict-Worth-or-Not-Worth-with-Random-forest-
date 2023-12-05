import java.util.List;

public class ProductController {
    private ProductModel productModel;
    private MainView mainView;

    private ProductModel model;
    private PredictionController predictionController;

    private PredictionResultView predictionResultView;

    private DataPreprocessingController dataPreprocessingController;



    public ProductController(ProductModel productModel, PredictionController predictionController, MainView mainView) {
        this.productModel = productModel;
        this.predictionController = predictionController;
        this.mainView = mainView;
        this.predictionResultView = new PredictionResultView();
        this.dataPreprocessingController = new DataPreprocessingController();

    }

    public void handleSearch(double priceFrom, double priceTo, String productName, MainView view) {
        try {
            List<Product> products = productModel.getFilteredProducts(priceFrom, priceTo, productName);
            List<Product> preprocessedProducts = dataPreprocessingController.preprocessProducts(products);
            view.displayProducts(preprocessedProducts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleProductSelection(Product selectedProduct) {
        // 创建 ProductDetailView 实例并传递所需的参数
        ProductDetailView detailView = new ProductDetailView(selectedProduct, predictionController, this);
        detailView.setVisible(true);

        // 可以在这里添加调用预测控制器的代码
        // 例如：String prediction = predictionController.predictProduct(selectedProduct);
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
