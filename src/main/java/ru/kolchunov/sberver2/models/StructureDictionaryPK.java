package ru.kolchunov.sberver2.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class StructureDictionaryPK implements Serializable {
    @Column(name = "id_field", nullable = false)
    @Id
    private Long idField;
    @Column(name = "id_dictionary", nullable = false)
    @Id
    private Long idDictionary;

    public StructureDictionaryPK(Long idField, Long idDictionary) {
        this.idField = idField;
        this.idDictionary = idDictionary;
    }

    public Long getIdField() {
        return idField;
    }

    public void setIdField(Long idField) {
        this.idField = idField;
    }

    public Long getIdDictionary() {
        return idDictionary;
    }

    public void setIdDictionary(Long idDictionary) {
        this.idDictionary = idDictionary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StructureDictionaryPK that = (StructureDictionaryPK) o;

        if (idField != null ? !idField.equals(that.idField) : that.idField != null) return false;
        if (idDictionary != null ? !idDictionary.equals(that.idDictionary) : that.idDictionary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idField != null ? idField.hashCode() : 0;
        result = 31 * result + (idDictionary != null ? idDictionary.hashCode() : 0);
        return result;
    }
}
