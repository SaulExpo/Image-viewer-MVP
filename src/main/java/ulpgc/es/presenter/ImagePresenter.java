package ulpgc.es.presenter;

import ulpgc.es.model.Image;
import ulpgc.es.view.ImageDisplay;

import java.util.Arrays;

public class ImagePresenter {
    private Image image;
    private final ImageDisplay display;

    public ImagePresenter(ImageDisplay imageDisplay) {
        this.display = imageDisplay;
        this.display.on((ImageDisplay.Dragging) this::dragging);
        this.display.on((ImageDisplay.Released) this::released);
    }

    public void show(Image image){
        this.image = image;
        repaint();
    }

    private ImageDisplay.PaintOrder currentImage() {
        return new ImageDisplay.PaintOrder(0, image().content());
    }

    private void paint(ImageDisplay.PaintOrder... paintOrders){
        display.execute(Arrays.stream(paintOrders).toList());
    }
    private void dragging(int offset) {
        display.clear();
        display.paint(offset, image.content());
        if (offset > 0)
            display.paint(offset - display.Width(), image.prev().content());
        else
            display.paint(display.Width() + offset, image.next().content());
    }

    private void released(int offset) {
        if (Math.abs(offset) >= display.Width() / 2)
            image = offset > 0 ? image.prev() : image.next();
        repaint();
    }

    public Image image() {
        return image;
    }

    private void repaint() {
        this.display.clear();
        this.display.paint(0, image.content());
    }

}
