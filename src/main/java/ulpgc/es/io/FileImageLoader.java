package ulpgc.es.io;

import ulpgc.es.model.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Set;

public class FileImageLoader implements ImageLoader {
    private final File[] files;

    public FileImageLoader(File folder) {
        this.files = folder.listFiles(isImage());
    }

    private static final Set<String> imageExtensions = Set.of(".jpg", ".png");
    private static FilenameFilter isImage() {
        return (dir, name) -> imageExtensions.stream().anyMatch(name::endsWith);
    }

    @Override
    public Image load() {
        return imageAt(0);
    }

    private byte[] load(String name) {
        try {
            BufferedImage image = ImageIO.read(new File(name));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] byteArray = baos.toByteArray();
            baos.close();
            return byteArray;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String name() {
                return files[i].getAbsolutePath();
            }

            @Override
            public Image next() {
                return imageAt((i+1) % files.length);
            }

            @Override
            public Image prev() {
                return imageAt((i-1+ files.length) % files.length);
            }
            @Override
            public byte[] content(){
                return load(files[i].getAbsolutePath());
            }
        };
    }
}
