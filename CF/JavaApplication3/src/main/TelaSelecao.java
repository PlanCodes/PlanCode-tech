package main;

import agenda.view.TelaAgenda;
import calculadora.JFCalc;
import calculadora.corescalc;

import javax.swing.*;
import java.awt.*;

public class TelaSelecao extends JFrame {

    private JButton btnCalculadora, btnAgenda;

    public TelaSelecao() {
        setIconImage(new ImageIcon("src/img/LOGOCIMA.png").getImage());
        setTitle("PlanCode - Menu Principal");
        setSize(360, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(corescalc.FUNDO_CALC);

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

        JLabel frase = new JLabel("Bem-vindo ao nosso projeto de POO", SwingConstants.CENTER);
        frase.setFont(new Font("Arial", Font.PLAIN, 14));
        frase.setForeground(Color.WHITE);
        frase.setBounds(30, 90, 300, 25);
        add(frase);

        btnCalculadora = corescalc.criarBotao("Calculadora");
        btnCalculadora.setBounds(80, 140, 200, 35);
        add(btnCalculadora);

        btnAgenda = corescalc.criarBotao("Agenda de Contatos");
        btnAgenda.setBounds(80, 200, 200, 35);
        add(btnAgenda);

        btnCalculadora.addActionListener(e -> abrirCalculadora());
        btnAgenda.addActionListener(e -> abrirAgenda());
    }

    private void abrirCalculadora() {
        new JFCalc().setVisible(true);
        dispose();
    }

    private void abrirAgenda() {
        new TelaAgenda().setVisible(true);
        dispose();
    }
}