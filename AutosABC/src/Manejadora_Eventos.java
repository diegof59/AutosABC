
import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.edisoncor.gui.util.Avatar;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Roberth
 */
public class Manejadora_Eventos implements ActionListener {

    Interfaz interfaz;
    Validaciones validar;

    public Manejadora_Eventos() throws IOException {
        interfaz = new Interfaz(this);
        validar = new Validaciones();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        //LOGIN
        if (ae.getSource().equals(interfaz.jButton1)) {
            interfaz.codigo = interfaz.usuario.getText();
            interfaz.jTable1.setVisible(false);
            // validacion de que los campos no esten vacios
            if (interfaz.validador.noVacio(interfaz.contraseña.getText()) || interfaz.validador.noVacio(interfaz.usuario.getText())) {

                try {
                    //Validacion que los datos sean correctos y segun el tipo de usuario abre su ventana correspondiente
                    if (interfaz.obj.Loguearse(interfaz.usuario.getText(), interfaz.contraseña.getText()) == true) {

                        String ruta = interfaz.obj.foto(interfaz.usuario.getText());
                        if ("Gerente".equals(interfaz.obj.tipoUsuario(interfaz.usuario.getText()))) {
                            interfaz.setVisible(false);
                            interfaz.jFrameGerenteBotones.setVisible(true);
                            interfaz.nombreUsuario.setText(interfaz.codigo);
                            interfaz.nombreUsuario11.setText(interfaz.codigo);
                            // jFrameGerente.setSize(new Dimension(800, 800));
                            interfaz.jFrameGerenteBotones.pack();

                            interfaz.jCheckBoxMenuItem2.setSelected(true);
                            ImageIcon imagen = new ImageIcon(ruta);
                            Image image = imagen.getImage();
                            Image newImage = image.getScaledInstance(interfaz.jLabel37.getWidth(), interfaz.jLabel37.getHeight(), Image.SCALE_SMOOTH);
                            interfaz.jLabel37.setIcon(new ImageIcon(newImage));
                            interfaz.jLabel134.setIcon(new ImageIcon(newImage));
                            
                            AccesoDatos ad = new AccesoDatos();
                            String sede = ad.sedeEmpleado(interfaz.codigo);
                            interfaz.setSede(sede);
                        }
                        if ("Vendedor".equals(interfaz.obj.tipoUsuario(interfaz.usuario.getText()))) {
                            interfaz.setVisible(false);
                            interfaz.jFrameVendedor.setVisible(true);
                            interfaz.nombreUsuario1.setText(interfaz.codigo);
                            interfaz.jFrameVendedor.setSize(new Dimension(800, 800));
                            interfaz.jFrameVendedor.pack();

                            ImageIcon imagen = new ImageIcon(ruta);
                            Image image = imagen.getImage();
                            Image newImage = image.getScaledInstance(interfaz.jLabel38.getWidth(), interfaz.jLabel38.getHeight(), Image.SCALE_SMOOTH);
                            interfaz.jLabel38.setIcon(new ImageIcon(newImage));
                        }
                        if ("Jefe".equals(interfaz.obj.tipoUsuario(interfaz.usuario.getText()))) {
                            interfaz.setVisible(false);
                            interfaz.jFrameJefeTaller.setVisible(true);
                            interfaz.nombreUsuario2.setText(interfaz.codigo);
                            interfaz.jFrameJefeTaller.setSize(new Dimension(800, 800));
                            interfaz.jFrameJefeTaller.pack();

                            ImageIcon imagen = new ImageIcon(ruta);
                            Image image = imagen.getImage();
                            Image newImage = image.getScaledInstance(interfaz.jLabel59.getWidth(), interfaz.jLabel59.getHeight(), Image.SCALE_SMOOTH);
                            interfaz.jLabel59.setIcon(new ImageIcon(newImage));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nombre de usuario o Contraseña erradas", "ERROR", WIDTH, null);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Los campos o uno de los campos estan vacios", "ERROR", WIDTH, null);

            }

        }

        if (ae.getSource().equals(interfaz.jCheckBoxMenuItem2)) {
            if (interfaz.jCheckBoxMenuItem2.getState()) {

            } else {
                interfaz.jFrameGerenteBotones.setVisible(false);
                pintar();
                interfaz.jCheckBoxMenuItem1.setSelected(false);
            }
        }

        if (ae.getSource().equals(interfaz.jCheckBoxMenuItem1)) {
            if (interfaz.jCheckBoxMenuItem1.getState()) {
                interfaz.jFrameGerenteBotones.setVisible(true);
                interfaz.jFrameGerente.setVisible(false);
                interfaz.jCheckBoxMenuItem1.setSelected(true);
            } else {
                interfaz.jFrameGerente.setVisible(true);
                interfaz.jFrameGerenteBotones.setVisible(false);
            }
        }
        //BOTON CANCELAR DE FRAME SEDE
        if (ae.getSource().equals(interfaz.jButton25)) {
            interfaz.jFrameGerente.setEnabled(true);
            interfaz.jFrameGerenteBotones.setEnabled(true);
            interfaz.jFrameSede.setVisible(false);
            interfaz.jTextField34.setText("");
            interfaz.jTextField33.setText("");
            interfaz.jTextField32.setText("");
            interfaz.jTextField31.setText("");
            interfaz.jTextField30.setText("");
            interfaz.jTextField27.setText("");

        }

        //BOTON MODIFICAR DE FRAME SEDE
        if (ae.getSource().equals(interfaz.jButton46)) {
            if (interfaz.validador.validarNumero(interfaz.jTextField30.getText()) && interfaz.validador.validarNumero(interfaz.jTextField27.getText())
                    && interfaz.validador.noVacio(interfaz.jTextField34.getText()) && interfaz.validador.noVacio(interfaz.jTextField33.getText())
                    && interfaz.validador.noVacio(interfaz.jTextField32.getText()) && interfaz.validador.noVacio(interfaz.jTextField31.getText())
                    && interfaz.validador.noVacio(interfaz.jTextField27.getText())) {

                interfaz.obj2.actualizarSede(interfaz.jTextField34.getText(), interfaz.jTextField33.getText(), interfaz.jTextField32.getText(), interfaz.jTextField31.getText(), interfaz.jTextField30.getText(), interfaz.jTextField27.getText());
                interfaz.jFrameSede.setVisible(false);
                interfaz.jFrameGerenteBotones.setVisible(true);
                interfaz.jTextField34.setText("");
                interfaz.jTextField33.setText("");
                interfaz.jTextField32.setText("");
                interfaz.jTextField31.setText("");
                interfaz.jTextField30.setText("");
                interfaz.jTextField27.setText("");
                interfaz.jFrameBuscarSede.setVisible(false);
                interfaz.jFrameGerente.setEnabled(true);
                interfaz.jFrameGerenteBotones.setEnabled(true);
                JOptionPane.showMessageDialog(null, "La sede fue Modificada", "Exito", WIDTH, null);
            } else {
                JOptionPane.showMessageDialog(null, "Verifique los numros de telefono solo pueden ser enteros" + "\n" + "O algunos campos estan vacios", "ERROR", WIDTH, null);
            }
        }

        //BOTON GUARDAR DE FRAME BUSCAR SEDE
        if (ae.getSource().equals(interfaz.jButton13)) {
            if (interfaz.validador.validarNumero(interfaz.jTextField30.getText()) && interfaz.validador.validarNumero(interfaz.jTextField27.getText())
                    && interfaz.validador.noVacio(interfaz.jTextField34.getText()) && interfaz.validador.noVacio(interfaz.jTextField33.getText())
                    && interfaz.validador.noVacio(interfaz.jTextField32.getText()) && interfaz.validador.noVacio(interfaz.jTextField31.getText())) {

                if (interfaz.obj2.ingresarSede(interfaz.jTextField34.getText(), interfaz.jTextField33.getText(), interfaz.jTextField32.getText(), interfaz.jTextField31.getText(), interfaz.jTextField30.getText(), interfaz.jTextField27.getText())) {
                    JOptionPane.showMessageDialog(null, "La sede fue registrada", "Exito", WIDTH, null);
                    interfaz.jFrameSede.setVisible(false);
                    interfaz.jFrameGerenteBotones.setVisible(true);
                    interfaz.jFrameGerente.setEnabled(true);
                    interfaz.jFrameGerenteBotones.setEnabled(true);
                    interfaz.jTextField34.setText("");
                    interfaz.jTextField33.setText("");
                    interfaz.jTextField32.setText("");
                    interfaz.jTextField31.setText("");
                    interfaz.jTextField30.setText("");
                    interfaz.jTextField27.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "La sede no se registro", "Error", WIDTH, null);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Verifique los numeros de telefono solo pueden ser enteros" + "\n" + "O algunos campos estan vacios", "ERROR", WIDTH, null);
            }
        }

        //BOTON CANCELAR DE FRAME BUSCAR SEDE
        if (ae.getSource().equals(interfaz.jButton45)) {
            interfaz.jFrameBuscarSede.setVisible(false);
            interfaz.jFrameGerente.setEnabled(true);
            interfaz.jFrameGerenteBotones.setEnabled(true);
        }
        //BOTON MODIFICAR DE FRAME BUSCAR SEDE
        if (ae.getSource().equals(interfaz.jButton44)) {
            interfaz.jFrameBuscarSede.setVisible(false);
            String id_sede = interfaz.jComboBox22.getSelectedItem().toString();
            if ("Seleccione una sede".equals(id_sede)) {
                JOptionPane.showMessageDialog(null, "Seleccione una sede", "Error", WIDTH, null);

            } else {

                ArrayList<String> datos = interfaz.obj.consultarSede();
                for (int i = 0; i < datos.size(); i = i + 2) {
                    if (interfaz.jComboBox22.getSelectedItem().toString().equals(datos.get(i) + " - " + datos.get(i + 1))) {
                        id_sede = datos.get(i);
                    }
                }

                ArrayList<String> dato = interfaz.obj.datosSede(id_sede);

                interfaz.jTextField34.setText(dato.get(0));
                interfaz.jTextField33.setText(dato.get(1));
                interfaz.jTextField32.setText(dato.get(2));
                interfaz.jTextField31.setText(dato.get(3));
                interfaz.jTextField30.setText(dato.get(4));
                interfaz.jTextField27.setText(dato.get(5));
                interfaz.nombreUsuario7.setText(interfaz.usuario.getText());
                interfaz.jFrameSede.setVisible(true);
                interfaz.jButton13.setEnabled(false);
                interfaz.jButton46.setEnabled(true);
                interfaz.jFrameSede.setSize(new Dimension(350, 350));

            }
        }

        //EVENTO DE CREAR SEDES
        if (ae.getSource().equals(interfaz.jButton36) || ae.getSource().equals(interfaz.jMenuItem40)
                || ae.getSource().equals(interfaz.jMenuItem42)) {
            interfaz.jTextField34.setEditable(true);
            interfaz.nombreUsuario7.setText(interfaz.codigo);
            interfaz.jFrameGerente.setEnabled(false);
            interfaz.jFrameGerenteBotones.setEnabled(false);
            interfaz.jFrameSede.setVisible(true);
            interfaz.jFrameSede.setSize(new Dimension(350, 350));
            interfaz.jButton46.setEnabled(false);
        }

        //EVENTO MODIFICAR SEDE
        if (ae.getSource().equals(interfaz.jButton37) || ae.getSource().equals(interfaz.jMenuItem12)
                || ae.getSource().equals(interfaz.jMenuItem26)) {
            interfaz.jFrameGerente.setEnabled(false);
            interfaz.jFrameGerenteBotones.setEnabled(false);
            interfaz.jComboBox22.removeAllItems();
            interfaz.jFrameBuscarSede.setVisible(true);
            interfaz.jFrameBuscarSede.setSize(new Dimension(250, 150));
            interfaz.jTextField34.setEditable(false);
            ArrayList<String> datos;
            datos = interfaz.obj.consultarSede();
            String[] dato = new String[datos.size() + 1];
            for (int i = 0; i < datos.size(); i = i + 2) {
                interfaz.jComboBox22.addItem(datos.get(i) + " - " + datos.get(i + 1));
            }
        }
        //EVENTO VENDEDORES SEDE
        if (ae.getSource().equals(interfaz.jButton39) || ae.getSource().equals(interfaz.jMenuItem27)) {
            interfaz.jTable1.setVisible(true);
            interfaz.obj.listarV((DefaultTableModel) interfaz.jTable1.getModel());
        }
        if (ae.getSource().equals(interfaz.jMenuItem13)) {
            interfaz.jTable7.setVisible(true);
            interfaz.obj.listarV((DefaultTableModel) interfaz.jTable7.getModel());
        }

        //EVENTO LISTAR SEDE
        if (ae.getSource().equals(interfaz.jButton38) || ae.getSource().equals(interfaz.jMenuItem41)) {
            interfaz.jTable1.setVisible(true);
            interfaz.obj.listarS((DefaultTableModel) interfaz.jTable1.getModel());
        }
        if (ae.getSource().equals(interfaz.jMenuItem43)) {
            interfaz.jTable7.setVisible(true);
            interfaz.obj.listarS((DefaultTableModel) interfaz.jTable7.getModel());
        }

        //BOTON CANCELAR DE FRAME USUARIO
        if (ae.getSource().equals(interfaz.jButton74)) {

            interfaz.jFrameUsuario4.setVisible(false);
            interfaz.jFrameGerente.setEnabled(true);
            interfaz.jFrameGerenteBotones.setEnabled(true);
            interfaz.jComboBox13.removeAllItems();
            interfaz.jComboBox13.addItem("Seleccione una sede");

            interfaz.jLabel27.setText("Nombre Sede");

            interfaz.jComboBox2.setSelectedIndex(0);
            interfaz.jComboBox24.setSelectedIndex(0);
            interfaz.jComboBox25.setSelectedIndex(0);
            interfaz.jComboBox26.setSelectedIndex(0);
            interfaz.jComboBox27.setSelectedIndex(0);
            interfaz.jTextFieldCedulaUsuario.setText("");
            interfaz.jTextField93.setText("");
            interfaz.jTextField94.setText("");
            interfaz.jTextField95.setText("");
            interfaz.jTextField96.setText("");
            interfaz.jTextField97.setText("");
            interfaz.jTextField98.setText("");
            interfaz.jTextField99.setText("");
            interfaz.jTextField100.setText("");
            interfaz.jTextField101.setText("");
        }

        //BOTON MODIFICAR USUARIO
        if (ae.getSource().equals(interfaz.jButton69)) {
            String dia = "", mes = "";
            if (interfaz.jComboBox24.getSelectedIndex() > 0) {
                if (interfaz.jComboBox24.getSelectedIndex() < 10) {
                    dia = "0" + interfaz.jComboBox24.getSelectedIndex();
                } else {
                    dia = "" + interfaz.jComboBox24.getSelectedIndex();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes registrar un dia");
            }
            if (interfaz.jComboBox25.getSelectedIndex() > 0) {
                if (interfaz.jComboBox25.getSelectedIndex() < 10) {
                    mes = "0" + (interfaz.jComboBox25.getSelectedIndex());
                } else {
                    mes = "" + (interfaz.jComboBox25.getSelectedIndex());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes registrar un mes");
            }
            String anio = interfaz.jComboBox26.getSelectedItem().toString();
            String sede = interfaz.jLabel27.getText();

            String tipo = interfaz.jComboBox27.getSelectedItem().toString();
            String estado = interfaz.jComboBox2.getSelectedItem().toString();
            if (interfaz.obj2.ActualizarUsuario(interfaz.jTextField98.getText(), interfaz.jTextField99.getText(), interfaz.jTextFieldCedulaUsuario.getText(), interfaz.jTextField93.getText(), interfaz.jTextField101.getText(), interfaz.jTextField94.getText(), interfaz.jTextField100.getText(), interfaz.jTextField95.getText(), interfaz.jTextField96.getText(), interfaz.jTextField97.getText(), interfaz.jLabel35.getText(), anio + "-" + mes + "-" + dia, estado, sede, tipo) == -1) {
                JOptionPane.showMessageDialog(null, "El usuario no se modifico correctamente", "Error", WIDTH, null);
            } else {
                JOptionPane.showMessageDialog(null, "El usuario fue modificado", "Exito", WIDTH, null);
            }

            String ruta = interfaz.obj.foto(interfaz.usuario.getText());
            ImageIcon imagen = new ImageIcon(ruta);
            Image image = imagen.getImage();
            Image newImage = image.getScaledInstance(interfaz.jLabel37.getWidth(), interfaz.jLabel37.getHeight(), Image.SCALE_SMOOTH);
            interfaz.jLabel37.setIcon(new ImageIcon(newImage));
            interfaz.jLabel134.setIcon(new ImageIcon(newImage));

            interfaz.jFrameUsuario4.setVisible(false);
            interfaz.jFrameBuscarUsuario.setVisible(false);

            interfaz.jFrameGerente.setEnabled(true);
            interfaz.jFrameGerenteBotones.setEnabled(true);
            interfaz.jComboBox13.removeAllItems();
            interfaz.jComboBox13.addItem("Seleccione una sede");
        }

        //BOTON GUARDAR DE FRAME USUARIO
        if (ae.getSource().equals(interfaz.jButton73)) {
            String dia = "", mes = "";
            if (interfaz.jComboBox24.getSelectedIndex() > 0) {
                if (interfaz.jComboBox24.getSelectedIndex() < 10) {
                    dia = "0" + interfaz.jComboBox24.getSelectedIndex();
                } else {
                    dia = "" + interfaz.jComboBox24.getSelectedIndex();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes registrar un dia");
            }
            if (interfaz.jComboBox24.getSelectedIndex() > 0) {
                if (interfaz.jComboBox24.getSelectedIndex() < 10) {
                    mes = "0" + (interfaz.jComboBox25.getSelectedIndex());
                } else {
                    mes = "" + (interfaz.jComboBox25.getSelectedIndex());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes registrar un mes");
            }

            String anio = interfaz.jComboBox26.getSelectedItem().toString();
            String sede = interfaz.jLabel27.getText();
            String tipo = interfaz.jComboBox27.getSelectedItem().toString();
            String estado = interfaz.jComboBox2.getSelectedItem().toString();

            if (interfaz.obj2.ingresarUsuario(interfaz.jTextField98.getText(), interfaz.jTextField99.getText(), interfaz.jTextFieldCedulaUsuario.getText(), interfaz.jTextField93.getText(), interfaz.jTextField101.getText(), interfaz.jTextField94.getText(), interfaz.jTextField100.getText(), interfaz.jTextField95.getText(), interfaz.jTextField96.getText(), interfaz.jTextField97.getText(), interfaz.jLabel35.getText(), anio + "-" + mes + "-" + dia, estado, sede, tipo) == -1) {
                JOptionPane.showMessageDialog(null, "El usuario no se registro correctamente", "Error", WIDTH, null);
            } else {
                JOptionPane.showMessageDialog(null, "El usuario fue registrado", "Exito", WIDTH, null);
            }
            interfaz.jFrameUsuario4.setVisible(false);
            interfaz.jFrameGerente.setEnabled(true);
            interfaz.jFrameGerenteBotones.setEnabled(true);
            interfaz.jLabel27.setText("Nombre Sede");
            
            interfaz.jComboBox2.setSelectedIndex(0);
            interfaz.jComboBox24.setSelectedIndex(0);
            interfaz.jComboBox25.setSelectedIndex(0);
            interfaz.jComboBox26.setSelectedIndex(0);
            interfaz.jComboBox27.setSelectedIndex(0);
            interfaz.jTextFieldCedulaUsuario.setText("");
            interfaz.jTextField93.setText("");
            interfaz.jTextField94.setText("");
            interfaz.jTextField95.setText("");
            interfaz.jTextField96.setText("");
            interfaz.jTextField97.setText("");
            interfaz.jTextField98.setText("");
            interfaz.jTextField99.setText("");
            interfaz.jTextField100.setText("");
            interfaz.jTextField101.setText("");

        }

        //BOTON CANCELAR DE FRAME BUSCAR USUARIO
        if (ae.getSource().equals(interfaz.jButton23)) {
            interfaz.jFrameBuscarUsuario.setVisible(false);
            interfaz.jFrameGerente.setEnabled(true);
            interfaz.jFrameGerenteBotones.setEnabled(true);
            

        }
        //BOTON MODIFICAR DE FRAME BUSCAR USUARIO
        if (ae.getSource().equals(interfaz.jButton24)) {

            interfaz.jButton73.setEnabled(false);
            interfaz.jButton69.setEnabled(true);
            String datos = interfaz.jComboBox13.getSelectedItem().toString();

            if ("Seleccione un usuario".equals(datos)) {
                JOptionPane.showMessageDialog(null, "Seleccione un Usuario", "Error", WIDTH, null);

            } else {
                String id_usuario = "";
                ArrayList<String> dato1 = interfaz.obj.consultarUsuario();
                for (int i = 0; i < dato1.size(); i = i + 2) {
                    if (interfaz.jComboBox13.getSelectedItem().toString().equals(dato1.get(i) + " - " + dato1.get(i + 1))) {
                        id_usuario = dato1.get(i);
                    }
                }

                ArrayList<String> dato = interfaz.obj.consultarUsuario(id_usuario);
                interfaz.jTextField98.setText(dato.get(0));
                interfaz.jTextField98.setEnabled(false);
                interfaz.jTextField99.setText(dato.get(1));
                interfaz.jTextFieldCedulaUsuario.setText(dato.get(2));
                interfaz.jTextField93.setText(dato.get(3));
                interfaz.jTextField101.setText(dato.get(4));
                interfaz.jTextField94.setText(dato.get(5));
                interfaz.jTextField100.setText(dato.get(6));
                interfaz.jTextField95.setText(dato.get(7));
                interfaz.jTextField96.setText(dato.get(8));
                interfaz.jTextField97.setText(dato.get(9));
                interfaz.jLabel35.setText(dato.get(10));
                interfaz.jLabel27.setText(dato.get(13));
                interfaz.jComboBox27.setSelectedItem(dato.get(14));
                interfaz.jComboBox2.setSelectedItem(dato.get(12));

                String fecha = dato.get(11);
                StringTokenizer token = new StringTokenizer(fecha, "-");

                String[] date = new String[3];
                int m = 0;
                while (token.hasMoreTokens()) {
                    String str = token.nextToken();
                    date[m] = str;
                    m++;
                }
                interfaz.jComboBox26.setSelectedItem(date[0]);

                interfaz.jComboBox24.setSelectedItem(date[1].substring(1));
                String monthString;
                int numero = Integer.parseInt(date[2]);
                switch (numero) {
                    case 1:
                        monthString = "Enero";
                        break;
                    case 2:
                        monthString = "Febrero";
                        break;
                    case 3:
                        monthString = "Marzo";
                        break;
                    case 4:
                        monthString = "Abril";
                        break;
                    case 5:
                        monthString = "Mayo";
                        break;
                    case 6:
                        monthString = "Junio";
                        break;
                    case 7:
                        monthString = "Julio";
                        break;
                    case 8:
                        monthString = "Agosto";
                        break;
                    case 9:
                        monthString = "Septiembre";
                        break;
                    case 10:
                        monthString = "Octubre";
                        break;
                    case 11:
                        monthString = "Noviembre";
                        break;
                    case 12:
                        monthString = "Diciembre";
                        break;
                    default:
                        monthString = "Invalid month";
                        break;
                }

                interfaz.jComboBox25.setSelectedItem(monthString);

                ArrayList<String> sedes = interfaz.obj.consultarSede();

                for (int i = 1; i < interfaz.jComboBox23.getItemCount(); i++) {
                    interfaz.jComboBox23.removeItemAt(i);
                }

                for (int i = 0; i < sedes.size(); i = i + 2) {
                    interfaz.jComboBox23.addItem(sedes.get(i) + " - " + sedes.get(i + 1));
                }

                interfaz.jFrameUsuario4.setVisible(true);
                interfaz.jFrameUsuario4.pack();

                ImageIcon imagen = new ImageIcon(interfaz.jLabel35.getText());
                Image image = imagen.getImage();
                Image newImage = image.getScaledInstance(interfaz.jLabel34.getWidth(), interfaz.jLabel34.getHeight(), Image.SCALE_SMOOTH);
                interfaz.jLabel34.setIcon(new ImageIcon(newImage));

            }
        }

        //BOTONES DE MODIFICAR DATOS
        if (ae.getSource().equals(interfaz.jButton22)
                || ae.getSource().equals(interfaz.jButton80)
                || ae.getSource().equals(interfaz.jMenuItem21)
                || ae.getSource().equals(interfaz.jMenuItem1)) {

            interfaz.jButton69.setEnabled(true);
            interfaz.jButton73.setEnabled(false);
            interfaz.jFrameGerente.setEnabled(false);
            interfaz.jFrameGerenteBotones.setEnabled(false);
            ArrayList<String> dato = interfaz.obj.consultarUsuario(interfaz.nombreUsuario.getText());
            interfaz.jTextField98.setText(dato.get(0));
            interfaz.jTextField98.setEnabled(false);
            interfaz.jTextField99.setText(dato.get(1));
            interfaz.jTextFieldCedulaUsuario.setText(dato.get(2));
            interfaz.jTextField93.setText(dato.get(3));
            interfaz.jTextField101.setText(dato.get(4));
            interfaz.jTextField94.setText(dato.get(5));
            interfaz.jTextField100.setText(dato.get(6));
            interfaz.jTextField95.setText(dato.get(7));
            interfaz.jTextField96.setText(dato.get(8));
            interfaz.jTextField97.setText(dato.get(9));
            interfaz.jLabel35.setText(dato.get(10));
            interfaz.jLabel27.setText(dato.get(13));
            interfaz.jComboBox27.setSelectedItem(dato.get(14));
            interfaz.jComboBox2.setSelectedItem(dato.get(12));

            String fecha = dato.get(11);
            StringTokenizer token = new StringTokenizer(fecha, "-");

            String[] date = new String[3];
            int m = 0;
            while (token.hasMoreTokens()) {
                String str = token.nextToken();
                date[m] = str;
                m++;
            }
            interfaz.jComboBox26.setSelectedItem(date[0]);

            interfaz.jComboBox24.setSelectedItem(date[1].substring(1));
            String monthString;
            int numero = Integer.parseInt(date[2]);
            switch (numero) {
                case 1:
                    monthString = "Enero";
                    break;
                case 2:
                    monthString = "Febrero";
                    break;
                case 3:
                    monthString = "Marzo";
                    break;
                case 4:
                    monthString = "Abril";
                    break;
                case 5:
                    monthString = "Mayo";
                    break;
                case 6:
                    monthString = "Junio";
                    break;
                case 7:
                    monthString = "Julio";
                    break;
                case 8:
                    monthString = "Agosto";
                    break;
                case 9:
                    monthString = "Septiembre";
                    break;
                case 10:
                    monthString = "Octubre";
                    break;
                case 11:
                    monthString = "Noviembre";
                    break;
                case 12:
                    monthString = "Diciembre";
                    break;
                default:
                    monthString = "Invalid month";
                    break;
            }

            interfaz.jComboBox25.setSelectedItem(monthString);

            ArrayList<String> sedes = interfaz.obj.consultarSede();
            for (int i = 0; i < sedes.size(); i = i + 2) {
                interfaz.jComboBox23.addItem(sedes.get(i) + " - " + sedes.get(i + 1));
            }
            for (int i = 0; i < sedes.size(); i = i + 2) {
                if (dato.get(13).equals(sedes.get(i))) {
                    interfaz.jComboBox23.setSelectedItem(sedes.get(i) + " - " + sedes.get(i + 1));
                }
            }

            interfaz.jFrameUsuario4.setVisible(true);
            interfaz.jFrameUsuario4.pack();

            ImageIcon imagen = new ImageIcon(interfaz.jLabel35.getText());
            Image image = imagen.getImage();
            Image newImage = image.getScaledInstance(interfaz.jLabel34.getWidth(), interfaz.jLabel34.getHeight(), Image.SCALE_SMOOTH);
            interfaz.jLabel34.setIcon(new ImageIcon(newImage));
        }

        //EVENTO CREAR USUARIO
        if (ae.getSource().equals(interfaz.jButton4) || ae.getSource().equals(interfaz.jMenuItem23)
                || ae.getSource().equals(interfaz.jMenuItem3)) {
            interfaz.nombreUsuario15.setText(interfaz.codigo);
            interfaz.jFrameUsuario4.setVisible(true);
            interfaz.jFrameUsuario4.pack();

            interfaz.jFrameGerente.setEnabled(false);
            interfaz.jFrameGerenteBotones.setEnabled(false);
            interfaz.jButton69.setEnabled(false);

            interfaz.jButton69.setEnabled(false);
            interfaz.jButton69.setFocusable(false);

            String ruta = "/Fotos/usuario.jpeg";
            Image image = loadImage(ruta);
            Image newImage = image.getScaledInstance(interfaz.jLabel34.getWidth(), interfaz.jLabel34.getHeight(), Image.SCALE_SMOOTH);
            interfaz.jLabel34.setIcon(new ImageIcon(newImage));
            interfaz.jLabel35.setText(ruta);

            for (int i = 1; i < interfaz.jComboBox23.getItemCount(); i++) {
                interfaz.jComboBox23.removeItemAt(i);
            }

            ArrayList<String> sedes = interfaz.obj.consultarSede();
            for (int i = 0; i < sedes.size(); i = i + 2) {
                interfaz.jComboBox23.addItem(sedes.get(i) + " - " + sedes.get(i + 1));
            }

        }

        //EVENTO MODIFICAR USUARIO
        if (ae.getSource().equals(interfaz.jButton5) || ae.getSource().equals(interfaz.jMenuItem24)
                || ae.getSource().equals(interfaz.jMenuItem4)) {
            interfaz.jFrameGerente.setEnabled(false);
            interfaz.jFrameGerenteBotones.setEnabled(false);
            interfaz.jFrameBuscarUsuario.setVisible(true);
            interfaz.jFrameBuscarUsuario.setSize(new Dimension(250, 150));
            interfaz.jTextFieldCedulaUsuario.setText("");
            interfaz.jTextField93.setText("");
            interfaz.jTextField101.setText("");
            interfaz.jTextField94.setText("");
            interfaz.jTextField100.setText("");
            interfaz.jTextField95.setText("");
            interfaz.jTextField96.setText("");
            interfaz.jTextField97.setText("");
            interfaz.jTextField98.setText("");
            interfaz.jTextField99.setText("");
            interfaz.jComboBox2.setSelectedIndex(0);
            interfaz.jComboBox23.setSelectedIndex(0);
            interfaz.jComboBox24.setSelectedIndex(0);
            interfaz.jComboBox25.setSelectedIndex(0);
            interfaz.jComboBox26.setSelectedIndex(0);
            interfaz.jComboBox27.setSelectedIndex(0);

            ArrayList<String> datos;
            datos = interfaz.obj.consultarUsuario();
            for (int i = 0; i < datos.size(); i = i + 2) {
                interfaz.jComboBox13.addItem(datos.get(i) + " - " + datos.get(i + 1));
            }

        }

        //EVENTO LISTAR USUARIO
        if (ae.getSource().equals(interfaz.jButton12) || ae.getSource().equals(interfaz.jMenuItem25)) {
            interfaz.jTable1.setVisible(true);
            interfaz.obj.listarU((DefaultTableModel) interfaz.jTable1.getModel());
        }
        if (ae.getSource().equals(interfaz.jMenuItem5)) {
            interfaz.jTable7.setVisible(true);
            interfaz.obj.listarU((DefaultTableModel) interfaz.jTable7.getModel());
        }

        //REABASTECER REPUESTO
        if (ae.getSource().equals(interfaz.jButton41)
                || ae.getSource().equals(interfaz.jMenuItem28)
                || ae.getSource().equals(interfaz.jMenuItem6)
                || ae.getSource().equals(interfaz.jButton78)) {
            interfaz.jFrameReabastecer.setVisible(true);
            interfaz.jFrameReabastecer.pack();
        }

        //BOTON LISTAR DE FRAME REABASTECER
        if (ae.getSource().equals(interfaz.jButton71)) {
            interfaz.jTable4.setVisible(true);
            interfaz.obj.listarR((DefaultTableModel) interfaz.jTable4.getModel(), 0, interfaz.jTextField13.getText());
        }
        //BOTON REABASTECER DE FRAME REABASTECER
        if (ae.getSource().equals(interfaz.jButton72)) {
            JOptionPane.showMessageDialog(null, "Se registraron correctamente los repuestos");
            String nombre = interfaz.jTextField21.getText();
            int valor = interfaz.jSlider2.getValue();
            System.out.println(nombre + " " + valor);
            String cantidad = interfaz.obj.consultarRepuesto(nombre);
            int nueva_cantidad = Integer.parseInt(cantidad) + valor;
            interfaz.obj2.ActualizarRepuesto(nombre, nueva_cantidad);
            interfaz.jTable4.setVisible(true);
            interfaz.obj.listarR((DefaultTableModel) interfaz.jTable4.getModel(), 0, interfaz.jTextField13.getText());

        }

        //BUSCAR REPUESTO
        if (ae.getSource().equals(interfaz.jButton20)
                || ae.getSource().equals(interfaz.jMenuItem29)
                || ae.getSource().equals(interfaz.jMenuItem8)
                || ae.getSource().equals(interfaz.jButton88)) {
            interfaz.jTable2.setVisible(false);
            interfaz.jFrameInventarioRepuestos.setVisible(true);
            interfaz.jFrameInventarioRepuestos.pack();
        }
        //BUSCAR CARROS
        if (ae.getSource().equals(interfaz.jButton21)
                || ae.getSource().equals(interfaz.jMenuItem30)
                || ae.getSource().equals(interfaz.jMenuItem14)
                || ae.getSource().equals(interfaz.jButton79)) {
            interfaz.jTable3.setVisible(false);
            interfaz.jFrameInventarioAutos.setVisible(true);
            interfaz.jFrameInventarioAutos.pack();
        }

        //SALIR
        if (ae.getSource().equals(interfaz.jMenuItem22)
                || ae.getSource().equals(interfaz.jMenuItem2)) {
            System.exit(1);
        }

        //REPORTES VENTAS
        if (ae.getSource().equals(interfaz.btReportes)
                || ae.getSource().equals(interfaz.jMenuItem35)
                || ae.getSource().equals(interfaz.jMenuItem18)) {
            GenerarReportes repor = new GenerarReportes();
            repor.reporteUsuarios();
        }

        //REPORTES COTIZACIONES
        
        

        //REPORTES INVENTARIO REPUESTOS

        //REPORTES INVENTARIO AUTOMOVILES

        //REPORTES BUSCAR VENTA
        if (ae.getSource().equals(interfaz.jButton35)
                || ae.getSource().equals(interfaz.jMenuItem33)
                || ae.getSource().equals(interfaz.jMenuItem11)) {

            interfaz.jFrameBuscarVenta.setVisible(true);
            interfaz.jFrameBuscarVenta.pack();
            interfaz.jTextField22.setEnabled(false);
            interfaz.jButton3.setEnabled(false);
            interfaz.jComboBox15.setEnabled(false);
            interfaz.jComboBox16.setEnabled(false);
            interfaz.jComboBox28.setEnabled(false);
            interfaz.jComboBox29.setEnabled(false);
            interfaz.jComboBox30.setEnabled(false);
            interfaz.jComboBox31.setEnabled(false);
            interfaz.jComboBox32.setEnabled(false);

            interfaz.jFrameGerente.setEnabled(false);
            interfaz.jFrameGerenteBotones.setEnabled(false);
        }

        //REPORTES BUSCAR COTIZACION
        if (ae.getSource().equals(interfaz.jButton34)
                || ae.getSource().equals(interfaz.jMenuItem34)
                || ae.getSource().equals(interfaz.jMenuItem15)) {

            interfaz.jTextField23.setEnabled(false);
            interfaz.jButton76.setEnabled(false);
            interfaz.jComboBox36.setEnabled(false);
            interfaz.jComboBox37.setEnabled(false);
            interfaz.jComboBox38.setEnabled(false);
            interfaz.jComboBox39.setEnabled(false);
            interfaz.jComboBox40.setEnabled(false);
            interfaz.jComboBox41.setEnabled(false);
            interfaz.jFrameBuscarCotizaciones.setVisible(true);
            interfaz.jFrameBuscarCotizaciones.pack();

            interfaz.jFrameGerente.setEnabled(false);
            interfaz.jFrameGerenteBotones.setEnabled(false);
        }

        //SALIR DE BUSCAR COTIZACIONES Y VENTAS
        if (ae.getSource().equals(interfaz.jButton31)
                || ae.getSource().equals(interfaz.jButton47)) {

            interfaz.jFrameGerente.setEnabled(true);
            interfaz.jFrameGerenteBotones.setEnabled(true);
            interfaz.jFrameBuscarCotizaciones.setVisible(false);
            interfaz.jFrameBuscarVenta.setVisible(false);
        }

        //OPCION CREDITO
        if (ae.getSource().equals(interfaz.jRadioButton4)) {
            interfaz.jRadioButton5.setSelected(false);

            interfaz.jTextField66.setEnabled(true);
            interfaz.jTextField67.setEnabled(true);
            interfaz.jButton48.setEnabled(true);

            interfaz.jTextField69.setText("");
        }

        //OPCION CONTADO
        if (ae.getSource().equals(interfaz.jRadioButton5)) {
            interfaz.jRadioButton4.setSelected(false);

            interfaz.jButton48.setEnabled(false);

            interfaz.jTextField69.setText(interfaz.jTextField65.getText());

            interfaz.jTextField66.setEnabled(false);
            interfaz.jTextField67.setEnabled(false);

            interfaz.jTextField68.setText("");
            interfaz.jTextField66.setText("");
            interfaz.jTextField67.setText("");
        }

        //OPCION CUOTA
        if (ae.getSource().equals(interfaz.jButton48)) {
            double cuota = 0;

            if (validar.validarNumero(interfaz.jTextField66.getText())
                    & validar.validarNumero(interfaz.jTextField67.getText())) {

                double valor = Double.parseDouble(interfaz.jTextField65.getText());
                double inicial = Double.parseDouble(interfaz.jTextField66.getText());
                int cuotas = Integer.parseInt(interfaz.jTextField67.getText());
                cuota = (valor - inicial) / cuotas;

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese solo valores numericos");
            }

            interfaz.jTextField68.setText("" + cuota);
        }        
        
    }

//Eventos de loguearse
    void pintar() {
        List<Avatar> avatares = new ArrayList<>();
        avatares.add(new Avatar("menu 1", loadImage("/Fotos/1.jpg")));
        avatares.add(new Avatar("menu 2", loadImage("/Fotos/2.jpg")));
        avatares.add(new Avatar("menu 2", loadImage("/Fotos/3.jpg")));
        avatares.add(new Avatar("menu 2", loadImage("/Fotos/4.jpg")));
        avatares.add(new Avatar("menu 14", loadImage("/Fotos/5.jpg")));
        avatares.add(new Avatar("menu 14", loadImage("/Fotos/6.jpg")));
        //interfaz.panelAvatarChooser1.setAvatars(avatares);

        interfaz.jFrameGerente.setVisible(true);
        interfaz.jCheckBoxMenuItem1.setState(false);
        interfaz.jFrameGerente.pack();

    }

    private static Image loadImage(String fileName) {
        try {
            return ImageIO.read(JFrame.class
                    .getResource(fileName));
        } catch (IOException ex) {
            return null;
        }
    }

}
