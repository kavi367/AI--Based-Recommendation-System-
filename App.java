package org.example;

import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;

import java.io.*;

public class App {
    public static void main(String[] args) throws Exception {
        DataModel model = new FileDataModel(new File("src/main/resources/ratings.csv"));

        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        for (RecommendedItem recommendation : recommender.recommend(1, 2)) {
            System.out.println("Recommended Item for User 1: " +
                               recommendation.getItemID() +
                               " (score: " + recommendation.getValue() + ")");
        }
    }
}
