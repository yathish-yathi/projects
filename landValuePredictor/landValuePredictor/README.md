1. What is the Problem ?
  Land buyers struggle to indentify actual worth of land and land brokers misuse this to quote high land price 

2. How this application solve the problem ?
   This appliction is desinged based on theory that "price of the land depends on the needy land marks(Value Points) such as school, university , hospital, airport, bus stand, railway station... etc. and its distance from given land"
   Application will understand the influence of value points around the given land using the pre-defined land price data of nearby lands present in DB, then the Price of given land is predicted.

3. How this application works ?
   > First we will fetch pre-defined land-price data from DB within configured distance from the given land.
   > for every fetched land data we generate two Maps containing nuetralized distance of their value points and its price for that distace then
   this will be used to create and train simpleRegression object for every Value point Name(actual name of specific value point) and Value point Type(category of value point exm. school, airport)
   > When we have single value to train simpleRegression we can not use simpleRegression, in this case we will use cross multiplication, this will be tracked in another cross multiplier Map 
   > Then we will generate Nuetralized distance map for given land
   > Finally we traverse over Nuetralized distance map of given land and 
   > case 1 : if we have simpleRegression object trained with more than one value point data use that simpleRegression to predict price influeced by it
   > case 2 : if we have dont simpleRegression object trained with more than one value point data then check if simpleRegression of its type exits? if yes use that simpleRegression to predict price influeced by it
   > case 3 : if not then use cross multiplier Map and to get cross multiplier and predict price influeced
   > case 4 : we dont have enough data to predict price influece by that value point (skip and print on console)
   > As we traverse Keep adding influeced price by each value point around it to find Final Predicted Price and display it.

4. How to get started
  > Run SQL command to create and add data into DB (we can predict value only around given data points, so use your onw coordinate-price data) and configure DB access in applicaton.properties
  > start spring boot application and hit http://localhost:8080/landPrice (if running in local host)
  > Fill cordinate and area of land then submite  
