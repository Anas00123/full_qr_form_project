package com.project.qrform.service;

import org.springframework.stereotype.Service;

import com.project.qrform.entity.UserForm;
import com.project.qrform.repo.UserFormRepo;

@Service
public class UserFormService {

    private final UserFormRepo repo;

    // Allowed area (example)
    private static final double ALLOWED_LAT = 19.0760;
    private static final double ALLOWED_LNG = 72.8777;
    private static final double ALLOWED_RADIUS = 500; // meters

    private static final double EARTH_RADIUS = 6371e3; // meters

    public UserFormService(UserFormRepo repo) {
        this.repo = repo;
    }

    // Save user
    public void save(UserForm user) {
        repo.save(user);
    }

    // Haversine distance calculation
    public double distance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                 + Math.cos(lat1) * Math.cos(lat2)
                 * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // distance in meters
    }

    // Check if user is within allowed area
    public boolean isWithinAllowedArea(double userLat, double userLon) {
        return distance(userLat, userLon, ALLOWED_LAT, ALLOWED_LNG) <= ALLOWED_RADIUS;
    }
}
