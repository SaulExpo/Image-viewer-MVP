package ulpgc.es;

import ulpgc.es.control.NextCommand;
import ulpgc.es.control.PreviousCommand;
import ulpgc.es.io.FileImageLoader;
import ulpgc.es.model.Image;
import ulpgc.es.presenter.ImagePresenter;
import ulpgc.es.view.MainFrame;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        Image image = new FileImageLoader(new File("./")).load();
        ImagePresenter presenter = new ImagePresenter(mainFrame.imageDisplay());
        presenter.show(image);
        mainFrame.add("<", new PreviousCommand(presenter))
                .add(">", new NextCommand(presenter));
        mainFrame.setVisible(true);
    }}
