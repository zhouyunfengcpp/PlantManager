package doublee.plantmanager.entity;

import java.util.Date;

public class Image {
    private long imageId;
    private String imagePath;
    private Date time;

    public long getImageId(){
        return imageId;
    }

    public void setImageId(long imageId){
        this.imageId = imageId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
