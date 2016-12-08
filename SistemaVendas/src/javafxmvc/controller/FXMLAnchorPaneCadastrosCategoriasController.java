/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafxmvc.model.dao.CategoriaDAO;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
import javafxmvc.model.domain.Categoria;

public class FXMLAnchorPaneCadastrosCategoriasController implements Initializable {

    @FXML
    private TableView<Categoria> tableViewCategorias;
    @FXML
    private TableColumn<Categoria, String> tableColumnCategoriaNome;
    @FXML
    private TableColumn<Categoria, String> tableColumnCategoriaCodigo;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Label labelCategoriaCodigo;
    @FXML
    private Label labelCategoriaNome;

    private List<Categoria> listCategorias;
    private ObservableList<Categoria> observableListCategorias;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoriaDAO.setConnection(connection);
        carregarTableViewCategoria();

        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        tableViewCategorias.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewCategorias(newValue));

    }

    public void carregarTableViewCategoria() {
        tableColumnCategoriaNome.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tableColumnCategoriaCodigo.setCellValueFactory(new PropertyValueFactory<>("cdCategoria"));

        listCategorias = categoriaDAO.listar();

        observableListCategorias = FXCollections.observableArrayList(listCategorias);
        tableViewCategorias.setItems(observableListCategorias);
    }
    
    public void selecionarItemTableViewCategorias(Categoria categoria){
        if (categoria != null) {
            labelCategoriaCodigo.setText(String.valueOf(categoria.getCdCategoria()));
            labelCategoriaNome.setText(categoria.getDescricao());
        } else {
            labelCategoriaCodigo.setText("");
            labelCategoriaNome.setText("");
        }

    }
    
    @FXML
    public void handleButtonInserir() throws IOException {
        Categoria categoria = new Categoria();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosCategoriasDialog(categoria);
        if (buttonConfirmarClicked) {
            categoriaDAO.inserir(categoria);
            carregarTableViewCategoria();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Categoria categoria = tableViewCategorias.getSelectionModel().getSelectedItem();
        if (categoria != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosCategoriasDialog(categoria);
            if (buttonConfirmarClicked) {
                categoriaDAO.alterar(categoria);
                carregarTableViewCategoria();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um categoria na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        Categoria categoria = tableViewCategorias.getSelectionModel().getSelectedItem();
        if (categoria != null) {
            categoriaDAO.remover(categoria);
            carregarTableViewCategoria();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um categoria na Tabela!");
            alert.show();
        }
    }
    
    public boolean showFXMLAnchorPaneCadastrosCategoriasDialog(Categoria categoria) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastrosCategoriasDialogController.class.getResource("/javafxmvc/view/FXMLAnchorPaneCadastrosCategoriasDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Categorias");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o categoria no Controller.
        FXMLAnchorPaneCadastrosCategoriasDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCategoria(categoria);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }



}