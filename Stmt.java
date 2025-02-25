class Stmt {
    
    Assign a;
    If i;
    Loop l;
    Print p;
    Read r;
    Decl d;

    void parse()
    {
        if (Parser.scanner.currentToken() == Core.ID)
        {
            a = new Assign();
            a.parse();
        } else if (Parser.scanner.currentToken() == Core.IF)
        {
            i = new If();
            i.parse();
        } else if (Parser.scanner.currentToken() == Core.FOR) // loop
        {
            l = new Loop();
            l.parse();
        } else if (Parser.scanner.currentToken() == Core.PRINT)
        {
            p = new Print();
            p.parse();
        } else if (Parser.scanner.currentToken() == Core.READ)
        {
            r = new Read();
            r.parse();
        } else if (Parser.scanner.currentToken() == Core.INTEGER || Parser.scanner.currentToken() == Core.OBJECT)
        {
            d = new Decl();
            d.parse();
        } else
        {
            System.out.println("ERROR: Not valid statement " + Parser.scanner.currentToken());
            System.exit(0);
        }
    }

    void print()
    {
        if (a != null)
        {
            a.print();
        } else if (i != null)
        {
            i.print();
        } else if (l != null)
        {
            l.print();
        } else if (p != null)
        {
            p.print();
        } else if (r != null)
        {
            r.print();
        } else if (d != null)
        {
            d.print();
        }
    }

    void execute()
    {
        if (a != null)
        {
            a.execute();
        } else if (i != null)
        {
            i.execute();
        } else if (l != null)
        {
            l.execute();
        } else if (p != null)
        {
            p.execute();
        } else if (r != null)
        {
            r.execute();
        } else if (d != null)
        {
            d.execute();
        }
    }
}
