package ulpgc.es.view;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Dragging dragging = Dragging.Null;
    private Released released = Released.Null;
    private int initShift;
    private List<PaintOrder> paintOrders = new ArrayList<>();
    private BufferedImage bitmap;

    public SwingImageDisplay() {
        this.addMouseListener(mouseListener());
        this.addMouseMotionListener(mouseMotionListener());
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                initShift = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                released.offset(e.getX() - initShift);
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) { }
        };
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                dragging.offset(e.getX() - initShift);
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        };
    }
    @Override
    public void paint(int offset, byte[] image) {
        paintOrders.add(new PaintOrder(offset, image));
        repaint();
    }
    @Override
    public void clear() {
        paintOrders.clear();
    }

    @Override
    public void paint(Graphics g) {
        for (PaintOrder paintOrder : paintOrders) {
            this.bitmap = load(paintOrder.image());
            g.drawImage(bitmap, paintOrder.offset(), 0, 600, 400, this);
        }
    }

    private ViewPort calculateViewPort() {
        return ViewPort.ofSize(this.getWidth(), this.getHeight())
                .fit(bitmap.getWidth(), bitmap.getHeight());
    }

    @Override
    public void execute(List<PaintOrder> paintOrders) {
        for (PaintOrder paintOrder : paintOrders) {
            this.paintOrders.add(new PaintOrder(paintOrder.offset(), paintOrder.image()));
        }
        repaint();
    }

    @Override
    public void on(Dragging dragging) {
        this.dragging = dragging != null ? dragging : Dragging.Null;
    }

    @Override
    public void on(Released released) {
        this.released = released != null ? released : Released.Null;
    }

    @Override
    public int Width() {
        return super.getWidth();
    }

    private BufferedImage load(byte[] image) {
        try {
            ByteArrayInputStream byteArray = new ByteArrayInputStream(image);
            return ImageIO.read(byteArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
