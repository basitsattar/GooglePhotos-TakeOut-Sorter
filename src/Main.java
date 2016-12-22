import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame{
	public static void main(String args[]){
		Main frame = new Main();
		frame.add(new MyPanel());
		frame.setSize(300, 70);
		frame.setVisible(true);
	}

}

class MyPanel extends JPanel implements ActionListener{
	
	String rootPath;
	JLabel completedLabel ;
	{
		JButton button = new JButton("Select Folder");
		button.addActionListener(this);
		add(button);
		completedLabel = new JLabel("Completed Successfully");
		completedLabel.setVisible(false);
		add(completedLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser= new JFileChooser();
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int choice = chooser.showOpenDialog(getParent());
		if (choice != JFileChooser.APPROVE_OPTION) return;
		File chosenFile = chooser.getSelectedFile();
		rootPath = chosenFile.getAbsolutePath();
		System.out.println(rootPath);
		showFiles(chosenFile);
		completedLabel.setVisible(true);
		
	}
	
	public  void showFiles(File f) {
		File[] files = f.listFiles();
	    for (File file : files) {
	        if (file.isDirectory()) {
	            System.out.println("Directory: " + file.getName());
	            showFiles(file);
	        } else {
	        	String fileName = file.getName();
	        	String extension = fileName.substring(fileName.lastIndexOf(".")+1);
	        	if(extension.equalsIgnoreCase("json")){
	        		file.delete();
	        		System.out.println("Deleted : " + fileName);
	        	}else{
	        		file.renameTo(new File(rootPath + "/" + fileName));
	            	System.out.println("Moved File: " + fileName);
	        	}
	        }
	    }
	}
}