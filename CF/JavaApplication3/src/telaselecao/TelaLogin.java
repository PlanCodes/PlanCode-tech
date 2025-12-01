package telaselecao;

import javax.swing.*;
import java.awt.*;
import main.TelaSelecao;

public class TelaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar;

    public TelaLogin() {
        setIconImage(new ImageIcon("src/img/LOGOCIMA.png").getImage());
        setTitle("Login - PlanCode");
        setSize(360, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(16, 0, 43));

        String caminhoLogo = "/img/LOGOCIMA.png";
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(caminhoLogo));
            Image img = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);

            JLabel logo = new JLabel(icon, SwingConstants.CENTER);
            logo.setBounds(150, 25, 60, 60);
            add(logo);
        } catch (Exception e) {
            JLabel logoTxt = new JLabel("PLAN⚙️CODE", SwingConstants.CENTER);
            logoTxt.setFont(new Font("Arial", Font.BOLD, 26));
            logoTxt.setForeground(Color.WHITE);
            logoTxt.setBounds(40, 30, 280, 40);
            add(logoTxt);
        }

        JLabel frase = new JLabel("Digite seu usuário e senha", SwingConstants.CENTER);
        frase.setFont(new Font("Arial", Font.PLAIN, 14));
        frase.setForeground(Color.WHITE);
        frase.setBounds(30, 100, 300, 25);
        add(frase);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setBounds(50, 140, 80, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(130, 140, 150, 25);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setBounds(50, 180, 80, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(130, 180, 150, 25);
        add(txtSenha);

        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(130, 230, 150, 35);
        btnEntrar.setBackground(new Color(0, 255, 249));
        btnEntrar.setForeground(Color.BLACK);
        add(btnEntrar);

        btnEntrar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String senha = new String(txtSenha.getPassword());

            if (usuario.equals("POO") && senha.equals("poo123")) {
                TelaSelecao menu = new TelaSelecao();
                menu.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Usuário ou senha incorretos!",
                        "Erro de Login",
                        JOptionPane.ERROR_MESSAGE);
                txtSenha.setText("");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaSelecao tela = new TelaSelecao();
            tela.setVisible(true);
        });
    }
}