package javafxmvc.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafxmvc.model.dao.ProdutoDAO;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
import javafxmvc.model.domain.Categoria;
import javafxmvc.model.domain.Produto;

public class FXMLAnchorPaneCadastrosProdutosController implements Initializable {

    @FXML
    private TableView<Produto> tableViewProdutos;
    @FXML
    private TableColumn<Produto, String> tableColumnProdutoNome;
    @FXML
    private TableColumn<Produto, String> tableColumnProdutoPreco;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Label labelProdutoCodigo;
    @FXML
    private Label labelProdutoNome;
    @FXML
    private Label labelProdutoPreco;
    @FXML
    private Label labelProdutoQuantidade;
    @FXML
    private Label labelProdutoCategoria;
    private List<Produto> listProdutos;
    private ObservableList<Produto> observableListProdutos;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produtoDAO.setConnection(connection);
        carregarTableViewProduto();

        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        tableViewProdutos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewProdutos(newValue));

    }

    public void carregarTableViewProduto() {
        tableColumnProdutoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnProdutoPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        listProdutos = produtoDAO.listar();

        observableListProdutos = FXCollections.observableArrayList(listProdutos);
        tableViewProdutos.setItems(observableListProdutos);
    }

    public void selecionarItemTableViewProdutos(Produto produto) {
        if (produto != null) {
            labelProdutoCodigo.setText(String.valueOf(produto.getCdProduto()));
            labelProdutoNome.setText(produto.getNome());
            labelProdutoPreco.setText(Double.toString(produto.getPreco()));
            labelProdutoQuantidade.setText(Integer.toString(produto.getQuantidade()));
            labelProdutoCategoria.setText(produto.getCategoria().getDescricao());
        } else {
            labelProdutoCodigo.setText("");
            labelProdutoNome.setText("");
            labelProdutoPreco.setText("");
            labelProdutoQuantidade.setText("");
            labelProdutoCategoria.setText("");
        }

    }

    @FXML
    public void handleButtonInserir() throws IOException, SQLException {
        Produto produto = new Produto();
        Categoria c = new Categoria();
        produto.setCategoria(c);
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosProdutosDialog(produto);
        if (buttonConfirmarClicked) {
            try {
                connection.setAutoCommit(false);
                produtoDAO.setConnection(connection);
                produtoDAO.inserir(produto);
                connection.commit();
                carregarTableViewProduto();
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(FXMLAnchorPaneCadastrosProdutosController.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(FXMLAnchorPaneCadastrosProdutosController.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Produto produto = tableViewProdutos.getSelectionModel().getSelectedItem();
        if (produto != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosProdutosDialog(produto);
            if (buttonConfirmarClicked) {
                produtoDAO.alterar(produto);
                carregarTableViewProduto();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um produto na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException, SQLException {
         Produto produto = tableViewProdutos.getSelectionModel().getSelectedItem();
        if (produto!= null) {
            produtoDAO.remover(produto);
            carregarTableViewProduto();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um produto na Tabela!");
            alert.show();
        }
        /*Produto produto = tableViewProdutos.getSelectionModel().getSelectedItem();
        if (produto != null) {
            connection.setAutoCommit(false);
            produtoDAO.setConnection(connection);
            categoriaDAO.setConnection(connection);
            produtoDAO.remover(produto);
            connection.commit();
            carregarTableViewProduto();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um produto na Tabela!");
            alert.show();
        }*/
    }

    public boolean showFXMLAnchorPaneCadastrosProdutosDialog(Produto produto) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastrosProdutosDialogController.class.getResource("/javafxmvc/view/FXMLAnchorPaneCadastrosProdutosDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Produtos");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o produto no Controller.
        FXMLAnchorPaneCadastrosProdutosDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setProduto(produto);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }

}
