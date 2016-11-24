/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.engien.mads327b.github;

import java.awt.Canvas;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

/**
 *
 * @author Mads327b
 */
public final class Game extends Canvas implements Runnable{
    Thread t;
    boolean runing = false;
    static final Logger logger = Logger.getLogger("3D-Engien");
    ArrayList<Double> 
            FPS = new ArrayList<>(),
            TPS = new ArrayList<>();
    Handler handler;
    double 
            fpsLMT = 60,
            tpsLMT = 180;
    public static void main(String[] args) { 
        new Game();
        
    }

    public Game() {

        try {  
            File dir = new File("Logs");
            // This block configure the logger with handler and formatter 
            if(!dir.exists())
                dir.mkdir();
            
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            File file = new File("Logs/"+sdf.format(cal.getTime())+".log");
            FileHandler fh;
            String fLog = "Creating Log";
            if(file.exists()){
                fh = new FileHandler(file.getPath(),true);
                fLog = "open Log";
            }else
                fh = new FileHandler(file.getPath());
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter);  

            // the following statement is used to log any messages  
            logger.info(fLog);  

        } catch (IOException ex) {  
            logger.log(Level.SEVERE, null, ex);
        }

        logger.info("Creating window");
        Window(800, 600, "3D-Engien");
        start();
    }
    void Window(int Width,int Height,String title){
        JFrame f = new JFrame(title);
        f.setSize(Width,Height);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.add(this);
        this.setVisible(true);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
//                e.getWindow().hide();
                runing = false;
                logger.info("*--------Stopping system--------*\n\n\n\n");
                try {
                    t.join();
                } catch (InterruptedException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        });
    }
    void start(){
        logger.info("Starting Engien");
        handler = new Handler(this);
        runing = true;
        t = new Thread(this,"3D-Engien");
        t.start();
    }
    
    @Override
    public void run() {
        logger.info("Runing Engien");
        long lastFPS = System.nanoTime();
        long lastTime = System.nanoTime();
        long lastTPS = System.nanoTime();
        while(runing){
            long now = System.nanoTime();
            double fps = 1000000000.0/(now-lastFPS);
            double time = (now-lastTime)/1000000000.0;
            double tps = 1000000000.0/(now-lastTPS);
            if(tps <= tpsLMT){
                this.TPS.add(tps);
                handler.tich((now-lastTPS)/1000000000.0);
                lastTPS = now;
            }
            if(fps <= fpsLMT){
                this.FPS.add(fps);
                lastFPS = now;
                handler.render();
            }
            if(time >= 10){
                double sum = 0;
                for (double i : this.FPS) {
                    sum += i;
                }
                double sum2 = 0;
                for (double i : this.TPS) {
                    sum2 += i;
                }
                logger.log(Level.INFO, "Average TPS in the last {0}s.\tis: {1}({2})", new Object[]{CALM.dicimal(time,2), (int)CALM.dicimal(sum2/this.TPS.size(),0), this.TPS.size()});
                logger.log(Level.INFO, "Average FPS in the last {0}s.\tis: {1}({2})", new Object[]{CALM.dicimal(time,2), (int)CALM.dicimal(sum/this.FPS.size(),0), this.FPS.size()});
                
                this.FPS.clear();
                this.TPS.clear();
                lastTime = System.nanoTime();
            }
        }
    }
    
}
