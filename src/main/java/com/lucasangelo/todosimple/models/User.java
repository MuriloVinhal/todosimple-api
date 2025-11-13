package com.lucasangelo.todosimple.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = User.TABLE_NAME)
public class User {

        public interface CreateUser {
        }

        public interface UpdateUser {
        }

        public static final String TABLE_NAME = "users";

        @Id
        @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        private Long id;

        @Column(name = "username", unique = true, nullable = false, length = 50)
        @NotNull(groups = CreateUser.class)
        @NotEmpty(groups = CreateUser.class)
        @Size(groups = CreateUser.class, min = 2, max = 50)
        private String username;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @JsonAlias("password")
        @NotNull(groups = { CreateUser.class, UpdateUser.class })
        @NotEmpty(groups = { CreateUser.class, UpdateUser.class })
        @Size(groups = CreateUser.class, min = 8, max = 50)
        @Column(name = "password", nullable = false)
        private String password;


        @OneToMany(mappedBy = "user")
        private List<Task> tasks = new ArrayList<Task>();


        public User() {
        }

        public User(Long id,
                        @NotNull(groups = CreateUser.class) @NotEmpty(groups = CreateUser.class) @Size(groups = CreateUser.class, min = 2, max = 50) String username,
                        @NotNull(groups = { CreateUser.class, UpdateUser.class }) @NotEmpty(groups = { CreateUser.class,
                                        UpdateUser.class }) @Size(groups = CreateUser.class, min = 8, max = 50) String password) {
                this.id = id;
                this.username = username;
                this.password = password;
        }


        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        @Override
        public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((id == null) ? 0 : id.hashCode());
                return result;
        }

        @Override
        public boolean equals(Object obj) {
                if (this == obj)
                        return true;
                if (obj == null)
                        return false;
                if (getClass() != obj.getClass())
                        return false;
                User other = (User) obj;
                if (id == null) {
                        if (other.id != null)
                                return false;
                } else if (!id.equals(other.id))
                        return false;
                return true;
        }
        
        

}