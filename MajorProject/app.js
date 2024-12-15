const express = require("express")
const app =  express()
const ejs = require("ejs")
const mongoose = require("mongoose")
const path = require("path");
const methodOverride = require('method-override')
const ejsMate = require("ejs-mate")

const Listing = require("./models/listing.js");
const wrapAsync = require("./utils/wrapAsync.js");
const ExpressError = require("./utils/ExpressError.js");

app.listen(8080, ()=>{
    console.log("listening to port 8080")
})

const MONGO_URL = "mongodb://127.0.0.1:27017/wanderlust"

main()
    .then(()=>console.log('connected to DB'))
    .catch((err)=> console.log(err));

async function main() {
    await mongoose.connect(MONGO_URL);
}

app.set("view engine", "ejs");
app.set("views", path.join(__dirname,"views"))

app.use(express.urlencoded({extended : true}))
app.use(methodOverride('_method'))
app.use(express.static(path.join(__dirname,"/public")))

// use ejs-locals for all ejs templates:
app.engine("ejs", ejsMate);

app.get("/", (req, res)=>{
    res.send("basic response from root")
})

app.get("/listings/style.css", (req,res)=>{
    res.send("hitting wild card API")
    console.log("hitting wild card API")
})

app.get("/testListing", async (req, res)=>{
    let sampleListing = new Listing({
        title : "My new Villa",
        description : "By the beach",
        price : 1200,
        location : "baga Goa",
        country : "India"
    })

    await sampleListing.save().then(res => console.log(res)).catch(err => console.log(err))
    console.log("sample was saved")
    res.send("successfull testing")
})

// index route (home page) / all listing
app.get("/listings", async (req , res)=>{
    let allListings = await Listing.find()

    res.render("listings/index.ejs", {allListings})
})

//redirect to add new details
app.get("/listings/new", async (req, res)=>{

    res.render("listings/new.ejs")
})

// show individual details when link on home page is clicked
app.get("/listings/:id", async (req, res)=>{
    let {id} = req.params

    let listing = await Listing.findById(id);

    res.render("listings/show.ejs", {listing})
})

//create new
app.post("/listings", wrapAsync(
    async (req ,res)=>{
        // let {title, description, price, country, location} = req.body;
        //OR
         let list = req.body.listing 

         if(!list){
            throw new ExpressError(400, "Listing not found") // if request is missing listing in body (can be tried through postman by not sending body)
         }
     
        console.log(req.body)
     
         let newListing = new Listing(list)
         await newListing.save()
         
         res.redirect("/listings")
     }
))

//edit route
app.get("/listings/:id/edit", async (req, res)=>{
    let {id} = req.params

    let listing = await Listing.findById(id);

    res.render("listings/edit.ejs", {listing})
})

//update route
app.put("/listings/:id", async (req ,res)=>{
    let {id} = req.params
    let list = req.body.listing 

    if(!list){
        throw new ExpressError(400, "Listing not found") // if request is missing listing in body (can be tried through postman by not sending body)
     }
     
    await Listing.findByIdAndUpdate(id, {...list}) // ... => will deconstruct the object into key and value
     
     res.redirect(`/listings/${id}`)
 })

 //Delete listing
 app.delete("/listings/:id", async (req, res)=>{
    let {id} = req.params

    let deletedlisting = await Listing.findByIdAndDelete(id);

    console.log(deletedlisting)

    res.redirect("/listings")
})

app.all("*", (req, res, next)=>{
    next( new ExpressError(404, "page not found"))
})

app.use((err, req, res, next)=>{
    let {statusCode = 500, message = "something went wrong!"} = err
    res.status(statusCode).send(message)
})