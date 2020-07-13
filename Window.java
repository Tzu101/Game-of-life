import java.awt.Color;
import java.awt.Graphics;

import javax.swing.Timer;
import javax.swing.JPanel;


public class Window extends JPanel {

    private static final long serialVersionUID = 1L;  // just to hide the annoying warning

    private Cell[][] map;
    private int cellSize;
    private int sizeX, sizeY;

    private Timer mainLoopTimer;
    private int renderDelay = 20;

    private Boolean paused = false;
    private int claculateCounter = 0;
    private int calculateSlowedBy = 10;
    

    public Window(int sizeX, int sizeY, int cellSize) {

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addMouseListener(new GetMousePress(this));
        addMouseWheelListener(new GetMouseScroll(this));

        this.mainLoopTimer = new Timer(this.renderDelay, new GetAction(this));
        this.mainLoopTimer.start();

        this.cellSize = cellSize;
        this.sizeX = sizeX / this.cellSize * this.cellSize;
        this.sizeY = sizeY / this.cellSize * this.cellSize;

        this.map = new Cell[sizeY/this.cellSize][sizeX/this.cellSize];
        for(int y = 0; y < this.sizeY / this.cellSize; y++) {
            for(int x = 0; x < this.sizeX / this.cellSize; x++) {

                this.map[y][x] = new Cell();
            }
        }
    }

    /* Main function that handles everything at a certain speed */
    public void mainLoop() {

        /* Handles pause and lowers the amount of times clees are recalculated */
        if(!this.paused) {
            if(this.claculateCounter < this.calculateSlowedBy) this.claculateCounter++;
            else {
                this.claculateCounter = 0;
                recalculateMap();
            }
        }
        repaint();
    }

    /* Displays cell states and border on screen */
    public void paint(Graphics g) {

        /* Draws the cell states */
        for(int y = 0; y < this.sizeY/this.cellSize; y++) {
            for(int x = 0; x < this.sizeX/this.cellSize; x++) {
                
                if(this.map[y][x].state == 0) g.setColor(Color.BLACK);
                else g.setColor(Color.WHITE);

                g.fillRect(y*this.cellSize, x*this.cellSize, this.cellSize, this.cellSize);
            }
        }

        /* Draws the border */
        if(this.paused) g.setColor(Color.RED);
        else g.setColor(Color.GREEN);
        g.drawRect(0, 0, sizeY-1, sizeX-1);

        g.dispose();
    }

    /* Calculates the next generation in the game of life */
    public void recalculateMap() {

        /* Finds the number of neighbouring cells for all cells */
        for(int y = 0; y < this.sizeY/this.cellSize; y++) {
            for(int x = 0; x < this.sizeX/this.cellSize; x++) {

                /* Determines the possible loaction of neighbouring cells */
                int left = x-1, right = x+1, up = y-1, down = y+1;
                
                if(x == 0) left = this.sizeX / this.cellSize - 1;
                if(x == this.sizeX / this.cellSize - 1) right = 0;
                if(y == 0) up = this.sizeY / this.cellSize - 1;
                if(y == this.sizeY / this.cellSize - 1) down = 0;

                /* Checks all combinations for living cells */
                int closeCells = 0;
                int[] arrayX = {left, right, x};
                int[] arrayY = {up, down, y};

                for(int j = 0; j < 3; j++) {
                    for(int i = 0; i < 3; i++) {

                        if(this.map[arrayY[j]][arrayX[i]].state == 1) closeCells++;
                    }
                }
                if(this.map[y][x].state == 1) closeCells--;

                this.map[y][x].closeCells = closeCells;
            }
        }

        /* Updates the cells state */
        for(int y = 0; y < this.sizeY/this.cellSize; y++) {
            for(int x = 0; x < this.sizeX/this.cellSize; x++) {

                this.map[y][x].update();
            }
        }
    }

    /* Changes the state of a specefied cell */
    public void setCellState(int x, int y, int state) {
        this.map[x / this.cellSize][y / this.cellSize].state = state;
    }

    /* Swiches / sets the pause mode */
    public void pause(Boolean mode) {
        if(mode == null) this.paused = !this.paused;
        else this.paused = mode;
    }

    /* Changes the number of calls to calculate function */
    public void changeCalculateSpeed(int change) {
        if(change > 0 && this.calculateSlowedBy < 20) this.calculateSlowedBy += change;
        else if(change < 0 && this.calculateSlowedBy > 1) this.calculateSlowedBy += change;
    }
}