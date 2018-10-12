package com.hbrd.TerminusJoin;

import com.hbrd.Model.Message;
import com.hbrd.Service.impl.CarImpl;
import com.hbrd.Service.impl.MessageImpl;
import com.hbrd.Util.ApplicationContextProvider;
import com.hbrd.Util.Util;

import java.io.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Arrays;

public class TerminusMessage extends  Thread{
    private Socket s;

    private InputStream inStream;
    private BufferedInputStream bufferedInputStream;
    private DataInputStream dis;

    private OutputStream outStream;
    private PrintWriter out;

    private  byte[]  buffer= new byte[300];

    private Parse parse;
    public TerminusMessage(Socket  s) {
        this.s=s;
        this.start();
    }
    @Override
    public void run() {
        try {
            init();  //初始化
            int len = -1;
            while ((len = dis.read(buffer)) != -1) {
                Message m=Parse.bytesToHexString(buffer);
                System.out.println("分割："+m.toString());
                MessageImpl message= ApplicationContextProvider.getBean(MessageImpl.class);
                message.InputTerminusMessage(m);
                buffer=new byte[300];
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close();
        }

    }

    /**
     * 初始化
     * @throws IOException
     */
    public void init() throws IOException {
        s.setSoLinger(true,0);
        System.out.println("终端连接--------");
        //输入
        inStream = s.getInputStream();
        bufferedInputStream = new BufferedInputStream(inStream);
        dis = new DataInputStream(bufferedInputStream);
        //输出
        outStream = s.getOutputStream();
        out = new PrintWriter(outStream, true);
        out.println("HHH");   //保证连接
        parse=new Parse();
    }

    /**
     * 断开链接
     */
    public void close(){
        try {
            System.out.println("终端断开：");
            dis.close();
            bufferedInputStream.close();
            inStream.close();

            out.close();
            outStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
