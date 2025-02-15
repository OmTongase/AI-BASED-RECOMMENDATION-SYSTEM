package Internship_Task;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductRecommendation {

    public static void main(String[] args) throws IOException, TasteException {

        // 1. Sample Data (User Preferences)
        List<Preference> preferences = new ArrayList<>();
        preferences.add(new Preference(1, 101, 5.0)); // User 1 likes item 101 (rating 5)
        preferences.add(new Preference(1, 102, 3.0));
        preferences.add(new Preference(1, 103, 4.0));
        preferences.add(new Preference(2, 101, 4.0));
        preferences.add(new Preference(2, 102, 2.0));
        preferences.add(new Preference(3, 102, 4.0));
        preferences.add(new Preference(3, 103, 5.0));
        preferences.add(new Preference(3, 104, 3.0)); // User 3 likes item 104

        // 2. Create Data Model
        DataModel model = new GenericDataModel(preferences);

        // 3. Define Neighborhood (How to find similar users)
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, model); // Consider 2 nearest neighbors

        // 4. Create Recommender
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood);

        // 5. Get Recommendations for a User (e.g., User 1)
        List<RecommendedItem> recommendations = recommender.recommend(1, 2); // Recommend 2 items for user 1

        // 6. Display Recommendations
        System.out.println("Recommendations for User 1:");
        for (RecommendedItem recommendation : recommendations) {
            System.out.println("Item " + recommendation.getItemID() + " (Score: " + recommendation.getValue() + ")");
        }


        // Example: Get all preferences of a user
        PreferenceArray userPrefs = model.getPreferencesFromUser(3);
        System.out.println("\nPreferences for User 3:");
        for (int i = 0; i < userPrefs.length(); i++) {
            Preference pref = userPrefs.get(i);
            System.out.println("Item " + pref.getItemID() + ": " + pref.getValue());
        }

    }
}