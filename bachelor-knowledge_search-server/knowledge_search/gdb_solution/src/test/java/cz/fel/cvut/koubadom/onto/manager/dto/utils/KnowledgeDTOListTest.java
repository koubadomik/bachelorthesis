package cz.fel.cvut.koubadom.onto.manager.dto.utils;

import cz.fel.cvut.koubadom.onto.manager.dto.KnowledgeDTO;
import cz.fel.cvut.koubadom.onto.manager.model.PersonGDB;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author domia
 * created on 16.05.2019
 */
public class KnowledgeDTOListTest {


    @Test
    public void addsNoDuplicates() {
        //arrange
        PersonGDB p1 = new PersonGDB("Dominik", "Kouda", "koudadom");
        KnowledgeDTOList list = new KnowledgeDTOList();
        //act
        list.add(new KnowledgeDTO(p1, 0.5, new HashSet<>()));
        list.add(new KnowledgeDTO(p1, 0.4, new HashSet<>()));
        //assert
        assertEquals(1, list.size());
    }

    @Test
    public void addsOnlyHighest() {
        //arrange
        KnowledgeDTOList list = new KnowledgeDTOList();
        PersonGDB p1 = new PersonGDB("Dominik", "Kouda", "koudadom");
        PersonGDB p2 = new PersonGDB("Dominik2", "Kouda1", "koudadom1");
        PersonGDB p3 = new PersonGDB("Dominik1", "Kouda2", "koudadom2");
        //act
        list.add(new KnowledgeDTO(p1, 0.5, new HashSet<>()));
        list.add(new KnowledgeDTO(p2, 0.5, new HashSet<>()));
        list.add(new KnowledgeDTO(p3, 0.6, new HashSet<>()));
        list.add(new KnowledgeDTO(p1, 0.7, new HashSet<>()));
        //assert
        assertEquals(3, list.size());
        boolean success = false;
        for (KnowledgeDTO dto : list.getKnowledgeDTOs()) {
            if (dto.getOwner().equals(p1) && dto.getWeightOfKnowledge() == 0.7) {
                success = true;
            }
        }
        assertTrue(success);
    }
}