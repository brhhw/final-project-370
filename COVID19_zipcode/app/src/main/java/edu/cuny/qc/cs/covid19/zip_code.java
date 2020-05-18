package edu.cuny.qc.cs.covid19;

import jxl.Cell;

public class zip_code {
    private String zip, state, city, county;
    private double lat,lon;

    //getter
    public String getZip(){ return zip; }
    public String getState(){ return state; }
    public String getCity(){ return city; }
    public String getCounty(){ return county; }
    public double getLat(){ return lat; }
    public double getLon(){ return lon; }

    public zip_code(Cell[] cells){
        zip = cells[0].getContents();
        state = cells[6].getContents();
        city = cells[3].getContents();
        county = cells[7].getContents();
        lat = Double.parseDouble(cells[12].getContents());
        lon = Double.parseDouble(cells[13].getContents());
    }

    /*  distance formula
        dlon = lon2 - lon1
        dlat = lat2 - lat1
        a = (sin(dlat/2))^2 + cos(lat1) * cos(lat2) * (sin(dlon/2))^2
        c = 2 * atan2( sqrt(a), sqrt(1-a) )
        d = R * c (where R is the radius of the Earth)
    */
    // distance from this zipcode to input zipcode
    private double distance(zip_code zip){
        double dlon = lon - zip.getLon();
        double dlat = lat - zip.getLat();
        double a = Math.pow(Math.sin(dlat/2), 2) + Math.cos(lat) * Math.cos(lat) * Math.pow(Math.sin(dlon/2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return c;
    }
    public double distance_mile(zip_code zip){
        double radE = 3961;
        double d= radE * distance(zip);
        return d;
    }
    public double distance_km(zip_code zip){
        double radE = 6373;
        double d= radE * distance(zip);
        return d;
    }

}
