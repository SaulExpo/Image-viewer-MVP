package ulpgc.es.control;

import ulpgc.es.presenter.ImagePresenter;

public class NextCommand implements Command {
    private final ImagePresenter imagePresenter;

    public NextCommand(ImagePresenter imagePresenter) {
        this.imagePresenter = imagePresenter;
    }

    @Override
    public void execute() {
        imagePresenter.show(imagePresenter.image().next());
    }
}
