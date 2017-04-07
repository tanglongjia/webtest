package com.tonyj.frame.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
	private static Logger logger = Logger.getLogger(FileUtils.class);
	
	//写文件
	public static boolean writeFile(String filePath,String fileName,MultipartFile file){
		File fileDir =new File(filePath);     
		if(!fileDir.exists()  && !fileDir.isDirectory()){
			boolean f = fileDir.mkdirs();  
		    if(!f){
		    	return false;
		    }
		}
		FileOutputStream output=null;
		try {
			byte[] bytes = file.getBytes();
			output = new FileOutputStream(new File(filePath+fileName));
			output.write(bytes);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}finally{
			if(output!=null){
				try {
					output.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
		}
		return true;
	}
	
	
	
	////////////////以下为图片压缩相关/////////////////////////////
	public static void zip(String inputFileName,String outputFileName) throws Exception {
        System.out.println(outputFileName);
        zip(outputFileName, new File(inputFileName));
    }

    private static void zip(String outputFileName, File inputFile) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFileName));
        zip(out, inputFile, "");
        System.out.println("zip done");
        out.close();
    }

    private static void zip(ZipOutputStream out, File f, String base) throws Exception {
        if (f.isDirectory()) {
           File[] fl = f.listFiles();
           out.putNextEntry(new ZipEntry(base + "/"));
           base = base.length() == 0 ? "" : base + "/";
           for (int i = 0; i < fl.length; i++) {
           zip(out, fl[i], base + fl[i].getName());
         }
        }else {
           out.putNextEntry(new ZipEntry(base));
           FileInputStream in = new FileInputStream(f);
           int b;
           System.out.println(base);
           while ( (b = in.read()) != -1) {
            out.write(b);
         }
         in.close();
       }
    }
}
