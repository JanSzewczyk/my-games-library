package pl.myGamesLibrary.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import pl.myGamesLibrary.modelFx.AuthorFx;
import pl.myGamesLibrary.modelFx.AuthorModel;
import pl.myGamesLibrary.utils.DialogUtils;
import pl.myGamesLibrary.utils.exceptions.ApplicationException;

import java.sql.SQLException;


public class AuthorController {

    @FXML
    private TextField nameTextField;
    @FXML
    private Button addButton;
    @FXML
    private TableView<AuthorFx> authorTableView;
    @FXML
    private TableColumn<AuthorFx, String> nameColumn;
    @FXML
    private MenuItem deleteMenuItem;

    private AuthorModel authorModel;

    @FXML
    public void initialize(){
        this.authorModel = new AuthorModel();

        try {
            this.authorModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        initBindings();
        initBindingsTableView();
    }

    private void initBindingsTableView() {
        this.authorTableView.setItems(this.authorModel.getAuthorFxObservableList());
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        this.nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        this.authorTableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            this.authorModel.setAuthorObjectPropertyEdit(newValue);
        }));
    }

    private void initBindings() {
        this.authorModel.authorObjectPropertyProperty().get().nameProperty().bind(this.nameTextField.textProperty());
        this.addButton.disableProperty().bind(this.nameTextField.textProperty().isEmpty());
        this.deleteMenuItem.disableProperty().bind(this.authorTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    @FXML
    public void addAuthorOnAction(){
        try {
            this.authorModel.saveAuthorInDatabase();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        this.nameTextField.clear();
    }

    @FXML
    public void onEditCommitName(TableColumn.CellEditEvent<AuthorFx, String> authorFxStringCellEditEvent) {
        this.authorModel.getAuthorObjectPropertyEdit().setName(authorFxStringCellEditEvent.getNewValue());
        try {
            this.authorModel.saveAuthorEditInDatabase();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    @FXML
    public void deleteAuthorOnAction() {
        try {
            this.authorModel.deleteAuthorInDataBase();
        } catch (ApplicationException | SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

    }
}
