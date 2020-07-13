import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


/* Informs the main program when the mouse wheel is moved */
public class GetMouseScroll implements MouseWheelListener {

    Window window;

    public GetMouseScroll(Window window) {
        this.window = window;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        this.window.changeCalculateSpeed(e.getWheelRotation());;
    }
    
}
