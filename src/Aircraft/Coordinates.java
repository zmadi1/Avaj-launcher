package Aircraft;

import java.util.Objects;

public class Coordinates {
    private  int longitude;
    private  int latitude;
    private  int height;

    Coordinates(int longitude, int latitude, int height){

        if (longitude < 0)
            longitude = 0;
        if (latitude < 0)
            latitude = 0;
        if (height > 0)
            height = 100;

        this.latitude = latitude;
        this.longitude = longitude;
        this.height = height;

    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return getLongitude() == that.getLongitude() &&
                getLatitude() == that.getLatitude() &&
                getHeight() == that.getHeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLongitude(), getLatitude(), getHeight());
    }
}

