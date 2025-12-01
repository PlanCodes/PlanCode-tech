
package agenda.dao;

import agenda.model.Contato;
import java.sql.*;
import java.util.ArrayList;

public class ContatoDAO {
    public void inserir(Contato c) {
        try (Connection conn = Conexao.conectar()) {
            String sql = "INSERT INTO contatos (nome, telefone, email) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getTelefone());
            stmt.setString(3, c.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }

    public void atualizar(Contato c) {
        try (Connection conn = Conexao.conectar()) {
            String sql = "UPDATE contatos SET nome=?, telefone=?, email=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getTelefone());
            stmt.setString(3, c.getEmail());
            stmt.setInt(4, c.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        try (Connection conn = Conexao.conectar()) {
            String sql = "DELETE FROM contatos WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }
    }

    public ArrayList<Contato> listar() {
        ArrayList<Contato> lista = new ArrayList<>();
        try (Connection conn = Conexao.conectar()) {
            String sql = "SELECT * FROM contatos";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Contato c = new Contato();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return lista;
    }
}
