package dao;

public class DaoFactory {
    
    public static UsuarioDao getUsuarioDao(){
        return new UsuarioDao();
    }
    public static EpPodcastDao getEpPodcastDao(){
        return new EpPodcastDao();
    }
    public static EpisodioOuvidoDao getEpisodioOuvidoDao(){
        return new EpisodioOuvidoDao();
    }
    
}