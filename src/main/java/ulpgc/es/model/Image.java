package ulpgc.es.model;

public interface Image {
    String name();
    Image next();
    Image prev();
    byte[] content();
}
