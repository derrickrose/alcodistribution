package com.pushtech.ihm;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CentralPanel extends JPanel {

   private JButton launch;
   private JButton cancel;
   private static final Dimension BUTTON_SIZE = new Dimension(200, 25);

   public CentralPanel() {
      super();
      this.setLayout(new GridLayout(6, 2, 10, 10));
      // On ajoute le bouton au content pane de la JFrame

      // Border titleBorder = new TitledBorder(new LineBorder(Color.BLACK), "Alco");

      this.add(new JPanel().add((new JLabel("Url to crawl"))));
      this.add(new JTextField(10));
      this.add(new JLabel("Database link"));
      this.add(new JTextField(10));
      this.add(new JLabel("Database user"));
      this.add(new JTextField(10));
      this.add(new JLabel("Database pass"));
      this.add(new JTextField(10));

      launch = initLaunchButton();
      JPanel launchButtonContainer = new JPanel();
      launchButtonContainer.add(launch);
      this.add(launchButtonContainer);

      cancel = initCancelButton();
      JPanel cancelButtonContainer = new JPanel();
      cancelButtonContainer.add(cancel);
      this.add(cancelButtonContainer);

      // this.setBorder(titleBorder);
   }

   private JButton initCancelButton() {
      JButton cancel = new JButton("Cancel");
      cancel.setEnabled(false);
      cancel.setPreferredSize(BUTTON_SIZE);
      cancel.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
            launch.setEnabled(true);
            cancel.setEnabled(false);
         }
      });
      return cancel;
   }

   private JButton initLaunchButton() {
      JButton launch = new JButton("Launch");
      launch.setPreferredSize(BUTTON_SIZE);//
      launch.setEnabled(true);
      launch.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {

            launch.setEnabled(false);
            cancel.setEnabled(true);
         }
      });
      return launch;
   }

}
