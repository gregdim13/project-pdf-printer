package pdf_printer;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.text.PDFTextStripper; 
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//COMMMANDS FOR CMD
//cd C:\Users\gregd\eclipse-workspace\pdf_printer\src\pdf_printer
//javac -cp C:\Users\gregd\Downloads\pdfbox-app-3.0.0-alpha3.jar pdf_printer.java
//javac -cp C:\Users\gregd\eclipse-workspace\pdf_printer\jar_files\* pdf_printer.java

//javac -cp C:\Users\gregd\eclipse-workspace\pdf_printer\jar_files\pdfbox-app-3.0.0-alpha3.jar pdf_printer.java

//java  pdf_printer.pdf_printer

//java -classpath C:\Users\gregd\eclipse-workspace\pdf_printer\src\pdfbox-app-3.0.0-alpha3.jar pdf_printer.pdf_printer

//javac -cp  "C:\Users\gregd\eclipse-workspace\pdf_printer\jar_files\debugger-app-3.0.0-alpha3.jar;fontbox-3.0.0-alpha3.jar;pdfbox-3.0.0-alpha3.jar;pdfbox-app-3.0.0-alpha3.jar;pdfbox-debugger-3.0.0-alpha3.jar;pdfbox-tools-3.0.0-alpha3.jar;preflight-3.0.0-alpha3;preflight-app-3.0.0-alpha3.jar;xmpbox-3.0.0-alpha3.jar" pdf_printer.java

//java -cp C:\Users\gregd\eclipse-workspace\pdf_printer\src  pdf_printer.pdf_printer
//java -cp C:\Users\gregd\eclipse-workspace\pdf_printer\src  pdf_printer.pdf_printer
//java -cp C:\Users\gregd\eclipse-workspace\pdf_printer\bin  pdf_printer.pdf_printer
//java -cp pdfbox-app-3.0.0-alpha3.jar pdf_printer.pdf_printer


//javac -cp C:\Users\gregd\eclipse-workspace\pdf_printer\debugger-app-3.0.0-alpha3.jar C:\Users\gregd\eclipse-workspace\pdf_printer\src\pdf_printer\pdf_printer.java
//java -classpath C:\Users\gregd\eclipse-workspace\pdf_printer\src\pdfbox-app-3.0.0-alpha3.jar pdf_printer.pdf_printer


//java -jar C:\Users\gregd\eclipse-workspace\pdf_printer\dist\pdf_printer.jar

public class pdf_printer implements ActionListener {
	
	static String pdf_file;
	static String pdf_path;
	static String pdf_namefile;
	static boolean open;
	
	Thread main = Thread.currentThread();
    	
    public void actionPerformed(ActionEvent ae) {
   
	    JFileChooser fileChooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf");
	    fileChooser.setFileFilter(filter);
	    
	    int returnValue;
	    
	    if (open) {
	    	returnValue = fileChooser.showOpenDialog(null);
	    }
	    else {
	    	returnValue = fileChooser.showSaveDialog(null);
	    }
	    
	    
	    if (returnValue == JFileChooser.APPROVE_OPTION) {
	    	
	    	File selectedFile = fileChooser.getSelectedFile();
			pdf_file = selectedFile.getPath();
			pdf_path = selectedFile.getParent();
			pdf_namefile = selectedFile.getName();
			System.out.println("Full Path with file: " + pdf_file);
			System.out.println("Path of the file: " + pdf_path);
			System.out.println("File Name: " + pdf_namefile);
	    }
    }
      
    public void openJFrame(String title) {

	    JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);
	    JFrame frame = new JFrame("Press the button Open/Save");
	    frame.setPreferredSize(new Dimension(300, 200));
		pdf_file = null;
		pdf_path = null;
		pdf_namefile = null;
		
	    frame.setLayout(new GridBagLayout());
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    JButton button = new JButton(title);
	    button.setPreferredSize(new Dimension(100, 50));
	    button.addActionListener(this);

	    
	    frame.add(button, new GridBagConstraints());
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);


	    synchronized(main) {
	    	while (true) {
	    		System.out.print("");
	    		if (pdf_file != null) {
	    			frame.dispose();
	    			break;
	    		}
	    	}
	    }
    }

	public static String[] removeElement(String[] array, String element) {
	    
		if (array.length > 0) {
			
	        int index = -1;
	        
	        for (int i = 0; i < array.length; i++) {
	        	
	            if (array[i].equals(element)) {
	                index = i;
	                break;
	            }
	        }
	        if (index >= 0) {
	        	
	            String[] copy = (String[]) Array.newInstance(array.getClass().getComponentType(), array.length - 1);
	            
	            if (copy.length > 0) {
	                System.arraycopy(array, 0, copy, 0, index);
	                System.arraycopy(array, index + 1, copy, index, copy.length - index);
	            }
	            
	            return copy;
	        }
	    }
		
	    return array;    
	}
	
	/*public static void checkAnswer(String a) {
		
		if (a.equals("Y") ||a.equals("N") || a.equals("y") || a.equals("n")) {
			if (a.equals("N") || a.equals("n")) 
				break;
			else
				continue;
		}
		else {
			System.out.println("Μη αποδεκτή απάντηση!! Ξαναπροσπαθήστε...");
			continue;
		}
	}*/
	
	public static String[] addElement(int n, String arr[], String x) {
		int i;
   
        // create a new array of size n+1
        String newarr[] = new String[n + 1];
   
        // insert the elements from the old array into the new array insert all elements till n then insert x at n+1
        for (i = 0; i < n; i++)
           newarr[i] = arr[i];
   
        newarr[n] = x;
   
        return newarr;
	}
	
    private static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9.]+");
    }
	 
	public static void main(String[] args) throws IOException, PrinterException, InterruptedException {
		
		//String charset1 = System.out.charset().displayName();
		
		
		/*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("buffer greek test: ");
        try{
            String test = br.readLine(); //We read from user's input
           
            System.out.println("Test is: " + test); //If all was right, we print this
        }
        catch(Exception e){
            System.out.println("Write an integer number"); //This is what user will see if he/she write other thing that is not an integer
        }*/
		
		System.out.println("Hello GitHub!!");
		
		//Scanner scan = new Scanner(System.in, "UTF-8");
		Scanner scan = new Scanner(System.in);
		//String test = scan.nextLine();
		//System.out.println("This is a test of Scan: " + test);
		
		System.out.println("MΕΝΟΥ ΕΚΤΥΠΩΣΗΣ ΕΠΙΛΕΓΜΕΝΩΝ ΣΕΛΙΔΩΝ ΕΝΟΣ PDF");
		System.out.println("=============================================");
		System.out.println("1. Εύρεση σελίδων των ΙΔΟΧ Υπαλλήλων.");
		System.out.println("2. Εύρεση σελίδων των Μόνιμων Υπαλλήλων.");
		System.out.println("3. Ορισμός λέξεων κλειδιά για την επιλογή σελίδων προς εκτύπωση.");
		System.out.println("4. Τερματισμός.");
		
		

		String option;
		boolean multiple_key;
	
		while (true) {
			System.out.print("\nΕπιλέξτε ενέργεια που επιθυμείτε (1-4): ");	
			option = scan.nextLine();
			
			if (isNumeric(option)) {
				if (Integer.parseInt(option)<1 || Integer.parseInt(option)>4) {
					System.out.println("Λάθος επιλογή! Παρακαλώ επιλέξτε ένα νούμερο απο το 1-4 του μενού!");
					continue;
				}
				else {
					break;
				}
			}
			
			System.out.println("Λάθος επιλογή! Παρακαλώ επιλέξτε ένα νούμερο απο το 1-4 του μενού!");
			
		}
		
		String multi_answer;
			
		String IDOX[] = 
		{
			"ΑΡΓΥΡΕΛΛΗ ΑΝΤΩΝΙΑ",
			"ΓΚΥΡΤΗ ΕΙΡΗΝΗ",
			"ΓΟΥΡΟΥΝΑ ΕΛΕΝΗ",
			"ΔΗΜΟΠΟΥΛΟΣ ΓΡΗΓΟΡΙΟΣ",
			"ΔΗΜΟΡΑΓΚΑ ΧΡΙΣΤΙΝΑ",
			"ΔΡΟΣΙΝΟΥ ΑΝΝΑ",
			"ΚΑΡΥΠΙΔΗΣ ΜΕΝΕΛΑΟΣ",
			"ΚΟΝΤΟΓΙΑΝΝΗΣ ΔΗΜΗΤΡΙΟΣ",
			"ΚΟΥΚΟΥΦΙΚΗ ΣΤΑΥΡΟΥΛΑ",
			"ΚΟΥΡΝΙΑΝΟΥ ΑΡΓΥΡΩ",
			"ΜΑΝΩΛΑΚΟΥ ΣΤΑΥΡΟΥΛΑ",
			"ΜΕΛΕΤΗΣ ΧΡΗΣΤΟΣ",
			"ΜΠΑΚΟΥ ΚΑΛΛΙΟΠΗ",
			"ΜΠΑΡΕΤΑ ΜΑΡΙΑ",
			"ΜΥΛΩΝΑ ΜΑΡΙΛΕΝΑ",
			"ΝΤΑΜΑΔΑΚΗ ΒΑΣΙΛΕΙΑ",
			"ΠΑΠΑΓΕΩΡΓΙΟΥ ΧΑΙΔΩ",
			"ΠΑΠΑΣ ΚΩΝΣΤΑΝΤΙΝΟΣ",
			"ΠΕΛΕΚΑΣΗΣ ΑΝΤΩΝΙΟΣ",
			"ΠΙΣΙΝΙΔΗ ΠΑΡΑΣΚΕΥΗ",
			"ΣΑΜΑΡΤΖΙΔΟΥ ΣΟΦΙΑ",
			"ΣΙΩΚΟΥ ΟΥΡΑΝΙΑ",
			"ΣΚΑΜΠΑΒΙΡΙΑ ΕΙΡΗΝΗ",
			"ΣΜΕΡΟΥ ΚΑΝΕΛΛΑ",
			"ΣΤΑΥΡΟΥ ΑΙΚΑΤΕΡΙΝΗ",
			"ΣΩΤΗΡΟΠΟΥΛΟΥ ΚΩΝΣΤΑΝΤΙΝΑ"
		};
		
		String MONMOI[] = 
		{
			"ΑΘΑΝΑΣΙΟΥ ΕΛΕΝΗ",
			"ΑΝΤΩΝΙΟΥ ΓΕΩΡΓΙΑ ΙΦΙΓΕΝΕΙΑ",
			"ΑΡΒΑΝΙΤΗ ΑΓΓΕΛΙΚΗ ΓΡΑΜΜΑΤΙΚΗ",
			"ΓΚΡΟΖΑ ΕΛΕΝΗ",
			"ΔΗΜΗΤΡΑΚΟΠΟΥΛΟΥ ΜΑΡΙΝΑ",
			"ΖΗΚΑ ΑΝΑΣΤΑΣΙΑ",  
			"ΖΩΤΑΛΗ ΠΑΡΑΣΚΕΥΗ",  
			"ΚΟΥΣΟΥΜΒΡΗ ΑΙΚΑΤΕΡΙΝΗ",  
			"ΛΑΙΟΥ ΕΥΑΓΓΕΛΙΑ", 
			"ΜΠΑΛΚΑΜΟΣ ΚΩΝΣΤΑΝΤΙΝΟΣ",
			"ΠΑΠΑΓΕΩΡΓΙΟΥ ΓΕΩΡΓΙΟΣ",  
			"ΡΕΚΚΑΣ ΙΩΑΝΝΗΣ",
			"ΣΑΒΟΥΙΔΑΚΗ ΧΡΥΣΑΝΘΗ",  
			//"ΣΚΟΥΡΑ ΕΛΕΝΗ",    <<ΔΕΝ ΔΙΚΑΙΟΥΤΑΙ>>
			"ΣΟΥΡΕΛΗΣ ΣΠΥΡΙΔΩΝ",
			"ΣΠΑΗΣ ΒΑΣΙΛΕΙΟΣ",  
			"ΣΤΑΘΑΚΟΥ ΣΟΦΙΑ", 
			"ΣΤΑΜΑΤΟΠΟΥΛΟΥ ΑΛΕΞΑΝΔΡΑ",
			"ΤΖΕΡΕΜΕ ΓΕΩΡΓΙΑ",
			//"ΤΡΙΚΑΛΙΩΤΗ ΑΝΝΑ", <<ΑΠΟΧΩΡΗΣΕ>>
			"ΤΣΙΚΕΡΔΗ ΑΝΔΡΙΑΝΗ",
			"ΤΣΙΛΙΓΙΑΝΝΗΣ ΧΡΗΣΤΟΣ",
			"ΦΡΑΔΕΛΟΥ ΔΗΜΗΤΡΑ"
		};
		
		String key;
		String keywords[] = new String[0];

		switch (Integer.parseInt(option)) {
			case 1:
				keywords = IDOX;
				break;
			case 2:
				keywords = MONMOI;
				break;
			case 3:		
				
				System.out.print("Δώστε μια συμβολοσειρά που θέλετε να αναζητήσετε: ");
				key = scan.nextLine();
				keywords = addElement(keywords.length, keywords, key);
					
				while (true) {			
					
					System.out.print("Θέλετε να αναζητήσετε κι άλλη συμβολοσειρά; (Y/N): ");
					multi_answer = scan.nextLine();
					if (multi_answer.equals("Y") || multi_answer.equals("N") || multi_answer.equals("y") || multi_answer.equals("n")) {
						if (multi_answer.equals("N") || multi_answer.equals("n")) {
							break;
						}
						else {
							System.out.print("Δώστε μια συμβολοσειρά που θέλετε να αναζητήσετε: ");
							key = scan.nextLine();
							keywords = addElement(keywords.length, keywords, key);
							continue; 
						}
					}
					else {
						System.out.println("Μη αποδεκτή απάντηση!! Ξαναπροσπαθήστε...");
						continue;
					}
				}

				break;
			case 4:
				scan.close();
				return;
			default:
				scan.close();
				return;		
		}

		
		System.out.println("length: " + keywords.length);
		for(int i=0;i<keywords.length;i++) {
			System.out.println(i + " : " + keywords[i]);
		}		        
				
		while (true) {
			
			System.out.print("Θέλετε να ψάξετε πολλαπλές σελίδες στο pdf με τις τρέχουσες λέξεις κλειδιά; (Y/N): ");
			multi_answer = scan.nextLine();
			
			if (multi_answer.equals("Y") || multi_answer.equals("N") || multi_answer.equals("y") || multi_answer.equals("n")) {
				if (multi_answer.equals("Y") || multi_answer.equals("y")) 
					multiple_key = true;
				else 
					multiple_key = false;
				break;
			}
			else {
				System.out.println("Μη αποδεκτή απάντηση!! Ξαναπροσπαθήστε...");
				continue;
			}
			
		} 
		
		pdf_printer p1 = new pdf_printer();
		open = true;
		p1.openJFrame("Open a File");
		
		int pageCount;
		
		try {
			
			File fileName = new File(pdf_file); 
			PDDocument document = Loader.loadPDF(fileName);
			
			document.getPages();

			if (!document.isEncrypted()) {
				
				pageCount = document.getNumberOfPages();
				System.out.println(pdf_file + " page count: " + pageCount);
				
				PDFTextStripper stripper = new PDFTextStripper();
				String text;
				int current_page = 1;
				
				
				for (int i = 0;i<pageCount;i++) {
					
					stripper.setStartPage(current_page);
					stripper.setEndPage(current_page);
					text = stripper.getText(document);
					
					if (keywords.length==0) {
						document.getPages().remove(current_page-1);
						continue;
					}
					
					for(int j=0;j<keywords.length;j++) {   //ΨΑΧΝΕΙ ΣΤΗΝ CURRENT_PAGE ΝΑ ΒΡΕΙ ΑΝ ΥΠΑΡΧΕΙ ΚΑΠΟΙΟ KEYWORD
						
						if (text.contains(keywords[j])) {
							
							System.out.println("This name was found: " + keywords[j]);
							current_page++;
							
							if (multiple_key == true) {
								continue;
							}
							else {
								keywords = removeElement(keywords, keywords[j]);  //ΑΦΑΙΡΕΙ ΤΟ KEYWORD ΑΠΤΟΝ ΠΙΝΑΚΑ ΚΑΙ ΠΡΟΧΩΡΑΕΙ ΣΤΗΝ ΕΠΟΜΕΝΗ ΣΕΛΙΔΑ
								break;
							}
						}
						else if (j==keywords.length-1) {
							document.getPages().remove(current_page-1);   //ΑΦΑΙΡΕΙ ΤΗΝ ΣΕΛΙΔΑ ΑΝ ΔΕΝ ΒΡΕΙ ΚΑΝΕΝΑ KEYWORD
						}
					}
			    }
				
				open = false;
				
				if (document.getNumberOfPages()==0) {
					System.out.println("Δεν βρέθηκε καμία σελίδα με τις λέξεις κλειδιά που ορίσατε!!!\nΤο πρόγραμμα τερματίζει...");
					return;
				}
				
				p1.openJFrame("Save a File");				
				document.save(pdf_file + ".pdf");
				System.out.println(pdf_file + ".pdf");
	
				while (true) {
					System.out.print("Θέλετε να εκτυπώσετε το έγγραφο σας; (Y/N): ");
					multi_answer = scan.nextLine();
					
					if (multi_answer.equals("Y") || multi_answer.equals("N") || multi_answer.equals("y") || multi_answer.equals("n")) {
						
						if (multi_answer.equals("Y") || multi_answer.equals("y")) {
							//PRINT PROCESS
							Paper paperA4 = new Paper();
							paperA4.setSize(595, 842); // A4 dimensions in font points
							paperA4.setImageableArea(0, 0, paperA4.getWidth(), paperA4.getHeight());				
							
							PageFormat pgFormat = new PageFormat();
							pgFormat.setOrientation(PageFormat.LANDSCAPE);
							pgFormat.setPaper(paperA4);
							
							PDFPrintable printable = new PDFPrintable(document, Scaling.SHRINK_TO_FIT);
						
							PrinterJob job = PrinterJob.getPrinterJob();
							job.setPrintable(printable, pgFormat);
							job.print();

						}

						break;
						
					}
					else {
						System.out.println("Μη αποδεκτή απάντηση!! Ξαναπροσπαθήστε...");
						continue;
					}
				}
			}
			
			document.close();
		
		}
		catch (IOException io_ex) {
			System.out.println(io_ex.toString());
			System.out.println("Could not find file " + pdf_file);
		}
		catch (PrinterException pr_ex) {
			System.out.println(pr_ex.toString());
			System.out.println("Can not print " + pdf_file);
		}
		
		System.out.println("Το πρόγραμμα τερματίζει...");
		scan.close();
	}
}
