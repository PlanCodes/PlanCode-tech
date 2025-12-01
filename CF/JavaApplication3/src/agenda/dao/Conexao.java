/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.dao;
import java.sql.Connection;
import java.sql.DriverManager;  
import java.sql.SQLException;
/**
 *
 * @author gbrlf
 */
public class Conexao {
    public static Connection conectar() {
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/agenda";
            String user = "root"; 
            String pass = "root"; 

            return DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Connection conn = conectar();
        if (conn != null) {
            System.out.println("Conexão realizada com sucesso!");
        } else {
            System.out.println("Falha na conexão.");
        }
    }
}
