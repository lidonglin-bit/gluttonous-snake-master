package com.snake;
import javax.swing.*;
import java.awt.*;
public class StartGame {
    public static void main(String[] args){
        //������Ϸ����
        JFrame frame = new JFrame("Java-̰����С��Ϸ");//����
        frame.setSize(900,720);//���ڴ�С
        frame.setLocationRelativeTo(null);//������ʾ��Ļ�м�
        frame.setResizable(false);//�̶����ڴ�С
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ô���ر��¼�
        frame.add(new GamePanel());//�����Ϸ����
        frame.setVisible(true);//���ô���ɼ�
    }
}