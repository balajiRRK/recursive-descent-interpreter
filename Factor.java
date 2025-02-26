class Factor {

    Expr e;
    int scenario, constVal;
    String id, string;

    void parse()
    {
        if (Parser.scanner.currentToken() == Core.ID)
        {
            Parser.expectedToken(Core.ID);
            id = Parser.scanner.getId();
            Parser.scanner.nextToken();

            Parser.checkIfDeclared(id);

            scenario = 0; // id

            if (Parser.scanner.currentToken() == Core.LSQUARE)
            {
                Parser.expectedToken(Core.LSQUARE);
                Parser.scanner.nextToken();
    
                Parser.expectedToken(Core.STRING);
                string = Parser.scanner.getString();
                Parser.scanner.nextToken();
    
                Parser.expectedToken(Core.RSQUARE);
                Parser.scanner.nextToken();

                scenario = 1; // id [ string ]
            }
        } else if (Parser.scanner.currentToken() == Core.CONST)
        {
            Parser.expectedToken(Core.CONST);
            constVal = Parser.scanner.getConst();
            Parser.scanner.nextToken();

            scenario = 2; // const
        } else if (Parser.scanner.currentToken() == Core.LPAREN)
        {
            Parser.expectedToken(Core.LPAREN);
            Parser.scanner.nextToken();

            e = new Expr();
            e.parse();

            Parser.expectedToken(Core.RPAREN);
            Parser.scanner.nextToken();

            scenario = 3; // ( <expr> )
        } else
        {
            System.out.println("ERROR: Not valid factor " + Parser.scanner.currentToken());
            System.exit(0);
        }
    }

    void print()
    {
        if (scenario == 0) // id
        {
            System.out.println(id);
        } else if (scenario == 1) // id [ string ]
        {
            System.out.println(id + " [ \'" + string + "\' ]");
        } else if (scenario == 2) // const
        {
            System.out.println(constVal);
        } else if (scenario == 3) // ( <expr> )
        {
            System.out.print(" ( ");
            e.print();
            System.out.println(" ) ");
        }
    }

    int execute()
    {
        if (scenario == 0) // id
        {
            return Memory.getSpecificMap(id).get(id).getVal();
        } else if (scenario == 1) // id [ string ]
        {
            // does error checking to see if key val pair exists
            return Memory.getSpecificMap(id).get(id).getObjVal(string);
        } else if (scenario == 2) // const
        {
            return constVal;
        } else if (scenario == 3) // ( <expr> )
        {
            return e.execute();
        }

        return 0; // wont run
    }
}
