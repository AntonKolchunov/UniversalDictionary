package ru.kolchunov.sberver2.models;

import javax.persistence.*;

@Entity
@Table(name = "table_values", schema = "testtask", catalog = "postgres")
@IdClass(TableValuesPK.class)
public class TableValues {
    @Id
    @Column(name = "id_dictionary", nullable = false)
    private Long idDictionary;
    @Id
    @Column(name = "id_filed", nullable = false)
    private Long idFiled;
    @Basic
    @Column(name = "value", nullable = false, length = -1)
    private String value;
    @Id
    @Column(name = "id_row", nullable = false)
    private Long idRow;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "id_dictionary",
                    referencedColumnName = "id_dictionary",insertable=false, updatable=false),
            @JoinColumn(
                    name = "id_filed",
                    referencedColumnName = "id_field",insertable=false, updatable=false)
    })
    private StructureDictionary structureDictionary;

    public StructureDictionary geStructureDictionary() {
        return structureDictionary;
    }

    public void setStructureDictionary(StructureDictionary structureDictionary) {
        this.structureDictionary = structureDictionary;
    }

    public Long getIdDictionary() {
        return idDictionary;
    }

    public void setIdDictionary(Long idDictionary) {
        this.idDictionary = idDictionary;
    }


    public Long getIdFiled() {
        return idFiled;
    }

    public void setIdFiled(Long idFiled) {
        this.idFiled = idFiled;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getIdRow() {
        return idRow;
    }

    public void setIdRow(Long idRow) {
        this.idRow = idRow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableValues that = (TableValues) o;

        if (idDictionary != null ? !idDictionary.equals(that.idDictionary) : that.idDictionary != null) return false;
        if (idFiled != null ? !idFiled.equals(that.idFiled) : that.idFiled != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (idRow != null ? !idRow.equals(that.idRow) : that.idRow != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDictionary != null ? idDictionary.hashCode() : 0;
        result = 31 * result + (idFiled != null ? idFiled.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (idRow != null ? idRow.hashCode() : 0);
        return result;
    }
}
