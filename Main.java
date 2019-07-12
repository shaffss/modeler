import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import static java.lang.Math.abs;

public class Main extends Applet implements ActionListener {
    /*POPUP MENU*/
    PopupMenu popup;
    /*CO-ORDINATES*/
    public static int x;
    public static int y;
    public int n=2; //liczba procesow
    /*RESOLUTION*/
    public int width = 1920, height = 1080; //rozdzielczosc
    public ArrayList<StartingPoint> SP = new ArrayList<StartingPoint>();
    public ArrayList<EndingPoint> EP = new ArrayList<EndingPoint>();
    public ArrayList<Entity> ENT = new ArrayList<Entity>();
    public ArrayList<Gateway> GATES = new ArrayList<Gateway>();
    public ArrayList<DataObject> DATAOBJECTS = new ArrayList<DataObject>();
    public ArrayList<DataStore> DATASTORES = new ArrayList<DataStore>();
    public ArrayList<Connection> CONNECTIONS = new ArrayList<Connection>();
    public ArrayList<Connection> DASHEDCONNECTIONS = new ArrayList<Connection>();
    public ArrayList<Socket> SOCKETLIST = new ArrayList<Socket>();
    public ArrayList<Socket> DASHEDSOCKETLIST = new ArrayList<Socket>();
    public Polygon p = new Polygon();
    static String o;
    // JTextField
    static JTextField t;
    // JFrame
    static JFrame f;
    // JButton
    static JButton b;
    // label to diaplay text
    static JLabel l;
    static Socket selected;
    static Socket connected;
    static Connection connectionSelected;
    static Entity entitySelected;
    static Gateway gatewaySelected;

    BufferedImage dataObjectImage;

    {
        try {
            dataObjectImage = ImageIO.read(new File("C:\\Users\\shaff\\IdeaProjects\\Modeler\\dataObject.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BufferedImage dataStoreImage;

    {
        try {
            dataStoreImage = ImageIO.read(new File("C:\\Users\\shaff\\IdeaProjects\\Modeler\\dataStore.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(){
        /*POPUP MENU*/
        MenuItem mi;
        popup = new PopupMenu("Edit");

        mi = new MenuItem("Start");
        mi.addActionListener(this);
        popup.add(mi);
        mi = new MenuItem("Entity");
        mi.addActionListener(this);
        popup.add(mi);
        mi = new MenuItem("Gateway");
        mi.addActionListener(this);
        popup.add(mi);
        mi = new MenuItem("End");
        mi.addActionListener(this);
        popup.add(mi);
        mi = new MenuItem("Data Object");
        mi.addActionListener(this);
        popup.add(mi);
        mi = new MenuItem("Data Store");
        mi.addActionListener(this);
        popup.add(mi);
        popup.addSeparator();
        mi = new MenuItem("Remove");
        mi.addActionListener(this);
        popup.add(mi);
        mi = new MenuItem("Edit...");
        mi.addActionListener(this);
        popup.add(mi);
        popup.addSeparator();
        mi = new MenuItem("Select");
        mi.addActionListener(this);
        popup.add(mi);
        mi = new MenuItem("Discard");
        mi.addActionListener(this);
        popup.add(mi);
        mi = new MenuItem("Connect");
        mi.addActionListener(this);
        popup.add(mi);
        mi = new MenuItem("Connect Dashed");
        mi.addActionListener(this);
        popup.add(mi);
        popup.addSeparator();
        mi = new MenuItem("Add Label...");
        mi.addActionListener(this);
        popup.add(mi);

        add(popup); // add popup menu to applet

        enableEvents(AWTEvent.MOUSE_EVENT_MASK);

        /*Applet variables*/
        setSize(width, height); //rozdzielczosc poczatkowa
        setBackground(Color.WHITE); //malujemy tlo na bialo

        //SZKIELET SCHEMATU
        SP.add(new StartingPoint(100,125));
        SOCKETLIST.add(SP.get(SP.size()-1).s2); //0
        ENT.add(new Entity(250,100,"Przygotowanie wniosku o   stypendium"));
        SOCKETLIST.add(ENT.get(ENT.size()-1).s4); //1
        DASHEDSOCKETLIST.add(ENT.get(ENT.size()-1).s1); //0
        SOCKETLIST.add(ENT.get(ENT.size()-1).s2); //2
        DATAOBJECTS.add(new DataObject(550,25,"Wniosek (papierowy)"));
        DASHEDSOCKETLIST.add(DATAOBJECTS.get(DATAOBJECTS.size()-1).s4); //1
        ENT.add(new Entity(750,100,"Złożenie wniosku w        dziekanacie"));
        SOCKETLIST.add(ENT.get(ENT.size()-1).s4); //3
        SOCKETLIST.add(ENT.get(ENT.size()-1).s3); //4
        ENT.add(new Entity(750,800,"Weryfikacja kompletności  wniosku"));
        SOCKETLIST.add(ENT.get(ENT.size()-1).s1); //5
        DASHEDSOCKETLIST.add(ENT.get(ENT.size()-1).s4); //2
        SOCKETLIST.add(ENT.get(ENT.size()-1).s2); //6
        DATAOBJECTS.add(new DataObject(550,825,"Wniosek (papierowy)"));
        DASHEDSOCKETLIST.add(DATAOBJECTS.get(DATAOBJECTS.size()-1).s2); //3
        GATES.add(new Gateway(1200,825,"Weryfikacja kompletności wniosku"));
        SOCKETLIST.add(GATES.get(GATES.size()-1).s4); //7
        SOCKETLIST.add(GATES.get(GATES.size()-1).s1); //8
        SOCKETLIST.add(GATES.get(GATES.size()-1).s2); //9
        ENT.add(new Entity(1125,550,"Przekazanie informacji    studentowi"));
        SOCKETLIST.add(ENT.get(ENT.size()-1).s3); //10
        SOCKETLIST.add(ENT.get(ENT.size()-1).s1); //11
        DASHEDSOCKETLIST.add(ENT.get(ENT.size()-1).s4); //4
        DATASTORES.add(new DataStore(1000,575,"Poczta elektroniczna"));
        DASHEDSOCKETLIST.add(DATASTORES.get(DATASTORES.size()-1).s2); //5
        ENT.add(new Entity(1425,800,"Skanowanie i rejestracja   wniosku"));
        SOCKETLIST.add(ENT.get(ENT.size()-1).s4); //12
        SOCKETLIST.add(ENT.get(ENT.size()-1).s2); //13
        DASHEDSOCKETLIST.add(ENT.get(ENT.size()-1).s1); //6
        DATAOBJECTS.add(new DataObject(1625,625,"Rejestr wniosków o stypendioum (MS Excel)"));
        DASHEDSOCKETLIST.add(DATAOBJECTS.get(DATAOBJECTS.size()-1).s4); //7
        EP.add(new EndingPoint(1725,825));
        SOCKETLIST.add(EP.get(EP.size()-1).s4); //14
        ENT.add(new Entity(1425,100,"Odebranie wniosku"));
        SOCKETLIST.add(ENT.get(ENT.size()-1).s4); //15
        SOCKETLIST.add(ENT.get(ENT.size()-1).s2); //16
        EP.add(new EndingPoint(1725,125));
        SOCKETLIST.add(EP.get(EP.size()-1).s4); //17
        //ŁĄCZENIA
        CONNECTIONS.add(new Connection(SOCKETLIST.get(0),SOCKETLIST.get(1)));
        CONNECTIONS.add(new Connection(SOCKETLIST.get(2),SOCKETLIST.get(3)));
        CONNECTIONS.add(new Connection(SOCKETLIST.get(4),SOCKETLIST.get(5)));
        CONNECTIONS.add(new Connection(SOCKETLIST.get(6),SOCKETLIST.get(7)));
        CONNECTIONS.add(new Connection(SOCKETLIST.get(8),SOCKETLIST.get(10)));
        CONNECTIONS.get(CONNECTIONS.size()-1).label = "  NIE";
        CONNECTIONS.add(new Connection(SOCKETLIST.get(9),SOCKETLIST.get(12)));
        CONNECTIONS.get(CONNECTIONS.size()-1).label = "TAK";
        CONNECTIONS.add(new Connection(SOCKETLIST.get(13),SOCKETLIST.get(14)));
        CONNECTIONS.add(new Connection(SOCKETLIST.get(11),SOCKETLIST.get(15)));
        CONNECTIONS.add(new Connection(SOCKETLIST.get(16),SOCKETLIST.get(17)));
        //ŁĄCZENIA PRZERYWANE
        DASHEDCONNECTIONS.add(new Connection(DASHEDSOCKETLIST.get(0),DASHEDSOCKETLIST.get(1)));
        DASHEDCONNECTIONS.add(new Connection(DASHEDSOCKETLIST.get(3),DASHEDSOCKETLIST.get(2)));
        DASHEDCONNECTIONS.add(new Connection(DASHEDSOCKETLIST.get(4),DASHEDSOCKETLIST.get(5)));
        DASHEDCONNECTIONS.add(new Connection(DASHEDSOCKETLIST.get(6),DASHEDSOCKETLIST.get(7)));

        resize(width,height);
    }

    public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2){

        //creates a copy of the Graphics instance
        Graphics2D g2d = (Graphics2D) g.create();

        //set the stroke of the copy, not the original
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.drawLine(x1, y1, x2, y2);

        //gets rid of the copy
        g2d.dispose();
    }

    public void paint(Graphics g) //tworzenie obiektu "g" ktory posluzy do narysowania kratek
    {
        width = getSize().width; //pobieramy aktualna szerokosc
        height = getSize().height; //pobieramy aktualna wysokosc

        /*RYSOWANIE LINII*/
        g.setColor(Color.BLACK); //paski beda czarne
        for (int i = 1; i <= n; i++) g.drawLine(0, height * i / n, width, height * i / n); //linie poziome
        g.drawLine(50,0,50,1080); //linia pionowa

        //printing StartingPoint objects
        for(StartingPoint i : SP){
            g.setColor(Color.GREEN);
            g.fillOval(i.x,i.y,50,50);
            g.setColor(Color.WHITE);
            g.fillOval(i.x+3,i.y+3,44,44);
            g.setColor(Color.BLACK);
            g.drawOval(i.s1.x,i.s1.y,6,6);
            g.drawOval(i.s2.x,i.s2.y,6,6);
            g.drawOval(i.s3.x,i.s3.y,6,6);
            g.drawOval(i.s4.x,i.s4.y,6,6);
        }
        //printing EndingPoint objects
        for(EndingPoint i : EP){
            g.setColor(Color.RED);
            g.fillOval(i.x,i.y,50,50);
            g.setColor(Color.WHITE);
            g.fillOval(i.x+3,i.y+3,44,44);
            g.setColor(Color.BLACK);
            g.drawOval(i.s1.x,i.s1.y,6,6);
            g.drawOval(i.s2.x,i.s2.y,6,6);
            g.drawOval(i.s3.x,i.s3.y,6,6);
            g.drawOval(i.s4.x,i.s4.y,6,6);
        }
        //printing Entity objects
        for(Entity i : ENT){
            g.setColor(Color.BLUE);
            g.fillRect(i.x,i.y,200,100);
            g.setColor(Color.WHITE);
            g.fillRect(i.x+6,i.y+6,188,88);
            g.setColor(Color.BLACK);
            //g.drawString(i.TEXT,i.x+7,i.y+16);
            i.convertString();
            g.drawChars(i.TEXTCHAR,0,i.TEXTCHAR.length,i.x+7,i.y+16); //Pierwsza Linijka
            g.drawChars(i.TEXTCHAR2,0,i.TEXTCHAR2.length,i.x+7,i.y+26); //Druga Linijka
            g.drawChars(i.TEXTCHAR3,0,i.TEXTCHAR3.length,i.x+7,i.y+36); //Trzecia Linijka
            g.drawChars(i.TEXTCHAR4,0,i.TEXTCHAR4.length,i.x+7,i.y+45); //Czwarta Linijka
            g.setColor(Color.BLACK);
            g.drawOval(i.s1.x,i.s1.y,6,6);
            g.drawOval(i.s2.x,i.s2.y,6,6);
            g.drawOval(i.s3.x,i.s3.y,6,6);
            g.drawOval(i.s4.x,i.s4.y,6,6);
        }
        //printing Gateway objects
        for(Gateway i : GATES) {
            g.setColor(Color.BLACK);
            p.addPoint(i.x+25,i.y);
            p.addPoint(i.x+50,i.y+25);
            p.addPoint(i.x+25,i.y+50);
            p.addPoint(i.x,i.y+25);
            g.fillPolygon(p);
            p.reset();
            g.setColor(Color.WHITE);
            p.addPoint(i.x+25,i.y+3);
            p.addPoint(i.x+47,i.y+25);
            p.addPoint(i.x+25,i.y+47);
            p.addPoint(i.x+3,i.y+25);
            g.fillPolygon(p);
            p.reset();
            g.setColor(Color.BLACK);
            g.drawString(i.TEXT,i.x-50,i.y+70);
            g.drawOval(i.s1.x,i.s1.y,6,6);
            g.drawOval(i.s2.x,i.s2.y,6,6);
            g.drawOval(i.s3.x,i.s3.y,6,6);
            g.drawOval(i.s4.x,i.s4.y,6,6);
        }
        for(DataObject i : DATAOBJECTS) {
            g.drawImage(dataObjectImage,i.x,i.y,null);
            g.drawString(i.TEXT,i.x-50,i.y+70);
            g.drawOval(i.s1.x,i.s1.y,6,6);
            g.drawOval(i.s2.x,i.s2.y,6,6);
            g.drawOval(i.s3.x,i.s3.y,6,6);
            g.drawOval(i.s4.x,i.s4.y,6,6);
        }
        for(DataStore i : DATASTORES) {
            g.drawImage(dataStoreImage,i.x,i.y,null);
            g.drawString(i.TEXT,i.x-50,i.y+70);
            g.drawOval(i.s1.x,i.s1.y,6,6);
            g.drawOval(i.s2.x,i.s2.y,6,6);
            g.drawOval(i.s3.x,i.s3.y,6,6);
            g.drawOval(i.s4.x,i.s4.y,6,6);
        }
        Graphics2D gg = (Graphics2D) g;
        for(Connection i : CONNECTIONS) {
            g.setColor(Color.BLACK);
            //g.drawLine(i.from.x+3,i.from.y+3,i.to.x+3,i.to.y+3);
            gg.setStroke(new BasicStroke(3));
            g.drawLine(i.from.x+3,i.from.y+3,i.to.x+3,i.to.y+3);
            g.fillOval(i.from.x,i.from.y,7,7);
            g.fillOval(i.to.x,i.to.y,7,7);
            p.addPoint(i.to.x+3,i.to.y+3);
            if(i.to.layout == 1){
                p.addPoint(i.to.x-2,i.to.y-7);
                p.addPoint(i.to.x+8,i.to.y-7);
            }
            else if(i.to.layout == 2){
                p.addPoint(i.to.x+13,i.to.y-2);
                p.addPoint(i.to.x+13,i.to.y+8);
            }
            else if(i.to.layout == 3){
                p.addPoint(i.to.x-2,i.to.y+13);
                p.addPoint(i.to.x+8,i.to.y+13);
            }
            else if(i.to.layout == 4){
                p.addPoint(i.to.x-7,i.to.y-2);
                p.addPoint(i.to.x-7,i.to.y+8);
            }
            g.fillPolygon(p);
            p.reset();
            if(i.from.x < i.to.x && i.from.y <= i.to.y) g.drawString(i.label,i.from.x+abs(i.from.x - i.to.x)/2, i.from.y+abs(i.from.y - i.to.y)/2);
            else if(i.from.x >= i.to.x && i.from.y < i.to.y) g.drawString(i.label,i.from.x-abs(i.from.x - i.to.x)/2, i.from.y+abs(i.from.y - i.to.y)/2);
            else if(i.from.x <= i.to.x && i.from.y > i.to.y) g.drawString(i.label,i.from.x+abs(i.from.x - i.to.x)/2, i.from.y-abs(i.from.y - i.to.y)/2);
            else if(i.from.x > i.to.x && i.from.y >= i.to.y) g.drawString(i.label,i.from.x-abs(i.from.x - i.to.x)/2, i.from.y-abs(i.from.y - i.to.y)/2);
        }
        for(Connection i : DASHEDCONNECTIONS) {
            g.setColor(Color.BLACK);
            drawDashedLine(g,i.from.x+3,i.from.y+3,i.to.x+3,i.to.y+3);
            g.fillOval(i.from.x,i.from.y,7,7);
            g.fillOval(i.to.x,i.to.y,7,7);
            p.addPoint(i.to.x+3,i.to.y+3);
            if(i.to.layout == 1){
                p.addPoint(i.to.x-2,i.to.y-7);
                p.addPoint(i.to.x+8,i.to.y-7);
            }
            else if(i.to.layout == 2){
                p.addPoint(i.to.x+13,i.to.y-2);
                p.addPoint(i.to.x+13,i.to.y+8);
            }
            else if(i.to.layout == 3){
                p.addPoint(i.to.x-2,i.to.y+13);
                p.addPoint(i.to.x+8,i.to.y+13);
            }
            else if(i.to.layout == 4){
                p.addPoint(i.to.x-7,i.to.y-2);
                p.addPoint(i.to.x-7,i.to.y+8);
            }
            g.fillPolygon(p);
            p.reset();
            if(i.from.x < i.to.x && i.from.y <= i.to.y) g.drawString(i.label,i.from.x+abs(i.from.x - i.to.x)/2, i.from.y+abs(i.from.y - i.to.y)/2);
            else if(i.from.x >= i.to.x && i.from.y < i.to.y) g.drawString(i.label,i.from.x-abs(i.from.x - i.to.x)/2, i.from.y+abs(i.from.y - i.to.y)/2);
            else if(i.from.x <= i.to.x && i.from.y > i.to.y) g.drawString(i.label,i.from.x+abs(i.from.x - i.to.x)/2, i.from.y-abs(i.from.y - i.to.y)/2);
            else if(i.from.x > i.to.x && i.from.y >= i.to.y) g.drawString(i.label,i.from.x-abs(i.from.x - i.to.x)/2, i.from.y-abs(i.from.y - i.to.y)/2);
        }
        g.setColor(Color.BLACK);
        if(selected != null) g.fillOval(selected.x,selected.y,7,7);
    }

    public void input(String objectType)
    {
        o = objectType;
        // create a new frame to stor text field and button
        f = new JFrame("text-input");
        // create a label to display text
        l = new JLabel("nothing entered");
        // create a new button
        b = new JButton("Submit");
        // create a object of the text class
        Text te = new Text();
        // addActionListener to button
        b.addActionListener(te);
        // create a object of JTextField with 16 columns and a given initial text
        t = new JTextField("", 16);
        // create a panel to add buttons and textfield
        JPanel p = new JPanel();
        // add buttons and textfield to panel
        p.add(t);
        p.add(b);
        p.add(l);
        // add panel to frame
        f.add(p);
        // set the size of frame
        f.setSize(300, 300);
        f.show();
    }

    public void processMouseEvent(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3){
            x = e.getX();
            y = e.getY();
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
            super.processMouseEvent(e);
        }
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        repaint();
        if (command.equals("Start")) {
            SP.add(new StartingPoint(x,y));
        } else if (command.equals("Entity")) {
            input("Entity");
        } else if (command.equals("Gateway")) {
            input("Gateway");
        } else if (command.equals("End")) {
            EP.add(new EndingPoint(x,y));
        } else if (command.equals("Data Object")) {
            input("Data Object");
        } else if (command.equals("Data Store")) {
            input("Data Store");
        } else if (command.equals("Remove")){
            for(StartingPoint i : SP) if(x < i.x + 50 && x > i.x && y > i.y && y < i.y + 50) SP.remove(i);
            for(EndingPoint i : EP) if(x < i.x + 50 && x > i.x && y > i.y && y < i.y + 50) EP.remove(i);
            for(Entity i : ENT) if(x < i.x + 200 && x > i.x && y > i.y && y < i.y + 100) ENT.remove(i);
            for(Gateway i : GATES) if(x<i.x+50 && x>i.x && y>i.y && y<i.y+50) GATES.remove(i);
            for(DataObject i : DATAOBJECTS) if(x<i.x+50 && x>i.x && y>i.y && y<i.y+50) DATAOBJECTS.remove(i);
            for(DataStore i : DATASTORES) if(x<i.x+50 && x>i.x && y>i.y && y<i.y+50) DATASTORES.remove(i);
            for(Connection i : CONNECTIONS) if((x<i.from.x+6 && x>i.from.x && y>i.from.y && y<i.from.y+6) || (x<i.to.x+6 && x>i.to.x && y>i.to.y && y<i.to.y+6)) CONNECTIONS.remove(i);
            for(Connection i : DASHEDCONNECTIONS) if((x<i.from.x+6 && x>i.from.x && y>i.from.y && y<i.from.y+6) || (x<i.to.x+6 && x>i.to.x && y>i.to.y && y<i.to.y+6)) DASHEDCONNECTIONS.remove(i);
        } else if (command.equals("Edit...")){
            for(Entity i : ENT) if(x < i.x + 200 && x > i.x && y > i.y && y < i.y + 100) entitySelected = i;
            for(Gateway i : GATES) if(x<i.x+50 && x>i.x && y>i.y && y<i.y+50) gatewaySelected = i;
            if(entitySelected != null || gatewaySelected != null) input("Edit...");
        } else if (command.equals("Select")){
            for(StartingPoint i : SP) i.selectMe();
            for(Entity i : ENT) i.selectMe();
            for(Gateway i : GATES) i.selectMe();
            for(EndingPoint i : EP) i.selectMe();
            for(DataObject i : DATAOBJECTS) i.selectMe();
            for(DataStore i : DATASTORES) i.selectMe();
        } else if (command.equals("Discard")){
            selected = null;
            connected = null;
        } else if (command.equals("Connect") && selected!=null){
            for(StartingPoint i : SP) i.connectMe();
            for(Entity i : ENT) i.connectMe();
            for(Gateway i : GATES) i.connectMe();
            for(EndingPoint i : EP) i.connectMe();
            for(DataObject i : DATAOBJECTS) i.connectMe();
            for(DataStore i : DATASTORES) i.connectMe();
            if (connected != null) CONNECTIONS.add(new Connection(selected,connected));
            selected = null;
            connected = null;
        }
        else if (command.equals("Connect Dashed") && selected!=null){
            for(StartingPoint i : SP) i.connectMe();
            for(Entity i : ENT) i.connectMe();
            for(Gateway i : GATES) i.connectMe();
            for(EndingPoint i : EP) i.connectMe();
            for(DataObject i : DATAOBJECTS) i.connectMe();
            for(DataStore i : DATASTORES) i.connectMe();
            if (connected != null) DASHEDCONNECTIONS.add(new Connection(selected,connected));
            selected = null;
            connected = null;
        }
        else if (command.equals("Add Label...")) {
            for(Connection i : CONNECTIONS) i.addLabel();
            for(Connection i : DASHEDCONNECTIONS) i.addLabel();
            if(connectionSelected != null) input("Add Label...");
        }
    }

    class Text extends JFrame implements ActionListener {
        // if the button is pressed
        public void actionPerformed(ActionEvent ee)
        {
            String s = ee.getActionCommand();
            if (s.equals("Submit")) {
                // set the text of the label to the text of the field
                l.setText(t.getText());
                if(o == "Entity") ENT.add(new Entity(x,y,l.getText()));
                else if(o == "Gateway") GATES.add(new Gateway(x,y,l.getText()));
                else if(o == "Data Object") DATAOBJECTS.add(new DataObject(x,y,l.getText()));
                else if(o == "Data Store") DATASTORES.add(new DataStore(x,y,l.getText()));
                else if(o == "Add Label...") connectionSelected.label = l.getText();
                else if(o == "Edit..."){
                    if(entitySelected != null){
                        entitySelected.TEXT = l.getText();
                        entitySelected.convertString();
                    }
                    else if(gatewaySelected != null) gatewaySelected.TEXT = l.getText();
                }
                f.dispose();
                Main.this.repaint();
                connectionSelected = null;
                entitySelected = null;
                gatewaySelected = null;
            }
        }
    }
}
