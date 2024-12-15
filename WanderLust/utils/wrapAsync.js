module.exports = (fn) => {
    return (req, res, next)=>{
        console.log("reached wrap async")
        fn(req, res, next).catch(next)
    }
}