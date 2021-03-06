package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.App;
import modelo.EpPodcast;
import modelo.EpisodioOuvido;
import modelo.Usuario;
import util.ConnectionFactory;

public class EpisodioOuvidoDao extends Dao {
	public EpisodioOuvido salvar(EpisodioOuvido e) {
		
        try {
            
            
            PreparedStatement stmt = this.con.prepareStatement("insert into episodiosouvidos (idUsuario, idEpisodio) values (?,?)");
            stmt.setInt(1, e.getIdUsuario());
            stmt.setInt(2, e.getIdEpisodio());

           
            stmt.executeUpdate();
            //ResultSet rs = stmt.getGeneratedKeys();
                

            stmt.close();
            ConnectionFactory.closeConexao(this.con);
            

        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return e;
    }  
	public List<EpPodcast> getEpisodiosOuvidos(Integer id){
        try {
                   List<EpPodcast> eppodcast = new ArrayList<>();
                    PreparedStatement stmt = this.con.prepareStatement("select eppodcast.id, eppodcast.titulo, eppodcast.genero, eppodcast.autor, eppodcast.tema, eppodcast.faixaetaria from episodiosouvidos inner join eppodcast on idEpisodio = eppodcast.id where idUsuario = ?");
                    stmt.setInt(1, id);
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        EpPodcast e = new EpPodcast();
                        
                        e.setId(rs.getInt("id"));
                        e.setTitulo(rs.getString("titulo"));
                        e.setGenero(rs.getString("genero"));
                        e.setAutor(rs.getString("autor"));
                        e.setTema(rs.getString("tema"));
                        e.setFaixaEtaria(rs.getString("faixaetaria"));



                        eppodcast.add(e);
                    }
                    rs.close();
                    stmt.close();
                    ConnectionFactory.closeConexao(this.con);
                    return eppodcast;

                } catch (SQLException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
                }

    }
	
	public EpisodioOuvido buscarPorId(Integer idEpisodio, Integer idUsuario) {
        try {
        	EpisodioOuvido e = new EpisodioOuvido();
            

            PreparedStatement stmt = this.con.prepareStatement("select idusuario, idepisodio from episodiosouvidos where idepisodio=? and idusuario=?");
            stmt.setInt(1, idEpisodio);
            stmt.setInt(2, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {               
            	e.setIdUsuario(rs.getInt("idusuario"));
            	e.setIdEpisodio(rs.getInt("idepisodio"));
                
            }
            rs.close();
            stmt.close();
            ConnectionFactory.closeConexao(this.con);
            
            return e;

        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
	public Boolean delete(Integer idEpisodio, Integer idUsuario) {
        try {

            PreparedStatement stmt = this.con.prepareStatement("delete from episodiosouvidos where idepisodio=? and idusuario=?");
            stmt.setInt(1, idEpisodio);
            stmt.setInt(2, idUsuario);
            stmt.executeUpdate();

            stmt.close();
            ConnectionFactory.closeConexao(this.con);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
	public Boolean deleteEpisodios(Integer idEpisodio) {
        try {

            PreparedStatement stmt = this.con.prepareStatement("delete from episodiosouvidos where idepisodio=?");
            stmt.setInt(1, idEpisodio);
            stmt.executeUpdate();

            stmt.close();
            ConnectionFactory.closeConexao(this.con);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
	public EpisodioOuvido buscarEpisodiosPorId(Integer idEpisodio) {
        try {
        	EpisodioOuvido e = new EpisodioOuvido();
            

            PreparedStatement stmt = this.con.prepareStatement("select idusuario, idepisodio from episodiosouvidos where idepisodio=?");
            stmt.setInt(1, idEpisodio);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {               
            	e.setIdUsuario(rs.getInt("idusuario"));
            	e.setIdEpisodio(rs.getInt("idepisodio"));
                
            }
            rs.close();
            stmt.close();
            ConnectionFactory.closeConexao(this.con);
            
            return e;

        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
}
