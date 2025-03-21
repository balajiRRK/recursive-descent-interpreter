import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

public class Memory {

    static Stack<Stack<Map<String, Variable>>> frameStack = new Stack<Stack<Map<String, Variable>>>();
    static Scanner s;
    static Map<String, Function> functionMap = new HashMap<String, Function>();

    // adds id, val to top-most stack (current function scope this function was called in)
    public static void addToMap(String id, Variable temp)
    {
        frameStack.peek().peek().put(id, temp);
    }

    // gets specific map from top-most stack (current function scope this function was called in)
    public static Map<String, Variable> getSpecificMap(String id)
    {
        Map<String, Variable> temp = null;

        // FIFO/Queue order so outermost scope checked first and gets overridden by innermost
        for (Map<String, Variable> map : frameStack.peek())
        {
            if (map.containsKey(id))
            {
                temp = map;
            }
        }

        if (temp == null)
        {
            System.out.println("ERROR: id not contained in any map in stack");
            System.exit(0);
        }

        return temp;
    }

    public static void push()
    {
        if (frameStack.size() != 0)
        {
            frameStack.peek().push(new HashMap<String, Variable>());
        } else 
        {
            frameStack.push(new Stack<Map<String, Variable>>());
            frameStack.peek().push(new HashMap<String, Variable>());
        }
    }

    // creates new frame with global variables and formal parameters pushed into it
    // pushes and executes function
    public static void pushFrame(String funcName, ArrayList<String> idList)
    {
        Stack<Map<String, Variable>> frame = new Stack<Map<String, Variable>>();
        frame.push(frameStack.peek().get(0)); // pushes global variables into each frame
        
        
        // adds formal params from begin() call to a map with their key id names from 
        // <param> and then finds their value for the key value pair and then pushes that 
        // map to the frame
        Map<String, Variable> temp = new HashMap<String, Variable>();

        for (int i = 0; i < idList.size(); i++)
        {
            // param name
            String key = Memory.functionMap.get(funcName).p.execute().get(i);
            // param value
            Variable value = Memory.getSpecificMap(idList.get(i)).get(idList.get(i));
            
            temp.put(key, value);
        }
        
        frame.push(temp); // formal param pushed to frame
        frameStack.push(frame);
        Memory.functionMap.get(funcName).ss.execute();
        Memory.frameStack.pop(); // pop func after execution
    }

    public static Map<String, Variable> pop()
    {
        return frameStack.peek().pop();
    }

    // Procedure semantic checks

    public static void checkIfProcedureNameUnique(String id)
    {
        if (functionMap.containsKey(id))
        {
            System.out.println("ERROR: Procedure name is a duplicate of another procedure name: " + id);
            System.exit(0);
        }
    }

    // check if call(id) calls a declared procedure
    public static void checkIfProcedureDeclared(String id)
    {
        if (!functionMap.containsKey(id))
        {
            System.out.println("ERROR: Procedure is not declared: " + id);
            System.exit(0);
        }
    }

    public static void checkFormalParamDuplicateName(ArrayList<String> params)
    {
        if (new HashSet<>(params).size() != params.size())
        {
            System.out.println("ERROR: duplicate formal parameter names");
            System.exit(0);
        }
    }

    // GETTERS & SETTERS

    public static Stack<Stack<Map<String, Variable>>> getStack()
    {
        return frameStack;
    }
}