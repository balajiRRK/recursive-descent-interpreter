import java.util.Map;
import java.util.Stack;

class Parser {

    // stores all variables for all scopes
    public static Stack<Map<String, String>> stackOfMaps = new Stack<Map<String, String>>();
    public static Scanner scanner;

    static void expectedToken(Core expected)
    {
        if (scanner.currentToken() != expected)
        {
            System.out.println("ERROR: Expected " + expected + ", received " + scanner.currentToken());
            System.exit(0);
        }
    }

    static void checkIfDeclared(String id)
    {
        // check if declared in all maps (including outer scopes)
        boolean containsId = false;
        for (Map<String, String> map : Parser.stackOfMaps)
        {
            if (map.containsKey(id))
            {
                containsId = true;
            }
        }
        
        if (!containsId)
        {
            System.out.println("ERROR: identifier not declared: " + id);
            System.exit(0);
        }
    }

    static void checkType(String id, String type)
    {
        // check if declared type matches use (including outer scopes)
        boolean typeMatches = false;
        
        for (Map<String, String> map : Parser.stackOfMaps) // FIFO/Queue order 
        {
            if (map != null && map.containsKey(id) && map.get(id).equals(type))
            {
                typeMatches = true; // keep updating typeMatches until you reach the last one (deepest one)
            }
        }

        if (!typeMatches)
        {
            System.out.println("ERROR: declared type does not match usage: " + id);
            System.exit(0);
        }
    }

    static void checkIfDoublyDeclared(String id)
    {
        // only check same scope double declaration
        if (stackOfMaps.peek().containsKey(id))
        {
            System.out.println("ERROR: identifier has been doubly-declared in same scope: " + id);
            System.exit(0);
        }
    }
}