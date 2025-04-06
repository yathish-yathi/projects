package com.project.landValuePredictor.model;

import java.util.ArrayList;

public class GeoCodeResponse{
	 public ArrayList<Result> results;
	 public String status;
	 
	 
	 public static class AddressComponent{
		 public String long_name;
		 public String short_name;
		 public ArrayList<String> types;
		}

	public static class Geometry{
		 public Location location;
		 public String location_type;
		 public Viewport viewport;
		}

	public static class Location{
		 public double lat;
		 public double lng;
		 public double latitude;
		 public double longitude;
		}

	public static class NavigationPoint{
		 public Location location;
		}

	public static class Northeast{
		 public double lat;
		 public double lng;
		}

	public static class Result{
		 public ArrayList<AddressComponent> address_components;
		 public String formatted_address;
		 public Geometry geometry;
		 public ArrayList<NavigationPoint> navigation_points;
		 public boolean partial_match;
		 public String place_id;
		 public ArrayList<String> types;
		}

	public static class Southwest{
		 public double lat;
		 public double lng;
		}

	public static class Viewport{
		 public Northeast northeast;
		 public Southwest southwest;
		}
	}