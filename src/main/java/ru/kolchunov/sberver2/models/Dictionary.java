package ru.kolchunov.sberver2.models;

import javax.persistence.*;

/**
 * Entity for dictionary_table
 */
@Entity
@Table(name = "dictionary_table", schema = "testtask", catalog = "postgres")
public class Dictionary {
    /**
     * Id dictionary
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Name dictionary
     */
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    /**
     * Code of the dictionary in external system
     */
    @Basic
    @Column(name = "code", nullable = false)
    private Long code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dictionary that = (Dictionary) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
