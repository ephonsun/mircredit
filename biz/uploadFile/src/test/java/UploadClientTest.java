import com.banger.mobile.facade.impl.uploadFile.UploadClient;
import org.junit.Test;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.io.File;

/**
 * Created by Administrator on 14-2-11.
 */
public class UploadClientTest {

    @Test
    public void testSendFile(){
        String host = "170.100.130.107";
        int port = 5555;
        String appid = "YDDK";
        int packageSize = 1024 * 4;
        String filePath = "E:";
        String fileName = "mov_0001073_1392358290052.3gp";


        UploadClient uploadClient = new UploadClient(host, port, appid, packageSize);
        File fileInput = new File(filePath + "//" + fileName);
        uploadClient.sendFile(fileInput, fileName, "3");
    }

    @Test
    public void testUploadFile(){
        String host = "170.100.130.107";
        int port = 5555;
        String appid = "YDDK";
        int packageSize = 1024 * 4;

        UploadClient uploadClient = new UploadClient(host, port, appid, packageSize);
        String dirName = "e:/test/";
        File dirFile = new File(dirName);
        File[] files = dirFile.listFiles();
        for (File f : files) {
            if (f.isDirectory()){
                continue;
            }
            uploadClient.sendFile(f, f.getName(),"3");
        }
    }
}
