package com.questionpro;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CrudOperationHandler {

    public static Table createTable(String tableName, String columns){
        Table table = new Table();
        table.setTableName(tableName);
        Map<String, ColumnMetadata> tableColumns = new HashMap<>();
        if (!tableName.isEmpty()) {

            String columnString = columns.substring(1,columns.length()-1);
            String[] splitStringArray = columnString.split(", ");

            Arrays.stream(splitStringArray).forEach(s -> {
                        String[] columnDetail = s.split(" ");
                        ColumnMetadata columnMetadata = new ColumnMetadata(columnDetail[1], 10);
                        tableColumns.put(columnDetail[0], columnMetadata);
                    }
            );

            /*for(String column:splitStringArray) {
                String[] columnDetail = column.split(" ");

                ColumnMetadata columnMetadata = new ColumnMetadata(columnDetail[1], 10);
                tableColumns.put(columnDetail[0], columnMetadata);
            }*/
            table.setColumns(tableColumns);

            System.out.println("table name is : "+table.getTableName());
            Map<String, ColumnMetadata> columns1 = table.getColumns();

            columns1.forEach((key, value) -> {
                System.out.println("column name is  : " + key + " : column type is  : " + value.getColumnType() +" : size is :"+value.getColumnSize());
            });
            return table;

        } else {
            System.out.println("invalid table name.");
        }
        return null;
    }
}
