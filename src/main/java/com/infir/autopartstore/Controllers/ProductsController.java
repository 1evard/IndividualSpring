package com.infir.autopartstore.Controllers;

import com.infir.autopartstore.CheckRoles;
import com.infir.autopartstore.Models.*;
import com.infir.autopartstore.Repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/adminProducts")
public class ProductsController {

    @Autowired
    private ProductsRepos productsRepos;

    @Autowired
    private StampRepos stampRepos;

    @Autowired
    private StampCarRepos stampCarRepos;

    @Autowired
    private CurrentBalanceRepos currentBalanceRepos;

    @Autowired
    private ProductContentRepos productContentRepos;

    @Autowired
    private BasketRepos basketRepos;

    @Autowired
    private OrderRepos orderRepos;

    @GetMapping()
    public String productsList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<Products> products = productsRepos.findAll();
        model.addAttribute("products",products);
        model.addAttribute("stamps",stampRepos.findAll());
        model.addAttribute("stampCars",stampCarRepos.findAll());
        Iterable<CurrentBalance> currentBalances = currentBalanceRepos.findAll();
        model.addAttribute("currentBalances",currentBalances);
        Iterable<ProductContent> productContents = productContentRepos.findAll();
        model.addAttribute("productContents",productContents);
        return "Products/main";
    }

    @GetMapping("/main")
    @PreAuthorize("hasAnyAuthority('USER')")
    public String basketList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<Basket> baskets = basketRepos.findAll();
        model.addAttribute("baskets",baskets);
        Iterable<Order> orders = orderRepos.findAll();
        model.addAttribute("orders",orders);
        Iterable<Products> products = productsRepos.findAll();
        model.addAttribute("products",products);
        return "Basket/main";
    }

    @GetMapping("/main/del/{basket}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public String basketDel(
            Basket basket, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        basketRepos.delete(basket);
        return "redirect:../";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
    public String productsAdd(Products products, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        model.addAttribute("stamps",stampRepos.findAll());
        model.addAttribute("stampCars",stampCarRepos.findAll());
        Iterable<CurrentBalance> currentBalances = currentBalanceRepos.findAll();
        model.addAttribute("currentBalances",currentBalances);
        Iterable<ProductContent> productContents = productContentRepos.findAll();
        model.addAttribute("productContents",productContents);
        return "Products/add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
    public String productsAdd(@ModelAttribute("products")@Valid Products products,
                              BindingResult bindingResult, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if(bindingResult.hasErrors()){
            Iterable<CurrentBalance> currentBalances = currentBalanceRepos.findAll();
            model.addAttribute("currentBalances",currentBalances);
            Iterable<ProductContent> productContents = productContentRepos.findAll();
            model.addAttribute("productContents",productContents);
            return "Products/add";
        }
        productsRepos.save(products);
        return "redirect:";
    }

    @GetMapping("/addToCard")
    public String productsAddToCard(Basket basket, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<Order> orders = orderRepos.findAll();
        model.addAttribute("orders",orders);
        Iterable<Products> products = productsRepos.findAll();
        model.addAttribute("products",products);
        Iterable<CurrentBalance> currentBalances = currentBalanceRepos.findAll();
        model.addAttribute("currentBalances",currentBalances);
        return "Products/addToCard";
    }

    @PostMapping("/addToCard")
    public String productsAddToCard(@ModelAttribute("basket")@Valid Basket basket,
                                    BindingResult bindingResult,Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if(bindingResult.hasErrors()){
            return "Products/addToCard";
        }

        basketRepos.save(basket);
        return "redirect:";
    }

    @GetMapping("/edit/{products}")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
    public String productsEdit(Products products, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        model.addAttribute("stamps",stampRepos.findAll());
        model.addAttribute("stampCars",stampCarRepos.findAll());
        Iterable<CurrentBalance> currentBalances = currentBalanceRepos.findAll();
        model.addAttribute("currentBalances",currentBalances);
        Iterable<ProductContent> productContents = productContentRepos.findAll();
        model.addAttribute("productContents",productContents);
        return "Products/edit";
    }

    @PostMapping("/edit/{products}")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
    public String productsPostEdit(
            @ModelAttribute("products") @Valid Products products,
            BindingResult bindingResult, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if (bindingResult.hasErrors()) {
            Iterable<CurrentBalance> currentBalances = currentBalanceRepos.findAll();
            model.addAttribute("currentBalances",currentBalances);
            Iterable<ProductContent> productContents = productContentRepos.findAll();
            model.addAttribute("productContents",productContents);
            return "Products/edit";
        }
        productsRepos.save(products);
        return "redirect:../";
    }

    @GetMapping("/show/{products}")
    public String productsShow(
            Products products,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        model.addAttribute("stamps",stampRepos.findAll());
        model.addAttribute("stampCars",stampCarRepos.findAll());
        Iterable<CurrentBalance> currentBalances = currentBalanceRepos.findAll();
        model.addAttribute("currentBalances",currentBalances);
        Iterable<ProductContent> productContents = productContentRepos.findAll();
        model.addAttribute("productContents",productContents);
        return "Products/show";
    }

    @GetMapping("/del/{products}")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
    public String productsDel(
            Products products, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        productsRepos.delete(products);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String productsFilter(@RequestParam(defaultValue = "") String titleproducts,
                             @RequestParam(required = false) boolean accurate_search,
                             Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if (!titleproducts.equals("")) {
            List<Products> result = accurate_search ? productsRepos.findByTitleproducts(titleproducts) : productsRepos.findByTitleproductsContains(titleproducts);
            model.addAttribute("result", result);
        }
        model.addAttribute("titleproducts", titleproducts);
        model.addAttribute("accurate_search", accurate_search);
        return "Products/filter";
    }
}
