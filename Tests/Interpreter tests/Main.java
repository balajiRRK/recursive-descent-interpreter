class Main {
    public static void main(String[] args)
    {
        Scanner s = new Scanner(args[0]);
        Parser.scanner = s;

        Procedure p = new Procedure();
        
        p.parse();

        Memory.s = new Scanner(args[1]);
        p.execute();
    }
}