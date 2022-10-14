package br.com.zeniti.spraia.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
@Entity
@Table(name = "T_USUARIO")
public class Usuario implements UserDetails {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome obrigatorio")
    @Column(name = "nm_usuario",nullable=false)
    private String nome;

    @NotBlank(message = "Email obrigatorio")
    @Column(name = "em_usuario", nullable=false)
    private String email;

    @NotBlank(message = "Senha obrigatoria")
    private String senha;

    @NotNull(message = "Data de nascimento obrigatoria")
    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;

     @ManyToMany(fetch = FetchType.EAGER)
     private List<Role> roles;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    } 

    public void setDataNascimento(LocalDate dataNascimento ) {
        this.dataNascimento = dataNascimento;
    }
     public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
         return this.roles;
     }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString(){
        return "Nome:" + nome + "Email:" + email;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}
