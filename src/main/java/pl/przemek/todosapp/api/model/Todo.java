package pl.przemek.todosapp.api.model;

import javax.persistence.*;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private Boolean checked;
    private String user;

    public Todo() {
    }

    public Todo(String content, Boolean checked, String user) {
        this.content = content;
        this.checked = checked;
        this.user = user;
    }

    public Todo(Long id, String content, Boolean checked, String user) {
        this.id = id;
        this.content = content;
        this.checked = checked;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
