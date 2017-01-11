/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Juancho270
 */
public class GenerarReportes {
    Fachada conexion;
    
    GenerarReportes(){
        conexion=new Fachada();
    }
    
 
  public void reporteUsuarios(){
     /* try {
          JasperReport reporte = (JasperReport) JRLoader.loadObject("ReporteUsuarios.jasper");
          JasperPrint j = JasperFillManager.fillReport(reporte, null, conexion.conectarABD());
          JasperViewer vj = new JasperViewer(j,false);
          vj.setTitle("REPORTE USUARIOS");
          vj.setVisible(true);
          
          
      } catch (Exception e) {
          System.out.println("Error" + e);
      }*/
  }
  
   public void reporteSedes(){
      /*try {
          JasperReport reporte = (JasperReport) JRLoader.loadObject("ReporteSedes.jasper");
          JasperPrint j = JasperFillManager.fillReport(reporte, null, conexion.conectarABD());
          JasperViewer vj = new JasperViewer(j,false);
          vj.setTitle("REPORTE SEDES");
          vj.setVisible(true);
          
          
      } catch (Exception e) {
          System.out.println("Error" + e);
      }*/
  }
  
  public void generarFactura(String id_venta){
      /*try {
          JasperReport reporte = (JasperReport) JRLoader.loadObject("Factura.jasper");
          Map parametro = new HashMap();
          parametro.put("id_venta",id_venta);
          JasperPrint j = JasperFillManager.fillReport(reporte, parametro, conexion.conectarABD());
          JasperViewer jv = new JasperViewer(j,false);
          jv.setTitle("FACTURA");
          jv.setVisible(true);
      } catch (JRException e) {
          JOptionPane.showMessageDialog(null, "Excepcion" + e);
      }*/
  }
}
