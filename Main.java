import java.awt.Dimension;
import javax.swing.JFrame;


public class Main {

    /* Constants incase of invalid user input */
    private static final int DEAFOULT_WINDOW_SIZE = 500;
    private static final int DEAFOULT_CELL_SIZE = 20;

    public static void main(String[] args) {

        JFrame window = new JFrame("Game of life");

        /* Defining size with user parameters or if neceseary deafoult ones */
        int windowWidth, windowHeight, cellSize;

        try {
            windowWidth = Integer.parseInt(args[0]);
            windowHeight = Integer.parseInt(args[1]);
            cellSize = Integer.parseInt(args[2]);
        }
        catch(Exception e) {
            windowWidth = DEAFOULT_WINDOW_SIZE;
            windowHeight = DEAFOULT_WINDOW_SIZE;
            cellSize = DEAFOULT_CELL_SIZE;
        }

        /* Instructions */
        System.out.println("To make a cell live left click on it and to kill it right click on it. To pause press the scrool wheel.");

        /* Window setup */
        window.getContentPane().setPreferredSize(new Dimension(windowHeight, windowWidth));
        window.pack();
        window.setResizable(false);
            
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        window.add(new Window(windowWidth, windowHeight, cellSize));
        window.setVisible(true);
    }
}
