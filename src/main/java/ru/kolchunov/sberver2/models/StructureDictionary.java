package ru.kolchunov.sberver2.models;

import javax.persistence.*;

@Entity
@Table(name = "structure_dictionary_table", schema = "testtask", catalog = "postgres")
public class StructureDictionaryTable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_field", nullable = false)
    private Long idField;
    @Basic
    @Column(name = "id_dictionary", nullable = false)
    private Long idDictionary;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "data_type", nullable = false, length = -1)
    private String dataType;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StructureDictionaryTable that = (StructureDictionaryTable) o;

        if (idField != null ? !idField.equals(that.idField) : that.idField != null) return false;
        if (idDictionary != null ? !idDictionary.equals(that.idDictionary) : that.idDictionary != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idField != null ? idField.hashCode() : 0;
        result = 31 * result + (idDictionary != null ? idDictionary.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        return result;
    }
}
