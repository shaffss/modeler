public class Gateway extends Drawing {
    boolean decision;
    String TEXT;
    Gateway(int x, int y, String TEXT){
        this.x = x;
        this.y = y;
        this.TEXT = TEXT;
        s1 = new Socket(x+22,y-6,1);
        s2 = new Socket(x+50,y+22,2);
        s3 = new Socket(x+22,y+50,3);
        s4 = new Socket(x-6,y+22,4);
    }
}
