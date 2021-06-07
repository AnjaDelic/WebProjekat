package projekat.FitnessCentar.entity;

public class TrenerZahteviDTO {
    String name,surname;
    Long id;

    public TrenerZahteviDTO(String name, String surname,Long id) {
        this.name = name;
        this.surname = surname;
        this.id=id;
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
