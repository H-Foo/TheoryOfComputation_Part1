import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class StringChecker {
    private Map<String, List<String>> grammarProductions;

    public StringChecker(String grammarFilePath){
        grammarProductions = readRegularGrammarFromFile(grammarFilePath);
    }

    public boolean validateString(String input, String initialState, String finalState){
        return validateStringHelper(input,initialState,finalState);
    }

    private boolean validateStringHelper(String input, String currentState, String finalState) { 
        
        //check if input is empty for final state
        if(input.isEmpty()){
            return currentState.equals(finalState);
        }
        
        List<String> productions = grammarProductions.get(currentState);
    
        if (productions != null) {
            for(String production : productions){
                String inputSymbol = production.substring(0,1);
                String nextState = production.substring(1);
                
                if (inputSymbol.equals("ε")){
                    if(validateStringHelper(input, nextState,finalState)){
                            return true;
                        }
                }
                else if (input.startsWith(inputSymbol)){
                    if (validateStringHelper(input.substring(1), nextState, finalState)) {
                        return true;
                    }           
                }                 
            }
        }
        return false; 
    }

    private Map<String, List<String>> readRegularGrammarFromFile(String filePath){

        Map<String, List<String>> grammarProductions = new HashMap<>();


        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("->");
    
                if (parts.length == 2) {
                    String nonTerminal = parts[0].trim();
                    String productions = parts[1].trim();
    
                    List<String> productionList = new ArrayList<>();
                    String[] productionArray = productions.split("\\|");
    
                    for (String production : productionArray) {
                        productionList.add(production.trim());
                    }
                    grammarProductions.put(nonTerminal, productionList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }     
        return grammarProductions;
    }

    public Map<String, List<String>> getGrammarProductions(){
        return grammarProductions;
    }
}
