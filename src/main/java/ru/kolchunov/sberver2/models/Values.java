package ru.kolchunov.sberver2.models;

import javax.persistence.*;

@Entity
@Table(name = "values_table", schema = "testtask", catalog = "postgres")
public class Values {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "id_row", nullable = false)
    private Long idRow;
    @Basic
    @Column(name = "id_field", nullable = false)
    private Long idField;
    @Basic
    @Column(name = "value", nullable = false, length = -1)
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdRow() {
        return idRow;
    }

    public void setIdRow(Long idRow) {
        this.idRow = idRow;
    }

    public Long getIdField() {
        return idField;
    }

    public void setIdField(Long idField) {
        this.idField = idField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Values that = (Values) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idRow != null ? !idRow.equals(that.idRow) : that.idRow != null) return false;
        if (idField != null ? !idField.equals(that.idField) : that.idField != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idRow != null ? idRow.hashCode() : 0);
        result = 31 * result + (idField != null ? idField.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
