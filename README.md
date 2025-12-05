# Description
An interpreter for a custom pseudo-code-esque language built using recursive descent parsing to execute the code line-by-line including a tokenizer (lexical analysis), parsing (syntax analysis along with semantic analysis), parse tree generator, and interpretation (executor). 

# How to run and test

There is continuous integration automatic testing implemented through GitHub Actions on this repo which can be used to run and test the code on the cloud.

However, you can also run the test cases locally by running the .sh scripts using `./tester.sh` within a test directory for each type of test (Tokenizer, Interpreter, Parser, or Procedure) to individually test each key component of the interpreter.

1. Clone this repository:
  ```bash
  git clone https://github.com/balajiRRK/recursive-descent-interpreter.git
  cd recursive-descent-interpreter
  ```

2. Navigate to the `Tests` directory:
  ```bash
  cd /Tests
  ```

3. Navigate to a specific test sub-directory (Tokenizer, Interpreter, Parser, or Procedure):
  ```bash
  cd /Interpreter tests
  ``` 
  OR
  ```bash
  cd /Parser tests
  ``` 
  OR
  ```bash
  cd /Procedure Tests
  ``` 
  OR
  ```bash
  cd /Tokenizer tests
  ``` 

4. Run the .sh script in your chosen directory (run in any Unix-like environment):
  ```
  ./tester.sh
  ```
