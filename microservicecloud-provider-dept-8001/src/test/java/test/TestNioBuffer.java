package test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestNioBuffer {
    public static void main(String[] args) throws Exception{
        RandomAccessFile fromFile = new RandomAccessFile("", "re");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile("", "r");
        FileChannel toChannel = toFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        toChannel.transferFrom(fromChannel,position,count);
//        final RandomAccessFile aFile = new RandomAccessFile("D:\\软件\\库\\bigscreen.sql","rw");
//        FileChannel inChannel = aFile.getChannel();
//        ByteBuffer header = ByteBuffer.allocate(128);
//        ByteBuffer body = ByteBuffer.allocate(1024);
//        ByteBuffer[] bufferArray = {header,body};
//        long read = inChannel.read(bufferArray);
////        ByteBuffer buf = ByteBuffer.allocate(48);
////        int bytesRead = inChannel.read(buf);
//        while (bytesRead!= -1){
//            System.out.println("bytesRead"+bytesRead);
//            buf.flip();
//            while (buf.hasRemaining()){
//                System.out.println((char) buf.get());
//            }
//            buf.clear();
//            bytesRead = inChannel.write(buf);
//            long write = inChannel.write(bufferArray);
//
//        }
//        aFile.close();
    }
}
