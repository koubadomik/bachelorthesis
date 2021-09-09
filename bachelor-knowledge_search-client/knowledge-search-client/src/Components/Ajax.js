class Ajax{

    constructor(){
    }

    get(url, onSuccess, onError){
        fetch(url)
            .then(function(response) {
                return response.json();
            })
            .then(function(myJson) {
                onSuccess(myJson);
            }).catch(message => onError(message));
    }


}

const INSTANCE = new Ajax();

export default INSTANCE;