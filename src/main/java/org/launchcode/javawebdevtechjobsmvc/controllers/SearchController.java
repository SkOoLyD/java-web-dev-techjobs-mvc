package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

//     TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping("/results")
        public String displaySearchResults(Model model,@RequestParam String searchType,@RequestParam String searchTerm ) {
        ArrayList<Job> jobs;
        String radio = "searchType";

        if(searchType.toLowerCase().equals("all") && searchTerm.toLowerCase().equals("all") || searchTerm.toLowerCase().equals("")) {
            jobs = JobData.findAll();
            model.addAttribute("title"," searchType "+" searchTerm");
        } else{
            jobs = JobData.findByColumnAndValue("searchType","searchTerm");
            model.addAttribute("title"," + searchType " + "searchTerm");
        }
        model.addAttribute("radio",radio);
        model.addAttribute("columns",columnChoices);
        model.addAttribute("jobs", jobs);

        return "searchType";

    }
}
