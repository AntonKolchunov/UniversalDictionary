package ru.kolchunov.sberver2.models;

import javax.persistence.*;

@Entity
@Table(name = "structure_dictionary_table", schema = "testtask", catalog = "postgres")
@IdClass(StructureDictionaryPK.class)
public class StructureDictionary {
    @Id
    @Column(name = "id_field", nullable = false)
    private Long idField;
    @Id
    @Column(name = "id_dictionary", nullable = false)
    private Long idDictionary;
    @Basic
    @Column(name = "name_field", nullable = false, length = -1)
    private String nameField;
    @Basic
    @Column(name = "data_type", nullable = false, length = -1)
    private String dataType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dictionary", insertable=false, updatable=false)
    //@JoinColumn(name = "fk_dictionary", referencedColumnName = "id")
    private Dictionary dictionary;

    public Dictionary getDictionary() {
        return this.dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
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

    public String getNameField() {
        return nameField;
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
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

        StructureDictionary that = (StructureDictionary) o;

        if (idField != null ? !idField.equals(that.idField) : that.idField != null) return false;
        if (idDictionary != null ? !idDictionary.equals(that.idDictionary) : that.idDictionary != null) return false;
        if (nameField != null ? !nameField.equals(that.nameField) : that.nameField != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idField != null ? idField.hashCode() : 0;
        result = 31 * result + (idDictionary != null ? idDictionary.hashCode() : 0);
        result = 31 * result + (nameField != null ? nameField.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        return result;
    }
}
