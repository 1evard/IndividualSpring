package com.infir.autopartstore.Controllers;

import com.infir.autopartstore.CheckRoles;
import com.infir.autopartstore.Models.OrderStatus;
import com.infir.autopartstore.Repos.OrderStatusRepos;
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
@RequestMapping("/adminOrderStatus")
@PreAuthorize("hasAnyAuthority('EMPLOYEE')")
public class OrderStatusController {
    @Autowired
    private OrderStatusRepos orderStatusRepos;

    @GetMapping
    public String orderSList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<OrderStatus> orderStatuses = orderStatusRepos.findAll();
        model.addAttribute("status",orderStatuses);
        return "OrderStatus/main";
    }

    @GetMapping("/add")
    public String orderSAdd(OrderStatus orderStatus, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        return "OrderStatus/add";
    }

    @PostMapping("/add")
    public String orderSAdd(@ModelAttribute("orderStatus")@Valid OrderStatus orderStatus,
                          BindingResult bindingResult, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if(bindingResult.hasErrors()){
            return "OrderStatus/add";
        }
        orderStatusRepos.save(orderStatus);
        return "redirect:/adminOrderStatus";
    }

    @GetMapping("/edit/{orderStatus}")
    public String orderSEdit(OrderStatus orderStatus, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        return "OrderStatus/edit";
    }

    @PostMapping("/edit/{orderStatus}")
    public String orderSPostEdit(
            @ModelAttribute("orderStatus") @Valid OrderStatus orderStatus,
            BindingResult bindingResult,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if (bindingResult.hasErrors()) {
            return "OrderStatus/edit";
        }
        orderStatusRepos.save(orderStatus);
        return "redirect:../";
    }

    @GetMapping("/del/{orderStatus}")
    public String orderSDel(
            OrderStatus orderStatus, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        orderStatusRepos.delete(orderStatus);
        return "redirect:../";
    }
}
