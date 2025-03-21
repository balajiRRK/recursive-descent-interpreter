class Read {

    String id;

    void parse()
    {
        Parser.expectedToken(Core.READ);
        Parser.scanner.nextToken();

        Parser.expectedToken(Core.LPAREN);
        Parser.scanner.nextToken();

        Parser.expectedToken(Core.ID);
        id = Parser.scanner.getId();
        Parser.scanner.nextToken();

        Parser.checkIfDeclared(id);

        Parser.expectedToken(Core.RPAREN);
        Parser.scanner.nextToken();

        Parser.expectedToken(Core.SEMICOLON);
        Parser.scanner.nextToken();
    }

    void print()
    {
        System.out.println("read ( " + id + " ) ;");
    }

    void execute()
    {
        // set current token value to id val whether its an int or an object
        if (Memory.s.currentToken() != Core.EOS)
        {
            Memory.getSpecificMap(id).get(id).setVal(Memory.s.getConst()); // might not be in top map
        } else if (Memory.s.currentToken() == Core.EOS)
        {
            System.out.println("ERROR: Data file out of values");
            System.exit(0);
        }

        Memory.s.nextToken();
    }
}