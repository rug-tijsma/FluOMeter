import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class FluOMeterViewer extends JFrame implements ActionListener {

	private static final long serialVersionUID = 8453164331402975408L;
	private static final String INFLUENZA_A = "Influenza A";
	private static final String INFLUENZA_B = "Influenza B";
	private static final String INFLUENZA_C = "Influenza C";
	private static final String INFLUENZA_ALL = "All types of influenza";
	private static final String ADD_FLU = "Add flu";
	private static final String MANUAL = "Manual";

	private JRadioButton influenza_a, influenza_b, influenza_c, influenza_all;
	private JButton add_flu, manual;

	public FluOMeterViewer() {
		JPanel main_panel = new JPanel(new BorderLayout());
		
		JPanel west_panel = new JPanel();
		west_panel.setPreferredSize(new Dimension(1300, 900));
		west_panel.add(new JLabel("HI"));

		JPanel east_panel = new JPanel(new GridLayout(4, 1));
		east_panel.setPreferredSize(new Dimension(300, 900));

		for (int i = 0; i < 2; i++) {
			east_panel.add(new JPanel());
		}

		JPanel east_sub_panel = new JPanel(new BorderLayout());
		JPanel influenza_panel = new JPanel();
		ButtonGroup button_group = new ButtonGroup();
		influenza_a = new JRadioButton(INFLUENZA_A);
		influenza_a.addActionListener(this);
		influenza_a.setActionCommand(INFLUENZA_A);
		influenza_b = new JRadioButton(INFLUENZA_B);
		influenza_b.addActionListener(this);
		influenza_b.setActionCommand(INFLUENZA_B);
		influenza_c = new JRadioButton(INFLUENZA_C);
		influenza_c.addActionListener(this);
		influenza_c.setActionCommand(INFLUENZA_C);
		influenza_all = new JRadioButton(INFLUENZA_ALL);
		influenza_all.addActionListener(this);
		influenza_all.setActionCommand(INFLUENZA_ALL);
		influenza_all.setSelected(true);
		button_group.add(influenza_a);
		button_group.add(influenza_b);
		button_group.add(influenza_c);
		button_group.add(influenza_all);
		influenza_panel.add(influenza_a);
		influenza_panel.add(influenza_b);
		influenza_panel.add(influenza_c);
		influenza_panel.add(influenza_all);
		east_sub_panel.add(new JLabel("Select visible type(s) of influenza"),
				BorderLayout.NORTH);
		east_sub_panel.add(influenza_panel, BorderLayout.CENTER);

		JPanel button_panel = new JPanel(new GridLayout(2, 1));
		add_flu = new JButton("Register new flu patient");
		add_flu.addActionListener(this);
		add_flu.setActionCommand(ADD_FLU);
		manual = new JButton(MANUAL);
		manual.addActionListener(this);
		manual.setActionCommand(MANUAL);
		button_panel.add(add_flu);
		button_panel.add(manual);

		east_panel.add(east_sub_panel);
		east_panel.add(button_panel);
		main_panel.add(east_panel, BorderLayout.EAST);
		main_panel.add(west_panel, BorderLayout.CENTER);

		this.getContentPane().add(main_panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("FluOMeter - by Thom Carretero Seinhorst and Ynte Tijsma");
		this.setSize(1600, 900);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action_command = e.getActionCommand();

		if (INFLUENZA_A.equals(action_command)) {
			System.out.println("Showing: " + INFLUENZA_A);
		} else if (INFLUENZA_B.equals(action_command)) {
			System.out.println("Showing: " + INFLUENZA_B);
		} else if (INFLUENZA_C.equals(action_command)) {
			System.out.println("Showing: " + INFLUENZA_C);
		} else if (INFLUENZA_ALL.equals(action_command)) {
			System.out.println("Shwoing: " + INFLUENZA_ALL);
		} else if (ADD_FLU.equals(action_command)) {
			addFlu();
		} else if (MANUAL.equals(action_command)) {
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
		JOptionPane.showMessageDialog(this, "All the manuals", "Manual", JOptionPane.PLAIN_MESSAGE);
	}

	public static void main(String[] args) {
		new FluOMeterViewer();
	}

}
