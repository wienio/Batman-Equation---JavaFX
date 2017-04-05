package sample;

import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Wienio on 2017-04-02.
 */
public class DrawerTask extends Task{
    private final double MIN = -8;
    private final double MAX = 8;
    private GraphicsContext gc;
    private double N;

    @Override
    protected Object call() throws Exception {
            Random random = new Random();
            double x,y;
            long hits=0;
            double result;

            BufferedImage bi = new BufferedImage((int)gc.getCanvas().getWidth(),(int) gc.getCanvas().getHeight(), BufferedImage.TYPE_INT_RGB);
            for ( int i  = 0 ; i < N ; i++) {
                x=MIN+(MAX-MIN)*random.nextDouble();
                y=MIN+(MAX-MIN)*random.nextDouble();
                if(Equation.calc(x,y)) {
                    double x1 = gc.getCanvas().getWidth() * (x-MIN) / (MAX-MIN);
                    double y1=  gc.getCanvas().getHeight() * (y-MIN) / (MAX-MIN);
                    bi.setRGB((int)x1,(int)(-y1+gc.getCanvas().getHeight()),Color.YELLOW.getRGB());
                    hits++;
                }
                else {
                    double x1 = gc.getCanvas().getWidth() * (x-MIN) / (MAX-MIN);
                    double y1=  gc.getCanvas().getHeight() * (y-MIN) / (MAX-MIN);
                    bi.setRGB((int)x1,(int)(-y1+gc.getCanvas().getHeight()),Color.BLUE.getRGB());
                }
                if (i%2500==0) {
                    gc.drawImage(SwingFXUtils.toFXImage(bi,null),0,0);
                }
                updateProgress(i,N);
                if(isCancelled()) break;
            }
            gc.drawImage(SwingFXUtils.toFXImage(bi,null),0,0);
            result=hits * (MAX-MIN)*(MAX-MIN)/N;
            return result;
    }

    public DrawerTask(GraphicsContext gc, double N) {
        this.gc=gc;
        this.N=N;
    }
}
