import java.util.HashMap;

class Loop {
    
    Expr e, e2;
    Cond c;
    StmtSeq ss;
    String id;

    void parse()
    {
        Parser.expectedToken(Core.FOR);
        Parser.scanner.nextToken();
        
        Parser.expectedToken(Core.LPAREN);
        Parser.scanner.nextToken();

        Parser.expectedToken(Core.ID);
        id = Parser.scanner.getId();
        Parser.scanner.nextToken();

        Parser.checkIfDeclared(id);

        Parser.expectedToken(Core.ASSIGN);
        Parser.scanner.nextToken();

        e = new Expr();
        e.parse();

        Parser.expectedToken(Core.SEMICOLON);
        Parser.scanner.nextToken();

        c = new Cond();
        c.parse();

        Parser.expectedToken(Core.SEMICOLON);
        Parser.scanner.nextToken(); 
        
        e2 = new Expr();
        e2.parse();

        Parser.expectedToken(Core.RPAREN);
        Parser.scanner.nextToken();

        Parser.expectedToken(Core.DO);
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
        System.out.print("for ( " + id + " = ");
        e.print();
        System.out.print("; ");
        c.print();
        System.out.print("; ");
        e2.print();
        System.out.println(" ) do ");
        ss.print();
        System.out.println("end");
    }

    void execute()
    {
        // set initial value
        Memory.getSpecificMap(id).get(id).setVal(e.execute());
        while (c.execute()) 
        {
            Memory.push();
            ss.execute(); // execute body
            Memory.pop(); // pop before updating id since it exits body scope by then
            Memory.getSpecificMap(id).get(id).setVal(e2.execute()); // update id
        }
    }
}
