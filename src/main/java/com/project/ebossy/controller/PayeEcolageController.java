package com.project.ebossy.controller;



import com.project.ebossy.model.Eleve;
import com.project.ebossy.model.PayeEcolage;
import com.project.ebossy.model.PeriodeEcolage;
import com.project.ebossy.repository.PayeEcolageRepository;
import com.project.ebossy.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.ebossy.model.Ecole;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/payeEcolage")
public class PayeEcolageController {


}