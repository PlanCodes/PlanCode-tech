package agenda.view;

import agenda.dao.ContatoDAO;
import agenda.model.Contato;
import calculadora.corescalc;
import main.TelaSelecao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TelaAgenda extends JFrame {

    private JTable tabela;
    private JTextField txtNome, txtTelefone, txtEmail, txtID;
    private JButton btnAdicionar, btnAtualizar, btnExcluir, btnListar;

    public TelaAgenda() {

        setTitle("Agenda");
        setSize(750, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(corescalc.FUNDO_CALC);
        setIconImage(new ImageIcon("src/img/LOGOCIMA.png").getImage());

        txtID = new JTextField();
        txtID.setBounds(0, 0, 0, 0);
        add(txtID);

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(null);
        painelCampos.setBounds(20, 20, 700, 80);
        painelCampos.setBackground(corescalc.FUNDO_CALC);
        add(painelCampos);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 10, 60, 25);
        lblNome.setForeground(corescalc.TEXTO_BOTAO);
        painelCampos.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(70, 10, 200, 25);
        painelCampos.add(txtNome);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(280, 10, 70, 25);
        lblTelefone.setForeground(corescalc.TEXTO_BOTAO);
        painelCampos.add(lblTelefone);

        txtTelefone = new JTextField();
        txtTelefone.setBounds(350, 10, 150, 25);
        painelCampos.add(txtTelefone);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(510, 10, 60, 25);
        lblEmail.setForeground(corescalc.TEXTO_BOTAO);
        painelCampos.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(560, 10, 130, 25);
        painelCampos.add(txtEmail);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(null);
        painelBotoes.setBounds(20, 110, 700, 40);
        painelBotoes.setBackground(corescalc.FUNDO_CALC);
        add(painelBotoes);

        btnAdicionar = corescalc.criarBotao("Adicionar");
        btnAdicionar.setBounds(10, 5, 120, 30);
        painelBotoes.add(btnAdicionar);

        btnAtualizar = corescalc.criarBotao("Atualizar");
        btnAtualizar.setBounds(140, 5, 120, 30);
        painelBotoes.add(btnAtualizar);

        btnExcluir = corescalc.criarBotao("Excluir");
        btnExcluir.setBounds(270, 5, 120, 30);
        painelBotoes.add(btnExcluir);

        btnListar = corescalc.criarBotao("Listar");
        btnListar.setBounds(400, 5, 120, 30);
        painelBotoes.add(btnListar);

        Color corVoltar = new Color(
                Math.min(corescalc.FUNDO_CALC.getRed() + 25, 255),
                Math.min(corescalc.FUNDO_CALC.getGreen() + 25, 255),
                Math.min(corescalc.FUNDO_CALC.getBlue() + 25, 255)
        );

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(540, 5, 120, 30);
        btnVoltar.setBackground(corVoltar);
        btnVoltar.setForeground(corescalc.TEXTO_BOTAO);
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
        btnVoltar.setBorder(BorderFactory.createLineBorder(corescalc.TEXTO_BOTAO));
        painelBotoes.add(btnVoltar);

        btnAdicionar.addActionListener(e -> adicionar());
        btnAtualizar.addActionListener(e -> atualizar());
        btnExcluir.addActionListener(e -> excluir());
        btnListar.addActionListener(e -> listar());

        btnVoltar.addActionListener(e -> {
            dispose();
            new TelaSelecao().setVisible(true);
        });

        tabela = new JTable();
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 160, 700, 230);
        add(scroll);

        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabela.getSelectedRow();
                if (linha >= 0) {
                    txtID.setText(tabela.getValueAt(linha, 0).toString());
                    txtNome.setText(tabela.getValueAt(linha, 1).toString());
                    txtTelefone.setText(tabela.getValueAt(linha, 2).toString());
                    txtEmail.setText(tabela.getValueAt(linha, 3).toString());
                }
            }
        });

        listar();
    }

    private void adicionar() {
        if (txtNome.getText().isEmpty() || txtTelefone.getText().isEmpty() || txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        Contato c = new Contato();
        c.setNome(txtNome.getText());
        c.setTelefone(txtTelefone.getText());
        c.setEmail(txtEmail.getText());

        new ContatoDAO().inserir(c);

        listar();
        limpar();
    }

    private void atualizar() {
        if (txtID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um contato.");
            return;
        }

        Contato c = new Contato();
        c.setId(Integer.parseInt(txtID.getText()));
        c.setNome(txtNome.getText());
        c.setTelefone(txtTelefone.getText());
        c.setEmail(txtEmail.getText());

        new ContatoDAO().atualizar(c);

        listar();
        limpar();
    }

    private void excluir() {
        if (txtID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um contato.");
            return;
        }

        int id = Integer.parseInt(txtID.getText());
        new ContatoDAO().excluir(id);

        listar();
        limpar();
    }

    private void listar() {
        List<Contato> contatos = new ContatoDAO().listar();

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"ID", "Nome", "Telefone", "Email"});

        for (Contato c : contatos) {
            modelo.addRow(new Object[]{c.getId(), c.getNome(), c.getTelefone(), c.getEmail()});
        }

        tabela.setModel(modelo);
    }

    private void limpar() {
        txtID.setText("");
        txtNome.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
    }
}