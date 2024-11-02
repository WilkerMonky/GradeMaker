// package com.datamonki.APIsCadastro.model;

// import java.time.LocalDateTime;
// import java.util.Collection;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import jakarta.persistence.Column; 
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;


// @Entity
// @Table(name = "usuario")
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// public class User implements UserDetails {  

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer id;

//     @Column(nullable = false, unique = true)
//     private String nome;

//     @Column(nullable = false, unique = true)
//     private String email;

//     @Column(nullable = false)
//     private String senha;

//     @Column(name = "data_cadastro", nullable = false, updatable = false)
//     private LocalDateTime data_cadastro;

//     @Column(name = "data_atualizacao", nullable = false)
//     private LocalDateTime data_atualizacao;

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return null;
//     }

//     @Override
//     public String getPassword() {
//         return senha;
//     }

//     @Override
//     public String getUsername() {
//         return email;
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }
//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }
// }