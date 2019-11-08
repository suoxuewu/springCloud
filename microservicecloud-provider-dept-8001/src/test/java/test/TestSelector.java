package test;

import java.nio.ByteBuffer;
import java.nio.channels.*;

public class TestSelector {
    public static void main(String[] args) throws Exception{
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sink = pipe.sink();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.clear();
        buf.put("wss".getBytes());
        buf.flip();
        while (buf.hasRemaining()){
            sink.write(buf);
        }
        Pipe.SourceChannel source = pipe.source();
        int read = source.read(buf);

//        ServerSocketChannel se = ServerSocketChannel.open();
//        se.socket().bind(new InetSocketAddress(9999));
//        se.configureBlocking(false);
//        while (true){
//            SocketChannel accept = se.accept();
//            if(accept != null){
//
//            }
//        }
//        SocketChannel socketChannel = SocketChannel.open();
//        socketChannel.configureBlocking(false);
//        while (socketChannel.finishConnect()){
//
//        }
//        socketChannel.connect(new InetSocketAddress("",80));
//        socketChannel.close();
//        ByteBuffer buf = ByteBuffer.allocate(1024);
//        int read = socketChannel.read(buf);
//        buf.flip();
//        while (buf.hasRemaining()){
//            socketChannel.write(buf);
//        }
//        buf.clear();
//        Selector open = Selector.open();
//
//        FileChannel channel = aFile.getChannel();
//        channel.config
    }
}
