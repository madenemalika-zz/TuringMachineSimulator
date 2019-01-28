package turingmachine1;


public class Transitions {
    private String s1;  //current state
    private String s2;  // next state
    private String read;     //character to read
    private String write;    //character to write
    private int move;      // move left or right
    
    public final static int RIGHT=0;  //To move Right 
    public final static int LEFT=1;   //To move LEFT

    public Transitions(String s1, String s2, String read, String write, int move) {
        this.s1 = s1;
        this.s2 = s2;
        this.read = read;
        this.write = write;
        this.move = move;
    }
    
    

    public int getMove() {
        return move;
    }
    
    
    public String getS1() {
        return s1;
    }

    public String getS2() {
        return s2;
    }

    public String getRead() {
        return read;
    }

    public String getWrite() {
        return write;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public void setWrite(String write) {
        this.write = write;
    }

    public void setMove(int move) {
        this.move = move;
    }

    
    
    public String getMoveString() {
        if(move==LEFT){
            return "LEFT";
        }else{
            return "RIGHT";
        }
    }
    @Override
	public String toString() {
		return 
           s1 + "," + read + "->" + s2 + "," + write + "," + getMoveString();
	}
    
    
   
}
