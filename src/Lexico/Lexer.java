package Lexico;

public class Lexer {

    class Palabra {
        String nomb; int cod;
        
        public Palabra(String n, int c) {
            nomb = n; cod = c;
        }
    }

    private CharReader mLector = null;
    private int mNumeroDeLinea = 1;
    private int mNumeroDeCaracter = 1;
    private String mLexema = ""; //texto del token
    private boolean hayError = false; //para saber desde afuera, si hay algun error
    
    private String Error="Corecto!";
    
    // Constantes (codigos) que representan a cada uno de los tokens que reconoce el Analizador Lexico
   
    public static int TOKEN_PARIZQ = 1001;
    public static int TOKEN_PARDER = 1002;
    public static int TOKEN_IDENT = 1003;
    public static int TOKEN_TRUE = 1004;
    public static int TOKEN_FALSE = 1005;
    public static int TOKEN_CORCHIZQ = 1006;
    public static int TOKEN_CORCHDER = 1007;
    public static int TOKEN_PUNTO = 1008;
    public static int TOKEN_COMA = 1009;
    public static int TOKEN_DPUNTOS = 1010;
    public static int TOKEN_ASTER = 1011;
    public static int TOKEN_MOD = 1012;
    public static int TOKEN_MAYIZQ = 1013;
    public static int TOKEN_MAYDER = 1014;
    public static int TOKEN_IGUALDAD = 1015;
    public static int TOKEN_DISTINTO = 1016;
    public static int TOKEN_AND = 1017;
    public static int TOKEN_OR = 1018;
    public static int TOKEN_ASIGNACION = 1019;
    public static int TOKEN_VAR = 1020;
    public static int TOKEN_WHILE = 1021;
    public static int TOKEN_RECORD = 1022;
    public static int TOKEN_INTEGER = 1023;
    public static int TOKEN_BEGIN = 1024;
    public static int TOKEN_DO = 1025;
    public static int TOKEN_ARRAY = 1026;
    public static int TOKEN_BOOLEAN = 1027;
    public static int TOKEN_PROGRAM = 1028;
    public static int TOKEN_IF = 1029;
    public static int TOKEN_OF = 1030;
    public static int TOKEN_THEN = 1031;
    public static int TOKEN_FUNCTION = 1032;
    public static int TOKEN_END = 1033;
    public static int TOKEN_TYPE = 1034;
    public static int TOKEN_ELSE = 1035;
    public static int TOKEN_PROCEDURE = 1036;
    public static int TOKEN_PUNTOYCOMA = 1037;
    public static int TOKEN_ENTERO = 1038;
    public static int TOKEN_PTOPTO = 1039;
    public static int TOKEN_WRITELN = 1040;
    public static int TOKEN_COMENTARIO = 1041;
    public static int TOKEN_SUMA = 1042;
    public static int TOKEN_RESTA = 1043;
    public static int TOKEN_CTELITERAL = 1044;
    

    private Palabra palabrasReservadas[] = {
        // Considerar las palabras reservadas de su lenguaje con las constantes que las representan
        new Palabra("True", TOKEN_TRUE),
        new Palabra("False", TOKEN_FALSE),
        new Palabra("Mod", TOKEN_MOD),
        new Palabra("True", TOKEN_TRUE),
        new Palabra("And", TOKEN_AND),
        new Palabra("Or", TOKEN_OR),
        new Palabra("Var", TOKEN_VAR),
        new Palabra("While", TOKEN_WHILE),
        new Palabra("Integer", TOKEN_INTEGER),
        new Palabra("Begin", TOKEN_BEGIN),
        new Palabra("do", TOKEN_DO),
        new Palabra("array", TOKEN_ARRAY),
        new Palabra("boolean", TOKEN_BOOLEAN),
        new Palabra("program", TOKEN_PROGRAM),
        new Palabra("if", TOKEN_IF),
        new Palabra("of", TOKEN_OF),
        new Palabra("then", TOKEN_THEN),
        new Palabra("function", TOKEN_FUNCTION),
        new Palabra("End", TOKEN_END),
        new Palabra("type", TOKEN_TYPE),
        new Palabra("Else", TOKEN_ELSE),
        new Palabra("Procedure", TOKEN_PROCEDURE),
        new Palabra("writeln", TOKEN_WRITELN),    
    };
   
    public Lexer() {
    }

    public int getNumeroDeLinea() {
        return mNumeroDeLinea;
    }

    public String getUltimoLexema() {
        return mLexema;
    }

    public int getNextToken() {

        if (mLector == null) {
            return -1;
        }

        char caracterActual;
        int estadoActual = 0;
        hayError = false;
        mLexema = "";
        msjError("Correcto!");
        while (mLector.listo()) {
            // modificar segun el AFD de su Analizador Lexico

            caracterActual = peekChar();

            if (estadoActual == 0) {

                if (Character.isWhitespace(caracterActual)) {
                    estadoActual = 33;
                    readChar(); //consumir el caracter
                } else if (Character.isLetter(caracterActual)) { 
                    estadoActual = 12;
                    readChar(); //consumir el caracter
                } else if (Character.isDigit(caracterActual)) { 
                    estadoActual = 32; 
                    readChar(); //consumir el caracter
                } else if (caracterActual == ')') {
                    estadoActual = 8;
                    readChar(); //consumir el caracter
                } else if (caracterActual == '(') {
                    estadoActual = 3;
                    readChar(); //consumir el caracter
                } else if (caracterActual == '[') {
                    estadoActual = 10;
                    readChar(); //consumir el caracter
                } else if (caracterActual == ']') {
                    estadoActual = 11;
                    readChar(); //consumir el caracter
                } else if (caracterActual == '.') {
                    estadoActual = 14;
                    readChar(); //consumir el caracter
                } else if (caracterActual == ',') {
                    estadoActual = 17;
                    readChar(); //consumir el caracter
                } else if (caracterActual == ':') {
                    estadoActual = 18;
                    readChar(); //consumir el caracter
                } else if (caracterActual == '*') {
                    estadoActual = 21;
                    readChar(); //consumir el caracter
                } else if (caracterActual == '<') {
                    estadoActual = 22;
                    readChar(); //consumir el caracter
                } else if (caracterActual == '=') {
                    estadoActual = 25;
                    readChar(); //consumir el caracter
                } else if (caracterActual == '+') {
                    estadoActual = 27;
                    readChar(); //consumir el caracter
                } else if (caracterActual == '-') {
                    estadoActual = 28;
                    readChar(); //consumir el caracter
                }else if (caracterActual == ';') {
                    estadoActual = 26;
                    readChar(); //consumir el caracter
                } else if ("'".equals(caracterActual) ) {//COMILLA SIMPLE
                    estadoActual = 29;
                    readChar(); //consumir el caracter
                } else {
                    //llega un caracter que no es valido en este estado
                    hayError = true;
                    readChar();
                    msjError("Simbolo ilegal");
                    Error="Simbolo Ilegal (" + mLexema +")";
                    return -1;
                }

            } //fin del estado 0
            else if (estadoActual == 33) {

                if (Character.isWhitespace(caracterActual)) {
                    estadoActual = 33; 
                    readChar(); //consumir el caracter
                } else {
                    mLexema = ""; //
                    estadoActual = 0;
                }

            } else if (estadoActual == 12) {

                if (Character.isLetterOrDigit(caracterActual)) {
                    estadoActual = 12;
                    readChar(); //consumir el caracter
                } else {
                    return esPalabraReservada(getUltimoLexema());
                    // return TOKEN_IDENT;
                }

            } else if (estadoActual == 3) {

                if (caracterActual == '*') {
                    estadoActual = 5;//
                    readChar(); //consumir el caracter
                }else{
                   return TOKEN_PARIZQ;
                } 
             
            } else if (estadoActual == 5) {

                if (caracterActual != '*') {
                    estadoActual = 6;//
                    readChar(); //consumir el caracter
                } else {
                    hayError = true;
                    readChar(); //quitar el error ( un caracter )
                    msjError("Error de Sintaxis");
                    return -1;
                }

            }else if (estadoActual == 6) {

                if (caracterActual != '*') {
                    estadoActual = 6;//
                    readChar(); //consumir el caracter
                } else {
                    estadoActual = 7;//
                    readChar(); //consumir el caracter
                }

            }else if (estadoActual == 7) {

                if (caracterActual == ')') {
                    return TOKEN_COMENTARIO;
                } else {
                    hayError = true;
                    readChar(); //quitar el error ( un caracter )
                    msjError("Falta ')'");
                    return -1;
                }}
              else if (estadoActual == 14) {

                if (caracterActual == '.') {
                    readChar(); //consumir el caracter
                    return TOKEN_PTOPTO;
                } else {
                    return TOKEN_PUNTO;
                }
                
              }else if (estadoActual == 18) {
                if (caracterActual == '=') {
                    readChar(); //consumir el caracter
                    return TOKEN_ASIGNACION;
                } else {
                    return TOKEN_DPUNTOS;
                }
              
              }else if (estadoActual == 22) {
                if (caracterActual == '>') {
                    readChar(); //consumir el caracter
                    return TOKEN_DISTINTO;
                } else {
                    return TOKEN_MAYIZQ;
                }
              
              }else if (estadoActual == 29) {
                if (String.valueOf(caracterActual) != "'") { // COMILLA SIMPLE
                    estadoActual = 30;//
                    readChar();
                }
               else if (estadoActual == 30) {
               if ("'".equals(caracterActual)) { // COMILLA SIMPLE
                    readChar();
                    return TOKEN_CTELITERAL;
                }else{
                    hayError = true;
                    readChar(); //quitar el error ( un caracter )
                    msjError("Falta un Digito");
                    Error="Falta un digito (" + mLexema +")";
                    return -1;
                }     
                   }
              }else if (estadoActual == 32) {

                if (Character.isDigit(caracterActual)) {
                    estadoActual = 32;//
                    readChar(); //consumir el caracter
                } else {
                   return TOKEN_ENTERO;
                }

            }else if (estadoActual == 10) {
                return TOKEN_CORCHIZQ;
            } else if (estadoActual == 26) {
                return TOKEN_PUNTOYCOMA;
            } else if (estadoActual == 27) {
                return TOKEN_SUMA;
            } else if (estadoActual == 28) {
                return TOKEN_RESTA;
            } else if (estadoActual == 11) {
                return TOKEN_CORCHDER;
            } else if (estadoActual == 17) {
                return TOKEN_COMA;
            } else if (estadoActual == 21) {
                return TOKEN_ASTER;
            } 
        }
        //caso que llegue hasta aqui , es que no encontro token
        return -1;
    }

    private char peekChar() {

        if (mLector == null) {
            //no hay archivo abierto
            return Character.MIN_VALUE;
        }

        if (!mLector.listo()) {
            //no esta listo
            return Character.MIN_VALUE;
        }

        return mLector.peekNextChar();

    }

    private char readChar() {

        if (mLector == null) {
            //no hay archivo abierto
            return Character.MIN_VALUE;
        }

        if (!mLector.listo()) {
            //no esta listo
            return Character.MIN_VALUE;
        }

        char temp = mLector.ReadNextChar();

        //si el caracter actual es ENTER "" == cadenas , '' == caracter
        if (temp == '\n') {
            mNumeroDeLinea++; // incrementar en 1
            mNumeroDeCaracter = 1; //regresar a 1
        } else {
            mNumeroDeCaracter++;
        }
        //concatenar este caracter, a la cadena mLexema
        mLexema += temp;
        return temp;
    }

    public int esPalabraReservada(String s){

        for(int i=0; i<palabrasReservadas.length; i++) {
            if ( s.compareTo(palabrasReservadas[i].nomb)==0 )
                return palabrasReservadas[i].cod;
	}
        return TOKEN_IDENT;
    }

    public void inicializar(String pFilePath) {
        //solo preparar el charReader con el archivo que nos indican
        mLector = new CharReader();
        mLector.inicializar(pFilePath);
        mNumeroDeLinea = 1;
        mNumeroDeCaracter = 1;
    }
    
        
        public void msjError(String msj) {
        Error="Correcto!";
        System.out.println("Error en linea " + mNumeroDeLinea + ": " + msj + " (" + mLexema +")");
    }
        
     public String DevuelveEstado(){
        return Error;
     }
        
    
}