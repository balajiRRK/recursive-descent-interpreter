import java.util.ArrayList;

class Parameters {
    
    Parameters p;
    ArrayList<String> idList;
    String id;

    ArrayList<String> parse()
    {
        idList = new ArrayList<String>();

        Parser.expectedToken(Core.ID);
        id = Parser.scanner.getId();
        idList.add(id);
        Parser.stackOfMaps.peek().put(id, "object");
        Parser.scanner.nextToken();

        if (Parser.scanner.currentToken() == Core.COMMA)
        {
            Parser.expectedToken(Core.COMMA);
            Parser.scanner.nextToken();

            p = new Parameters();
            idList.addAll(p.parse());
        }

        Memory.checkFormalParamDuplicateName(idList);

        return idList;
    }

    void print()
    {
        System.out.print(id);

        if (p != null) {
            System.out.print(", ");
            p.print();
        }
    }

    ArrayList<String> execute()
    {
        return idList;
    }
}