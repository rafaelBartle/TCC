/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;



public class ConnectionFactory {
   
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String URL = "jdbc:mariadb://db4free.net:3306/vendasnbl";
    private static final String USER = "nbl_user";
    private static final String PASS = "84259973xp";
    public Statement statement;
    public ResultSet resultset;
    private Connection conexao;
    
    
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
           throw new RuntimeException("Erro na conexão: ", ex);
        }  
    }
    
    public static void closeConnection(Connection con){
        try {
            if(con != null){
                con.close();
            }
        } catch (SQLException ex) {
           throw new RuntimeException("Erro ao fechar conexão: ", ex);
        } 
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
        closeConnection(con);
        try {
            if(stmt != null){
                stmt.close();
            }
        } catch (SQLException ex) {
           throw new RuntimeException("Erro ao fechar conexão: ", ex);
        } 
    }
        
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        closeConnection(con,stmt);
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException ex) {
           throw new RuntimeException("Erro ao fechar conexão: ", ex);
        } 
    }
    
}
