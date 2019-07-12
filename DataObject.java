public class DataObject extends Drawing {
    String TEXT;
    DataObject(int x, int y, String TEXT){
        this.x = x;
        this.y = y;
        this.TEXT = TEXT;
        s1 = new Socket(x+17,y-6,1);
        s2 = new Socket(x+41,y+25,2);
        s3 = new Socket(x+17,y+51,3);
        s4 = new Socket(x-6,y+25,4);
    }
}
