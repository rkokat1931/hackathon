import javax.swing.*; //overall graphics package
import java.awt.*;    //imports the Abstract Window Toolkit
import java.awt.event.*;  //allows for action listening (buttons)
import java.text.*;

public class taxCalc {
  JFrame frame;
  JPanel pan;
  JLabel askState, askTotal, taxedTotal, currentTotal;
  JTextField enterItem;
  JComboBox selectState; //drop down for state
  JButton doCalculation, addItem;
  JButton cat; //CAT

  double tempTotal=0;
  
  final String[] STATES = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY" }; 
  //percents
  final double[] TAXES = {0.04, 0.0, 0.056, .065, 0.725,  .029, .0635, 0.066, 0.06, 0.06, 0.04, 0.04, 0.06, 0.0625, 0.07,0.06, .065, 0.06, 0.045,0.055, 0.06, 0.0625, 0.06, 0.06875, 0.07, 0.04225,0.0, 0.055, 0.046, 0.0, 0.6625, 0.05125, 0.04, 0.0475, 0.05, 0.0575, 0.045, 0.0, 0.06, 0.07, 0.06, 0.045, 0.07, 0.065, 0.047, 0.06, 0.053, 0.065, 0.06, 0.05, .04}; 

  public taxCalc() {
    //setting up the frame
    frame = new JFrame("Sales Tax Calculation");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //setting up the panel
    pan = new JPanel();
    pan.setLayout(new GridLayout(0,2,10,5)); //2 column, infinite rows grid 
    pan.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

    //creating text labels
    askState = new JLabel ("What state are you shopping in?"); 
    askTotal = new JLabel ("How much does your item cost?"); 
    taxedTotal = new JLabel("Taxed Total: ");
    currentTotal = new JLabel("Current Total: ");

    //creating textField - text entry
    enterItem = new JTextField(10);
    
    //creating drop down
    selectState = new JComboBox(STATES);
    selectState.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
    
    //creating button
    NumberFormat formatter = NumberFormat.getCurrencyInstance(); //formatting
    doCalculation = new JButton( new AbstractAction("Calculate") {
      @Override
      public void actionPerformed( ActionEvent e ) {
        //retrieve combobox value
        int s = selectState.getSelectedIndex();
        
        //get the tax percent
        double taxp = TAXES[s];

        //get total amount
        double total;
        if (enterItem.getText().equals(""))
          total = tempTotal;
        else
          total = tempTotal + Double.parseDouble(enterItem.getText());

        //Calculation
        double calculated = total + (total * taxp);

        //output
        currentTotal.setText("Current Total: "+formatter.format(total));
        taxedTotal.setText("Taxed Total: "+formatter.format(calculated));
      }
    });

    addItem = new JButton(new AbstractAction("Add Item") {
      @Override
      public void actionPerformed(ActionEvent e) {
        //actions
        tempTotal += Double.parseDouble(enterItem.getText());
        currentTotal.setText("Current Total: "+formatter.format(tempTotal));
        
        //clearing current textField
        enterItem.setText("");
      }
    });

    cat = new JButton(new AbstractAction("CLICK 4 MOTIVATION"){
      @Override
      public void actionPerformed(ActionEvent e) {
        JFrame catFrame = new JFrame("MONEY KITTY");
        catFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel catPan = new JPanel();

        //ADD CAT PICTURE
        JLabel kitty = new JLabel(new ImageIcon("money_kitty.jpg"));
        catPan.add(kitty);

        catFrame.setContentPane(catPan);
        catFrame.pack();
        catFrame.setVisible(true);
        
      }
    });

    //adding all the components to the panel
    pan.add(askState);
    pan.add(selectState);
    pan.add(askTotal);
    pan.add(enterItem);
    pan.add(currentTotal);
    pan.add(addItem);
    pan.add(doCalculation);
    pan.add(taxedTotal);
    pan.add(cat);

    frame.setContentPane(pan);
    frame.pack();
    frame.setVisible(true);
    
  }

  //create the runGUI command
	private static void runGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		taxCalc greeting = new taxCalc();
	}

  //run it
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				runGUI();
			}
		});
    
	}


}
