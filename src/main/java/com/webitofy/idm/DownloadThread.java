package com.webitofy.idm;


import com.webitofy.idm.models.FileInfo;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

public class DownloadThread extends Thread {
    FileInfo file;
    DownloadManager manager;

    public DownloadThread(FileInfo file, DownloadManager manager) {
        this.file = file;
        this.manager = manager;
    }

    @Override
    public void run() {
        this.file.setStatus("DOWNLOADING...");
        this.manager.updateUI(this.file);

        try {
//            Files.copy(new URL(this.file.getUrl()).openStream(), Paths.get(this.file.getPath()));
            URL url = new URI(this.file.getUrl()).toURL();
            System.out.println(url);
            URLConnection connection = url.openConnection();
            int fileSize = connection.getContentLength();
            int countBytes;
            int totalBytes = 0;
            byte[] data = new byte[1024];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(this.file.getPath());
            while (true) {
                countBytes = bufferedInputStream.read(data, 0, data.length);
                if (countBytes <= 0) break;
                fileOutputStream.write(data, 0, countBytes);
                totalBytes += countBytes;
                double percentage = 0.00;
                if (fileSize > 0)
                    percentage = (totalBytes * 1.0 / fileSize) * 100;
                DecimalFormat percentageWithTwoDecimal= new DecimalFormat("0.00");
                if(percentage<100)
                    this.file.setPercentage(String.valueOf(percentageWithTwoDecimal.format(percentage)));
                else this.file.setPercentage("100");
                this.manager.updateUI(file);
            }
            fileOutputStream.close();
            bufferedInputStream.close();
            this.file.setStatus("DONE");
        }
        catch (Exception e) {
            this.file.setStatus("FAILED");
            e.printStackTrace();
        }
        this.manager.updateUI(this.file);
    }
}
