// Part 1: 
// Convert Finite Automata into Regular Grammar (FA can be entered as a formal definition
// with transition table)
// Testing strings (up to 5 at once) a statement to inform user whether
// string is accepted or rejected

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

import javax.swing.border.*;

public class MainProgram {

    public static void main(String[] args){
        JFrame program_frame = new JFrame(); //creating one new window where everything takes place
        JTextArea content = new JTextArea(500,500);
        program_frame.add(content);
        JTabbedPane tp = new JTabbedPane();
        tp.setBounds(0,0,500,500);  

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


        // Component 2: Regular Grammar Conversion Output

        // Component 3: Check Strings (input) (can check min. 5 string at once)
        JButton b1 = new JButton("New"); 
        b1.setBounds(5,10, 80,25); 
        JButton b2 = new JButton("Clear");  // on click, clear program
        b2.setBounds(5,40, 80,25);
        JButton b3 = new JButton("RG");  // on click, submit input to generate rg output
        b3.setBounds(5,70, 80,25);
       
        JLabel output1 = new JLabel();
        fa_rg_page.add(output1);
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

        //upon clicking b3
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String states_choice = states_input.getSelectedItem().toString();
                String alphabets_choice = alphabets_input.getSelectedItem().toString();
                String initial_choice = initial_input.getSelectedItem().toString();
                String final_choice = final_input.getSelectedItem().toString();
        
                output1.setText("The output is: " + states_choice +", "+alphabets_choice+", "+initial_choice+", "+final_choice);
                output1.setBounds(300, 10, 200, 100);
                
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

        program_frame.setSize(500,500);
        program_frame.setLayout(null);  
        program_frame.setVisible(true);
        program_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        
    }


 

}

