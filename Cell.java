public class Cell {
    
    public int state = 0;
    public int closeCells = 0;

    /* Sets its state acording to the number of its neighbours */
    public void update() {

        if(this.closeCells == 3) this.state = 1;
        else if(this.closeCells != 2) this.state = 0;
    }
}