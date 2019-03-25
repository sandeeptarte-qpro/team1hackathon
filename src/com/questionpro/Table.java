package com.questionpro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    private String tableName;
    private Map<String, ColumnMetadata> columns = new HashMap<>();
    private Map<String, List> rows = new HashMap<>();

    public Table() {
    }

    public Table(Map<String, ColumnMetadata> columns, Map<String, List> rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public Map<String, ColumnMetadata> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, ColumnMetadata> columns) {
        this.columns = columns;
    }

    public Map<String, List> getRows() {
        return rows;
    }

    public void setRows(Map<String, List> rows) {
        this.rows = rows;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
