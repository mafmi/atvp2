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
import model.bean.Servico;
import model.bean.Veiculo;

/**
 *
 * @author Aluno
 */
public class ServicoDAO {
    public void create(Servico s) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "INSERT INTO produto(descricao, valor_unitario, quantidade, id_tipo_produto) VALUES (?,?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, s.getId());
            stmt.setDate(2, s.getEntrada());
            stmt.setDate(3, s.getSaida());
            stmt.setString(4, s.getValor());
            // Definição do valor do id de veiculo (fk)
            stmt.setInt(5, s.getVeiculo().getId());
            
            // Executar a query
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Serviço cadastrado com sucesso!");            
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar Serviço. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    //ARRAY
     public ArrayList<Servico> read() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Servico> listaServicos = new ArrayList();
        
        try {
            String query = "SELECT * FROM Serviço";
            stmt = con.prepareStatement(query);
            
            rs = stmt.executeQuery();
            
            while(rs.next()) {                
                Servico s = new Servico();
                s.setId(rs.getInt("id_servico"));
                s.setEntrada(rs.getDate("entrada"));
                s.setSaida(rs.getDate("saida"));
                s.setValor(rs.getString("valor"));
                // Associação com o Veiculo
                Veiculo v = new VeiculoDAO().read(rs.getInt("id_veiculo"));
                s.setVeiculo(v);
                
                listaServicos.add(s);
            }
            
            return listaServicos;
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar Veiculo. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return null;
     }
    
    //READ
       public Servico read(int id) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT * FROM servico WHERE id_servico = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                Servico s = new Servico();
                s.setId(rs.getInt("id_servico"));
                s.setEntrada(rs.getDate("entrada"));
                s.setSaida(rs.getDate("saida"));
                s.setValor(rs.getString("valor"));
                // Associação com o Veiculo
                Veiculo v = new VeiculoDAO().read(rs.getInt("id_veiculo"));
                s.setVeiculo(v);
                
                
                return s;
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
       public void update(Servico s) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "UPDATE servico "
                    + "SET entrada = ?, saida = ?, valor = ?, id_veiculo = ? "
                    + "WHERE id_servico = ?";
            stmt = con.prepareStatement(query);
            stmt.setDate(1, s.getEntrada());
            stmt.setDate(2, s.getSaida());
            stmt.setString(3, s.getValor());
            
            // Definição do valor do id de Produto (fk)
            stmt.setInt(4, s.getVeiculo().getId());
            
            stmt.setInt(5, s.getId());
            
            // Executar a query
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Serviço editado com sucesso!");            
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao editar Serviço. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    //DESTROY
     public void destroy(Servico s) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "DELETE FROM Servico WHERE id_veiculo = ?";
            stmt = con.prepareStatement(query);            
            stmt.setInt(1, s.getId());
            
            // Executar a query
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Serviço excluído com sucesso!");            
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir Serviço. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}
