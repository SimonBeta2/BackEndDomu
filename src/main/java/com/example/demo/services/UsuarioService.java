package com.example.demo.services;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.UsuarioRepository;

@Service
public class UsuarioService{
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    public UsuarioModel crearUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
}
    
    public Optional<UsuarioModel> buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email);
}
    public UsuarioModel actualizarUsuario(Integer id, UsuarioModel datos) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
    }
        UsuarioModel usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(datos.getNombre());
        usuario.setEmail(datos.getEmail());
        usuario.setTelefono(datos.getTelefono());

        return usuarioRepository.save(usuario);
}

    public UsuarioModel obtenerPorId(Integer id) {
    return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
}

    public void eliminarUsuario(Integer id){
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
    }
        usuarioRepository.deleteById(id);
    }

    public UsuarioModel findOrCreateUser(String email, String name, String googleId) {
        return usuarioRepository.findByEmail(email)
                .orElseGet(() -> {
                    UsuarioModel newUser = new UsuarioModel();
                    newUser.setEmail(email);
                    newUser.setNombre(name);
                    newUser.setGoogleId(googleId);
                    return usuarioRepository.save(newUser);
                });
    }
}