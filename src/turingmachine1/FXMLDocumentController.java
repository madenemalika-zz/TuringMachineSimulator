/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turingmachine1;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

/**
 *
 * @author kika
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private JFXTextField statesID,startStateID,inputSymbolsID,tapeSymbolsID,
            TcurrentStateID,TcurrentTapeSymbID,TnewStateID,TnewTapeSymb,Tmove,
            inputID,finalStatesID,emptySymbolID,startPosID,maxInputSizeID;
    @FXML
    private Label acceptedID,NotacceptedID,outPutLabelID;
    @FXML
    private JFXButton OutputID,addTbtnId,
            addStateBtnID, addInputSymbBtnID,addFinalStatesBtnID, addTypeSymbBtnID;;
    @FXML
    private ImageView turingImgID;
    @FXML
    private AnchorPane rootPane;
    JFXSnackbar snackbar;
    
    
        private TuringMachine tm;
        private ArrayList<String> states=new ArrayList<String>();  //finit not empty set of states 
	private ArrayList<String> inputSymbols=new ArrayList<String>();
	private ArrayList<String> tapeSymbols=new ArrayList<String>();
	private String emptyCellSymbol;  //blank symbol
	private ArrayList<String> tape=new ArrayList<String>();   //input
	private ArrayList<String> finalStates=new ArrayList<String>(); 
	private String intialState; 
	private int startingHeadPosition;  //start position of reading the input
	private ArrayList<Transitions> transitions=new ArrayList<Transitions>(); //tansition functions
        private int maxInputsize;
        private String outPut;
        
        private int size,begin,end;
        
        
        private String currentState="",currentSymb="",newState="",newSymb=""; 
        private int move;
        
        

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //NotacceptedID.setStyle("-fx-background-color: #e42323");
      //acceptedID.setStyle("-fx-background-color: #75c959");
      snackbar=new JFXSnackbar(rootPane);
    } 
    @FXML
    void Clicked(MouseEvent event) { //Output Button mouse clicked event
        
        if( startStateID.getText().length()!=0 && emptySymbolID.getText().length()!=0
         && inputID.getText().length()!=0 && startPosID.getText().length()!=0 &&
                !states.isEmpty() && !finalStates.isEmpty() && !inputSymbols.isEmpty()&& !tapeSymbols.isEmpty()
                && !transitions.isEmpty() && maxInputSizeID.getText().length()!=0){
            //all fields are fill
            maxInputsize=Integer.parseInt(maxInputSizeID.getText());
            if(inputID.getText().length()==maxInputsize){
                begin=maxInputsize*4;
                long Stime=System.currentTimeMillis();
                getdata();
                end=maxInputsize+begin;
                for(int i=end;i<end+begin;i++){
                    tape.add(i, emptyCellSymbol);
                }
               System.out.println("empty s"+emptyCellSymbol+" intialState "+intialState);
               System.out.println("startingHeadPosition "+startingHeadPosition+" tape size "+tape.size());
               //Create turing machine instance from the data obtained 
                tm=new TuringMachine(states,inputSymbols,tapeSymbols,emptyCellSymbol,
            tape,finalStates,intialState,startingHeadPosition,transitions,maxInputsize); 
            
            System.out.println(" TM initial state "+tm.getQ0()+
                    " TM input size "+tm.getInputsize()+" TM input "+tm.getTape());
        
            run(tm,tm.getTape()); //runing our turing machine with the input given
            
            long Etime=System.currentTimeMillis();
            long time=Etime-Stime;
            System.out.println("*****"+time+"*****");
            for (String s:tm.getTape()){
                System.out.print(s);   
            }
            
            
            }else{
                snackbar.show("Input size must be equal to "+maxInputsize, 3000);
            //error message
            System.err.println("Input size must be equal to "+maxInputsize);
            }
            
            
        }else{
            
            snackbar.show("Please fill all the inputs ", 3000);
            //error message
            System.err.println("Please fill all Turing Machine inputs ");
            
        }
    }
    @FXML
    void addBtnClicked(MouseEvent event) { //add transaction
        if(TcurrentStateID.getText().length()!=0 && TcurrentTapeSymbID.getText().length()!=0 && 
        TnewStateID.getText().length()!=0 && TnewTapeSymb.getText().length()!=0 && Tmove.getText().length()!=0){
            //all the text field are filled
        currentState= TcurrentStateID.getText();
        currentSymb=TcurrentTapeSymbID.getText();
        newState=TnewStateID.getText();
        newSymb=TnewTapeSymb.getText();
        move= Integer.parseInt(Tmove.getText()); //parse move text field to integer
        
        System.out.println(currentState+" "+currentSymb+" "+newState+" "+newSymb+" "+move);
        // transaction details
        Transitions t =new Transitions(currentState,newState,currentSymb,newSymb,move);  //creat new transaction
       
        
        
        transitions.add(t);
        //add transaction crated to the arrayList
        System.out.println(transitions.size());
        System.out.println(transitions.get(0).getMoveString());
        //empty transaction details text fields
        TcurrentStateID.setText("");
        TcurrentTapeSymbID.setText("");
        TnewStateID.setText("");
        TnewTapeSymb.setText("");
        Tmove.setText("");
        
        }else{
            //error message
            snackbar.show("Please fill all Transaction inputs", 3000);
            System.err.println("Please fill all Transaction inputs");
        }
        

    }
    
    @FXML
    void addFinalStatesClik(MouseEvent event) {
        if(finalStatesID.getText().length()!=0){
            //final state text field is not empty
            finalStates.add(finalStatesID.getText()); //add the input to final states arrayList
            System.out.println(finalStates.size());
            finalStatesID.setText(""); //empty the text field
        }else{
            //error message
            snackbar.show("Please fill final states", 3000);
            System.err.println("Please fill final states");
        }
    }
    @FXML
    void addInputSymbClik(MouseEvent event) {
        if(inputSymbolsID.getText().length()!=0){
            //imput symbols text field is not empty
            inputSymbols.add(inputSymbolsID.getText()); //add the input to imput symbols  arrayList
            System.out.println(inputSymbols.size());
            inputSymbolsID.setText(""); //empty the text field
        }else{
            //error message
            snackbar.show("Please fill input symbols inputs", 3000);
            System.err.println("Please fill input symbols inputs");
        }
    }
    @FXML
    void addStateClik(MouseEvent event) {
        if(statesID.getText().length()!=0){
            //states text field is not empty
            states.add(statesID.getText()); //add the input to states arrayList
            System.out.println(states.size());
            statesID.setText(""); //empty the text field
            
        }else{
            //error message
            snackbar.show("Please fill states inputs", 3000);
            System.err.println("Please fill states inputs");
        }
    }
    @FXML
    void addTapeSymbClik(MouseEvent event) {
        if(tapeSymbolsID.getText().length()!=0){
            //tape symbols text field is not empty
            tapeSymbols.add(tapeSymbolsID.getText()); //add the input to tape symbols arrayList
            System.out.println(tapeSymbols.size());
            tapeSymbolsID.setText(""); //empty the text field
        }else{
            //error message
            snackbar.show("Please fill tape symbols inputs", 3000);
            System.err.println("Please fill tape symbols inputs");
        }
    }
    
    void getdata(){
        
        emptyCellSymbol= emptySymbolID.getText();
        for(int i=0;i<begin;i++){
                    tape.add(i, emptyCellSymbol);
                }
        intialState=startStateID.getText();
        for (int i = 0; i < inputID.getText().length(); ++i) {
			tape.add(Character.toString(inputID.getText().charAt(i)));
		}
        startingHeadPosition=Integer.parseInt(startPosID.getText());
        
          
    }
    
   
    public void run(TuringMachine turingMachine,ArrayList<String> tapeIn ){
	String state = turingMachine.getQ0(); //get the initial state
	int position = begin+turingMachine.getStartingHeadPosition();  //get the position
        //geting transition of the first state and first position character
        Transitions getTrans=getNextTransition(turingMachine, state, tapeIn.get(position)); 
        while(true){
                       if (getTrans == null) {
                            //in the case of there is no transiction with the state and position character
                            System.err.println("is not accepted");
                            NotacceptedID.setStyle("-fx-background-color: #e42323");
				break;
			}
		        
                        if(turingMachine.getF().contains(getTrans.getS2())){
                            //the case of the new state is the final one
                            //end of programme
                            System.err.println(getTrans.getS1()+" , "
                                    +getTrans.getRead()+" -> "+getTrans.getS2()
                                    +" , "+getTrans.getWrite()+" , "+getTrans.getMoveString());
                            System.out.println("End");
                            acceptedID.setStyle("-fx-background-color: #75c959");
                            //getting out put
                            for(String s :tapeIn){
                                System.out.print(s);
                                StringBuilder stringBuilder= new StringBuilder();
                                for(String element: turingMachine.getTape()){
                                 stringBuilder.append(element);
                                 }
                                  outPut=stringBuilder.toString();
                                  outPutLabelID.setText(outPut); //set the outPut Label by our obtained out put
                            }
                            System.out.println("");
                            break;
                        }
                        
                        
                        tapeIn.set(position, getTrans.getWrite());
                        state=getTrans.getS2();
                        if(getTrans.getMove()==0){
                            position++;
                        }else{
                            position--;
                        }
                        getTrans= getNextTransition(turingMachine,state, tapeIn.get(position));

        }
        
    }
    private Transitions getNextTransition(TuringMachine turingMachine,String state, String tapeSymbol) {
                //getting all transitions of the turing machine given
		ArrayList<Transitions> tran = turingMachine.getTransitions();
              
		for(Transitions transition : tran) {
                    //search if the is a transiction with the given state and the symbol
                    if(transition.getS1().equals(state) && transition.getRead().equals(tapeSymbol)) {
				return transition;
			}
		}
		//in the case of the is not a transiction   
		return null;
	}
    
    
}
