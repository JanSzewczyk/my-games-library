package pl.myGamesLibrary.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.myGamesLibrary.modelFx.CategoryFx;
import pl.myGamesLibrary.modelFx.CategoryModel;
import pl.myGamesLibrary.utils.DialogUtils;
import pl.myGamesLibrary.utils.exceptions.ApplicationException;

import java.sql.SQLException;
import java.util.Optional;


public class CategoryController {

    @FXML
    private Button addCategoryButton;

    @FXML
    private Button editCategoryButton;

    @FXML
    private Button deleteCategoryButton;

    @FXML
    private TextField categoryTextField;

    @FXML
    private ComboBox<CategoryFx> categoryComboBox;

    @FXML
    private TreeView<String> categoryTreeView;

    private CategoryModel categoryModel;

    @FXML
    public void initialize() {
        this.categoryModel = new CategoryModel();
        try {
            this.categoryModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        initBindings();
    }

    private void initBindings() {
        this.categoryComboBox.setItems(this.categoryModel.getCategoryList());
        this.categoryTreeView.setRoot(this.categoryModel.getRoot());

        this.addCategoryButton.disableProperty().bind(categoryTextField.textProperty().isEmpty());
        this.deleteCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());
        this.editCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());
    }

    @FXML
    public void addCategoryOnAction() {
        try {
            categoryModel.saveCategoryInDataBase(categoryTextField.getText());
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        categoryTextField.clear();
    }

    @FXML
    public void onActionComboBox() {
        this.categoryModel.setCategory(this.categoryComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void onActionDeleteButton() {
        Optional<ButtonType> result = DialogUtils.confirmationDialog("delete.category.title", "delete.category.header", "delete.category.content");
        if(result.get() == ButtonType.OK){
            try {
                this.categoryModel.deleteCatogoryById();
            } catch (ApplicationException | SQLException e) {
                DialogUtils.errorDialog(e.getMessage());
            }
        } else{
            this.categoryComboBox.getSelectionModel().clearSelection();
        }
    }

    @FXML
    public void onActionEditCategory() {
        String newCategoryName = DialogUtils.editDialog(this.categoryModel.getCategory().getName());
        if(newCategoryName != null){
            this.categoryModel.getCategory().setName(newCategoryName);
            try {
                this.categoryModel.updateCategoryInDataBase();
            } catch (ApplicationException e) {
                DialogUtils.errorDialog(e.getMessage());
            }
        }
    }
}
