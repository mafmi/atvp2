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
import model.bean.Peca;

/**
 *
 * @author Aluno
 */
public class PecaDAO {
     public void create(Peca p){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try{
            String query = "INSERT INTO cliente(nome) VALUES (?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTipo());
            
            
            stmt.executeUpdate();
           
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        }
            catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "Falha ao cadastrar Cliente.Erro: " + ex.getMessage());
                    }
            finally{
                    Conexao.fecharConexao(con, stmt);
            }
        
    }
     public ArrayList<Peca> read(){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Peca> ListaPeca = new ArrayList();
        
        try{
            String query = "SELECT * FROM peca";
            stmt = con.prepareStatement(query);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
            Peca p = new Peca();
            p.setId(rs.getInt("id_peca"));
            p.setNome(rs.getString("nome"));
            p.setTipo(rs.getString("tipo"));
            
            ListaPeca.add(p);
            }
            
        }
        catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "Falha ao consultar tipos de Peça.Erro: " + ex.getMessage());
                    }
        finally{
                Conexao.fecharConexao(con, stmt, rs);
        }
        return ListaPeca;
    }
     
       //READ
       public Peca read(int id) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT * FROM peca WHERE id_peca = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                 Peca p = new Peca();
                p.setId(rs.getInt("id_peca"));
                p.setNome(rs.getString("nome"));
                p.setTipo(rs.getString("tipo"));
               
                
                return p;
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Peca. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return null;
    }
    //UPDATE
       public void update(Peca p) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "UPDATE peca "
                    + "SET nome = ?, tipo = ? "
                    + "WHERE id_peca = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTipo());
            
            stmt.setInt(3, p.getId());
            
            // Executar a query
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Peca editado com sucesso!");            
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao editar Peca. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    //DESTROY
     public void destroy(Peca p) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "DELETE FROM peca WHERE id_peca = ?";
            stmt = con.prepareStatement(query);            
            stmt.setInt(1, p.getId());
            
            // Executar a query
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Peca excluídoa com sucesso!");            
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir Peca. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}
