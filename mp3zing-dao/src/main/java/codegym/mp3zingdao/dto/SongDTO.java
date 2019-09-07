package codegym.mp3zingdao.dto;

import java.io.Serializable;

public class SongDTO implements Serializable {
    private Integer id;
    private String nameSong;
    private String descriptionSong;
    private String fileMp3;
    private String avatarSong;
    private String comment;
    private Boolean deleted;

//ko can khai bao constructor luon??
    public SongDTO() {
    }

    public SongDTO(Integer id, String nameSong, String descriptionSong, String fileMp3, String avatarSong, String comment, Boolean deleted) {
        this.id = id;
        this.nameSong = nameSong;
        this.descriptionSong = descriptionSong;
        this.fileMp3 = fileMp3;
        this.avatarSong = avatarSong;
        this.comment = comment;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getDescriptionSong() {
        return descriptionSong;
    }

    public void setDescriptionSong(String descriptionSong) {
        this.descriptionSong = descriptionSong;
    }

    public String getFileMp3() {
        return fileMp3;
    }

    public void setFileMp3(String fileMp3) {
        this.fileMp3 = fileMp3;
    }

    public String getAvatarSong() {
        return avatarSong;
    }

    public void setAvatarSong(String avatarSong) {
        this.avatarSong = avatarSong;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
