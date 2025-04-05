class Cond {
    
    Cmpr c;
    Cond co;
    int scenario;

    // cond not tested heavily

    void parse()
    {
        if (Parser.scanner.currentToken() == Core.NOT)
        {
            Parser.expectedToken(Core.NOT);
            Parser.scanner.nextToken();
            
            co = new Cond();
            co.parse();

            scenario = 1;
        } else if (Parser.scanner.currentToken() == Core.LSQUARE)
        {
            Parser.expectedToken(Core.LSQUARE);
            Parser.scanner.nextToken();

            co = new Cond();
            co.parse();

            Parser.expectedToken(Core.RSQUARE);
            Parser.scanner.nextToken();

            scenario = 2;
        } else
        {
            c = new Cmpr();
            c.parse();

            scenario = 0;

            if (Parser.scanner.currentToken() == Core.OR)
            {
                Parser.expectedToken(Core.OR);
                Parser.scanner.nextToken();

                co = new Cond();
                co.parse();

                scenario = 3;
            } else if (Parser.scanner.currentToken() == Core.AND)
            {
                Parser.expectedToken(Core.AND);
                Parser.scanner.nextToken();

                co = new Cond();
                co.parse();

                scenario = 4;
            }
        }
    }

    void print()
    {
        if (scenario == 0) // <cmpr>
        {
            c.print();
        } else if (scenario == 1) // not <cond>
        {
            System.out.print("not ");
            co.print();
        } else if (scenario == 2) // [ <cond> ]
        {
            System.out.print(" [ ");
            co.print();
            System.out.println(" ] ");
        } else if (scenario == 3) // <cmpr> or <cond>
        {
            c.print();
            System.out.print(" or ");
            co.print();
        } else if (scenario == 4) // <cmpr> and <cond>
        {
            c.print();
            System.out.print(" and ");
            co.print();
        }
    }

    boolean execute()
    {
        if (scenario == 0) // <cmpr>
        {
            return c.execute();    
        } else if (scenario == 1) // not <cond>
        {
            return !co.execute();
        } else if (scenario == 2) // [ <cond> ]
        {
            return co.execute(); 
        } else if (scenario == 3) // <cmpr> or <cond>
        {
            return c.execute() || co.execute();
        } else if (scenario == 4) // <cmpr> and <cond>
        {   
            return c.execute() && co.execute();
        }

        return false; // wont run
    }
}
