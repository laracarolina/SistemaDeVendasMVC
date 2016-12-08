package javafxmvc.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafxmvc.model.dao.ProdutoDAO;
import javafxmvc.model.dao.VendaDAO;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
import javafxmvc.model.domain.DOCX;
import javafxmvc.model.domain.HTML;
import javafxmvc.model.domain.PDF;
import javafxmvc.model.domain.Produto;
import javafxmvc.model.domain.Relatorio;

import javafxmvc.model.domain.Venda;
import javafxmvc.model.domain.XML;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Lara
 */
public class FXMLAnchorPaneRelatoriosController implements Initializable {

    @FXML
    private ComboBox<String> selecaoRelatorio;
    @FXML
    ObservableList<String> tiposRelatorio = FXCollections.observableArrayList("Produtos", "Vendas", "Clientes");
    @FXML
    private CheckBox pdf;
    @FXML
    private CheckBox xml;
    @FXML
    private CheckBox doc;
    @FXML
    private CheckBox html;
    @FXML
    private TextField nome;
    @FXML
    private DatePicker data1;
    @FXML
    private DatePicker data2;
    @FXML
    private Button buttonSalvarRelatorio;
    @FXML
    private Button buttonVisualizarRelatorio;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final VendaDAO vendaDAO = new VendaDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produtoDAO.setConnection(connection);
        vendaDAO.setConnection(connection);
        selecaoRelatorio.setItems(tiposRelatorio);
        buttonVisualizarRelatorio.setStyle("-fx-cursor: hand; ");
        buttonSalvarRelatorio.setStyle("-fx-cursor: hand; ");
    }

    public void handleButtonSalvarRelatorio() {
        Relatorio relatorio = new Relatorio();
        String nomeArquivo = nome.getText();
       
            if (selecaoRelatorio.getValue().equals("Produtos")) {
            List<Produto> produtos = produtoDAO.listar();
            ArrayList<Produto> produtosEsgotados = new ArrayList<Produto>(); // guarda os produtos com qde = 0
            for (Produto p : produtos) {
                if (p.getQuantidade() == 0) {
                    produtosEsgotados.add(p);
                }
            }
            if (pdf.isSelected() == true) {
                relatorio.setFormato(new PDF());
                relatorio.gerarRelatorioProdutos(nomeArquivo);
            }
              if (doc.isSelected() == true) {
                relatorio.setFormato(new DOCX());
                relatorio.gerarRelatorioVendas(nomeArquivo);//(vendasDoPeriodo, nomeArquivo, data1.getValue(), data2.getValue());
            }
            if (html.isSelected() == true) {
                relatorio.setFormato(new HTML());
              System.out.println(" FORMATOOO: "+relatorio.getFormato().getClass().getName());
                relatorio.gerarRelatorioProdutos(nomeArquivo);
            }
        } else if (selecaoRelatorio.getValue().equals("Vendas")) {
            /*List<Venda> vendas = vendaDAO.listar();
            ArrayList<Venda> vendasDoPeriodo = new ArrayList<Venda>(); // guarda as vendas do periodo selecionado
            for(Venda v : vendas){
                //se a data da venda for igual a data inicial, ou estiver entre a data inicial e data final ou for igual a data final
                if(v.getData().isEqual(data1.getValue()) || v.getData().isAfter(data1.getValue()) && v.getData().isBefore(data2.getValue())
                        || v.getData().isEqual(data2.getValue())){
                    vendasDoPeriodo.add(v);
                }
            }*/

            if (pdf.isSelected() == true) {
                relatorio.setFormato(new PDF());
                relatorio.gerarRelatorioVendas(nomeArquivo);//(vendasDoPeriodo, nomeArquivo, data1.getValue(), data2.getValue());
            }
            
             if (doc.isSelected() == true) {
                relatorio.setFormato(new DOCX());
                relatorio.gerarRelatorioVendas(nomeArquivo);//(vendasDoPeriodo, nomeArquivo, data1.getValue(), data2.getValue());
            }
             
            if (html.isSelected() == true) {
             
                relatorio.setFormato(new HTML());
                 
                relatorio.gerarRelatorioVendas(nomeArquivo);//vendasDoPeriodo, nomeArquivo, data1.getValue(), data2.getValue());
            }
            if (xml.isSelected() == true) {
                
                relatorio.setFormato(new XML());
                relatorio.gerarRelatorioVendas(nomeArquivo);
            }

        }
            
            else if (selecaoRelatorio.getValue().equals("Clientes")) {
            /*List<Venda> vendas = vendaDAO.listar();
            ArrayList<Venda> vendasDoPeriodo = new ArrayList<Venda>(); // guarda as vendas do periodo selecionado
            for(Venda v : vendas){
                //se a data da venda for igual a data inicial, ou estiver entre a data inicial e data final ou for igual a data final
                if(v.getData().isEqual(data1.getValue()) || v.getData().isAfter(data1.getValue()) && v.getData().isBefore(data2.getValue())
                        || v.getData().isEqual(data2.getValue())){
                    vendasDoPeriodo.add(v);
                }
            }*/

            if (pdf.isSelected() == true) {
                relatorio.setFormato(new PDF());
                relatorio.gerarRelatorioClientes(nomeArquivo);//(vendasDoPeriodo, nomeArquivo, data1.getValue(), data2.getValue());
            }
            if (html.isSelected() == true) {
             
                relatorio.setFormato(new HTML());
                 System.out.println(" FORMATOOO: "+relatorio.getFormato().getClass().getName());
               relatorio.gerarRelatorioClientes(nomeArquivo);//vendasDoPeriodo, nomeArquivo, data1.getValue(), data2.getValue());
            }
            if (xml.isSelected() == true) {
                
                relatorio.setFormato(new XML());
               relatorio.gerarRelatorioClientes(nomeArquivo);
            }
              if (doc.isSelected() == true) {
                relatorio.setFormato(new DOCX());
                relatorio.gerarRelatorioVendas(nomeArquivo);//(vendasDoPeriodo, nomeArquivo, data1.getValue(), data2.getValue());
            }

        }
    }
    

    public void handleButtonVisualizarRelatorio() {
        Database database = DatabaseFactory.getDatabase("postgresql");
        Connection connection = database.conectar();

         
            if (selecaoRelatorio.getValue().equals("Produtos")) {

                String src = "relatorios/produtos.jasper";

                JasperPrint jaspertPrint = null;

                try {

                    jaspertPrint = JasperFillManager.fillReport(src, null, connection);

                } catch (JRException ex) {
                    System.out.println("Error: " + ex);
                }

                JasperViewer view = new JasperViewer(jaspertPrint, false);

                view.setVisible(true);
            }
            if (selecaoRelatorio.getValue().equals("Vendas")) {
                String src = "relatorios/vendas.jasper";

                JasperPrint jaspertPrint = null;

                try {

                    jaspertPrint = JasperFillManager.fillReport(src, null, connection);

                } catch (JRException ex) {
                    System.out.println("Error: " + ex);
                }

                JasperViewer view = new JasperViewer(jaspertPrint, false);

                view.setVisible(true);
            }
            
            if (selecaoRelatorio.getValue().equals("Clientes")) {
                String src = "relatorios/clientes.jasper";

                JasperPrint jaspertPrint = null;

                try {

                    jaspertPrint = JasperFillManager.fillReport(src, null, connection);

                } catch (JRException ex) {
                    System.out.println("Error: " + ex);
                }

                JasperViewer view = new JasperViewer(jaspertPrint, false);

                view.setVisible(true);
            }
        }
    }

