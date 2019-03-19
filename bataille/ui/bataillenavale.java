package adrar.bataille.ui;

import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JPanel;

import adrar.bataille.ui.Abstract.AbstractIA;
import adrar.bataille.ui.Datas.*;
import adrar.bataille.ui.Enums.*;

public class bataillenavale {

	public JFrame frame;
	public AbstractIA ia;
	public Grid grid;
	
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
	
	public void GenerateMap()
	{
		grid = new Grid();
		
		int startX = 80;
		int startY = 0;
		int currentX = startX;
		int currentY = startY;
		int sizeLabelX = 85;
		int sizeLabelY = 50;
		int sizeCanvasX = sizeLabelX;
		int sizeCanvasY = 50;

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
				CreateCase(grid.getPosition(letter,  number), currentX, currentY, sizeCanvasX, sizeCanvasY);
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
	private void CreateCase(GridPosition position, int x, int y, int sizeX, int sizeY)
	{
		JPanel panel = new JPanel();
		panel.setLocation(x, y);
		panel.setSize(sizeX, sizeY);
		panel.setBackground(Color.GRAY);
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel.setEnabled(false);
		
		JLabel label = CreateLabel(position.getKey(), x, y, sizeX, sizeY);
		label.setForeground(Color.yellow);
		label.setBorder(null);
		panel.add(label);
		
		position.setPanel(panel);		
		frame.getContentPane().add(panel);
	}

	public void createShipsForIA()
	{
		//random selection test (not working)
		
		ShipDirection[] directions = ShipDirection.values();
		Letter[] letters = Letter.values();
		GridNumber[] numbers = GridNumber.values();
		
		Random rand = new Random();
		
		for (ShipType shipType : ShipType.values()) {
			
			ShipDirection direction = directions[rand.nextInt(directions.length)];
			Ship ship = null;
			
			do {
				GridPosition position = grid.getPosition(letters[rand.nextInt(letters.length)], numbers[rand.nextInt(numbers.length)]);
				ship = grid.createShip(position, direction, shipType);
			}
			while (ship == null);
			
			ia.addShip(ship);
		}
	}
	
	public void handSelection()
	{
		grid.activeSelection();
	}
	
	
	
	
	
	
	
	
	
	
}
