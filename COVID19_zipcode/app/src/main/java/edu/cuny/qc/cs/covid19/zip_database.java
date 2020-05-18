package edu.cuny.qc.cs.covid19;

import android.content.res.AssetManager;
import android.content.Context;

import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;

public class zip_database {
    private zip_code[] database;
    private Sheet s;

    public zip_database(Context context){
        try{
            AssetManager manager = context.getAssets();
            InputStream input = manager.open("zip_code_database.xls");
            Workbook wb = Workbook.getWorkbook(input);
            s = wb.getSheet(0);

            int row = s.getRows();
            database = new zip_code[row-1];
            for(int i=1; i<row; i++){
                database[i-1] = new zip_code(s.getRow(i));
            }
        }catch (Exception e){
        }
    }

    public zip_code findZip(String zip){
        zip_code result = null;
        int pos = s.findCell(zip).getRow();
        if(pos > 0) result = database[pos-1];
        return result;
    }
    public boolean isNY(String zip){
        return findZip(zip).getState().equals("NY");
    }
}
