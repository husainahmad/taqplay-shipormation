package com.interview.taqplay.shipormation.util;

import java.awt.*;

public class LocationUtil {

    private LocationUtil() {
    }

    public static boolean isLocationInPolygon(double lat, double lon, double[][] polygonPoints) {
        if (polygonPoints==null || polygonPoints.length==0) {
            throw new IllegalArgumentException("Polygon point must not be null");
        }

        Polygon polygon = new Polygon();
        for (double[] point: polygonPoints) {
            polygon.addPoint((int) (point[0] * 1_000_000), (int) (point[1] * 1_000_000));
        }

        int scaledX = (int) (lat * 1_000_000);
        int scaledY = (int) (lon * 1_000_000);

        return polygon.contains(scaledX, scaledY);
    }

}
