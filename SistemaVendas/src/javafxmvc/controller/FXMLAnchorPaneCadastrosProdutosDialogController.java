package javafxmvc.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxmvc.model.domain.EstoqueCritico;
import javafxmvc.model.domain.EstoqueEmFalta;
import javafxmvc.model.domain.EstoqueNormal;
import javafxmvc.model.domain.Produto;

public class FXMLAnchorPaneCadastrosProdutosDialogController implements Initializable {

    @FXML
    private Label labelProdutoNome;
    @FXML
    private Label labelProdutoPreco;
    @FXML
    private Label labelProdutoQuantidade;
    @FXML
    private TextField textFieldProdutoNome;
    @FXML
    private TextField textFieldProdutoPreco;
    @FXML
    private TextField textFieldProdutoQuantidade;
    @FXML
    private TextField textFieldProdutoCategoria;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Produto produto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
    /*    
        if(produto.getQuantidade() > 10)
		{
                    produto.setEstado(new EstoqueNormal(produto));

		} else if(produto.getQuantidade() > 0 )
		{
                                        produto.setEstado(new EstoqueCritico(produto));

		}else if(produto.getQuantidade() == 0){
                                        produto.setEstado(new EstoqueEmFalta(produto));
                }  
      */  
        this.produto = produto;
        this.textFieldProdutoNome.setText(produto.getNome());
        this.textFieldProdutoPreco.setText(Double.toString(produto.getPreco()));
        this.textFieldProdutoQuantidade.setText(Integer.toString(produto.getQuantidade()));
        this.textFieldProdutoCategoria.setText(Integer.toString(produto.getCategoria().getCdCategoria()));
   
    
    }

    @FXML
    public void handleButtonConfirmar() {

        if (validarEntradaDeDados()) {

            produto.setNome(textFieldProdutoNome.getText());
            produto.setPreco(Double.parseDouble(textFieldProdutoPreco.getText()));
            produto.setQuantidade(Integer.parseInt(textFieldProdutoQuantidade.getText()));
            produto.getCategoria().setCdCategoria(Integer.parseInt(textFieldProdutoCategoria.getText()));

            buttonConfirmarClicked = true;
            dialogStage.close();
        }

    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

    //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (textFieldProdutoNome.getText() == null || textFieldProdutoNome.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
        }
        if (textFieldProdutoPreco.getText() == null || textFieldProdutoPreco.getText().length() == 0) {
            errorMessage += "Preço inválido!\n";
        }
        if (textFieldProdutoQuantidade.getText() == null || textFieldProdutoQuantidade.getText().length() == 0) {
            errorMessage += "Quantidade inválido!\n";
        }
        if (textFieldProdutoCategoria.getText() == null || textFieldProdutoCategoria.getText().length() == 0) {
            errorMessage += "Código inválido!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }


}
