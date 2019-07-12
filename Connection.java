public class Connection {
    Socket from;
    Socket to;
    String label="";
    Connection(Socket from, Socket to){
        this.from = from;
        this.to = to;
    }
    public void addLabel(){
        if((Main.x<from.x + 6 && Main.x>from.x && Main.y>from.y && Main.y<from.y+6) || (Main.x<to.x + 6 && Main.x>to.x && Main.y>to.y && Main.y<to.y+6)) Main.connectionSelected = this;
    }
}
