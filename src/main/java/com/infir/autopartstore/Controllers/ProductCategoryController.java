package com.infir.autopartstore.Controllers;

import com.infir.autopartstore.CheckRoles;
import com.infir.autopartstore.Models.ProductCategory;
import com.infir.autopartstore.Repos.ProductCategoryRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/adminProductCategory")
@PreAuthorize("hasAnyAuthority('EMPLOYEE')")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryRepos productCategoryRepos;

    @GetMapping()
    public String prodCategList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<ProductCategory> productCategories = productCategoryRepos.findAll();
        model.addAttribute("productCategories",productCategories);
        return "ProductCategory/main";
    }

    @GetMapping("/add")
    public String prodCategAdd(ProductCategory productCategory, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        return "ProductCategory/add";
    }

    @PostMapping("/add")
    public String prodCategAdd(@ModelAttribute("productCategory")@Valid ProductCategory productCategory,
                              BindingResult bindingResult, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if(bindingResult.hasErrors()){
            return "ProductCategory/add";
        }
        productCategoryRepos.save(productCategory);
        return "redirect:";
    }

    @GetMapping("/edit/{productCategory}")
    public String prodCategEdit(ProductCategory productCategory, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        return "ProductCategory/edit";
    }

    @PostMapping("/edit/{productCategory}")
    public String prodCategPostEdit(
            @ModelAttribute("productCategory") @Valid ProductCategory productCategory,
            BindingResult bindingResult, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if (bindingResult.hasErrors()) {
            return "ProductCategory/edit";
        }
        productCategoryRepos.save(productCategory);
        return "redirect:../";
    }

    @GetMapping("/del/{productCategory}")
    public String prodCategDel(
            ProductCategory productCategory, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        productCategoryRepos.delete(productCategory);
        return "redirect:../";
    }
}
