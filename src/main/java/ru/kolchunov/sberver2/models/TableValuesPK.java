package ru.kolchunov.sberver2.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class TableValuesPK implements Serializable {
    @Column(name = "id_dictionary", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDictionary;
    @Column(name = "id_filed", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFiled;
    @Column(name = "id_row", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRow;

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

        TableValuesPK that = (TableValuesPK) o;

        if (idDictionary != null ? !idDictionary.equals(that.idDictionary) : that.idDictionary != null) return false;
        if (idFiled != null ? !idFiled.equals(that.idFiled) : that.idFiled != null) return false;
        if (idRow != null ? !idRow.equals(that.idRow) : that.idRow != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDictionary != null ? idDictionary.hashCode() : 0;
        result = 31 * result + (idFiled != null ? idFiled.hashCode() : 0);
        result = 31 * result + (idRow != null ? idRow.hashCode() : 0);
        return result;
    }
}
