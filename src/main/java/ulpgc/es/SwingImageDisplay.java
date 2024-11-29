package ulpgc.es;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Image image;
    private BufferedImage bitmap;

    @Override
    public void show(Image image) {
        this.image = image;
        this.bitmap = load(image.name());
        this.repaint();
    }

    @Override
    public Image image() {
        return image;
    }

    @Override
    public void paint(Graphics g) {
        clean(g);
        g.drawImage(bitmap, calculateViewPort().x(), calculateViewPort().y(), calculateViewPort().width(), calculateViewPort().height(), this);
    }

    private ViewPort calculateViewPort() {
        return ViewPort.ofSize(this.getWidth(), this.getHeight())
                .fit(bitmap.getWidth(), bitmap.getHeight());
    }

    private void clean(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private BufferedImage load(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
