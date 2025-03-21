class DeclObj {

    String id;

    void parse()
    {
        Parser.expectedToken(Core.OBJECT);
        Parser.scanner.nextToken();

        Parser.expectedToken(Core.ID);
        id = Parser.scanner.getId();
        Parser.scanner.nextToken();

        if (Parser.stackOfMaps.size() != 0)
        {
            Parser.checkIfDoublyDeclared(id);
            Parser.stackOfMaps.peek().put(id, "object");
        }

        Parser.expectedToken(Core.SEMICOLON);
        Parser.scanner.nextToken();
    }

    void print()
    {
        if (id != null)
        {
            System.out.println("object " + id + ";");
        }   
    }

    void execute()
    {
        Memory.addToMap(id, new Variable()); // new map
    }
}