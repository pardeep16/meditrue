package in.ideveloper.meditrue;

/**
 * Created by pardeep on 22-07-2016.
 */
public class UploadContent {
    private  String imagePath;
    private String fileType;
    private String fileNotes;

    public UploadContent(String imagePath, String fileType, String fileNotes) {
        this.imagePath = imagePath;
        this.fileType = fileType;
        this.fileNotes = fileNotes;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileNotes() {
        return fileNotes;
    }

    public void setFileNotes(String fileNotes) {
        this.fileNotes = fileNotes;
    }
}
