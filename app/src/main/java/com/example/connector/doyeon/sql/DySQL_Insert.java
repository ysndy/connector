package com.example.connector.doyeon.sql;

import com.example.connector.doyeon.util.Util;

public class DySQL_Insert extends DySQL {

    /*
    ::활용법
      DySQL_Update sql = new DySQL_Update();
                sql.setTableName("product");
                String[][] data = {
                        {"name", name}
                        ,{"price", price}
                        ,{"fromto", fromTo}
                };
                sql.setfAndValues(data);
                sql.setWhere("code = '"+product.getCode()+"'");
                String query = sql.getQuery();
     */

    public String[][] fAndValues;

    public DySQL_Insert() {
    }

    public String getQuery() {
        String updateQuery = "Insert INTO " + tableName + " ";
        String setFieldQuery = "(";
        String setValueQuery = "VALUES(";
        for (int i = 0; i < fAndValues.length; i++) {
            setFieldQuery += fAndValues[i][0];
            setValueQuery += "'" + fAndValues[i][1] + "'";
            if (i != fAndValues.length - 1) {
                setFieldQuery += ", ";
                setValueQuery += ", ";
            } else {
                setFieldQuery += ") ";
                setValueQuery += ") ";
            }
        }

        String query;
        if (!where.equals(""))
            query = updateQuery + setFieldQuery + setValueQuery + " WHERE " + where;
        else query = updateQuery + setFieldQuery + setValueQuery;
        Util.printingLog(DySQL_Insert.this.getClass().getSimpleName(), query);
        return query;
    }

    public void setfAndValues(String[][] fAndValues) {
        this.fAndValues = fAndValues;
    }
}
