package com.snake;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    int[] snakeX = new int[500];//̰���ߺ�����
    int[] snakeY = new int[500];//̰����������
    int foodX;//ʳ�������
    int foodY;//ʳ����������
    int length;//̰���ߵĳ���
    String  direction;//̰����ͷ����
    int score;//����
    Random r = new Random();
    Timer timer = new Timer(100,this);
    boolean isStart;
    boolean isFail;
    //���캯��
    public GamePanel(){
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }
    private void init(){
        length=3;
        snakeX[0]=100;snakeY[0]=100;
        snakeX[1]=75;snakeY[1]=100;
        snakeX[2]=50;snakeY[2]=100;
        direction = "R";
        eat(foodX,foodY);
        isStart = false;
        isFail = false;
        score = 0;

    }
    private void eat(int x,int y){
        x= 25 + 25*r.nextInt(34);
        y= 75 + 25*r.nextInt(24);
        for (int i = 0; i < length; i++) {
            if(snakeX[i]==x&&snakeY[i]==y){
                x = 25 + 25*r.nextInt(34);
                y = 75 + 25*r.nextInt(24);
            }
        }
        foodX = x;foodY = y;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.white);//���ñ�����Ϊ��ɫ
        //������
        g.setColor(Color.GREEN);
        g.setFont(new Font("��Բ",Font.BOLD,50));
        g.drawString("̰������Ϸ",300,60);
        //������Ϸ����
        g.setColor(Color.GRAY);
        g.fillRect(25,75,850,600);
        //��̰����ͷ��
        if(direction=="R"){
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        else if(direction=="L"){
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        if(direction=="U"){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        else if(direction=="D"){
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        //������
        for (int i = 1; i < length ; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }
        //��ʳ��
        Data.food.paintIcon(this,g,foodX,foodY);
        //���ƻ�����
        g.setColor(Color.BLACK);
        g.setFont(new Font("��Բ",Font.BOLD,20));
        g.drawString("���ȣ�"+length,730,30);
        g.drawString("�÷֣�"+score,730,60);
        //��Ϸ��ʼ����
        if(isStart==false){
            g.setColor(Color.BLACK);
            g.setFont(new Font("��Բ",Font.BOLD,40));
            g.drawString("���ո����ʼ��Ϸ",300,300);
        }
        //ʧ���ж�
        if(isFail){
            g.setColor(Color.RED);
            g.setFont(new Font("��Բ",Font.BOLD,40));
            g.drawString("��Ϸʧ�ܣ����ո�����¿�ʼ",300,300);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();//��ȡ���µİ���
        //�жϿո�
        if(keyCode==KeyEvent.VK_SPACE){
            if(isFail){
                isFail = false;
                init();
            }
            else{
                isStart = !isStart;
            }
            repaint();
        }
        //�жϷ���
        if(keyCode==KeyEvent.VK_LEFT&&direction!="R"){
            direction = "L";
        }
        else if(keyCode==KeyEvent.VK_RIGHT&&direction!="L"){
            direction = "R";
        }
        else if(keyCode==KeyEvent.VK_UP&&direction!="D"){
            direction = "U";
        }
        else if(keyCode==KeyEvent.VK_DOWN&&direction!="U"){
            direction = "D";
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //�ж���Ϸ״̬
        if(isStart&&!isFail){
            //�ƶ�����
            for (int i = length-1; i > 0 ; i--) {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            //�ƶ�ͷ��
            if(direction=="R"){
                snakeX[0] += 25;
                if(snakeX[0]>850){
                    snakeX[0] = 25;
                }
            }
            else  if(direction=="L"){
                snakeX[0] -= 25;
                if(snakeX[0]<25){
                    snakeX[0] = 850;
                }
            }
            else  if(direction=="U"){
                snakeY[0] -= 25;
                if(snakeY[0]<75){
                    snakeY[0] = 650;
                }
            }
            else  if(direction=="D"){
                snakeY[0] += 25;
                if(snakeY[0]>650){
                    snakeY[0] = 75;
                }
            }
            //��ʳ��
            if(snakeX[0]==foodX&&snakeY[0]==foodY){
                length++;
                score += 10;
                eat(foodX,foodY);
            }
            //�����ж�
            for (int i = 1; i < length; i++) {
                if(snakeX[0]==snakeX[i]&&snakeY[0]==snakeY[i]){
                    isFail=true;
                }
            }
            repaint();
        }
        timer.start();
    }
}