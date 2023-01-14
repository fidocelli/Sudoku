import java.awt.*;
import java.util.*;
import java.awt.event.*;
class display extends Frame implements ActionListener
{
    Timer timer;
    int ans[][]= new int[9][9];
    Frame f = new Frame ();
    Button end ;
    TextField tf[][] = new TextField[9][9] ;
    Label lb;
    TextField res = new TextField();
    Sudoku ob = new Sudoku();
    Button start;
    Button solve;
    TextField ti;
    Label lb2;
    public static void main (String args[])
    {
        display ob = new display();
        ob.setup();
    }
   public   void setup()
   {  
      f.setBackground(Color.cyan);
       Font font = new Font("Tw Cen MT Condensed Extra Bold",Font.BOLD,14);
       lb = new Label("RESULT");
       lb.setFont(font);
       lb.setForeground(Color.BLACK);
       lb.setBounds(500,250,70,50);
       res.setEditable(false);
       res.setBounds(500,290,70,20);
       f.add(res);
       f.add(lb);
       end = new Button();
       end.setBounds(560,330,50,40);
       end.setLabel("END");
       f.add(end);

       start = new Button();       
       start.setBounds(500,330, 57,40);
       start.setLabel("START");
       f.add(start);

       solve = new Button();       
       solve.setBounds(615,330, 59,40);
       solve.setLabel("REVEAL");
       f.add(solve);

       ti = new TextField();
       ti.setBounds(500,230,70,20);
       f.add(ti);
       lb2 = new Label("TIME(IN SECONDS)");
       start.setFont(new Font("Tw Cen MT Condensed Extra Bold",Font.BOLD,14));
       start.setForeground(Color.BLACK);
       end.setFont(font);
       end.setForeground(Color.BLACK);
       solve.setFont(font);
       solve.setForeground(Color.BLACK);
       lb2.setFont(font);
       lb2.setForeground(Color.BLACK);
       lb2.setBounds(500,210,160,15);
       f.add(lb2);
      int x =100;//initial x
       int y =210;//initial y
       Label head = new Label();
       head.setBounds(100,50,330,50);
       head.setText("LET'S PLAY SUDOKU");
       head.setFont(new Font("Tw Cen MT Condensed Extra Bold",Font.ITALIC,30));
       head.setForeground(Color.black);
       f.add(head);

       Label txt1 = new Label();
       txt1.setBounds(100,100,300,40);
       txt1.setText("INSTRUCTIONS:");
       txt1.setFont(new Font("Tw Cen MT Condensed Extra Bold",Font.ITALIC,20));
       txt1.setForeground(Color.black);
       f.add(txt1);

       Label txt2 = new Label();
       txt2.setBounds(100,130,600,38);
       txt2.setText("Press START to get grid and press END after you've finished solving");
       txt2.setFont(new Font("Tw Cen MT Condensed Extra Bold",Font.ITALIC,15));
       txt2.setForeground(Color.black);
       f.add(txt2);

       Label txt3 = new Label();
       txt3.setBounds(100,160,300,40);
       txt3.setText("Press ENTER key after each input to lock it");
       txt3.setFont(new Font("Tw Cen MT Condensed Extra Bold",Font.ITALIC,15));
       txt3.setForeground(Color.black);
       f.add(txt3);
       for(int x1 =0;x1<9;x1++)
       {
           x= 100;
           for(int y1 = 0;y1<9;y1++){
               tf[x1][y1] = new TextField();
           tf[x1][y1].setBounds(x,y,30,30);
           tf[x1][y1].addActionListener(this);
           tf[x1][y1].setBackground(Color.black);
           x = x+ 30;
            f.add(tf[x1][y1]);
        }
        y =y +30;
        }
        end.addActionListener(this);
        start.addActionListener(this);
        solve.addActionListener(this);
       f.setSize(700,700);
       f.setLayout(null);
       f.setVisible(true);
       f.addWindowListener(new myclass());
     
       
    }
    void showcase()//how it will look with numbers
    {
       
        int ar[][] =ob.main();
       Font font = new Font("Tw Cen MT Condensed Extra Bold",Font.BOLD,15);
        for(int x =0;x<9;x++)
        {
            for(int y = 0;y<9;y++)
           {
               tf[x][y].setFont(font);
               if(ar[x][y]!=0)
              {
                  ans[x][y] = ar[x][y];
                  tf[x][y].setText(String.valueOf(ar[x][y]));
               tf[x][y].setEditable(false);
               tf[x][y].setBackground(Color.yellow);
               
               tf[x][y].setForeground(Color.black);
            }
            else
            tf[x][y].setForeground(Color.white);
           
        }
    }


    }
     void start()
    {
        timer =new Timer();
        TimerTask task = new drag();
        timer.scheduleAtFixedRate(task,1000,1000);
    }

    void solve(){
        int ar[][] =ob.f;
       Font font = new Font("Tw Cen MT Condensed Extra Bold",Font.BOLD,15);
        for(int x =0;x<9;x++)
        {
            for(int y = 0;y<9;y++)
           {
               tf[x][y].setFont(font);
               if(ar[x][y]!=0)
              {
                  ans[x][y] = ar[x][y];
                  tf[x][y].setText(String.valueOf(ar[x][y]));
               tf[x][y].setEditable(false);
               tf[x][y].setBackground(Color.yellow);
               
               tf[x][y].setForeground(Color.black);
            }
            else
            tf[x][y].setForeground(Color.white);
           
        }
    }
}
    public void actionPerformed(ActionEvent e)
    {
        for(int x =0;x<9;x++)
        {
            for(int y = 0;y<9;y++)
            if(e.getSource()== tf[x][y])
            ans[x][y]= Integer.parseInt(tf[x][y].getText());
        }
        if(e.getSource()== end)
        {
            timer.cancel();
        if(compare(ans)==true)
        res.setText("WIN");
        else res.setText("FAILED");
       
    }
    if(e.getSource()==start)
    {showcase();
    start();
    start.removeActionListener(this);
}
if(e.getSource()==solve)
    {
    solve();
    timer.cancel();
    res.setText("---");
    solve.removeActionListener(this);
    end.removeActionListener(this);
}
    }
    boolean compare(int anss[][])
    {
       
        int sol[][] = ob.f;

        for(int x =0;x<9;x++)
        {
            for(int y =0;y<9;y++)
            if(sol[x][y]!=anss[x][y])
            {
                return false;
            }
        }
        return true;
    }
    void print(int[][] x)//prints array
{

for (int r = 0; r < 9; r++)
{
for (int d = 0; d < 9; d++)
{
System.out.print(x[r][d]+" ");
System.out.print(" ");
}
System.out.println();
}
}
  class drag extends TimerTask
    {
        int c =0;
        public void run()
        {c++;
            ti.setText(""+c);
           
           
}
}
   
class myclass extends WindowAdapter
{
    public void windowClosing(WindowEvent e)
    {
      f.dispose();
    }
}
}
