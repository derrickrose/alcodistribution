package com.pushtech.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DefaultPanel extends JPanel {

	private CustomJButton launch;
	private CustomJButton cancel;
	private static final Dimension BUTTON_SIZE = new Dimension(200, 25);
	private static final Dimension TEXT_FIELD_SIZE = new Dimension(400, 25);

	public DefaultPanel() {
		super();

		this.setLayout(new BorderLayout());

		JPanel labelPanel = new JPanel();
		GridLayout labelLayout = new GridLayout(4, 1);
		labelPanel.setLayout(labelLayout);

		CustomJLabel urlLabel = new CustomJLabel("Url to crawl");
		labelPanel.add(urlLabel);

		CustomJLabel dataBaseLink = new CustomJLabel("Data base link");
		labelPanel.add(dataBaseLink);

		CustomJLabel dataBaseUser = new CustomJLabel("Data base user");
		labelPanel.add(dataBaseUser);

		CustomJLabel dataBaseUserPassWord = new CustomJLabel("User password");
		labelPanel.add(dataBaseUserPassWord);

		this.add(labelPanel, BorderLayout.WEST);

		// ----------------------------------------------------------------------------------------------

		JPanel centerContainer = new JPanel();
		centerContainer.setLayout(new GridLayout(4, 1));

		CustomJTextField urlField = new CustomJTextField("url", TEXT_FIELD_SIZE);
		centerContainer.add(urlField);
		
		CustomJTextField dataBaseLinkField = new CustomJTextField("link",
				TEXT_FIELD_SIZE);
		centerContainer.add(dataBaseLinkField);

		CustomJTextField dataBaseUserField = new CustomJTextField("user",
				TEXT_FIELD_SIZE);
		centerContainer.add(dataBaseUserField);

		CustomJTextField dataBaseUserPasswordField = new CustomJTextField(
				"pass", TEXT_FIELD_SIZE);
		centerContainer.add(dataBaseUserPasswordField);

		this.add(centerContainer, BorderLayout.CENTER);

		// --------------------------------------------------------------------------------------------
		/**
		 * --------------------------------------------------------------------
		 * -------
		 **/
		JPanel userActionPanel = new JPanel();

		initLaunchButton();
		userActionPanel.add(launch);

		initCancelButton();
		userActionPanel.add(cancel);

		this.add(userActionPanel, BorderLayout.SOUTH);
	}

	private void initCancelButton() {
		cancel = new CustomJButton("Cancel", BUTTON_SIZE);
		cancel.getComponent().setEnabled(false);
		((JButton) cancel.getComponent())
				.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						launch.getComponent().setEnabled(true);
						cancel.getComponent().setEnabled(false);
					}
				});
	}

	private void initLaunchButton() {
		launch = new CustomJButton("Launch", BUTTON_SIZE);
		launch.getComponent().setEnabled(true);
		((JButton) launch.getComponent())
				.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {

						launch.getComponent().setEnabled(false);
						cancel.getComponent().setEnabled(true);
					}
				});
	}
}
