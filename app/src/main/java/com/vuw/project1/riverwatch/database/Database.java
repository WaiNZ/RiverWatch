package com.vuw.project1.riverwatch.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.vuw.project1.riverwatch.objects.Incident_Report;
import com.vuw.project1.riverwatch.objects.Nitrate_Report;
import com.vuw.project1.riverwatch.objects.Water_Report;

import java.util.ArrayList;

/**
 * Created by James on 6/08/2016.
 */
public class Database extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ArrayList<Incident_Report> getIncidentReportList(){
        ArrayList<Incident_Report> incidents = new ArrayList<>();
        Cursor cursor = getReadableDatabase().query("incident_report", null, null, null, null, null, "_id DESC");
        if(cursor != null){
            if (cursor.moveToFirst()) {
                do {
                    int idxId = cursor.getColumnIndex("_id");
                    int idxName = cursor.getColumnIndex("name");
                    int idxLocation = cursor.getColumnIndex("location");
                    int idxDate = cursor.getColumnIndex("date");
                    int idxDescription = cursor.getColumnIndex("description");
                    int idxImage = cursor.getColumnIndex("image");
                    incidents.add(new Incident_Report(
                            cursor.getLong(idxId),
                            cursor.getString(idxName),
                            cursor.getString(idxLocation),
                            cursor.getString(idxDate),
                            cursor.getString(idxDescription),
                            cursor.getString(idxImage)
                    ));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return incidents;
    }

    public Incident_Report getIncidentReportById(long id){
        Incident_Report incident = null;
        Cursor cursor = getReadableDatabase().query("incident_report", null, "_id = ?", new String[]{Long.toString(id)}, null, null, null);
        if(cursor != null){
            if (cursor.moveToFirst()) {
                do {
                    int idxId = cursor.getColumnIndex("_id");
                    int idxName = cursor.getColumnIndex("name");
                    int idxLocation = cursor.getColumnIndex("location");
                    int idxDate = cursor.getColumnIndex("date");
                    int idxDescription = cursor.getColumnIndex("description");
                    int idxImage = cursor.getColumnIndex("image");
                    incident = new Incident_Report(
                            cursor.getLong(idxId),
                            cursor.getString(idxName),
                            cursor.getString(idxLocation),
                            cursor.getString(idxDate),
                            cursor.getString(idxDescription),
                            cursor.getString(idxImage)
                    );
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return incident;
    }

    /**
     * Database database = new Database(MainActivity.this);
     long id = database.saveIncidentReport("test name", "location", 10, 10, "DD/MM/YYYY", "description", "img");
     ^how to call
     */
    public long saveIncidentReport(String name, String location, double latitude, double longitude, String date, String description, String image){
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("location", location);
        values.put("latitude", latitude);
        values.put("longitude", longitude);
        values.put("date", date);
        values.put("description", description);
        values.put("image", image);
        return getWritableDatabase().insert("incident_report", null, values);
    }

    public ArrayList<Nitrate_Report> getNitrateReportList(){
        ArrayList<Nitrate_Report> nitrateReports = new ArrayList<>();
        Cursor cursor = getReadableDatabase().query("nitrate_report", null, null, null, null, null, "_id DESC");
        if(cursor != null){
            if (cursor.moveToFirst()) {
                do {
                    int idxId = cursor.getColumnIndex("_id");
                    int idxName = cursor.getColumnIndex("name");
                    int idxLocation = cursor.getColumnIndex("location");
                    int idxDate = cursor.getColumnIndex("date");
                    int idxDescription = cursor.getColumnIndex("description");
                    int idxImage = cursor.getColumnIndex("image");
                    int idxNitrate = cursor.getColumnIndex("nitrate");
                    int idxNitrite = cursor.getColumnIndex("nitrite");
                    nitrateReports.add(new Nitrate_Report(
                            cursor.getLong(idxId),
                            cursor.getString(idxName),
                            cursor.getString(idxLocation),
                            cursor.getString(idxDate),
                            cursor.getString(idxDescription),
                            cursor.getString(idxImage),
                            cursor.getDouble(idxNitrate),
                            cursor.getDouble(idxNitrite)
                    ));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return nitrateReports;
    }

    public Nitrate_Report getNitrateReportById(long id){
        Nitrate_Report nitrateReport = null;
        Cursor cursor = getReadableDatabase().query("nitrate_report", null, "_id = ?", new String[]{Long.toString(id)}, null, null, null);
        if(cursor != null){
            if (cursor.moveToFirst()) {
                do {
                    int idxId = cursor.getColumnIndex("_id");
                    int idxName = cursor.getColumnIndex("name");
                    int idxLocation = cursor.getColumnIndex("location");
                    int idxDate = cursor.getColumnIndex("date");
                    int idxDescription = cursor.getColumnIndex("description");
                    int idxImage = cursor.getColumnIndex("image");
                    int idxNitrate = cursor.getColumnIndex("nitrate");
                    int idxNitrite = cursor.getColumnIndex("nitrite");
                    nitrateReport = new Nitrate_Report(
                            cursor.getLong(idxId),
                            cursor.getString(idxName),
                            cursor.getString(idxLocation),
                            cursor.getString(idxDate),
                            cursor.getString(idxDescription),
                            cursor.getString(idxImage),
                            cursor.getDouble(idxNitrate),
                            cursor.getDouble(idxNitrite)
                    );
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return nitrateReport;
    }

    /**
     * Database database = new Database(MainActivity.this);
     long id = database.saveIncidentReport("test name", "location", 10, 10, "DD/MM/YYYY", "description", "img");
     ^how to call
     */
    public long saveNitrateReport(String name, String location, double latitude, double longitude, String date, String description, String image, double nitrate, double nitrite){
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("location", location);
        values.put("latitude", latitude);
        values.put("longitude", longitude);
        values.put("date", date);
        values.put("description", description);
        values.put("image", image);
        values.put("nitrate", nitrate);
        values.put("nitrite", nitrite);
        return getWritableDatabase().insert("nitrate_report", null, values);
    }

    public ArrayList<Water_Report> getWaterReportList(){
        ArrayList<Water_Report> waterReports = new ArrayList<>();
        Cursor cursor = getReadableDatabase().query("water_report", null, null, null, null, null, "_id DESC");
        if(cursor != null){
            if (cursor.moveToFirst()) {
                do {
                    int idxId = cursor.getColumnIndex("_id");
                    int idxName = cursor.getColumnIndex("name");
                    int idxLocation = cursor.getColumnIndex("location");
                    int idxDate = cursor.getColumnIndex("date");
                    int idxDescription = cursor.getColumnIndex("description");
                    int idxImage = cursor.getColumnIndex("image");
                    int idxTemperature = cursor.getColumnIndex("temperature");
                    int idxPH = cursor.getColumnIndex("pH");
                    int idxConductivity = cursor.getColumnIndex("conductivity");
                    int idxTurbidity = cursor.getColumnIndex("turbidity");
                    waterReports.add(new Water_Report(
                            cursor.getLong(idxId),
                            cursor.getString(idxName),
                            cursor.getString(idxLocation),
                            cursor.getString(idxDate),
                            cursor.getString(idxDescription),
                            cursor.getString(idxImage),
                            cursor.getDouble(idxTemperature),
                            cursor.getDouble(idxPH),
                            cursor.getDouble(idxConductivity),
                            cursor.getDouble(idxTurbidity)
                    ));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return waterReports;
    }

    public Water_Report getWaterReportById(long id){
        Water_Report waterReport = null;
        Cursor cursor = getReadableDatabase().query("water_report", null, "_id = ?", new String[]{Long.toString(id)}, null, null, null);
        if(cursor != null){
            if (cursor.moveToFirst()) {
                do {
                    int idxId = cursor.getColumnIndex("_id");
                    int idxName = cursor.getColumnIndex("name");
                    int idxLocation = cursor.getColumnIndex("location");
                    int idxDate = cursor.getColumnIndex("date");
                    int idxDescription = cursor.getColumnIndex("description");
                    int idxImage = cursor.getColumnIndex("image");
                    int idxTemperature = cursor.getColumnIndex("temperature");
                    int idxPH = cursor.getColumnIndex("pH");
                    int idxConductivity = cursor.getColumnIndex("conductivity");
                    int idxTurbidity = cursor.getColumnIndex("turbidity");
                    waterReport = new Water_Report(
                            cursor.getLong(idxId),
                            cursor.getString(idxName),
                            cursor.getString(idxLocation),
                            cursor.getString(idxDate),
                            cursor.getString(idxDescription),
                            cursor.getString(idxImage),
                            cursor.getDouble(idxTemperature),
                            cursor.getDouble(idxPH),
                            cursor.getDouble(idxConductivity),
                            cursor.getDouble(idxTurbidity)
                    );
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return waterReport;
    }

    /**
     * Database database = new Database(MainActivity.this);
     long id = database.saveIncidentReport("test name", "location", 10, 10, "DD/MM/YYYY", "description", "img");
     ^how to call
     */
    public long saveWaterReport(String name, String location, double latitude, double longitude, String date, String description, String image, double temperature, double pH, double conductivity, double turbidity){
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("location", location);
        values.put("latitude", latitude);
        values.put("longitude", longitude);
        values.put("date", date);
        values.put("description", description);
        values.put("image", image);
        values.put("temperature", temperature);
        values.put("pH", pH);
        values.put("conductivity", conductivity);
        values.put("turbidity", turbidity);
        return getWritableDatabase().insert("water_report", null, values);
    }
}