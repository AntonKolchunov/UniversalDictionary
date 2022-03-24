package ru.kolchunov.sberver2.models;

import javax.persistence.*;

@Entity
@Table(name = "structure_table_values", schema = "testtask", catalog = "postgres")
public class StructureTableValues {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_value", nullable = false)
    private Long idValue;
    @Basic
    @Column(name = "id_row", nullable = false)
    private Long idRow;
    @Basic
    @Column(name = "id_field", nullable = false)
    private Long idField;

    public Long getIdValue() {
        return idValue;
    }

    public void setIdValue(Long idValue) {
        this.idValue = idValue;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StructureTableValues that = (StructureTableValues) o;

        if (idValue != null ? !idValue.equals(that.idValue) : that.idValue != null) return false;
        if (idRow != null ? !idRow.equals(that.idRow) : that.idRow != null) return false;
        if (idField != null ? !idField.equals(that.idField) : that.idField != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idValue != null ? idValue.hashCode() : 0;
        result = 31 * result + (idRow != null ? idRow.hashCode() : 0);
        result = 31 * result + (idField != null ? idField.hashCode() : 0);
        return result;
    }
}
