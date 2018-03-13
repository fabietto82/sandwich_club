package com.udacity.sandwichclub.utils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;

        try {
            JSONObject objectJson = new JSONObject(json);

            JSONObject subObject = objectJson.optJSONObject("name");

            String mainName = subObject.optString("mainName");

            JSONArray alsoKnownAsJson = subObject.optJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<>();

            int i ;
            for (i=0 ; i<alsoKnownAsJson.length(); i++) {
                String alsoKnow = alsoKnownAsJson.optString(i);
                alsoKnownAs.add(alsoKnow);
            }

            String placeOfOrigin = objectJson.optString("placeOfOrigin");

            String description = objectJson.optString("description");

            String image = objectJson.optString("image");

            JSONArray ingredientsJson = objectJson.optJSONArray("ingredients");

            List<String> ingredientsList = new ArrayList<>();
            for (int j=0 ; j <ingredientsJson.length(); j++){
                String singleIngredient = ingredientsJson.optString(j);
                ingredientsList.add(singleIngredient);
            }

            sandwich = new Sandwich(mainName ,alsoKnownAs ,placeOfOrigin ,description ,image , ingredientsList);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("error" , "json data not loaded");
        }

        return sandwich;
    }
}