package animationeditor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class BoneImage extends JPanel {

    Image image;
    public int x = 100;
    public int y = 100;
    public int direction = 0;

    public BoneImage(String path) {
        image = Toolkit.getDefaultToolkit().getImage(path);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(x, y);
        g2d.rotate(Math.toRadians(direction));
        g2d.drawImage(image, 0, 0, 32, 128, this);
    }
}
