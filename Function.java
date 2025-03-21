import java.util.HashMap;

class Function {
    
    String id;
    Parameters p;
    StmtSeq ss;
    
    void parse()
    {
        Parser.expectedToken(Core.PROCEDURE);
        Parser.scanner.nextToken();
        
        Parser.expectedToken(Core.ID);
        id = Parser.scanner.getId();
        Parser.scanner.nextToken();
        
        Parser.expectedToken(Core.LPAREN);
        Parser.scanner.nextToken();
        
        Parser.expectedToken(Core.OBJECT);
        Parser.scanner.nextToken();
        
        p = new Parameters();
        p.parse();
        
        Parser.expectedToken(Core.RPAREN);
        Parser.scanner.nextToken();
        
        Parser.expectedToken(Core.IS);
        Parser.scanner.nextToken();
        
        Parser.stackOfMaps.push(new HashMap<String, String>());
        
        ss = new StmtSeq();
        ss.parse();
        
        Parser.stackOfMaps.pop();
        
        Parser.expectedToken(Core.END);
        Parser.scanner.nextToken();
    }
    
    void print()
    {
        System.out.print("procedure ");
        System.out.print(id);
        System.out.print(" ( object ");
        p.print();
        System.out.print(" ) is ");
        ss.print();
        System.out.println(" end");
    }
    
    void execute()
    {
        Memory.checkIfProcedureNameUnique(id);
        Memory.functionMap.put(id, this);
    }
}