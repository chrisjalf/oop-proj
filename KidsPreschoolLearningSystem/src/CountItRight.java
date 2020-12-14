import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class CountItRight extends Module implements Counting, ActionListener{
    private int plusMinus, correctTotal = 0, questionsTotal = 15, questionCount = 0;
    private int [] firstNumber  = new int [questionsTotal];
    private int [] secondNumber  = new int [questionsTotal];
    private int [] questionsAnswer = new int [questionsTotal];
    private String [] sign = new String [questionsTotal];
    private boolean started = false, stopped = false;
    
    public JFrame f;
    private JLabel firstNumberLabel, signLabel, secondNumberLabel, equalSign, questionNumber, timerLabel;
    private JButton choice1, choice2, choice3, choice4, start, stop, next;
    
    public CountItRight(){
        for(int i = 0; i < questionsTotal; i++){
            int temp = 0;
            firstNumber[i] = (int) Math.floor(Math.random() * 11);
            secondNumber[i] = (int) Math.floor(Math.random() * 11);
            plusMinus = (int) Math.floor(Math.random() * 2);
            
            if(plusMinus == 1){ // if plusMinus = 1 then addition operation
                sign[i] = "+";               
                questionsAnswer[i] = firstNumber[i] + secondNumber[i];
            }
            else{               // else minus operation
                sign[i] = "-";
                if(firstNumber[i] < secondNumber[i]){
                    temp = secondNumber[i];
                    secondNumber[i] = firstNumber[i];
                    firstNumber[i] = temp;
                }
                questionsAnswer[i] = firstNumber[i] - secondNumber[i];
            }
        }      
        
        f = new JFrame();
        
        firstNumberLabel = new JLabel();
        signLabel = new JLabel();
        secondNumberLabel = new JLabel();
        equalSign = new JLabel();
        questionNumber = new JLabel();
        
        choice1 = new JButton();
        choice2 = new JButton();
        choice4 = new JButton();
        choice3 = new JButton();
        
        timerLabel = new JLabel();
        
        start = new JButton();
        stop = new JButton();
        next = new JButton();
        
        f.setTitle("Count-It-Right!");
        f.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        f.setResizable(false);

        firstNumberLabel.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        firstNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstNumberLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        signLabel.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        signLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        signLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        secondNumberLabel.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        secondNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        secondNumberLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        equalSign.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        equalSign.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        equalSign.setText("=");
        equalSign.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        questionNumber.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        questionNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        questionNumber.setText("Q#");
        questionNumber.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        choice1.setBackground(Color.pink);
        choice1.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        choice1.setFocusPainted(false);

        choice2.setBackground(Color.magenta);
        choice2.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        choice2.setFocusPainted(false);

        choice4.setBackground(Color.orange);
        choice4.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        choice4.setFocusPainted(false);

        choice3.setBackground(Color.blue);
        choice3.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        choice3.setForeground(java.awt.Color.white);
        choice3.setFocusPainted(false);

        next.setBackground(java.awt.Color.cyan);
        next.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        next.setText("Next");
        next.setVisible(false);
        next.setFocusPainted(false);
        next.addActionListener(this);

        timerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerLabel.setText("00:00:00");

        stop.setBackground(java.awt.Color.white);
        stop.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        stop.setText("Stop");
        stop.setFocusPainted(false);
        stop.addActionListener(this);

        start.setBackground(java.awt.Color.white);
        start.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        start.setText("Start");
        start.setFocusPainted(false);
        start.addActionListener(this);

        // layout settings
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(f.getContentPane());
        f.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 42, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(choice2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(choice3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(choice4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(136, 136, 136))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(firstNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(signLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(secondNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(equalSign, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(questionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(stop)
                        .addGap(75, 75, 75)
                        .addComponent(timerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(124, 124, 124)
                    .addComponent(start)
                    .addContainerGap(293, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(questionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(signLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(secondNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equalSign, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(next)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(timerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(choice3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(choice2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(choice4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(stop)
                        .addContainerGap(33, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(442, Short.MAX_VALUE)
                    .addComponent(start)
                    .addGap(31, 31, 31)))
        );
        
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick++;
                clockTime = ((double)tick) / 10.0;
                seconds = (int)clockTime;
                minutes = seconds / 60;
                hours = minutes / 60;
                String stringSeconds = "00", stringMinutes = "00", stringHours = "00";

                if(clockTime == (double)(int)clockTime){
                    if(seconds >= 60){
                        seconds %= 60;
                    }

                    if(minutes >= 60){
                        minutes %= 60;
                    }                        

                    if(seconds < 10){
                        stringSeconds = "0" + Integer.toString(seconds); 
                    }
                    else{
                        stringSeconds = Integer.toString(seconds); 
                    }

                    if(minutes < 10){
                        stringMinutes = "0" + Integer.toString(minutes); 
                    }
                    else{
                        stringMinutes = Integer.toString(minutes); 
                    }

                    if(hours < 10){
                        stringHours = "0" + Integer.toString(hours); 
                    }
                    else{
                        stringHours = Integer.toString(hours); 
                    }
                    
                    timeTaken = stringHours + ":" + stringMinutes + ":" + stringSeconds;
                    timerLabel.setText(timeTaken);
                }
            }
        });  
        f.pack();
        f.setVisible(true);       
    }  
    
    public void actionPerformed(ActionEvent e){
        int [] choices = {};
        if(!completed){
            if(questionCount < questionsTotal){
                if(e.getSource() == start && !started && !stopped){
                    started = true;

                    start.setBackground(Color.green);

                    addChoiceActionListener();

                    questionNumber.setText("Q" + Integer.toString(questionCount + 1));
                    firstNumberLabel.setText(Integer.toString(firstNumber[questionCount]));
                    secondNumberLabel.setText(Integer.toString(secondNumber[questionCount]));
                    signLabel.setText(sign[questionCount]);

                    choices = choiceRandomiser();

                    choice1.setText(Integer.toString(choices[0]));
                    choice2.setText(Integer.toString(choices[1]));
                    choice3.setText(Integer.toString(choices[2]));
                    choice4.setText(Integer.toString(choices[3]));

                    timer.start();
                }

                if(e.getSource() == start && stopped){
                    stopped = false;
                    start.setBackground(Color.green);
                    stop.setBackground(Color.white);
                    timer.start();
                }

                if(e.getSource() == stop && started && !stopped){
                    timer.stop();
                    stopped = true;
                    stop.setBackground(Color.red);
                    start.setBackground(Color.white);
                }

                if(e.getSource() == next && started && !stopped){
                    next.setVisible(false);

                    setDefaultChoiceColor();
                    addChoiceActionListener();

                    questionNumber.setText("Q" + Integer.toString(questionCount + 1));
                    firstNumberLabel.setText(Integer.toString(firstNumber[questionCount]));
                    secondNumberLabel.setText(Integer.toString(secondNumber[questionCount]));
                    signLabel.setText(sign[questionCount]);

                    choices = choiceRandomiser();

                    choice1.setText(Integer.toString(choices[0]));
                    choice2.setText(Integer.toString(choices[1]));
                    choice3.setText(Integer.toString(choices[2]));
                    choice4.setText(Integer.toString(choices[3]));
                }

                if(e.getSource() == choice1 && started && !stopped){
                    if(Integer.parseInt(choice1.getText()) == questionsAnswer[questionCount]){
                        correctTotal++;
                    }

                    next.setVisible(true);
                    choice1.setBackground(Color.lightGray);

                    questionCount++;

                    removeChoiceActionListener();
                }

                if(e.getSource() == choice2 && started && !stopped){
                    if(Integer.parseInt(choice2.getText()) == questionsAnswer[questionCount]){
                        correctTotal++;
                    } 

                    next.setVisible(true);
                    choice2.setBackground(Color.lightGray);

                    questionCount++;               

                    removeChoiceActionListener();
                }

                if(e.getSource() == choice3 && started && !stopped){
                    if(Integer.parseInt(choice3.getText()) == questionsAnswer[questionCount]){
                        correctTotal++;
                    }  

                    next.setVisible(true);
                    choice3.setBackground(Color.lightGray);

                    questionCount++;

                    removeChoiceActionListener();
                }

                if(e.getSource() == choice4 && started && !stopped){
                    if(Integer.parseInt(choice4.getText()) == questionsAnswer[questionCount]){
                        correctTotal++;
                    }    

                    next.setVisible(true);
                    choice4.setBackground(Color.lightGray);

                    questionCount++;

                    removeChoiceActionListener();                
                }            
            }

            if(questionCount == questionsTotal){
                next.setText("Finish");

                if(e.getSource() == next){
                    timer.stop();
                    start.setBackground(Color.white);
                    removeChoiceActionListener();
                    start.removeActionListener(this);
                    stop.removeActionListener(this);
                    next.removeActionListener(this);
                    completed = true;

                    JOptionPane.showMessageDialog(null, "You have correctly answered " + correctTotal + " questions. \nTime taken: " + timerLabel.getText(), "Clear", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
    
    public void addChoiceActionListener(){
        choice1.addActionListener(this);
        choice2.addActionListener(this);
        choice3.addActionListener(this);
        choice4.addActionListener(this);
    }
    
    public void removeChoiceActionListener(){
        choice1.removeActionListener(this);
        choice2.removeActionListener(this);
        choice3.removeActionListener(this);
        choice4.removeActionListener(this);
    }    
    
    public void setDefaultChoiceColor(){
        choice1.setBackground(Color.pink);
        choice2.setBackground(Color.magenta);
        choice3.setBackground(Color.blue);
        choice4.setBackground(Color.orange);
    }
    
    public int [] choiceRandomiser(){
        int [] choices = new int [4]; 
        int correctAns = (int) Math.floor(Math.random() * choices.length);
        int min = 0, max = 0, randomiser = 0;
        
        for(int i = 0; i < choices.length; i++){    // to get min and max so that choices can be randomised
            if(sign[i] == "+"){
                if(firstNumber[i] < secondNumber[i]){
                    min = firstNumber[i];
                    max = firstNumber[i] + secondNumber[i];
                }
                else if(firstNumber[i] > secondNumber[i]){
                    min = secondNumber[i];
                    max = firstNumber[i] + secondNumber[i];
                }
                
                randomiser = (int) (Math.random() * ((max - min) + 1)) + min;
                choices[i] = randomiser;
            }
            else if(sign[i] == "-"){
                if(firstNumber[i] < secondNumber[i]){
                    min = firstNumber[i];
                    max = firstNumber[i] + secondNumber[i];
                }
                else if(firstNumber[i] > secondNumber[i]){
                    min = secondNumber[i];
                    max = firstNumber[i] + secondNumber[i];
                }
                
                randomiser = (int) (Math.random() * ((max - min) + 1)) + min;
                choices[i] = randomiser;
            }
            
            while(choices[0] == questionsAnswer[questionCount]){    // if first choice is correct answer then change value until it is not same anymore
                randomiser = (int) (Math.random() * ((max - min) + 1)) + min;
                choices[i] = randomiser;
            }
            
            if(i > 0){  // reset value for choice to ensure no same value 
                for(int j = 0; j < i; j++){
                    while(choices[i] == choices[j] || choices[i] == questionsAnswer[questionCount]){
                        randomiser = (int) (Math.random() * ((max - min) + 1)) + min;
                        choices[i] = randomiser;
                    }
                }
            }
        }
                
        choices[correctAns] = questionsAnswer[questionCount];
        
        return choices;
    }
    
    public String getScore(){
        return Integer.toString(correctTotal) + "/" + Integer.toString(questionsTotal);
    }
    
    public static void main(String args[]) {
    }                  
}
