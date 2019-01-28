package turingmachine1;

import java.util.ArrayList;


public class TuringMachine {
    private ArrayList<String> states;  //finit not empty set of states 
    private ArrayList<String> inputSymbols; 
    private ArrayList<String> tapeSymbols; //
    private String b; //blank symbol
    private ArrayList<String> tape;
    private ArrayList<String> F; //FinalStates
    private String q0; //intialState
    private int startingHeadPosition;  //start position of reading the input
    private ArrayList<Transitions> transitions;  //tansition functions
    private int inputsize;

    public TuringMachine(ArrayList<String> states, ArrayList<String> inputSymbols,
            ArrayList<String> tapeSymbols, String b, ArrayList<String> tape, ArrayList<String> F,
            String q0, int startingHeadPosition, ArrayList<Transitions> transitions, int inputsize) {
        this.states = states;
        this.inputSymbols = inputSymbols;
        this.tapeSymbols = tapeSymbols;
        this.b = b;
        this.tape = tape;
        this.F = F;
        this.q0 = q0;
        this.startingHeadPosition = startingHeadPosition;
        this.transitions = transitions;
        this.inputsize=inputsize;
    }

    
    public ArrayList<String> getStates() {
        return states;
    }

    public ArrayList<String> getInputSymbols() {
        return inputSymbols;
    }

    public ArrayList<String> getTapeSymbols() {
        return tapeSymbols;
    }

    public String getB() {
        return b;
    }

    public ArrayList<String> getTape() {
        return tape;
    }

    public ArrayList<String> getF() {
        return F;
    }

    public String getQ0() {
        return q0;
    }

    public int getStartingHeadPosition() {
        return startingHeadPosition;
    }

    public ArrayList<Transitions> getTransitions() {
        return transitions;
    }

    public void setStates(ArrayList<String> states) {
        this.states = states;
    }

    public void setInputSymbols(ArrayList<String> inputSymbols) {
        this.inputSymbols = inputSymbols;
    }

    public void setTapeSymbols(ArrayList<String> tapeSymbols) {
        this.tapeSymbols = tapeSymbols;
    }

    public void setB(String b) {
        this.b = b;
    }

    public void setTape(ArrayList<String> tape) {
        this.tape = tape;
    }

    public void setF(ArrayList<String> F) {
        this.F = F;
    }

    public void setQ0(String q0) {
        this.q0 = q0;
    }

    public void setStartingHeadPosition(int startingHeadPosition) {
        this.startingHeadPosition = startingHeadPosition;
    }

    public void setTransitions(ArrayList<Transitions> transitions) {
        this.transitions = transitions;
    }

    public int getInputsize() {
        return inputsize;
    }

    public void setInputsize(int inputsize) {
        this.inputsize = inputsize;
    }
    
    

   
    
    
    


   
}

