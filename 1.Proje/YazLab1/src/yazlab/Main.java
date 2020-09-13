package yazlab;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Main
{

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTable table;
    JProgressBar progressBar;
    String result ;
    JTextPane textPane ;
    static int width; 
    static int height; 
    static double alpha = 1; 
    static double beta = 90; 
    StringBuilder stryapici;
    String a;
	int bayrak=0;
 	int tarihIndis=0;

    DefaultTableModel modelim = new DefaultTableModel();
    Object[] kolonlar= {"isletmeID","isletmeAd","Tarih","fisNo","toplamFiyat","urunler","kdv","fiyat"};
    Object[] satirlar= new Object[8];
    String[] parsedilen=new String[7];
    ArrayList<String> parsedilen1 = new ArrayList<String>();
    ArrayList<String> parsedilen2 = new ArrayList<String>();
    ArrayList<String> parsedilen3 = new ArrayList<String>();
    String sql_sorgu2;


    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    Main window = new Main();
                    window.frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Main()
    {
        initialize();
        listele();
    }


    public void pars (String fisDokumu)
    {
    	int bayrak=0;
	    	int tarihIndis=0;
	        //String fisDokumu = "Migros Ticaret A.S 25/02/2018 FIS NO:126  Cikolata %18 2.5 kek %8 2.5 oyuncak %1 2.5    TOPKDV *6,35 TOPLAM *43,40";
	    	//	String fisDokumu = "MIGROS TICARET A.S-. DEN›ZL› DEMOKRASI MEYDANI SATIS MAGAZASI SITELER MH. INCILIPINAR CD. NO:8A2/B TEL:0(850)3102659 ó PAMUKKALE/DEN›ZL› BUYUK MUKELLEFLER V.D.6220529513 http://www.migros.com.tr : MERKEZ ADRES›: ATAT‹RK MAH. TURGUT ÷ZAL BULV. NO:7 ATAﬁEH›R/›STANBUL  MERSI›S NO: 0622052951300016  TARIH:  2D/02/2018 SAAT : 10:903  F›ﬁNO: ›Zb  #6002080107449672  ###86905705418164#  xx FINISH OVANTUM 80 %18 *39,90  HAHHHH##SO0ODO2Z7OA  xx ROCHER 3 L‹ X8 *3,O0  TOPKDV X18,2 - c  TOPLAM   33,40  444677xxxxxx2006 ó ORTAK POS x43,40  KDV KDV TUTARI KDV'L› TOPLAM  x8 x*0,26 x3, 50  x18 x6,09 x39,90 KASi›YER DOo ó CEYDA fft*'k*******X***t*******************XX***";

	    	
	    	
	    	//	System.out.println(fisDokumu);
	    	
	    	
	    	System.out.println("****************************");
	    	
	    	
	    	String[] parsedStr =fisDokumu.split( "[ ;:*	]");
	    	

	    	for(int i=0;i<parsedStr.length;i++) {
	    		System.out.println(parsedStr[i]);
	    		
	    	}for(int i=0;i<parsedStr.length;i++) {
	    		
	    		
	    	}
	    	
	    	
	    	System.out.println("****************************");
	    	
	    	for(int i=0;i<parsedStr.length;i++)
	    	{
	    		if(i==4) {

 	    			 a=parsedStr[0]+parsedStr[1]+parsedStr[2]+parsedStr[3];
 	    			parsedilen[0]=a;
 	    			
 	    		}
	    		
	    		
	    		if(parsedStr[i].equals("TAR›H")||parsedStr[i].equals("TARIH") || parsedStr[i].equals("TAR›K")|| parsedStr[i].equals("TARIK")) {
	    			for (int j=1;j<10;j++)
	    			{
	    				if(parsedStr[i+j].isEmpty())
	    			{
	    				
	    			}else {
	    				//System.out.println("Tarih: "+parsedStr[i+j]);
	    				parsedilen[1]=parsedStr[i+j];
	    				break;
	    			}
	    				
	    			}
	    			
	    			
	    			bayrak++;
	    		}
	    		if(parsedStr[i].equals("F›ﬁNO")||parsedStr[i].equals("F›ENO")) {
	    			parsedilen[1]=parsedStr[i-1];
	    		}
	    		}
	    	//parsedilen[1]="11.10.2013";
	    	
	    	for(int i=0;i<parsedStr.length;i++)
	    	{
	    	

	    		if(parsedStr[i].equals("F›ﬁ")) {
	    			if(parsedStr[i+1].equals("NO")) {
	    			//System.out.println("fi˛:"+parsedStr[i]);
	    			for (int j=1;j<10;j++)
	    			{
	    				if(parsedStr[(i+1)+j].isEmpty())
	    			{
	    				
	    			}else {
	    				//System.out.println("Fi˛ NO: "+parsedStr[(i+1)+j]);
	    				parsedilen[2]=parsedStr[(i+1)+j];
	    				break;
	    			}
	    			tarihIndis=i+1;
	    		}
	    	}	
	   	}
	    		if(parsedStr[i].equals("F›ﬁNO")||parsedStr[i].equals("F›ENO")) {
	    		
 	    			//System.out.println("fi˛:"+parsedStr[i]);
 	    			for (int j=1;j<10;j++)
 	    			{
 	    				if(parsedStr[i+j].isEmpty())
 	    			{
 	    				
 	    			}else {
 	    				//System.out.println("Fi˛ NO: "+parsedStr[i+j]);
 	    				parsedilen[2]=parsedStr[(i)+j];
 	    				break;
 	    			}
 	    			tarihIndis=i;
 	    		
 	    	}	

	    		}
	    	

               // String sql_sorgu="insert into isletmeler(isletmeAdi) values ('"+parsedilen[0]+"')";
               // String sql_sorgu1="insert into fis (isletmeID,tarih,fisNo,toplamFiyat) values ((select isletmeID from isletmeler where isletmeAdi = '"+parsedilen[0]+"'),'"+parsedilen[1]+"','"+parsedilen[2]+"','"+parsedilen[3]+"')";
              //  String sql_sorgu2="insert into urunler (FisNoID, urunler,kdv,fiyat) values ((SELECT fis.fisNoID FROM ((isletmeler INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID  ))where fisNo='"+parsedilen[2]+"' and ›sletmeAdi='"+parsedilen[0]+"'  ),'"+parsedilen[4]+"','"+parsedilen[5]+"','"+parsedilen[6]+"'";
	    
	    		if(parsedStr[i].equals("%8")||parsedStr[i].equals("%18")||parsedStr[i].equals("%1") || parsedStr[i].equals("%08")) {
	    			
	    			for (int j=1;j<4;j++)
	    			{
	    			//System.out.println(parsedStr[(i-4)+j]);

	 	    			 a=parsedStr[i-3]+parsedStr[i-2]+parsedStr[i-1]+parsedStr[i];
	 	    			System.out.println(a);

	    				//System.out.println(stryapici.toString());
	    			//
	    			}
	    			parsedilen[4]=a;
	    			parsedilen1.add(a);
	    			
	    			
	    			//System.out.println("Kdv: "+parsedStr[i].substring(1, 3));	//urun kdv
	    			
	    			parsedilen[5]=parsedStr[i].substring(1,3);
	    			parsedilen2.add(parsedStr[i].substring(1,3));
	    			
	    			for (int j=1;j<10;j++)
	    			{
	    				if(parsedStr[i+j].isEmpty())
	    			{
	    				
	    			}else {
	    				//System.out.println("Fiyat: "+parsedStr[i+j]);
	    				parsedilen[6]=parsedStr[i+j];
	    				parsedilen3.add(parsedStr[i+j]);
	    				break;
	    			}
	    				
	    			}
	    			
	    			
	    		//	System.out.println(parsedStr[i+1]);// fiyat
	    			
	    			
	    			
	    			
	    		}else if(parsedStr[i].equals("&8")||parsedStr[i].equals("&18")||parsedStr[i].equals("&1") || parsedStr[i].equals("&08")){
	    			for (int j=1;j<4;j++)
	    			{
	    			
	    				 a=parsedStr[i-3]+parsedStr[i-2]+parsedStr[i-1]+parsedStr[i];
	 	    			System.out.println(a);
	 	    			

	    				//System.out.println(stryapici.toString());
	    			//
	    			}
	    			parsedilen[4]=a;
	    			parsedilen1.add(a);
	    			
	    			//System.out.println("Kdv: "+parsedStr[i].substring(1, 3));	//urun kdv
	    			parsedilen[5]=parsedStr[i].substring(1,3);
	    			parsedilen2.add(parsedStr[i].substring(1,3));
	    			for (int j=1;j<10;j++)
	    			{
	    				if(parsedStr[i+j].isEmpty())
	    			{
	    				
	    			}else {
	    				System.out.println("Fiyat: "+parsedStr[i+j]);
	    				parsedilen[6]=parsedStr[i+j];
	    				parsedilen3.add(parsedStr[i+j]);
	    				break;
	    			}
	    				
	    			}
	    			
	    		}else if(parsedStr[i].equals("48")||parsedStr[i].equals("418")||parsedStr[i].equals("41") || parsedStr[i].equals("408")){
	    			for (int j=1;j<4;j++)
	    			{
	    				 a=parsedStr[i-3]+parsedStr[i-2]+parsedStr[i-1]+parsedStr[i];
	 	    			System.out.println(a);

	    				//System.out.println(stryapici.toString());
	    			//
	    			}
	    			parsedilen[4]=a;
	    			parsedilen1.add(a);
	    			
	    			System.out.println("Kdv: "+parsedStr[i].substring(1, 3));	//urun kdv
	    			parsedilen[5]=parsedStr[i].substring(1,3);
	    			parsedilen2.add(parsedStr[i].substring(1,3));
	    			for (int j=1;j<10;j++)
	    			{
	    				if(parsedStr[i+j].isEmpty())
	    					
	    			{
	    				
	    			}else {
	    			//	System.out.println("Fiyat: "+parsedStr[i+j]);
	    				parsedilen[6]=parsedStr[i+j];
	    				parsedilen3.add(parsedStr[i+j]);
	    				break;
	    			}
	    				
	    			}
	    			
	    		}else if(parsedStr[i].equals("968")||parsedStr[i].equals("9618")||parsedStr[i].equals("961") || parsedStr[i].equals("9608")){
	    			for (int j=1;j<4;j++)
	    			{
	    				 a=parsedStr[i-3]+parsedStr[i-2]+parsedStr[i-1]+parsedStr[i];
	 	    			System.out.println(a);

	    				//System.out.println(stryapici.toString());
	    			//
	    			}
	    			parsedilen[4]=a;
	    			parsedilen1.add(a);
	    			
	    			
	    			System.out.println("Kdv: "+parsedStr[i].substring(1, 3));	//urun kdv
	    			parsedilen[5]=parsedStr[i].substring(1,3);
	    			parsedilen2.add(parsedStr[i].substring(1,3));
	    			
	    			for (int j=1;j<10;j++)
	    			{
	    				if(parsedStr[i+j].isEmpty())
	    			{
	    				
	    			}else {
	    				System.out.println("Fiyat: "+parsedStr[i+j]);
	    				parsedilen[6]=parsedStr[i+j];
	    				parsedilen3.add(parsedStr[i+j]);
	    				break;
	    			}
	    				
	    			}
	    			
	    		}else if(parsedStr[i].equals("X8")||parsedStr[i].equals("X18")||parsedStr[i].equals("X1") || parsedStr[i].equals("X08")){
	    			
	    			for (int j=1;j<4;j++)
	    			{
	    				 a=parsedStr[i-3]+parsedStr[i-2]+parsedStr[i-1]+parsedStr[i];
	 	    			System.out.println(a);

	    				//System.out.println(stryapici.toString());
	    			//
	    			}
	    			parsedilen[4]=a;
	    			parsedilen1.add(a);
	    			
	    		
	    			System.out.println("Kdv: "+parsedStr[i].substring(1, 2));	//urun kdv
	    			parsedilen[5]=parsedStr[i].substring(1,3);
	    			parsedilen2.add(parsedStr[i].substring(1,3));
	    			
	    			for (int j=1;j<10;j++)
	    			{
	    				if(parsedStr[i+j].isEmpty())
	    			{
	    				
	    			}else {
	    				System.out.println("Fiyat: "+parsedStr[i+j]);
	    				parsedilen[6]=parsedStr[i+j];
	    				parsedilen3.add(parsedStr[i+j]);
	    				break;
	    			}
	    				
	    			}
	    			
	    		}
	    			    		
	    		if(parsedStr[i].equals("TOPLAM")||parsedStr[i].equals("IOPLAM")) {
	    			
	    			
	    			for (int j=1;j<10;j++)
	    			{
	    				if(parsedStr[i+j].isEmpty())
	    			{
	    				
	    			}else {
	    				if(parsedStr[i+j].equals("X"))
	    				{
	    					//System.out.print("Fiyat: "+parsedStr[i+j+1]);
	    					parsedilen[3]=parsedStr[i+j+1];
	    				}
	    				else {
	    				//System.out.print("Fiyat: "+parsedStr[i+j]);
	    				parsedilen[3]=parsedStr[i+j];
	    		
	    				}break;
	    			}
	    				
	    			}
	    			//System.out.println(parsedStr[i+2]+','+parsedStr[i+3]);
	    			break;
	    		}
	    		
	    		
	    	}
	    	
	    
	for(int i=0;i<parsedilen1.size();i++)
	    	{
	    		System.out.println(i+"--"+parsedilen[i]);
		System.out.println(parsedilen1.get(i)+"---"+parsedilen2.get(i)+"---"+parsedilen3.get(i));
	    	}
	 
	 }





    

    public void listele()
    {
        modelim.setRowCount(0);
        ResultSet myRs= baglanti.yap();

        try
        {
            while(myRs.next())
            {
//					satÔøΩrlar[0]=myRs.getString("denemeId");
//					satÔøΩrlar[1]=myRs.getString("name");
//					satÔøΩrlar[2]=myRs.getString("Soyad");
//					satÔøΩrlar[3]=myRs.getString("Tarih");

                satirlar[0]=myRs.getString("isletmeID");
                satirlar[1]=myRs.getString("isletmeAdi");
                satirlar[2]=myRs.getString("tarih");
                satirlar[3]=myRs.getString("fisNo");
                satirlar[4]=myRs.getString("toplamFiyat");
                satirlar[5]=myRs.getString("Urunler");
                satirlar[6]=myRs.getString("kdv");
                satirlar[7]=myRs.getString("Fiyat");

                modelim.addRow(satirlar);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


    }










    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        frame = new JFrame();
        frame.setBounds(100, 100, 897, 619);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);


        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "RES\u0130M", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBounds(0, 0, 393, 292);
        frame.getContentPane().add(panel);

        JLabel label = new JLabel("\r\n");
        label.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        label.setBounds(10, 22, 373, 232);
        panel.add(label);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "PARSE", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_1.setBounds(403, 0, 273, 292);
        frame.getContentPane().add(panel_1);

        textPane = new JTextPane();
        textPane.setFont(new Font("Arial", Font.BOLD, 12));
        //textPane.enable(false);
        textPane.setBounds(10, 23, 253, 258);

        //panel_3.add(textPane);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 23, 253, 258);
        panel_1.add(scrollPane);
        scrollPane.setViewportView(textPane);

        //RES›M EKLEME

        JButton button = new JButton("File");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {

                progressBar.setValue(0);
                String userhome = System.getProperty("user.home");
                JFileChooser FileChooser = new JFileChooser(userhome +"\\Desktop");
                //filter the files
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
                FileChooser.addChoosableFileFilter(filter);
                int selected = FileChooser.showOpenDialog(null);
                if(selected ==JFileChooser.APPROVE_OPTION)
                {


                    File file = FileChooser.getSelectedFile();
                    String getselectedimage = file.getAbsolutePath();
                    //OptionPane.showMessageDialog(null, getselectedimage);
                    ImageIcon imIcon =new ImageIcon(getselectedimage);
                    Image imfit=imIcon.getImage();
                    Image imgfit = imfit.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);

                    label.setIcon(new ImageIcon(imgfit));
                    
                    
                    
           
         		   
         		   System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

         		      Mat im = Imgcodecs.imread(getselectedimage,0);
         		      Mat dst = new Mat();
         		    
         		      Imgproc.adaptiveThreshold(im, dst, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 95, 50);


         		      Imgproc.threshold(dst, dst, 0, 255, Imgproc.THRESH_TOZERO + Imgproc.THRESH_OTSU);
         		      
         		      
         		   
         		   Mat dst2 = new Mat();
         		   Size size = new Size(dst.rows()*2, dst.rows()*2);
         		   
         		   Imgproc.resize(dst, dst2, size, 0, 0, Imgproc.INTER_AREA);
         		      
         		      
         		      Mat destination = new Mat(dst2.rows(), dst2.cols(), dst2.type()); 
         		      dst.convertTo(destination, -1, alpha, beta);
         		      
         		      Mat im2 = new Mat();
         	  		  Size sz = new Size ((double)(destination.width()),(double)(destination.height()));
         	  		  Imgproc.resize(destination, im2, new Size(sz.width*8, sz.height*8));
         		      
         	  		  destination = new Mat(im2.rows(), im2.cols(), im2.type()); 
         		      dst.convertTo(destination, -1, alpha, beta);
         		  
         		      
         		      Imgcodecs.imwrite("C:\\Users\\TOHIBA\\Desktop\\resim\\ascag.jpg", destination);
         		     
                    


                    File imageFile = new File("C:\\Users\\TOHIBA\\Desktop\\resim\\ascag.jpg");
                    ITesseract instance = new Tesseract();
                    progressBar.setValue(100);
                    instance.setDatapath("C:\\Users\\TOHIBA\\Desktop\\java projeleri\\TessTest\\tessdata");
                    instance.setLanguage("tur");

                    try
                    {
                        result = instance.doOCR(imageFile);

                        System.out.println(result);
                        textPane.setText(result);
                        
                        String[] denem = result.split("\n");
			            String abc = "";
			            for (String string : denem) {
			            	abc += " " + string;
			            	
						}
			            progressBar.setValue(100);
			            System.out.println(abc);
			            pars(abc.toUpperCase());
   
                    }
                    catch(TesseractException e)
                    {
                        System.out.println(e.getMessage());
                    }


                    progressBar.setValue(100);
                }

            }
        });
        button.setBounds(10, 258, 75, 23);
        panel.add(button);

        JButton button_1 = new JButton("Ekle");
        button_1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
/*
            	String sql_sorgu="insert into isletmeler(isletmeAdi) values ('K›M')";
            	 String sql_sorgu1="insert into fis (isletmeID,tarih,fisNo,toplamFiyat) values ((select isletmeID from isletmeler where isletmeAdi = 'K›M'),'08-01-2019','8','11')";
            	 String sql_sorgu2="insert into urunler (FisNoID, urunler,kdv,fiyat) values ((SELECT fis.fisNoID FROM ((isletmeler INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID  ))where fisNo='8' and ›sletmeAdi='A101' ),'Ekmek','21','13')";

            	 baglanti.ekle(sql_sorgu);
                 baglanti.ekle(sql_sorgu1);
                 baglanti.ekle(sql_sorgu2);*/
            	System.out.println("---");
            	for (int i = 0; i < 7; i++) {
					System.out.println(i+"-- "+parsedilen[i]);
				}
            	

                String sql_sorgu="insert into isletmeler(isletmeAdi) values ('"+parsedilen[0]+"')";
                String sql_sorgu1="insert into fis (isletmeID,tarih,fisNo,toplamFiyat) values ((select isletmeID from isletmeler where isletmeAdi = '"+parsedilen[0]+"'),'"+parsedilen[1]+"','"+parsedilen[2]+"','"+parsedilen[3]+"')";
               //    String sql_sorgu2="insert into urunler (FisNoID, urunler,kdv,fiyat) values ((SELECT fis.fisNoID FROM ((isletmeler INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID  ))where fisNo='"+parsedilen[2]+"' and ›sletmeAdi='"+parsedilen[0]+"'  ),'"+parsedilen[4]+"','"+parsedilen[5]+"','"+parsedilen[6]+"'";
                  baglanti.ekle(sql_sorgu);
                baglanti.ekle(sql_sorgu1);
                for (int i = 0; i <parsedilen1.size(); i++) {
                 sql_sorgu2="insert into urunler (FisNoID, urunler,kdv,fiyat) values ((SELECT fis.fisNoID FROM ((isletmeler INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID  ))where fisNo='"+parsedilen[2]+"' and ›sletmeAdi='"+parsedilen[0]+"'  ),'"+parsedilen1.get(i)+"','"+parsedilen2.get(i)+"','"+parsedilen3.get(i)+"')";
                     baglanti.ekle(sql_sorgu2);
				}
             
                // String sql_sorgu2="insert into urunler (FisNoID, urunler,kdv,fiyat) values ((select FisNoID from fis where fisNo ='"+parsedilen[2]+"' ),'"+parsedilen[4]+"','"+parsedilen[5]+"','"+parsedilen[6]+"'";
                
             
               
                listele();
            }
        });
        button_1.setBounds(294, 258, 89, 23);
        panel.add(button_1);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.GREEN);
        progressBar.setBounds(111, 265, 146, 14);
        panel.add(progressBar);



        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "ARAMA", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_2.setBounds(686, 11, 175, 147);
        frame.getContentPane().add(panel_2);

        textField = new JTextField();
        textField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyReleased(KeyEvent arg0)
            {


                modelim.setRowCount(0);
                String ad=textField.getText();
                ResultSet myRs=null;
                //String sql_sorgu="select*from sakila.actor where first_name like '%"+ad+"%' ";
                //System.out.println(sql_sorgu);
                String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                 " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                 "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                 "FROM ((isletmeler\r\n" +
                                 "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                 "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID) where isletmeler.isletmeAdi like '%"+ad+"%';";

                myRs= baglanti.sorgula(sql_sorgu);

                try
                {
                    while(myRs.next())
                    {

                        satirlar[0]=myRs.getString("isletmeID");
                        satirlar[1]=myRs.getString("isletmeAdi");
                        satirlar[2]=myRs.getString("tarih");
                        satirlar[3]=myRs.getString("fisNo");
                        satirlar[4]=myRs.getString("toplamFiyat");
                        satirlar[5]=myRs.getString("Urunler");
                        satirlar[6]=myRs.getString("kdv");
                        satirlar[7]=myRs.getString("Fiyat");
                        modelim.addRow(satirlar);

                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }


            }
        });
        textField.setColumns(10);
        textField.setBounds(12, 46, 155, 20);
        panel_2.add(textField);

        JButton button_2 = new JButton("New button");
        button_2.setBounds(78, 210, 89, 23);
        panel_2.add(button_2);

        textField_1 = new JTextField();
        textField_1.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyReleased(KeyEvent arg0)
            {

                modelim.setRowCount(0);
                String tarih=textField_1.getText();
                ResultSet myRs=null;
                String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                 " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                 "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                 "FROM ((isletmeler\r\n" +
                                 "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                 "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID)where fis.tarih like '%"+tarih+"%' ";

                myRs= baglanti.sorgula(sql_sorgu);


                try
                {
                    while(myRs.next())
                    {

                        satirlar[0]=myRs.getString("isletmeID");
                        satirlar[1]=myRs.getString("isletmeAdi");
                        satirlar[2]=myRs.getString("tarih");
                        satirlar[3]=myRs.getString("fisNo");
                        satirlar[4]=myRs.getString("toplamFiyat");
                        satirlar[5]=myRs.getString("Urunler");
                        satirlar[6]=myRs.getString("kdv");
                        satirlar[7]=myRs.getString("Fiyat");
                        modelim.addRow(satirlar);

                    }
                }
                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }

            }


        });
        textField_1.setBounds(10, 96, 155, 20);
        panel_2.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel = new JLabel("\u015Eirket Ad\u0131:");
        lblNewLabel.setBounds(12, 21, 69, 14);
        panel_2.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Tarih:");
        lblNewLabel_1.setBounds(12, 71, 46, 14);
        panel_2.add(lblNewLabel_1);

        JPanel panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.setBorder(new TitledBorder(null, "SIRALAMA", TitledBorder.CENTER, TitledBorder.TOP, null, null));
        panel_3.setBounds(686, 158, 175, 134);
        frame.getContentPane().add(panel_3);

        String s˝ralama[]= {"isletmeID","›˛letme Ad˝","Tarih","Fi˛ No","Toplam Fiyat","‹r¸nler","Kdv","‹r¸n Fiyat"};
        JComboBox comboBox = new JComboBox(s˝ralama);


        comboBox.setSelectedItem(null);
        comboBox.setBounds(20, 29, 145, 20);
        panel_3.add(comboBox);

        String s˝ralama2[]= {"K¸Á¸kten B¸y¸e","B¸y¸kten K¸Á¸ke"};
        JComboBox comboBox_1 = new JComboBox(s˝ralama2);

        comboBox_1.setSelectedItem(null);
        comboBox_1.setBounds(20, 60, 145, 20);
        panel_3.add(comboBox_1);


        JButton button_3 = new JButton("S\u0131rala");
        button_3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                if(comboBox.getSelectedIndex()==0)
                {
                    if(comboBox_1.getSelectedIndex()==0)
                    {

                        //ASC K¸Á¸kten b¸y¸ge
                        modelim.setRowCount(0);
                        ResultSet myRs = null;
                        String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                         " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                         "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                         "FROM ((isletmeler\r\n" +
                                         "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                         "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID) ORDER BY isletmeler.isletmeID ASC;";
                        myRs= baglanti.sorgula(sql_sorgu);
                        try
                        {
                            while(myRs.next())
                            {

                                satirlar[0]=myRs.getString("isletmeID");
                                satirlar[1]=myRs.getString("isletmeAdi");
                                satirlar[2]=myRs.getString("tarih");
                                satirlar[3]=myRs.getString("fisNo");
                                satirlar[4]=myRs.getString("toplamFiyat");
                                satirlar[5]=myRs.getString("Urunler");
                                satirlar[6]=myRs.getString("kdv");
                                satirlar[7]=myRs.getString("Fiyat");
                                modelim.addRow(satirlar);

                            }
                        }
                        catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }


                    }
                    else
                    {
                        modelim.setRowCount(0);
                        ResultSet myRs=null;
                        String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                         " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                         "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                         "FROM ((isletmeler\r\n" +
                                         "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                         "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID) ORDER BY isletmeler.isletmeID DESC;";
                        myRs= baglanti.sorgula(sql_sorgu);


                        try
                        {
                            while(myRs.next())
                            {

                                satirlar[0]=myRs.getString("isletmeID");
                                satirlar[1]=myRs.getString("isletmeAdi");
                                satirlar[2]=myRs.getString("tarih");
                                satirlar[3]=myRs.getString("fisNo");
                                satirlar[4]=myRs.getString("toplamFiyat");
                                satirlar[5]=myRs.getString("Urunler");
                                satirlar[6]=myRs.getString("kdv");
                                satirlar[7]=myRs.getString("Fiyat");
                                modelim.addRow(satirlar);

                            }
                        }
                        catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }

                    }

                }
                if(comboBox.getSelectedIndex()==1)
                {
                    if(comboBox_1.getSelectedIndex()==0)
                    {
                        //ASC K¸Á¸kten b¸y¸ge
                        modelim.setRowCount(0);
                        ResultSet myRs = null;
                        String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                         " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                         "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                         "FROM ((isletmeler\r\n" +
                                         "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                         "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID) ORDER BY isletmeler.isletmeAdi ASC;";
                        myRs= baglanti.sorgula(sql_sorgu);
                        try
                        {
                            while(myRs.next())
                            {

                                satirlar[0]=myRs.getString("isletmeID");
                                satirlar[1]=myRs.getString("isletmeAdi");
                                satirlar[2]=myRs.getString("tarih");
                                satirlar[3]=myRs.getString("fisNo");
                                satirlar[4]=myRs.getString("toplamFiyat");
                                satirlar[5]=myRs.getString("Urunler");
                                satirlar[6]=myRs.getString("kdv");
                                satirlar[7]=myRs.getString("Fiyat");
                                modelim.addRow(satirlar);

                            }
                        }
                        catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }


                    }
                    else
                    {
                        modelim.setRowCount(0);
                        ResultSet myRs = null;
                        String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                         " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                         "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                         "FROM ((isletmeler\r\n" +
                                         "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                         "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID) ORDER BY isletmeler.isletmeAdi DESC;";
                        myRs= baglanti.sorgula(sql_sorgu);
                        try
                        {
                            while(myRs.next())
                            {

                                satirlar[0]=myRs.getString("isletmeID");
                                satirlar[1]=myRs.getString("isletmeAdi");
                                satirlar[2]=myRs.getString("tarih");
                                satirlar[3]=myRs.getString("fisNo");
                                satirlar[4]=myRs.getString("toplamFiyat");
                                satirlar[5]=myRs.getString("Urunler");
                                satirlar[6]=myRs.getString("kdv");
                                satirlar[7]=myRs.getString("Fiyat");
                                modelim.addRow(satirlar);

                            }
                        }
                        catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }

                    }

                }
                if(comboBox.getSelectedIndex()==2)
                {
                    if(comboBox_1.getSelectedIndex()==0)
                    {
                        //ASC K¸Á¸kten b¸y¸ge
                        modelim.setRowCount(0);
                        ResultSet myRs = null;
                        String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                         " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                         "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                         "FROM ((isletmeler\r\n" +
                                         "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                         "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID) ORDER BY fis.tarih ASC;";
                        myRs= baglanti.sorgula(sql_sorgu);
                        try
                        {
                            while(myRs.next())
                            {

                                satirlar[0]=myRs.getString("isletmeID");
                                satirlar[1]=myRs.getString("isletmeAdi");
                                satirlar[2]=myRs.getString("tarih");
                                satirlar[3]=myRs.getString("fisNo");
                                satirlar[4]=myRs.getString("toplamFiyat");
                                satirlar[5]=myRs.getString("Urunler");
                                satirlar[6]=myRs.getString("kdv");
                                satirlar[7]=myRs.getString("Fiyat");
                                modelim.addRow(satirlar);

                            }
                        }
                        catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }


                    }
                    else
                    {
                        modelim.setRowCount(0);
                        ResultSet myRs = null;
                        String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                         " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                         "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                         "FROM ((isletmeler\r\n" +
                                         "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                         "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID) ORDER BY fis.tarih DESC;";
                        myRs= baglanti.sorgula(sql_sorgu);
                        try
                        {
                            while(myRs.next())
                            {

                                satirlar[0]=myRs.getString("isletmeID");
                                satirlar[1]=myRs.getString("isletmeAdi");
                                satirlar[2]=myRs.getString("tarih");
                                satirlar[3]=myRs.getString("fisNo");
                                satirlar[4]=myRs.getString("toplamFiyat");
                                satirlar[5]=myRs.getString("Urunler");
                                satirlar[6]=myRs.getString("kdv");
                                satirlar[7]=myRs.getString("Fiyat");
                                modelim.addRow(satirlar);

                            }
                        }
                        catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }

                    }

                }
                if(comboBox.getSelectedIndex()==3)
                {
                    if(comboBox_1.getSelectedIndex()==0)
                    {
                        //ASC K¸Á¸kten b¸y¸ge
                        modelim.setRowCount(0);
                        ResultSet myRs = null;
                        String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                         " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                         "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                         "FROM ((isletmeler\r\n" +
                                         "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                         "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID) ORDER BY fis.fisNo ASC;";
                        myRs= baglanti.sorgula(sql_sorgu);
                        try
                        {
                            while(myRs.next())
                            {

                                satirlar[0]=myRs.getString("isletmeID");
                                satirlar[1]=myRs.getString("isletmeAdi");
                                satirlar[2]=myRs.getString("tarih");
                                satirlar[3]=myRs.getString("fisNo");
                                satirlar[4]=myRs.getString("toplamFiyat");
                                satirlar[5]=myRs.getString("Urunler");
                                satirlar[6]=myRs.getString("kdv");
                                satirlar[7]=myRs.getString("Fiyat");
                                modelim.addRow(satirlar);

                            }
                        }
                        catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }


                    }
                    else
                    {
                        modelim.setRowCount(0);
                        ResultSet myRs = null;
                        String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                         " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                         "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                         "FROM ((isletmeler\r\n" +
                                         "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                         "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID) ORDER BY fis.fisNo DESC;";
                        myRs= baglanti.sorgula(sql_sorgu);
                        try
                        {
                            while(myRs.next())
                            {

                                satirlar[0]=myRs.getString("isletmeID");
                                satirlar[1]=myRs.getString("isletmeAdi");
                                satirlar[2]=myRs.getString("tarih");
                                satirlar[3]=myRs.getString("fisNo");
                                satirlar[4]=myRs.getString("toplamFiyat");
                                satirlar[5]=myRs.getString("Urunler");
                                satirlar[6]=myRs.getString("kdv");
                                satirlar[7]=myRs.getString("Fiyat");
                                modelim.addRow(satirlar);

                            }
                        }
                        catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }

                    }

                }
                if(comboBox.getSelectedIndex()==4)
                {
                    if(comboBox_1.getSelectedIndex()==0)
                    {
                        //ASC K¸Á¸kten b¸y¸ge
                        modelim.setRowCount(0);
                        ResultSet myRs = null;
                        String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                         " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                         "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                         "FROM ((isletmeler\r\n" +
                                         "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                         "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID) ORDER BY fis.toplamFiyat ASC;";
                        myRs= baglanti.sorgula(sql_sorgu);
                        try
                        {
                            while(myRs.next())
                            {

                                satirlar[0]=myRs.getString("isletmeID");
                                satirlar[1]=myRs.getString("isletmeAdi");
                                satirlar[2]=myRs.getString("tarih");
                                satirlar[3]=myRs.getString("fisNo");
                                satirlar[4]=myRs.getString("toplamFiyat");
                                satirlar[5]=myRs.getString("Urunler");
                                satirlar[6]=myRs.getString("kdv");
                                satirlar[7]=myRs.getString("Fiyat");
                                modelim.addRow(satirlar);

                            }
                        }
                        catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }


                    }
                    else
                    {
                        modelim.setRowCount(0);
                        ResultSet myRs = null;
                        String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                         " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                         "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                         "FROM ((isletmeler\r\n" +
                                         "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                         "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID) ORDER BY fis.toplamFiyat DESC;";
                        myRs= baglanti.sorgula(sql_sorgu);
                        try
                        {
                            while(myRs.next())
                            {

                                satirlar[0]=myRs.getString("isletmeID");
                                satirlar[1]=myRs.getString("isletmeAdi");
                                satirlar[2]=myRs.getString("tarih");
                                satirlar[3]=myRs.getString("fisNo");
                                satirlar[4]=myRs.getString("toplamFiyat");
                                satirlar[5]=myRs.getString("Urunler");
                                satirlar[6]=myRs.getString("kdv");
                                satirlar[7]=myRs.getString("Fiyat");
                                modelim.addRow(satirlar);

                            }
                        }
                        catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }

                    }

                }
                if(comboBox.getSelectedIndex()==5)
                {
                    if(comboBox_1.getSelectedIndex()==0)
                    {
                        //ASC K¸Á¸kten b¸y¸ge
                        modelim.setRowCount(0);
                        ResultSet myRs = null;
                        String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                         " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                         "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                         "FROM ((isletmeler\r\n" +
                                         "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                         "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID) ORDER BY urunler.Urunler ASC;";
                        myRs= baglanti.sorgula(sql_sorgu);
                        try
                        {
                            while(myRs.next())
                            {

                                satirlar[0]=myRs.getString("isletmeID");
                                satirlar[1]=myRs.getString("isletmeAdi");
                                satirlar[2]=myRs.getString("tarih");
                                satirlar[3]=myRs.getString("fisNo");
                                satirlar[4]=myRs.getString("toplamFiyat");
                                satirlar[5]=myRs.getString("Urunler");
                                satirlar[6]=myRs.getString("kdv");
                                satirlar[7]=myRs.getString("Fiyat");
                                modelim.addRow(satirlar);

                            }
                        }
                        catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }


                    }
                    else
                    {
                        modelim.setRowCount(0);
                        ResultSet myRs = null;
                        String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                         " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                         "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                         "FROM ((isletmeler\r\n" +
                                         "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                         "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID) ORDER BY urunler.Urunler DESC;";
                        myRs= baglanti.sorgula(sql_sorgu);
                        try
                        {
                            while(myRs.next())
                            {

                                satirlar[0]=myRs.getString("isletmeID");
                                satirlar[1]=myRs.getString("isletmeAdi");
                                satirlar[2]=myRs.getString("tarih");
                                satirlar[3]=myRs.getString("fisNo");
                                satirlar[4]=myRs.getString("toplamFiyat");
                                satirlar[5]=myRs.getString("Urunler");
                                satirlar[6]=myRs.getString("kdv");
                                satirlar[7]=myRs.getString("Fiyat");
                                modelim.addRow(satirlar);

                            }
                        }
                        catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }
                    }

                }
                if(comboBox.getSelectedIndex()==6)
                {
                    if(comboBox_1.getSelectedIndex()==0)
                    {
                        //ASC K¸Á¸kten b¸y¸ge
                        modelim.setRowCount(0);
                        ResultSet myRs = null;
                        String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                         " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                         "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                         "FROM ((isletmeler\r\n" +
                                         "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                         "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID) ORDER BY urunler.kdv ASC;";
                        myRs= baglanti.sorgula(sql_sorgu);
                        try
                        {
                            while(myRs.next())
                            {

                                satirlar[0]=myRs.getString("isletmeID");
                                satirlar[1]=myRs.getString("isletmeAdi");
                                satirlar[2]=myRs.getString("tarih");
                                satirlar[3]=myRs.getString("fisNo");
                                satirlar[4]=myRs.getString("toplamFiyat");
                                satirlar[5]=myRs.getString("Urunler");
                                satirlar[6]=myRs.getString("kdv");
                                satirlar[7]=myRs.getString("Fiyat");
                                modelim.addRow(satirlar);

                            }
                        }
                        catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }


                    }
                    else
                    {
                        modelim.setRowCount(0);
                        ResultSet myRs = null;
                        String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                         " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                         "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                         "FROM ((isletmeler\r\n" +
                                         "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                         "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID) ORDER BY urunler.kdv DESC;";
                        myRs= baglanti.sorgula(sql_sorgu);
                        try
                        {
                            while(myRs.next())
                            {

                                satirlar[0]=myRs.getString("isletmeID");
                                satirlar[1]=myRs.getString("isletmeAdi");
                                satirlar[2]=myRs.getString("tarih");
                                satirlar[3]=myRs.getString("fisNo");
                                satirlar[4]=myRs.getString("toplamFiyat");
                                satirlar[5]=myRs.getString("Urunler");
                                satirlar[6]=myRs.getString("kdv");
                                satirlar[7]=myRs.getString("Fiyat");
                                modelim.addRow(satirlar);

                            }
                        }
                        catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }
                    }

                }
                if(comboBox.getSelectedIndex()==7)
                {
                    if(comboBox_1.getSelectedIndex()==0)
                    {
                        //ASC K¸Á¸kten b¸y¸ge
                        modelim.setRowCount(0);
                        ResultSet myRs = null;
                        String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                         " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                         "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                         "FROM ((isletmeler\r\n" +
                                         "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                         "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID) ORDER BY urunler.Fiyat ASC;";
                        myRs= baglanti.sorgula(sql_sorgu);
                        try
                        {
                            while(myRs.next())
                            {

                                satirlar[0]=myRs.getString("isletmeID");
                                satirlar[1]=myRs.getString("isletmeAdi");
                                satirlar[2]=myRs.getString("tarih");
                                satirlar[3]=myRs.getString("fisNo");
                                satirlar[4]=myRs.getString("toplamFiyat");
                                satirlar[5]=myRs.getString("Urunler");
                                satirlar[6]=myRs.getString("kdv");
                                satirlar[7]=myRs.getString("Fiyat");
                                modelim.addRow(satirlar);

                            }
                        }
                        catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }


                    }
                    else
                    {
                        modelim.setRowCount(0);
                        ResultSet myRs = null;
                        String sql_sorgu="SELECT isletmeler.isletmeID,isletmeler.isletmeAdi,\r\n" +
                                         " fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" +
                                         "  urunler.Urunler, urunler.kdv, urunler.Fiyat\r\n" +
                                         "FROM ((isletmeler\r\n" +
                                         "INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" +
                                         "INNER JOIN urunler ON fis.fisNoID = urunler.fisNoID) ORDER BY urunler.Fiyat DESC;";
                        myRs= baglanti.sorgula(sql_sorgu);
                        try
                        {
                            while(myRs.next())
                            {

                                satirlar[0]=myRs.getString("isletmeID");
                                satirlar[1]=myRs.getString("isletmeAdi");
                                satirlar[2]=myRs.getString("tarih");
                                satirlar[3]=myRs.getString("fisNo");
                                satirlar[4]=myRs.getString("toplamFiyat");
                                satirlar[5]=myRs.getString("Urunler");
                                satirlar[6]=myRs.getString("kdv");
                                satirlar[7]=myRs.getString("Fiyat");
                                modelim.addRow(satirlar);

                            }
                        }
                        catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }
                    }
                }

            }
        });
        button_3.setBounds(40, 100, 89, 23);
        panel_3.add(button_3);


        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 318, 861, 251);
        frame.getContentPane().add(scrollPane_1);
        table = new JTable();

        modelim.setColumnIdentifiers(kolonlar);
        table.setModel(modelim);
        table.setBounds(215, 37, 209, 213);
        scrollPane_1.setViewportView(table);
    }
}
