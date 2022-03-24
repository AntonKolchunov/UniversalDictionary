package ru.kolchunov.sberver2.models;

import javax.persistence.*;

@Entity
@Table(name = "table_values", schema = "testtask", catalog = "postgres")
public class TableValues {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_value", nullable = false)
    private Long idValue;
    @Basic
    @Column(name = "value", nullable = false, length = -1)
    private String value;

    public Long getIdValue() {
        return idValue;
    }

    public void setIdValue(Long idValue) {
        this.idValue = idValue;
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

        TableValues that = (TableValues) o;

        if (idValue != null ? !idValue.equals(that.idValue) : that.idValue != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idValue != null ? idValue.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
