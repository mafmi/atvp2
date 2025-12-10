/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.ServicoPeca;
import model.bean.Servico;

/**
 *
 * @author Aluno
 */
public class ServicoPecaDAO {
     public void create(ServicoPeca sc) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "INSERT INTO produto(id_peca, id_servico) VALUES (?,?)";
            stmt = con.prepareStatement(query);
            // Definição do valor do id de veiculo (fk)
            stmt.setInt(1, sc.getPeca().getId());
            stmt.setInt(2, sc.getServico().getId());
            
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
}
