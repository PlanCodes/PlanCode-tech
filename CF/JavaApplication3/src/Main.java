import telaselecao.TelaLogin; 
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        Image icon = new ImageIcon("src/img/LOGOCIMA.png").getImage();
        UIManager.put("Application.iconImage", icon);

        SwingUtilities.invokeLater(() -> {
            TelaLogin telaSelecao = new TelaLogin(); 
            telaSelecao.setIconImage(icon);
            telaSelecao.setVisible(true);
        });
    }
}
