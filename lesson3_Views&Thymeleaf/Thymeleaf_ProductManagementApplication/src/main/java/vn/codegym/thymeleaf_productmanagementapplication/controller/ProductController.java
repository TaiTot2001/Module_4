package vn.codegym.thymeleaf_productmanagementapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.codegym.thymeleaf_productmanagementapplication.model.Product;
import vn.codegym.thymeleaf_productmanagementapplication.service.IProductService;
import vn.codegym.thymeleaf_productmanagementapplication.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private IProductService productService = new ProductService();

    @GetMapping("")
    public String index(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("products", productList);
        return "/index";
    }

    @GetMapping("/{id}/view")

    public String view(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/view";
    }

    @GetMapping("/create")

    public String create(Model model) {

        model.addAttribute("product", new Product());

        return "/create";

    }

    @PostMapping("/save")
    public String save(Product product, RedirectAttributes redirect) {
        product.setId((int) (Math.random() * 10000));
        productService.save(product);
        redirect.addFlashAttribute("success", "Added product successfully!");
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")

    public String update(@PathVariable("id") int id, Model model) {

        model.addAttribute("product", productService.findById(id));

        return "/update";

    }

    @PostMapping("/update")
    public String update(Product product, RedirectAttributes redirect) {
        productService.update(product.getId(), product);
        redirect.addFlashAttribute("success", "Updated product successfully!");
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")

    public String delete(@PathVariable("id") int id, Model model) {

        model.addAttribute("product", productService.findById(id));

        return "/delete";

    }

    @PostMapping("/delete")
    public String delete(Product product, RedirectAttributes redirect) {
        productService.remove(product.getId());
        redirect.addFlashAttribute("success", "Removed product successfully!");
        return "redirect:/products";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<Product> result = productService.searchByName(keyword);
        model.addAttribute("products", result);
        model.addAttribute("keyword", keyword);
        return "/index";
    }
}
