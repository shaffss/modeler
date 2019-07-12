import java.util.Arrays;

public class Entity extends Drawing{
    String TEXT;
    char[] TEXTCHAR = new char[26];
    char[] TEXTCHAR2 = new char[26];
    char[] TEXTCHAR3 = new char[26];
    char[] TEXTCHAR4 = new char[26];
    Entity(int x, int y, String TEXT){
        this.x = x;
        this.y = y;
        this.TEXT = TEXT;
        s1 = new Socket(x+97,y-6,1);
        s2 = new Socket(x+200,y+47,2);
        s3 = new Socket(x+97,y+100,3);
        s4 = new Socket(x-6,y+47,4);
    }

    public void convertString(){
        int i=0;
        int j=0;
        int k=0;
        int l=0;
        Arrays.fill(TEXTCHAR,' ');
        Arrays.fill(TEXTCHAR2,' ');
        Arrays.fill(TEXTCHAR3,' ');
        Arrays.fill(TEXTCHAR4,' ');
        while(i<TEXT.length()){
            if(i<26){
                TEXTCHAR[i] = TEXT.charAt(i);
            }
            else if(i>25 && i<52){
                TEXTCHAR2[j] = TEXT.charAt(i);
                j++;
            }
            else if(i>51 && i<78){
                TEXTCHAR3[k] = TEXT.charAt(i);
                k++;
            }
            else if(i>77 && i<104){
                TEXTCHAR4[l] = TEXT.charAt(i);
                l++;
            }
            i++;
        }
    }
}
