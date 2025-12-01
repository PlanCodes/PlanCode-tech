package calculadora;

import java.awt.*;
import javax.swing.*;

public class corescalc {

    // Cores principais
    public static final Color FUNDO_CALC = Color.decode("#10002B");
    public static final Color COR1_BOTAO = Color.decode("#00FFF9");
    public static final Color COR2_BOTAO = Color.decode("#9600FF");
    public static final Color TEXTO_BOTAO = Color.WHITE;

    // Método para criar botão com degradê
    public static JButton criarBotao(String texto) {
        JButton botao = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                GradientPaint gradiente = new GradientPaint(
                        0, 0, COR1_BOTAO,
                        getWidth(), getHeight(), COR2_BOTAO);
                g2.setPaint(gradiente);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        botao.setOpaque(false);
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.setForeground(TEXTO_BOTAO);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        return botao;
    }
    
}
