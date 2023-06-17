import java.util.*;

public class StringChecker {
    private Map<String, List<String>> grammarProductions;

    public StringChecker(Map<String, List<String>> grammarProductions){
        this.grammarProductions = grammarProductions;
    };

    public boolean validateString(String input, String initialState){
        return validateStringHelper(input,initialState);
    }

    private boolean validateStringHelper(String input, String currentState){
        if (input.isEmpty()){
            return currentState.equals("FINAL_STATE");
        }

        List<String> productions = grammarProductions.get(currentState);

        for(String production : productions){
            String inputSymbol = production.substring(0,1);
            String nextState = production.substring(1);

            if (input.startsWith(inputSymbol)){
                if(validateStringHelper(input.substring(1), nextState)){
                    return true;
                }
            }
        }

        return false;
    }
}
