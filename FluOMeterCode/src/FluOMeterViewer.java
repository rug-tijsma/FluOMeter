import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class FluOMeterViewer extends JFrame implements ActionListener{
	
	JButton add_flu, legend;

	public FluOMeterViewer() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add_flu = new JButton("Add flu");
		add_flu.addActionListener(this);
		legend = new JButton("Legend");
		legend.addActionListener(this);
		
		JPanel right_side_panel = new JPanel(new GridLayout(6, 1));
		right_side_panel.setPreferredSize(new Dimension(350, 900));
		
		JLabel label1 = new JLabel("- Register a new flu patient");
		right_side_panel.add(label1);
		
		JLabel label2 = new JLabel("Kind of flu:");
		JRadioButton flu_a = new JRadioButton("Influenza A");
		JRadioButton flu_b = new JRadioButton("Influenza B");
		JRadioButton flu_c = new JRadioButton("Influenza C");
		JPanel radio_panel = new JPanel(new GridLayout(3, 1));
		ButtonGroup group = new ButtonGroup();
		group.add(flu_a);
		group.add(flu_b);
		group.add(flu_c);
		radio_panel.add(flu_a);
		radio_panel.add(flu_b);
		radio_panel.add(flu_c);
		right_side_panel.add(label2);
		right_side_panel.add(radio_panel);
		
		JLabel label3 = new JLabel("Location \n (e.g \"Province/State, City/Village\"):");
		right_side_panel.add(label3);
		
		JTextField location = new JTextField();
		right_side_panel.add(location);
		
		JPanel main_panel = new JPanel(new BorderLayout());
		main_panel.add(right_side_panel, BorderLayout.EAST);
		
		this.setTitle("FluOMeter - by Thom Carretero Seinhorst and Ynte Tijsma");
		this.getContentPane().add(main_panel);
		this.setSize(1600, 900);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();

		if (button == add_flu) {
			
		} else if (button == legend) {
			
		}
	}
	
	public static void main(String[] args) {
		new FluOMeterViewer();
	}
	

}
