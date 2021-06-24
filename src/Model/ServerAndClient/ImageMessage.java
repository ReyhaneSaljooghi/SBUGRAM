package Model.ServerAndClient;

public class ImageMessage extends Message{
    byte[]image;

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }
}
