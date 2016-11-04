package in.ideveloper.meditrue;

/**
 * Created by pardeep on 25-07-2016.
 */
public class ViewFileContent {
    private String dateOfFile="";
    private String fileType="";
    private String imageData;
    private String notesOfFile="";
    private String tagsOfFile="";

    public ViewFileContent(String dateOfFile, String fileType,String imageData, String notesOfFile, String tagsOfFile) {
        this.dateOfFile = dateOfFile;
        this.fileType = fileType;
        this.imageData = imageData;
        this.notesOfFile = notesOfFile;
        this.tagsOfFile = tagsOfFile;
    }

    public String getDateOfFile() {
        return dateOfFile;
    }

    public void setDateOfFile(String dateOfFile) {
        this.dateOfFile = dateOfFile;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public String getNotesOfFile() {
        return notesOfFile;
    }

    public void setNotesOfFile(String notesOfFile) {
        this.notesOfFile = notesOfFile;
    }

    public String getTagsOfFile() {
        return tagsOfFile;
    }

    public void setTagsOfFile(String tagsOfFile) {
        this.tagsOfFile = tagsOfFile;
    }
}