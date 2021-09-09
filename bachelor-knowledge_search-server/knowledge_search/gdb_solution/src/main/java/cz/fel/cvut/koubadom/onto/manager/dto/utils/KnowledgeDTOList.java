package cz.fel.cvut.koubadom.onto.manager.dto.utils;

import cz.fel.cvut.koubadom.onto.manager.dto.KnowledgeDTO;

import java.util.ArrayList;

/**
 * Class that represents set of KnowledgeDTOs. If one KnowledgeDTO is inserted twice (it means has same PersonGDB in itself),
 * there will stay only the one with bigger metric in the set.
 */
public class KnowledgeDTOList {
    private ArrayList<KnowledgeDTO> knowledgeDTOs;

    public KnowledgeDTOList() {
        this.knowledgeDTOs = new ArrayList<>();
    }

    /**
     * If one KnowledgeDTO is inserted twice (it means has same PersonGDB in itself),
     * there will stay only the one with bigger metric in the set.
     *
     * @param dto
     */
    public void add(KnowledgeDTO dto) {
        //for this solution, we choose for each person in result only shortest path
        if (this.knowledgeDTOs.contains(dto)) {
            //Shorter path
            if (this.knowledgeDTOs.get(this.knowledgeDTOs.indexOf(dto)).getTransitiveConceptsURLs().size() > dto.getTransitiveConceptsURLs().size()) {
                //we want to add
                this.knowledgeDTOs.remove(dto);
                this.knowledgeDTOs.add(dto);
            }
            //Same path and lower weight
            if (this.knowledgeDTOs.get(this.knowledgeDTOs.indexOf(dto)).getTransitiveConceptsURLs().size() == dto.getTransitiveConceptsURLs().size()
                    && this.knowledgeDTOs.get(this.knowledgeDTOs.indexOf(dto)).getWeightOfKnowledge() < dto.getWeightOfKnowledge()) {
                //we want to replace
                this.knowledgeDTOs.remove(dto);
                this.knowledgeDTOs.add(dto);
            }
        } else {
            this.knowledgeDTOs.add(dto);
        }
    }

    public ArrayList<KnowledgeDTO> getKnowledgeDTOs() {
        return knowledgeDTOs;
    }

    public void setKnowledgeDTOs(ArrayList<KnowledgeDTO> knowledgeDTOs) {
        this.knowledgeDTOs = knowledgeDTOs;
    }

    public int size() {
        return knowledgeDTOs.size();
    }

}
