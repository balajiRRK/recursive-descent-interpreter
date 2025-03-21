class Call {
    
    String id;
    Parameters p;
    
    void parse()
    {
        Parser.expectedToken(Core.BEGIN);
        Parser.scanner.nextToken();
        
        Parser.expectedToken(Core.ID);
        id = Parser.scanner.getId();
        Parser.scanner.nextToken();
        
        Parser.expectedToken(Core.LPAREN);
        Parser.scanner.nextToken();
        
        p = new Parameters();
        p.parse();
        
        Parser.expectedToken(Core.RPAREN);
        Parser.scanner.nextToken();
        
        Parser.expectedToken(Core.SEMICOLON);
        Parser.scanner.nextToken();
    }
    
    void print()
    {
        System.out.print("begin ");
        System.out.print(id);
        System.out.print(" ( ");
        p.print();
        System.out.println(" ) ;");
    }
    
    void execute()
    {
        Memory.checkIfProcedureDeclared(id);
        Memory.pushFrame(id, p.execute());
    }
}
