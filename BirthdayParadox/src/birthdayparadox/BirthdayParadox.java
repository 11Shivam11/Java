/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package birthdayparadox;
import java.awt.Color;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.*;
import org.jfree.ui.RefineryUtilities;
import javax.swing.*;
import java.io.*;

public class BirthdayParadox extends JFrame{
int trials;
    BirthdayParadox(int n)
    {
        super("Birthday Paradox");
        trials=n;
        //display(plot());
    }
    public JFreeChart plot()
    {
        XYSeriesCollection collection = new XYSeriesCollection();
        XYSeries series=new XYSeries("Probability");
        int date;
        int success[]=new int[366];
        for(int i=1;i<=trials;i++)
        {
            int year[]=new int[365];
            for(int j=0;j<366;j++)
            {
                date= (int)(Math.random() * (365));
                year[date]+=1;
                if(year[date]>1)
                {
                    while(j<=365)
                    {
                        success[j]+=1;
                        j++;
                    }
                }
            }
        }
        
        for(int i=0;i<366;i++)
        {
            series.add(i+1, (float)success[i]/trials);
        }
	collection.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Probability Plot","Number of persons","Probability",collection,PlotOrientation.VERTICAL,true,false,false);
		chart.setBackgroundPaint(new Color(175,238,238));
                chart.setBorderPaint(Color.BLACK);
                return chart;
    }
    public void display(JFreeChart chart)
    {
        ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1000, 700));
		setContentPane(chartPanel);
                pack();
                RefineryUtilities.centerFrameOnScreen(this);
                setVisible(true);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
    }
    public static void main(String[] args) {
        // TODO code application logic here
        int trials=100;
        BirthdayParadox paradox;
        
        String str = JOptionPane.showInputDialog("Enter number of trials : ");
        try{
            trials=Integer.parseInt(str);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Invalid Input, using default value(10,000)","Invalid data", 1);
            trials=10000;
        }
        System.out.println(trials);
        
        paradox=new BirthdayParadox(trials);
        paradox.display(paradox.plot());
        
    }
}
