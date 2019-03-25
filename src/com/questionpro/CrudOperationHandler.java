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
                        ColumnMetadata columnMetadata = new ColumnMetadata(columnDetail[1], columnDetail.length>2 ? columnDetail[2].isBlank() ? 10:
                                Integer.parseInt(columnDetail[2]) : 10);
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
        try {
            Map<String, List> rows = table.getRows();
            Map<String, ColumnMetadata> columnsMetadata = table.getColumns();
            columns = columns.substring(1, columns.length() - 1);
            List<String> columnValList = Arrays.asList(columns.split(","));

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
                rows.entrySet().stream().forEach(r-> System.out.println(r.getKey() + " : " +r.getValue()));
                return true;
            }
            List<String> columnList = Arrays.asList(columns.split(","));
            columnList.stream().forEach(s -> System.out.println(s + " : " + rows.get(s)));
        } catch (Exception exception){
            return false;
        }
        return true;
    }
}
