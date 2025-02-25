class Expr {

    Term t;
    Expr e;
    int scenario;

    void parse()
    {
        t = new Term();
        t.parse();

        scenario = 0; // <term>

        if (Parser.scanner.currentToken() == Core.ADD)
        {
            Parser.expectedToken(Core.ADD);
            Parser.scanner.nextToken();

            e = new Expr();
            e.parse();

            scenario = 1; // <term> + <expr>
        } else if (Parser.scanner.currentToken() == Core.SUBTRACT)
        {
            Parser.expectedToken(Core.SUBTRACT);
            Parser.scanner.nextToken();

            e = new Expr();
            e.parse();

            scenario = 2; // <term> - <expr>
        }
    }

    void print()
    {
        if (scenario == 0) // <term>
        {
            t.print();
        } else if (scenario == 1) // <term> + <expr>
        {
            t.print();
            System.out.print(" + ");
            e.print();
        } else if (scenario == 2) // <term> - <expr>
        {
            t.print();
            System.out.print(" - ");
            e.print();
        }
    }

    int execute()
    {
        int value = t.execute();
        // scenario 0 is covered by t.execute();
        if (scenario == 1) // <term> + <expr>
        {
            value += e.execute(); 
        } else if (scenario == 2) // <term> - <expr>
        {
            value -= e.execute();
        }  
        
        return value;
    }
}
