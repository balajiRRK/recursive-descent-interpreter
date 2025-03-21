class Assign {
    
    Expr e;
    int scenario;
    String id, id2, string;

    void parse() 
    {
        Parser.expectedToken(Core.ID);
        id = Parser.scanner.getId();
        Parser.scanner.nextToken();

        Parser.checkIfDeclared(id);

        if (Parser.scanner.currentToken() == Core.ASSIGN)
        {
            Parser.expectedToken(Core.ASSIGN);
            Parser.scanner.nextToken();
            
            if (Parser.scanner.currentToken == Core.NEW)
            {
                Parser.checkType(id, "object");

                Parser.expectedToken(Core.NEW);
                Parser.scanner.nextToken();

                Parser.expectedToken(Core.OBJECT);
                Parser.scanner.nextToken();

                Parser.expectedToken(Core.LPAREN);
                Parser.scanner.nextToken();

                Parser.expectedToken(Core.STRING);
                string = Parser.scanner.getString();
                Parser.scanner.nextToken();

                Parser.expectedToken(Core.COMMA);
                Parser.scanner.nextToken();

                e = new Expr();
                e.parse();

                Parser.expectedToken(Core.RPAREN);
                Parser.scanner.nextToken();

                Parser.expectedToken(Core.SEMICOLON);
                Parser.scanner.nextToken();

                scenario = 2; // id = new object( string, <expr>); 
            } else 
            {
                e = new Expr();
                e.parse();

                Parser.expectedToken(Core.SEMICOLON);
                Parser.scanner.nextToken();

                scenario = 0; // id = <expr>;
            }
        } else if (Parser.scanner.currentToken() == Core.LSQUARE)
        {
            Parser.checkType(id, "object");

            Parser.expectedToken(Core.LSQUARE);
            Parser.scanner.nextToken();

            Parser.expectedToken(Core.STRING);
            string = Parser.scanner.getString();
            Parser.scanner.nextToken();

            Parser.expectedToken(Core.RSQUARE);
            Parser.scanner.nextToken();

            Parser.expectedToken(Core.ASSIGN);
            Parser.scanner.nextToken();

            e = new Expr();
            e.parse();

            Parser.expectedToken(Core.SEMICOLON);
            Parser.scanner.nextToken();

            scenario = 1; // id [ string ] = <expr>;
        } else
        {
            Parser.expectedToken(Core.COLON);
            Parser.scanner.nextToken();

            Parser.expectedToken(Core.ID);
            id2 = Parser.scanner.getId();
            Parser.scanner.nextToken();

            Parser.checkIfDeclared(id);

            Parser.expectedToken(Core.SEMICOLON);
            Parser.scanner.nextToken();
            
            scenario = 3; // id : id;
        } 
    }

    void print()
    {
        if (scenario == 0) // id = <expr>;
        {
            System.out.print(id + " = " );
            e.print();
            System.out.println(";");
        } else if (scenario == 1) // id [ string ] = <expr>;   
        {
            System.out.print(id + " [ \'" + string + "\' ] = ");
            e.print();
            System.out.println(";");
        } else if (scenario == 2) // id = new object( string, <expr> );
        {
            System.out.print(id + " = new object( \'" + string + "\', ");
            e.print();
            System.out.println(" );");
        } else if (scenario == 3) // id : id ;
        {
            System.out.println(id + " : " + id2 + ";");
        }
    }

    void execute()
    {   
        if (scenario == 0) // id = <expr>;
        {
            Memory.getSpecificMap(id).get(id).setVal(e.execute());
        } else if (scenario == 1) // id [ string ] = <expr>;  
        {
            Memory.getSpecificMap(id).get(id).addToObject(string, e.execute());
        } else if (scenario == 2) // id = new object( string, <expr> );
        {
            Memory.getSpecificMap(id).put(id, new Variable(string, e.execute()));
        } else if (scenario == 3) // id : id2 ;
        {
            Memory.getSpecificMap(id).get(id).setMapReference(Memory.getSpecificMap(id2).get(id2));
        }
    }
}