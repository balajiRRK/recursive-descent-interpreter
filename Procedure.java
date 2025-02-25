import java.util.HashMap;

class Procedure {

    String name;
    DeclSeq ds;
    StmtSeq ss;
    int scenario;

    void parse()
    {
        Parser.expectedToken(Core.PROCEDURE);
        Parser.scanner.nextToken();

        // procedure name declaration, dont need to check if declared
        Parser.expectedToken(Core.ID);
        name = Parser.scanner.getId();
        Parser.scanner.nextToken();

        Parser.expectedToken(Core.IS);
        Parser.scanner.nextToken();

        Parser.stackOfMaps.push(new HashMap<String, String>());
        
        if (Parser.scanner.currentToken() != Core.BEGIN) {
            ds = new DeclSeq();
            ds.parse();
        }

        Parser.expectedToken(Core.BEGIN);
        Parser.scanner.nextToken();

        Parser.stackOfMaps.push(new HashMap<String, String>());

        ss = new StmtSeq();
        ss.parse();

        Parser.stackOfMaps.pop();
        Parser.stackOfMaps.pop();

        Parser.expectedToken(Core.END);
        Parser.scanner.nextToken();
        
        Parser.expectedToken(Core.EOS);
    }
    
    void print()
    {
        System.out.println("procedure " + name + " is ");
        if (ds != null)
        {
            ds.print();
        }

        System.out.println("begin");
        ss.print();
        System.out.println("end");
    }

    void execute()
    {
        boolean hasGlobalScope = false;
        if (ds != null)
        {
            Memory.push(); // new global scope
            ds.execute();
            hasGlobalScope = true;
        } 
        Memory.push(); // new local scope

        ss.execute();

        Memory.pop(); // local scope pop
        if (hasGlobalScope)
        {
            Memory.pop(); // global scope pop
        }
    }
}    