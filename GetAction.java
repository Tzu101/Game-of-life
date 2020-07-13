import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/* Informs the main program when an action happens */
public class GetAction implements ActionListener {

    private Window window;

    public GetAction(Window window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.mainLoop();
    }
}
