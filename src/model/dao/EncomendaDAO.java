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
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.EncomendaBEAN;

/**
 *
 * @author rafae
 */
public class EncomendaDAO {
    public void create(EncomendaBEAN Encomenda) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("INSERT INTO encomendas (codigo, produto, descricao, status, data, previsao, quantidade, valor) VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
            stmt.setString(1, Encomenda.getCodigo());
            stmt.setString(2, Encomenda.getProduto());
            stmt.setString(3, Encomenda.getDescricao());
            stmt.setString(4, Encomenda.getStatus());
            stmt.setString(5, Encomenda.getData());
            stmt.setString(6, Encomenda.getPrevisao());
            stmt.setInt(7, Encomenda.getQuantidade());
            stmt.setInt(8, Encomenda.getValor());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "ENCOMENDA SALVO NO BANCO DE DADOS!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR ENCOMENDA NO BANCO DE DADOS!");
            throw new RuntimeException("ERRO AO INSERIR NA TABELA ENCOMENDA:", ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<EncomendaBEAN> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<EncomendaBEAN> produtos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM encomendas");
            rs = stmt.executeQuery();
            while (rs.next()) {
                EncomendaBEAN Encomenda = new EncomendaBEAN(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
               
                produtos.add(Encomenda);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO AO BUSCAR ENCOMENDAS: ", ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

    public EncomendaBEAN get(int codigo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<EncomendaBEAN> produtos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM encomendas WHERE codigo LIKE ?");
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                EncomendaBEAN Encomenda = new EncomendaBEAN(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
        
                produtos.add(Encomenda);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO AO BUSCAR UMA ÚNICA ENCOMENDA: ", ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtos.get(0);

    }

    public List<EncomendaBEAN> getCodigo(String codigo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<EncomendaBEAN> produtos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM Produto WHERE codigo LIKE ?");
            stmt.setString(1, "%" + codigo + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                EncomendaBEAN Encomenda = new EncomendaBEAN(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                
                produtos.add(Encomenda);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO AO BUSCAR UM PRODUTO PELA DESCRIÇÃO: ", ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtos;

    }

    public void update(EncomendaBEAN Encomenda) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE encomendas SET codigo = ?, produto = ?, descricao = ?, status = ? , data = ?, previsao = ?, quantidade = ? ,valor = ? WHERE codigo LIKE ?");
            stmt.setString(1, Encomenda.getCodigo());
            stmt.setString(2, Encomenda.getProduto());
            stmt.setString(3, Encomenda.getDescricao());
            stmt.setString(4, Encomenda.getStatus());
            stmt.setString(5, Encomenda.getData());
            stmt.setString(6, Encomenda.getPrevisao());
            stmt.setInt(7, Encomenda.getQuantidade());
            stmt.setInt(8, Encomenda.getValor());
            stmt.setString(9, "%" +Encomenda.getCodigo()+ "");

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "ENCOMENDA EDITADO NO BANCO DE DADOS!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO EDITAR ENCOMENDA NO BANCO DE DADOS!");
            throw new RuntimeException("ERRO AO EDITAR NA TABELA ENCOMENDAS: ", ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void delete(String codigo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE  FROM encomendas WHERE codigo LIKE ?");
            stmt.setString(1, codigo);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "PRODUTO EXCLUIDO DO BANCO DE DADOS");
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR PRODUTO");
            throw new RuntimeException("ERRO EXCLUIR PRODUTO: ", ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
}
