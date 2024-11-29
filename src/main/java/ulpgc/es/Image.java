package ulpgc.es;

public interface Image {
    String name();
    Image next();
    Image prev();
}
