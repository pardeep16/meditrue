package in.ideveloper.meditrue;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by pardeep on 22-07-2016.
 */
public class UploadToServer extends IntentService {

    /*private String filePath;*/
    private String imagePath;
    private String fileType;
    private String notes;
    private static int count=0;

    Handler handler;
    public static ArrayList<UploadContent> listUpload=null;
    public static UploadFileViewActivity uploadFileViewActivity;

    public UploadToServer(){
        super("UploadToServer");

    }
    @Override
    protected void onHandleIntent(Intent intent) {
        try{
            System.out.println("Intent services");
            System.out.println(listUpload.size());
            int len = listUpload.size();
            int i = 0;
            while (i < len) {
                imagePath = listUpload.get(i).getImagePath();
                fileType = listUpload.get(i).getFileType();
                notes = listUpload.get(i).getFileNotes();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("upload :");
                        System.out.println(imagePath);
                        System.out.println(fileType);
                        System.out.println(notes);
                        Toast.makeText(UploadToServer.this, "upload: " + imagePath + "\n" + fileType + "\n" + notes, Toast.LENGTH_SHORT).show();
                    }
                });

                boolean isFileUpload = doFileUpload(imagePath, fileType, notes);
                if (isFileUpload) {
                    try {
                        System.out.println(listUpload.size());
                        uploadFileViewActivity.removeChild(i);
                    }
                    catch (NullPointerException e){
                        e.printStackTrace();
                    }

                    //listUpload.remove(i);
                }
                i++;
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }

    }

    public void setListUpload(ArrayList<UploadContent> listUpload, UploadFileViewActivity uploadFileViewActivity) {
        System.out.println("call");
        UploadToServer.listUpload = listUpload;
        this.uploadFileViewActivity=uploadFileViewActivity;
        if(uploadFileViewActivity!=null){
            System.out.println("object is not null");

        }
        else{
            System.out.println("oblect is null");

        }
    }

    private boolean doFileUpload(String filePath, String fileType, String notes) {

        /*int serverResponseCode = 0;

        String fileName = filePath;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(filePath);

        if (!sourceFile.isFile()) {
            Log.e("uploadFile", "Source File not exist :");
            return false;

        }
        else
        {
            try {

                // open a URL connection to the Servlet
                File file=new File(filePath);
                String fileName1=file.getName();

                FileInputStream fileInputStream = new FileInputStream(sourceFile);
               //URL url = new URL("http://xdeveloper.royalwebhosting.net/fileupload.php");

                URL url=new URL(LocalUrls.uploadUrl);
               // URL url=new URL("http://api.medimojo.in/photos/upload/");
                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"photos\";filename=\""
                                + fileName1 + "" + lineEnd);

                        dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                }

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if(serverResponseCode == 200){
                    System.out.println("File Upload Completed.");

                }
                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {

                ex.printStackTrace();
                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {

                e.printStackTrace();

                handler.post(new Runnable() {
                    public void run() {
                        //System.out.println("Got Exception : see logcat ");
                        Toast.makeText(UploadToServer.this, "Unable to upload File! Check Network connection",
                                Toast.LENGTH_SHORT).show();
                    }
                });

            }
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    Log.i("FileUpload", "FileUpload:RES Message: " + line);
                }
                rd.close();
            } catch (IOException ioex) {
                Log.e("FileUpload", "error: " + ioex.getMessage(), ioex);
            }
        }
        return true;*/
        
        
        
        
        
        
        /////////////////////////////////////
        HttpURLConnection connection = null;
        DataOutputStream outputStream = null;
        InputStream inputStream = null;

        String twoHyphens = "--";
        String boundary = "*****" + Long.toString(System.currentTimeMillis()) + "*****";
        String lineEnd = "\r\n";

        String result = "";

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;

        String[] q = filePath.split("/");
        int idx = q.length - 1;

        try {
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);

            String fileName1=file.getName();
            //String filesplit=fileName1.split(".")[0];
            fileName1="File-"+System.currentTimeMillis()+"."+"jpg";

            System.out.println("file new name :"+fileName1);
            
            URL url = new URL(LocalUrls.uploadUrl);
            connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("User-Agent", "Android Multipart HTTP Client 1.0");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data;name=\"photos\";filename=\""
                    + fileName1+ "\"" + lineEnd);
            outputStream.writeBytes("Content-Type: " + "multipart/form-data" + lineEnd);
            outputStream.writeBytes("Content-Transfer-Encoding: binary" + lineEnd);

            outputStream.writeBytes(lineEnd);

            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            while (bytesRead > 0) {
                outputStream.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            outputStream.writeBytes(lineEnd);

            // Upload POST Data
            

                /*outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                outputStream.writeBytes("Content-Disposition: form-data; name=\"photos\" ,filename=\""
                        + fileName1 + "" + lineEnd);
                outputStream.writeBytes("Content-Type: text/plain" + lineEnd);
                outputStream.writeBytes(lineEnd);
                outputStream.writeBytes(lineEnd);*/
            

            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);


            if (200 != connection.getResponseCode()) {

                //throw new CustomException("Failed to upload code:" + connection.getResponseCode() + " " + connection.getResponseMessage());
                System.out.println("Faied to connect");
                return false;
            }

            inputStream = connection.getInputStream();

            result = this.convertStreamToString(inputStream);

            fileInputStream.close();
            inputStream.close();
            outputStream.flush();
            outputStream.close();

            return true;
        } catch (Exception e) {
            System.out.println("Faied to connect");

            
        }
        return true;
    
    }

    private String convertStreamToString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler=new Handler();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        return START_NOT_STICKY;

    }

    /**********************************/








}
