package diepafiskaki;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 

public class Grifoi extends JFrame implements ActionListener{
    
    private String Xroma;
    private String Epipedo;
    private JFrame tamplo;
    private JButton[][] board;
    private JPanel chessBoard;
    private JLabel infoLabel;
    private boolean check=false;
    private String img;
    private int metr=0;
    private JPanel g;
    private JButton help;
    private JButton menou;
    private int  tempr=0,tempc=0;
    private boolean elegxosBoitheias=false;
    private JButton help1;
    
    public Grifoi(String xroma,String epipedo){
    
        
        Xroma=xroma;
        Epipedo=epipedo;

        tamplo=new JFrame();
    
     //o pinakas me ta koympia ths skakieras
        board = new JButton[8][8];
        //to panel ths skakieras
        chessBoard = new JPanel(new GridLayout(8, 8));
        
        //dhmioyrgia koympion me action listener gia otan patithoun
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
               
                board[row][col] = new JButton();
                
                board[row][col].addActionListener(this);
                
               
               
                
               
                
                //eisagogi twn koympiwn sto panel
                chessBoard.add(board[row][col]);
            }
        }
        
         XromatismosTamplo(xroma);
        
        JPanel g =new JPanel(); 
 
 g.setPreferredSize(new Dimension(100,100));
 g.setLayout(new BorderLayout());
 
 //dhmiourgia koympion paraitisis/isopalias me action listener
 help = new JButton("Βοήθεια");
 help.setPreferredSize(new Dimension(100,300));
 
 help.setToolTipText("Πατήστε εδώ για να δείτε ποιο κομμάτι πρέπεινα κινηθεί");
 help.addActionListener(this);
 
 help.setBackground(Color.orange);
 help.setOpaque(true);
 help.setBorderPainted(false);
 
 
  menou = new JButton("Μενού");
 menou.setPreferredSize(new Dimension(100,280));
 
 menou.setBackground(Color.red);
 menou.setOpaque(true);
 menou.setBorderPainted(false);
 
  menou.setToolTipText("Πατήστε εδώ για να επιστρέψετε στο μενού");
 
  menou.addActionListener(this);
  
  g.add(help,BorderLayout.NORTH);
  g.add(menou,BorderLayout.SOUTH);
    
  JPanel jp = new JPanel();
  jp.setLayout(new BorderLayout());
  
  
        infoLabel = new JLabel("Παίζουν τα Λευκά-Ματ σε 1");
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        
        help1 = new JButton("help");
        help1.addActionListener(this);
        help1.setToolTipText("Πατήστε εδώ για να διαβάσετε τις οδηγίες επίλυσης γρίφων");
        
        jp.add(infoLabel,BorderLayout.CENTER);
        jp.add(help1,BorderLayout.WEST);
        
        tamplo.add(g,BorderLayout.EAST);
         tamplo.add(jp, BorderLayout.SOUTH);
     tamplo.add(chessBoard, BorderLayout.CENTER);
      tamplo.setTitle("Grifos");
        tamplo.setSize(600, 600);
        tamplo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tamplo.setVisible(true);
        
        ArxikopoiisiKommatiwn();
    
    
}


  @Override
    public void actionPerformed(ActionEvent e) {
    
        
        // To koumpi poy patithike
        JButton button = (JButton) e.getSource();
        
        if(button==help1){
        
        new help1().setVisible(true);
            
        return;
        }
        
        if(button==help){
            
            elegxosBoitheias=true;
        
        help.setEnabled(false);
        
        if(Epipedo.equals("Εύκολο")){
        
            board[5][3].setBackground(Color.orange);
           board[5][3].setOpaque(true);
           board[5][3].setBorderPainted(false);
        
        }
        
        else if(Epipedo.equals("Ενδιάμεσο")){
       
            board[5][4].setBackground(Color.orange);
           board[5][4].setOpaque(true);
           board[5][4].setBorderPainted(false);
        
        }
        else{
        board[5][7].setBackground(Color.orange);
           board[5][7].setOpaque(true);
           board[5][7].setBorderPainted(false);
        }
        
        return;
        
        }
        
        if(button == menou){
        
        tamplo.setVisible(false);
        
        DiepafiSkaki j = new DiepafiSkaki();
        
        j.Menou();
        
        return;
        }
        
        
        
        
         //afou eftase mexri edo patithike koympi apo to tamplo
        // pairno th grammh kai th stili
        int row = -1, col = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == button) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }
        
        
        if (board[row][col].getIcon() != null && board[row][col].getIcon().toString().contains("White")){
        //poio kommati epilexthike
        
        XromatismosTamplo(Xroma);
        
        board[row][col].setBackground(Color.MAGENTA);
                                board[row][col].setOpaque(true);
                                board[row][col].setBorderPainted(false);
        
                img = board[row][col].getIcon().toString();
                
                
                
                 tempr=row;
                 tempc=col;
                
                //to check deixnei oti epilexthike kommati pros kinisi
                check=true;
        }
        
        
        
        else if(check){
        
            
            if(Epipedo.contains("Εύκολο")){
        if(img.contains("White_Queen") && row==0 && col==3){
        
       tamplo.setVisible(false);
        
         
       
        metr++;
        new LysimoGrifou(metr,Epipedo,Xroma).setVisible(true);
        
        }
        
        else{
        
            metr++;
            infoLabel.setText("Λάθος κίνηση.Προσπαθήστε ξανά ("+metr+")");
            
            
            check=false;
        
        }}
            
            
            
            
            
            else if(Epipedo.contains("Ενδιάμεσο")){
                
                
        if(img.contains("White_Horse") && tempr==5 && tempc==4 && row==4 && col==2){
        
            metr++;
       tamplo.setVisible(false);
        
         
       
       
        new LysimoGrifou(metr,Epipedo,Xroma).setVisible(true);
        
        }
        
        else{
        
            metr++;
            infoLabel.setText("Λάθος κίνηση.Προσπαθήστε ξανά ("+metr+")");
            
            
            check=false;
        
        }}  
            
         else if(Epipedo.contains("Δύσκολο")){
                
                
        if(img.contains("White_Bishop") && tempr==5 && tempc==7 && row==4 && col==6){
        
            metr++;
       tamplo.setVisible(false);
        
         
       
        
        new LysimoGrifou(metr,Epipedo,Xroma).setVisible(true);
        
        }
        
        else{
        metr++;
            
            infoLabel.setText("Λάθος κίνηση.Προσπαθήστε ξανά ("+metr+")");
           
            
            check=false;
        
        }}      
        
        
        }
                
                
                
    
    }
    
    public final void ArxikopoiisiKommatiwn(){
    
         ImageIcon mPioni = new ImageIcon("Black_Pawn.png");
          ImageIcon mKing = new ImageIcon("Black_King.png");
          ImageIcon mQueen = new ImageIcon("Black_Queen.png");
           ImageIcon mRook = new ImageIcon("Black_Rook.png");
          ImageIcon mHorse = new ImageIcon("Black_Horse.png");
          ImageIcon mBishop = new ImageIcon("Black_Bishop.png");
          
        ImageIcon aPioni = new ImageIcon("White_Pawn.png");
          ImageIcon aKing = new ImageIcon("White_King.png");
          ImageIcon aQueen = new ImageIcon("White_Queen.png");
          ImageIcon aRook = new ImageIcon("White_Rook.png");
          ImageIcon aHorse = new ImageIcon("White_Horse.png");
          ImageIcon aBishop = new ImageIcon("White_Bishop.png");
          
        
    switch(Epipedo){
    
        case "Εύκολο" :
            
            
            board[1][5].setIcon(mPioni);
            board[1][6].setIcon(mPioni);
            board[1][7].setIcon(mPioni);
            board[0][6].setIcon(mKing);
            board[4][5].setIcon(mQueen);
            
            
            board[6][0].setIcon(aPioni);
            board[6][1].setIcon(aPioni);
            board[6][2].setIcon(aPioni);
            board[7][1].setIcon(aKing);
            board[5][3].setIcon(aQueen);
            
            
            
            
            
            break;
            
            
        case "Ενδιάμεσο":
            
            board[0][0].setIcon(mRook);
            board[0][7].setIcon(mRook);
            board[0][2].setIcon(mBishop);
            board[0][5].setIcon(mBishop);
            board[1][1].setIcon(mPioni);
            board[1][0].setIcon(mPioni); 
            board[1][6].setIcon(mPioni);
            board[2][7].setIcon(mPioni);
            board[3][7].setIcon(mPioni);
            board[3][3].setIcon(mPioni);
            board[3][4].setIcon(mPioni);
            board[2][2].setIcon(mPioni);
            board[2][4].setIcon(mHorse);
            board[2][3].setIcon(mKing);
            
            board[4][0].setIcon(aPioni);
            board[6][1].setIcon(aPioni);
            board[6][2].setIcon(aPioni);
            board[6][4].setIcon(aPioni);
            board[6][5].setIcon(aPioni);
            board[3][5].setIcon(aPioni);
            board[5][2].setIcon(aPioni);
            board[6][6].setIcon(aRook);
            board[7][3].setIcon(aRook);
            board[5][5].setIcon(aBishop);
            board[1][5].setIcon(aQueen);
            board[5][1].setIcon(aHorse);
            board[5][4].setIcon(aHorse);
            board[7][1].setIcon(aKing);
            
            
            
            
            
            break;
            
        
        case ("Δύσκολο"):
            
            board[1][5].setIcon(mPioni);
            board[2][1].setIcon(mPioni);
            
            board[4][2].setIcon(mHorse);
            board[4][6].setIcon(mHorse);
            board[6][4].setIcon(mHorse);
            
            board[2][7].setIcon(mQueen);
            board[7][7].setIcon(mQueen);
            
            board[0][3].setIcon(mBishop);
            
            board[2][4].setIcon(mKing);
            
            
            
            board[1][3].setIcon(aHorse);
            board[2][6].setIcon(aHorse);
            board[5][1].setIcon(aHorse);
            board[5][5].setIcon(aHorse);
            
            board[5][0].setIcon(aQueen);
            board[0][1].setIcon(aQueen);
            board[7][4].setIcon(aQueen);
            board[0][7].setIcon(aQueen);
            
            board[3][7].setIcon(aRook);
            
            board[5][7].setIcon(aBishop);
            
            board[2][2].setIcon(aKing);
            
            
            break;
    
    }
        
    
    }
    
    public final void XromatismosTamplo(String xroma){
        
      int rb=-1;
      int cb=-1;
      
      if(elegxosBoitheias){
      
      if(Epipedo.equalsIgnoreCase("Εύκολο")){
      
      rb=5;
      cb=3;
      }
      
      else if(Epipedo.equalsIgnoreCase("Ενδιάμεσο")){
      
      rb=5;
      cb=4;
      }
      
      else{rb=5;
      cb=7;}
      
      }
        
        
    
     //gia 8x8
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                
                if(rb!=-1 && row==rb && col==cb){}
                
                else{
                //an h seira einai zugos arithmos
                if(row%2==0){
                   
                    //an h sthlh einai monos arithmos
                    if(col%2==1){
                       
                        //elegxos gia to xroma kai xromatismos twn skoyroxromwn tetragonon
                        switch (xroma) {
                            case "Λευκά-Πράσινα" -> {
                                board[row][col].setBackground(Color.GREEN);
                                board[row][col].setOpaque(true);
                                board[row][col].setBorderPainted(false);
                            }
                            case "Λευκά-Μαύρα" -> {
                                board[row][col].setBackground(Color.black);
                                board[row][col].setOpaque(true);
                                board[row][col].setBorderPainted(false);
                            }
                            case "Λευκά-Μπλε" -> {
                                board[row][col].setBackground(Color.blue);
                                board[row][col].setOpaque(true);
                                board[row][col].setBorderPainted(false);
                            }
                            default -> {
                            }
                        }
                }
                    //xromatismos twn leykon tetragonon
                    else{ board[row][col].setBackground(Color.white);
                                board[row][col].setOpaque(true);
                                board[row][col].setBorderPainted(false);}
   }
                //an h graammi einai monos arithmos
                else{
                
                    //an h sthlh einai zugos arithmos
                    if(col%2==0){   
                         
                         //elegxos gia to xroma kai xromatismos twn skoyroxromwn tetragonon
                        switch (xroma) {
                            case "Λευκά-Μαύρα" -> {
                                board[row][col].setBackground(Color.black);
                                board[row][col].setOpaque(true);
                                board[row][col].setBorderPainted(false);
                            }
                            case "Λευκά-Πράσινα" -> {
                                board[row][col].setBackground(Color.green);
                                board[row][col].setOpaque(true);
                                board[row][col].setBorderPainted(false);
                            }
                            case "Λευκά-Μπλε" -> {
                                board[row][col].setBackground(Color.blue);
                                board[row][col].setOpaque(true);
                                board[row][col].setBorderPainted(false);
                            }
                            default -> {
                            }
                        }
                    
                }
                    //xromatismos twn leykon tetragonon
                    else{board[row][col].setBackground(Color.white);
                                board[row][col].setOpaque(true);
                                board[row][col].setBorderPainted(false);}
   
   }
         
  
 
   
            
            
            }}
            
            
            }
    
    
    
    
    }
    
  


}
