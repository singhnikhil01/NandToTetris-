package SyntaxAnalyzer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class CompilationEngine {

    private PrintWriter printWriter;
    private PrintWriter tokenPrintWriter;
    private JackTokenizer tokenizer;

   
    public CompilationEngine(File inFile, File outFile, File outTokenFile) {

        try {

            tokenizer = new JackTokenizer(inFile);
            printWriter = new PrintWriter(outFile);
            tokenPrintWriter = new PrintWriter(outTokenFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Compiles a type
     */
    private void compileType(){

        tokenizer.advance();

        boolean isType = false;

        if (tokenizer.tokenType() == JackTokenizer.KEYWORD && (tokenizer.keyWord() == JackTokenizer.INT || tokenizer.keyWord() == JackTokenizer.CHAR || tokenizer.keyWord() == JackTokenizer.BOOLEAN)){
            printWriter.print("<keyword>" + tokenizer.getCurrentToken() + "</keyword>\n");
            tokenPrintWriter.print("<keyword>" + tokenizer.getCurrentToken() + "</keyword>\n");
            isType = true;
        }

        if (tokenizer.tokenType() == JackTokenizer.IDENTIFIER){
            printWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");
            tokenPrintWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");
            isType = true;
        }

        if (!isType) error("in|char|boolean|className");
    }

    /**
     * Complies a complete class
     * class: 'class' className '{' classVarDec* subroutineDec* '}'
     */
    public void compileClass(){

        //'class'
        tokenizer.advance();

        if (tokenizer.tokenType() != JackTokenizer.KEYWORD || tokenizer.keyWord() != JackTokenizer.CLASS){
            error("class");
        }

        printWriter.print("<class>\n");
        tokenPrintWriter.print("<tokens>\n");

        printWriter.print("<keyword>class</keyword>\n");
        tokenPrintWriter.print("<keyword>class</keyword>\n");

        //className
        tokenizer.advance();

        if (tokenizer.tokenType() != JackTokenizer.IDENTIFIER){
            error("className");
        }

        printWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");
        tokenPrintWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");

        //'{'
        requireSymbol('{');

        //classVarDec* subroutineDec*
        compileClassVarDec();
        compileSubroutine();

        //'}'
        requireSymbol('}');

        if (tokenizer.hasMoreTokens()){
            throw new IllegalStateException("Unexpected tokens");
        }

        tokenPrintWriter.print("</tokens>\n");
        printWriter.print("</class>\n");

        //save file
        printWriter.close();
        tokenPrintWriter.close();

    }

    /**
     * Compiles a static declaration or a field declaration
     * classVarDec ('static'|'field') type varName (','varNAme)* ';'
     */
    private void compileClassVarDec(){

        //first determine whether there is a classVarDec, nextToken is } or start subroutineDec
        tokenizer.advance();

        //next is a '}'
        if (tokenizer.tokenType() == JackTokenizer.SYMBOL && tokenizer.symbol() == '}'){
            tokenizer.pointerBack();
            return;
        }

        //next is start subroutineDec or classVarDec, both start with keyword
        if (tokenizer.tokenType() != JackTokenizer.KEYWORD){
            error("Keywords");
        }

        //next is subroutineDec
        if (tokenizer.keyWord() == JackTokenizer.CONSTRUCTOR || tokenizer.keyWord() == JackTokenizer.FUNCTION || tokenizer.keyWord() == JackTokenizer.METHOD){
            tokenizer.pointerBack();
            return;
        }

        printWriter.print("<classVarDec>\n");

        //classVarDec exists
        if (tokenizer.keyWord() != JackTokenizer.STATIC && tokenizer.keyWord() != JackTokenizer.FIELD){
            error("static or field");
        }

        printWriter.print("<keyword>" + tokenizer.getCurrentToken() + "</keyword>\n");
        tokenPrintWriter.print("<keyword>" + tokenizer.getCurrentToken() + "</keyword>\n");

        //type
        compileType();

        //at least one varName
        //boolean varNamesDone = false;

        do {

            //varName
            tokenizer.advance();
            if (tokenizer.tokenType() != JackTokenizer.IDENTIFIER){
                error("identifier");
            }

            printWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");
            tokenPrintWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");

            //',' or ';'
            tokenizer.advance();

            if (tokenizer.tokenType() != JackTokenizer.SYMBOL || (tokenizer.symbol() != ',' && tokenizer.symbol() != ';')){
                error("',' or ';'");
            }

            if (tokenizer.symbol() == ','){

                printWriter.print("<symbol>,</symbol>\n");
                tokenPrintWriter.print("<symbol>,</symbol>\n");

            }else {

                printWriter.print("<symbol>;</symbol>\n");
                tokenPrintWriter.print("<symbol>;</symbol>\n");
                break;
            }


        }while(true);

        printWriter.print("</classVarDec>\n");

        compileClassVarDec();
    }

    /**
     * Compiles a complete method function or constructor
     */
    private void compileSubroutine(){

        //determine whether there is a subroutine, next can be a '}'
        tokenizer.advance();

        //next is a '}'
        if (tokenizer.tokenType() == JackTokenizer.SYMBOL && tokenizer.symbol() == '}'){
            tokenizer.pointerBack();
            return;
        }

        //start of a subroutine
        if (tokenizer.tokenType() != JackTokenizer.KEYWORD || (tokenizer.keyWord() != JackTokenizer.CONSTRUCTOR && tokenizer.keyWord() != JackTokenizer.FUNCTION && tokenizer.keyWord() != JackTokenizer.METHOD)){
            error("constructor|function|method");
        }

        printWriter.print("<subroutineDec>\n");

        printWriter.print("<keyword>" + tokenizer.getCurrentToken() + "</keyword>\n");
        tokenPrintWriter.print("<keyword>" + tokenizer.getCurrentToken() + "</keyword>\n");

        //'void' or type
        tokenizer.advance();
        if (tokenizer.tokenType() == JackTokenizer.KEYWORD && tokenizer.keyWord() == JackTokenizer.VOID){
            printWriter.print("<keyword>void</keyword>\n");
            tokenPrintWriter.print("<keyword>void</keyword>\n");
        }else {
            tokenizer.pointerBack();
            compileType();
        }

        //subroutineName which is a identifier
        tokenizer.advance();
        if (tokenizer.tokenType() != JackTokenizer.IDENTIFIER){
            error("subroutineName");
        }

        printWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");
        tokenPrintWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");

        //'('
        requireSymbol('(');

        //parameterList
        printWriter.print("<parameterList>\n");
        compileParameterList();
        printWriter.print("</parameterList>\n");

        //')'
        requireSymbol(')');

        //subroutineBody
        compileSubroutineBody();

        printWriter.print("</subroutineDec>\n");

        compileSubroutine();

    }

    /**
     * Compiles the body of a subroutine
     * '{'  varDec* statements '}'
     */
    private void compileSubroutineBody(){
        printWriter.print("<subroutineBody>\n");
        //'{'
        requireSymbol('{');
        //varDec*
        compileVarDec();
        //statements
        printWriter.print("<statements>\n");
        compileStatement();
        printWriter.print("</statements>\n");
        //'}'
        requireSymbol('}');
        printWriter.print("</subroutineBody>\n");
    }

    /**
     * Compiles a single statement
     */
    private void compileStatement(){

        //determine whether there is a statementnext can be a '}'
        tokenizer.advance();

        //next is a '}'
        if (tokenizer.tokenType() == JackTokenizer.SYMBOL && tokenizer.symbol() == '}'){
            tokenizer.pointerBack();
            return;
        }

        //next is 'let'|'if'|'while'|'do'|'return'
        if (tokenizer.tokenType() != JackTokenizer.KEYWORD){
            error("keyword");
        }else {
            switch (tokenizer.keyWord()){
                case JackTokenizer.LET:compileLet();break;
                case JackTokenizer.IF:compileIf();break;
                case JackTokenizer.WHILE:compilesWhile();break;
                case JackTokenizer.DO:compileDo();break;
                case JackTokenizer.RETURN:compileReturn();break;
                default:error("'let'|'if'|'while'|'do'|'return'");
            }
        }

        compileStatement();
    }

    /**
     * Compiles a (possibly empty) parameter list
     * not including the enclosing "()"
     * ((type varName)(',' type varName)*)?
     */
    private void compileParameterList(){

        //check if there is parameterList, if next token is ')' than go back
        tokenizer.advance();
        if (tokenizer.tokenType() == JackTokenizer.SYMBOL && tokenizer.symbol() == ')'){
            tokenizer.pointerBack();
            return;
        }

        //there is parameter, at least one varName
        tokenizer.pointerBack();
        do {
            //type
            compileType();

            //varName
            tokenizer.advance();
            if (tokenizer.tokenType() != JackTokenizer.IDENTIFIER){
                error("identifier");
            }
             printWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");
            tokenPrintWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");

            //',' or ')'
            tokenizer.advance();
            if (tokenizer.tokenType() != JackTokenizer.SYMBOL || (tokenizer.symbol() != ',' && tokenizer.symbol() != ')')){
                error("',' or ')'");
            }

            if (tokenizer.symbol() == ','){
                printWriter.print("<symbol>,</symbol>\n");
                tokenPrintWriter.print("<symbol>,</symbol>\n");
            }else {
                tokenizer.pointerBack();
                break;
            }

        }while(true);

    }

    /**
     * Compiles a var declaration
     * 'var' type varName (',' varName)*;
     */
    private void compileVarDec(){

        //determine if there is a varDec

        tokenizer.advance();
        //no 'var' go back
        if (tokenizer.tokenType() != JackTokenizer.KEYWORD || tokenizer.keyWord() != JackTokenizer.VAR){
            tokenizer.pointerBack();
            return;
        }

        printWriter.print("<varDec>\n");

        printWriter.print("<keyword>var</keyword>\n");
        tokenPrintWriter.print("<keyword>var</keyword>\n");

        //type
        compileType();

        //at least one varName
        boolean varNamesDone = false;

        do {

            //varName
            tokenizer.advance();

            if (tokenizer.tokenType() != JackTokenizer.IDENTIFIER){
                error("identifier");
            }

            printWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");
            tokenPrintWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");

            //',' or ';'
            tokenizer.advance();

            if (tokenizer.tokenType() != JackTokenizer.SYMBOL || (tokenizer.symbol() != ',' && tokenizer.symbol() != ';')){
                error("',' or ';'");
            }

            if (tokenizer.symbol() == ','){

                printWriter.print("<symbol>,</symbol>\n");
                tokenPrintWriter.print("<symbol>,</symbol>\n");

            }else {

                printWriter.print("<symbol>;</symbol>\n");
                tokenPrintWriter.print("<symbol>;</symbol>\n");
                break;
            }


        }while(true);

        printWriter.print("</varDec>\n");

        compileVarDec();

    }

    /**
     * Compiles a do statement
     * 'do' subroutineCall ';'
     */
    private void compileDo(){
        printWriter.print("<doStatement>\n");

        printWriter.print("<keyword>do</keyword>\n");
        tokenPrintWriter.print("<keyword>do</keyword>\n");
        //subroutineCall
        compileSubroutineCall();
        //';'
        requireSymbol(';');

        printWriter.print("</doStatement>\n");
    }

    /**
     * Compiles a let statement
     * 'let' varName ('[' ']')? '=' expression ';'
     */
    private void compileLet(){

        printWriter.print("<letStatement>\n");

        printWriter.print("<keyword>let</keyword>\n");
        tokenPrintWriter.print("<keyword>let</keyword>\n");

        //varName
        tokenizer.advance();
        if (tokenizer.tokenType() != JackTokenizer.IDENTIFIER){
            error("varName");
        }

        printWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");
        tokenPrintWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");

        //'[' or '='
        tokenizer.advance();
        if (tokenizer.tokenType() != JackTokenizer.SYMBOL || (tokenizer.symbol() != '[' && tokenizer.symbol() != '=')){
            error("'['|'='");
        }

        boolean expExist = false;

        //'[' expression ']'
        if (tokenizer.symbol() == '['){

            expExist = true;

            printWriter.print("<symbol>[</symbol>\n");
            tokenPrintWriter.print("<symbol>[</symbol>\n");

            compileExpression();

            //']'
            tokenizer.advance();
            if (tokenizer.tokenType() == JackTokenizer.SYMBOL && tokenizer.symbol() == ']'){
                printWriter.print("<symbol>]</symbol>\n");
                tokenPrintWriter.print("<symbol>]</symbol>\n");
            }else {
                error("']'");
            }
        }

        if (expExist) tokenizer.advance();

        //'='
        printWriter.print("<symbol>=</symbol>\n");
        tokenPrintWriter.print("<symbol>=</symbol>\n");

        //expression
        compileExpression();

        //';'
        requireSymbol(';');

        printWriter.print("</letStatement>\n");
    }

    /**
     * Compiles a while statement
     * 'while' '(' expression ')' '{' statements '}'
     */
    private void compilesWhile(){
        printWriter.print("<whileStatement>\n");

        printWriter.print("<keyword>while</keyword>\n");
        tokenPrintWriter.print("<keyword>while</keyword>\n");
        //'('
        requireSymbol('(');
        //expression
        compileExpression();
        //')'
        requireSymbol(')');
        //'{'
        requireSymbol('{');
        //statements
        printWriter.print("<statements>\n");
        compileStatement();
        printWriter.print("</statements>\n");
        //'}'
        requireSymbol('}');

        printWriter.print("</whileStatement>\n");
    }

    /**
     * Compiles a return statement
     * ‘return’ expression? ';'
     */
    private void compileReturn(){
        printWriter.print("<returnStatement>\n");

        printWriter.print("<keyword>return</keyword>\n");
        tokenPrintWriter.print("<keyword>return</keyword>\n");

        //check if there is any expression
        tokenizer.advance();
        //no expression
        if (tokenizer.tokenType() == JackTokenizer.SYMBOL && tokenizer.symbol() == ';'){
            printWriter.print("<symbol>;</symbol>\n");
            tokenPrintWriter.print("<symbol>;</symbol>\n");
            printWriter.print("</returnStatement>\n");
            return;
        }

        tokenizer.pointerBack();
        //expression
        compileExpression();
        //';'
        requireSymbol(';');

        printWriter.print("</returnStatement>\n");
    }

    /**
     * Compiles an if statement
     * possibly with a trailing else clause
     * 'if' '(' expression ')' '{' statements '}' ('else' '{' statements '}')?
     */
    private void compileIf(){
        printWriter.print("<ifStatement>\n");

        printWriter.print("<keyword>if</keyword>\n");
        tokenPrintWriter.print("<keyword>if</keyword>\n");
        //'('
        requireSymbol('(');
        //expression
        compileExpression();
        //')'
        requireSymbol(')');
        //'{'
        requireSymbol('{');
        //statements
        printWriter.print("<statements>\n");
        compileStatement();
        printWriter.print("</statements>\n");
        //'}'
        requireSymbol('}');

        //check if there is 'else'
        tokenizer.advance();
        if (tokenizer.tokenType() == JackTokenizer.KEYWORD && tokenizer.keyWord() == JackTokenizer.ELSE){
            printWriter.print("<keyword>else</keyword>\n");
            tokenPrintWriter.print("<keyword>else</keyword>\n");
            //'{'
            requireSymbol('{');
            //statements
            printWriter.print("<statements>\n");
            compileStatement();
            printWriter.print("</statements>\n");
            //'}'
            requireSymbol('}');
        }else {
            tokenizer.pointerBack();
        }

        printWriter.print("</ifStatement>\n");

    }

    /**
     * Compiles a term.
     * This routine is faced with a slight difficulty when trying to decide between some of the alternative parsing rules.
     * Specifically, if the current token is an identifier
     *      the routine must distinguish between a variable, an array entry and a subroutine call
     * A single look-ahead token, which may be one of "[" "(" "." suffices to distinguish between the three possibilities
     * Any other token is not part of this term and should not be advanced over
     *
     * integerConstant|stringConstant|keywordConstant|varName|varName '[' expression ']'|subroutineCall|
     * '(' expression ')'|unaryOp term
     */
    private void compileTerm(){

        printWriter.print("<term>\n");

        tokenizer.advance();
        //check if it is an identifier
        if (tokenizer.tokenType() == JackTokenizer.IDENTIFIER){
            //varName|varName '[' expression ']'|subroutineCall
            String tempId = tokenizer.identifier();

            tokenizer.advance();
            if (tokenizer.tokenType() == JackTokenizer.SYMBOL && tokenizer.symbol() == '['){
                printWriter.print("<identifier>" + tempId + "</identifier>\n");
                tokenPrintWriter.print("<identifier>" + tempId + "</identifier>\n");
                //this is an array entry
                printWriter.print("<symbol>[</symbol>\n");
                tokenPrintWriter.print("<symbol>[</symbol>\n");
                //expression
                compileExpression();
                //']'
                requireSymbol(']');
            }else if (tokenizer.tokenType() == JackTokenizer.SYMBOL && (tokenizer.symbol() == '(' || tokenizer.symbol() == '.')){
                //this is a subroutineCall
                tokenizer.pointerBack();tokenizer.pointerBack();
                compileSubroutineCall();
            }else {
                printWriter.print("<identifier>" + tempId + "</identifier>\n");
                tokenPrintWriter.print("<identifier>" + tempId + "</identifier>\n");
                //this is varName
                tokenizer.pointerBack();
            }

        }else{
            //integerConstant|stringConstant|keywordConstant|'(' expression ')'|unaryOp term
            if (tokenizer.tokenType() == JackTokenizer.INT_CONST){
                printWriter.print("<integerConstant>" + tokenizer.intVal() + "</integerConstant>\n");
                tokenPrintWriter.print("<integerConstant>" + tokenizer.intVal() + "</integerConstant>\n");
            }else if (tokenizer.tokenType() == JackTokenizer.STRING_CONST){
                printWriter.print("<stringConstant>" + tokenizer.stringVal() + "</stringConstant>\n");
                tokenPrintWriter.print("<stringConstant>" + tokenizer.stringVal() + "</stringConstant>\n");
            }else if(tokenizer.tokenType() == JackTokenizer.KEYWORD &&
                            (tokenizer.keyWord() == JackTokenizer.TRUE ||
                            tokenizer.keyWord() == JackTokenizer.FALSE ||
                            tokenizer.keyWord() == JackTokenizer.NULL ||
                            tokenizer.keyWord() == JackTokenizer.THIS)){
                    printWriter.print("<keyword>" + tokenizer.getCurrentToken() + "</keyword>\n");
                    tokenPrintWriter.print("<keyword>" + tokenizer.getCurrentToken() + "</keyword>\n");
            }else if (tokenizer.tokenType() == JackTokenizer.SYMBOL && tokenizer.symbol() == '('){
                printWriter.print("<symbol>(</symbol>\n");
                tokenPrintWriter.print("<symbol>(</symbol>\n");
                //expression
                compileExpression();
                //')'
                requireSymbol(')');
            }else if (tokenizer.tokenType() == JackTokenizer.SYMBOL && (tokenizer.symbol() == '-' || tokenizer.symbol() == '~')){
                printWriter.print("<symbol>" + tokenizer.symbol() + "</symbol>\n");
                tokenPrintWriter.print("<symbol>" + tokenizer.symbol() + "</symbol>\n");
                //term
                compileTerm();
            }else {
                error("integerConstant|stringConstant|keywordConstant|'(' expression ')'|unaryOp term");
            }
        }

        printWriter.print("</term>\n");
    }

    /**
     * Compiles a subroutine call
     * subroutineName '(' expressionList ')' | (className|varName) '.' subroutineName '(' expressionList ')'
     */
    private void compileSubroutineCall(){

        tokenizer.advance();
        if (tokenizer.tokenType() != JackTokenizer.IDENTIFIER){
            error("identifier");
        }

        printWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");
        tokenPrintWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");

        tokenizer.advance();
        if (tokenizer.tokenType() == JackTokenizer.SYMBOL && tokenizer.symbol() == '('){
            //'(' expressionList ')'
            printWriter.print("<symbol>(</symbol>\n");
            tokenPrintWriter.print("<symbol>(</symbol>\n");
            //expressionList
            printWriter.print("<expressionList>\n");
            compileExpressionList();
            printWriter.print("</expressionList>\n");
            //')'
            requireSymbol(')');
        }else if (tokenizer.tokenType() == JackTokenizer.SYMBOL && tokenizer.symbol() == '.'){
            //(className|varName) '.' subroutineName '(' expressionList ')'
            printWriter.print("<symbol>.</symbol>\n");
            tokenPrintWriter.print("<symbol>.</symbol>\n");
            //subroutineName
            tokenizer.advance();
            if (tokenizer.tokenType() != JackTokenizer.IDENTIFIER){
                error("identifier");
            }
            printWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");
            tokenPrintWriter.print("<identifier>" + tokenizer.identifier() + "</identifier>\n");
            //'('
            requireSymbol('(');
            //expressionList
            printWriter.print("<expressionList>\n");
            compileExpressionList();
            printWriter.print("</expressionList>\n");
            //')'
            requireSymbol(')');
        }else {
            error("'('|'.'");
        }
    }

    /**
     * Compiles an expression
     * term (op term)*
     */
    private void compileExpression(){
        printWriter.print("<expression>\n");

        //term
        compileTerm();
        //(op term)*
        do {
            tokenizer.advance();
            //op
            if (tokenizer.tokenType() == JackTokenizer.SYMBOL && tokenizer.isOp()){
                if (tokenizer.symbol() == '>'){
                    printWriter.print("<symbol>&gt;</symbol>\n");
                    tokenPrintWriter.print("<symbol>&gt;</symbol>\n");
                }else if (tokenizer.symbol() == '<'){
                    printWriter.print("<symbol>&lt;</symbol>\n");
                    tokenPrintWriter.print("<symbol>&lt;</symbol>\n");
                }else if (tokenizer.symbol() == '&') {
                    printWriter.print("<symbol>&amp;</symbol>\n");
                    tokenPrintWriter.print("<symbol>&amp;</symbol>\n");
                }else {
                    printWriter.print("<symbol>" + tokenizer.symbol() + "</symbol>\n");
                    tokenPrintWriter.print("<symbol>" + tokenizer.symbol() + "</symbol>\n");
                }
                //term
                compileTerm();
            }else {
                tokenizer.pointerBack();
                break;
            }

        }while (true);

        printWriter.print("</expression>\n");
    }

    /**
     * Compiles a (possibly empty) comma-separated list of expressions
     * (expression(','expression)*)?
     */
    private void compileExpressionList(){
        tokenizer.advance();
        //determine if there is any expression, if next is ')' then no
        if (tokenizer.tokenType() == JackTokenizer.SYMBOL && tokenizer.symbol() == ')'){
            tokenizer.pointerBack();
        }else {

            tokenizer.pointerBack();
            //expression
            compileExpression();
            //(','expression)*
            do {
                tokenizer.advance();
                if (tokenizer.tokenType() == JackTokenizer.SYMBOL && tokenizer.symbol() == ','){
                    printWriter.print("<symbol>,</symbol>\n");
                    tokenPrintWriter.print("<symbol>,</symbol>\n");
                    //expression
                    compileExpression();
                }else {
                    tokenizer.pointerBack();
                    break;
                }

            }while (true);

        }
    }

    /**
     * throw an exception to report errors
     * @param val
     */
    private void error(String val){
        throw new IllegalStateException("Expected token missing : " + val + " Current token:" + tokenizer.getCurrentToken());
    }

    /**
     * require symbol when we know there must be such symbol
     * @param symbol
     */
    private void requireSymbol(char symbol){
        tokenizer.advance();
        if (tokenizer.tokenType() == JackTokenizer.SYMBOL && tokenizer.symbol() == symbol){
            printWriter.print("<symbol>" + symbol + "</symbol>\n");
            tokenPrintWriter.print("<symbol>" + symbol + "</symbol>\n");
        }else {
            error("'" + symbol + "'");
        }
    }
}
