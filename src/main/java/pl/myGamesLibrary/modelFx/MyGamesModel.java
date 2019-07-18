package pl.myGamesLibrary.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.myGamesLibrary.controllers.ProductController;
import pl.myGamesLibrary.database.dao.ProductDao;
import pl.myGamesLibrary.database.models.Product;
import pl.myGamesLibrary.database.models.User;
import pl.myGamesLibrary.utils.DialogUtils;
import pl.myGamesLibrary.utils.FxmlUtils;
import pl.myGamesLibrary.utils.converters.ConverterProduct;
import pl.myGamesLibrary.utils.exceptions.ApplicationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MyGamesModel {

    private ObservableList<ProductFx> productFxObservableList= FXCollections.observableArrayList();
    private ObjectProperty<ProductFx> productFxObjectProperty = new SimpleObjectProperty<>();

    private User user;


    public void init() throws SQLException, ApplicationException {
        this.productFxObservableList.clear();
        initGameListView();
    }

    private void initGameListView() throws SQLException, ApplicationException {
        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.findAllByUserId(this.user.getId());

        products.forEach(c -> {
            ProductFx productFx = ConverterProduct.convertToProductFx(c);
            this.productFxObservableList.add(productFx);
        });
    }
    
    public void initEmptyProduct(){
        setProductFxObjectProperty(null);
    }

    public void addProduct() throws SQLException, ApplicationException {
        FXMLLoader loader = FxmlUtils.getLoader("/fxml/AddProduct.fxml");
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        ProductController productController = loader.getController();
        productController.getProductModel().init(this.user);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(FxmlUtils.getResourceBundle().getString("addproduct.title"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        init();
    }

    public void deleteProduct() throws ApplicationException, SQLException {
        ProductDao productDao = new ProductDao();
        productDao.deleteById(Product.class, this.productFxObjectProperty.get().getId());
        init();
    }


    public ObservableList<ProductFx> getProductFxObservableList() {
        return productFxObservableList;
    }

    public void setProductFxObservableList(ObservableList<ProductFx> productFxObservableList) {
        this.productFxObservableList = productFxObservableList;
    }

    public ProductFx getProductFxObjectProperty() {

        return productFxObjectProperty.get();
    }

    public ObjectProperty<ProductFx> productFxObjectPropertyProperty() {
        return productFxObjectProperty;
    }

    public void setProductFxObjectProperty(ProductFx productFxObjectProperty) {
        this.productFxObjectProperty.set(productFxObjectProperty);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
