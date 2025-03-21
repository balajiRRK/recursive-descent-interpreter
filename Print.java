class Print {
    
    Expr e;

    void parse()
    {
        Parser.expectedToken(Core.PRINT);
        Parser.scanner.nextToken();

        Parser.expectedToken(Core.LPAREN);
        Parser.scanner.nextToken();     
    
        e = new Expr();
        e.parse();
        
        Parser.expectedToken(Core.RPAREN);
        Parser.scanner.nextToken();

        Parser.expectedToken(Core.SEMICOLON);
        Parser.scanner.nextToken();        
    }

    void print()
    {
        System.out.print("print ( ");
        e.print();
        System.out.println(" ) ; ");
    }

    void execute()
    {
        System.out.println(e.execute()); 
    }
}