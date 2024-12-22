package ulpgc.es.control;

import ulpgc.es.presenter.ImagePresenter;

public class PreviousCommand implements Command {
    private final ImagePresenter imagePresenter;

    public PreviousCommand(ImagePresenter imagePresenter) {
        this.imagePresenter = imagePresenter;
    }

    @Override
    public void execute() {
        imagePresenter.show(imagePresenter.image().prev());
    }
}
