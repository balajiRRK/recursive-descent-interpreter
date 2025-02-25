import java.util.HashMap;

class If {
    
    Cond c;
    StmtSeq ss, ss2;
    int scenario;

    void parse()
    {
        Parser.expectedToken(Core.IF);
        Parser.scanner.nextToken();

        c = new Cond();
        c.parse();

        Parser.expectedToken(Core.THEN);
        Parser.scanner.nextToken();

        Parser.stackOfMaps.push(new HashMap<String, String>());

        ss = new StmtSeq();
        ss.parse();

        Parser.stackOfMaps.pop();

        if (Parser.scanner.currentToken() == Core.END)
        {
            Parser.expectedToken(Core.END);
            Parser.scanner.nextToken();
            scenario = 0; // if <cond> then <stmt-seq> end 
        } else if (Parser.scanner.currentToken == Core.ELSE)
        {
            Parser.expectedToken(Core.ELSE);
            Parser.scanner.nextToken();

            Parser.stackOfMaps.push(new HashMap<String, String>());

            ss2 = new StmtSeq();
            ss2.parse();

            Parser.stackOfMaps.pop();

            Parser.expectedToken(Core.END);
            Parser.scanner.nextToken();
            scenario = 1; // if <cond> then <stmt-seq> else <stmt-seq> end
        }
    }

    void print()
    {
        System.out.print("if ");
        c.print();
        System.out.println("then");
        ss.print();

        if (scenario == 0) // if <cond> then <stmt-seq> end
        {
            System.out.println("end");
        } else if (scenario == 1) // if <cond> then <stmt-seq> else <stmt-seq> end
        {
            System.out.println("else");

            ss2.print();

            System.out.println("end");
        }
    }

    void execute()
    {
        
        if (c.execute())
        {
            // new scope
            Memory.push();
            ss.execute();
            Memory.pop();
        } else
        {
            if (scenario == 0)
            {
                // no else
            } else if (scenario == 1)
            {
                // new else scope
                Memory.push();
                ss2.execute(); // else
                Memory.pop();
            }
        }
    }
}
