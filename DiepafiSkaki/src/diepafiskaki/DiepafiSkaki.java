package diepafiskaki;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class DiepafiSkaki extends JFrame implements ActionListener{
   
    //to basiko frame gia to tamplo
    private static JFrame tamplo;
    //o pinakas me ta koympia gia to tamplo
    private JButton[][] board;
    //panel gia na mpoune ta koympia
    private JPanel chessBoard;
    //to label poy deixnei ti symbainei ana pasa stigmi sto paixnidi
    private JLabel turnLabel;
    //to xroma tou tamplo
     private String xroma ;
    //elegxos gia to poios paizei
    private boolean whiteTurn;
    //ta koympia paraitisis
    private JButton surrw,surrb;
    //koympi gia symfonia isopalias
    private JButton isopalia;
    //koumpi gia boitheia
    private JButton help;
    //koumpi gia epistrofi sto menou
    private JButton menu;
    private int menuCounter=0;
    //koumpi gia anairesi kinhshs
    private JButton undo;
    
    private String unImg1,unImg2,unText;
    private int unR1,unR2,unC1,unC2,unIsoCounter,unRbas,unCbas;
    private boolean unIsoCheck,unCheck;
    
    //metablites gia ton elegxo ths sumfonhs isopalias
    private boolean isoCheck=false;
    private int isoCounter=0;
    //to combobox gia thn epilogi kommatiou sthn anabathmisi pioniou
    private JComboBox promotion;
    //to frame poy emfanizetai otan ena pioni anabathmizetai
    private JFrame jpromotion;
    //to panel poy mpainei pano sto jpromotion
    private JPanel ppromotion;
    //oi epiloges toy promotion
    private final String[] promOpt;
    //to koympi gia thn epilogi toy kommatiou anabathmisis
    private JButton bpromotion;
    //h seira kai h steilh opoy tha ginei h anabathmisi
    private int promr=0,promc=0;
    //elegxos gia to ti xroma einai to pioni poy anabathmizetai
    private String promcc="" ;
    //elegxos an epilegetai kommati pros metakinisi h tetragono gia metakinisi
    private boolean check=false;
    //h eikona toy kommatiou poy metakineitai
    private String img="";
    //h seira kai h steilh toy kommatiou pou metakineitai
    private   int tempr,tempc;
    //elegxos gia to an yparxei endiameso kommati
    private  boolean endiameso;
    //h seira kai h steilh toy basilia
    private  int rbas,cbas;
    //h kateythinsi ths apeilis toy basilia
    private   int katApeilis=-1;
    private int katApeilis1=-1;
    //elegxos an o basilias brisketai se sax
    private   boolean sax=false;
    //h seira kai h steilh toy kommatiou poy apeilei ton basilia
    private   int rap,cap,rap1,cap1;
    private boolean checkFF=false;
    
    
    private   boolean kaleitaiApoMat=false;
    
    private static  Kinhseis k ;
    private String kinText;
    private int guros=0;
    private String unKinText;
    private String kinApot="";
    
    private static Xronos xronos;
    
    private int epilogiXronou=0;
   
     
    
 
 
    public DiepafiSkaki() {
        
        k.setText(" ");
        k.setVisible(false);
        k.resetLabel();
        
        //epiloges gia anabathmisi
        promOpt = new String[]{"Βασίλισσα", "Άλογο", "Πύργος", "Αξιωματικός"};
        
        //apokripsi toy parathirou skakieras apo proigoumeno paixnidi
        tamplo.setVisible(false);
        
       
      
    }
    
     public DiepafiSkaki(String a,int xron){
         
         epilogiXronou=xron;
         
         //epiloges gia anabathmisi
        promOpt = new String[]{"Βασίλισσα", "Άλογο", "Πύργος", "Αξιωματικός"};
     
        //apokripsi toy parathirou skakieras apo proigoumeno paixnidi
   tamplo.setVisible(false);
   
   k.setVisible(false);
   k.setText(" ");
   k.resetLabel();
       
         
        //dhmioyrgia neoy tamplo me xroma skakieras a 
     SetupBoard(a);
     
     }
    
    
  
    //dhmiourgia tamplo
      public final void SetupBoard(String x){
          
         xroma=x; 
        //dhmiourgia neou frame gia to tamplo 
        tamplo = new JFrame();  
          
        //dhmiourgia frame gia anabathmisi pionioy
        jpromotion = new JFrame();
        jpromotion.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        jpromotion.setPreferredSize(new Dimension(200,200));
        
        
        ppromotion = new JPanel();
        
        ppromotion.setPreferredSize(new Dimension(200,200));
        ppromotion.setLayout(new BorderLayout());
        
        
        //dhmiourgia combobox me tis epiloges anabathmisis
        promotion = new JComboBox(promOpt);
        promotion.setToolTipText("Επιλέξτε σε ποιο κομμάτι θα θέλατε να ανβαθμιστεί το πιόνι");
        
        //koumpi poy tha oristikopoiei thn anabathmisi
        bpromotion = new JButton("Επιλογή");
        bpromotion.addActionListener(this);
        bpromotion.setToolTipText("Πατήστε εδώ μόλις βεβαιωθείτε για την επιλογή σας");
       
       
        
        //prostheto ta stoixeia sto panel
        ppromotion.add(promotion,BorderLayout.NORTH);
        
        ppromotion.add(bpromotion,BorderLayout.SOUTH);
        
        //prostheto to panel sto frame
        jpromotion.add(ppromotion);
        
        jpromotion.pack();
        
       
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
        
        //klhsh sunartisi gia ton xromatismo ths skakieras
        XromatismosTamplo();
        
      
    //panel gia taa koympia paraitisis/isopalias 
 JPanel g =new JPanel(); 
 
 g.setPreferredSize(new Dimension(80,100));
 g.setLayout(new BorderLayout());
 
 //dhmiourgia koympion paraitisis/isopalias/undo me action listener
 surrw = new JButton();
 surrw.setPreferredSize(new Dimension(80,180));
 surrw.setIcon(new ImageIcon("surr.png"));
 surrw.setToolTipText("Πατήστε εδώ για να παραιτηθείτε");
 surrw.addActionListener(this);
 
 
  surrb = new JButton();
 surrb.setPreferredSize(new Dimension(80,180));
  surrb.setIcon(new ImageIcon("surr.png"));
  surrb.setToolTipText("Πατήστε εδώ για να παραιτηθείτε");
  surrb.setEnabled(false);
  surrb.addActionListener(this);
  
  //panel gia ta koympia isopalias/undo
   JPanel isopUndo =new JPanel(); 
 
 isopUndo.setPreferredSize(new Dimension(80,200));
 isopUndo.setLayout(new BorderLayout());
  
  
  isopalia = new JButton("ΙΣΟΠΑΛΙΑ");
  isopalia.setPreferredSize(new Dimension(80,100));
 isopalia.setToolTipText("Πατήστε εδώ αν θέλετε να κάνετε ισοπαλία");
 isopalia.addActionListener(this);
 
 undo = new JButton("Undo");
 undo.setPreferredSize(new Dimension(80,100));
 undo.setToolTipText("Πατήστε εδώ για να αναιρέσετε την προηγούμενη κίνηση");
 undo.addActionListener(this);
 undo.setEnabled(false);
 
 isopUndo.add(isopalia,BorderLayout.NORTH);
 isopUndo.add(undo,BorderLayout.SOUTH);
 
 
 //eisagogi koympion sto panel
 g.add(surrw,BorderLayout.SOUTH);
  g.add(surrb,BorderLayout.NORTH);
  g.add(isopUndo,BorderLayout.CENTER);
  
        

// Arxikopoiish turnLabel
        
JPanel h =new JPanel(); 
h.setLayout(new BorderLayout());





        turnLabel = new JLabel("Παίζουν τα Λευκά");
        turnLabel.setHorizontalAlignment(JLabel.CENTER);
        
        help = new JButton("help");
        help.addActionListener(this);
        help.setToolTipText("Πατήστε εδώ για να διαβάσετε τις οδηγίες για τη χρήση της εφαρμογής");
        
        menu= new JButton("menu");
        menu.addActionListener(this);
        menu.setToolTipText("Πατήστε δύο φορές συνεχόμενα για να επιστρέψετε στο μενού");
 
        h.add(turnLabel,BorderLayout.CENTER);
        h.add(help,BorderLayout.WEST);
        h.add(menu,BorderLayout.EAST);
        
        // Eisagogh label kai tamplo sto frame
        tamplo.add(chessBoard, BorderLayout.CENTER);
       
        tamplo.add(h, BorderLayout.SOUTH);
        tamplo.add(g,BorderLayout.EAST);
        
        if(epilogiXronou!=0){
         xronos = new Xronos(epilogiXronou,xroma);
       xronos.setLocation(tamplo.getLocation().x+600, tamplo.getLocation().y);
       xronos.setVisible(true);}
 
        // Xaraktiristika toy frame
        tamplo.setTitle("Skaki");
        tamplo.setSize(600, 600);
        tamplo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tamplo.setVisible(true);
 
        // ksekinima tou paixnidiou
        whiteTurn = true;
        initializeBoard();
        
      
      
      
      }
 
   
      //arxikopoiisi tamplo
    private void initializeBoard() {
        
        // Eisagogh ton kommation
        
        
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (row==6){
                board[row][col].setIcon(new ImageIcon("White_Pawn.png"));
                
                }
                else if(row==1){
                board[row][col].setIcon(new ImageIcon("Black_Pawn.png"));
                
                }
                
                if(row==7 ){
                    switch (col) {
                        case 0, 7 -> board[row][col].setIcon(new ImageIcon("White_Rook.png"));
                        case 1, 6 -> board[row][col].setIcon(new ImageIcon("White_Horse.png"));
                        case 2, 5 -> board[row][col].setIcon(new ImageIcon("White_Bishop.png"));
                        case 3 -> board[row][col].setIcon(new ImageIcon("White_Queen.png"));
                        default -> board[row][col].setIcon(new ImageIcon("White_King.png"));
                    }
                
                
                }
                
                else if(row==0){
                
                    switch (col) {
                        case 0, 7 -> board[row][col].setIcon(new ImageIcon("Black_Rook.png"));
                        case 1, 6 -> board[row][col].setIcon(new ImageIcon("Black_Horse.png"));
                        case 2, 5 -> board[row][col].setIcon(new ImageIcon("Black_Bishop.png"));
                        case 3 -> board[row][col].setIcon(new ImageIcon("Black_Queen.png"));
                        default -> board[row][col].setIcon(new ImageIcon("Black_King.png"));
                    }
                
                }
                    
            }
        }
    }
 
  
    
   
        
       //anabathmisi pioniou
       public void promotion(int row,int col,String kommati){
       
      //anabathmisi pioniou analoga me thn epilogi toy xrhsth 
       switch(kommati){
       
           case "Βασίλισσα" -> {
               if(promcc.equals("w")){
                   img="White_Queen.png";}
               else{img="Black_Queen.png";}
            }
               
            case "Πύργος" -> {
                if(promcc.equals("w")){
                    img="White_Rook.png";}
                else{img="Black_Rook.png";}
            }
               
             case "Αξιωματικός" -> {
                 if(promcc.equals("w")){
                     img="White_Bishop.png";}
                 else{img="Black_Bishop.png";}
            }
               
             case "Άλογο" -> {
                 if(promcc.equals("w")){
                     img="White_Horse.png";}
                 else{img="Black_Horse.png";}
            }
       
       
       
       
       }
       
       //oristikopoiisi ths anabathmisis
       kinisi(row,col);
       
       }
  
       
       
   
        //an patithei kapoio koumpi
    @Override
    public void actionPerformed(ActionEvent e) {
        
     
// To koumpi poy patithike
        JButton button = (JButton) e.getSource();
        
        //an patithike to koympi epistrofis sto menu
        if(button==menu){
        
            if(menuCounter==1){
            tamplo.dispose();
            k.dispose();
            if(xronos!=null){
            xronos.StopTime();
            xronos.dispose();}
            
            Menou();}
            
            else{
            
            turnLabel.setText("Είστε σίγουροι ότι θέλετε να επιστρέψετε στο μενού?");
            menuCounter++;
            }
            
            
            
        return;
        }
        
        
        //an patithike to koympi boitheias
        if(button==help){
        
        new help().setVisible(true);
        
       
        
        return;
        
        }
        //an patithike to koympi isopalias
        if(button==isopalia){
          
            //an o paiktis proteinei isopalia ston antipalo
            if(isoCounter==0){
                
                
            isoCheck=true;
            
            isopalia.setText("1/2");
            
            //den mporei o idios paiktis ston idio gyro na paatisei >1 fores to koympi
            isopalia.setEnabled(false);}
            
            //an o paiktis apodexetai thn protash isopalias apo ton antipalo
            else if(isoCounter==1){
                
                isopalia.setText("2/2");
            
                //to paixnidi teleionei me sunthiki=3 (isopalia)
            new TelosPaixnidiou("Black",3,xroma,epilogiXronou).setVisible(true);
            
            k.setText(k.getText() + "    1/2-1/2");
           
            if(xronos!=null){
               xronos.StopTime();
           xronos.dispose();}
            //apenergopoiiisi tou tamplo kai twn kommation
           disableBoard();
            }
        
        return;
        }
        
        //an o aspros patise to koympi paraitisis
       if(button==surrw){
       
           if(!checkFF){
           checkFF=true;
           
           turnLabel.setText("Είστε σίγουρος ότι θέλετε να παραιτηθείτε?");
           
           return;
           }
           
           //to paixnidi teleionei me niki mavrou logw paraitisis
           new TelosPaixnidiou("Black",2,xroma,epilogiXronou).setVisible(true);
           
           if(xronos!=null){
               xronos.StopTime();
           xronos.dispose();}
           
           k.setText(k.getText() + "    0-1");
           
           
           //apenergopoiiisi tou tamplo kai twn kommation
           disableBoard();
           
           
           
           return;
       
       
       }
       
       //an o mavros pathsei to koympi paraitisis
       if(button==surrb){
           
            if(!checkFF){
           checkFF=true;
           
           turnLabel.setText("Είστε σίγουροι ότι θέλετε να παραιτηθείτε?");
           
           return;
           }
            
            if(xronos!=null){
               xronos.StopTime();
           xronos.dispose();}
       
       //to paixnidi teleionei me niki asprou logw paraitisis
        new TelosPaixnidiou("White",2,xroma,epilogiXronou).setVisible(true);
        
        k.setText(k.getText() + "    1-0");
        
        
        
        //apenergopoiiisi tou tamplo kai twn kommation
        disableBoard();
           return;
       
       }
       
       //an o xrhsths epileksei to koumpi oristikopoiisis anabathmisis pioniou
       if(button == bpromotion){
        
           //krupse to frame anabathmisis
        jpromotion.setVisible(false);
        
        //klhsh sunartisis anabathmisis
        promotion(promr,promc,(String) promotion.getSelectedItem());
        
        return;
        
        
        }
       
       if(button==undo){
       
           
           undo();
           
           
       
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
        
        
 
        // an paizei o aspros
        if (whiteTurn) {
            
            
           
            //an epiilexthike aspro kommati
            if (board[row][col].getIcon() != null && board[row][col].getIcon().toString().contains("White")) {
                
                
               
             
                //poio kommati epilexthike
                img = board[row][col].getIcon().toString();
                
                //to check deixnei oti epilexthike kommati pros kinisi
                check=true;
                
                //apothikevo th grammh kai stili tou kommatiou
                tempr=row;
                tempc=col;
                
                //klhsh sunartisis emfanisis pithanwn kinisewn
                PithanesKiniseis("w");
                
                
                
                
            } 
           
           //elegxos gia to an exei epilegei kommati gia metakinisi
           else if(check==true){
              
            //elegxos gia to an to kommati pros metakinisi einai pioni  
            if(img.contains("Pawn"))  { 
                
                //elegxos an to tetragono metakinisis einai keno
            if(board[row][col].getIcon() == null) { 
                
                //elegxos an to pioni einai sthn arxiki tou thesi
                if(tempr==6){
                    
                    //elegxos an to pioni kineitai ena h duo tetragona mprosta
                    if((row==tempr-1 || row==tempr-2) && col==tempc){
                        
               //klhsh sunartisis kinisis         
            kinisi(row,col);}
                
                    //to tetragono metakinisis den einai prosbasimo apo to pioni
                else{turnLabel.setText("Το πιόνι δεν μπορεί να μετακινηθεί εκεί");}
                } 
               
                
             //an den einai to pioni sthn arxiki tou thesi   
             else{
                
                    //elegxos an to pioni kineitai ena tetragono mprosta
                if(row==tempr-1 && col==tempc){ 
                    
                    //an to pioni eftase thn teliki grammi
                    if(row==0){
                    
                        //dino tis katallhles times stis metablites poy xreiazontai gia thn anabaathmisi
                        promcc="w";
                        promr=row;
                        promc=col;
                        
                        //emfanisi frame anabathmisisi
                       jpromotion.setVisible(true);
                       
                       return;
                       
                       
                    }
                
                    //klhsh sunratisis kinhshs
                kinisi(row,col);
                
                }
                
                //elegxos gia en dieleysi
                else if(tempr==3 && row==2 && (col==tempc+1 || col==tempc-1 ) && board[row+1][col].getIcon()!=null && board[row+1][col].getIcon().toString().contains("Black_Pawn")){
                
                  //aixmalotisi kai kinhsh  
                board[row+1][col].setIcon(null);
                kinisi(row,col);
                
                }
                
                
                //to tetragono metakinisis den einai prosbasimo sto pioni
                else{turnLabel.setText("Το πιόνι δεν μπορεί να μετακινηθεί εκεί");}
                
                
                }
            
            }
           
            //an to pioni den metakineitai apla, alla aixmalotiizei
            else{  
                
                //elegxos an to pioni kineitai ena tetragono diagonia mprosta
                if(img.contains("Pawn") && row==tempr-1 && (col==tempc+1 || col==tempc-1)){
                 
                    
                    //an eftase sthn telikh grammh
                    if(row==0){
                    
                    
                      //dino tis katalalliles times stis metablites gia thn anabathmisi
                    promcc="w";
                        promr=row;
                        promc=col;
                        
                        //klhsh sunartisis anabathmisis
                       jpromotion.setVisible(true);
                       
                       return;
                    
                    
                    
                    
                    }
                    
                    
                //klhsh sunartisis kinisis    
            kinisi(row,col);
            }
                //to tetragono metakinisis den einai prosbasimo apo to pioni
                else{turnLabel.setText("Το πιόνι δεν μπορεί να μετακινηθεί εκεί");}
          }}
           
            //an to kommati pros metakinisi einai pyrgos
           if(img.contains("Rook") ) {
                
               //elegxos an o pyrgos kineitai sthn idia stili h thn idia grammh
                if(row==tempr || col==tempc){
           
                    //klhsh sunartisis kinisis pyrgou
                kinisiPyrgou(row,col);}
                
                //to tetragono metakinisis den einai prosbasimo apo ton pyrgo
                else{turnLabel.setText("Ο πύργος δεν μπορεί να μετακινηθεί εκεί");}
           }
            
           //an to kommati einai alogo
           if(img.contains("Horse") ){
               
               //elegxos an to alogo kineitai me γ pros opoiadhpote kateythinsi
               if(((row==tempr-2 || row==tempr+2) && (col==tempc+1 || col==tempc-1))   || ((row==tempr-1 || row==tempr+1) && (col==tempc+2 || col==tempc-2))){
           
                   //klhsh sunartisis kinisis
          kinisi(row,col);}
               
               //to tetragono metakinisis den einai prosbasimo apo to alogo
               else{turnLabel.setText("Το άλογο δεν μπορεί να μετακινηθεί εκεί");}
           
           }
           
           //elegxos an to kommati einai aksiomatikos
           if(img.contains("Bishop")){
                
               //elegxos an o aksiomatikos kineitai ektos ths idias grammhs
                if(row!=tempr){
           
                    //klhsh sunartisis kinisisi aksiomatikou
                kinisiAksiomatikou(row,col);}
                
                
                //to tetragono metakinisis den einai prosbasimo apo ton aksiomatiko
                else{turnLabel.setText("Ο αξιωματικός δεν μπορεί να μετακινηθεί εκεί");}
                
           }
           
           //an to kommati einai basilissa
           if(img.contains("Queen")){
           
               //klhsh sunartisis kinisis basilissas
          kinisiBasilissas(row,col);
           
           }
           
           //an to kommati einai basilias
           if(img.contains("King")){
             
               
               //klhsh sunartisis kinisis basilia
               kinisiBasilia(row,col,"White","Black");
             
           }
           
           }
         }
        
        //an paizei o mavros
        else{
            
            
            
        
            //elegxos gia to an epilexthike mavro kommati
            if (board[row][col].getIcon() != null && board[row][col].getIcon().toString().contains("Black")){
            
            //apothikeusi toy kommatiou
             img = board[row][col].getIcon().toString();
                
             //to check deixnei oti epilexthike kommati
             check=true;
             
             //apothikeusi grammhs kai stilis toy kommatiou
                tempr=row;
                tempc=col;
                
                
                //klhsh sunartisis gia thn emfanisi pithanwn kinhsewn
                PithanesKiniseis("b");
            
            }
             
            //an exei epilexthei kommati pros metakinisi
           else if(check==true){
                
          //elegxos gia to an to kommati einai pioni   
            if(img.contains("Pawn"))  { 
                
                //elegxos to pioni apla kineitai
            if(board[row][col].getIcon() == null) { 
                
                //elegxos an to pioni einai sthn arxiki tou thesi
                if(tempr==1){
                    
                    //elegxos an to pioni kineitai ena h duo tetragona mprosta
                    if((row==tempr+1 || row==tempr+2) && col==tempc){
                 
                        //klhsh sunartisis kinisis
            kinisi(row,col);}
                    
    
                //to tetragono metakinisis den einai prosbasimo apo to pioni
                else{turnLabel.setText("Το πιόνι δεν μπορεί να μετακινηθεί εκεί");}
                } 
               
                
             //an den einai to pioni sthn arxiki tou thesi   
             else{
                
                    //elegxos an to pioni kineitai ena tetragono mprosta
                if(row==tempr+1 && col==tempc){ 
                    
                    
                    //an to pioni eftase sthn telikh grammh
                    if(row==7){
                    
                    
                        //dino tis katalliles times stis metablites anabathmisis
                    promcc="b";
                        promr=row;
                        promc=col;
                        
                        //emfanisi frame anabathmisis
                       jpromotion.setVisible(true);
                       
                       return;
                    
                    
                    
                    
                    }
                    
                    
                    //klhsh sunartisis kinisis
                    kinisi(row,col);}
                
                //elegxos gia en dieleysi
                 else if(tempr==4 && row==5 && (col==tempc+1 || col==tempc-1 ) && board[row-1][col].getIcon()!=null && board[row-1][col].getIcon().toString().contains("White_Pawn")){
                
                    //aixmalotisi kai kinisi
                board[row-1][col].setIcon(null);
                kinisi(row,col);
                
                }
                 
                 //to tetragono metakinisis den einai prosbasimo apo to pioni
                else{turnLabel.setText("Το πιόνι δεν μπορεί να μετακινηθεί εκεί");}
                
                
                }
            
            }
           
            //an to pioni den metakineitai apla, alla aixmalotiizei
            else{  
                
                //elegxos an to pioni metakineitai ena tetragono diagonia mprosta
                if(img.contains("Pawn") && row==tempr+1 && (col==tempc+1 || col==tempc-1)){
                 
                    //an to pioni eftase sthn telikh grammh
                    if(row==7){
                    
                    //dino tis katalliles times stis metablites anabathmisis
                    promcc="b";
                        promr=row;
                        promc=col;
                        
                        //emfanisi frame anabathmisis
                       jpromotion.setVisible(true);
                       
                       return;
                    
                    
                    
                    
                    }
                    
                    
                    
              //klhsh sunartisis kinisis      
            kinisi(row,col);
            }
                //to tetragono metakinisis den einai prosbasimo apo to pioni
                else{turnLabel.setText("Το πιόνι δεν μπορεί να μετακινηθεί εκεί");}
          }}
            
            
            
           //an to kommati einai pyrgos
            if(img.contains("Rook") ) {
                
                //elegxos an o pyrgos kineitai sthn idia grammh h idia stili
                if(row==tempr || col==tempc){
           
                    //klhsh sunartisis kinisis pyrgou
                kinisiPyrgou(row,col);}
                
                //to tetragono metakinisis den einai prosbasimo apo ton pyrgo
                else{turnLabel.setText("Ο πύργος δεν μπορεί να μετακινηθεί εκεί");}
           }
            
            //an to kommati einai alogo
            if(img.contains("Horse") ){
               
                //elegxos an to alogo kineitai me γ pros opoiadipote kateythinsi
               if(((row==tempr-2 || row==tempr+2) && (col==tempc+1 || col==tempc-1))   || ((row==tempr-1 || row==tempr+1) && (col==tempc+2 || col==tempc-2))){
           
                   //klhsh sunartisis kinisis
          kinisi(row,col);}
               
               //to tetragono metakinisis den einai prosbasimo apo to alogo
               else{turnLabel.setText("Το άλογο δεν μπορεί να μετακινηθεί εκεί");}
           
           }
            
            //an to kommati einai aksiomatikos
            if(img.contains("Bishop")){
                
                //elegxos an o aksiomatikos kineitai ektos ths idias grammis
                if(row!=tempr){
           
                    //klhsh sunartisis kinisis aksiomatikou
                kinisiAksiomatikou(row,col);}
                
                //to tetragono metakinisis den einai prosbasimo apo ton aksiomatiko
                else{turnLabel.setText("Ο αξιωματικός δεν μπορεί να μετακινηθεί εκεί");}
                
           }
            
            //elegxos an to kommati einai basilissa
            if(img.contains("Queen")){
           
           //klhsh sunartisis kinisis basilissas
           kinisiBasilissas(row,col);
           
           
           }
            
            //elegxos an to kommati einai basilias
            if(img.contains("King")){
             
                //klhsh sunartisis kinisis basilia
              kinisiBasilia(row,col,"Black","White");
           } 
           
           }
        
        }
        
      
       
    }
     
    //sunarthsh kinisis
    public void kinisi(int row,int col){
        
        //an kapoios basilias apeileitai
        if(sax){
            
            
             String img1=null;
             
             
            String img2=board[tempr][tempc].getIcon().toString();
            
            //an paizei o aspros
        if(whiteTurn){
            
            //an den kounietai o basilias
            if(!(img2.contains("White_King"))){
           
            
            if(board[row][col].getIcon()!=null){ img1=board[row][col].getIcon().toString();}
            
            //bazo sto tetragono metakinisis ena prosorino kommati
            board[row][col].setIcon(new ImageIcon("White_temp"));
            
            //afairo to kommati poy metakineitai apo to tamplo
            board[tempr][tempc].setIcon(null);
            
            //an apeileitai o basilias
             if(!Apeileitai(rbas,cbas,"White","Black")){
             
                 
                 if(img1!=null){
                     
                     //epistrofi arxikis thesis
             board[row][col].setIcon(new ImageIcon(img1));}
                 
                 else{board[row][col].setIcon(null);}
             
             board[tempr][tempc].setIcon(new ImageIcon(img2));
             
             turnLabel.setText("Πρέπει να αντιμετωπιστεί το σαχ");
             
             return;
             
             }
             
              if(img1!=null){
                     
                     //epistrofi arxikis thesis
             board[row][col].setIcon(new ImageIcon(img1));}
                 
                 else{board[row][col].setIcon(null);}
             
             board[tempr][tempc].setIcon(new ImageIcon(img2));
             
             
            
            
        
        
        }}
        //an paizei o mavros
        else{
            
            //an den metakineitai o basilias
            if(!(img2.contains("Black_King"))){
                
                
             if(board[row][col].getIcon()!=null){ img1=board[row][col].getIcon().toString();}
        
             //bazo ena prosorino mavro kommati sto tetragono metakinisis
          board[row][col].setIcon(new ImageIcon("Black_temp"));
          
          //afairo to kommati poy metakineitai
           board[tempr][tempc].setIcon(null);
            
           //an apeileitai o basilias
             if(!Apeileitai(rbas,cbas,"Black","White")){
             
                 //epistrofi arxikis thesis
                 if(img1!=null){
             board[row][col].setIcon(new ImageIcon(img1));}
                 
                 
                 else{board[row][col].setIcon(null);}
             
             board[tempr][tempc].setIcon(new ImageIcon(img2));
             
             
             
             turnLabel.setText("Πρέπει να αντιμετωπιστεί το σαχ");
             
             return;
             
             }
             
             
            if(img1!=null){
             board[row][col].setIcon(new ImageIcon(img1));}
                 
                 
                 else{board[row][col].setIcon(null);}
             
             board[tempr][tempc].setIcon(new ImageIcon(img2));
        
        
        }}
        
        
        }
        
        if(whiteTurn){
         //brisko thn thesi toy basilia
       for(int j=0;j<=7;j++){
            for(int i=0;i<=7;i++){
            
            if(board[j][i].getIcon()!=null && board[j][i].getIcon().toString().contains("White_King")){
             
                rbas=j;
                cbas=i;
                break;
            
            }
            
            }
            
            
            }
        
        if(!dhmiourgeiSax(row,col,"White","Black")){
        
            turnLabel.setText("Η κίνηση θα δημιουργήσει απειλή για τον βασιλιά");
            return;
        
        }
        
        
        }
        
        else{ //brisko thn thesi toy basilia
       for(int j=0;j<=7;j++){
            for(int i=0;i<=7;i++){
            
            if(board[j][i].getIcon()!=null && board[j][i].getIcon().toString().contains("Black_King")){
             
                rbas=j;
                cbas=i;
                break;
            
            }
            
            }
            
            
            }
        
          if(!dhmiourgeiSax(row,col,"Black","White")){
        
            turnLabel.setText("Η κίνηση θα δημιουργήσει απειλή για τον βασιλιά");
            return;
        
        }
        
        }
        
        if(board[row][col].getIcon()!=null){unImg1=board[row][col].getIcon().toString();}
        else{unImg1=null;}
        
        unKinText=k.getText();
        
        unImg2=img;
        
        unR1=row;
        unC1=col;
        
        unR2=tempr;
        unC2=tempc;
        
        unCheck=check;
        
        unIsoCheck=isoCheck;
        
        unIsoCounter=isoCounter;
        
        unText=turnLabel.getText();
        
        unRbas=rbas;
        unCbas=cbas;
        
        //to kommati metaferetai sto tetragono metakinisis
    board[row][col].setIcon(new ImageIcon(img));
    
    checkFF=false;
       //to kommati afaireitai apo tin arxiki toy thesi
            board[tempr][tempc].setIcon(null);
            
            //prepei na ksanaepilexthei kommati
            check=false;
            
            //allagh toy xromatos poy paizei
            whiteTurn=!whiteTurn;
            
            menuCounter=0;
            
            if(whiteTurn && epilogiXronou!=0){
            xronos.setGyros("White");
            }
            else{if(epilogiXronou!=0){xronos.setGyros("Black");}}
            
            undo.setEnabled(true);
            
            XromatismosTamplo();
            
             //energopoiisi toy koympioy isopalias
            isopalia.setEnabled(true);
            
            //an exei patithei apo kapoion paikti to koympi isopalias
            if(isoCheck){
            
            isoCounter++;
            
           
            
            //an exei perasei o gyros ths protasis isopalias
            if(isoCounter==2){
            
                //arxikes sunthikes gia to koympi isopalias
            isopalia.setText("ΙΣΟΠΑΛΙΑ");
            isoCheck=false;
            isoCounter=0;
            
            }
            
            
            }
    
            //an einai seira toy asproy
            if(whiteTurn){
       
                //energopoiio kai apenergopoio to katallilo koympi paraitisis
        surrw.setEnabled(true);
        surrb.setEnabled(false);
       turnLabel.setText("Παίζουν τα Λευκά");
       
       //brisko thn thesi toy basilia
      for(int j=0;j<=7;j++){
            for(int i=0;i<=7;i++){
            
            if(board[j][i].getIcon()!=null && board[j][i].getIcon().toString().contains("White_King")){
             
                rbas=j;
                cbas=i;
                break;
            
            }
            
            }
            
            
            }
            
            //an apeileitai o basilias
            if(!Apeileitai(rbas,cbas,"White","Black")){
                
                turnLabel.setText("Ο λευκός βασιλιάς βρίσκεται σε σαχ");
                
                //o basilias apeileitai
                sax=true;
                
                katApeilis1 = katApeilis;
                
                //klhsh sunartisis gia elegxo mat
                elegxosMat(rbas,cbas,"White","Black");
            
            
            }
            //o basilias den apeileitai
            else{sax=false;}
       
       } 
            //an einai seira toy mavrou
       else{
                
             //energopoio kai apenergopoio to katallilo koympi paraitisis   
         surrb.setEnabled(true);
         surrw.setEnabled(false);
       turnLabel.setText("Παίζουν τα Μαύρα");
       
       //brisko thn thesi toy basilia
      for(int j=0;j<=7;j++){
            for(int i=0;i<=7;i++){
            
            if(board[j][i].getIcon()!=null && board[j][i].getIcon().toString().contains("Black_King")){
             
                rbas=j;
                cbas=i;
                break;
            
            }
            
            }
            
            
            }
            
       //an apeileitai o basilias
            if(!Apeileitai(rbas,cbas,"Black","White")){
                
                turnLabel.setText("Ο μαύρος βασιλιάς βρίσκεται σε σαχ");
                
                //apeileitai o basilias
                sax=true;
                
                
                katApeilis1 = katApeilis;
                rap1=rap;
                cap1=cap;
            
                //klhsh sunartisis gia elegxo mat
                elegxosMat(rbas,cbas,"Black","White");
            
            }
            
            //o basilias den apeileitai
            else{sax=false;}
    }
            
     guros++;        
    
    if(guros==1){        
    

    k.setLocation(tamplo.getLocation().x+600,tamplo.getLocation().y+350);
    k.setVisible(true);
    
    }
    
    kinText=KinText(img,row,col);
   
    
    if(sax){kinText=kinText+"+";}
    
    if(guros==1){
    k.setText(kinText, 1);}
    
    else if(guros%2==0){
        k.setText(kinText, 2);
    }
    else{k.setText(kinText, 3);}
    
    
    k.setText(k.getText()+kinApot);
    
    
    
    }
     
    //synarthsh gia thn kinisi pyrgou
    public void kinisiPyrgou(int row,int col){
    
        //arxikopoihsh metablitis elegxou endiamesou kommatiou
    endiameso=true;
                
                //an kineitai stin idia grammi
                if(row==tempr){
                    
                    //an kineitai deksia
                     if(col>tempc+1){
                     
                         //elegxos gia endiameso kommati
                     for(int j=tempc+1;j<=col-1;j++){
                      if(board[tempr][j].getIcon()!=null){
                          endiameso=false;
                          break;
                      
                      }}}
                       
                     //an kineitai aristera
                  else if(col<tempc-1){
                     
                      //elegxos gia endiameso kommati
                     for(int j=tempc-1;j>=col+1;j--){
                      if(board[tempr][j].getIcon()!=null){
                          endiameso=false;
                          break;
                      
                      }}}}
                 
                //an kineitai sthn idia sthlh
                else if(col==tempc){
                
                    //an kineitai pros ta kato
                if(row>tempr+1){
                
                    //elegxos gia endiameso kommati
                    for(int j = tempr+1; j<=row-1;j++){
                      
                        if(board[j][tempc].getIcon()!=null){
                        endiameso=false;
                        break;
                        }}}
                 
                //an kineitai pros ta pano
                else if(row<tempr-1){
                
                    //elegxos gia endiameso kommati
                for(int j=tempr-1;j>=row+1;j--){
                if(board[j][tempc].getIcon()!=null){
                endiameso=false;
                break;
                }}}}
               
                //an den yparxei endiameso kommati
                if(endiameso){ 
                    
                    //klhsh sunartisis kinisis
                         kinisi(row,col);}
                
                //yparxei endiameso kommati
                else{turnLabel.setText("Υπάρχει ενδιάμεσο κομμάτι");}
    
    }
    
    //synartisi gia thn kinhsh pyrgou
    public void kinisiAksiomatikou(int row,int col){
    
    //poses grammes diafora exoun ta tetragona ekkinisis kai metakinisis
    float p = Math.abs(row-tempr);
    
    //an h metabliti einai alithis shmainei oti den brethike endiameso
           endiameso=true;
           
           //an kineitai diagonia
           if(col==tempc+p || col==tempc-p){
               
               //an kineitai pros ta kato
               if(row>tempr+1){
               
                 //an kineitai pros ta deksia  
               if(col>tempc){
               
                   //elegxos twn tetragonwn poy mesolaboun gia endiameso kommati
               for(int j=1;j<=p-1;j++){
               
                   if(board[tempr+j][tempc+j].getIcon()!=null){
                   endiameso=false;
                   break;
                   }}}
              
               //an kineitai pros ta aristera
               else if(col<tempc){
                   
                   //elegxos twn tetragonwn poy mesolaboun gia endiameso kommati
               for(int j=1;j<=p-1;j++){
               if(board[tempr+j][tempc-j].getIcon()!=null){
                   endiameso=false;
                   break;
               
               }}}}
              
               //an kineitai pros ta pano
               else if(row<tempr-1){
               
                   //an kineitai pros ta deksia
               if(col>tempc){
                   
                   //elegxos twn tetragonwn poy mesolaboun gia endiameso kommati
               for(int j=1;j<=p-1;j++){
               if(board[tempr-j][tempc+j].getIcon()!=null){
                   endiameso=false;
                   break;
               
               }}}
               
               //an kineitai pros ta aristera
               else if(col<tempc){
                   
                   //elegxos twn tetragonwn poy mesolaboun gia endiameso kommati
               for(int j=1;j<=p-1;j++){
               if(board[tempr-j][tempc-j].getIcon()!=null){
                   endiameso=false;
                   break;
               
               }}}}
               
               //an den brethike endiameso kommati pragmatopoihtai h kinhsh
            if(endiameso){   
          kinisi(row,col);
            }
            //an yparxei endiameso kommati
            else{turnLabel.setText("Υπάρχει ενδιάμεσο κομμάτι");}
    }
           
           //an den kineitai diagonia
    else{turnLabel.setText("Ο αξιωματικός δεν μπορεί να μετακινηθεί εκεί");}
    }
       
    
    //sunartisi gia thn kinisi tis basilissas
    public void kinisiBasilissas(int row,int col){
    
        //an h metabliti einai alithis shmainei oti den brethike endiameso kommati
   endiameso=true;
   
          //poses grammes diafora exoun ta tetragona ekkinisis kai metakinisis
           float p = Math.abs(row-tempr);
           
           //pithanes kiniseis gia th basilissa
           if(row==tempr || col==tempc || col==tempc+p || col==tempc-p){
               
               //an kineitai diagonia
           if(row!=tempr && col!=tempc && (col==tempc+p || col==tempc-p)){
               
               
           if(col==tempc+p || col==tempc-p){
               
               //an kineitai pros ta kato
               if(row>tempr+1){
               
                   //an kineitai pros ta deksia
               if(col>tempc){
               
                 //elegxos twn tetragonwn poy mesolaboun gia endiameso kommati  
               for(int j=1;j<=p-1;j++){
               
                   if(board[tempr+j][tempc+j].getIcon()!=null){
                   endiameso=false;
                   break;
                   }}}

               //an kineitai pros ta aristera
               else if(col<tempc){
                   
                   //elegxos twn tetragonwn poy mesolaboun gia endiameso kommati  
               for(int j=1;j<=p-1;j++){
               if(board[tempr+j][tempc-j].getIcon()!=null){
                   endiameso=false;
                   break;
               }}}}
              
               //an kineitai pros ta pano
               else if(row<tempr-1){
               
                   //an kineitai pros ta deksia
               if(col>tempc){
                   
                   //elegxos twn tetragonwn poy mesolaboun gia endiameso kommati  
               for(int j=1;j<=p-1;j++){
               if(board[tempr-j][tempc+j].getIcon()!=null){
                   endiameso=false;
                   break;
               }}}
              
               //an kineitai pros ta aristera
               else if(col<tempc){
                   //elegxos twn tetragonwn poy mesolaboun gia endiameso kommati  
               for(int j=1;j<=p-1;j++){
               if(board[tempr-j][tempc-j].getIcon()!=null){
                   endiameso=false;
                   break;
               }}}}}}
             
           //an kineitai sthn idia grammi
          else if(row==tempr){
              
              //an kineitai pros ta deksia
                     if(col>tempc+1){
                     
                         //elegxos twn tetragonwn poy mesolaboun gia endiameso kommati  
                     for(int j=tempc+1;j<=col-1;j++){
                      if(board[tempr][j].getIcon()!=null){
                          endiameso=false;
                          break;
                      }}}
  
                     //an kineitai pros ta aristera
                  else if(col<tempc-1){
                     
                      //elegxos twn tetragonwn poy mesolaboun gia endiameso kommati  
                     for(int j=tempc-1;j>=col+1;j--){
                      if(board[tempr][j].getIcon()!=null){
                          endiameso=false;
                          break;
                      }}}}
                  
          //an kineitai sthn idia sthlh
                else if(col==tempc){
                
                    //an kineitai pros ta kato
                if(row>tempr+1){
                
                    //elegxos twn tetragonwn poy mesolaboun gia endiameso kommati  
                    for(int j = tempr+1; j<=row-1;j++){
                      
                        if(board[j][tempc].getIcon()!=null){
                        endiameso=false;
                        break;
                        }}}
                 
                //an kineitai pros ta pano
                else if(row<tempr-1){
                
                    //elegxos twn tetragonwn poy mesolaboun gia endiameso kommati  
                for(int j=tempr-1;j>=row+1;j--){
                if(board[j][tempc].getIcon()!=null){
                endiameso=false;
                break;
                }}}}
              
           //an den brethike endiameso kommati kane thn kinhshs
            if(endiameso){   
          kinisi(row,col);
            }
            //an brethike endiameso kommati
            else{turnLabel.setText("Υπάρχει ενδιάμεσο κομμάτι");}
    }
           //an den kineitai isia h diagonia
    else{turnLabel.setText("Η βασίλισσα δεν μπορεί να μετακινηθεί εκεί");}
    }
    
    
    //sunartisi gia thn kinisi tou basilia
    public void kinisiBasilia(int row,int col,String colour1,String colour2){
    
        //metabliti gia ton elegxo apeilhs tou tetragonou metakinisis
     boolean apeilh = Apeileitai(row,col,colour1,colour2);
     
     //an kineitai sosta
     if(row==tempr && (col==tempc+1 || col==tempc-1)  || (row==tempr+1 || row==tempr-1) && (col==tempc+1 || col==tempc || col==tempc-1)){
        
         //an den apeileitai to tetragono kane thn kinhsh
     if(apeilh){
     if(row==tempr && (col==tempc+1 || col==tempc-1)){
             kinisi(row,col);
               
               }
               
               else if((row==tempr+1 || row==tempr-1) && (col==tempc+1 || col==tempc || col==tempc-1)){
              kinisi(row,col);
               
               }
     }
     //an apeileitai to tetragono
     else{turnLabel.setText("Ο βασιλιάς δεν μπορεί να μετακινηθεί σε τετράγωνο που απειλείται");}
     
    
     }
     
    //roke
     else{
         
       
         //an o basilias einai aspros
         if(colour1.equals("White")){
             
             
         //an brisketai sthn arxiki tou thesi kai metakineitai sthn arxikh grammi
         if(tempr==7 && tempc==4 && row==7){
          
             //an thelei na kanei roke pros ta deksia
         if(col==6){
         
          //an isxuoun oi proypotheseis gia roke   
         if(board[7][7].getIcon()!=null && board[7][7].getIcon().toString().contains(colour1+"_Rook") && board[7][5].getIcon()==null && board[7][6].getIcon()==null ){
          
             if(Apeileitai(7,4,colour1,colour2) && Apeileitai(7,5,colour1,colour2) && Apeileitai(7,6,colour1,colour2)){
             
                 //kane to roke
             board[7][5].setIcon(new ImageIcon("White_Rook.png"));
               board[7][7].setIcon(null);
               kinisi(row,col);
               return;
         
         }}
         
         }
         //an thelei na kanei roke pros ta aristera
         else if(col==2){
         
           
             //an isxuoun oi proypotheseis gia to roke
         if(board[7][0].getIcon()!=null && board[7][0].getIcon().toString().contains(colour1+"_Rook") && board[7][3].getIcon()==null && board[7][2].getIcon()==null && board[7][1].getIcon()==null ){
          
             if(Apeileitai(7,4,colour1,colour2) && Apeileitai(7,3,colour1,colour2) && Apeileitai(7,2,colour1,colour2)){
             
                 //kane to roke
             board[7][3].setIcon(new ImageIcon("White_Rook.png"));
               board[7][0].setIcon(null);
               kinisi(row,col);
               return;
         
         }}
         
         
         
         }
         
         
         }
         
         
         
         }
         
         //an o basilias einai mavros
          else if(colour1.equals("Black")){
             
          //an o basilias einai stin arxikh tou thesi kai kineitai sthn arxikh grammh
         if(tempr==0 && tempc==4 && row==0){
             
          //an thelei na kanei roke pros ta deksia
         if(col==6){
         
         //an isxuoun oi proypotheseis gia to roke 
         if(board[0][7].getIcon()!=null && board[0][7].getIcon().toString().contains(colour1+"_Rook") && board[0][5].getIcon()==null && board[0][6].getIcon()==null ){
          
             if(Apeileitai(0,4,colour1,colour2) && Apeileitai(0,5,colour1,colour2) && Apeileitai(0,6,colour1,colour2)){
             //kane to roke
             board[0][5].setIcon(new ImageIcon("Black_Rook.png"));
               board[0][7].setIcon(null);
               kinisi(row,col);
               return;
         
         }}
         
         }
         //an thelei na kanei roke pros ta aristera
         else if(col==2){
         
           //an isxuoun oi proypotheseis gia to roke 
         if(board[0][0].getIcon()!=null && board[0][0].getIcon().toString().contains(colour1+"_Rook") && board[0][3].getIcon()==null && board[0][2].getIcon()==null && board[0][1].getIcon()==null ){
          
             if(Apeileitai(0,4,colour1,colour2) && Apeileitai(0,3,colour1,colour2) && Apeileitai(0,2,colour1,colour2)){
             //kane to roke
             board[0][3].setIcon(new ImageIcon("Black_Rook.png"));
               board[0][0].setIcon(null);
               kinisi(row,col);
               return;
         
         }}
         
         
         
         }
         
         
         }
         
         
         
         }
         //an h kinhsh den einai apodekth gia ton basilia
        turnLabel.setText("Ο βασιλιάς δεν μπορεί να μετακινηθεί εκεί");
     }
          
    
    }
    
    //sunartisi elegxou tetragonou gia apeilh
  public boolean Apeileitai(int row,int col,String colour1,String colour2){
    
      //an einai alithis den apeileitai
    boolean check1=true;
    
        //elegxo sthn idia grammi pros ta pano 
        if(row!=0){
    for(int j=row-1;j>=0;j-- ){
        if(board[j][col].getIcon()!=null){
            
            
            if(j==row-1){
                
                //an apeilei basilias
                if(board[j][col].getIcon().toString().contains(colour2 + "_King")){
            
                    
            check1=false;
            return check1;
            }}
       
            //an apeilei pyrgos h basilissa
    if(board[j][col].getIcon().toString().contains(colour2 + "_Rook") || board[j][col].getIcon().toString().contains(colour2 + "_Queen")){
    
        
     rap=j;
     cap=col;
     katApeilis=0;
    check1=false;
    return check1;
    
    }
    
   else if(board[j][col].getIcon().toString().contains(colour1+"_King")){}
    else{break;}
        
        }}
    }
       
        //pros ta kato
      if(row!=7){
      
          
      for(int j=row+1;j<=7;j++ ){
        if(board[j][col].getIcon()!=null){
            
            if(j==row+1){
            
                //an apeilei basilias
            if(board[j][col].getIcon().toString().contains(colour2 + "_King")){
            
            check1=false;
            return check1;
            }}
            
      //an apeilei pyrgos h basilissa      
    if(board[j][col].getIcon().toString().contains(colour2 + "_Rook") || board[j][col].getIcon().toString().contains(colour2 + "_Queen")){
    
        
        rap=j;
     cap=col;
        katApeilis=1;
    check1=false;
    return check1;
    
    }
    
   else if(board[j][col].getIcon().toString().contains(colour1+"_King")){}
    else{break;}
        
        }}
      
      
      }  
    
      //an apeileitai apo thn idia sthlh pros ta aristera
      if(col!=0){
    for(int j=col-1;j>=0;j-- ){
        if(board[row][j].getIcon()!=null){
            
            if(j==col-1){
            
                //an apeileitai apo basilia
            if(board[row][j].getIcon().toString().contains(colour2 + "_King")){
            
                
            check1=false;
            return check1;
            }}
            
            //an apeileitai apo pyrgo h basilissa
    if(board[row][j].getIcon().toString().contains(colour2 + "_Rook") || board[row][j].getIcon().toString().contains(colour2 + "_Queen")){
    
        
        rap=row;
     cap=j;
        katApeilis=2;
    check1=false;
    return check1;
    
    }
    else if(board[row][j].getIcon().toString().contains(colour1+"_King")){}
    else{break;}
        
        }}
    }
        //an apeileitai apo deksia
      if(col!=7){
      
      for(int j=col+1;j<=7;j++ ){
        if(board[row][j].getIcon()!=null){
            
            if(j==col+1){
            
                 //an apeileitai apo basilia
            if(board[row][j].getIcon().toString().contains(colour2 + "_King")){
            
            check1=false;
            return check1;
            }}
            
        //an apeileitai apo pyrgo h basilissa     
    if(board[row][j].getIcon().toString().contains(colour2 + "_Rook") || board[row][j].getIcon().toString().contains("Queen")){
    
        rap=row;
     cap=j;
        katApeilis=3;
    check1=false;
    return check1;
    
    }
    else if(board[row][j].getIcon().toString().contains(colour1+"_King")){}
    else{break;}
        
        }}
      
      
      }
      
   //an apeileitai apo diagonio tetragono pros ta pano   
  if(row!=0){
    
      //pros ta aristera
        if(col!=0){
        
        
        for(int j=1;j<=row;j++){
            
            if(col-j<0 || row-j<0){break;}
            
            if(board[row-j][col-j].getIcon()!=null){
                
                if(j==1){
                
                 if(!kaleitaiApoMat){  
                      //an apeileitai apo pioni h basilia
                if(board[row-j][col-j].getIcon().toString().contains(colour2+"_King") || board[row-j][col-j].getIcon().toString().contains(colour2+"_Pawn") ){
                
                check1=false;
                return check1;
                
                }
                }
                
                }
        
                 //an apeileitai apo aksiomatiko h basilissa
            if(board[row-j][col-j].getIcon().toString().contains(colour2 + "_Bishop") || board[row-j][col-j].getIcon().toString().contains(colour2 + "_Queen")){
            
              rap=row- j;
              cap=col - j;  
              katApeilis=4;  
            check1=false;
            return check1;
            
            
            }
            else if(board[row-j][col-j].getIcon().toString().contains(colour1+"_King")){}
            else{break;}
            }
            
            
        
        
        
        }
        
        
        }
        //pros ta deksia
        if(col!=7){for(int j=1;j<=row;j++){
            
            if(col+j>7 || row-j<0){break;}
            
            if(board[row-j][col+j].getIcon()!=null){
                
                if(j==1){
                    
                    
                if(!kaleitaiApoMat){
                    //an apeileitai apo pioni h basilia
                if(board[row-j][col+j].getIcon().toString().contains(colour2+"_King") || board[row-j][col+j].getIcon().toString().contains(colour2+"_Pawn")){
                
                check1=false;
                return check1;
                
                }}
                
                
                }
        
                //an apeileitai apo aksiomatiko h basilissa
            if(board[row-j][col+j].getIcon().toString().contains(colour2 + "_Bishop") || board[row-j][col+j].getIcon().toString().contains(colour2 + "_Queen")){
            
                rap=row- j;
     cap=col+j;
                katApeilis=5;
            check1=false;
            return check1;
            
            
            }
           else if(board[row-j][col+j].getIcon().toString().contains(colour1+"_King")){}
            
            else{break;}
            }
            
            
        
        
        
        }}
    
    }
  
  //pros ta kato
  if (row!=7){
  
      //pros ta deksia
   if(col!=0){
   
   
   for(int j=1;j<=7;j++){
            
            if(col-j<0 || row+j>7){break;}
            
            if(board[row+j][col-j].getIcon()!=null){
                
                if(j==1){
                
                  if(!kaleitaiApoMat){  
                      //an apeileitai apo pioni h basilia
                if(board[row+j][col-j].getIcon().toString().contains(colour2+"_King") || board[row+j][col-j].getIcon().toString().contains(colour2+"_Pawn") ){
                
                check1=false;
                return check1;
                
                }}
                
                
                }
        
                //an apeileitai apo aksiomatiko h basilissa
            if(board[row+j][col-j].getIcon().toString().contains(colour2 + "_Bishop") || board[row+j][col-j].getIcon().toString().contains(colour2 + "_Queen")){
            
                rap=row+j;
     cap=col-j;
                katApeilis=6;
            check1=false;
            return check1;
            
            
            }
            else if(board[row+j][col-j].getIcon().toString().contains(colour1+"_King")){}
            
            else{break;}
            }
            
            
            
        
        
        
        }
   
   
   
   
   
   }
   
   //pros ta deksia
   if(col!=7){for(int j=1;j<=7;j++){
            
            if(col+j>7 || row+j>7){break;}
            
            if(board[row+j][col+j].getIcon()!=null){
                
                if(j==1){
                
                    if(!kaleitaiApoMat){
                        //an apeileitai apo pioni h basilia
                if(board[row+j][col+j].getIcon().toString().contains(colour2+"_King") ||board[row+j][col+j].getIcon().toString().contains(colour2+"_Pawn") ){
                
                check1=false;
                return check1;
                
                }}
                
                
                }
        
                //an apeileitai apo aksiomatiko h basilissa
            if(board[row+j][col+j].getIcon().toString().contains(colour2 + "_Bishop") || board[row+j][col+j].getIcon().toString().contains(colour2 + "_Queen")){
            
                rap=row+j;
     cap=col+j;
                katApeilis=7;
            check1=false;
            return check1;
            
            
            }
            else if(board[row+j][col+j].getIcon().toString().contains(colour1+"_King")){}
            
            else{break;}
            }
            
            
        
        
        
        }}
  
  
  
  
  
  
  }
  
  
  //elegxo gia apeilh apo alogo
 for(int j=-2;j<=2;j++){
      
      boolean e = true;
  
  if(j==-2 || j==-1)   { 
      
  if(row+j<0){e=false;}}
  
  else if(j==1 || j==2){if(row+j>7){e=false;}}
  
  if(e){
  if(j==-2 || j==+2){
      if(col+1<=7){
      
      if(board[row+j][col+1].getIcon()!=null && board[row+j][col+1].getIcon().toString().contains(colour2 + "_Horse")){
      
          
          rap=row+j;
          cap=col+1;
          katApeilis=8;
      check1=false;
      return check1;
      
      }
      
      
      }
      if(col-1>=0){
      
      if(board[row+j][col-1].getIcon()!=null && board[row+j][col-1].getIcon().toString().contains(colour2 + "_Horse")){
      
          
           rap=row+j;
          cap=col-1;
          katApeilis=8;
      check1=false;
      return check1;
      
      }
      
      
      }}
  
  
  
  
  
  else if(j==1 || j==-1){
  
  
  if(col+2<=7){
      
      if(board[row+j][col+2].getIcon()!=null && board[row+j][col+2].getIcon().toString().contains(colour2 + "_Horse")){
      
          
           rap=row+j;
          cap=col+2;
          katApeilis=8;
      check1=false;
      return check1;
      
      }
      
      
      }
      if(col-2>=0){
      
      if(board[row+j][col-2].getIcon()!=null && board[row+j][col-2].getIcon().toString().contains(colour2 + "_Horse")){
      
          
           rap=row+j;
          cap=col-2;
          katApeilis=8;
      check1=false;
      return check1;
      
      }}
  }
    }}
  
    return check1;
  }
  
  
  //sunartisi gia na elegxei an egine mat
  public void elegxosMat(int row,int col,String colour1,String colour2){
  
     
      //elegxos an yparxei eleyhtero tetragono gia ton basilia
  for(int i=-1;i<=1;i++){
  
   if(row+i > -1 && row+i < 8){
      
  for(int j=-1;j<=0;j++){
      
      if(col+j > -1 && row+j < 8){
      
      if(Apeileitai(row+i,col+j,colour1,colour2)){
      
      if(board[row+i][col+j].getIcon()!=null && board[row+i][col+j].getIcon().toString().contains(colour1)){
       
          
      }
      else{
          System.out.println("asfaf");
          return;}
      
      }
      
      
      }
      
      
      
  
  
  
  }}
  
  
  }
  
  System.out.println(cap1 );
  System.out.println(rap1);
  
  //elegxos an to kommati poy apeilei ton basilia apeileitai apo pioni
  if(colour2.equals("Black")){
  
  if(rap1!=7){
  
      if(cap1!=0){
      
      if(board[rap1+1][cap1-1].getIcon()!=null && board[rap1+1][cap1-1].getIcon().toString().contains("White_Pawn")){
      
          System.out.println("545");
          
      return;
      }
      }
  
     if(cap1!=7){
      
      if(board[rap1+1][cap1+1].getIcon()!=null && board[rap1+1][cap1+1].getIcon().toString().contains("White_Pawn")){
          
          System.out.println("545");
      
      return;
      }
      } 
      
      
  }
      
  
  }
  
  
  else{
  
  
  if(rap1!=0){
  
      if(cap1!=0){
      
      if(board[rap1-1][cap1-1].getIcon()!=null && board[rap1-1][cap1-1].getIcon().toString().contains("Black_Pawn")){
          
          System.out.println("547");
      
      return;
      }
      }
  
     if(cap1!=7){
      
      if(board[rap1-1][cap1+1].getIcon()!=null && board[rap1-1][cap1+1].getIcon().toString().contains("Black_Pawn")){
          
          System.out.println("546");
      
      return;
      }
      } 
      
      
  }
     
  
  
  }
  
  System.out.println("4"); 
  
  
  kaleitaiApoMat=true;
  
  //elegxos apo poy proerxetai h apeilh
  switch(katApeilis1){
  
      //an einai apo pano
      case 0 -> {
          
          
           System.out.println("0");
          for(int j=1;j<=row-rap1;j++){
              
              
              if(!Apeileitai(row-j,col,colour2,colour1)){
                  
                  
                  kaleitaiApoMat=false;
                  return;
                  
              }
              
              
              
              
              
          }
          
          turnLabel.setText("Mat");
          
          new TelosPaixnidiou(colour2,1,xroma,epilogiXronou).setVisible(true);
          
          if(colour1.contains("Black")){
          
              kinApot= "   1-0";
          }
          else{
          kinApot= "   0-1";
          
          }
          
           if(xronos!=null){
               xronos.StopTime();
           xronos.dispose();}
          
          disableBoard();
            }
      
      
      //an einai apo kato
      case 1 -> {
          
           System.out.println("1");
          for(int j=1;j<=rap1-row;j++){
              
              
              if(!Apeileitai(row+j,col,colour2,colour1)){
                  
                  kaleitaiApoMat=false;
                  return;
                  
              }
              
              
              
              
              
          }
          
          turnLabel.setText("Mat");
          
          new TelosPaixnidiou(colour2,1,xroma,epilogiXronou).setVisible(true);
          
           if(colour1.contains("Black")){
          
              kinApot= "   1-0";
          }
          else{
          kinApot= "   0-1";
          
          }
          
            if(xronos!=null){
               xronos.StopTime();
           xronos.dispose();}
            
            
          disableBoard();
            }
      
      //an einai apo deksia
      case 2 -> {
          
           System.out.println("2");
          for(int j=1;j<=col-cap1;j++){
              
              
              if(!Apeileitai(row,col-j,colour2,colour1)){
                  
                  kaleitaiApoMat=false;
                  return;
                  
              }
              
              else if(kalypteiPioni(row,col-j,colour1)){
                  
                  kaleitaiApoMat=false;
                  return;
                  
                  
              }
              
              
              
              
              
          }
          
          turnLabel.setText("Mat");
          
          new TelosPaixnidiou(colour2,1,xroma,epilogiXronou).setVisible(true);
          
           if(colour1.contains("Black")){
          
              kinApot= "   1-0";
          }
          else{
          kinApot= "   0-1";
          
          }
           if(xronos!=null){
               xronos.StopTime();
           xronos.dispose();}
           
           
          disableBoard();
            }
      
      //an einai apo aristera
      case 3 -> {
          
           System.out.println("3");
          for(int j=1;j<=cap1-col;j++){
              
              
              if(!Apeileitai(row,col+j,colour2,colour1)){
                  
                  kaleitaiApoMat=false;
                  return;
                  
              }
              else if(kalypteiPioni(row,col+j,colour1)){
                  
                  kaleitaiApoMat=false;
                  return;
                  
                  
              }
              
              
              
              
              
          }
          
          turnLabel.setText("Mat");
          
          new TelosPaixnidiou(colour2,1,xroma,epilogiXronou).setVisible(true);
          
           if(colour1.contains("Black")){
          
              kinApot= "   1-0";
          }
          else{
          kinApot= "   0-1";
          
          }
           
            if(xronos!=null){
               xronos.StopTime();
           xronos.dispose();}
          
          disableBoard();
            }
      
     
      //an einai apo diagonia pano aristera
     case 4 -> {
         
         System.out.println("4");
         for(int j=1;j<=row-rap1;j++){
             
             if(!Apeileitai(row-j,col-j,colour2,colour1)){
                 
                 kaleitaiApoMat=false;
                 return;
                 
             }
             
             else if(kalypteiPioni(row-j,col-j,colour1)){
                 
                 kaleitaiApoMat=false;
                 return;
                 
                 
             }
             
             
             
         }
         
         turnLabel.setText("Mat");
         
         new TelosPaixnidiou(colour2,1,xroma,epilogiXronou).setVisible(true);
         
          if(colour1.contains("Black")){
          
              kinApot= "   1-0";
          }
          else{
          kinApot= "   0-1";
          }
          
           if(xronos!=null){
               xronos.StopTime();
           xronos.dispose();}
         
         disableBoard();
            }
      
      
     //diagonia pano deksia
      case 5 -> {
          
          System.out.println("5");
          for(int j=1;j<=row-rap1;j++){
              
              if(!Apeileitai(row-j,col+j,colour2,colour1)){
                  
                  kaleitaiApoMat=false;
                  return;
                  
              }
              
              else if(kalypteiPioni(row-j,col+j,colour1)){
                  
                  kaleitaiApoMat=false;
                  return;
                  
                  
              }
              
          }
          
          turnLabel.setText("Mat");
          
          new TelosPaixnidiou(colour2,1,xroma,epilogiXronou).setVisible(true);
          
           if(colour1.contains("Black")){
          
              kinApot= "   1-0";
          }
          else{
          kinApot= "   0-1";
          
          }
           
            if(xronos!=null){
               xronos.StopTime();
           xronos.dispose();}
          
          disableBoard();
            }
      
      //diagonia kato aristera
      case 6 -> {
          
          
          System.out.println("6");
          for(int j=1;j<=rap1-row;j++){
              
              if(!Apeileitai(row+j,col-j,colour2,colour1)){
                  
                  kaleitaiApoMat=false;
                  return;
                  
              }
              
              else if(kalypteiPioni(row+j,col-j,colour1)){
                  
                  kaleitaiApoMat=false;
                  return;
                  
                  
              }
              
          }
          
          turnLabel.setText("Mat");
          
          new TelosPaixnidiou(colour2,1,xroma,epilogiXronou).setVisible(true);
          
           if(colour1.contains("Black")){
          
              kinApot= "   1-0";
          }
          else{
          kinApot= "   0-1";
          
          }
           
            if(xronos!=null){
               xronos.StopTime();
           xronos.dispose();}
          
          disableBoard();
            }
  
      
      
      
      
      
      
      //diagonia kato deksia
      case 7 -> {
          
          System.out.println("7");
          
          for(int j=1;j<=rap1-row;j++){
              
              if(!Apeileitai(row+j,col+j,colour2,colour1)){
                  
                  kaleitaiApoMat=false;
                  return;
                  
              }
              
              else if(kalypteiPioni(row+j,col+j,colour1)){
                  
                  kaleitaiApoMat=false;
                  return;
                  
                  
              }
              
              
              
              
          }
          
          turnLabel.setText("Mat");
          
          
          new TelosPaixnidiou(colour2,1,xroma,epilogiXronou).setVisible(true);
          
           if(colour1.contains("Black")){
          
              kinApot= "   1-0";
          }
          else{
          kinApot= "   0-1";
          
          }
           
            if(xronos!=null){
               xronos.StopTime();
           xronos.dispose();}
          
          disableBoard();
            }
          
      
      //an apeileitai apo alogo
      case 8 -> {
          
          System.out.println("8");
          if(!Apeileitai(rap1,cap1,colour2,colour1)){
              
              kaleitaiApoMat=false;
              return;
              
          }
          
          turnLabel.setText("Mat");
          
          new TelosPaixnidiou(colour2,1,xroma,epilogiXronou).setVisible(true);
          
          
                     if(colour1.contains("Black")){
          
              kinApot= "   1-0";
          }
          else{
          kinApot= "   0-1";
          
          }
                     
                      if(xronos!=null){
               xronos.StopTime();
           xronos.dispose();}
                     
            disableBoard();
          
          
            }
      
     
  }
  
  
  
  kaleitaiApoMat=false;
  
  
  
  
  }
  
  
  public boolean kalypteiPioni(int row,int col,String colour){
  
  boolean check1=false;
  
  if(colour.contains("White")){
     
      if(row==4){
      
      if(board[6][col].getIcon()!=null){
          
          if(board[6][col].getIcon().toString().contains("White_Pawn"))
      
        if(board[5][col].getIcon()!=null && board[4][col].getIcon()!=null){
        
        check1=false;
        return check1;
        
        }
          
        else if(board[5][col].getIcon()==null && board[4][col].getIcon()==null){check1=true;
          return check1;
        }
          
      
      
      }
      
      
      }
      
     
      if(row!=7){
      
       if(board[row+1][col].getIcon()!=null){
           
          if(board[row+1][col].getIcon().toString().contains("White_Pawn")) {
      
        if(board[row][col].getIcon()!=null){
        
        check1=false;
        return check1;
        }
        else{check1=true;
        return check1;}
          
          
          
          }
      
      
      }
      
      }  
  
  
  }
  else{
  
  
  if(row==3){
      
      if(board[1][col].getIcon()!=null){
          
          if(board[1][col].getIcon().toString().contains("Black_Pawn")){
      
        if(board[2][col].getIcon()!=null && board[3][col].getIcon()!=null){
        
        check1=false;
        return check1;
        
        }
        else if(board[2][col].getIcon() == null && board[3][col].getIcon() ==null){check1=true;
        return check1;
        }
        
          }
      
      }
      
      
      }
      
     
      if(row!=0){
      
       if(board[row-1][col].getIcon()!=null){
           
           if(board[row-1][col].getIcon().toString().contains("Black_Pawn")){
               
              
      
       if(board[row][col].getIcon()!=null){
        
        check1=false;
        return check1;
        }
       else{check1=true;
       return check1;}
           
           }
      
      
      }
      
      }
  
  
  
  
  }
  
  
  
  return check1;
  }
  
  //sunartisi apenergopoihshs tamplo 
  public void disableBoard(){
  
      //8x8
   for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                
                //apenergopoio to koympi ths skakieras
                  board[i][j].setEnabled(false);
                
            }
        }
   
   //apenergopoio ta koympia paraitisis kai isopalias
   surrw.setEnabled(false);
   surrb.setEnabled(false);
   isopalia.setEnabled(false);
  
  }
  
  //sunartisi gia thn evresi pithanwn kinhsewn kommatiwn
  public void PithanesKiniseis(String colour){
       
      XromatismosTamplo();
      
      XromatismosTetragonou(tempr,tempc,1);
  
 if(img.contains("Pawn")){
 
 if(colour.equals("w")){
 
 if(tempr==6){
 
     if(board[tempr-2][tempc].getIcon()==null && board[tempr-1][tempc].getIcon()==null){
    
          XromatismosTetragonou(tempr-2,tempc,2);
     }
 }
 
 if(board[tempr-1][tempc].getIcon()==null){
  XromatismosTetragonou(tempr-1,tempc,2);
 }
 
 if(tempc!=7){
     
 if(tempr==3 && board[tempr][tempc+1].getIcon()!=null && board[tempr][tempc+1].getIcon().toString().contains("Black_Pawn") && board[tempr-1][tempc+1].getIcon()==null){
 XromatismosTetragonou(tempr-1,tempc+1,3);}   
     
     
 if(board[tempr-1][tempc+1].getIcon()!=null && board[tempr-1][tempc+1].getIcon().toString().contains("Black")){
  XromatismosTetragonou(tempr-1,tempc+1,3);
 }}
 
 if(tempc!=0){
     
     if(tempr==3 && board[tempr][tempc-1].getIcon()!=null && board[tempr][tempc-1].getIcon().toString().contains("Black_Pawn") && board[tempr-1][tempc-1].getIcon()==null){
 XromatismosTetragonou(tempr-1,tempc-1,3);} 
     
 if(board[tempr-1][tempc-1].getIcon()!=null && board[tempr-1][tempc-1].getIcon().toString().contains("Black")){
  XromatismosTetragonou(tempr-1,tempc-1,3);
 }}}
 
 
 else{
 
     if(tempr==1){
 
     if(board[tempr+2][tempc].getIcon()==null && board[tempr+1][tempc].getIcon()==null){
    
          XromatismosTetragonou(tempr+2,tempc,2);
     }
 }
 
 if(board[tempr+1][tempc].getIcon()==null){
  XromatismosTetragonou(tempr+1,tempc,2);
 }
 
 if(tempc!=7){
     
     if(tempr==4 && board[tempr][tempc+1].getIcon()!=null && board[tempr][tempc+1].getIcon().toString().contains("White_Pawn") && board[tempr+1][tempc+1].getIcon()==null){
 XromatismosTetragonou(tempr+1,tempc+1,3);} 
     
 if(board[tempr+1][tempc+1].getIcon()!=null && board[tempr+1][tempc+1].getIcon().toString().contains("White")){
  XromatismosTetragonou(tempr+1,tempc+1,3);
 }}
 
 if(tempc!=0){
     
      if(tempr==4 && board[tempr][tempc-1].getIcon()!=null && board[tempr][tempc-1].getIcon().toString().contains("White_Pawn") && board[tempr+1][tempc-1].getIcon()==null){
 XromatismosTetragonou(tempr+1,tempc-1,3);} 
     
 if(board[tempr+1][tempc-1].getIcon()!=null && board[tempr+1][tempc-1].getIcon().toString().contains("White")){
  XromatismosTetragonou(tempr+1,tempc-1,3);
 }}}}
 

 
if(img.contains("Rook") || img.contains("Queen")) {


    if(colour.equals("w")){
    
    if(tempr!=0){
    
   for(int i=1;i<=7;i++){
   
       if(tempr-i<0){break;}
       
       else{
       
       if(board[tempr-i][tempc].getIcon()==null){
       
            XromatismosTetragonou(tempr-i,tempc,2);
       }
       else{if(board[tempr-i][tempc].getIcon().toString().contains("Black")){ XromatismosTetragonou(tempr-i,tempc,3);}
       
       
       
       break;
       
       }}}}
       
   
    
    if(tempr!=7){
    
      for(int i=1;i<=7;i++){
   
       if(tempr+i>7){break;}
       
       else{
       
       if(board[tempr+i][tempc].getIcon()==null){
       
            XromatismosTetragonou(tempr+i,tempc,2);
       }
       else{if(board[tempr+i][tempc].getIcon().toString().contains("Black")){ XromatismosTetragonou(tempr+i,tempc,3);}
       
       
       
       break;
       
       }}}}
       
   
    if(tempc!=0){
    
    
      for(int i=1;i<=7;i++){
   
       if(tempc-i<0){break;}
       
       else{
       
       if(board[tempr][tempc-i].getIcon()==null){
       
            XromatismosTetragonou(tempr,tempc-i,2);
       }
       else{if(board[tempr][tempc-i].getIcon().toString().contains("Black")){ XromatismosTetragonou(tempr,tempc-i,3);}
       
       
       
       break;
       
       }}}}
       
  
    
    if(tempc!=7){
    
    
          for(int i=1;i<=7;i++){
   
       if(tempc+i>7){break;}
       
       else{
       
       if(board[tempr][tempc+i].getIcon()==null){
       
            XromatismosTetragonou(tempr,tempc+i,2);
       }
       else{if(board[tempr][tempc+i].getIcon().toString().contains("Black")){ XromatismosTetragonou(tempr,tempc+i,3);}
       
       
       
       break;
       
       }}}}}
       
    
    
    else{
    
        if(tempr!=0){
    
   for(int i=1;i<=7;i++){
   
       if(tempr-i<0){break;}
       
       else{
       
       if(board[tempr-i][tempc].getIcon()==null){
       
            XromatismosTetragonou(tempr-i,tempc,2);
       }
       else{if(board[tempr-i][tempc].getIcon().toString().contains("White")){ XromatismosTetragonou(tempr-i,tempc,3);}
       
       
       
       break;
       
       }}}}
       
  
    
    if(tempr!=7){
    
      for(int i=1;i<=7;i++){
   
       if(tempr+i>7){break;}
       
       else{
       
       if(board[tempr+i][tempc].getIcon()==null){
       
            XromatismosTetragonou(tempr+i,tempc,2);
       }
       else{if(board[tempr+i][tempc].getIcon().toString().contains("White")){ XromatismosTetragonou(tempr+i,tempc,3);}
       
       
       
       break;
       
       }}}}
       

    if(tempc!=0){
    
    
      for(int i=1;i<=7;i++){
   
       if(tempc-i<0){break;}
       
       else{
       
       if(board[tempr][tempc-i].getIcon()==null){
       
            XromatismosTetragonou(tempr,tempc-i,2);
       }
       else{if(board[tempr][tempc-i].getIcon().toString().contains("White")){ XromatismosTetragonou(tempr,tempc-i,3);}
       
       
       
       break;
       
       }}}}
       
     
    if(tempc!=7){
    
    
          for(int i=1;i<=7;i++){
   
       if(tempc+i>7){break;}
       
       else{
       
       if(board[tempr][tempc+i].getIcon()==null){
       
            XromatismosTetragonou(tempr,tempc+i,2);
       }
       else{if(board[tempr][tempc+i].getIcon().toString().contains("White")){ XromatismosTetragonou(tempr,tempc+i,3);}
       
       
       
       break;
       
       }}}}}}
       
   
if(img.contains("Bishop") || img.contains("Queen")){

if(colour.equals("w")){

if(tempr!=0){

if(tempc!=0){

for(int i=1;i<=7;i++){

if(tempr-i<0 || tempc-i<0){break;}

else{

if(board[tempr-i][tempc-i].getIcon()==null){

XromatismosTetragonou(tempr-i,tempc-i,2);

}

else{

    if(board[tempr-i][tempc-i].getIcon().toString().contains("Black")){XromatismosTetragonou(tempr-i,tempc-i,3);}

break;
}}}}



if(tempc!=7){

for(int i=1;i<=7;i++){

if(tempr-i<0 || tempc+i>7){break;}

else{

if(board[tempr-i][tempc+i].getIcon()==null){

XromatismosTetragonou(tempr-i,tempc+i,2);

}

else{

    if(board[tempr-i][tempc+i].getIcon().toString().contains("Black")){XromatismosTetragonou(tempr-i,tempc+i,3);}

break;
}}}}}


if(tempr!=7){

if(tempc!=0){

for(int i=1;i<=7;i++){

if(tempr+i>7 || tempc-i<0){break;}

else{

if(board[tempr+i][tempc-i].getIcon()==null){

XromatismosTetragonou(tempr+i,tempc-i,2);

}

else{

    if(board[tempr+i][tempc-i].getIcon().toString().contains("Black")){XromatismosTetragonou(tempr+i,tempc-i,3);}

break;
}}}}


if(tempc!=7){

for(int i=1;i<=7;i++){

if(tempr+i>7 || tempc+i>7){break;}

else{

if(board[tempr+i][tempc+i].getIcon()==null){

XromatismosTetragonou(tempr+i,tempc+i,2);

}

else{

    if(board[tempr+i][tempc+i].getIcon().toString().contains("Black")){XromatismosTetragonou(tempr+i,tempc+i,3);}

break;
}}}}}}


else{



if(tempr!=0){

if(tempc!=0){

for(int i=1;i<=7;i++){

if(tempr-i<0 || tempc-i<0){break;}

else{

if(board[tempr-i][tempc-i].getIcon()==null){

XromatismosTetragonou(tempr-i,tempc-i,2);

}

else{

    if(board[tempr-i][tempc-i].getIcon().toString().contains("White")){XromatismosTetragonou(tempr-i,tempc-i,3);}

break;
}}}}

if(tempc!=7){

for(int i=1;i<=7;i++){

if(tempr-i<0 || tempc+i>7){break;}

else{

if(board[tempr-i][tempc+i].getIcon()==null){

XromatismosTetragonou(tempr-i,tempc+i,2);

}

else{

    if(board[tempr-i][tempc+i].getIcon().toString().contains("White")){XromatismosTetragonou(tempr-i,tempc+i,3);}

break;
}}}}}

if(tempr!=7){

if(tempc!=0){

for(int i=1;i<=7;i++){

if(tempr+i>7 || tempc-i<0){break;}

else{

if(board[tempr+i][tempc-i].getIcon()==null){

XromatismosTetragonou(tempr+i,tempc-i,2);

}

else{

    if(board[tempr+i][tempc-i].getIcon().toString().contains("White")){XromatismosTetragonou(tempr+i,tempc-i,3);}

break;
}}}}


if(tempc!=7){

for(int i=1;i<=7;i++){

if(tempr+i>7 || tempc+i>7){break;}

else{

if(board[tempr+i][tempc+i].getIcon()==null){

XromatismosTetragonou(tempr+i,tempc+i,2);

}

else{

    if(board[tempr+i][tempc+i].getIcon().toString().contains("White")){XromatismosTetragonou(tempr+i,tempc+i,3);}

break;
}}}}}}}

if(img.contains("Horse")){

    if(colour.equals("w")){

    if(tempr!=0){
    
    
        if(tempr!=1){
        
            if(tempc!=0){
            
             if(board[tempr-2][tempc-1].getIcon()==null )   {
            XromatismosTetragonou(tempr-2,tempc-1,2);}
             
             else if(board[tempr-2][tempc-1].getIcon().toString().contains("Black")){
             XromatismosTetragonou(tempr-2,tempc-1,3);
             }
              
             
             
             
            }
            if(tempc!=7){
          if(board[tempr-2][tempc+1].getIcon()==null ){
            XromatismosTetragonou(tempr-2,tempc+1,2);}
            
          else if(board[tempr-2][tempc+1].getIcon().toString().contains("Black")){
          XromatismosTetragonou(tempr-2,tempc+1,3);
          }
            
            
            }
        }
        
        if(tempc!=0 && tempc!=1){
        
            if(board[tempr-1][tempc-2].getIcon()==null){
         XromatismosTetragonou(tempr-1,tempc-2,2);}
        
            else if(board[tempr-1][tempc-2].getIcon().toString().contains("Black")){
            XromatismosTetragonou(tempr-1,tempc-2,3);
            }
            
            
            
        }
        if(tempc!=6 && tempc!=7){
            
            if(board[tempr-1][tempc+2].getIcon()==null){
         XromatismosTetragonou(tempr-1,tempc+2,2);}
            
            else if(board[tempr-1][tempc+2].getIcon().toString().contains("Black")){
            
            XromatismosTetragonou(tempr-1,tempc+2,3);
            } 
        
        
        }
        
    }
    
     if(tempr!=7){
    
    
        if(tempr!=6){
        
            if(tempc!=0){
            
              if(board[tempr+2][tempc-1].getIcon()==null){
            XromatismosTetragonou(tempr+2,tempc-1,2);}
              
              else if(board[tempr+2][tempc-1].getIcon().toString().contains("Black")){
              XromatismosTetragonou(tempr+2,tempc-1,3);
              
              }
                
              
              
              
            }
            if(tempc!=7){
                
            if(board[tempr+2][tempc+1].getIcon()==null ){    
            XromatismosTetragonou(tempr+2,tempc+1,2);}
            
            else if(board[tempr+2][tempc+1].getIcon().toString().contains("Black")){
            
            XromatismosTetragonou(tempr+2,tempc+1,3);
            }
            
            
            
            }
        }
        
        if(tempc!=0 && tempc!=1){
        
            if(board[tempr+1][tempc-2].getIcon()==null ){
         XromatismosTetragonou(tempr+1,tempc-2,2);}
            
            else if(board[tempr+1][tempc-2].getIcon().toString().contains("Black")){
            
            XromatismosTetragonou(tempr+1,tempc-2,3);
            }
            
            
        
            
        }
        if(tempc!=6 && tempc!=7){
            
            if(board[tempr+1][tempc+2].getIcon()==null){
         XromatismosTetragonou(tempr+1,tempc+2,2);}
            
            else if(board[tempr+1][tempc+2].getIcon().toString().contains("Black")){
            XromatismosTetragonou(tempr+1,tempc+2,3);
            }
        
        
        
        }
        
    }}

    else{
    
    if(tempr!=0){
    
    
        if(tempr!=1){
        
            if(tempc!=0){
            
             if(board[tempr-2][tempc-1].getIcon()==null)   {
            XromatismosTetragonou(tempr-2,tempc-1,2);}
             
            else if(board[tempr-2][tempc-1].getIcon().toString().contains("White")){
            XromatismosTetragonou(tempr-2,tempc-1,3);
            }
             
             
                
             
             
            }
            if(tempc!=7){
          if(board[tempr-2][tempc+1].getIcon()==null ){
            XromatismosTetragonou(tempr-2,tempc+1,2);}
            
          else if(board[tempr-2][tempc+1].getIcon().toString().contains("White")){
          XromatismosTetragonou(tempr-2,tempc+1,3);}
            
            }
        }
        
        if(tempc!=0 && tempc!=1){
        
            if(board[tempr-1][tempc-2].getIcon()==null){
         XromatismosTetragonou(tempr-1,tempc-2,2);}
            
            else if(board[tempr-1][tempc-2].getIcon().toString().contains("White")){
            
            XromatismosTetragonou(tempr-1,tempc-2,3);
            }
        
            
            
        }
        
        if(tempc!=6 && tempc!=7){
            
            if(board[tempr-1][tempc+2].getIcon()==null){
         XromatismosTetragonou(tempr-1,tempc+2,2);}
        
            else if(board[tempr-1][tempc+2].getIcon().toString().contains("White")){
            XromatismosTetragonou(tempr-1,tempc+2,3);
            
            }
        
        }
        
    }
    
     if(tempr!=7){
    
    
        if(tempr!=6){
        
            if(tempc!=0){
            
              if(board[tempr+2][tempc-1].getIcon()==null ){
            XromatismosTetragonou(tempr+2,tempc-1,2);}
              
              else if(board[tempr+2][tempc-1].getIcon().toString().contains("White")){
              XromatismosTetragonou(tempr+2,tempc-1,3);
              
              }
                
            }
            if(tempc!=7){
                
            if(board[tempr+2][tempc+1].getIcon()==null ){    
            XromatismosTetragonou(tempr+2,tempc+1,2);}
            
            else if(board[tempr+2][tempc+1].getIcon().toString().contains("White")){
            
            XromatismosTetragonou(tempr+2,tempc+1,3);
            }
            
            
            }
            
            
            
            
            
        }
        
        if(tempc!=0 && tempc!=1){
        
            if(board[tempr+1][tempc-2].getIcon()==null ){
         XromatismosTetragonou(tempr+1,tempc-2,2);}
            
         else if(board[tempr+1][tempc-2].getIcon().toString().contains("White")){
         XromatismosTetragonou(tempr+1,tempc-2,3);
         }
              
            
        }
        if(tempc!=6 && tempc!=7){
            
            if(board[tempr+1][tempc+2].getIcon()==null){
         XromatismosTetragonou(tempr+1,tempc+2,2);}
            else if(board[tempr+1][tempc+2].getIcon().toString().contains("White")){
            
            XromatismosTetragonou(tempr+1,tempc+2,3);
            
            }
        
        }
        
    }
    }
}

if(img.contains("King")){

if(colour.equals("w")){
    
    if(tempr==7 && tempc==4 && Apeileitai(7,4,"White","Black")){
    
    if(board[7][7].getIcon()!=null && board[7][7].getIcon().toString().contains("White_Rook")){
    
    if(board[7][5].getIcon()==null && board[7][6].getIcon()==null && Apeileitai(7,5,"White","Black") && Apeileitai(7,6,"White","Black")){
    
        XromatismosTetragonou(7,6,2);
    
    }
    
    }
    if(board[7][0].getIcon()!=null && board[7][0].getIcon().toString().contains("White_Rook")){
    
    if(board[7][1].getIcon()==null && board[7][2].getIcon()==null && board[7][3].getIcon()==null && Apeileitai(7,2,"White","Black") && Apeileitai(7,3,"White","Black")){
    
        XromatismosTetragonou(7,3,2);
     XromatismosTetragonou(7,2,2);
    }}}
    

for(int i=-1;i<=1;i++){
    
    if(tempr+i>=0 && tempr+i<=7){
    
    for(int j=-1;j<=1;j++){
    
    if(tempc+j>=0 && tempc+j<=7){
    
    if(Apeileitai(tempr+i,tempc+j,"White","Black")){
    
    if(board[tempr+i][tempc+j].getIcon()==null ){
    
        XromatismosTetragonou(tempr+i,tempc+j,2);
    
    }
    else if(board[tempr+i][tempc+j].getIcon().toString().contains("Black")){
    XromatismosTetragonou(tempr+i,tempc+j,3);
    
    }}}}}}}
    

else{

     if(tempr==0 && tempc==4 && Apeileitai(0,4,"Black","White")){
    
    if(board[0][7].getIcon()!=null && board[0][7].getIcon().toString().contains("Black_Rook")){
    
    if(board[0][5].getIcon()==null && board[0][6].getIcon()==null && Apeileitai(0,5,"Black","White") && Apeileitai(0,6,"Black","White")){
    
        XromatismosTetragonou(0,6,2);
    
    }
    
    }
    if(board[0][0].getIcon()!=null && board[0][0].getIcon().toString().contains("Black_Rook")){
    
    if(board[0][1].getIcon()==null && board[0][2].getIcon()==null && board[0][3].getIcon()==null && Apeileitai(0,2,"Black","White") && Apeileitai(0,3,"Black","White")){
    
        XromatismosTetragonou(0,3,2);
     XromatismosTetragonou(0,2,2);
    }
    
    }}    
    
for(int i=-1;i<=1;i++){
    
    if(tempr+i>=0 && tempr+i<=7){
    
    for(int j=-1;j<=1;j++){
    
    if(tempc+j>=0 && tempc+j<=7){
    
    if(Apeileitai(tempr+i,tempc+j,"Black","White")){
    
    if(board[tempr+i][tempc+j].getIcon()==null ){
    
        XromatismosTetragonou(tempr+i,tempc+j,2);
    
    }
    else if(board[tempr+i][tempc+j].getIcon().toString().contains("White")){
    XromatismosTetragonou(tempr+i,tempc+j,3);
    
    }}}}}}}}


  }
  
  //sunartisi gia ton elegxo dhmioyrgias sax apo kapoia kinhsh
  public boolean dhmiourgeiSax(int row,int col,String colour1,String colour2){
  
      boolean check1=true;
  
      String image = null;
      
      if(board[tempr][tempc].getIcon()!=null){
          
          
     image=board[tempr][tempc].getIcon().toString();}
      
      String image1=null;
      
      if(board[row][col].getIcon()!=null){
          
          
          image1 = board[row][col].getIcon().toString();
      }
      
      
      board[row][col].setIcon(new ImageIcon(image));
      board[tempr][tempc].setIcon(null);
      
      if(!Apeileitai(rbas,cbas,colour1,colour2)){
      
      check1=false;
      
      }
      
      board[tempr][tempc].setIcon(new ImageIcon(image));
      
      if(image1==null){
      board[row][col].setIcon(null);
      
      }
      else{board[row][col].setIcon(new ImageIcon(image1));}
      
  
  return check1;
  }
  
  //sunartisi xromatismou enos tetragonou me kokkino
  public void XromatismosTetragonou(int r,int c,int sunthiki){
  
      if(sunthiki==1){
      //xromatismos tetragonou analoga me th seira kai sthlh pou dothikan
  board[r][c].setBackground(Color.ORANGE);
       board[r][c].setOpaque(true);
       board[r][c].setBorderPainted(false);}
      
      if(sunthiki==2){
      //xromatismos tetragonou analoga me th seira kai sthlh pou dothikan
  board[r][c].setBackground(Color.red);
       board[r][c].setOpaque(true);
       board[r][c].setBorderPainted(false);}
      
      if(sunthiki==3){
      //xromatismos tetragonou analoga me th seira kai sthlh pou dothikan
  board[r][c].setBackground(Color.MAGENTA);
       board[r][c].setOpaque(true);
       board[r][c].setBorderPainted(false);}
       
  
     
  }
  
  //sunartisi xromatismou tamplo
  public void XromatismosTamplo(){
  
      //gia 8x8
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
   
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
         
  
  }}}
  
  
  public void undo(){
  
      if(unImg1!=null){
           
        board[unR1][unC1].setIcon(new ImageIcon(unImg1));}
      else{board[unR1][unC1].setIcon(null);}
      
      
  
  board[unR2][unC2].setIcon(new ImageIcon(unImg2));
  
  check=unCheck;
  
  
  isoCheck=unIsoCheck;
  
  if(isoCheck){isopalia.setEnabled(false);}
  
  isoCounter=unIsoCounter;
  
  rbas=unRbas;
  cbas=unCbas;
  
  whiteTurn = !whiteTurn;
  
  if(whiteTurn){
  
      if(epilogiXronou!=0){
  xronos.setGyros("White");}
  surrw.setEnabled(true);
  surrb.setEnabled(false);
  
  }
  else{
      if(epilogiXronou!=0){
      xronos.setGyros("Black");}
      
  surrw.setEnabled(false);
  surrb.setEnabled(true);
  
  }
  
  if(unText.contains("σαχ")){sax=true;}
  
  turnLabel.setText(unText);
  
  k.setText(unKinText);
  
  guros--;
  
  undo.setEnabled(false);
  
  }
  
  
  public String KinText(String img,int row,int col){
  
      String text="";
     
      if(img.contains("Rook")){
      
      text=text + "Π";
      
      }
      else if(img.contains("Horse")){
      
      text=text + "Ι";
      
      }
      else if(img.contains("Bishop")){
      
      text=text + "Α";
      
      }
      else if(img.contains("Queen")){
      
      text=text + "Β";
      
      }
      else if(img.contains("King")){
      
      text=text + "Ρ";
      
      }
      
      switch(col){
      
          case 0 -> text=text+"α";
          
          case 1 -> text=text+"β";
          
          case 2 -> text=text+"γ";
          
          case 3 -> text=text+"δ";
          
          case 4 -> text=text+"ε";
          
          case 5 -> text=text+"ζ";
          
          case 6 -> text=text+"η";
          
          case 7 -> text=text+"θ";
      
      
      }
      
      
      switch(row){
      
          case 0 -> text=text+"8";
          
          case 1 -> text=text+"7";
          
          case 2 -> text=text+"6";
          
          case 3 -> text=text+"5";
          
          case 4 -> text=text+"4";
          
          case 5 -> text=text+"3";
          
          case 6 -> text=text+"2";
          
          case 7 -> text=text+"1";
      
      
      }
      
  
  return text;
  
  }
    
  
  //sunartisi emfanisis menou
  public void Menou(){
   
      //neo menou
    new Menou().setVisible(true);
 
  }
  
  
  
  
  

    public static void main(String[] args) {
        
        
        tamplo = new JFrame();
        
        k=new Kinhseis();
        
      
        //nea efarmogi
        DiepafiSkaki j = new DiepafiSkaki();
        
        //klhsh sunartisis gia emfanisi menou
        j.Menou();
        
        
    }

}