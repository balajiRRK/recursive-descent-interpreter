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
            int temp = t.execute();
            if (temp != 0)
            {
                value /= temp;
            } else
            {
                System.out.println("ERROR: Cannot divide by 0");
                System.exit(0);
            }
        }  
        
        return value;
    }
}