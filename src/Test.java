public class Test{
    public static void main(String[]args){
        char ch=args[0].charAt(0);
        switch(ch){
            case'0':case'1':case'2':case'3':case'4':case'5':case'6':case'7':case'8':case'9':
                System.out.println("The first character is digit"+ch);
                break;
            case'a':case'b':case'c':case'd':case'f':case'g':case'h':case'i': case'j':case'k': case'l':case'm':case'n':
            case'o':case'p':case'r':case's':case't':case'u':case'v':case'w':case'x':case'y':case'z':
                System.out.println("The first character is lowercae letter"+ch);
                break;
            case'A':case'B':case'C':case'D':case'E':case'F':case'G':case'H':case'I':case'J':case'K':case'L':case'M':case'N':
            case'O':case'P':case'Q':case'R':case'S':case'T':case'U':case'V':case'W':case'X':case'Y':case'Z':
                System.out.println("The first character is uppercase letter"+ch);
                break;
            default:System.out.println("The first charachter"+ch+"is neither a digit nor a letter.");
        }
    }
}
