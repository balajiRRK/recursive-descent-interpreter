class Term {

    Factor f;
    Term t;
    int scenario;

    void parse()
    {
        f = new Factor();
        f.parse();

        scenario = 0; // <factor>

        if (Parser.scanner.currentToken() == Core.MULTIPLY)
        {
            Parser.expectedToken(Core.MULTIPLY);
            Parser.scanner.nextToken();

            t = new Term();
            t.parse();

            scenario = 1; // <factor> * <term>
        } else if (Parser.scanner.currentToken() == Core.DIVIDE)
        {
            Parser.expectedToken(Core.DIVIDE);
            Parser.scanner.nextToken();

            t = new Term();
            t.parse();

            scenario = 2; // <factor> / <term>
        }
    }

    void print()
    {
        if (scenario == 0) // <factor> 
        {
            f.print();
        } else if (scenario == 1) // <factor> * <term>
        {
            f.print();
            System.out.print(" * ");
            t.print();
        } else if (scenario == 2) // <factor> / <term>
        {
            f.print();
            System.out.print(" / ");
            t.print();
        }
    }

    int execute()
    {
        int value = f.execute();
        if (scenario == 1) // scenario 0 is covered by f.execute();
        {
            value *= t.execute();
        } else if (scenario == 2)
        {
            // need to check if dividing by 0 and throw error somehow
            value /= t.execute();
        }  
        
        return value;
    }
}