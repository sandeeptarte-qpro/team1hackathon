package com.questionpro;

public class ColumnMetadata {
    private String columnType;
    private int columnSize;

    public ColumnMetadata() {
    }

    public ColumnMetadata(String columnType, int columnSize) {
        this.columnType = columnType;
        this.columnSize = columnSize;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }
}
