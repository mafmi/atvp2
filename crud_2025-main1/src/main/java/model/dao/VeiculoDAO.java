/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Veiculo;
import model.bean.Cliente;

/**
 *
 * @author Aluno
 */
public class VeiculoDAO {
     public void create(Veiculo v) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "INSERT INTO produto(descricao, valor_unitario, quantidade, id_tipo_produto) VALUES (?,?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, v.getId());
            stmt.setString(2, v.getPlaca());
            stmt.setString(3, v.getMarca());
            stmt.setInt(4, v.getAno());
            // Definição do valor do id de Produto (fk)
            stmt.setInt(5, v.getCliente().getId());
            
            // Executar a query
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Veiculo cadastrado com sucesso!");            
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar Veiculo. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
     
       public ArrayList<Veiculo> read() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Veiculo> listaVeiculos = new ArrayList();
        
        try {
            String query = "SELECT * FROM veiculo";
            stmt = con.prepareStatement(query);
            
            rs = stmt.executeQuery();
            
            while(rs.next()) {                
                Veiculo v = new Veiculo();
                v.setId(rs.getInt("id_veiculo"));
                v.setPlaca(rs.getString("placa"));
                v.setMarca(rs.getString("marca"));
                v.setAno(rs.getInt("ano"));
                // Associação com o Cliente
                Cliente cli = new ClienteDAO().read(rs.getInt("id_cliente"));
                v.setCliente(cli);
                
                listaVeiculos.add(v);
            }
            
            return listaVeiculos;
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar Veiculo. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return null;
    }
       
    public Veiculo read(int id) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT * FROM Veiculo WHERE id_veiculo = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                Veiculo v = new Veiculo();
                v.setId(rs.getInt("id_veiculo"));
                v.setPlaca(rs.getString("placa"));
                v.setMarca(rs.getString("marca"));
                v.setAno(rs.getInt("ano"));
                // Associação com o Cliente
                Cliente cli = new ClienteDAO().read(rs.getInt("id_cliente"));
                v.setCliente(cli);
                
                return v;
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Veiculo. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return null;
    }
    //UPDATE
    public void update(Veiculo v) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "UPDATE veiculo "
                    + "SET placa = ?, marca = ?, ano = ?, id_cliente = ? "
                    + "WHERE id_veiculo = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, v.getPlaca());
            stmt.setString(2, v.getMarca());
            stmt.setInt(3, v.getAno());
            // Definição do valor do id de Produto (fk)
            stmt.setInt(4, v.getCliente().getId());
            
            stmt.setInt(5, v.getId());
            
            // Executar a query
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "veiculo editado com sucesso!");            
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao editar Veiculo. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    //destroy
    public void destroy(Veiculo v) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "DELETE FROM veiculo WHERE id_veiculo = ?";
            stmt = con.prepareStatement(query);            
            stmt.setInt(1, v.getId());
            
            // Executar a query
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Veiculo excluído com sucesso!");            
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir Veiculo. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}
