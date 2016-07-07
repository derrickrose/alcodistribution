package com.pushtech.crawler.ihm;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ProgressBarTest extends JFrame {
   JProgressBar pbar;
   JPanel controlPanel = new JPanel();
   static final int MY_MINIMUM = 0;

   static final int MY_MAXIMUM = 1000;

   public ProgressBarTest(String strTitle) {
      this.setTitle(strTitle);
      this.setSize(400, 250);
      this.setLocationRelativeTo(null);
      pbar = new JProgressBar();
      pbar.setMinimum(0);
      pbar.setMaximum(1000);
      pbar.setValue(0);
      pbar.setStringPainted(true);

      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JLabel title = new JLabel("Fenêtre d'administration");
      this.setLayout(new GridLayout(6, 2, 10, 10));

      // On ajoute le bouton au content pane de la JFrame
      JTextField urlField = new JTextField(10);
      this.getContentPane().add(new JLabel("Url to crawl"));

      this.getContentPane().add(urlField);

      this.getContentPane().add(new JLabel("Database link"));

      this.getContentPane().add(new JTextField(10));
      this.getContentPane().add(new JLabel("Database user"));

      this.getContentPane().add(new JTextField(10));
      this.getContentPane().add(new JLabel("Database pass"));

      this.getContentPane().add(new JTextField(10));
      JButton launch = new JButton("Launch");

      this.getContentPane().add(new JButton("Cancel"));

      this.setVisible(true);
      this.getContentPane().add(pbar);
      launch.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {

            for (int i = MY_MINIMUM; i <= MY_MAXIMUM; i++) {
               // int percent = i;

               pbar.setValue(i);
               // urlField.setText(String.valueOf(percent));
               System.out.println("===> " + String.valueOf(i));
               try {
                  Thread.sleep(500);
               } catch (InterruptedException e1) {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
               }
            }

            // SwingUtilities.invokeLater(new Runnable() {
            // @Override
            // public void run() {

            // for (int i = MY_MINIMUM; i <= MY_MAXIMUM; i++) {
            // // int percent = i;
            //
            // pbar.setValue(i);
            // // urlField.setText(String.valueOf(percent));
            // System.out.println("===> " + String.valueOf(i));
            // try {
            // Thread.sleep(500);
            // } catch (InterruptedException e1) {
            // // TODO Auto-generated catch block
            // e1.printStackTrace();
            // }
            // }

            Thread t = new Thread(new T());
            t.start();
            //
            // }
            //
            // });

         }
      });
      this.getContentPane().add(launch);
      pbar.setStringPainted(true);
      // controlPanel.add(pbar);

   }

   public void updateBar(int newValue) {
      pbar.setValue(newValue);
   }

   class T implements Runnable {
      @Override
      public void run() {
         for (int i = MY_MINIMUM; i <= MY_MAXIMUM; i++) {
            // int percent = i;

            pbar.setValue(i);
            // urlField.setText(String.valueOf(percent));
            System.out.println("===> " + String.valueOf(i));
            try {
               Thread.sleep(500);
            } catch (InterruptedException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
      }

   }

//   public static void main(String[] args) {
//      try {
//         UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//      } catch (ClassNotFoundException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      } catch (InstantiationException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      } catch (IllegalAccessException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      } catch (UnsupportedLookAndFeelException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//      new ProgressBarTest("Alco Distribution");
//   }

}