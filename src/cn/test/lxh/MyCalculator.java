package cn.test.lxh;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class MyCalculator{
	private Frame f;
	private TextField tf=new TextField(30);
	private double result;
	private boolean append=false;
	private char operator='=';
	private Button[] btn=new Button[15];
	public 	MyCalculator(){
		initComponent();
	}
	private void initComponent(){
		f=new Frame("xingyezhuifeng");
		f.setLayout(new BorderLayout());;
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt){
				System.exit(0);
			}
		});
		Panel centePanel=new Panel();
		centePanel.setLayout(new GridLayout(5, 3));
		NumberListener nl=new NumberListener();
		OperatorListener ol=new OperatorListener();
		btn[10]=new Button("+");
		btn[11]=new Button("-");
		btn[12]=new Button("*");
		btn[13]=new Button("/");
		btn[14]=new Button("=");
		for (int i=0;i<=9;i++){
			btn[i]=new Button(String.valueOf(i));
			centePanel.add(btn[i]);
			btn[i].addActionListener(nl);
			if(i%2==1){
				centePanel.add(btn[(i+19)/2]);
				btn[(i+19)/2].addActionListener(ol);			
			}
		}
		f.add(centePanel,BorderLayout.CENTER);
		Panel northPanel=new Panel();
		tf.setEditable(false);
		northPanel.add(tf);
		f.add(northPanel,BorderLayout.NORTH);
	}
	public void go(){
		f.pack();
		f.setVisible(true);
	} 
	public static void main(String[] args){
		new MyCalculator().go();
	}
	class NumberListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(!append){
				tf.setText("");
				append=true;
			}
			String s=tf.getText();
			s+=e.getActionCommand();
			tf.setText(s);
			if(!btn[10].isEnabled()){
				for(int i=10;i<=14;i++)
					btn[i].setEnabled(true);
			}
		}		
	}
	class OperatorListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(!append)
				return;
			for(int i=10;i<=14;i++)
				btn[i].setEnabled(false);
			String s=tf.getText();
			double num=Double.parseDouble(s);
			append=false;
			switch (operator) {
			case '+':result+=num;
			break;
			case '-':result-=num;
			break;
			case '*':result*=num;
			break;
			case '/':{
				if(num==0)
                result=0;
				else
				result/=num;				
				break;
			}
			case '=':result=num;
			break;
			}
			tf.setText(String.valueOf(result));
			String op=e.getActionCommand();
			operator=op.charAt(0);
		}		
	}
}