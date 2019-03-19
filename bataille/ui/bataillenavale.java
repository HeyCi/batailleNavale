package adrar.bataille.ui;

import java.awt.EventQueue;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JPanel;

import adrar.bataille.ui.Datas.Grid;
import adrar.bataille.ui.Datas.GridPosition;
import adrar.bataille.ui.Enums.GridNumber;
import adrar.bataille.ui.Enums.Letter;

public class bataillenavale {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Grid();
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					bataillenavale window = new bataillenavale();
					window.GenerateMap();
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public bataillenavale() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 962, 628);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
	}
	
	private void GenerateMap()
	{
		int startX = 80;
		int startY = 0;
		int currentX = startX;
		int currentY = startY;
		int sizeLabelX = 85;
		int sizeLabelY = 50;
		int sizeCanvasX = sizeLabelX;
		int sizeCanvasY = 50;
		Random rColor = new Random();

		for (Letter letter : Letter.values())
		{			
			frame.getContentPane().add(CreateLabel(letter.toString(), currentX, currentY, sizeLabelX, sizeLabelY));
			currentX += sizeLabelX;
		}
		
		currentX = 0;
		sizeLabelX = startX;
		currentY = sizeLabelY;
		
		for (GridNumber number : GridNumber.values())
		{			
			frame.getContentPane().add(CreateLabel(number.getValue() + "", currentX, currentY, sizeLabelX, sizeLabelY));
			currentX += sizeLabelX;
			
			for (Letter letter : Letter.values()) {
				Color color = new Color(rColor.nextInt(255), rColor.nextInt(255), rColor.nextInt(255));				
				CreateCase(Grid.instance.getPosition(letter,  number), currentX, currentY, sizeCanvasX, sizeCanvasY, color);
				
				currentX += sizeCanvasX;
			}
			
			currentX = 0;
			currentY += sizeCanvasY;
		}

	}
	
	private JLabel CreateLabel(String content, int x, int y, int sizeX, int sizeY)
	{
		JLabel label = new JLabel();
		
		label.setText(content);
		label.setSize(sizeX, sizeY);
		label.setLocation(x, y);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.black));

		return label;
	}
	private void CreateCase(GridPosition position, int x, int y, int sizeX, int sizeY, Color color)
	{
		JPanel panel = new JPanel();
		panel.setLocation(x, y);
		panel.setSize(sizeX, sizeY);
		panel.setBackground(color);
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel label = CreateLabel(position.getKey(), x, y, sizeX, sizeY);
		label.setForeground(Color.yellow);
		label.setBorder(null);
		panel.add(label);
		
		position.setPanel(panel);		
		frame.getContentPane().add(panel);
	}
}
