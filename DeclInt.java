class DeclInt {
    
    String id;
    
    void parse()
    {
        Parser.expectedToken(Core.INTEGER);
        Parser.scanner.nextToken();

        Parser.expectedToken(Core.ID);
        id = Parser.scanner.getId();
        Parser.scanner.nextToken();

        if (Parser.stackOfMaps.size() != 0)
        {
            Parser.checkIfDoublyDeclared(id);
            Parser.stackOfMaps.peek().put(id, "integer");
        }   

        Parser.expectedToken(Core.SEMICOLON);
        Parser.scanner.nextToken();
    }

    void print()
    {
        if (id != null)
        {
            System.out.println("integer " + id + ";");
        }
    }

    void execute()
    {
        // default val of 0
        Memory.addToMap(id, new Variable(0));
    }
}