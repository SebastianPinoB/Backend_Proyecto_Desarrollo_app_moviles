package Backend_Proyecto_Desarrollo_app_moviles.Service;
import Backend_Proyecto_Desarrollo_app_moviles.Model.Usuario;
import Backend_Proyecto_Desarrollo_app_moviles.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service // Indicamos que es un componente de servicio de Spring
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Constructor
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(int id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con ID: " + id));
    }

    public Usuario actualizarUsuario(int id, Usuario nuevaInfo) {
        Usuario usuarioExistente = obtenerPorId(id);

        // Actualizar campos (se podría usar para el actualizar contrasenia y la foto)
        usuarioExistente.setContrasena(nuevaInfo.getContrasena());
        // usuarioExistente.setFotoPerfil(nuevaInfo.getFotoPerfil());

        return usuarioRepository.save(usuarioExistente);
    }

}
