package com.webapp.share4better.controller;


import com.webapp.share4better.model.Profile;
import com.webapp.share4better.sevice.AddUserService;
import com.webapp.share4better.sevice.IProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ProfileListController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileListController.class);

    @Autowired
    private IProfileService service;
    @Autowired
    private AddUserService userService;

    @PostMapping("/validateUser")
    public String getUserProfile(@RequestParam("userEmail") String userEmail, @RequestParam("password") String password, HttpServletRequest httpServletRequest) {
        Profile userProfile = new Profile();
        String redirect = null;
        
            Iterable<Profile> profiles = service.getUserProfile(userEmail);
            for (Profile profile : profiles) {
                if (profile.getUserPassword().equals(password)) {
                    httpServletRequest.getSession().setAttribute("userID", profile.getUserId());
                    httpServletRequest.getSession().setAttribute("userName", profile.getUserName());
                    return "redirect:/home.html";
                } else {
                    return "redirect:/index.html#id03";

                }

            }
        return "redirect:/index.html#id03";

    }


    @PostMapping("/signUp")
    public String signupUser(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail, @RequestParam("password") String password, HttpServletRequest httpServletRequest) {


        Iterable<Profile> profiles = service.getUserProfile(userEmail);
        for (Profile profile : profiles) {
            if (profile.getUserEmail().equals(userEmail)) {
                return "redirect:/index.html#id04";
            } else {
                Profile userProfile = new Profile();
                userProfile.setUserName(userName);
                userProfile.setUserEmail(userEmail);
                userProfile.setUserPassword(password);
                userProfile.setDonorStatus(true);
                userService.insertWithQuery(userProfile);
                httpServletRequest.getSession().setAttribute("userID", profile.getUserId());
                httpServletRequest.getSession().setAttribute("userName", profile.getUserName());
                return "redirect:/home.html";
            }

        }

        return "redirect:/index.html#id05";
    }



    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Object> getUser(HttpServletRequest httpServletRequest)
    {

        String userName = (String) httpServletRequest.getSession().getAttribute("userName");
        Map<String,Object> user = new HashMap<>();
        user.put("name", userName);

        return user;
    }
    @RequestMapping("/invalidate")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index.html";
    }



}
