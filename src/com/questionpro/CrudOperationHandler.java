package com.questionpro;

import java.util.*;

public class CrudOperationHandler {

    public static Table createTable(String tableName, String columns) {
        Table table = new Table();
        table.setTableName(tableName);
        Map<String, ColumnMetadata> tableColumns = new HashMap<>();
        if (!tableName.isBlank()) {

            String columnString = columns.substring(1, columns.length() - 1);
            String[] splitStringArray = columnString.split(",");

            Arrays.stream(splitStringArray).forEach(s -> {
                        String[] columnDetail = s.strip().split(" ");
                        ColumnMetadata columnMetadata = new ColumnMetadata(columnDetail[1], 10);
                        tableColumns.put(columnDetail[0], columnMetadata);
                    }
            );
            table.setColumns(tableColumns);

            System.out.println("table name is : " + table.getTableName());
            Map<String, ColumnMetadata> columns1 = table.getColumns();

            columns1.forEach((key, value) -> {
                System.out.println("column name is  : " + key + " : column type is  : " + value.getColumnType() + " : size is :" + value.getColumnSize());
            });
            return table;

        } else {
            System.out.println("invalid table name.");
        }
        return null;
    }

    private static boolean validateColumnType(List columnList, String value, String columnType) {
        switch (columnType) {
            case "INT":
                if (Utils.isInt(value)) {
                    columnList.add(value);
                    return true;
                }
                return false;
            case "BOOL":
                if (Utils.isBoolean(value)) {
                    columnList.add(value);
                    return true;
                }
                return false;
            case "DOUBLE":
                if (Utils.isDouble(value)) {
                    columnList.add(value);
                    return true;
                }
                return false;
            case "TEXT":
                columnList.add(value);
                return true;
            default:
                return false;
        }
    }

    public static boolean insertRecord(Table table, String columns) {
        String tableName = table.getTableName();
        Map<String, List> rows = table.getRows();
        Map<String, ColumnMetadata> columnsMetadata = table.getColumns();
        columns = columns.substring(1, columns.length() - 1);
        List<String> columnValList = Arrays.asList(columns.split(","));

        try {
            for (String colVal : columnValList) {
                String[] values = colVal.strip().split(" ");
                String columnName = values[0];
                ColumnMetadata columnMetadata = columnsMetadata.get(columnName);
                List columnList = rows.get(columnName);
                columnList = Optional.ofNullable(columnList).orElseGet(ArrayList::new);
                if (!validateColumnType(columnList, values[1], columnMetadata.getColumnType())) {
                    return false;
                }
                rows.put(columnName, columnList);
                table.setRows(rows);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean fetchRecord(Table table, String columns) {
        Map<String, List> rows = table.getRows();
        try {
            columns = columns.substring(1, columns.length() - 1);
            if("*".equals(columns.strip())){
                for (Map.Entry row : rows.entrySet()){
                    System.out.println(row.getKey() + " : " +row.getValue());
                }
                return true;
            }
            List<String> columnList = Arrays.asList(columns.split(","));
            for (String column : columnList) {
                List colValues = rows.get(column);
                System.out.println(column + " : " + colValues);
            }
        } catch (Exception exception){
            return false;
        }
        return true;
    }
}
