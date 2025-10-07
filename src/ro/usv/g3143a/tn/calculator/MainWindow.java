package ro.usv.g3143a.tn.calculator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private String expression;
	
	public void SetExpression(String exp)
	{
		expression = exp;
		textPane.setText(exp);
	}
	
	public String GetExpression()
	{
		return expression;
	}
	
	private float Evaluate() throws InvalidTokenException {
		expression = textPane.getText();
		
		if(expression.isEmpty())
			return 0.0f;
		
		return (new Parser(expression)).Evaluate();
	}

	JTextPane textPane;
	
	void ConcatenateExpression(String str)
	{
		SetExpression(GetExpression() + str);
	}
	
	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Calculator");
		expression = "";
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 269, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textPane = new JTextPane();
		textPane.setBounds(10, 11, 233, 20);
		contentPane.add(textPane);
		
		JButton btn1 = new JButton("1");
		btn1.setBounds(10, 45, 49, 23);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConcatenateExpression("1");
			}
		});
		contentPane.add(btn1);
		
		JButton btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConcatenateExpression("+");
			}
		});
		btnPlus.setBounds(187, 45, 56, 23);
		contentPane.add(btnPlus);
		
		JButton btnMinus = new JButton("-");
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConcatenateExpression("-");
			}
		});
		btnMinus.setBounds(187, 79, 56, 23);
		contentPane.add(btnMinus);
		
		JButton btnMul = new JButton("*");
		btnMul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConcatenateExpression("*");
			}
		});
		btnMul.setBounds(187, 113, 56, 23);
		contentPane.add(btnMul);
		
		JButton btnDiv = new JButton("\\");
		btnDiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConcatenateExpression("/");
			}
		});
		btnDiv.setBounds(187, 147, 56, 23);
		contentPane.add(btnDiv);
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConcatenateExpression("2");
			}
		});
		btn2.setBounds(69, 45, 49, 23);
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConcatenateExpression("3");
			}
		});
		btn3.setBounds(128, 45, 49, 23);
		contentPane.add(btn3);
		
		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConcatenateExpression("4");
			}
		});
		btn4.setBounds(10, 79, 49, 23);
		contentPane.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConcatenateExpression("5");
			}
		});
		btn5.setBounds(69, 79, 49, 23);
		contentPane.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConcatenateExpression("6");
			}
		});
		btn6.setBounds(128, 79, 49, 23);
		contentPane.add(btn6);
		
		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConcatenateExpression("7");
			}
		});
		btn7.setBounds(10, 113, 49, 23);
		contentPane.add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConcatenateExpression("8");
			}
		});
		btn8.setBounds(69, 113, 49, 23);
		contentPane.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConcatenateExpression("9");
			}
		});
		btn9.setBounds(128, 113, 49, 23);
		contentPane.add(btn9);
		
		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConcatenateExpression("0");
			}
		});
		btn0.setBounds(69, 147, 49, 23);
		contentPane.add(btn0);
		
		JButton btnNegate = new JButton("±");
		btnNegate.setBounds(10, 147, 49, 23);
		contentPane.add(btnNegate);
		
		JButton btnSep = new JButton(".");
		btnSep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConcatenateExpression(".");
			}
		});
		btnSep.setBounds(128, 147, 49, 23);
		contentPane.add(btnSep);
		
		JButton btnEquals = new JButton("=");
		btnEquals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SetExpression(Evaluate()+"");
				} catch (InvalidTokenException e1) {
					SetExpression("Syntax error! " + e1.getMessage());
				}
			}
		});
		btnEquals.setBounds(10, 181, 233, 23);
		contentPane.add(btnEquals);

	}
}
