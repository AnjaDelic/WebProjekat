package projekat.FitnessCentar.entity;

public class TrenerZahteviDTO {
    String name,surname,username;
    Long id;

    public TrenerZahteviDTO() {
    }

    public TrenerZahteviDTO(String name, String surname, Long id, String username) {
        this.name = name;
        this.surname = surname;
        this.id=id;
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
