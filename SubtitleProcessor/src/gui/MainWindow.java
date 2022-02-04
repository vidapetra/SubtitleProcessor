package gui;

import alg.Subtitle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author VidaPetra
 */
public class MainWindow extends JFrame {


    private JButton btnLongestWord;
    private JButton btnReadFile;
    private JButton btnSaveShiftedFile;
    private JButton btnSearch;
    private JLabel lbError;
    private JLabel lbExample;
    private JLabel lbFileName;
    private JLabel lbLongestWord;
    private JLabel lbQuantity;
    private JLabel lbReadingOk;
    private JLabel lbSaved;
    private JLabel lbSec;
    private JLabel lbShift;
    private JLabel lbTime;
    private JLabel lbWord;
    private JTextField tfShift;
    private JTextField tfWord;
    
    Subtitle subtitle;
    boolean isFileOk;
    final JFileChooser fc;
    
    public MainWindow() {
        initComponents();
        lbError.setVisible(false);
        lbReadingOk.setVisible(false);
        lbSaved.setVisible(false);
        isFileOk = false;
        fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    }

    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        btnReadFile = new JButton();
        btnLongestWord = new JButton();
        lbLongestWord = new JLabel();
        tfWord = new JTextField();
        lbWord = new JLabel();
        lbQuantity = new JLabel();
        lbTime = new JLabel();
        btnSaveShiftedFile = new JButton();
        lbShift = new JLabel();
        tfShift = new JTextField();
        btnSearch = new JButton();
        lbError = new JLabel();
        lbFileName = new JLabel();
        lbReadingOk = new JLabel();
        lbSaved = new JLabel();
        lbExample = new JLabel();
        lbSec = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btnReadFile.setText("Fajl beolvasasa...");
        btnReadFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReadFileMouseClicked(evt);
            }
        });

        btnLongestWord.setText("Leghosszabb ideig lathato szo");
        btnLongestWord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLongestWordMouseClicked(evt);
            }
        });

        lbWord.setText("A keresett szo: ");

        btnSaveShiftedFile.setText("Mentes");
        btnSaveShiftedFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveShiftedFileMouseClicked(evt);
            }
        });

        lbShift.setText("Eltolas merteke:");

        btnSearch.setText("Kereses");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        lbError.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbError.setForeground(new java.awt.Color(255, 0, 0));
        lbError.setHorizontalAlignment(SwingConstants.CENTER);
        lbError.setText("Nem megfelelo szot adott meg!");

        lbReadingOk.setForeground(new java.awt.Color(0, 153, 0));
        lbReadingOk.setText("Fajl beolvasva");

        lbSaved.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbSaved.setForeground(new java.awt.Color(0, 153, 0));
        lbSaved.setHorizontalAlignment(SwingConstants.CENTER);
        lbSaved.setText("Fajl mentve");

        lbExample.setText("pl.: 30,4");

        lbSec.setText("masodperc");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(lbSaved, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lbExample)
                            .addComponent(lbError, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLongestWord)
                        .addGap(112, 112, 112)
                        .addComponent(lbLongestWord)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbShift)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSaveShiftedFile))
                            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(lbWord)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lbReadingOk)))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(tfShift, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbSec)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(lbTime, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbQuantity)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lbFileName)
                                                    .addComponent(tfWord, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                                                .addComponent(btnSearch)))))))
                        .addGap(105, 105, 105))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnReadFile)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReadFile)
                    .addComponent(lbFileName))
                .addGap(8, 8, 8)
                .addComponent(lbReadingOk)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLongestWord)
                    .addComponent(lbLongestWord))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lbWord)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(tfWord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbQuantity)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTime)))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveShiftedFile)
                    .addComponent(lbShift)
                    .addComponent(lbSec)
                    .addComponent(tfShift, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbExample)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(lbError)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSaved)
                .addGap(33, 33, 33))
        );

        pack();
    }
    
    private void btnReadFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReadFileMouseClicked
        try {
            int returnValue = fc.showOpenDialog(null);
		
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());

                if(selectedFile.getAbsolutePath().substring(selectedFile.getAbsolutePath().length() - 4, selectedFile.getAbsolutePath().length()).equals(".srt")){
                    subtitle = new Subtitle(selectedFile.getAbsolutePath());
                    lbFileName.setText(selectedFile.getName());
                    lbReadingOk.setVisible(true);
                    lbError.setVisible(false);
                    isFileOk = true;
                } else {
                    lbError.setText("Nem megfelelo fajlt adott meg!");
                    lbError.setVisible(true);
                    lbReadingOk.setVisible(false);
                    isFileOk = false;
                }
            }
        }
        catch (IOException e){
            lbError.setText("Nem talalhato a megadott fajl!");
            lbError.setVisible(true);
            lbReadingOk.setVisible(false);
            isFileOk = false;
        }
    }
    
    private void btnLongestWordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLongestWordMouseClicked
        if (isFileOk){
            lbLongestWord.setText(subtitle.getLongestTimeWord());
        } else {
            lbError.setText("Elobb meg kell adnia a fajlt!");
                lbError.setVisible(true);
        }
    }
    
    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        if(isFileOk){
            if(tfWord.getText().length() < 4){
                lbError.setText("3 karakternél hosszabb szot adjon meg!");
                lbQuantity.setText("");
                lbTime.setText("");
                lbError.setVisible(true);
            } else {
                lbError.setVisible(false);
                DecimalFormat formatter = new DecimalFormat("0.00");
                lbQuantity.setText(subtitle.getWordQuantity(tfWord.getText()) + " alkalommal,");
                lbTime.setText(formatter.format(subtitle.getWordTime(tfWord.getText())) + " masodpercig lathato.");
            }
        } else {
            lbError.setText("Elobb meg kell adnia a fajlt!");
            lbError.setVisible(true);
        }
        
    }
    
    private void btnSaveShiftedFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveShiftedFileMouseClicked
        if (isFileOk && tfShift.getText() != ""){
            try
            {
                NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
                Number number = format.parse(tfShift.getText());
                double d = number.doubleValue();
                if(subtitle.shiftSubtitle(d) == "ok"){
                    lbError.setVisible(false);
                    lbSaved.setVisible(true);
                } else if(subtitle.shiftSubtitle(d) == "minusError"){
                    lbError.setText("Nem megfelelo tartomany");
                    lbError.setVisible(true);
                    lbSaved.setVisible(false);
                }
            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                lbError.setText("Szamot adjon meg!");
                lbError.setVisible(true);
                lbSaved.setVisible(false);
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
                lbError.setText("Elobb meg kell adnia a fajlt!");
                lbSaved.setVisible(false);
                lbError.setVisible(true);
        }
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

}
