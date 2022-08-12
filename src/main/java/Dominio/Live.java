package Dominio;

public class Live {
    private String live_updated;
    private String live_latitude;
    private String live_longitude;
    private String live_altitude;
    private String live_direction;
    private String live_speed_horizontal;
    private String live_speed_vertical;
    private String live_is_ground;

    public String getLive_updated(){return live_updated;}
    public String getLive_latitude(){return live_latitude;}
    public String getLive_longitude(){return live_longitude;}
    public String getLive_altitude(){return live_altitude;}
    public String getLive_direction(){return live_direction;}
    public String getLive_speed_horizontal(){return live_speed_horizontal;}
    public String getLive_speed_vertical(){return live_speed_vertical;}
    public String getLive_is_ground(){return live_is_ground;}

    public void setLive_updated(String a){this.live_updated = a;}
    public void setLive_latitude(String a){this.live_latitude = a;}
    public void setLive_longitude(String a){this.live_longitude = a;}
    public void setLive_altitude(String a){this.live_altitude = a;}
    public void setLive_direction(String a){this.live_direction = a;}
    public void setLive_speed_horizontal(String a){this.live_speed_horizontal = a;}
    public void setLive_speed_vertical(String a){this.live_speed_vertical = a;}
    public void setLive_is_ground(String a){this.live_is_ground = a;}
}
