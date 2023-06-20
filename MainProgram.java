import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

import javax.swing.border.*;

public class MainProgram {

    

    public static void main(String[] args){
        JFrame program_frame = new JFrame(); //creating one new window where everything takes place
        JTextArea content = new JTextArea(500,500);
        program_frame.add(content);
        JTabbedPane tp = new JTabbedPane();
        tp.setBounds(0,0,700,500);  

        //panel for home page
        JPanel home_page = new JPanel();        
        BoxLayout boxlayout1 = new BoxLayout(home_page, BoxLayout.Y_AXIS);// Creating Object of "boxlayout" in Y_Axis from top to down
        home_page.setLayout(boxlayout1);
        home_page.setBorder(new EmptyBorder(new Insets(5, 120, 100, 100)));
        JLabel home_label1 = new JLabel("<html>TIC2151 Theory of Computation <br/> Tut. no. <br/> Group no.<br/> Group Members, Name, photo and ID </html>",SwingConstants.CENTER);
        JLabel home_label2 = new JLabel("content");
        JLabel home_label3 = new JLabel("Other info such as members participation");
        JLabel home_label4 = new JLabel("<html>Member 1 = 25% <br/> Member 2 = 25% <br/> Member 3 = 25% <br/> Member 4 = 25%</html>",SwingConstants.CENTER);
        home_page.add(home_label1);
        home_page.add(home_label2);
        home_page.add(home_label3);
        home_page.add(home_label4);
        
        
        
        //panel for FA to RG page
        JPanel fa_rg_page = new JPanel();
        fa_rg_page.setLayout(null);
        // Component 1: Input (must accept 1-10 variables/states, 1-3 alphabet and Epsilon)
        String[] states = {"A","A,B","A,B,C","A,B,C,D","A,B,C,D,E","A,B,C,D,E,F","A,B,C,D,E,F,G","A,B,C,D,E,F,G,H","A,B,C,D,E,F,G,H,I","A,B,C,D,E,F,G,H,I,J"};//Q
        String[] alphabets = {"0,1","0,1,2","0,1,2,3","a,b","a,b,c"}; //∑
        String[] initials= {"A","B","C","D","E","F","G","H","I","J"};
        String[] finals= {"A","B","C","D","E","F","G","H","I","J"};
   
        JLabel fa_label1 = new JLabel("Q=");
        fa_label1.setBounds(100,14, 80,30); 
        JLabel fa_label2 =new JLabel("∑=");    
        fa_label2.setBounds(100,34, 80,30);
        JLabel fa_label3 = new JLabel("Initial state=");
        fa_label3.setBounds(100,54, 97,30);
        JLabel fa_label4 = new JLabel("Final state=");
        fa_label4.setBounds(100,74, 80,30);
        JComboBox<String> states_input = new JComboBox<>(states);
        states_input.setBounds(120,20, 130,20);
        JComboBox<String> alphabets_input = new JComboBox<>(alphabets);
        alphabets_input.setBounds(120,40, 100,20);
        JComboBox<String> initial_input = new JComboBox<>(initials);
        initial_input.setBounds(180, 60, 60,20);
        JComboBox<String> final_input = new JComboBox<>(finals);
        final_input.setBounds(180, 80, 60,20);

        JButton b1 = new JButton("New");  //on click, generate a transition table based on Q and ∑, for user to fill up
        b1.setBounds(5,10, 80,25); 
        JButton b2 = new JButton("Clear");  // on click, clear program
        b2.setBounds(5,40, 80,25);
        JButton b3 = new JButton("RG");  // on click, submit input to generate rg output
        b3.setBounds(5,70, 80,25);

        //NFA Table component
        JLabel NFA_Header = new JLabel("(Input)");
        NFA_Header.setBounds(320,0, 300,25);
        fa_rg_page.add(NFA_Header);
        DefaultTableModel model = new DefaultTableModel();
        JTable NFATable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(NFATable);
        scrollPane.setBounds(320, 20, 300, 200);
        fa_rg_page.add(scrollPane);

        //RG component
        JLabel RG_Header = new JLabel("Regular Grammars(Output)");
        RG_Header.setBounds(10,200, 300,25);
        fa_rg_page.add(RG_Header);
        JTextArea RGTextArea = new JTextArea();
        RGTextArea.setEditable(false);
        JScrollPane scrollPane2 = new JScrollPane(RGTextArea);
        scrollPane2.setBounds(10, 220, 300, 200);      
        fa_rg_page.add(scrollPane2);

        //String Checking component
        JLabel FA_Strings_Header = new JLabel("Check Strings(Input)");
        FA_Strings_Header.setBounds(320,225, 300,25);
        fa_rg_page.add(FA_Strings_Header);
        JButton b4 = new JButton("Check");
        b4.setBounds(540,225, 60, 20);
        fa_rg_page.add(b4);
        JButton b5 = new JButton("Clear");
        b5.setBounds(480,225, 60, 20);
        fa_rg_page.add(b5);
        DefaultTableModel fa_checkstrings_model = new DefaultTableModel(); //Displaying check strings and status in table
        JTable FA_Checkstrings_Table = new JTable(fa_checkstrings_model);
        JScrollPane scrollPane3 = new JScrollPane(FA_Checkstrings_Table);
        scrollPane3.setBounds(320, 250, 300, 150);
        fa_rg_page.add(scrollPane3);
        fa_checkstrings_model.addColumn("Input Strings"); //add two columns: one for user input and one for status
        fa_checkstrings_model.addColumn("Result");
        
        for(int i=0; i<5; i++){
            fa_checkstrings_model.insertRow(0, new Object[]{""});
        }
        
        fa_rg_page.add(b1);
        fa_rg_page.add(b2);
        fa_rg_page.add(b3);
        fa_rg_page.add(fa_label1);
        fa_rg_page.add(states_input);
        fa_rg_page.add(fa_label2);
        fa_rg_page.add(alphabets_input);
        fa_rg_page.add(fa_label3);
        fa_rg_page.add(initial_input);
        fa_rg_page.add(fa_label4);
        fa_rg_page.add(final_input);

        //upon clicking b1
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                if (alphabets_input.getSelectedItem().toString() == "0,1"){
                    model.addColumn("δNFA");
                    model.addColumn("0");
                    model.addColumn("1");
                    model.addColumn("ε");
                } else if (alphabets_input.getSelectedItem().toString() == "0,1,2"){
                    model.addColumn("δNFA");
                    model.addColumn("0");
                    model.addColumn("1");
                    model.addColumn("2");
                    model.addColumn("ε");
                   
                } else if (alphabets_input.getSelectedItem().toString() == "0,1,2,3"){
                    model.addColumn("δNFA");
                    model.addColumn("0");
                    model.addColumn("1");
                    model.addColumn("2");
                    model.addColumn("3");
                    model.addColumn("ε");

                } else if (alphabets_input.getSelectedItem().toString() == "a,b"){
                    model.addColumn("δNFA");
                    model.addColumn("a");
                    model.addColumn("b");
                    model.addColumn("ε");
                   
                } else if (alphabets_input.getSelectedItem().toString() == "a,b,c"){
                    model.addColumn("δNFA");
                    model.addColumn("a");
                    model.addColumn("b");
                    model.addColumn("c");
                    model.addColumn("ε");
                   
                }

                //add rows
                if (states_input.getSelectedItem().toString()=="A"){
                    model.insertRow(0, new Object[] { "A" });
                } else if (states_input.getSelectedItem().toString()=="A,B"){
                    model.insertRow(0, new Object[] { "B" });
                    model.insertRow(0, new Object[] { "A" });
                } else if (states_input.getSelectedItem().toString()=="A,B,C"){
                    model.insertRow(0, new Object[] { "C" });
                    model.insertRow(0, new Object[] { "B" });
                    model.insertRow(0, new Object[] { "A" });
                } else if (states_input.getSelectedItem().toString()=="A,B,C,D"){
                    model.insertRow(0, new Object[] { "D" });
                    model.insertRow(0, new Object[] { "C" });
                    model.insertRow(0, new Object[] { "B" });
                    model.insertRow(0, new Object[] { "A" });
                } else if (states_input.getSelectedItem().toString()=="A,B,C,D,E"){
                    model.insertRow(0, new Object[] { "E" });
                    model.insertRow(0, new Object[] { "D" });
                    model.insertRow(0, new Object[] { "C" });
                    model.insertRow(0, new Object[] { "B" });
                    model.insertRow(0, new Object[] { "A" });
                } else if (states_input.getSelectedItem().toString()=="A,B,C,D,E,F"){
                    model.insertRow(0, new Object[] { "F" });
                    model.insertRow(0, new Object[] { "E" });
                    model.insertRow(0, new Object[] { "D" });
                    model.insertRow(0, new Object[] { "C" });
                    model.insertRow(0, new Object[] { "B" });
                    model.insertRow(0, new Object[] { "A" });
                } else if (states_input.getSelectedItem().toString()=="A,B,C,D,E,F,G"){
                    model.insertRow(0, new Object[] { "G" });
                    model.insertRow(0, new Object[] { "F" });
                    model.insertRow(0, new Object[] { "E" });
                    model.insertRow(0, new Object[] { "D" });
                    model.insertRow(0, new Object[] { "C" });
                    model.insertRow(0, new Object[] { "B" });
                    model.insertRow(0, new Object[] { "A" });
                } else if (states_input.getSelectedItem().toString()=="A,B,C,D,E,F,G,H"){
                    model.insertRow(0, new Object[] { "H" });
                    model.insertRow(0, new Object[] { "G" });
                    model.insertRow(0, new Object[] { "F" });
                    model.insertRow(0, new Object[] { "E" });
                    model.insertRow(0, new Object[] { "D" });
                    model.insertRow(0, new Object[] { "C" });
                    model.insertRow(0, new Object[] { "B" });
                    model.insertRow(0, new Object[] { "A" });
                } else if (states_input.getSelectedItem().toString()=="A,B,C,D,E,F,G,H,I"){
                    model.insertRow(0, new Object[] { "I" });
                    model.insertRow(0, new Object[] { "H" });
                    model.insertRow(0, new Object[] { "G" });
                    model.insertRow(0, new Object[] { "F" });
                    model.insertRow(0, new Object[] { "E" });
                    model.insertRow(0, new Object[] { "D" });
                    model.insertRow(0, new Object[] { "C" });
                    model.insertRow(0, new Object[] { "B" });
                    model.insertRow(0, new Object[] { "A" });
                } else if(states_input.getSelectedItem().toString()=="A,B,C,D,E,F,G,H,I,J"){
                    model.insertRow(0, new Object[] { "J" });
                    model.insertRow(0, new Object[] { "I" });
                    model.insertRow(0, new Object[] { "H" });
                    model.insertRow(0, new Object[] { "G" });
                    model.insertRow(0, new Object[] { "F" });
                    model.insertRow(0, new Object[] { "E" });
                    model.insertRow(0, new Object[] { "D" });
                    model.insertRow(0, new Object[] { "C" });
                    model.insertRow(0, new Object[] { "B" });
                    model.insertRow(0, new Object[] { "A" });
                }

                
            }
        });

        b2.addActionListener(new ActionListener(){ //clears the table
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                model.setColumnCount(0);
                File file = new File("RG.txt");
                file.delete();
            }
        });

        //generate RG when user clicks button
        b3.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e) {
                final String finalState = final_input.getSelectedItem().toString();
                //get the range of valid states based on the state_input
                final String stateRange = states_input.getSelectedItem().toString();
                //parse the stateRange into an array to be compared with the Nextstate input from table
                String[] stateRangeArr = stateRange.split(",");

                //flag for input error handling
                boolean errorInput = false;
                
                int numRows = NFATable.getRowCount();
                int numCols = NFATable.getColumnCount();

                try (FileWriter fileWriter = new FileWriter("RG.txt")) { 
                    StringBuilder grammar = new StringBuilder();   
                   
                    for (int row = 0; row < numRows; row++) {
                    StringBuilder productions = new StringBuilder();
                    String currentState = (String) NFATable.getValueAt(row, 0);
                        
                    for (int col = 0; col < numCols; col++) {
                        Object value = NFATable.getValueAt(row, col);

                        // Save the value to your desired storage or perform any other operation
                        if(NFATable.getColumnName(col)!="δNFA"){
                            if (value != null) {
                    
                                //String nextStates = (String) NFATable.getValueAt(row, col);
                                String nextStates = value.toString();
                                String inputSymbol = (String) NFATable.getColumnName(col);
                                //Conditions for epsilon
                                if (inputSymbol.equals("ε")){
                                    inputSymbol="";
                                }
                                
                                String[] nextStatesArr = nextStates.split(",");
                                boolean nextStatesMatch = false;

                                for (String nextState : nextStatesArr){
                                    if (Arrays.asList(stateRangeArr).contains(nextState.trim())){
                                        if (productions.length() >0){
                                            productions.append(" | ");
                                        }
                                        productions.append(inputSymbol).append(nextState.trim());
                                        nextStatesMatch = true;
                                    }
                                }

                                if(!nextStatesMatch){
                                    System.out.println("Input Error!");
                                    JOptionPane.showMessageDialog(null, "Invalid state entered", "Error", JOptionPane.ERROR_MESSAGE);
                                    errorInput = true;
                                    break;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                    //prevents from generating wrong RG when wrong user input
                    if(errorInput){
                        fileWriter.write("Invalid transition table");
                        break;
                    }
                    //build the grammar for each state 
                    if(productions.length() >0){
                        grammar.append(currentState).append(" -> ").append(productions).append(System.lineSeparator());    
                    }
                }

                if (!errorInput){
                    //for final state, add grammar of epsilon
                    grammar.append(finalState).append (" -> ").append("ε").append(System.lineSeparator());

                    String regularGrammar = grammar.toString();

                    // Save the RG
                    fileWriter.write(regularGrammar);
                }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }

                //display to gui
                try {
                    // Read some text from the resource file to display in
                    // the JTextArea.
                    RGTextArea.read(new BufferedReader(new FileReader("RG.txt")), null);

                } catch (IOException e3) {
                    e3.printStackTrace();
                }

            }
        });

        //component: string checking
        b4.addActionListener(new ActionListener(){ //checks if the user table input values will be accepted based on grammar
            public void actionPerformed(ActionEvent e) {
                final String finalState = final_input.getSelectedItem().toString();
                final String initState = initial_input.getSelectedItem().toString();
                int numRows = FA_Checkstrings_Table.getRowCount();
                boolean isAccepted; 
                List<String> inputString = new ArrayList<String>();
                //retrieve strings from user input
                for (int row = 0; row < numRows; row++){
                    Object value =FA_Checkstrings_Table.getValueAt(row,0); //get strings from the user input column
                    
                    //store user input in an array
                    if (value != null && !value.toString().isEmpty()){
                        inputString.add(value.toString());
                    }
                }
           
                StringChecker checkString = new StringChecker("RG.txt");

                //debug error in RG.txt
                if (checkString.getGrammarProductions().isEmpty()){
                    System.out.println("RG.txt productions not loaded properly. Check the file.");
                    JOptionPane.showMessageDialog(null, "No Regular Grammar Generated", "Error", JOptionPane.ERROR_MESSAGE);        
                    return;
                }
                //stores string result Accept/Reject
                List<String> checkedString = new ArrayList<String>();
                for (String input : inputString){
                    isAccepted = checkString.validateString(input,initState,finalState);

                    if(isAccepted){
                        System.out.println("String "+ input + " is Accepted");
                        checkedString.add("Accepted");
                    }
                    else{
                        System.out.println("String " + input + " is Rejected");
                        checkedString.add("Rejected");
                    }
                }
                
                for(int row=0;row<checkedString.size();row++){
                    fa_checkstrings_model.setValueAt(checkedString.get(row), row, 1);
                }
            }
        });
        
         //component: clears check Strings table.
         b5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int rows = fa_checkstrings_model.getRowCount();
                int cols = fa_checkstrings_model.getColumnCount();

                for(int row=0; row<rows; row++){
                    for(int col=0; col<cols; col++){
                        fa_checkstrings_model.setValueAt("", row, col);
                    }
                }
            }
        });




        //panel for CYK page
        JPanel cyk_page = new JPanel();


        //panel to help page
        JPanel help_page = new JPanel();

        tp.addTab("Home",home_page);
        tp.addTab("FA -> RG",fa_rg_page);
        tp.addTab("CYK",cyk_page);
        tp.addTab("Help",help_page);
        program_frame.add(tp);

        program_frame.setSize(700,500);
        program_frame.setLayout(null);  
        program_frame.setVisible(true);
        program_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        
    }


 

}

