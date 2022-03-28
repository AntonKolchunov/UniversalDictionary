package ru.kolchunov.sberver2.models;

import javax.persistence.*;

/**
 * Entity for row_values_table
 */
@Entity
@Table(name = "row_values_table", schema = "testtask", catalog = "postgres")
public class RowValues {
    /**
     * Id row
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Id dictionary
     */
    @Basic
    @Column(name = "id_dict", nullable = false)
    private Long idDict;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDict() {
        return idDict;
    }

    public void setIdDict(Long idDict) {
        this.idDict = idDict;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RowValues rowValues = (RowValues) o;

        if (id != null ? !id.equals(rowValues.id) : rowValues.id != null) return false;
        if (idDict != null ? !idDict.equals(rowValues.idDict) : rowValues.idDict != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idDict != null ? idDict.hashCode() : 0);
        return result;
    }
}
