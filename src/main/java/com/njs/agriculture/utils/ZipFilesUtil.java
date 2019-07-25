package com.njs.agriculture.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/14
 * @Description:
 */
public class ZipFilesUtil {


    public static void compress(File f, String baseDir, ZipOutputStream zos){
        if(!f.exists()){
            System.out.println("待压缩的文件目录或文件"+f.getName()+"不存在");
            return;
        }


        File[] fs = f.listFiles();
        BufferedInputStream bis = null;
        //ZipOutputStream zos = null;
        byte[] bufs = new byte[1024*10];
        FileInputStream fis = null;


        try{
            //zos = new ZipOutputStream(new FileOutputStream(zipFile));
            for(int i=0; i<fs.length; i++){
                String fName =  fs[i].getName();
                System.out.println("压缩：" + baseDir+fName);
                if(fs[i].isFile()){
                    ZipEntry zipEntry = new ZipEntry(baseDir+fName);//
                    zos.putNextEntry(zipEntry);
                    //读取待压缩的文件并写进压缩包里
                    fis = new FileInputStream(fs[i]);
                    bis = new BufferedInputStream(fis, 1024*10);
                    int read = 0;
                    while((read=bis.read(bufs, 0, 1024*10))!=-1){
                        zos.write(bufs, 0, read);
                    }
                    //如果需要删除源文件，则需要执行下面2句
                    // TODO 有可能有bug
//                    fis.close();
//                    fs[i].delete();
                }
                else if(fs[i].isDirectory()){
                    compress(fs[i], baseDir+fName+"/", zos);
                }
            }//end for
        }catch  (IOException e) {
            e.printStackTrace();
        } finally{
            //关闭流
            try {
                if(null!=bis)
                    bis.close();
                //if(null!=zos)
                //zos.close();
                if(null!=fis)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 压缩工具类
     * @param zipName 压缩包名字
     * @param sourceFilePath  源文件路径
     * @param zipLocation  zip包路径
     */
    public static void zipUtil(String zipName, String sourceFilePath, String zipLocation){
        File sourceDir = new File(sourceFilePath);
        File zipFile = new File(zipLocation + zipName +".zip");
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFile));
            String baseDir = zipName + "/";
            compress(sourceDir, baseDir, zos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally{
            if(zos!=null)
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }


    }
    public static void main( String[] args )  {
        zipUtil("123", "D://213124", "D://");
    }
}
