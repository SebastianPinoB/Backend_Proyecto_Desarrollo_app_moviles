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

        // Actualizar campos, en este caso solo la contrasenia, podria ocuparse para la foto o el correo
        usuarioExistente.setContrasena(nuevaInfo.getContrasena());

        return usuarioRepository.save(usuarioExistente);
    }

    public void eliminarUsuario(int id) {
        // Verifica si el usuario existe antes de intentar eliminar
        if (!usuarioRepository.existsById(id)) {
            // Lanza una excepción si no existe
            throw new NoSuchElementException("Usuario con ID " + id + " no encontrado.");
        }
        usuarioRepository.deleteById(id);
    }
}
