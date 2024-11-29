package ulpgc.es;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        Image image = new FileImageLoader(new File("./")).load();
        mainFrame.imageDisplay().show(image);
        mainFrame.add("<", new PreviousCommand(mainFrame.imageDisplay()))
                .add(">", new NextCommand(mainFrame.imageDisplay()));
        mainFrame.setVisible(true);
    }}
