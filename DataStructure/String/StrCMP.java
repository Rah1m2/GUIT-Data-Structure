package String;

public class StrCMP {
    public static void main(String args[]){
    int next[] = new int[100];
    BruteForece BF = new BruteForece();
    BF.cmp();
    KMP Kmp = new KMP(next);
    System.out.println(next[0]);
    System.out.println(next[1]);
    System.out.println(next[2]);
    System.out.println(next[3]);
        System.out.println(next[4]);
    }
}

class BruteForece{
    void cmp(){
        String Target = "Lucifer";
        String Str = "eesesmadLucifersakd";
        char chT[] = Target.toCharArray();
        char chS[] = Str.toCharArray();
        int i = 0;
        int j = 0;
        while(j<Target.length() && i<Str.length()){
            if(chT[j] == chS[i]){
                i++;
                j++;
            }
            else{
                i = i-j+1;
                j = 0;
            }
            if(Target.length() == j){
                System.out.println("Found");
                break;
            }
        }
    }
}

class KMP {
    KMP(int next[]){
        String Target = "Lucifer";
        String Str = "eesesmadLucifersakd";
        char chT[] = Target.toCharArray();
        int i = 0;
        int j = -1;
        next[0] = -1;
        while(i<chT.length){
            if(j==-1 || chT[i]==chT[j]){
                i++;
                j++;
                next[i] = j;
            }
            else{
                j = next[j];
            }
        }
    }
}