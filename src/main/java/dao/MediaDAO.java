package dao;

import bean.GameCategory;
import bean.Media;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MediaDAO {

    public Media getThumbnailByGameId(int gameId) {
        try {
            PreparedStatement sqlMedias = Database.connexion.prepareStatement("select media.id, media.filename, media.path from media" +
                                                                                  " INNER JOIN media_type ON media_type.id = media.media_type_id" +
                                                                                  " where media.game_id = ? AND media_type.name = 'image'");

            sqlMedias.setInt(1, gameId);

            ResultSet rs = sqlMedias.executeQuery();

            if (rs.next()) {
                return new Media(rs.getInt("media.id"), rs.getString("media.filename"), rs.getString("media.path"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Media> getMediasPageByGameId(int gameId, String mediaType) {
        ArrayList<Media> medias = new ArrayList<>();
        try {
            PreparedStatement sqlMedias = Database.connexion.prepareStatement("select media.id, media.filename, media.path from media" +
                                                                                  " INNER JOIN media_type ON media_type.id = media.media_type_id" +
                                                                                  " where media.game_id = ? AND media_type.name = ?");

            sqlMedias.setInt(1, gameId);
            sqlMedias.setString(2, mediaType);

            ResultSet rs = sqlMedias.executeQuery();

            while (rs.next()) {
                Media media = new Media(rs.getInt("media.id"), rs.getString("media.filename"), rs.getString("media.path"));

                medias.add(media);
            }

            return medias;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
