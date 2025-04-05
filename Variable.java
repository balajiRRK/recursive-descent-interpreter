import java.util.HashMap;
import java.util.Map;

public class Variable {
    int val;
    Core type;
    Map<String, Integer> map;
    String originalKey;

    public Variable(int temp)
    {
        val = temp;
        type = Core.INTEGER;
    }

    public Variable()
    {
        type = Core.OBJECT;
    }

    // <assign>'s id = new object( string, <expr> );'
    public Variable(String temp, int temp2)
    {
        map = new HashMap<String, Integer>();
        map.put("Reference Count", 1);
        Memory.totalObjCount++; 
        map.put(temp, temp2);
        originalKey = temp;
        type = Core.OBJECT;
    }
    
    // GETTERS & SETTERS

    public int getVal()
    {
        if (type == Core.INTEGER)
        {
            return val;
        } else {
            return map.get(originalKey);
        }
    }
    
    public Core getType() {
        return type;
    }
    
    // <factor>'s id [ string ]
    public int getObjVal(String temp)
    {
        if (map.containsKey(temp))
        {
            return map.get(temp);
        } else
        {
            System.out.println("ERROR: String key \'" + temp + "\' not in map");
            System.exit(0);
            return 0;   
        }
    }

    public Map<String, Integer> getMap()
    {
        return map;
    }

    public String getOriginalKey()
    {
        return originalKey;
    }

    // id = <expr> for eitiher original key = val assignment or int id = val assignment
    public void setVal(int val) {
        if (type == Core.INTEGER)
        {
            this.val = val;
        } else { // obj
            if (map == null) {
                System.out.println("ERROR: Object has not been declared yet, cannot assign default key value");
                System.exit(0);
            }
            map.put(originalKey, val);
        }
    }

    // id [ string ] = <expr>;
    public void addToObject(String key, int val)
    {
        map.put(key, val);
    }

    // id : id2
    public void setMapReference(Variable temp)
    {
        map = temp.getMap();
        originalKey = temp.getOriginalKey();
    }

    @Override
    public String toString()
    {
        if (map != null)
        {
            return map.toString();
        } else 
        {
            return "empty";
        }
    }

    public void adjustReferenceCount()
    {
        
    }
}