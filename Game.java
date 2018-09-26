
import java.awt.*;
import java.awt.event.*;

class TicTacToe extends Frame implements ActionListener{
	Label status;
	int CrossZero = 1;
	Button[][] b;
	TicTacToe(){
		setSize(408, 450);
		this.setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		
		setLayout(null);
		setVisible(true);
		setTitle("Tic-Tac-Toe");

		
		Panel game_area = new Panel();
		
		game_area.setBounds(5, 25, 400, 400);
		
		game_area.setLayout(new GridLayout(3,3));
		b = new Button[3][3];
		Font f = new Font("Times New Roman",Font.PLAIN,60);
		for(int i=0;i<3;i++)
		{
			for(int j = 0; j<3;j++)
			{
				b[i][j] = new Button();
				b[i][j].addActionListener(this);
				b[i][j].setFont(f);
				game_area.add(b[i][j]);
			}
			
		}
		add(game_area);
		status = new Label("Start the game now...");
		
		status.setBounds(5, 25 + game_area.getHeight(), game_area.getWidth(), 20);
		status.setBackground(Color.CYAN);
		status.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				status.setBackground(Color.CYAN);
				status.setText("Game Restarted...");
				restart();
			}
			
		});
		add(status);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		
	}
	
	private void restart() {
		for(Button row[] : b)
		{
			for(Button x: row)
			{
				x.setLabel("");
				x.setEnabled(true);
			}
		}
	}
	
	private void disableAll() {
		for(Button row[] : b)
		{
			for(Button x: row)
			{
				x.setEnabled(false);
			}
		}
	}
	
	private int val(String xo)
	{
		if(xo.equals("X"))
			return 1;
		else if(xo.equals("O"))
			return -1;
		else
			return 0;
	}
	
	private Boolean check_tie(){
		for(Button row[] : b)
		{
			for(Button x: row)
			{
				if(x.getLabel().equals(""))
					return false;
			}
		}
		return true;
	}
	
	private void check_winner()
	{
		int i,j;
		int sum = 0;
		for(i=0;i<3;i++)
		{
			sum = 0;
			for(j=0;j<3;j++)
			{
				sum += (val(b[i][j].getLabel()));
			}
			if(sum==3 || sum== -3)
				break;
		}
		if(sum!=3 && sum!= -3)
		{
			for(j=0;j<3;j++)
			{
				sum = 0;
				for(i=0;i<3;i++)
				{
					sum += (val(b[i][j].getLabel()));
				}
				if(sum==3 || sum== -3)
					break;
			}
			if(sum!=3 && sum!= -3)
			{
				sum = 0;
				for(i=0;i<3;i++)
					sum+= (val(b[i][i].getLabel()));
				if(sum!=3 && sum!= -3)
				{
					sum = 0;
					for(i=0;i<3;i++)
						sum+= (val(b[i][2-i].getLabel()));
				}
				
			}
		}
		if(sum==3)
		{
			status.setText("Congrats! Winner is ' X ' Click me to Restart.");
			status.setBackground(Color.GREEN);
			disableAll();
		}
		else if(sum==-3)
		{
			status.setText("Congrats! Winner is ' O ' Click me to Restart.");
			status.setBackground(Color.GREEN);
			disableAll();
		}
		else if(check_tie())
		{
			status.setText("It's a TIE. Click me to Restart.");
			status.setBackground(Color.RED);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Button x = (Button) e.getSource();
		if(CrossZero == 1) {
			x.setLabel("X");
			x.setEnabled(false);
			CrossZero *= -1;
		}
		else
		{
			x.setLabel("O");
			x.setEnabled(false);
			CrossZero *= -1;
		}
		status.setText("");
		check_winner();
	}

		
}

public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TicTacToe();
		
	}

}
