import Constants from "./Constants";

class Utils{

    static parseUri(uri){
        let parser = document.createElement("a");
        parser.href = uri;
        return parser;
    }

}

export default Utils;