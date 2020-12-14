import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class SlidePuzzle extends Module implements Sliding, ActionListener{
    public JFrame f = new JFrame("Sliding Puzzle");
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();
    private JPanel p4 = new JPanel();
    private JPanel p5 = new JPanel();
    private JLabel l = new JLabel("00:00:00");
    private JButton[] b = new JButton[9];
    private JButton start, shuffle, stop; 
    private JButton image;
    
    private BufferedImage oriImage;
    private BufferedImage [] segImage = new BufferedImage [9];
    private int rows, cols, oriWidth, oriHeight, segWidth, segHeight, lastSegmentedImageIndexMarker, left, right, top, bottom;
    private int [] segmentedImageIndexMarker;   // marker for segmented images
    private boolean shuffled = false, started = false, stopped = false;
   
    public SlidePuzzle(){
        rows = 3;
        cols = 3;
        segmentedImageIndexMarker = new int [rows * cols];
        lastSegmentedImageIndexMarker = rows * cols - 1;
        
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        p1.setMaximumSize(new Dimension(500, 610));
        
        p2.setLayout(new GridLayout(rows, cols, 0, 0));
        p2.setMaximumSize(new Dimension(500, 500));
        
        p3.setLayout(new BorderLayout());
        p3.setMaximumSize(new Dimension(500, 20));
        
        p4.setLayout(new GridLayout(1, 4, 30, 0));
        p4.setMaximumSize(new Dimension(600, 40));
        
        p5.setMaximumSize(new Dimension(500, 20));
        
        image = new JButton("Select Image");
        image.setFocusPainted(false);
        
        shuffle = new JButton("Shuffle");
        shuffle.setFocusPainted(false);
        start = new JButton("Start");
        start.setFocusPainted(false);
        stop = new JButton("Stop");
        stop.setFocusPainted(false);
            
        image.addActionListener(this);
        shuffle.addActionListener(this);
        start.addActionListener(this);
        stop.addActionListener(this);

        p4.add(image);

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
                    l.setText(timeTaken);
                }
            }
        });
        p1.add(p2);
        p1.add(p3);
        p1.add(p4);
        p1.add(p5);

        f.setContentPane(p1);
        f.setSize(500,610);
        f.setResizable(false);
        f.setVisible(true);
    }
    
    public void shuffleTiles(){
        int [] bordersLeft = new int [rows];
        int [] bordersRight = new int [rows];
        int [] positions = {left, right, top, bottom};  // created to be randomly selected to be exchanged with empty tile
        int randPosition, t;
        boolean isLeftMost = false, isRightMost = false;

        left = lastSegmentedImageIndexMarker - 1;
        right = lastSegmentedImageIndexMarker + 1;
        top = lastSegmentedImageIndexMarker - cols; 
        bottom = lastSegmentedImageIndexMarker + cols;

        for(int i = 0; i < bordersLeft.length; i++){  // get index for leftmost side
            bordersLeft[i] = i * cols;
        }

        for(int i = 0; i < bordersRight.length; i++){  // get index for rightmost side
            bordersRight[i] = i * cols + cols - 1;
        }

        for(int i = 0; i < bordersLeft.length; i++){    // check whether empty tile is leftmost
            if(lastSegmentedImageIndexMarker == bordersLeft[i]){
                isLeftMost = true;
                break;
            }
        }

        for(int i = 0; i < bordersRight.length; i++){   // check whether empty tile is rightmost
            if(lastSegmentedImageIndexMarker == bordersRight[i]){
                isRightMost = true;
                break;
            }
        }

        randPosition = (int)(Math.random() * positions.length);
        t = positions[randPosition];

        if(left > -1){
            if(t == left && !isLeftMost){
                exchangeTiles(t, lastSegmentedImageIndexMarker);
                left++;
            }
        }

        if(right < rows * cols){
            if(t == right && !isRightMost){
                exchangeTiles(t, lastSegmentedImageIndexMarker);
                right--;
            }
        }

        if(top > -1){
            if(t == top){
                exchangeTiles(t, lastSegmentedImageIndexMarker);
                top+=cols;
            }
        }

        if(bottom < rows * cols){
            if(t == bottom){
                exchangeTiles(t, lastSegmentedImageIndexMarker);
                bottom-=cols;
            }
        }
    }
    
    public void exchangeTiles(int t1, int t2){
        BufferedImage tempImage;
        int temp = 0;
        
        temp = segmentedImageIndexMarker[t2];
        segmentedImageIndexMarker[t2] = segmentedImageIndexMarker[t1];
        segmentedImageIndexMarker[t1] = temp;
        
        // index of segmented images will be swapped, therefore, there is a need to track the initial last segmented image after numerous swaps
        if(lastSegmentedImageIndexMarker == t1){
            lastSegmentedImageIndexMarker = t2;
        } 
        else if(lastSegmentedImageIndexMarker == t2){
            lastSegmentedImageIndexMarker = t1;
        }
        
        // tempImage is used to ease swapping between segmented images of tiles
        tempImage = segImage[t2];
        segImage[t2] = segImage[t1];
        segImage[t1] = tempImage;
        
        setTiles();
    }
    
    public void setTiles(){
        for(int i = 0; i < segImage.length; i++){               // loop for setting icons of tiles
            try{
                if(i == lastSegmentedImageIndexMarker){
                    b[i].setIcon(null);                         // set tile with last segmented image as empty
                }
                else{
                    b[i].setIcon(new ImageIcon(segImage[i]));   // set tiles using segmented images as icons
                }
            }
            catch(Exception e){}
        }
    }
    
    public void actionPerformed(ActionEvent e){
        int match = 0;
        int [] bordersLeft = new int [rows];
        int [] bordersRight = new int [rows];
        boolean isLeftMost = false, isRightMost = false;
        
        left = lastSegmentedImageIndexMarker - 1;
        right = lastSegmentedImageIndexMarker + 1;
        top = lastSegmentedImageIndexMarker - cols; 
        bottom = lastSegmentedImageIndexMarker + cols;
        
        if(e.getSource() == image){
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Image (PNG)", "png");
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Select a 500x500 image");
             
            int returnVal = chooser.showOpenDialog(null);   
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String fileName = chooser.getSelectedFile().toString();
                
                if(!fileName.substring(fileName.lastIndexOf(".") + 1).equals("png")){   //  if file extensin not png
                    JOptionPane.showMessageDialog(null,
                    "File is not a png type.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                }
                else{
                    try{
                        oriImage = ImageIO.read(new File(fileName));    // read images
                        oriWidth = oriImage.getWidth();     // width of original image
                        oriHeight = oriImage.getHeight();   // height of original image

                        if(oriWidth != 500 & oriHeight != 500){
                            JOptionPane.showMessageDialog(null,
                            "Image dimensions mismatch, make sure a 500x500 image is selected.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE); 
                            oriImage = null;
                        }
                        else{
                            segWidth = oriWidth / cols;     // width of segmented image
                            segHeight = oriHeight / rows;   // height of segmented image

                            int x = 0; // x-coordinate of image
                            int y = 0; // y-coordinate of image

                            for (int i = 0; i < rows; i++) {
                                x = 0;
                                for (int j = 0; j < cols; j++) {
                                    try {
                                        segImage[(i*2)+(i+j)] = oriImage.getSubimage(x, y, segWidth, segHeight);
                                        b[(i*2)+(i+j)] = new JButton("");                                   // button (tiles) creation
                                        b[(i*2)+(i+j)].addActionListener(this);                             // add action listener to tiles
                                        p2.add(b[(i*2)+(i+j)]);                                             // add buttons to container
                                        x += segWidth;                                                      // for getting the coordinate of the next segmented image on the same row
                                    } 
                                    catch (Exception ex) {
                                        System.out.println(ex);
                                    }
                                }
                                y += segHeight; // for getting the coordinate of the next segmented image on the next row
                            }

                            image.setVisible(false);
                            p4.remove(image);

                            p4.add(shuffle);
                            p4.add(start);
                            p4.add(stop);
                            p4.add(l);

                            for(int i = 0; i < segmentedImageIndexMarker.length; i++){
                                segmentedImageIndexMarker[i] = i;
                            } 
                        }                
                    }
                    catch(FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null,
                        "Image not found",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);                
                    } 
                    catch(IOException ex){
                        JOptionPane.showMessageDialog(null,
                        "Error occurred during file read",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    }
                }
            }   
        }
        
        for(int i = 0; i < bordersLeft.length; i++){  // get index for leftmost side
            bordersLeft[i] = i * cols;
        }
        
        for(int i = 0; i < bordersRight.length; i++){  // get index for rightmost side
            bordersRight[i] = i * cols + cols - 1;
        }
        
        for(int i = 0; i < bordersLeft.length; i++){    // check whether empty tile is leftmost
            if(lastSegmentedImageIndexMarker == bordersLeft[i]){
                isLeftMost = true;
                break;
            }
        }
        
        for(int i = 0; i < bordersRight.length; i++){   // check whether empty tile is rightmost
            if(lastSegmentedImageIndexMarker == bordersRight[i]){
                isRightMost = true;
                break;
            }
        }
        
        if(!completed){
            try{
                if(e.getSource() == shuffle && !started && !stopped){   // only allows shuffle if not started and stopped yet
                    shuffled = true;
                    int count = (int)(Math.random() * 100);
                    for(int i = 0; i < count; i++){
                        shuffleTiles();
                    }
                }

                if(e.getSource() == start && shuffled){ // only allows start process once shuffled
                    started = true;
                    stopped = false;
                    start.setBackground(Color.green);
                    stop.setBackground(null);
                    timer.start();
                }

                if(e.getSource() == stop && shuffled && started){   // only allows stop process once shuffled and started
                    stopped = true;
                    started = false;
                    timer.stop();
                    stop.setBackground(Color.red);
                    start.setBackground(null);
                }

                if(left > -1){  // if left side of empty tile is clicked
                    if(e.getSource() == b[left] && !isLeftMost && shuffled && started){
                        exchangeTiles(left, lastSegmentedImageIndexMarker);
                        left++;
                    }
                }

                if(right < rows * cols){ // if right side of empty tile is clicked
                    if(e.getSource() == b[right] && !isRightMost && shuffled && started){
                        exchangeTiles(right, lastSegmentedImageIndexMarker);
                        right--;
                    }
                }

                if(top > -1){   // if top of empty tile is clicked
                    if(e.getSource() == b[top] && shuffled && started){
                        exchangeTiles(top, lastSegmentedImageIndexMarker);
                        top+=cols;
                    }
                }

                if(bottom < rows * cols){    // if bottom of empty tile is clicked
                    if(e.getSource() == b[bottom] && shuffled && started){
                        exchangeTiles(bottom, lastSegmentedImageIndexMarker);
                        bottom-=cols;
                    }
                }
            }
            catch(Exception ex){}
            
            for(int i = 0; i < segmentedImageIndexMarker.length; i++){  // for checking whether all tiles are in proper position
                if(i == segmentedImageIndexMarker[i]){
                    match++;
                }
                else{
                    break;
                }

                if(match == (cols * rows - 1) && shuffled && started){ // if all tiles are in proper position, then reveal the icon for empty tile (last tile), disable action listeners, and stop timer
                    timer.stop();
                    start.setBackground(null);
                    b[8].setIcon(new ImageIcon(segImage[8]));
                    shuffle.removeActionListener(this);
                    start.removeActionListener(this);
                    stop.removeActionListener(this);

                    for(int j = 0; j < b.length; j++){
                        b[j].removeActionListener(this);
                    }

                    completed = true;

                    JOptionPane.showMessageDialog(null, "Well done, you have cleared the puzzle! \nTime taken: " + l.getText(), "Clear", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
    
    public static void main(String [] args){
    }
}
