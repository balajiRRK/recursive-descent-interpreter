class Decl {

    DeclInt dI;
    DeclObj dO;

    void parse()
    {
        if (Parser.scanner.currentToken() == Core.INTEGER)
        {
            dI = new DeclInt();
            dI.parse();
        } else if (Parser.scanner.currentToken() == Core.OBJECT)
        {
            dO = new DeclObj();
            dO.parse();
        }
    }

    void print()
    {
        if (dI != null)
        {
            dI.print();
        } else if (dO != null)
        {
            dO.print();
        }
    }

    void execute()
    {
        if (dI != null)
        {
            dI.execute();
        } else if (dO != null)
        {
            dO.execute();
        }
    }
}