package model;

public class Music {
    private String idSong;
    private String nameSong;
    private String singer;
    private boolean like;

    public Music() {
    }

    public Music(String idSong, String nameSong, String singer, boolean like) {
        this.idSong = idSong;
        this.nameSong = nameSong;
        this.singer = singer;
        this.like = like;
    }

    public String getIdSong() {
        return idSong;
    }

    public void setIdSong(String idSong) {
        this.idSong = idSong;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
