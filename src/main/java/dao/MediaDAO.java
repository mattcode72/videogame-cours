package dao;

import bean.GameCategory;
import bean.Media;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MediaDAO {

    public Media getThumbnailByGameId(int gameId) {
        try {
            PreparedStatement sqlMedias = Database.connexion.prepareStatement("select media.id, media.path, media.is_thumbnail from media" +
                                                                                  " INNER JOIN media_type ON media_type.id = media.media_type_id" +
                                                                                  " where media.game_id = ? AND media_type.name = 'image' AND media.is_thumbnail = 1");

            sqlMedias.setInt(1, gameId);

            ResultSet rs = sqlMedias.executeQuery();

            if (rs.next()) {
                return new Media(rs.getInt("media.id"), rs.getBoolean("media.is_thumbnail"),rs.getString("media.path"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Media> getMediasPageByGameId(int gameId, String mediaType) {
        ArrayList<Media> medias = new ArrayList<>();
        try {
            PreparedStatement sqlMedias = Database.connexion.prepareStatement("select media.id, media.path, media.is_thumbnail from media" +
                                                                                  " INNER JOIN media_type ON media_type.id = media.media_type_id" +
                                                                                  " where media.game_id = ? AND media_type.name = ? AND media.is_thumbnail = 0");

            sqlMedias.setInt(1, gameId);
            sqlMedias.setString(2, mediaType);

            ResultSet rs = sqlMedias.executeQuery();

            while (rs.next()) {
                Media media = new Media(rs.getInt("media.id"), rs.getBoolean("media.is_thumbnail"), rs.getString("media.path"));

                medias.add(media);
            }

            return medias;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addMedia(Media media) {
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("INSERT INTO media (path, is_thumbnail, game_id, media_type_id) VALUES (?, ?, ?, ?)");

            sql.setString(1, media.getPath());
            sql.setBoolean(2, media.getThumbnail());
            sql.setInt(3, media.getGame().getId());
            sql.setInt(4, media.getMediaType().getId());

            sql.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
