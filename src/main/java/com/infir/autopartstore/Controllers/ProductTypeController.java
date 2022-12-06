package com.infir.autopartstore.Controllers;

import com.infir.autopartstore.CheckRoles;
import com.infir.autopartstore.Models.ProductCategory;
import com.infir.autopartstore.Models.ProductType;
import com.infir.autopartstore.Repos.ProductCategoryRepos;
import com.infir.autopartstore.Repos.ProductTypeRepos;
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
@RequestMapping("/adminProductType")
@PreAuthorize("hasAnyAuthority('EMPLOYEE')")
public class ProductTypeController {
    @Autowired
    private ProductTypeRepos productTypeRepos;

    @Autowired
    private ProductCategoryRepos productCategoryRepos;

    @GetMapping()
    public String prodTypeList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<ProductType> productTypes = productTypeRepos.findAll();
        model.addAttribute("productTypes",productTypes);
        model.addAttribute("productCategory",productCategoryRepos.findAll());
        return "ProductType/main";
    }

    @GetMapping("/add")
    public String prodTypeAdd(ProductType productType, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<ProductCategory> productCategories = productCategoryRepos.findAll();
        model.addAttribute("productCategory", productCategoryRepos.findAll());
        return "ProductType/add";
    }

    @PostMapping("/add")
    public String prodTypeAdd(@ModelAttribute("productType")@Valid ProductType productType,
                               BindingResult bindingResult, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if(bindingResult.hasErrors()){
            return "ProductType/add";
        }
        productTypeRepos.save(productType);
        return "redirect:";
    }

    @GetMapping("/edit/{productType}")
    public String prodTypeEdit(ProductType productType, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<ProductCategory> productCategories = productCategoryRepos.findAll();
        model.addAttribute("productCategory", productCategoryRepos.findAll());
        return "ProductType/edit";
    }

    @PostMapping("/edit/{productType}")
    public String prodTypePostEdit(
            @ModelAttribute("productType") @Valid ProductType productType,
            BindingResult bindingResult, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if (bindingResult.hasErrors()) {
            return "ProductType/edit";
        }
        productTypeRepos.save(productType);
        return "redirect:../";
    }

    @GetMapping("/del/{productType}")
    public String prodTypeDel(
            ProductType productType, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        productTypeRepos.delete(productType);
        return "redirect:../";
    }
}
