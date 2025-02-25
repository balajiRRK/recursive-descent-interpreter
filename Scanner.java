import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Scanner {

    FileReader fileReader;
    BufferedReader reader;

    Core currentToken;
    String currentTokenID;
    int currentTokenVal;
    String currentTokenString;

    // Initialize the Scanner
    Scanner(String filename) {
        try {
            fileReader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        reader = new BufferedReader(fileReader);
        nextToken();
    }

    // Advance to the next token
    public void nextToken() {
        int c;
        String token = "";

        try {
            // find initial char of token
            while (Character.isWhitespace(c = reader.read()));

            if (c == -1)
            {
                currentToken = Core.EOS;
            // if initial char of token is letter then continue finding rest of the letters
            } else if (Character.isLetter(c))
            {
                token += (char) c;
                reader.mark(1);
                while ((c = reader.read()) != -1)
                {   
                    if (Character.isLetter(c) || Character.isDigit(c)) 
                    {
                        token += (char) c;
                    } else {
                        // reset so that we don't skip the non-digit char that we read
                        // since nextToken() starts by reading in a new initial char so it would skip 
                        // the non-digit char that we read
                        reader.reset();
                        break;
                    }
                    // mark after going to each new character
                    reader.mark(1);
                }

                if (token.equals("and"))
                {
                    currentToken = Core.AND;
                } else if (token.equals("begin"))
                {
                    currentToken = Core.BEGIN;
                } else if (token.equals("case"))
                {
                    currentToken = Core.CASE;
                } else if (token.equals("do"))
                {
                    currentToken = Core.DO;
                } else if (token.equals("else"))
                {
                    currentToken = Core.ELSE;
                } else if (token.equals("end"))
                {
                    currentToken = Core.END;
                } else if (token.equals("for"))
                {
                    currentToken = Core.FOR;
                } else if (token.equals("if"))
                {
                    currentToken = Core.IF;
                } else if (token.equals("in"))
                {
                    currentToken = Core.IN;
                } else if (token.equals("integer"))
                {
                    currentToken = Core.INTEGER;
                } else if (token.equals("is"))
                {
                    currentToken = Core.IS;
                } else if (token.equals("new"))
                {
                    currentToken = Core.NEW;
                } else if (token.equals("not"))
                {
                    currentToken = Core.NOT;
                } else if (token.equals("object"))
                {
                    currentToken = Core.OBJECT;
                } else if (token.equals("or"))
                {
                    currentToken = Core.OR;
                } else if (token.equals("print"))
                {
                    currentToken = Core.PRINT;
                } else if (token.equals("procedure"))
                {
                    currentToken = Core.PROCEDURE;
                } else if (token.equals("read"))
                {
                    currentToken = Core.READ;
                } else if (token.equals("return"))
                {
                    currentToken = Core.RETURN;
                } else if (token.equals("then"))
                { 
                    currentToken = Core.THEN;
                } else
                {
                    currentToken = Core.ID;
                    currentTokenID = token;
                }

            // if initial char of token is digit continue finding rest of the number
            } else if (Character.isDigit(c))
            {
                if (c == '0') {
                    reader.mark(1);
                    if (reader.read() == '0')
                    {
                        currentToken = Core.ERROR;
                        System.out.println("\nERROR: Cannot have leading 0s");
                        return;
                    } else {
                        reader.reset();
                    }
                }

                token += (char) c;
                reader.mark(1);
                while ((c = reader.read()) != -1)
                {
                    if (Character.isDigit(c)) {
                        token += (char) c;
                    } else {
                        // reset so that we don't skip the non-digit char that we read
                        // since nextToken() starts by reading in a new initial char so it would skip 
                        // the non-digit char that we read
                        reader.reset();
                        break;
                    }
                    // mark after going to each new character
                    reader.mark(1);
                }

                int constNumber = Integer.parseInt(token);
                if (constNumber > 1000003)
                {
                    currentToken = Core.ERROR;
                    System.out.println("\nERROR: " + constNumber + " <- Constant outside of valid range");
                    return;
                } else
                {
                    currentToken = Core.CONST;
                    currentTokenVal = constNumber;
                }
            // if not char or digit (symbol)
            } else {

                // Symbols
                if (c == '+')
                {
                    currentToken = Core.ADD;
                } else if (c == '-')
                {
                    currentToken = Core.SUBTRACT;
                } else if (c == '*')
                {
                    currentToken = Core.MULTIPLY;
                } else if (c == '/')
                {
                    currentToken = Core.DIVIDE;
                } else if (c == '=')
                {
                    reader.mark(1);
                    // double equal means equal
                    if ((reader.read()) == '=')
                    {
                        currentToken = Core.EQUAL;
                    // single equal means assignment
                    } else
                    {
                        reader.reset();
                        currentToken = Core.ASSIGN;
                    }
                } else if (c == '<')
                {
                    currentToken = Core.LESS;
                } else if (c == ':')
                {
                    currentToken = Core.COLON;
                } else if (c == ';')
                {
                    currentToken = Core.SEMICOLON;
                } else if (c == '.')
                {
                    currentToken = Core.PERIOD;
                } else if (c == ',')
                {
                    currentToken = Core.COMMA;
                } else if (c == '(')
                {
                    currentToken = Core.LPAREN;
                } else if (c == ')')
                {
                    currentToken = Core.RPAREN;
                } else if (c == '[')
                {
                    currentToken = Core.LSQUARE;
                } else if (c == ']')
                {
                    currentToken = Core.RSQUARE;
                } else if (c == '{')
                {
                    currentToken = Core.LCURL;
                } else if (c == '}')
                {
                    currentToken = Core.RCURL;
                } else if (c == '\'') // if char is ' which is the start of a String
                {
                    while ((c = reader.read()) != '\'')
                    {   
                        token += (char) c;
                    }
                    // dont need to reset or mark since we don't care about skipping ' char

                    currentToken = Core.STRING;
                    currentTokenString = token;
                } else
                {
                    currentToken = Core.ERROR;
                    System.out.println("\nERROR: " + (char)c + " <- Not Valid Symbol in Language");
                    return;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Return the current token
    public Core currentToken() {
        return currentToken;
    }

	// Return the identifier string
    public String getId() {
        return currentTokenID;
    }

	// Return the constant value
    public int getConst() {
        return currentTokenVal;
    }
	
	// Return the character string
    public String getString() {
        return currentTokenString;
    }

}
