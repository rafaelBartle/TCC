/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.dao;




import conexao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.VendasBEAN;

/**
 *
 * @author rafae 
 */
public class VendasDAO {
    public void create(VendasBEAN ven){
    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO Venda (Codigo, Vendedor  , Produto , Quantidade , ValorTotal ,Data ) VALUES (?,?,?,?,?,?)");
            stmt.setString(1, ven.getCodigo());
            stmt.setString(2, ven.getVendedor());
            stmt.setString(3, ven.getProduto());
            stmt.setString(4, ven.getQuantidade());
            stmt.setString(5, ven.getValorTotal());
            stmt.setString(6, ven.getData());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "VENDA SALVA NO BANCO DE DADOS");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO SALVAR VENDA NO BANCO DE DADOS");
          throw new RuntimeException("ERRO AO INSERIR NA TABELA VENDA: ", ex);
        
          } finally{
            ConnectionFactory.closeConnection(con, stmt);
        } 
    
    }
public java.util.List<VendasBEAN> read(){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       java.util.List<VendasBEAN> vendas = new ArrayList<>();
       try{
           stmt = con.prepareStatement("SELECT * FROM Venda");
           rs = stmt.executeQuery();
           while(rs.next()){
               VendasBEAN venda = new VendasBEAN(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
               vendas.add(venda);
           }
       }catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar vendas: ", ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        } 
       return vendas;
    }



public VendasBEAN get(String codigo){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       java.util.List<VendasBEAN> venda = new ArrayList<>();
       try{
           stmt = con.prepareStatement("SELECT * FROM Venda WHERE  Codigo LIKE ?");
           stmt.setString(1, codigo);
           rs = stmt.executeQuery();
           while(rs.next()){
               VendasBEAN emp = new VendasBEAN(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4)
                       ,rs.getString(5),rs.getString(6));
               venda.add(emp);
           }
       }catch (SQLException ex) {
            throw new RuntimeException("ERRO AO BUSCAR UMA ??NICA VENDA: ", ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        } 
       return venda.get(0);
    
    }

public  java.util.List<VendasBEAN> getVendedor(String vendedor){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       java.util.List<VendasBEAN> venda = new ArrayList<>();
       try{
           stmt = con.prepareStatement("SELECT * FROM Venda WHERE Vendedor LIKE ?");
           stmt.setString(1, "%" + vendedor +"%");
           rs = stmt.executeQuery();
           while(rs.next()){
               VendasBEAN ven = new VendasBEAN(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
               venda.add(ven);
           }
       }catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar uma venda pelo vendedor: ", ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        } 
       return venda;
       
       
    }



  public void update(VendasBEAN venda){
    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Venda SET Codigo = ? , Vendedor = ?  , Produto = ? ,Quantidade = ?, ValorTotal = ? , Data = ?   WHERE  Codigo LIKE ?");
            stmt.setString(1, venda.getCodigo());
            stmt.setString(2, venda.getVendedor());
            stmt.setString(3, venda.getProduto());
            stmt.setString(4, venda.getQuantidade());
            stmt.setString(5, venda.getValorTotal());
            stmt.setString(6, venda.getData());
            stmt.setString(7, "%"+venda.getCodigo()+"%");

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "VENDA ATUALIZADA NO BANCO DE DADOS");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO EDITAR A VENDA NO BANCO DE DADOS");
          throw new RuntimeException("Erro ao editar na tabela Venda: ", ex);
        
          } finally{
            ConnectionFactory.closeConnection(con, stmt);
        } 
  }
  public void delete(String codigo){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
     
       try{
           stmt = con.prepareStatement("DELETE  FROM Venda WHERE codigo LIKE ?");
           stmt.setString(1, codigo);
           stmt.executeUpdate();
           JOptionPane.showMessageDialog(null,"VENDA EXCLUIDA DO BANCO DE DADOS");
       }catch (SQLException ex) {
           
       JOptionPane.showMessageDialog(null,"ERRO AO EXCLUIR VENDA");
            throw new RuntimeException("ERRO AO EXCLUIR VENDA: ", ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        } 
  }
  
  
}




