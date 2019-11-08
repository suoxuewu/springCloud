package test;

import java.io.File;

public class XmlRead {

    //定时任务去读取目标文件
    public void readXml(){
        //报文接受目录
        String url = "url";
        //报文解析成功后存入历史目录
        String urlLog = "url_log_success";
        //报文解析失败后存入失败目录
        String urlLogFail = "url_log_fail";
        //需要解析的文件后缀数组
        String endWith = "xml,REE,EPT";
        String[] endWithArr = endWith.split(",");
        createDirectory(url);
        File dir = new File(url);
        //获取目录下的所以文件
        File[] files = dir.listFiles();
        if(files != null && files.length >0){

        }
    }

    private static int createDirectory(String descDirName) {
        String descDirNames = descDirName;
        if(!descDirNames.endsWith(File.separator)){
            descDirNames = descDirNames+File.separator;
        }
        File descDir = new File(descDirNames);
        if(descDir.exists()){
            return 1;
        }
        //创建目录
        if(descDir.mkdir()){
            return 2;
        }else{
            return 3;
        }
    }
}
