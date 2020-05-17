package plasticssolutionsusascraperlauncher;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

public class PlasticsSolutionsUSAScraperLauncher {
    public static void main(String[] args) throws IOException, InterruptedException {
        int counteroftheloop  = 0;
       int loopcap = 65;
       int subloopcount = 0;
       String[] URLHolderArrayL = new String[65]; 
       File file3 = new File("C:\\Users\\tremanleo\\Documents\\NetBeansProjects\\PlasticsSolutionsUSAScraperLauncher\\HoldTheFiles\\urlstoscrape.txt");  
       File UrlLoaderFile = new File("C:\\Users\\tremanleo\\Documents\\NetBeansProjects\\PlasticsSolutionsUSAScraperLauncher\\HoldTheFiles\\UrlLoader.txt");
       BufferedReader br3 = new BufferedReader(new FileReader(file3));
       String Manipulator = "";     
       
        //Read the url list and populate current url with scraping url.
	String line3;
	while ((line3 = br3.readLine()) != null) 
            {          
            if(subloopcount == 65)
            {
                subloopcount = 0;                
            }
            
            URLHolderArrayL[subloopcount] = line3;
                
             subloopcount++;
            }
             subloopcount = 0;
             br3.close();

                   while (counteroftheloop != loopcap)
                {  
                System.out.println("Launching Plastics Solutions USA Scraper.");            
                // Run LeonardScraper2
                Process proc = Runtime.getRuntime().exec("java -jar C:\\Users\\tremanleo\\Documents\\NetBeansProjects\\PlasticsSolutionsUSAScraperLauncher\\HoldTheFiles\\Leonard_Solutions_USA_Scraper.jar");
                InputStream in = proc.getInputStream();
                InputStream err = proc.getErrorStream();     
                
                //Give it time to run
                TimeUnit.SECONDS.sleep(15);                 
                    
                //Move Number Matching URL name from URLHolder Array To Manipulator                       
               Manipulator = URLHolderArrayL[counteroftheloop];
               System.out.println(Manipulator);
               
               //Write URL TO UrlLoader.txt for LeonardScraper2 To Use On Next Iteration
                   FileWriter fw3 = new FileWriter(UrlLoaderFile);
                   PrintWriter out3 = new PrintWriter(fw3);     
                   out3.print(Manipulator);
                    out3.flush(); 
                     out3.close();
                     fw3.close();                
                
                counteroftheloop++;
                }
                   //Reset UrlLoader.txt for next use
                   FileWriter fw3 = new FileWriter(UrlLoaderFile);
                   PrintWriter out3 = new PrintWriter(fw3);     
                   out3.print("https://www.plasticssolutionsusa.com/collections/cleaning-and-maintenance/products/acid-vapor-neutralizer");
                    out3.flush(); 
                     out3.close(); 
                     fw3.close();                

                // Run LeonardCSVCombiner
                Process proc = Runtime.getRuntime().exec("java -jar C:\\Users\\tremanleo\\Documents\\NetBeansProjects\\PlasticsSolutionsUSAScraperLauncher\\HoldTheFiles\\PlasticsSolutionsUSACSVCombiner.jar");
                InputStream in2 = proc.getInputStream();
                InputStream err2 = proc.getErrorStream();  
    }   
}