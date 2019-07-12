public abstract class Drawing {
    int x;
    int y;
    Socket s1;
    Socket s2;
    Socket s3;
    Socket s4;
    public void selectMe(){
        if(Main.x<s1.x + 6 && Main.x>s1.x && Main.y>s1.y && Main.y<s1.y+6) Main.selected = s1;
        if(Main.x<s2.x + 6 && Main.x>s2.x && Main.y>s2.y && Main.y<s2.y+6) Main.selected = s2;
        if(Main.x<s3.x + 6 && Main.x>s3.x && Main.y>s3.y && Main.y<s3.y+6) Main.selected = s3;
        if(Main.x<s4.x + 6 && Main.x>s4.x && Main.y>s4.y && Main.y<s4.y+6) Main.selected = s4;
    }
    public void connectMe(){
        if(Main.x<s1.x + 6 && Main.x>s1.x && Main.y>s1.y && Main.y<s1.y+6) Main.connected = s1;
        if(Main.x<s2.x + 6 && Main.x>s2.x && Main.y>s2.y && Main.y<s2.y+6) Main.connected = s2;
        if(Main.x<s3.x + 6 && Main.x>s3.x && Main.y>s3.y && Main.y<s3.y+6) Main.connected = s3;
        if(Main.x<s4.x + 6 && Main.x>s4.x && Main.y>s4.y && Main.y<s4.y+6) Main.connected = s4;
    }
}
