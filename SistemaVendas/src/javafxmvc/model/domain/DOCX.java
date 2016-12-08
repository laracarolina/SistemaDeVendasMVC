/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.model.domain;

import java.io.File;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 *
 * @author Lara
 */
public class DOCX extends Formato {

    public void gerarRelatorioVendas(String nome) {
        Database database = DatabaseFactory.getDatabase("postgresql");
        Connection connection = database.conectar();

        // String src = "Vendas.jasper";
        JasperReport report = null;
        try {
            report = JasperCompileManager.compileReport("relatorios/vendas.jrxml");
        } catch (JRException ex) {
            System.out.println(ex);
        }
        JasperPrint print = null;
        try {
            print = JasperFillManager.fillReport(report, null, connection);//new JRBeanCollectionDataSource(lista));
        } catch (JRException ex) {
            System.out.println(ex);
        }
        JRDocxExporter export = new JRDocxExporter();
        export.setExporterInput(new SimpleExporterInput(print));
        export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File("relatorios/" + nome + ".docx")));

        SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();
//config.setFlexibleRowHeight(true); //Set desired configuration
        export.setConfiguration(config);

        try {
            export.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(DOCX.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gerarRelatorioProdutos(String nome) {//ArrayList<Venda> lista, String nome, LocalDate data1, LocalDate data2) {

       Database database = DatabaseFactory.getDatabase("postgresql");
        Connection connection = database.conectar();

        // String src = "Vendas.jasper";
        JasperReport report = null;
        try {
            report = JasperCompileManager.compileReport("relatorios/produtos.jrxml");
        } catch (JRException ex) {
            System.out.println(ex);
        }
        JasperPrint print = null;
        try {
            print = JasperFillManager.fillReport(report, null, connection);//new JRBeanCollectionDataSource(lista));
        } catch (JRException ex) {
            System.out.println(ex);
        }
        JRDocxExporter export = new JRDocxExporter();
        export.setExporterInput(new SimpleExporterInput(print));
        export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File("relatorios/" + nome + ".docx")));

        SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();
//config.setFlexibleRowHeight(true); //Set desired configuration
        export.setConfiguration(config);

        try {
            export.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(DOCX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // http://www.k19.com.br/artigos/relatorios-em-java-jasperreports-e-irepor/
    /*Document document = new Document();
            try {
            PdfWriter.getInstance(document, new FileOutputStream(nome + ".pdf"));
            document.open();
            document.add(new Paragraph("                              Relatório de Vendas (" + data1 + " a " + data2+")"));
            document.add(new Paragraph("\n\n"));
            for (Venda v : lista) {
            document.add(new Paragraph("Cliente: " + v.getCliente()));
            document.add(new Paragraph("Itens de Venda:"));
            for (ItemDeVenda item : v.getItensDeVenda()) {
            document.add(new Paragraph("-> " + item.getProduto().getNome() + " / Quantidade: " + item.getQuantidade()));
            }
            document.add(new Paragraph("Valor da venda: " + v.getValor()));
            document.add(new Paragraph("Data: " + v.getData()));
            document.add(new Paragraph("\n"));
            
            }
            } catch (DocumentException | FileNotFoundException ex) {
            System.out.println("Error:" + ex);
            } finally {
            document.close();
            }*/
 /*Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(nome + ".pdf"));
            document.open();
            document.add(new Paragraph("                 Relatório de Produtos Esgotados"));
            document.add(new Paragraph("\n\n"));
            for (Produto p : lista) {
                document.add(new Paragraph("- " + p.getNome()));
            }
        } catch (DocumentException | FileNotFoundException ex) {
            System.out.println("Error:" + ex);
        } finally {
            document.close();
        }*/
    public void gerarRelatorioClientes(String nome) {
       Database database = DatabaseFactory.getDatabase("postgresql");
        Connection connection = database.conectar();

        // String src = "Vendas.jasper";
        JasperReport report = null;
        try {
            report = JasperCompileManager.compileReport("relatorios/produtos.jrxml");
        } catch (JRException ex) {
            System.out.println(ex);
        }
        JasperPrint print = null;
        try {
            print = JasperFillManager.fillReport(report, null, connection);//new JRBeanCollectionDataSource(lista));
        } catch (JRException ex) {
            System.out.println(ex);
        }
        JRDocxExporter export = new JRDocxExporter();
        export.setExporterInput(new SimpleExporterInput(print));
        export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File("relatorios/" + nome + ".docx")));

        SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();
//config.setFlexibleRowHeight(true); //Set desired configuration
        export.setConfiguration(config);

        try {
            export.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(DOCX.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
