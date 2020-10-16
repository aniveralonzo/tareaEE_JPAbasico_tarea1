package edu.academik.telus.jdbc_basico;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class Main {
    public static void main(String []args){
         Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientefactura?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "Sadmywaleska2020");
            System.out.println("Base de datos conectada!!!");
//            findCodigoBarras(conn);
  //          findClientexNit(conn);
//            ListadoFacRangoFecha(conn);
            countFacturas(conn);

            
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex1) {
                    System.err.println(ex1.getMessage());
                }
            }
        }                    
    }

    
    private static void findCodigoBarras(Connection conn) throws SQLException {

        String queryString = "SELECT * FROM producto WHERE codigo_barras=77558899";
        // try with resource ---> el va  a cerrar el stmt
        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int productoId  = rs.getInt("producto_id");
                    int codigo = rs.getInt("codigo");
                    int codigoBarras = rs.getInt("codigo_barras");
                    String nombre = rs.getString("nombre");
                    BigDecimal precio = rs.getBigDecimal("precio");

                    //Display values
                    System.out.println("producto id: " + productoId);
                    System.out.println("codigo: " + codigo);
                    System.out.println("codigo barras: " +codigoBarras);
                    System.out.println("nombre" + nombre);
                    System.out.println("precio" + precio);
                }
                    System.out.println();
                          rs.close();
                }
            }
        }            

    private static void findClientexNit(Connection conn) throws SQLException {

        String queryString = "SELECT * FROM cliente WHERE nit=8899";
        // try with resource ---> el va  a cerrar el stmt
        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int clienteId = rs.getInt("cliente_id");
                    String nombre = rs.getString("nombre");
                    String nit = rs.getString("nit");
                    String direccion = rs.getString("direccion");
                    
                    //Display values
                    System.out.println("cliente  ID: " + clienteId);
                    System.out.println("nombre: " +nombre);
                    System.out.println("nit" + nit);
                    System.out.println("direccion" + direccion);
                }
                    System.out.println();
                          rs.close();
                }
            }
        }
    
    
    private static void ListadoFacRangoFecha(Connection conn) throws SQLException {

        String queryString = "SELECT * FROM factura WHERE fecha BETWEEN '2018-09-01' AND '2019-12-31'";
        // try with resource ---> el va  a cerrar el stmt
        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int facturaId = rs.getInt("factura_id");
                    String numero = rs.getString("numero");
                    Timestamp fecha = rs.getTimestamp("fecha");
                    int clienteId = rs.getInt("cliente_id");                    
                    //Display values
                    System.out.println("Factura ID: " + facturaId);
                    System.out.println("nombre: " +numero);
                    System.out.println("fecha" + fecha);
                    System.out.println("cliente ID: " + clienteId);
                    System.out.println("");
                }
                    System.out.println();
                          rs.close();
                }
            }
        }
    
    private static void countFacturas(Connection conn) throws SQLException {
        String queryString = "SELECT numero,COUNT(*)AS NumberOfFact FROM factura  GROUP BY numero";        
        
        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {                    
                        String numero = rs.getString("numero");
                        int facturas=rs.getInt("NumberOfFact");

                        System.out.println("numero: " +numero);
                        System.out.println("Suma de facturas: "+facturas);
                        
                        System.out.println("###################");
                        System.out.println("");
                }
                    System.out.println();
                          rs.close();
                }
            }
        }
}
