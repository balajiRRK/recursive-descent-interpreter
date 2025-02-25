import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Memory {
    
    static Stack<Map<String, Variable>> idStack = new Stack<Map<String, Variable>>();
    static Scanner s;

    public static void addToMap(String id, Variable temp)
    {
        idStack.peek().put(id, temp);
    }

    public static Map<String, Variable> getMap()
    {
        return idStack.peek();
    }

    public static Map<String, Variable> getSpecificMap(String id)
    {
        Map<String, Variable> temp = null;

        for (Map<String, Variable> map : idStack) // FIFO/Queue order 
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
        idStack.push(new HashMap<String, Variable>());
    }


    public static Map<String, Variable> pop()
    {
        return idStack.pop();
    }

    // GETTERS & SETTERS

    public static Stack<Map<String, Variable>> getStack()
    {
        return idStack;
    }
}