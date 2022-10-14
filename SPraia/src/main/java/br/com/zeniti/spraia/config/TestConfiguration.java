package br.com.zeniti.spraia.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.zeniti.spraia.model.Denuncia;
import br.com.zeniti.spraia.model.Praia;
import br.com.zeniti.spraia.model.Usuario;
import br.com.zeniti.spraia.repository.DenunciaRepository;
import br.com.zeniti.spraia.repository.PraiaRepository;
import br.com.zeniti.spraia.repository.UsuarioRepository;
import br.com.zeniti.spraia.service.UsuarioService;

@Configuration
@Profile("dev")
public class TestConfiguration implements CommandLineRunner {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PraiaRepository praiaRepository;

    @Autowired
    DenunciaRepository denunciaRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsuarioService usuarioService;

    @Override
    public void run(String... args) throws Exception {

        usuarioService.save(
                new Usuario(
                        "Admin",
                        "admin@gmail.com",
                        "123",
                        LocalDate.parse("05/07/2002", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        usuarioService.save(
                new Usuario(
                        "Nicolas",
                        "nicolas@gmail.com",
                        "1234",
                        LocalDate.parse("27/12/2002", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        praiaRepository.saveAll(List.of(
                new Praia("Monguaguá","Uma bela praia, ótima de se visitar com a família", 8, "Está em boa condição para banho"),
                new Praia("Praia Grande","Uma das praias mais populares de São Paulo", 5, "Fica suja frequentemente nas temporadas de férias")
                ));

        denunciaRepository.saveAll(List.of(
                new Denuncia("Algumas sacolas de plástico encontradas na areia de Monguaguá", "Solicitada", "Plástico", "Normal"),
                new Denuncia("Mar infestado de canudos e latinhas em Praia Grande", "Solicitada", "Metal e Plástico", "Urgente")
        ));
    }

}
