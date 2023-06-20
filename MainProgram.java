import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import javax.swing.table.DefaultTableModel;

import javax.swing.border.*;

public class MainProgram {

    private static Set<String>[][] chart;

    public static void main(String[] args){
        JFrame program_frame = new JFrame(); //creating one new window where everything takes place
        JTextArea content = new JTextArea(500,500);
        program_frame.add(content);
        JTabbedPane tp = new JTabbedPane();
        tp.setBounds(0,0,700,500);  

        //panel for home page
        JPanel home_page = new JPanel();        
        home_page.setLayout(null);
        
        //scaling image
        ImageIcon imageIcon = new ImageIcon("image.jpg");
        Image originalImage = imageIcon.getImage();
        int icon_width = 75;
        int icon_height = 75;
        Image finalImage = originalImage.getScaledInstance(icon_width, icon_height, Image.SCALE_SMOOTH);
        
        //set icons
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border border2 = BorderFactory.createLineBorder(Color.BLACK, 2);
        JLabel home_label2a = new JLabel();
        home_label2a.setIcon(new ImageIcon(finalImage));
        home_label2a.setBorder(border2);
        JLabel home_label2b = new JLabel();
        home_label2b.setIcon(new ImageIcon(finalImage));
        home_label2b.setBorder(border);
        JLabel home_label2c = new JLabel();
        home_label2c.setIcon(new ImageIcon(finalImage));
        home_label2c.setBorder(border);
        JLabel home_label2d = new JLabel();
        home_label2d.setIcon(new ImageIcon(finalImage));
        home_label2d.setBorder(border);
        
        JLabel home_label1 = new JLabel("<html>TIC2151 Theory of Computation <br/> Tut. no. TT1L <br/> Group no.2 </html>",SwingConstants.CENTER);
        JLabel home_label2 = new JLabel("Geralyn Ting        Humaira' Foo        Yap Han Yang       Yan Jac Kie ");
        JLabel home_label3 = new JLabel(" 1181102223            1201301955            1181102575         1191201793 ");
        JLabel home_label4 = new JLabel("<html>Participation: <br/> Geralyn Ting = 25% - Q1 <br/> Humaira' Foo = 25% - Q1 <br/> Yap Han Yang = 30% - Q2<br/> Yan Jac Kie = 20% - Q2</html>",SwingConstants.CENTER);
        JLabel home_label5 = new JLabel("Leader");

        home_label1.setBounds(90,15, 200,100);
        home_label2.setBounds(100,100, 400,30);
        home_label3.setBounds(100,120, 400,30);
        home_label2a.setBounds(100,150, 75,75);
        home_label2b.setBounds(200,150, 75,75);
        home_label2c.setBounds(300,150, 75,75);
        home_label2d.setBounds(400,150, 75,75);
        home_label5.setBounds(100,220, 100,30);
        home_label4.setBounds(70,200, 200,200);
        
        home_page.add(home_label1);
        home_page.add(home_label2);
        home_page.add(home_label2a);
        home_page.add(home_label2b);
        home_page.add(home_label2c);
        home_page.add(home_label2d);
        home_page.add(home_label3);
        home_page.add(home_label4);
        home_page.add(home_label5);
        
        //panel for FA to RG page
        JPanel fa_rg_page = new JPanel();
        fa_rg_page.setLayout(null);
        // Component 1: Input (must accept 1-10 variables/states, 1-3 alphabet and Epsilon)
        String[] states = {"A","A,B","A,B,C","A,B,C,D","A,B,C,D,E","A,B,C,D,E,F","A,B,C,D,E,F,G","A,B,C,D,E,F,G,H","A,B,C,D,E,F,G,H,I","A,B,C,D,E,F,G,H,I,J"};//Q
        String[] alphabets = {"0,1","0,1,2","0,1,2,3"}; //∑
        String[] initials= {"A","B","C","D","E","F","G","H","I","J"};
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
        JComboBox<String> final_input = new JComboBox<>(initials);
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
        b4.setBounds(540,225, 70, 20);
        fa_rg_page.add(b4);
        DefaultTableModel fa_checkstrings_model = new DefaultTableModel(); //Displaying check strings and status in table
        JTable FA_Checkstrings_Table = new JTable(fa_checkstrings_model);
        JScrollPane scrollPane3 = new JScrollPane(FA_Checkstrings_Table);
        scrollPane3.setBounds(320, 250, 300, 150);
        fa_rg_page.add(scrollPane3);
        fa_checkstrings_model.addColumn(""); //add two columns: one for user input and one for status
        fa_checkstrings_model.addColumn("");
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

        b3.addActionListener(new ActionListener(){ //saves edited changes and generates rg
            public void actionPerformed(ActionEvent e) {
                
                int numRows = NFATable.getRowCount();
                int numCols = NFATable.getColumnCount();

                try (FileWriter fileWriter = new FileWriter("RG.txt")) {
                    
                

                for (int row = 0; row < numRows; row++) {
                    for (int col = 0; col < numCols; col++) {
                        Object value = NFATable.getValueAt(row, col);
                        // Save the value to your desired storage or perform any other operation
                        if(NFATable.getColumnName(col)!="δNFA"){
                            if (value != null) {
                                String currentState = (String) NFATable.getValueAt(row, 0);
                                String nextStates = (String) NFATable.getValueAt(row, col);
                                String inputSymbol = (String) NFATable.getColumnName(col);
                                
                                // Conditions for epsilon
                                if (inputSymbol=="ε"){
                                    inputSymbol="";
                                }

                                // Generate RG production rule in the form: currentState -> inputSymbol nextStates
                                String productionRule = currentState + " -> " + inputSymbol + nextStates +"\n";
                                
                                // Save the RG
                                fileWriter.write(productionRule);
                                
                            }else{
                                continue;
                            }
                        }
                        
                    
                    }
                }} catch (IOException e2) {
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

        b4.addActionListener(new ActionListener(){ //checks if the user table input values will be accepted based on grammar
            public void actionPerformed(ActionEvent e) {
                
                int numRows = FA_Checkstrings_Table.getRowCount();
                for (int row = 0; row < numRows; row++){
                    Object value =FA_Checkstrings_Table.getValueAt(row,0); //get value from the user input side
                    
                }
            }
        });
        
        
        //panel for CYK page
        JPanel cyk_page = new JPanel();
        cyk_page.setLayout(null);
        JButton b7 = new JButton("Import");
        b7.setBounds(5,10, 80,25);
        JButton b5 = new JButton("Clear");  // on click, clear program
        b5.setBounds(5,40, 80,25);
        JButton b6 = new JButton("Check");  // on click, submit input to generate rg output
        b6.setBounds(5,70, 80,25);
        cyk_page.add(b7);
        cyk_page.add(b5);
        cyk_page.add(b6);
        
        JTextArea CNF_input = new JTextArea();
        CNF_input.setBounds(120,20, 200,200);
       // CNF_input.setSize(200,200);
        cyk_page.add(CNF_input);
        
        JTextField inputStringField = new JTextField();
        inputStringField.setBounds(120, 230, 200, 20);
        cyk_page.add(inputStringField);

        JTextArea cyk_display = new JTextArea();
        JScrollPane scrollbarj = new JScrollPane(cyk_display);
        scrollbarj.setBounds(120, 250, 400, 180);
        cyk_page.add(scrollbarj);
        
        // Example grammar in CNF
        java.util.List<Rule> grammar = new ArrayList<>();
        grammar.add(new Rule("S", "AB"));
        grammar.add(new Rule("S", "BC"));
        grammar.add(new Rule("A", "BA"));
        grammar.add(new Rule("A", "a"));
        grammar.add(new Rule("B", "CC"));
        grammar.add(new Rule("B", "b"));
        grammar.add(new Rule("C", "AB"));
        grammar.add(new Rule("C", "a"));

        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CNF_input.setText("");
                cyk_display.setText("");
                inputStringField.setText("");
            }
        });
        
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputString = inputStringField.getText();
                String cnfGrammar = CNF_input.getText();

                // Parse CNF grammar
                java.util.List<Rule> grammar = parseCNFGrammar(cnfGrammar);

                // Generate CYK chart
                generateCYKChart(grammar, inputString);

                // Print CYK chart and result
                printCYKChart(inputString, cyk_display);
            }
        });

        //panel to help page
        JPanel help_page = new JPanel();
        JLabel help = new JLabel("<html>1. FA->RG: Avoid clicking 'New' more than once as it will append the table into itself. In this case, select 'Clear'. <br/> 2. To start a new instance, remember to select 'Clear'. <br/> 3. For the user manual, please refer to the application report  <html>");
        help_page.add(help);


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

        private static void generateCYKChart(java.util.List<Rule> grammar, String inputString) {
        int n = inputString.length();
        chart = new HashSet[n][n];

        // Initialize chart
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chart[i][j] = new HashSet<>();
            }
        }

        // Fill in the diagonal of the chart with terminals that generate the string
        for (int i = 0; i < n; i++) {
            for (Rule rule : grammar) {
                if (rule.getRhs().length() == 1 && rule.getRhs().equals(String.valueOf(inputString.charAt(i)))) {
                    chart[i][i].add(rule.getLhs());
                }
            }
        }

        // Fill in the rest of the chart
        for (int length = 2; length <= n; length++) {
            for (int start = 0; start <= n - length; start++) {
                int end = start + length - 1;
                for (int split = start; split < end; split++) {
                    for (Rule rule : grammar) {
                        if (rule.getRhs().length() == 2) {
                            String A = rule.getLhs();
                            String B = rule.getRhs().substring(0, 1);
                            String C = rule.getRhs().substring(1);

                            if (chart[start][split].contains(B) && chart[split + 1][end].contains(C)) {
                                chart[start][end].add(A);
                            }
                        }
                    }
                }
            }
        }
    }
    
private static java.util.List<Rule> parseCNFGrammar(String cnfGrammar) {
    java.util.List<Rule> grammar = new ArrayList<>();

    // Split the input by newlines to get individual rules
    String[] ruleLines = cnfGrammar.split("\\r?\\n");

    for (String line : ruleLines) {
        // Split each rule into LHS and RHS
        String[] ruleParts = line.split("->");
        String lhs = ruleParts[0].trim();
        String[] rhsParts = ruleParts[1].split("\\|");
        
        for (String rhs : rhsParts) {
            grammar.add(new Rule(lhs, rhs.trim()));
        }
    }

    return grammar;
}

    public static void printCYKChart(String inputString, JTextArea cyk_display) {
        int n = inputString.length();

        StringBuilder chartOutput = new StringBuilder();

        chartOutput.append("\t");
        for (int j = 0; j < n; j++) {
            chartOutput.append(inputString.charAt(j)).append("\t");
        }
        chartOutput.append("\n");

        for (int i = 0; i < n; i++) {
            chartOutput.append(i).append("\t");
            for (int j = 0; j < n - i; j++) {
                chartOutput.append("|");
                for (String value : chart[j][j + i]) {
                    chartOutput.append(value).append(" ");
                }
                chartOutput.append("\t");
            }
            chartOutput.append("|");
            chartOutput.append("\n");
        }

        if (chart[0][n - 1].contains("S")) {
            chartOutput.append("The string can be generated by the grammar.");
        } else {
            chartOutput.append("The string cannot be generated by the grammar.");
        }

        cyk_display.setText(chartOutput.toString());
    }

    static class Rule {
        private String lhs;
        private String rhs;

        public Rule(String lhs, String rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
        }

        public String getLhs() {
            return lhs;
        }

        public String getRhs() {
            return rhs;

        }
    }
 

}

