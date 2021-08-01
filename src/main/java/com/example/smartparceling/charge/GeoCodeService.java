/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 01-Aug-21
 *   Time: 8:39 AM
 *   File: GeoCodeService.java
 */

package com.example.smartparceling.charge;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeoCodeService {

    public static List calculate(String address1) throws IOException, InterruptedException {

        ObjectMapper mapper = new ObjectMapper();
        GeoCoder geocoder = new GeoCoder();

        String response = geocoder.GeocodeSync(address1);
        JsonNode responseJsonNode = mapper.readTree(response);

        JsonNode items = responseJsonNode.get("items");

        for (JsonNode item : items) {
            JsonNode address = item.get("address");
            String label = address.get("label").asText();
            JsonNode position = item.get("position");

            String lat = position.get("lat").asText();
            String lng = position.get("lng").asText();
            return List.of(lat,lng);
        }
        return List.of();
    }
}