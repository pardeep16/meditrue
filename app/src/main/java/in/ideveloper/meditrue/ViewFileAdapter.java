package in.ideveloper.meditrue;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by pardeep on 25-07-2016.
 */
public class ViewFileAdapter extends RecyclerView.Adapter<ViewFileAdapter.CustomFileHolder> {

    public static ArrayList<ViewFileContent> viewFileContentArrayList=new ArrayList<ViewFileContent>();
    Context context;

    Bitmap bitmapFile;

    public ViewFileAdapter(Context applicationContext) {
        context=applicationContext;

    }

    @Override
    public CustomFileHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewfiles_cardview,parent,false);
        CustomFileHolder customFileHolder=new CustomFileHolder(view);

        return customFileHolder;
    }

    @Override
    public void onBindViewHolder(CustomFileHolder holder, int position) {
       // holder.fileImageView.setImageResource(viewFileContentArrayList.get(position).getImageData());
        System.out.println("Get");
        new DownloadImage(holder.fileImageView).execute(viewFileContentArrayList.get(position).getImageData());
       /* if(bitmapFile!=null){
            holder.fileImageView.setImageBitmap(bitmapFile);
        }*/
       // Picasso.with(context).load(viewFileContentArrayList.get(position).getImageData()).noFade().into(holder.fileImageView);

   /* if(viewFileContentArrayList.get(position).getImageData()!=null){
        Picasso.with(context)
                .load(viewFileContentArrayList.get(position).getImageData())
                .noFade()
                .into(holder.fileImageView);
    }*/
        /*Picasso.with(context)
                .load(viewFileContentArrayList.get(position).getImageData()) // thumbnail url goes here
                .resize(350, 250)
                .noFade()
                .into(holder.fileImageView);
*/

        holder.fileTypeTextView.setText(viewFileContentArrayList.get(position).getFileType());
        holder.dateTextView.setText(viewFileContentArrayList.get(position).getDateOfFile());
        holder.notesTextView.setText(viewFileContentArrayList.get(position).getNotesOfFile());
        holder.tagsTextView.setText(viewFileContentArrayList.get(position).getTagsOfFile());
    }

    @Override
    public int getItemCount() {
        return viewFileContentArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class CustomFileHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dateTextView,fileTypeTextView,notesTextView,tagsTextView;

        Button dialogButton,tagsButton,buttonClickFile;
        ImageView fileImageView;

        public CustomFileHolder(View itemView) {
            super(itemView);
            dateTextView=(TextView)itemView.findViewById(R.id.textViewDateOfFile);
            fileTypeTextView=(TextView)itemView.findViewById(R.id.textViewFileCategory);
            notesTextView=(TextView)itemView.findViewById(R.id.textViewNotes);
            tagsTextView=(TextView)itemView.findViewById(R.id.textViewTags);
            fileImageView=(ImageView)itemView.findViewById(R.id.imageViewFile);
            buttonClickFile=(Button)itemView.findViewById(R.id.buttonToFileView);
            buttonClickFile.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v==buttonClickFile){
                Toast.makeText(context, "Image Click", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void setViewFileContentArrayList(ArrayList<ViewFileContent> viewFileContentArrayList) {
        ViewFileAdapter.viewFileContentArrayList = viewFileContentArrayList;
    }

    private class DownloadImage extends AsyncTask<String,Void,String> {

        WeakReference<ImageView> imageViewWeakReference;
        public DownloadImage(ImageView holder) {
            imageViewWeakReference=new WeakReference<ImageView>(holder);

        }

        @Override
        protected String doInBackground(String... params) {
            String filePath=downloadUrl(params[0]);
            return filePath;
        }

        @Override
        protected void onPostExecute(String path) {
            super.onPostExecute(path);
            //bitmapFile=bitmap;
            ImageView imageView=imageViewWeakReference.get();
           // imageView.setImageBitmap(bitmap);


                try {
                    /*File file=new File(path);
                    FileInputStream fileInputStream=new FileInputStream(file);*/
                    /*String compPath=compressImage(path);
                    Bitmap bitmap=BitmapFactory.decodeFile(compPath);*/
                    File file=new File(path);
                    FileInputStream inputStream=new FileInputStream(file);
                    Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
                    imageView.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }


        }

        private String downloadUrl(String urlPath) {
            Bitmap myBitmap=null;
            //String filePath=context.getFilesDir()+"/"+System.currentTimeMillis()+".jpg";

            String dirPath=Environment.getExternalStorageDirectory().toString()+"/WorkIn/Images";
            String filePath=dirPath+System.currentTimeMillis()+".jpg";

            File dir=new File(dirPath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            try
            {
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();

                /*BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;

                myBitmap= BitmapFactory.decodeStream(input, null, options);*/

                myBitmap=BitmapFactory.decodeStream(input);

                File file=new File(filePath);
                System.out.println("Path is  :"+filePath);
                FileOutputStream fileOutputStream=new FileOutputStream(file);

                myBitmap.compress(Bitmap.CompressFormat.JPEG,50,fileOutputStream);
                /*ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();

                byte[] bytes=new byte[1024];
                while (input.read(bytes)!=-1){
                   //byteArrayOutputStream.write(bytes);
                    fileOutputStream.write(bytes);
                }
                fileOutputStream.flush();
                fileOutputStream.close();*/

                System.out.println("Downloading Completed");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            catch (OutOfMemoryError error){
                System.out.println("Out of memory error");
                error.printStackTrace();
            }
            return filePath;
        }


        public String compressImage(String imageUri) {

            String filePath = getRealPathFromURI(imageUri);
            Bitmap scaledBitmap = null;

            BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
            options.inJustDecodeBounds = true;
            Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

            int actualHeight = options.outHeight;
            int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

            float maxHeight = 816.0f;
            float maxWidth = 612.0f;
            float imgRatio = actualWidth / actualHeight;
            float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

            if (actualHeight > maxHeight || actualWidth > maxWidth) {
                if (imgRatio < maxRatio) {
                    imgRatio = maxHeight / actualHeight;
                    actualWidth = (int) (imgRatio * actualWidth);
                    actualHeight = (int) maxHeight;
                } else if (imgRatio > maxRatio) {
                    imgRatio = maxWidth / actualWidth;
                    actualHeight = (int) (imgRatio * actualHeight);
                    actualWidth = (int) maxWidth;
                } else {
                    actualHeight = (int) maxHeight;
                    actualWidth = (int) maxWidth;

                }
            }

//      setting inSampleSize value allows to load a scaled down version of the original image

            options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
            options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
       /* options.inPurgeable = true;
        options.inInputShareable = true;*/
            options.inTempStorage = new byte[16 * 1024];

            try {
//          load the bitmap from its path
                bmp = BitmapFactory.decodeFile(filePath, options);
            } catch (OutOfMemoryError exception) {
                exception.printStackTrace();

            }
            try {
                scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
            } catch (OutOfMemoryError exception) {
                exception.printStackTrace();
            }

            float ratioX = actualWidth / (float) options.outWidth;
            float ratioY = actualHeight / (float) options.outHeight;
            float middleX = actualWidth / 2.0f;
            float middleY = actualHeight / 2.0f;

            android.graphics.Matrix scaleMatrix=new android.graphics.Matrix();
            scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

            Canvas canvas = new Canvas(scaledBitmap);
            canvas.setMatrix(scaleMatrix);
            canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
            ExifInterface exif;
            try {
                exif = new ExifInterface(filePath);

                int orientation = exif.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION, 0);
                Log.d("EXIF", "Exif: " + orientation);
            /*Matrix matrix = new Matrix();*/
                android.graphics.Matrix matrix=new android.graphics.Matrix();
                if (orientation == 6) {
                    matrix.postRotate(90);
                    Log.d("EXIF", "Exif: " + orientation);
                } else if (orientation == 3) {
                    matrix.postRotate(180);
                    Log.d("EXIF", "Exif: " + orientation);
                } else if (orientation == 8) {
                    matrix.postRotate(270);
                    Log.d("EXIF", "Exif: " + orientation);
                }
                scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                        scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                        true);
            } catch (IOException e) {
                e.printStackTrace();
            }

            FileOutputStream out = null;
            String filename = getFilename();
            try {
                out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
                scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return filename;

        }

        public String getFilename() {
            File file = new File(Environment.getExternalStorageDirectory().getPath(), "Workin/Images/compress");
            if (!file.exists()) {
                file.mkdirs();
            }
            String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
            return uriSting;

        }
        private String getRealPathFromURI(String contentURI) {
            Uri contentUri = Uri.parse(contentURI);
            Cursor cursor = context.getContentResolver().query(contentUri, null, null, null, null);
            if (cursor == null) {
                return contentUri.getPath();
            } else {
                cursor.moveToFirst();
                int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                return cursor.getString(index);
            }
        }

        public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            if (height > reqHeight || width > reqWidth) {
                final int heightRatio = Math.round((float) height / (float) reqHeight);
                final int widthRatio = Math.round((float) width / (float) reqWidth);
                inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
            }
            final float totalPixels = width * height;
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;
            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }

            return inSampleSize;
        }
        }
}
