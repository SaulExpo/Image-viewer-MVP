package ulpgc.es.view;



import java.util.List;

public interface ImageDisplay {
    int Width();
    void paint(int offset, byte[] image);
    record PaintOrder(int offset, byte[] image){}
    void execute(List<PaintOrder> paintOrders);
    void clear();
    interface Dragging{
        Dragging Null = value -> {};
        void offset(int offset);
    }
    interface Released{
        Released Null = value -> {};
        void offset(int offset);
    }
    void on(Released released);
    void on(Dragging dragging);
}
