//import com.ctc.wstx.sw.EncodingXmlWriter;
//import com.spring.springcloud.entities.Dept;
//import com.sun.org.apache.xml.internal.serialize.OutputFormat;
//import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
//import org.apache.commons.lang.time.DateFormatUtils;
//import org.apache.tomcat.util.http.fileupload.FileUtils;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.xml.sax.InputSource;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.annotation.*;
//import javax.xml.bind.annotation.adapters.XmlAdapter;
//import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.*;
//import java.text.DateFormat;
//import java.text.FieldPosition;
//import java.text.ParsePosition;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class TestCreateXml {
//
//    List<String> list = new ArrayList<>();
//    List<Dept> deptList = new ArrayList<>();
//    List<Dept> deptList2 = new ArrayList<>();
//    List<Dept> deptList3 = new ArrayList<>();
//    String xmlFiel = generateXML3("20190000528","CUS3500000039399","101",
//            list,deptList,deptList2,deptList3);
//
//    private static <T> String generateXML3(String businessId, String messageId, String messageType, List<String> list, List<?>...dataList) {
//        int cur_pocket_no = 1;
//        String pocketId = UUID.randomUUID().toString();//cafb0291_9f8d_4d8f_91b3
//        String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd'T'HH:mm:ss");//2019-09-19T10:12:05
//        String fileName = messageType+"_"+messageId+".xml";//INVT101_DJALD00000E.xml
//        //写入路径名
//       String urlName = "D:/软件/库/"+fileName;
//       //报文模板,都是在这个model.xml模板上面写
//        File f = new File("D:/model.xml");
//        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(f);
//            Element root = doc.getDocumentElement();
//            //设置xml中一些节点的信息
//            //设置流水号
//            Node businessCodeNode = root.getElementsByTagName("business_id").item(0);
//            businessCodeNode.setTextContent(businessId);
//            //设置业务主键
//            Node messageCodeNode = root.getElementsByTagName("message_id").item(0);
//            messageCodeNode.setTextContent(messageId);
//            //设置文件名
//            Node fileNameNode = root.getElementsByTagName("file_name").item(0);
//            fileNameNode.setTextContent(fileName);
//            //设置业务类型
//            Node messageTypeNode = root.getElementsByTagName("message_type").item(0);
//            messageTypeNode.setTextContent(messageType);
//            //设置发送时间
//            Node sendTimeNode = root.getElementsByTagName("send_time").item(0);
//            sendTimeNode.setTextContent(date);
//            //处理传入的实体类
//            if(dataList.length >0){
//                //创建节点 messageType层
//                Element messageTypeEle = doc.createElement(messageType);
//                //添加节点到DataInfo下
//                doc.getElementsByTagName("BusinessData").item(0).appendChild(messageTypeEle);
//                //遍历参数
//                for(int i=0;i<dataList.length;i++){
//                    List<?> tList = dataList[i];
//                    for(int j=0;j<tList.size();j++){
//                        T t = (T) tList.get(j);
//                       String object2xml =  object2xml(t);
//                        Document parse = db.parse(new InputSource(new StringReader(object2xml)));
//                        Element documentElement = parse.getDocumentElement();
//                        messageTypeEle.appendChild(doc.importNode(parse.getDocumentElement(),true));
//                    }
//                }
//            }
//            createDirectory("d:/suo/annex");
//            boolean writeXML = writeXML(doc,urlName,messageType);
//            if(writeXML){
//                 synchronized (TestCreateXml.class){
//                    sendxml(messageType,urlName);
//                 }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return "fail";
//        }
//    return "ok";
//    }
//
//    private static void sendxml(String messageType, String urlName) {
//        MQSend instance = MQSend.getInstance();
//        File file = new File(urlName);
//        try {
//            instance.sendFileMessage(urlName,messageType,null);
//            Date dat = new Date();
//            //报文发送成功后存入历史目录
//            String path = "c:/mqsendSuccess"+new SimpleDateFormat("yyyy-MM-dd").format(dat);
//            createDirectory(path);
//            //将报文转到成功历史目录下
//            copyFileNew(file,new File(path+File.separator+file.getName()));
//            file.delete();
//        } catch (Exception e) {
//            e.printStackTrace();
//            //失败，将报文转到失败目录下
//            createDirectory("c:/mqsendFail");
//            copyFileNew(file,new File("c:/mqsendFail"+file.getName()));
//        }
//    }
//
//    private static void copyFileNew(File sourceFile, File targetFile) {
//        BufferedInputStream inBuff = null;
//        BufferedOutputStream outBuff = null;
//        try {
//            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
//            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
//            byte[] b = new byte[1024*5];
//            int len;
//            while ((len = inBuff.read(b))!= -1){
//                outBuff.write(b,0,len);
//            }
//            outBuff.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    /**
//     * 写入xml
//     * @param doc
//     * @param newFileName
//     * @param messageType
//     * @return
//     */
//    private static boolean writeXML(Document doc, String newFileName, String messageType) {
//        PrintWriter pw = null;
//        try {
//            doc.normalize();
//            OutputFormat format = new OutputFormat(doc);
//            format.setLineWidth(10);
//            format.setIndenting(true);
//            format.setIndent(2);
//            format.setEncoding("UTF-8");
//            StringWriter out = new StringWriter();
//            XMLSerializer serializer = new XMLSerializer(out, format);
//            serializer.serialize(doc);
//            pw = new PrintWriter(new FileOutputStream(newFileName));
//            String content = out.toString();
//            pw.print(content);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }finally {
//            if(pw != null){
//                pw.close();
//            }
//        }
//        return true;
//    }
//
//    private static int createDirectory(String descDirName) {
//        String descDirNames = descDirName;
//        if(!descDirNames.endsWith(File.separator)){
//            descDirNames = descDirNames+File.separator;
//        }
//        File descDir = new File(descDirNames);
//        if(descDir.exists()){
//            return 1;
//        }
//        //创建目录
//        if(descDir.mkdir()){
//            return 2;
//        }else{
//            return 3;
//        }
//    }
//
//    private static String  object2xml(Object object){
//        try {
//            ConcurrentHashMap<String, JAXBContext> contentCache = new ConcurrentHashMap();
//            JAXBContext content = contentCache.get(object.getClass().getName());
//            if(content == null){
//                content = JAXBContext.newInstance(object.getClass());
//                contentCache.putIfAbsent(object.getClass().getName(),content);
//            }
//            Marshaller createMarshaller = content.createMarshaller();
//            createMarshaller.setProperty("jaxb.formatted,output",true);//格式化输出
//            createMarshaller.setProperty("jaxb.encoding","UTF-8");
//            createMarshaller.setProperty("jaxb.encoding","utf-8");
//            createMarshaller.setProperty("jaxb.fragment",false);//去掉xml头部信息
//            StringWriter sw = new StringWriter();
//            createMarshaller.marshal(object,sw);
//            return new String(sw.getBuffer());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//      return "";
//    }
//}
//@XmlRootElement(name = "Student")
//@XmlType
//@XmlAccessorType(XmlAccessType.FIELD)
//class Student implements Serializable{
//
//    @XmlTransient
//    //忽略age,不解析当前熟悉
//    private String age;
//
//    //时间类型加这个注解
//    @XmlJavaTypeAdapter(value=DateAdapter.class)
//    private Date invtDate;
//
//}
//class DateAdapter extends XmlAdapter<String,Date> {
//
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    @Override
//    public Date unmarshal(String v) throws Exception {
//        return sdf.parse(v);
//    }
//
//    @Override
//    public String marshal(Date v) throws Exception {
//        return sdf.format(v);
//    }
//}