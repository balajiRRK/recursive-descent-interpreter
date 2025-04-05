class DeclSeq {

    Decl d;
    DeclSeq ds;
    Function f;

    void parse()
    {
        if (Parser.scanner.currentToken() == Core.INTEGER || Parser.scanner.currentToken() == Core.OBJECT)
        {
            d = new Decl();
            d.parse();

            ds = new DeclSeq();
            ds.parse();
        } else if (Parser.scanner.currentToken() == Core.PROCEDURE)
        {
            f = new Function();
            f.parse();

            ds = new DeclSeq();
            ds.parse();
        }
    }    

    void print()
    {
        if (d != null) 
        {
            System.out.print("\t");
            d.print();
        } 
        
        if (ds != null)
        {
            ds.print();
        }
    }

    void execute()
    {
        if (d != null) 
        {
            d.execute();
        } 
        
        if (f != null) {
            f.execute();
        }

        if (ds != null)
        {
            ds.execute();
        }

    }
}