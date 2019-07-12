public class DataStore extends Drawing {
    String TEXT;
    DataStore(int x, int y, String TEXT){
        this.x = x;
        this.y = y;
        this.TEXT = TEXT;
        s1 = new Socket(x+23,y-6,1);
        s2 = new Socket(x+51,y+25,2);
        s3 = new Socket(x+23,y+51,3);
        s4 = new Socket(x-6,y+25,4);
    }
}
