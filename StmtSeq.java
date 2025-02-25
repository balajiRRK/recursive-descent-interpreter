class StmtSeq {

    Stmt s;
    StmtSeq ss;

    void parse()
    {
        s = new Stmt();
        s.parse();
        if (Parser.scanner.currentToken() != Core.END && Parser.scanner.currentToken() != Core.ELSE)
        {
            ss = new StmtSeq();
            ss.parse();
        }
    }

    void print()
    {
        if (s != null) 
        {
            System.out.print("\t");
            s.print();
        }
        
        if (ss != null)
        {
            ss.print();
        }
    }

    void execute()
    {
        if (s != null) 
        {
            s.execute();
        }
        
        if (ss != null)
        {
            ss.execute();
        }
    }
}
