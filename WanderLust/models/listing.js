const mongoose = require("mongoose")

//define schema
const listingSchema = new mongoose.Schema({
    title : {
        type : String,
        required : true
    },
    description : String,
    image : {
        type : String,
        default : "https://img.freepik.com/free-photo/fantastic-seascape-with-ripples_1232-424.jpg",
        set : (v) => 
            v === ""
            ? "https://img.freepik.com/free-photo/fantastic-seascape-with-ripples_1232-424.jpg"
            : v,
    },
    price : Number,
    location : String,
    country : String
})

const Listing = mongoose.model("Listing", listingSchema)

module.exports = Listing;