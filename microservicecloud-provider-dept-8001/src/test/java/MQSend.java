import com.ibm.mq.*;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.FileInputStream;
import java.io.InputStream;

public class MQSend {
    private static MQSend instance;
    private MQQueueManager qManager;
    private MQQueue queue;

    private static String userID = "gao";
    private static String password = "1234";
    //对列管理器名称
    private static String qmManager = "3ere";
    //远程对列名称
    private static String BHLremoteQname = "bhl";
    private static String EMSremoteQname = "bhl";
    private static String GTRremoteQname = "bhl";
    //本机名称
    private static String hostname = "10.200.4.28";
    //服务器连接通道
    private static String channe1 = "QM_ORANGE_SERVER";
    //1383
    private int  ccsid = Integer.parseInt("QM_ORANGE_SERVER");
    //1415
    private static int port = Integer.parseInt("QM_ORANGE_SERVER");

    //发送方文件读取路径
    private static String targetUrl = "c:/suo/lon";

    private MQSend(){
        MQEnvironment.hostname = hostname;
        MQEnvironment.channel = channe1;
        MQEnvironment.CCSID = ccsid;
        MQEnvironment.port = port;
        MQEnvironment.userID = userID;
        MQEnvironment.password = password;
        MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY,MQC.TRANSPORT_MQSERIES);

        try {
            qManager = new MQQueueManager(qmManager);
        } catch (MQException e) {
            e.printStackTrace();
        }
    }

    public static MQSend getInstance(){
        if(instance == null){
            instance = new MQSend();
        }
        return instance;
    }

    /**
     * 如果对列管理器为空，建立
     */
    private void createConnection(){
        if(qManager == null){
            new MQSend();
        }
    }

    public void sendFileMessage(String fileName,String type,String xml2) throws Exception{
        this.createConnection();
        InputStream in = null;
        InputStream inF = null;
        //建立对列打开模式
        int openOptions = MQC.MQOO_OUTPUT | MQC.MQOO_FAIL_IF_QUIESCING;
        //报税货物流转
        if(type.contains("BHL")){
            //连接对列（发送时此对列为发送方的远程对列）
             queue = qManager.accessQueue(BHLremoteQname,openOptions,null,null,null);
             //附件等非结构化报文
        } else if (type.contains("UNS")) {
            queue = qManager.accessQueue(BHLremoteQname,openOptions,null,null,null);
            //创建消息放入方式实例
            MQPutMessageOptions pmo = new MQPutMessageOptions();
            //创建mq消息实例
            MQMessage message = new MQMessage();
            //输入流读取要发送的文件
            inF = new FileInputStream(fileName);
            byte[] data = xml2.getBytes();
            byte[] data2 = new byte[inF.available()];
            inF.read(data2);
            byte[] data3 = new byte[data.length+data2.length];
            System.arraycopy(data,0,data3,data2.length,data.length);
            System.arraycopy(data2,0,data3,0,data2.length);
            message.write(data3);
            queue.put(message,pmo);
            qManager.commit();
        }
        if(!type.contains("UNS") && !type.contains("")){
            if(null != queue){
                MQPutMessageOptions pmo = new MQPutMessageOptions();
                MQMessage message = new MQMessage();
                if(fileName.indexOf(":") != -1){
                    in = new FileInputStream(fileName);
                }else{
                    in = new FileInputStream(targetUrl + fileName);
                }
                byte[] data = new byte[in.available()];
                in.read(data);
                message.write(data);
                queue.put(message,pmo);
                qManager.commit();
            }
        }
    }


}
