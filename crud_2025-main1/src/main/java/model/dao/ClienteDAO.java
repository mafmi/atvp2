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
import model.bean.Cliente;

/**
 *
 * @author Aluno
 */
public class ClienteDAO {
    public void create(Cliente cli){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try{
            String query = "INSERT INTO cliente(nome) VALUES (?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, cli.getCpf());
            stmt.setString(1, cli.getNome());
            stmt.setString(3, cli.getEndereco());
            stmt.setString(4, cli.getTelefone());
            
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
    public ArrayList<Cliente> read(){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Cliente> ListaCliente = new ArrayList();
        
        try{
            String query = "SELECT * FROM cliente";
            stmt = con.prepareStatement(query);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
            Cliente cli = new Cliente();
            cli.setId(rs.getInt("id_cliente"));
            cli.setCpf(rs.getString("cpf"));
            cli.setNome(rs.getString("nome"));
            cli.setEndereco(rs.getString("endereco"));
            cli.setTelefone(rs.getString("telefone"));
            
            ListaCliente.add(cli);
            }
            
        }
        catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "Falha ao consultar tipos de clientes.Erro: " + ex.getMessage());
                    }
        finally{
                Conexao.fecharConexao(con, stmt, rs);
        }
        return ListaCliente;
    }
    //tras um objt específico a partir do id
    public Cliente read(int id){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            String query = "SELECT * FROM Cliente WHERE id_cliente = ? ";
            stmt = con.prepareStatement(query);
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
            Cliente cli = new Cliente();
            cli.setId(rs.getInt("id_cliente"));
            cli.setCpf(rs.getString("cpf"));
            cli.setNome(rs.getString("nome"));
            cli.setEndereco(rs.getString("endereco"));
            cli.setTelefone(rs.getString("telefone"));
            return cli;

            }
            
        }
        catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "Falha ao buscar tipo clientes.Erro: " + ex.getMessage());
                    }
        finally{
                Conexao.fecharConexao(con, stmt, rs);
        }
        return null;
    }
    
     public void update (Cliente cli){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try{
            String query = "UPDATE cliente SET nome = ? WHERE id_cliente = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, cli.getId());
            stmt.setString(2, cli.getCpf());
            stmt.setString(3, cli.getNome());
            stmt.setString(4, cli.getEndereco());
            stmt.setString(5, cli.getTelefone());
            
            
            stmt.executeUpdate();
           
            JOptionPane.showMessageDialog(null, "Cliente ATUALIZADO  com sucesso!");
        }
            catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "Falha ao ATUALIZAR Cliente.Erro: " + ex.getMessage());
                    }
            finally{
                    Conexao.fecharConexao(con, stmt);
            }
        
    }
    //deleta
      public void destroy (Cliente cli){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try{
            String query = "DELETE FROM  cliente SET nome = ? WHERE id_cliente = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, cli.getId());
            stmt.setString(1, cli.getCpf());
            stmt.setString(1, cli.getNome());
            stmt.setString(1, cli.getEndereco());
            stmt.setString(1, cli.getTelefone());
            stmt.executeUpdate();
           
            JOptionPane.showMessageDialog(null, "Cliente DELETADO  com sucesso!");
        }
            catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "Falha ao DELETAR Cliente.Erro: " + ex.getMessage());
                    }
            finally{
                    Conexao.fecharConexao(con, stmt);
            }
        
    }
}
