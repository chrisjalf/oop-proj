import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class SmartKids{
    private String username;
    private String [] results = new String [3];
    private boolean spCompleted = false, cirCompleted = false;
    
    private JFrame f;
    private JButton slidingPuzzleButton, countItRightButton, enterButton, saveButton;
    private JLabel welcomeMessage, userNameLabel, slidingPuzzleLabel, countItRightLabel;
    private JPanel p1;
    private JTextField userNameTextField;  
    
    SlidePuzzle sp;
    CountItRight cir;
    
    public SmartKids() {
        f = new JFrame();
        p1 = new JPanel();
        welcomeMessage = new JLabel();
        userNameLabel = new JLabel();
        userNameTextField = new JTextField();
        slidingPuzzleButton = new JButton();
        countItRightButton = new JButton();
        enterButton = new JButton();
        saveButton = new JButton();
        slidingPuzzleLabel = new JLabel();
        countItRightLabel = new JLabel();

        f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        f.setTitle("SmartKids");

        p1.setBackground(new java.awt.Color(51, 153, 255));

        welcomeMessage.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        welcomeMessage.setForeground(java.awt.Color.white);
        welcomeMessage.setText("Welcome to SmartKids");

        userNameLabel.setBackground(new java.awt.Color(51, 204, 255));
        userNameLabel.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        userNameLabel.setForeground(java.awt.Color.white);
        userNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userNameLabel.setText("Username: ");
        userNameLabel.setToolTipText("");
        userNameLabel.setOpaque(true);
        
        userNameTextField.setText("");
        userNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        userNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        enterButton.setBackground(java.awt.Color.white);
        enterButton.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        enterButton.setForeground(new java.awt.Color(51, 53, 255));
        enterButton.setText("Enter");
        enterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(p1);
        p1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(welcomeMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userNameTextField)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(enterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(welcomeMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(userNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(enterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );

        slidingPuzzleButton.setBackground(new java.awt.Color(51, 153, 255));
        slidingPuzzleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iconSlidingPuzzle.png"))); // NOI18N
        slidingPuzzleButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        slidingPuzzleButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        slidingPuzzleButton.setFocusable(false);
        slidingPuzzleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        countItRightButton.setBackground(new java.awt.Color(51, 153, 255));
        countItRightButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iconCountItRight.png"))); // NOI18N
        countItRightButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        countItRightButton.setFocusable(false);
        countItRightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        
        saveButton.setBackground(new java.awt.Color(51, 153, 255));
        saveButton.setFocusable(false);
        saveButton.setText("Save results");
        saveButton.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        saveButton.setForeground(java.awt.Color.white);
        saveButton.setVisible(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        slidingPuzzleLabel.setBackground(new java.awt.Color(51, 153, 255));
        slidingPuzzleLabel.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        slidingPuzzleLabel.setForeground(java.awt.Color.white);
        slidingPuzzleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slidingPuzzleLabel.setText("Sliding Puzzle");
        slidingPuzzleLabel.setOpaque(true);

        countItRightLabel.setBackground(new java.awt.Color(51, 153, 255));
        countItRightLabel.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        countItRightLabel.setForeground(java.awt.Color.white);
        countItRightLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        countItRightLabel.setText("Count It Right");
        countItRightLabel.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(f.getContentPane());
        f.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(slidingPuzzleButton, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addComponent(slidingPuzzleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(countItRightButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countItRightLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))    
                .addGap(68, 68, 68))
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(196, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(slidingPuzzleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countItRightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(slidingPuzzleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countItRightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)    
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE) 
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))                      
                .addGap(238, 238, 238))
        );

        f.pack();        
        f.setVisible(true);
        f.setResizable(false);
    }
    
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        if(evt.getSource() == slidingPuzzleButton && !userNameTextField.getText().isEmpty() && !spCompleted){
            f.setVisible(false);
            sp = new SlidePuzzle();
            
            sp.f.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    spCompleted = sp.getCompleted();
                    results[0] = "Username: " + username;
                    results[1] = "[Slide Puzzle]-> Time taken: " + sp.getTimeTaken() + "\n";
                    
                    if(getModulesCompleted()){
                        saveButton.setVisible(true);
                    }                    
                    
                    f.setVisible(true);
                }
            });
        }
        
        if(evt.getSource() == slidingPuzzleButton && userNameTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter your username.", null, JOptionPane.INFORMATION_MESSAGE);
        }
        
        if(evt.getSource() == slidingPuzzleButton && spCompleted){
            JOptionPane.showMessageDialog(null, "You have already completed the module.", null, JOptionPane.INFORMATION_MESSAGE);
        }
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        if(evt.getSource() == countItRightButton && !userNameTextField.getText().isEmpty() && !cirCompleted){
            f.setVisible(false);
            cir = new CountItRight();
            
            cir.f.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    cirCompleted = cir.getCompleted();
                    results[2] = "[Count It Right]-> Time taken: " + cir.getTimeTaken() + "\n-> Score: " + cir.getScore() + "\n";
                    
                    if(getModulesCompleted()){
                        saveButton.setVisible(true);
                    }
                    
                    f.setVisible(true);
                }
            });            
        }   
        
        if(evt.getSource() == countItRightButton && userNameTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter your username.", null, JOptionPane.INFORMATION_MESSAGE);
        }
        
        if(evt.getSource() == countItRightButton && cirCompleted){
            JOptionPane.showMessageDialog(null, "You have already completed the module.", null, JOptionPane.INFORMATION_MESSAGE);
        }
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        if(evt.getSource() == enterButton && !userNameTextField.getText().isEmpty()){
            username = userNameTextField.getText();
            userNameTextField.setEditable(false);
            enterButton.setVisible(false);
        }
        else if(evt.getSource() == enterButton && userNameTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter your username.", null, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        if(evt.getSource() == saveButton){
            JFileChooser chooser = new JFileChooser(); 
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Text files (.txt)", "txt");    
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Save Results");  //  JFileChooser's title when saving public key
            
            int returnVal = chooser.showSaveDialog(null);   
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String fileName = chooser.getSelectedFile().toString();
                
                if(!fileName.substring(fileName.lastIndexOf(".") + 1).equals("txt")){   //  if file does not have .txt extension, then add the extension at the end of file name
                    fileName = fileName + ".txt";
                }
                
                try{
                    PrintWriter writer = new PrintWriter(fileName, "UTF-8"); 
//                    writer.println(jTextArea3.getText());
//                    writer.println(jTextArea4.getText());
                    for(int i = 0; i< results.length; i++){
                        writer.println(results[i]);
                    }
                    writer.close();
                }
                catch(IOException e){
                    JOptionPane.showMessageDialog(null,
                    "Error occurred during file creation",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean getModulesCompleted(){
        if(spCompleted && cirCompleted){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static void main(String args[]) {
        SmartKids sk = new SmartKids();
    }                  
}
