package javafxmvc.model.domain;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

/**
 *
 * @author Lara
 */
// STRATEGY
public abstract class Formato {

    
   public abstract void gerarRelatorioVendas(String nome);
       

   public abstract void gerarRelatorioProdutos(String nome);
   
   public abstract void gerarRelatorioClientes(String nome);
   
   
}

   
