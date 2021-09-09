import Constants from "../Constants";
import Ajax from "./Ajax";

const URL_PORT = Constants.HOST+":"+Constants.PORT+"/";
// const GET_ALL_PERSONS = URL_PORT+"persons";
const GET_ALL_CONCEPTS = URL_PORT+"concepts";
const GET_PEOPLE_BY_KNOWLEDGE = URL_PORT+"/persons/queryKnowledge/";

class KnowledgeServer{


    constructor(){

    }

    getAllConcepts(onSuccess, onError){
        Ajax.get(GET_ALL_CONCEPTS, onSuccess, onError);
    }

    getPeopleByKnowledge(conceptId, onSuccess, onError){
        Ajax.get(GET_PEOPLE_BY_KNOWLEDGE+conceptId, onSuccess, onError);
    }

}

const INSTANCE = new KnowledgeServer();

export default INSTANCE;