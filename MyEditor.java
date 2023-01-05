import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.filechooser.FileSystemView;

public class MyEditor implements ActionListener {
     Scanner input = new Scanner(System.in);
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit,close;
    JMenuItem newPage,open,save,cut,copy,paste,selectall,quit;
    JTextArea textArea;
     File f;

     FileReader file1;

    MyEditor(){
        frame = new JFrame("untitled");
        menuBar = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        close = new JMenu("Close");
        newPage = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectall = new JMenuItem("select all");
        quit = new JMenuItem("quit");
        textArea = new JTextArea();

        frame.setBounds(5 ,5,500,500);
        frame.setLayout(null);
        menuBar.setBounds(0,0,500,40);
        frame.add(menuBar);
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(close);
        file.add(newPage);
        file.add(open);
        file.add(save);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        close.add(quit);
        textArea = new JTextArea("welcome to my editor!");
        textArea.setBounds(0,40,500,500);
        frame.add(textArea);


        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        newPage.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        quit.addActionListener(this);

    }

     @Override
     public void actionPerformed(ActionEvent e) {
         if (e.getSource() == cut)
             textArea.cut();
         if (e.getSource() == copy)
             textArea.copy();
         if (e.getSource() == paste)
             textArea.paste();
         if (e.getSource() == selectall)
             textArea.selectAll();
         if (e.getSource() == newPage)
             textArea.setText("new Page");
         if (e.getSource() == quit)
             frame.setVisible(false);
         if (e.getSource() == open) {
         JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
             jfc.setDialogTitle("Choose destination.");
             int value = jfc.showOpenDialog(null);
             if (value == JFileChooser.APPROVE_OPTION) {
                 File f = new File(jfc.getSelectedFile().getAbsolutePath());
                 try{
                     FileReader fr = new FileReader(f);
                     Scanner scan = new Scanner(fr);
                     String line1 = "";
                     while (scan.hasNext()){
                         String tempLine = scan.nextLine()+"\n";
                         line1 += tempLine;
                     }
                     textArea.setText(line1);
                 } catch (FileNotFoundException ex) {
                     throw new RuntimeException(ex);
                 }

             }

         }
         if(e.getSource()==save){
         JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//             jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
             int value = jfc.showOpenDialog(null);
             try {
                 File f= new File(jfc.getSelectedFile().getAbsolutePath());
                 FileWriter writer = new FileWriter(f);
                 writer.write(textArea.getText());
                 writer.close();
             } catch (IOException ex) {
                 throw new RuntimeException(ex);
             }
         }

     }

    public static void main(String[] args) {
        new MyEditor();
    }

 }
