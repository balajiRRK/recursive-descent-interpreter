class Cmpr {
    
    Expr e, e2;
    int scenario;

    void parse()
    {
        e = new Expr();
        e.parse();

        if (Parser.scanner.currentToken() == Core.EQUAL)
        {
            Parser.expectedToken(Core.EQUAL);
            Parser.scanner.nextToken(); 

            e2 = new Expr();
            e2.parse();  

            scenario = 0;
        } else if (Parser.scanner.currentToken() == Core.LESS)
        {
            Parser.expectedToken(Core.LESS);
            Parser.scanner.nextToken(); 

            e2 = new Expr();
            e2.parse();

            scenario = 1;
        }
    }

    void print()
    {
        if (scenario == 0) // <expr> == <expr>
        {
            e.print();
            System.out.print(" == ");
            e2.print();
        } else if (scenario == 1) // <expr> < <expr>
        {
            e.print();
            System.out.print(" < ");
            e2.print();
        }
    }

    boolean execute()
    {
        if (scenario == 0) // <expr> == <expr>
        {
            return e.execute() == e2.execute();
        } else if (scenario == 1) // <expr> < <expr>
        {
            return e.execute() < e2.execute();
        }

        return false; // wont run
    }
}
