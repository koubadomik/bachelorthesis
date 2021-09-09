package cz.fel.cvut.koubadom.onto.manager.dao.util;

public class Queries {
    public static String PATH_FINDING = "select ?person ?name ?surname ?username ?weight ?sub1 ?sub2 ?sub3 ?sub4 where {" +
            " {" +
            "        ?person ?knows ?k ;" +
            "        ?hasName ?name;" +
            "          ?hasUsername ?username;" +
            "        ?hasSurname ?surname ." +
            "         ?k ?hasWeight ?weight;" +
            "           ?hasConcept ?concept ." +
            "    }" +
            "    UNION" +
            " {" +
            "        ?person ?knows ?k ;" +
            "        ?hasName ?name;" +
            "          ?hasUsername ?username;" +
            "        ?hasSurname ?surname ." +
            "         ?k ?hasWeight ?weight;" +
            "           ?hasConcept ?sub1 ." +
            "        ?sub1 ?semanticRelation ?concept ." +
            "    }" +
            "    UNION" +
            "    {" +
            "        ?person ?knows ?k ;" +
            "        ?hasName ?name;" +
            "          ?hasUsername ?username;" +
            "        ?hasSurname ?surname ." +
            "    ?k ?hasWeight ?weight;" +
            "       ?hasConcept ?sub1 ." +
            "    ?sub1 ?semanticRelation ?sub2 ." +
            "?sub2 ?semanticRelation ?concept ." +
            "    }" +
            "UNION" +
            "    {" +
            "        ?person ?knows ?k ;" +
            "        ?hasName ?name;" +
            "          ?hasUsername ?username;" +
            "        ?hasSurname ?surname ." +
            "        ?k ?hasWeight ?weight;" +
            "           ?hasConcept ?sub1 ." +
            "        ?sub1 ?semanticRelation ?sub2 ." +
            "        ?sub2 ?semanticRelation ?sub3 ." +
            "        ?sub3 ?semanticRelation ?concept ." +
            "}  " +
            "    UNION" +
            "    {" +
            "        ?person ?knows ?k ;" +
            "        ?hasName ?name;" +
            "          ?hasUsername ?username;" +
            "        ?hasSurname ?surname ." +
            "        ?k ?hasWeight ?weight;" +
            "           ?hasConcept ?sub1 ." +
            "        ?sub1 ?semanticRelation ?sub2 ." +
            "        ?sub2 ?semanticRelation ?sub3 ." +
            "        ?sub3 ?semanticRelation ?sub4 ." +
            "        ?sub4 ?semanticRelation ?concept ." +
            "}  " +
//            "        UNION" +
//            "    {" +
//            "        ?person ?knows ?k ." +
//            "        ?k ?hasWeight ?weight;" +
//            "           ?hasConcept ?sub1 ." +
//            "        ?sub1 ?semanticRelation ?sub2 ." +
//            "        ?sub2 ?semanticRelation ?sub3 ." +
//            "        ?sub3 ?semanticRelation ?sub4 ." +
//            "        ?sub4 ?semanticRelation ?sub5 ." +
//            "        ?sub5 ?semanticRelation ?concept ." +
//            "}  " +
            "}";
}

