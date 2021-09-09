import KnowledgeServer from "../Components/KnowledgeServer"
import Utils from "../Utils";

class Search {
    constructor() {
        this.state = {
            resultList: null,
            dataList: null,
            actualSearchString: "",
            dataListStatus: "FETCHING CONCEPTS",
            dataListComponent : document.querySelector("#concepts"),
            searchComponent : document.querySelector("#concept-choice"),
            resultListComponent: document.querySelector("#result-list tbody"),
            statusComponent: document.querySelector("#status")
        };
        this.update();
        this._initDataListComponent();
        this._initSearchComponent();
    }

    _initDataListComponent(){
        KnowledgeServer.getAllConcepts(this.setDataList.bind(this), this.setDataListStatus.bind(this));
    }
    _initSearchComponent(){
        this.state.searchComponent.addEventListener("keydown", e =>{
            if(e.keyCode===13){
                e.preventDefault();
                if(typeof this.state.dataList.get(e.target.value) === 'undefined'){
                    this.setResultList(null);
                    this.setDataListStatus(e.target.value+" is not name of concept");
                    this.state.searchComponent.value = "";
                }else{
                    this.state.actualSearchString = e.target.value;
                    this.setDataListStatus("SEARCHING "+this.state.actualSearchString);
                    KnowledgeServer.getPeopleByKnowledge(this.state.dataList.get(e.target.value),
                        this.setResultList.bind(this), this.setDataListStatus.bind(this));
                    this.state.searchComponent.value = "";
                }

            }
        })
    }


    setDataListStatus(message){
        this.state.dataListStatus = message;
        this.update();
    }

    setDataList(dataList){
        this.state.dataList = new Map();
        dataList.forEach(concept => {
            this.state.dataList.set(concept.prefLabel, Utils.parseUri(concept.id).hash.substring(1));
        });

        this.setDataListStatus("OK");
        this.update();
    }

    setResultList(resultList){
        this.state.resultList = resultList;
        this.state.dataListStatus = "SEARCH RESULT SET - "+this.state.actualSearchString;
        this.update();
    }

    update() {
        this._updateStatus();
        this._updateDataList();
        this._updateResultList();
    }

    _updateStatus(){
        this.state.statusComponent.innerHTML = "";
        this.state.statusComponent.appendChild(document.createTextNode(this.state.dataListStatus));
    }

    _updateDataList(){
        if(this.state.dataList != null){
            let conceptList = Array.from(this.state.dataList.keys()).map(conceptName => {
                return new Option(conceptName, conceptName);
            });
            //in case of select element, we need to sort
            conceptList.sort(function(a,b) {
                let x = a.value.toLowerCase();
                let y = b.value.toLowerCase();
                return x < y ? -1 : x > y ? 1 : 0;
            });
            this.state.dataListComponent.innerHTML = "";
            conceptList.forEach(conceptElement => this.state.dataListComponent.appendChild(conceptElement));
        }
    }

    _updateResultList(){
        if(this.state.resultList != null ){
            if(this.state.resultList.length !== 0){
                this.state.resultList.sort(function(a,b){
                    return b.metric - a.metric; //from the highest to the lowest
                });
                let peopleList = this.state.resultList.map(result =>{
                    let row = document.createElement("tr");

                    let name = document.createElement("td");
                    name.appendChild(document.createTextNode(result.person.name+" "+result.person.surname));
                    row.appendChild(name);

                    let username = document.createElement("td");
                    username.appendChild(document.createTextNode(result.person.username));
                    row.appendChild(username);

                    let info = document.createElement("td");
                    info.appendChild(document.createTextNode(result.info));
                    row.appendChild(info);

                    let metric = document.createElement("td");
                    metric.appendChild(document.createTextNode(result.metric));
                    row.appendChild(metric);

                    return row;
                });
                this.state.resultListComponent.innerHTML = "";
                peopleList.forEach(personElement => this.state.resultListComponent.appendChild(personElement));
            }else{
                this.state.resultListComponent.innerHTML = "Nothing to show";
            }
        }else{
            this.state.resultListComponent.innerHTML = "Nothing to show";
        }
    }

}
export default Search;