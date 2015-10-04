import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class JeopardyGame extends JPanel implements ActionListener {
	private JButton[][] btn = new JButton[5][4];
	private JFrame frame;
	private JButton btnAnswer;
	private JLabel question;
	private JPanel panel;
	private int i=0, j=0;
	private String[][] questions = { 
			{ "This person is a current companion of the Doctor", "This actor played 10th Doctor", "This enemy`s favorite word is 'Exterminate'", "The number of complete seasons of Doctor Who after the revival of the show" },
			{ "Bad Wolf entity was created by this character", "Favourite piece of clothing of 11th Doctor", "This enemy is constantly upgrading", "The date the last episode of Doctor Who aired on TV" },
			{ "This charactet lost all of her memories about the Doctor", "Number of different versions of the Doctor we have seen so far", "We forgot everything about this enemy", "The current showrunner of Doctor Who" },
			{ "This character is a daughter of one of the Doctor`s companions", "The amount of regenerations the Doctor had originally", "This character was driven insane by the drumbeat.", "This version of the Doctor stayed on the show for the longest" },
			{ "This character is suspected to be the Face of Boe", "The question that must not be answered", "This planet is Daleks` homeworld", "The date the first episode of Doctor Who air (The year is enough)" } };
	private String[][] answers = {
			{ "What is Clara Oswald?", "What is David Tennant?", "What is a Dalek?", "What is 8?" },
			{ "What is Rose Tyler?", "What is a bowtie?", "What is a Cyberman?", "What is September 19 (Last Saturday)?" },
			{ "What is Donna Noble?", "What is 13?", "What is the Silence?", "What is Steven Moffat?" },
			{ "What is River Song? ", "What is 12?", "What is the Master?", "What is the 4th Doctor?" },
			{ "What is Captain Jack Harkness?", "What is Doctor Who?", "What is Scaro?", "What is 1963?" }, };

	public static void main(String[] args) {
		new JeopardyGame();
	}

	public JeopardyGame() {
		// set up buttons
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 4; j++) {
				btn[i][j] = new JButton();
				btn[i][j].setPreferredSize(new Dimension(400, 100));
				btn[i][j].setFont(new Font("Tahoma", Font.PLAIN, 34));
				btn[i][j].setBackground(new Color(30, 144, 255));
				btn[i][j]
						.setBorder(BorderFactory.createLineBorder(Color.white));
				btn[i][j].addActionListener(this);
				if (i == 0) {
					btn[i][j].setText("100");
				} else if (i == 1) {
					btn[i][j].setText("200");
				} else if (i == 2) {
					btn[i][j].setText("300");
				} else if (i == 3) {
					btn[i][j].setText("400");
				} else {
					btn[i][j].setText("500");
				}
			}
		// set up title
		JLabel title = new JLabel();
		title.setFont(new Font("Tahoma", Font.BOLD, 40));
		title.setIcon(new ImageIcon("logo.png"));
		title.setForeground(new Color(30, 144, 255));
		title.setPreferredSize(new Dimension(1500, 250));
		title.setHorizontalAlignment(0);

		// set up labels for categories
		JLabel[] categories = new JLabel[4];
		categories[0] = new JLabel("Companions");
		categories[1] = new JLabel("The Doctor");
		categories[2] = new JLabel("Enemies");
		categories[3] = new JLabel("The show");
		for (JLabel element : categories) {
			element.setPreferredSize(new Dimension(400, 50));
			element.setFont(new Font("Tahoma", Font.BOLD, 32));
			element.setForeground(new Color(30, 144, 255));

		}
		// set up the panel
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridwidth = 4;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(5, 5, 20, 20);
		this.add(title, gc);
		gc.gridy = 1;
		gc.gridwidth = 1;
		for (JLabel element : categories) {
			this.add(element, gc);
			gc.gridx++;
		}
		gc.gridy = 2;
		gc.gridx = 0;
		for (JButton[] btns : btn) {
			for (JButton button : btns) {
				this.add(button, gc);
				gc.gridx++;
			}
			gc.gridy++;
			gc.gridx = 0;
		}
		// create new buttons and question with an answer
		question = new JLabel();
		//question.setPreferredSize(new Dimension(1920, 50));
		question.setBounds(50, 400, 1920-100, 100);
		question.setFont(new Font("Tahoma", Font.BOLD, 50));
		question.setHorizontalAlignment(0);
		btnAnswer = new JButton();
		//btnAnswer.setPreferredSize(new Dimension(300, 100));
		btnAnswer.setBounds(750,500,400,100);
		btnAnswer.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnAnswer.addActionListener(this);

		// create the frame
		frame = new JFrame();
		frame.setTitle("Doctor Who Jeopardy!");
		frame.setSize(1920, 1080);
		frame.setName("Jeopardy");
		frame.setResizable(false);
		frame.setContentPane(this);
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//System.out.println("Running");
		if (this.isVisible() == true)
			g2.drawImage(new ImageIcon("back1.jpg").getImage(), 0, 0, this);
	/*	else {
			g2.drawImage(new ImageIcon("back2.jpg").getImage(), 0, 0, panel);
			System.out.println("gotcha!");
		}*/
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAnswer) {
			if (btnAnswer.getText() == "ANSWER") {
				btnAnswer.setText("OK");
				question.setText(answers[i][j]);
			} else {
				frame.setContentPane(this);
				this.setVisible(true);
				repaint();
			}
		}
		for (int i1 = 0; i1 < 5; i1++) {
			for (int j1= 0; j1 < 4; j1++) {
				if (e.getSource() == btn[i1][j1]) {
					i=i1;
					j=j1;
					btn[i][j].setEnabled(false);
					this.setVisible(false);
					btnAnswer.setText("ANSWER");
					question.setText(questions[i][j]);
					// set new panel with a question and an answer
					panel = new JPanel();
					panel.setLayout(null);
					panel.setVisible(true);
					panel.add(question);
					panel.add(btnAnswer);
					panel.setBackground(new Color(30,144,255));
					frame.setContentPane(panel);
					break;
				}
			}
		}
	}
}
