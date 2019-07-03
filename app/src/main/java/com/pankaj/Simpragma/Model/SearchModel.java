package com.pankaj.Simpragma.Model;

import com.google.gson.annotations.SerializedName;
import com.pankaj.Simpragma.Constants.ResponseConstant;

import java.util.ArrayList;
import java.util.List;


public class SearchModel {
    @SerializedName("q")
    public String q;

    @SerializedName("from")
    public String from;

    @SerializedName("to")
    public String to;


    @SerializedName(ResponseConstant.HITS)
    public List<Hits> getHits = new ArrayList<>();


    public class Hits{


        public Recipe getRecipe() {
            return recipe;
        }

        public void setRecipe(Recipe recipe) {
            this.recipe = recipe;
        }

        @SerializedName(ResponseConstant.RECIPE)
        public Recipe recipe;


        public class Recipe {

            @SerializedName(ResponseConstant.LABEL)
            public String label;

            @SerializedName(ResponseConstant.IMAGE)
            public String image;

            @SerializedName(ResponseConstant.URL)
            public String url;
            @SerializedName(ResponseConstant.RATE)
            public String rate;


            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

    }


}
