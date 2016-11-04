package in.ideveloper.meditrue;

/**
 * Created by pardeep on 16-10-2016.
 */
public class HomeGridContent {
    private String text;
    private int imageRes;

    public HomeGridContent(String text, int imageRes) {
        this.text = text;
        this.imageRes = imageRes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}
