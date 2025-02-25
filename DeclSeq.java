class DeclSeq {

    Decl d;
    DeclSeq ds;

    void parse()
    {
        d = new Decl();
        d.parse();
        if (Parser.scanner.currentToken() == Core.INTEGER || Parser.scanner.currentToken() == Core.OBJECT)
        {
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
        
        if (ds != null)
        {
            ds.execute();
        }
    }
}