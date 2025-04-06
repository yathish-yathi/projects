package com.project.landValuePredictor.model;

import java.util.List;

import lombok.Data;

@Data
public class PlacesResponse {
    private List<Place> places;
    private String type="";
    
    public String toString() {
    	String s = "";
    	for(Place p : places) {
    		s += p.toString()+", ";
    	}
    	return s;
    }
    
    @Data
    public static class Place {
    	private String id;
        private DisplayName displayName;
        private Location location;
        
        public String toString() {
        	//System.out.println(displayName.getText());
        	return getDisplayName().getText()+"-"+getLocation().latitude+","+getLocation().longitude;
        }
    }
    
    @Data
    public static class DisplayName {
        private String text;
        private String languageCode;
    }
    
    @Data
    public static class Location{
    	private double latitude;
    	private double longitude;
        
    }
}







