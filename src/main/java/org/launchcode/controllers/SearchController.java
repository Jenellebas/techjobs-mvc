package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "", method=RequestMethod.GET)  //this displays the search.html form
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results", method=RequestMethod.GET)
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns", ListController.columnChoices);
       //brings in the searchType (location, etc) and searchTerm (KC, etc) from the html form
        if (searchType.equals("all")) {
           ArrayList<HashMap<String,String>> jobs =JobData.findByValue(searchTerm);
           //creates jobs which is an arraylist of hashmaps using the method in JobData which searches all columns for the Term
           model.addAttribute("title", "All Jobs");
           model.addAttribute("jobs", jobs);
           return "search";
        } else {
           ArrayList<HashMap<String,String>> jobs =JobData.findByColumnAndValue(searchType,searchTerm);
           //creates jobs which is an arraylist of hashmaps using the method in JobData searching by Type and Term
           model.addAttribute("title", "All");
           model.addAttribute("jobs", jobs);
           return "search";
       }
   }
}