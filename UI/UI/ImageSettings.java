package UI;

public class ImageSettings {
    public String path;
    public int x;
    public int y;
    public int width;
    public int height;

    public ImageSettings(String imagePath, int fromX, int fromY, int imageWidth, int imageHeight){
        x = fromX;
        path = imagePath;
        y = fromY;
        width = imageWidth;
        height = imageHeight;
    }

    public ImageSettings() {

    }

    public int GetX(){return x;}
    public int GetY(){return y;}
    public int GetWidth(){return width;}
    public int GetHeight(){return height;}

    public void SetX(int X){x = X;}
    public void SetY(int Y){y = Y;}
    public void SetWidth(int Width){width= Width;}
    public void SetHeight(int Height){height = Height;}

    public void SetImagePath(String Path) {path = Path;}
}
