package cz.muni.fi.pathtracer;

import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6811707662308413845L;
	Thread application;
	JPanelRenderer pane;
	static int width = 400;
	static int height = 400;

	public static void main(final String s[]) throws IOException {
		final JFrame frame = new JFrame("Render window");
		final Main mainForm = new Main();
		mainForm.init_pane(frame);
		frame.setSize(width + 8, height + 25);
		frame.validate();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void init_pane(final JFrame frame) throws IOException {
		this.pane = new JPanelRenderer(width, height);
		this.pane.setOpaque(true);
		this.pane.setLayout(null);
		frame.getContentPane().add(this.pane);
	}
}
