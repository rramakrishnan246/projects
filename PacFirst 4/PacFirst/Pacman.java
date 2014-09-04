//created by Andy Hutchison and Rohan Ramakrishnan

import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.JComponent;
import java.util.Random;

public class Pacman extends JComponent implements ActionListener
{
    public Ellipse2D.Double pacman;
    public Ellipse2D.Double ghost1;
    public Ellipse2D.Double ghost2;
    public Ellipse2D.Double ghost3;
    public Ellipse2D.Double ghost4;
    Random randomizer = new Random();
    Board game = new Board();
    Font currFont = new Font(getName(),Font.BOLD,20);
    int myDir;
    int status;
    int innerStatus;
    int movement;
    //indexes for 2D array - Pacman
    int currentX=0;
    int currentY=0;

    //indexes for 2D array - ghosts
    int currentGhostX=0;
    int currentGhostY=0;

    int ghost1x=3;
    int ghost1y=17;
    int ghost2x=3;
    int ghost2y=42;
    int ghost3x=11;
    int ghost3y=21;
    int ghost4x=14;
    int ghost4y=7;

    int score=0; 
    String scoreString="";
    int tempNum;
    int totalNum; 
    int pacmanDir;
    int turnAngle;
    int counter=0;
    int[][] positions = game.makeObstacles();

    //2D arrays that hold coordinate values
    int[][] xPositions = new int[15][43];
    int[][] yPositions = new int[15][43];

    //temp variables used in filling in array
    int xPos; 
    int yPos;
    int pacPosition;
    int ghostPosition;

    int[] directions = new int[4];

    int newDirection=4;
    int g1Direction=1; 
    int g2Direction=2; 
    int g3Direction=3; 
    int g4Direction=4;
    int pw=13;
    int ph=13;
    int gameover=1;
    public Pacman()
    {
        enterCoordinates();
    }

    public void actionPerformed (ActionEvent e)
    {
        repaint();
        movePacman(myDir);
        moveGhostPos(1, newDirection, ghost1x, ghost1y);
        moveGhostPos(2, newDirection, ghost2x, ghost2y);
        moveGhostPos(3, newDirection, ghost3x, ghost3y);
        moveGhostPos(4, newDirection, ghost4x, ghost4y);
        checkGame();
    }

    public void paintComponent(Graphics g)
    {
        counter ++;
        Graphics2D g2 = (Graphics2D) g;
        for (int a=0; a<15; a++)
        {
            for (int b=0; b<43; b++)
            {
                innerStatus = checkSpot(a,b);
                if (innerStatus == 1 ) g2.setColor(Color.BLACK);
                if (innerStatus == 2) g2.setColor(Color.RED);
                if (innerStatus == 3) g2.setColor(Color.BLUE);
                if (innerStatus ==4) g2.setColor(Color.BLACK);
                if (innerStatus ==5) g2.setColor(Color.ORANGE);
                else if (innerStatus==6) g2.setColor(Color.BLACK);
                g2.fillRect(xPositions[a][b], yPositions[a][b],15,15);
            }
        }

        for (int rowNum=0; rowNum<15; rowNum++)
        {
            for (int colNum=0; colNum<43; colNum++)
            {
                if (positions[rowNum][colNum] == 1)
                {
                    g2.setColor(Color.WHITE);
                    g2.fillOval(xPositions[rowNum][colNum]+5, yPositions[rowNum][colNum]+5,5,5);
                }
                else if (positions[rowNum][colNum] == 6)
                {
                    g2.setColor(Color.PINK);
                    g2.fillOval(xPositions[rowNum][colNum]+2, yPositions[rowNum][colNum]+2,10,10);
                }
            }
        }
        g2.setColor(Color.BLACK);
        g2.setFont(currFont);
        g2.drawString(scoreString, 25, 300);
        pacman = new Ellipse2D.Double(xPositions[currentX][currentY],yPositions[currentX][currentY],pw,ph);
        ghost1 = new Ellipse2D.Double(xPositions[ghost1x][ghost1y],yPositions[ghost1x][ghost1y],13,13);
        ghost2 = new Ellipse2D.Double(xPositions[ghost2x][ghost2y],yPositions[ghost2x][ghost2y],13,13);
        ghost3 = new Ellipse2D.Double(xPositions[ghost3x][ghost3y],yPositions[ghost3x][ghost3y],13,13);
        ghost4 = new Ellipse2D.Double(xPositions[ghost4x][ghost4y],yPositions[ghost4x][ghost4y],13,13);
        g2.setColor(Color.YELLOW);
        g2.fill(pacman);
        g2.setColor(Color.GREEN);
        g2.fill(ghost1);
        g2.fill(ghost2);
        g2.fill(ghost3);
        g2.fill(ghost4);
        g2.setColor(Color.BLACK);
        if(pacmanDir==1)turnAngle=45;
        if(pacmanDir==4)turnAngle=135;
        if(pacmanDir==3)turnAngle=225;
        if(pacmanDir==2)turnAngle=315;
        if (counter%2==0){
            g2.fillArc(xPositions[currentX][currentY],yPositions[currentX][currentY],13,13,turnAngle,90);
        }

    }

    public void movePacman(int dir)
    {
        if (gameover==1)
        {
            removeDots();
            checkGame();
            myDir = dir;
            //down

            if (myDir==1)
            {
                if(currentX<14)
                {
                    movement=checkSpot(currentX+1, currentY);
                    if ((movement==1||movement==4||movement==6) && yPositions[currentX][currentY]<=250) currentX+=1;
                    pacmanDir=3;
                }
            }
            //up
            else if (myDir == 2)
            {
                if(currentX>0)
                {
                    movement=checkSpot(currentX-1, currentY);
                    if ((movement==1||movement==4||movement==6) && yPositions[currentX][currentY]>=25) currentX-=1;
                    pacmanDir=1;
                }
            }
            //right
            else if (myDir == 3)
            {
                if(currentY<42)
                {
                    movement=checkSpot(currentX, currentY+1);
                    if ((movement==1||movement==4||movement==6) && xPositions[currentX][currentY]<=670) currentY+=1;    
                    pacmanDir=2;
                }   
            }
            //left
            else if (myDir == 4)
            {
                if (currentY>0)
                {
                    movement=checkSpot(currentX, currentY-1);
                    if ((movement==1||movement==4||movement==6) && xPositions[currentX][currentY]>=30) currentY-=1;
                    pacmanDir=4;
                }   
            }
        }
    }

    public void moveGhostPos(int tempGhostNum, int gDir, int tempX, int tempY)
    {
        if (gameover==1)
        {
            checkGame();
            int ghostNum = tempGhostNum; 
            int ghostIndex = tempGhostNum-1;
            int ghostDir = directions[ghostNum-1];
            currentGhostX = tempX;
            currentGhostY = tempY; 

            //down
            if (ghostDir==1)
            {
                if(currentGhostX<14)
                {
                    movement=checkSpot(currentGhostX+1, currentGhostY);
                    if ((movement==1||movement==4||movement==6) && yPositions[currentGhostX][currentGhostY]<=265) currentGhostX+=1;
                    else moveGhosts(ghostIndex);
                }
                else moveGhosts(ghostIndex);
            }
            //up
            else if (ghostDir == 2)
            {
                if(currentGhostX>0)
                { 
                    movement=checkSpot(currentGhostX-1, currentGhostY);
                    if ((movement==1||movement==4||movement==6) && yPositions[currentGhostX][currentGhostY]>=40) currentGhostX-=1;    
                    else moveGhosts(ghostIndex);
                }
                else moveGhosts(ghostIndex);
            }
            //right
            else if (ghostDir == 3)
            {
                if(currentGhostY<42)
                {
                    movement=checkSpot(currentGhostX, currentGhostY+1);
                    if ((movement==1||movement==4||movement==6) && xPositions[currentGhostX][currentGhostY]<=650) currentGhostY+=1;  
                    else moveGhosts(ghostIndex);
                }
                else moveGhosts(ghostIndex);
            }
            //left
            else if (ghostDir == 4)
            {
                if (currentGhostY>0)
                {
                    movement=checkSpot(currentGhostX, currentGhostY-1);
                    if ((movement==1||movement==4||movement==6) && xPositions[currentGhostX][currentGhostY]>=40) {currentGhostY-=1;}
                    else 
                    {moveGhosts(ghostIndex);}
                }
                else moveGhosts(ghostIndex);
            }

            if (ghostNum == 1)
            {
                ghost1x = currentGhostX;
                ghost1y = currentGhostY;
            }
            else if (ghostNum == 2)
            {
                ghost2x = currentGhostX;
                ghost2y = currentGhostY;
            }
            else if (ghostNum == 3)
            {
                ghost3x = currentGhostX;
                ghost3y = currentGhostY;
            }
            else if (ghostNum == 4)
            {
                ghost4x = currentGhostX;
                ghost4y = currentGhostY;
            }
        }
    }

    public int checkSpot(int xSpot, int ySpot)
    {
        if(positions[xSpot][ySpot] == 1) status=1;
        if (positions[xSpot][ySpot] == 2) status=2;
        if (positions[xSpot][ySpot] == 3) status=3;
        if (positions[xSpot][ySpot] == 4) status=4;
        if (positions[xSpot][ySpot] == 5) status=5;
        else if (positions[xSpot][ySpot] == 6) status=6;
        return status;
    }

    public void removeDots()
    {
        int curPos = positions[currentX][currentY];
        if(curPos==1)
        {
            positions[currentX][currentY]=4;
            score+=10;
            scoreString ="Score: " + Integer.toString(score);
            repaint();
        }
    }

    public void enterCoordinates()
    {
        directions[0]=1;
        directions[1]=2;
        directions[2]=3;
        directions[3]=4;
        yPos=10;
        for (int row=0; row<15; row++)
        {
            xPos=10;
            yPos+=15;
            for (int col=0; col<43; col++)
            {
                xPos+=15;
                xPositions[row][col] = xPos;
                yPositions[row][col] = yPos;
            }
        }
    }

    public void checkGame()
    {
        totalNum=0; 
        for(int xx=0; xx<15; xx++)
        {
            for(int yy=0; yy<43; yy++)
            {
                if (positions[xx][yy]==1)tempNum=1;
                else tempNum=0;
                totalNum+=tempNum;
            }
        }

        if (totalNum==0) 
        {
            scoreString = "WINNER!!!";
            gameover = 2;
            repaint();

        }

        if ((currentX == ghost1x && currentY == ghost1y)||(currentX == ghost2x && currentY == ghost2y)
        ||(currentX == ghost3x && currentY == ghost3y)||(currentX == ghost4x && currentY == ghost4y)) 
        {
            scoreString = "LOSER!!!";
            repaint();
            gameover=2;
        }

    }

    public int getDistances()
    {
        pacPosition = xPositions[currentX][currentY] + yPositions[currentX][currentY];
        pacPosition = xPositions[currentGhostX][currentGhostY] + yPositions[currentGhostX][currentGhostY];
        int distance = Math.abs(ghostPosition - pacPosition); 
        return distance;
    }

    public void moveGhosts(int GhostNumba)
    {
        int myGN = GhostNumba;
        int myDistance = getDistances();

        int randomNum = randomizer.nextInt(4)+1;
        directions[myGN] = randomNum;

    }

}
