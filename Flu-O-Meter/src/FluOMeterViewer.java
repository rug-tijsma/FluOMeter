import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class FluOMeterViewer extends JFrame implements ActionListener {

	private static final String INFLUENZA_A = "Influenza A";
	private static final String INFLUENZA_B = "Influenza B";
	private static final String INFLUENZA_C = "Influenza C";
	private static final String INFLUENZA_ALL = "All types of influenza";
	private static final String ADD_FLU = "Add flu";
	private static final String REDRAW = "Redraw map";
	private static final String HELP = "Help";
	private static final String URL_STRING = "http://localhost:8080/Flu-O-Meter/FluOMeterServlet";

	private ButtonGroup button_group;
	private JRadioButton influenza_a, influenza_b, influenza_c, influenza_all;
	private JButton add_flu, redraw, manual;

	public FluOMeterViewer() {
		JPanel main_panel = new JPanel(new BorderLayout());

		JPanel center_panel = new JPanel(new BorderLayout());
		JPanel influenza_panel = new JPanel(new GridLayout(2, 2));
		button_group = new ButtonGroup();
		influenza_a = new JRadioButton(INFLUENZA_A);
		influenza_a.addActionListener(this);
		influenza_b = new JRadioButton(INFLUENZA_B);
		influenza_b.addActionListener(this);
		influenza_c = new JRadioButton(INFLUENZA_C);
		influenza_c.addActionListener(this);
		influenza_all = new JRadioButton(INFLUENZA_ALL);
		influenza_all.addActionListener(this);
		influenza_all.setSelected(true);
		button_group.add(influenza_a);
		button_group.add(influenza_b);
		button_group.add(influenza_c);
		button_group.add(influenza_all);
		influenza_panel.add(influenza_a);
		influenza_panel.add(influenza_b);
		influenza_panel.add(influenza_c);
		influenza_panel.add(influenza_all);
		center_panel.add(influenza_panel, BorderLayout.CENTER);
		center_panel.add(new JLabel("Select visible type(s) of influenza"),
				BorderLayout.NORTH);

		JPanel south_panel = new JPanel(new FlowLayout());
		add_flu = new JButton("Register new flu patient");
		add_flu.addActionListener(this);
		add_flu.setActionCommand(ADD_FLU);
		redraw = new JButton(REDRAW);
		redraw.addActionListener(this);
		redraw.setActionCommand(REDRAW);
		manual = new JButton(HELP);
		manual.addActionListener(this);
		manual.setActionCommand(HELP);
		south_panel.add(add_flu);
		south_panel.add(redraw);
		south_panel.add(manual);

		main_panel.add(new JLabel(""), BorderLayout.WEST);
		main_panel.add(new JLabel(""), BorderLayout.EAST);
		main_panel.add(center_panel, BorderLayout.CENTER);
		main_panel.add(south_panel, BorderLayout.SOUTH);

		this.getContentPane().add(main_panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("FluOMeter");
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action_command = e.getActionCommand();

		if (ADD_FLU.equals(action_command)) {
			addFlu();
		} else if (REDRAW.equals(action_command)) {
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI(URL_STRING));
				} catch (IOException | URISyntaxException ex) {
					ex.printStackTrace();
				}
			}
		} else {
			showManual();
		}
	}

	private void addFlu() {
		String location = (String) JOptionPane.showInputDialog(this,
				"Location: \n (e.g <Province/State>,<City/Village>",
				"Register new flu patient", JOptionPane.PLAIN_MESSAGE, null,
				null, "");
		if (location != null) {
			Object[] possibilities = { INFLUENZA_A, INFLUENZA_B, INFLUENZA_C };
			String flu = (String) JOptionPane.showInputDialog(this,
					"What kind of flu does the patient have?", "Type of flu",
					JOptionPane.PLAIN_MESSAGE, null, possibilities, "");
			if (flu != null)
				new RabbitMQSender(location + "," + flu);
		}
	}

	private void showManual() {
		JOptionPane.showMessageDialog(this, "All the manuals", "Manual",
				JOptionPane.PLAIN_MESSAGE);
	}

	public String getSelectedFlu() {
		for (Enumeration<AbstractButton> buttons = button_group.getElements(); buttons
				.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected())
				return button.getText();
		}
		return null;
	}

}
