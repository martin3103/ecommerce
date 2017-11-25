package sg.apptreme.e_commerce.util.common.sqlite.image;

import android.graphics.Bitmap;

/**
 * Created by martinluternainggolan on 9/13/16.
 */
public class ImageData {
    public String id;
    public Bitmap image;

    public ImageData(Bitmap image, String id) {
        this.image = image;
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
