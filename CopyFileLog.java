package com.company;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CopyFileLog {
    private static Logger log = Logger.getLogger(CopyFileLog.class.getName());
    public static void main(String[] args) throws InterruptedException, IOException {
        FileHandler fileL;
        fileL = new FileHandler("FileLog.txt"); //файл, в который будут записываться логи
        log.addHandler(fileL);
        SimpleFormatter formatter = new SimpleFormatter();
        fileL.setFormatter(formatter); //форматирование текста(будут записываться только нужные строки)
        log.info("Происходит копирование первого текста из одного файла в другой");
        File source = new File("source.txt"); //файл с текстом
        File dest = new File("dest.txt"); //файл, в который будет копироваться
        log.info("Происходит копирование второго текста из одного файла в другой");
        File source1 = new File("source1.txt"); //файл с текстом
        File dest1 = new File("dest1.txt"); //файл, в который будет копироваться

        long start = System.nanoTime();
        copyFile(source,dest);
        copyFile(source1, dest1);
        log.info("Вывод времени вполнения копирования");
        System.out.println("Время копирования файла с помощью потоков = "+(System.nanoTime()-start));
    }
    public static  void copyFile( File source,  File dest)  throws IOException {

        FileChannel is= null;
        FileChannel os = null;
        try {
            is = new FileInputStream(source).getChannel();
            os = new FileOutputStream(dest).getChannel();
            os.transferFrom(is, 0, is.size());
        } finally {
            is.close();
            os.close();
        }
    }
}