/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static java.awt.image.ImageObserver.WIDTH;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.*;

/**
 *
 * @author USUARIO
 */
public class AccesoDatos {

    Fachada fachada;
    ResultSet respuesta;
    Statement instruccion;

    public AccesoDatos() {
        fachada = new Fachada();
    }

    /**
     * Método que se encarga de ingresar la información de un programa a la base
     * de datos *
     */
    public int ingresarUsuario(String id, String pass, String cdl, String pNom, String sNom, String pApe, String sApe, String tel1, String tel2, String dir, String foto, String fdn, String estado, String id_sede, String tipo) {
        int numFilas;

        String consulta = "INSERT INTO usuario VALUES ('" + id + "','" + pass + "','" + cdl + "','" + pNom + "','" + sNom + "','" + pApe + "','" + sApe + "','" + tel1 + "','" + tel2 + "','" + dir + "','" + foto + "','" + fdn + "','" + estado + "','" + id_sede + "','" + tipo + "');";
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            numFilas = instruccion.executeUpdate(consulta);
            fachada.cerrarConexion(con);
        } catch (SQLException sqle) {
            System.out.println("Error de Sql al conectar en programa \n" + sqle);
            numFilas = -1;
        } catch (Exception e) {
            System.out.println("Ocurrió cualquier otra excepcion en programa" + e);
            numFilas = -1;
        }

        return numFilas;
    }

    //Metodo que se encarga de ingresar una sede a la base de datos
    public boolean ingresarSede(String id_sede, String nom, String ciudad, String direccion, String tlf1, String tlf2) {
        boolean aux = false;
        String consulta = "INSERT INTO sede VALUES ('" + id_sede + "','" + nom + "','" + ciudad + "','" + direccion + "','" + tlf1 + "','" + tlf2 + "');";
        System.out.println(consulta);
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            instruccion.executeUpdate(consulta);
            fachada.cerrarConexion(con);
            aux = true;
        } catch (SQLException sqle) {
            System.out.println("Error de Sql al conectar en programa \n" + sqle);

        } catch (Exception e) {
            System.out.println("Ocurrió cualquier otra excepcion en programa" + e);

        }
        return aux;
    }

    //Metodo quese encarga de retornar los datos de los usuarios que estan registrados para poder compararlos
    //con los datos que el usuario digita en la ventana
    public ResultSet loguearse(String nom) {
        String consulta = "SELECT id_usuario,password FROM usuario WHERE id_usuario='" + nom + "';";
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);

            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }
        return respuesta;
    }

    //Toma el id de usuario y extrae el tipo  de ese usuario para asi saber que ventana retornara 
    public ResultSet validarUsuario(String nom) {
        String consulta = "SELECT id_usuario,tipo FROM usuario WHERE id_usuario='" + nom + "';";
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);
        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }
        return respuesta;
    }

    /* Extrae de la base de datos la informacion de una sede, esto es importante para la modificacion de la sede*/
    public ResultSet consultaSede(String id_sede) {
        String consulta = "SELECT * FROM sede WHERE id_sede = '" + id_sede + "';";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    /*Extrae los id y nombres de las sedes, esto es para llenar el combobox de modificar */
    public ResultSet consultarSede() {
        String consulta = "SELECT id_sede,nombre FROM sede ORDER BY id_sede;";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    /*selecciona los id de los usuarios tipo Gerentes, esto es para llenar el combobox del registro de la sede*/
    public ResultSet tomarGerentes() {
        String consulta = "SELECT id_usuario FROM usuario WHERE tipo = 'Gerente' ORDER BY id_usuario;";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    //actualizacion de las sedes, realiza el update para que se actualice esa sede en especifico 
    public int actualizarSede(String id_sede, String nom, String ciudad, String direccion, String tlf1, String tlf2) {
        int numFilas;
        String consulta = "UPDATE sede SET id_sede = '" + id_sede + "',nombre = '" + nom + "',ciudad = '" + ciudad + "',direccion = '" + direccion + "',telefono1 = '" + tlf1 + "',telefono2 = '" + tlf2 + "' WHERE id_sede = '" + id_sede + "';";
        System.out.println(consulta);
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            numFilas = instruccion.executeUpdate(consulta);
            fachada.cerrarConexion(con);
        } catch (SQLException sqle) {
            System.out.println("Error de Sql al conectar en programa \n" + sqle);
            numFilas = -1;
        } catch (Exception e) {
            System.out.println("Ocurrió cualquier otra excepcion en programa" + e);
            numFilas = -1;
        }
        return numFilas;
    }

    public ResultSet consultarUsuario(String id) {
        ResultSet usuario = null;

        String consulta = "SELECT * FROM usuario WHERE id_usuario = " + "'" + id + "'";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            usuario = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos" + sqle);
        }

        return usuario;
    }

    ResultSet consultarCliente(String cedula) {
        ResultSet cliente = null;

        String consulta = "SELECT * FROM cliente WHERE cedula = " + "'" + cedula + "'";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            cliente = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos" + sqle);
        }

        return cliente;
    }

    ResultSet consultarSede(String id_vendedor) {
        ResultSet usuario = null;

        String consulta = "SELECT id_sede FROM usuario WHERE id_usuario = " + "'" + id_vendedor + "'";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            usuario = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos" + sqle);
        }

        return usuario;
    }

    ResultSet listarCotizaciones(String cedula) {
        String consulta = "SELECT id_cotizacion,fecha FROM cotizacion WHERE cedula_cliente = '" + cedula + "' ORDER BY id_cotizacion;";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet consultarCotizacionFecha(String fecha) {
        String consulta = "SELECT id_cotizacion,fecha FROM cotizacion WHERE fecha = '" + fecha + "';";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet consultarFoto(String id) {
        String consulta = "SELECT foto FROM usuario WHERE id_usuario = '" + id + "';";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet consultarDatosSede() {
        String consulta = "SELECT * FROM sede ORDER BY id_sede;";
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet consultarDatosUsuario() {
        String consulta = "SELECT id_sede,id_usuario, cedula,primer_nombre,primer_apellido,"
                + "                telefono1,telefono2,direccion,estado FROM usuario GROUP BY id_sede,id_usuario ORDER BY id_sede;";

        System.err.println("Entro3");
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet consultarDatosVendedor() {
        String consulta = "SELECT id_sede,id_usuario, cedula,primer_nombre,primer_apellido,"
                + "                telefono1,telefono2,direccion,estado FROM usuario WHERE tipo = 'Vendedor' GROUP BY id_sede,id_usuario ORDER BY id_sede;";
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet consultarUsuario() {
        String consulta = "SELECT id_usuario,tipo FROM usuario ORDER BY id_usuario;";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    int ActualizarUsuario(String id, String pass, String cdl, String pNom, String sNom, String pApe, String sApe, String tel1, String tel2, String dir, String foto, String fdn, String estado, String sede, String tipo) {
        int numFilas;
        String id_sede = "";
        StringTokenizer tokens = new StringTokenizer(sede, "-");
        int nDatos = tokens.countTokens();
        String[] datos = new String[nDatos];
        int i = 0;
        while (tokens.hasMoreTokens()) {
            String str = tokens.nextToken();
            datos[i] = str;
            System.out.println(datos[i]);
            i++;
        }
        id_sede = datos[0];

        String consulta = "UPDATE usuario SET id_usuario = '" + id + "', password = '" + pass + "',cedula = '" + cdl + "',primer_nombre='" + pNom + "',segundo_nombre = '" + sNom + "',primer_apellido ='" + pApe + "',segundo_apellido = '" + sApe + "',telefono1 ='" + tel1 + "',telefono2='" + tel2 + "',direccion='" + dir + "',foto ='" + foto + "',fecha_nacimiento='" + fdn + "',estado='" + estado + "',id_sede='" + id_sede + "',tipo='" + tipo + "' WHERE id_usuario = '" + id + "';";
        System.out.println(consulta);
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            numFilas = instruccion.executeUpdate(consulta);
            fachada.cerrarConexion(con);
        } catch (SQLException sqle) {
            System.out.println("Error de Sql al conectar en programa \n" + sqle);
            numFilas = -1;
        } catch (Exception e) {
            System.out.println("Ocurrió cualquier otra excepcion en programa" + e);
            numFilas = -1;
        }

        return numFilas;
    }

    ResultSet consultarDatosRepuestos() {
        String consulta = "SELECT * FROM repuesto ORDER BY id_repuesto;";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet consultarDatosRepuestos(String nombre) {
        if (nombre.equalsIgnoreCase("chevrolet")) {
            nombre = "Chevrolet";
        } else {
            if (nombre.equalsIgnoreCase("mazda")) {
                nombre = "Mazda";
            } else {
                if (nombre.equalsIgnoreCase("audi")) {
                    nombre = "Audi";
                } else {
                    if (nombre.equalsIgnoreCase("Ford")) {
                        nombre = "Ford";
                    } else {
                        JOptionPane.showMessageDialog(null, "La marca que busca no se encuentra en nuestra base de datos", "Error", WIDTH, null);
                    }
                }
            }
        }

        String consulta = "SELECT * FROM repuesto WHERE marca = '" + nombre + "';";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet consultarDatosAuto() {
        String consulta = "SELECT * FROM carro;";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet consultarDatosAuto(String nombre) {
        if (nombre.equalsIgnoreCase("chevrolet")) {
            nombre = "chevrolet";
        } else {
            if (nombre.equalsIgnoreCase("mazda")) {
                nombre = "mazda";
            } else {
                if (nombre.equalsIgnoreCase("audi")) {
                    nombre = "audi";
                } else {
                    if (nombre.equalsIgnoreCase("Ford")) {
                        nombre = "ford";
                    } else {
                        JOptionPane.showMessageDialog(null, "La marca que busca no se encuentra en nuestra base de datos", "Error", WIDTH, null);
                    }
                }
            }
        }

        String consulta = "SELECT * FROM carro WHERE marca = '" + nombre + "';";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet consultarCantidad(String nombre) {
        ResultSet usuario = null;

        String consulta = "SELECT cantidad FROM repuesto WHERE id_repuesto = " + "'" + nombre + "'";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            usuario = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos" + sqle);
        }

        return usuario;
    }

    void ActualizarRepuesto(String nombre, int nueva_cantidad) {
        int numFilas;
        String consulta = "UPDATE repuesto SET cantidad = '" + nueva_cantidad + "' WHERE id_repuesto = '" + nombre + "';";
        System.out.println(consulta);
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            numFilas = instruccion.executeUpdate(consulta);
            fachada.cerrarConexion(con);
        } catch (SQLException sqle) {
            System.out.println("Error de Sql al conectar en programa \n" + sqle);
            numFilas = -1;
        } catch (Exception e) {
            System.out.println("Ocurrió cualquier otra excepcion en programa" + e);
            numFilas = -1;
        }

    }

    ResultSet consultarDatosventa(String busqueda, String busquda2, int numero) {
        String consulta = "";
        if (numero == 0) {
            consulta = "SELECT * FROM venta WHERE precio_total = '" + busqueda + "' ORDER BY id_vente;";
        }
        if (numero == 1) {
            consulta = "SELECT * FROM venta WHERE forma_pago = '" + busqueda + "' ORDER BY id_vente;";
        }
        if (numero == 2) {
            consulta = "SELECT * FROM venta WHERE fecha >= '" + busqueda + "' and fecha <= '" + busquda2 + "' ORDER BY id_vente;";
        }
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet consultarDatosCotizacion(String busqueda, String busqueda2, int numero) {
        String consulta = "";
        if (numero == 0) {
            consulta = "SELECT * FROM cotizacion WHERE cedula_cliente = '" + busqueda + "' ORDER BY id_cotizacion;";
        }
        if (numero == 2) {
            consulta = "SELECT * FROM cotizacion WHERE fecha >= '" + busqueda + "' and fecha <= '" + busqueda2 + "' ORDER BY id_cotizacion;";
        }
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet consultarfoto(String id) {
        String consulta = "SELECT foto FROM usuario WHERE id_usuario= '" + id + "';";
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);
        } catch (SQLException sqle) {
            System.out.println("Error de Sql al conectar en programa \n" + sqle);

        } catch (Exception e) {
            System.out.println("Ocurrió cualquier otra excepcion en programa" + e);
        }
        return respuesta;
    }

    //Manejo de Ordenes de Trabajo
    ResultSet consultarDatosOrdenes() {
        String consulta = "SELECT ord.id_orden, c.primer_nombre as \"Cliente\", em.primer_nombre as \"Jefe de Taller\" \n"
                + "FROM orden_trabajo as ord, cliente as c, usuario as em\n"
                + "WHERE ord.id_jefe_taller = em.id_usuario and ord.cedula_cliente = c.cedula";
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet consultarOrdenes() {
        String consulta = "SELECT id_orden FROM orden_trabajo;";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    public int ingresarOrden(String id_orden, String placa, String marca, String modelo, String descrip, int precio, String id_jefe_taller) {
        int numFilas;
        String consulta = "INSERT INTO orden_trabajo VALUES ('" + id_orden + "', '" + placa + "', '" + marca + "', '" + modelo + "', '" + descrip + "', " + precio + ", '" + id_jefe_taller + "');";
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            numFilas = instruccion.executeUpdate(consulta);
            fachada.cerrarConexion(con);
        } catch (SQLException sqle) {
            System.out.println("Error de Sql al conectar en programa \n" + sqle);
            numFilas = -1;
        } catch (Exception e) {
            System.out.println("Ocurrió cualquier otra excepcion en programa" + e);
            numFilas = -1;
        }
        return numFilas;
    }

    public int ingresarClientes(String cedula, String primNom, String segNom, String primAp, String segAp, String telefono) {
        int numFilas;
        String consulta = "INSERT INTO cliente VALUES ('" + cedula + "', '" + primNom + "', '" + segNom + "', '" + primAp + "', '" + segAp + "', '" + telefono + "');";
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            numFilas = instruccion.executeUpdate(consulta);
            fachada.cerrarConexion(con);
        } catch (SQLException sqle) {
            System.out.println("Error de Sql al conectar en programa \n" + sqle);
            numFilas = -1;
        } catch (Exception e) {
            System.out.println("Ocurrió cualquier otra excepcion en programa" + e);
            numFilas = -1;
        }
        return numFilas;
    }

    ResultSet consultarOrden(String id) {
        String consulta = "SELECT * FROM orden_trabajo WHERE id_orden = " + "'" + id + "';";
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet consultarClientes() {
        String consulta = "SELECT cedula FROM cliente;";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet obtenerUltimaOrden() {
        String consulta = "SELECT MAX(id_orden) FROM orden_trabajo;";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet consultarRepuestos() {
        String consulta = "SELECT id_repuesto FROM repuesto;";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet consultarDatosAutos() {
        String consulta = "SELECT * FROM carro;";
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    ResultSet obtenerUltimaVenta() {
        String consulta = "SELECT MAX(id_venta) FROM venta;";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    public int ingresarVenta(String id, double subtotal, double descuento, double iva, double total, String fecha, String cedula, String id_vendedor, String id_sede, String forma_de_pago, double cuota_inicial, int cuotas, double valor_Cuota) {
        int numFilas;
        String consulta = "INSERT INTO venta VALUES ('" + id + "', '" + subtotal + "','" + descuento + "', '" + iva + "','" + total + "', '" + fecha + "', '" + cedula + "','" + id_vendedor + "', '" + id_sede + "','" + forma_de_pago + "', '" + cuota_inicial + "','" + cuotas + "','" + valor_Cuota + "' );";
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            numFilas = instruccion.executeUpdate(consulta);
            fachada.cerrarConexion(con);
        } catch (SQLException sqle) {
            System.out.println("Error de Sql al conectar en programa \n" + sqle);
            numFilas = -1;
        } catch (Exception e) {
            System.out.println("Ocurrió cualquier otra excepcion en programa" + e);
            numFilas = -1;
        }
        return numFilas;
    }

    int insetarDetallesVenta(String id, String id_carro, String descripcion, int cantidad) {
        int numFilas;
        String consulta = "INSERT INTO detalles_venta VALUES ('" + id + "', '" + id_carro + "', '" + descripcion + "', '" + cantidad + "');";
        System.out.println(consulta);
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            numFilas = instruccion.executeUpdate(consulta);
            fachada.cerrarConexion(con);
        } catch (SQLException sqle) {
            System.out.println("Error de Sql al conectar en programa \n" + sqle);
            numFilas = -1;
        } catch (Exception e) {
            System.out.println("Ocurrió cualquier otra excepcion en programa" + e);
            numFilas = -1;
        }
        return numFilas;
    }

    ResultSet cantidadCotizaciones() {
        String consulta = "SELECT id_cotizacion  FROM cotizacion;";
        System.out.println(consulta);
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();

            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error de Sql al conectar en programa \n" + sqle);

        } catch (Exception e) {
            System.out.println("Ocurrió cualquier otra excepcion en programa" + e);

        }
        return respuesta;
    }

    ResultSet consultarCotizacion(String cedula) {
        String consulta = "SELECT id_cotizacion,valor FROM cotizacion;";

        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            respuesta = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

        } catch (SQLException sqle) {
            System.out.println("Error al consultar datos");
        }

        return respuesta;
    }

    int cantidadVentas() {
        int cantidad = 0;
        ResultSet aux;
        String consulta = "SELECT id_venta FROM venta;";
        System.out.println(consulta);
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();

            aux = instruccion.executeQuery(consulta);
            fachada.cerrarConexion(con);

            ArrayList<String> id_ventas = new ArrayList();

            while (aux.next()) {
                id_ventas.add(aux.getString("id_venta"));
            }

            cantidad = id_ventas.size();

        } catch (SQLException sqle) {
            System.out.println("Error de Sql al conectar en programa \n" + sqle);

        } catch (Exception e) {
            System.out.println("Ocurrió cualquier otra excepcion en programa" + e);

        }
        return cantidad;
    }

    int ingresarCotizacion(String id, String fecha, String cedula, double valor, String vendedor, String sede, String forma_pago, double cuota_inicial, double cuota, int cuotas) {
        int numFilas;
        String consulta = "INSERT INTO cotizacion VALUES ('" + id + "', '" + fecha + "','" + cedula + "', '" + valor + "','" + vendedor + "', '" + sede + "', '" + forma_pago + "','" + cuota_inicial + "', '" + cuota + "','" + cuotas + "' );";
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            numFilas = instruccion.executeUpdate(consulta);
            fachada.cerrarConexion(con);
        } catch (SQLException sqle) {
            System.out.println("Error de Sql al conectar en programa \n" + sqle);
            numFilas = -1;
        } catch (Exception e) {
            System.out.println("Ocurrió cualquier otra excepcion en programa" + e);
            numFilas = -1;
        }
        return numFilas;
    }

    public String sedeEmpleado(String id_empleado){
    
        String resultado = "";
        String consulta = "SELECT id_sede FROM usuario WHERE id_usuario = '"+ id_empleado +"'";
        try {
            Connection con = fachada.conectarABD();
            instruccion = con.createStatement();
            ResultSet rs = instruccion.executeQuery(consulta);

            if (rs != null) {

                while (rs.next()) {
                    resultado = rs.getString("id_sede");
                }
            }

            rs.close();
            fachada.cerrarConexion(con);
            
        } catch (SQLException sqle) {
            System.out.println("Error de Sql al conectar en programa \n" + sqle);
        } catch (Exception e) {
            System.out.println("Ocurrió cualquier otra excepcion en programa" + e);
        }
        
        return resultado;
    }
}
